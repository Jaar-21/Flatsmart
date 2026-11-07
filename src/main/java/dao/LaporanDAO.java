package dao;

import config.DatabaseConnection;
import model.BarisAnalisis;
import model.PenjualanProduk;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class LaporanDAO {

    private static final String ANTAR_TANGGAL =
            "t.tanggal_transaksi >= ? AND t.tanggal_transaksi < ? ";

    private static final String DARI_DETAIL =
            "FROM detail_transaksi d "
          + "JOIN stok s ON s.id_stok = d.stok_id_stok "
          + "JOIN produk p ON p.barcode = s.produk_barcode "
          + "JOIN transaksi t ON t.id_transaksi = d.transaksi_produk_id_transaksi WHERE ";


    private static final String POTONGAN = "p.harga_jual * d.jumlah - d.subtotal";

    private static final String DARI_DETAIL_DENGAN_KASIR =
            "FROM detail_transaksi d "
          + "JOIN stok s ON s.id_stok = d.stok_id_stok "
          + "JOIN produk p ON p.barcode = s.produk_barcode "
          + "JOIN transaksi t ON t.id_transaksi = d.transaksi_produk_id_transaksi "
          + "LEFT JOIN pengguna pg ON pg.username = t.pengguna_username WHERE ";

    private static final String SQL_RIWAYAT_PRODUK =
            "SELECT d.transaksi_produk_id_transaksi AS id_transaksi, t.tanggal_transaksi, "
          + "t.metode_pembayaran, s.id_stok, d.jumlah, d.harga_satuan, d.subtotal, "
          + "p.harga_jual AS harga_asli, COALESCE(NULLIF(pg.nama_pengguna, ''), t.pengguna_username) AS nama_kasir "
          + DARI_DETAIL_DENGAN_KASIR + ANTAR_TANGGAL + "AND p.barcode = ? "
          + "ORDER BY t.tanggal_transaksi DESC, s.id_stok ASC";

    private static final String SQL_OMZET =
            "SELECT COALESCE(SUM(d.subtotal), 0) " + DARI_DETAIL + ANTAR_TANGGAL;

    private static final String SQL_JUMLAH_TRANSAKSI =
            "SELECT COUNT(*) FROM transaksi t "
          + "JOIN transaksi_produk tp ON tp.id_transaksi = t.id_transaksi WHERE " + ANTAR_TANGGAL;

    private static final String SQL_UNIT =
            "SELECT COALESCE(SUM(d.jumlah), 0) " + DARI_DETAIL + ANTAR_TANGGAL;

    private static final String SQL_DISKON =
            "SELECT COALESCE(SUM(" + POTONGAN + "), 0) " + DARI_DETAIL + ANTAR_TANGGAL;

    private static final String SQL_JENIS_PRODUK =
            "SELECT COUNT(DISTINCT p.barcode) " + DARI_DETAIL + ANTAR_TANGGAL;

    private static final String SQL_TREN =
            "SELECT DATE(t.tanggal_transaksi) AS tgl, SUM(d.subtotal) AS omzet " + DARI_DETAIL
          + ANTAR_TANGGAL + "GROUP BY DATE(t.tanggal_transaksi)";

    private static final String SQL_RINCIAN =
            "SELECT p.barcode, p.nama AS nama_produk, SUM(d.jumlah) AS unit, "
          + "SUM(d.subtotal) AS omzet, SUM(" + POTONGAN + ") AS diskon "
          + DARI_DETAIL + ANTAR_TANGGAL
          + "GROUP BY p.barcode, p.nama ORDER BY unit DESC";

    public long omzet(LocalDate awal, LocalDate akhir) throws SQLException {
        return jumlahSatuBaris(SQL_OMZET, awal, akhir);
    }

    public int jumlahTransaksi(LocalDate awal, LocalDate akhir) throws SQLException {
        return (int) jumlahSatuBaris(SQL_JUMLAH_TRANSAKSI, awal, akhir);
    }

    public int unitTerjual(LocalDate awal, LocalDate akhir) throws SQLException {
        return (int) jumlahSatuBaris(SQL_UNIT, awal, akhir);
    }

    public int jenisProdukTerjual(LocalDate awal, LocalDate akhir) throws SQLException {
        return (int) jumlahSatuBaris(SQL_JENIS_PRODUK, awal, akhir);
    }

    public long totalDiskon(LocalDate awal, LocalDate akhir) throws SQLException {
        return jumlahSatuBaris(SQL_DISKON, awal, akhir);
    }

    public Map<LocalDate, Long> trenOmzetHarian(LocalDate awal, LocalDate akhir) throws SQLException {
        Map<LocalDate, Long> tren = new LinkedHashMap<>();
        for (LocalDate hari = awal; !hari.isAfter(akhir); hari = hari.plusDays(1)) {
            tren.put(hari, 0L);
        }
        DatabaseConnection.query(SQL_TREN, ps -> {
            isiRentang(ps, awal, akhir);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    tren.put(rs.getDate("tgl").toLocalDate(), rs.getLong("omzet"));
                }
            }
            return null;
        });
        return tren;
    }

    public List<BarisAnalisis> rincianPerProduk(LocalDate awal, LocalDate akhir) throws SQLException {
        return DatabaseConnection.query(SQL_RINCIAN, ps -> {
            isiRentang(ps, awal, akhir);
            List<BarisAnalisis> hasil = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    hasil.add(new BarisAnalisis(rs.getString("barcode"),
                            rs.getString("nama_produk"), rs.getInt("unit"),
                            rs.getLong("omzet"), rs.getLong("diskon")));
                }
            }
            return hasil;
        });
    }

    public List<PenjualanProduk> riwayatProduk(String barcode, LocalDate awal, LocalDate akhir)
            throws SQLException {
        return DatabaseConnection.query(SQL_RIWAYAT_PRODUK, ps -> {
            isiRentang(ps, awal, akhir);
            ps.setString(3, barcode);
            List<PenjualanProduk> hasil = new ArrayList<>();
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    PenjualanProduk b = new PenjualanProduk();
                    b.setIdTransaksi(rs.getInt("id_transaksi"));
                    b.setWaktu(rs.getTimestamp("tanggal_transaksi").toLocalDateTime());
                    b.setMetodeBayar(rs.getString("metode_pembayaran"));
                    b.setNamaKasir(rs.getString("nama_kasir"));
                    b.setIdStok(rs.getString("id_stok"));
                    b.setJumlah(rs.getInt("jumlah"));
                    b.setHargaSatuan(rs.getLong("harga_satuan"));
                    b.setHargaSebelumDiskon(rs.getLong("harga_asli"));
                    b.setSubtotal(rs.getLong("subtotal"));
                    hasil.add(b);
                }
            }
            return hasil;
        });
    }

    private long jumlahSatuBaris(String sql, LocalDate awal, LocalDate akhir) throws SQLException {
        return DatabaseConnection.query(sql, ps -> {
            isiRentang(ps, awal, akhir);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getLong(1);
            }
        });
    }

    private void isiRentang(java.sql.PreparedStatement ps, LocalDate awal, LocalDate akhir)
            throws SQLException {
        ps.setDate(1, Date.valueOf(awal));
        ps.setDate(2, Date.valueOf(akhir.plusDays(1)));
    }
}
