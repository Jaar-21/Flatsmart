package dao;

import config.DatabaseConnection;
import model.DetailTransaksi;
import model.TagihanTransaksi;
import model.Transaksi;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransaksiDAO {


    private static final String DETAIL_BERHARGA =
            "FROM detail_transaksi d "
          + "JOIN stok s ON s.id_stok = d.stok_id_stok "
          + "JOIN produk p ON p.barcode = s.produk_barcode ";

    private static final String HARGA_SEBELUM_DISKON = "p.harga_jual * d.jumlah";

    private static final String PILIH_HEADER =
            "SELECT t.id_transaksi, t.metode_pembayaran, t.total_bayar, t.uang_diterima, "
          + "t.kembalian, t.tanggal_transaksi, t.pengguna_username, "
          + "pg.nama_pengguna AS nama_kasir, "
          + "CASE WHEN tp.id_transaksi IS NOT NULL THEN 'penjualan' "
          + "     WHEN tt.id_transaksi IS NOT NULL THEN 'tagihan' END AS jenis, "
          + "COALESCE(tt.nominal_tagihan, 0) AS nominal_tagihan, "
          + "CASE WHEN tt.id_transaksi IS NULL THEN NULL "
          + "     ELSE CONCAT_WS(' | ', tt.jenis_tagihan, tt.provider, tt.nama_pelanggan) "
          + "END AS keterangan_tagihan, "
          + "COALESCE((SELECT SUM(" + HARGA_SEBELUM_DISKON + ") " + DETAIL_BERHARGA
          + "WHERE d.transaksi_produk_id_transaksi = t.id_transaksi), 0) AS subtotal, "
          + "COALESCE((SELECT SUM(" + HARGA_SEBELUM_DISKON + " - d.subtotal) " + DETAIL_BERHARGA
          + "WHERE d.transaksi_produk_id_transaksi = t.id_transaksi), 0) AS total_diskon "
          + "FROM transaksi t "
          + "LEFT JOIN transaksi_produk tp ON tp.id_transaksi = t.id_transaksi "
          + "LEFT JOIN transaksi_tagihan tt ON tt.id_transaksi = t.id_transaksi "
          + "LEFT JOIN pengguna pg ON pg.username = t.pengguna_username ";

    private static final String PILIH_DETAIL =
            "SELECT d.transaksi_produk_id_transaksi, d.stok_id_stok, d.jumlah, "
          + "d.harga_satuan, d.subtotal, p.harga_jual AS harga_asli, p.nama AS nama_produk "
          + DETAIL_BERHARGA;

    private static final String SQL_SIMPAN_HEADER =
            "INSERT INTO transaksi (metode_pembayaran, total_bayar, uang_diterima, kembalian, "
          + "tanggal_transaksi, pengguna_username) VALUES (?,?,?,?,?,?)";

    private static final String SQL_SIMPAN_PRODUK =
            "INSERT INTO transaksi_produk (id_transaksi, jumlah_item) VALUES (?,?)";

    private static final String SQL_SIMPAN_DETAIL =
            "INSERT INTO detail_transaksi (jumlah, harga_satuan, subtotal, "
          + "transaksi_produk_id_transaksi, stok_id_stok) VALUES (?,?,?,?,?)";

    private static final String SQL_SIMPAN_TAGIHAN =
            "INSERT INTO transaksi_tagihan (id_transaksi, jenis_tagihan, no_pelanggan, "
          + "nama_pelanggan, nominal_tagihan, provider) VALUES (?,?,?,?,?,?)";

    private static final String SQL_AMBIL_TAGIHAN =
            "SELECT id_transaksi, jenis_tagihan, no_pelanggan, nama_pelanggan, "
          + "nominal_tagihan, provider FROM transaksi_tagihan WHERE id_transaksi = ?";

    public int simpanHeader(Transaksi t) throws SQLException {
        return DatabaseConnection.insertAmbilId(SQL_SIMPAN_HEADER, ps -> {
            ps.setString(1, t.getMetodeBayar());
            ps.setLong(2, t.getTotalBayar());
            ps.setLong(3, t.getUangDiterima());
            ps.setLong(4, t.getKembalian());
            ps.setTimestamp(5, Timestamp.valueOf(t.getWaktu()));
            ps.setString(6, t.getUsername());
        });
    }

    public void catatPenjualan(int idTransaksi, int jumlahUnit) throws SQLException {
        DatabaseConnection.update(SQL_SIMPAN_PRODUK, ps -> {
            ps.setInt(1, idTransaksi);
            ps.setInt(2, jumlahUnit);
        });
    }


    public void simpanDetail(DetailTransaksi d) throws SQLException {
        DatabaseConnection.update(SQL_SIMPAN_DETAIL, ps -> {
            ps.setInt(1, d.getJumlah());
            ps.setLong(2, d.getHargaSatuan());
            ps.setLong(3, d.getSubtotal());
            ps.setInt(4, d.getIdTransaksi());
            ps.setString(5, d.getIdStok());
        });
    }

    public List<String> daftarJenisTagihan() throws SQLException {
        return DatabaseConnection.query(
                "SELECT DISTINCT jenis_tagihan FROM transaksi_tagihan ORDER BY jenis_tagihan",
            ps -> {
                List<String> hasil = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        hasil.add(rs.getString(1));
                    }
                }
                return hasil;
            });
    }

    public List<String> daftarProviderTagihan(String jenis) throws SQLException {
        return DatabaseConnection.query(
                "SELECT DISTINCT provider FROM transaksi_tagihan WHERE jenis_tagihan = ? "
              + "ORDER BY provider",
            ps -> {
                ps.setString(1, jenis);
                List<String> hasil = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        hasil.add(rs.getString(1));
                    }
                }
                return hasil;
            });
    }

    public void simpanTagihan(TagihanTransaksi tg) throws SQLException {
        DatabaseConnection.update(SQL_SIMPAN_TAGIHAN, ps -> {
            ps.setInt(1, tg.getIdTransaksi());
            ps.setString(2, tg.getJenisLayanan());
            ps.setString(3, tg.getNomorPelanggan());
            ps.setString(4, tg.getNamaPelanggan());
            ps.setLong(5, tg.getNominal());
            ps.setString(6, tg.getProvider());
        });
    }

    public List<Transaksi> cariByPengguna(String username, LocalDate awal, LocalDate akhir,
                                          String jenis, String keyword) throws SQLException {
        StringBuilder sql = new StringBuilder(PILIH_HEADER).append("WHERE t.pengguna_username = ? ");
        List<Object> nilai = new ArrayList<>();
        nilai.add(username);

        if (awal != null) {
            sql.append("AND t.tanggal_transaksi >= ? ");
            nilai.add(Timestamp.valueOf(awal.atStartOfDay()));
        }
        if (akhir != null) {
            sql.append("AND t.tanggal_transaksi < ? ");
            nilai.add(Timestamp.valueOf(akhir.plusDays(1).atStartOfDay()));
        }
        if ("penjualan".equalsIgnoreCase(jenis)) {
            sql.append("AND tp.id_transaksi IS NOT NULL ");
        } else if ("tagihan".equalsIgnoreCase(jenis)) {
            sql.append("AND tt.id_transaksi IS NOT NULL ");
        }
        if (keyword != null && !keyword.isBlank()) {
            sql.append("AND (CAST(t.id_transaksi AS CHAR) LIKE ? OR tt.nama_pelanggan LIKE ?) ");
            String pola = "%" + keyword.trim() + "%";
            nilai.add(pola);
            nilai.add(pola);
        }
        sql.append("ORDER BY t.tanggal_transaksi DESC");

        final String sqlAkhir = sql.toString();
        return DatabaseConnection.query(sqlAkhir, ps -> {
            for (int i = 0; i < nilai.size(); i++) {
                ps.setObject(i + 1, nilai.get(i));
            }
            List<Transaksi> hasil = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(petaHeader(rs));
                }
            }
            return hasil;
        });
    }

    public List<Transaksi> cariSemua(LocalDate awal, LocalDate akhir) throws SQLException {
        return DatabaseConnection.query(
                PILIH_HEADER + "WHERE t.tanggal_transaksi >= ? AND t.tanggal_transaksi < ? "
              + "ORDER BY t.tanggal_transaksi ASC, t.id_transaksi ASC",
            ps -> {
                ps.setDate(1, Date.valueOf(awal));
                ps.setDate(2, Date.valueOf(akhir.plusDays(1)));
                List<Transaksi> hasil = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        hasil.add(petaHeader(rs));
                    }
                }
                return hasil;
            });
    }

    public List<DetailTransaksi> cariDetailSemua(LocalDate awal, LocalDate akhir) throws SQLException {
        return DatabaseConnection.query(
                PILIH_DETAIL + "JOIN transaksi t ON t.id_transaksi = d.transaksi_produk_id_transaksi "
              + "WHERE t.tanggal_transaksi >= ? AND t.tanggal_transaksi < ? "
              + "ORDER BY t.tanggal_transaksi ASC, d.transaksi_produk_id_transaksi ASC, p.nama ASC",
            ps -> {
                ps.setDate(1, Date.valueOf(awal));
                ps.setDate(2, Date.valueOf(akhir.plusDays(1)));
                List<DetailTransaksi> hasil = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        hasil.add(petaDetail(rs));
                    }
                }
                return hasil;
            });
    }

    public List<DetailTransaksi> cariDetail(int idTransaksi) throws SQLException {
        return DatabaseConnection.query(PILIH_DETAIL + "WHERE d.transaksi_produk_id_transaksi = ?",
            ps -> {
                ps.setInt(1, idTransaksi);
                List<DetailTransaksi> hasil = new ArrayList<>();
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        hasil.add(petaDetail(rs));
                    }
                }
                return hasil;
            });
    }

    public TagihanTransaksi cariTagihan(int idTransaksi) throws SQLException {
        return DatabaseConnection.query(SQL_AMBIL_TAGIHAN, ps -> {
            ps.setInt(1, idTransaksi);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) {
                    return null;
                }
                TagihanTransaksi tg = new TagihanTransaksi();
                tg.setIdTransaksi(rs.getInt("id_transaksi"));
                tg.setJenisLayanan(rs.getString("jenis_tagihan"));
                tg.setProvider(rs.getString("provider"));
                tg.setNomorPelanggan(rs.getString("no_pelanggan"));
                tg.setNamaPelanggan(rs.getString("nama_pelanggan"));
                tg.setNominal(rs.getLong("nominal_tagihan"));
                return tg;
            }
        });
    }

    private Transaksi petaHeader(ResultSet rs) throws SQLException {
        Transaksi t = new Transaksi();
        t.setIdTransaksi(rs.getInt("id_transaksi"));
        t.setUsername(rs.getString("pengguna_username"));
        t.setJenis(rs.getString("jenis"));
        t.setMetodeBayar(rs.getString("metode_pembayaran"));
        t.setWaktu(rs.getTimestamp("tanggal_transaksi").toLocalDateTime());
        t.setTotalBayar(rs.getLong("total_bayar"));
        t.setUangDiterima(rs.getLong("uang_diterima"));
        t.setKembalian(rs.getLong("kembalian"));

        long subtotal = rs.getLong("subtotal");
        t.setSubtotal(subtotal > 0 ? subtotal : rs.getLong("nominal_tagihan"));
        t.setTotalDiskon(rs.getLong("total_diskon"));

        String namaKasir = rs.getString("nama_kasir");
        t.setNamaKasir(namaKasir == null || namaKasir.isBlank() ? t.getUsername() : namaKasir);
        t.setKeteranganTagihan(rs.getString("keterangan_tagihan"));
        return t;
    }

    private DetailTransaksi petaDetail(ResultSet rs) throws SQLException {
        DetailTransaksi d = new DetailTransaksi();
        d.setIdTransaksi(rs.getInt("transaksi_produk_id_transaksi"));
        d.setIdStok(rs.getString("stok_id_stok"));
        d.setNamaProduk(rs.getString("nama_produk"));
        d.setJumlah(rs.getInt("jumlah"));
        d.setHargaSatuan(rs.getLong("harga_satuan"));
        d.setHargaSebelumDiskon(rs.getLong("harga_asli"));
        d.setSubtotal(rs.getLong("subtotal"));
        return d;
    }
}
