package dao;

import config.DatabaseConnection;
import model.Stok;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StokDAO {

    public static final int HARI_DEKAT_KEDALUWARSA = 14;

    private static final String PILIH_BATCH =
            "SELECT s.id_stok, s.stok, s.tanggal_exp, s.produk_barcode, p.nama AS nama_produk "
          + "FROM stok s JOIN produk p ON p.barcode = s.produk_barcode ";

    private static final String SQL_ID_TERAKHIR =
            "SELECT MAX(CAST(SUBSTRING(id_stok, 5) AS UNSIGNED)) FROM stok";

    private static final String SQL_SIMPAN =
            "INSERT INTO stok (id_stok, stok, tanggal_exp, produk_barcode) VALUES (?,?,?,?)";

    private static final String SQL_KURANGI =
            "UPDATE stok SET stok = stok - ? WHERE id_stok = ? AND stok >= ?";

    private static final String SQL_TANGGAL_DEKAT =
            "tanggal_exp BETWEEN CURDATE() AND DATE_ADD(CURDATE(), INTERVAL ? DAY)";

    public String buatIdBerikutnya() throws SQLException {
        Integer tertinggi = DatabaseConnection.query(SQL_ID_TERAKHIR, ps -> {
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getObject(1, Integer.class) : null;
            }
        });
        return String.format("stk-%03d", (tertinggi == null ? 0 : tertinggi) + 1);
    }

    public void simpan(Stok s) throws SQLException {
        DatabaseConnection.update(SQL_SIMPAN, ps -> {
            ps.setString(1, s.getIdStok());
            ps.setInt(2, s.getJumlah());
            ps.setDate(3, Date.valueOf(s.getTglKedaluwarsa()));
            ps.setString(4, s.getBarcode());
        });
    }

    public List<Stok> cariByBarcode(String barcode) throws SQLException {
        return DatabaseConnection.query(PILIH_BATCH + "WHERE s.produk_barcode = ? ORDER BY s.tanggal_exp",
            ps -> {
                ps.setString(1, barcode);
                return kumpulkan(ps);
            });
    }

    public Stok cariBatchTerkeluar(String barcode) throws SQLException {
        return DatabaseConnection.query(
                PILIH_BATCH + "WHERE s.produk_barcode = ? AND s.stok > 0 "
              + "ORDER BY (s.tanggal_exp < CURDATE()) ASC, s.tanggal_exp ASC LIMIT 1",
            ps -> {
                ps.setString(1, barcode);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next() ? peta(rs) : null;
                }
            });
    }

    public void kurangi(String idStok, int jumlah) throws SQLException {
        int baris = DatabaseConnection.update(SQL_KURANGI, ps -> {
            ps.setInt(1, jumlah);
            ps.setString(2, idStok);
            ps.setInt(3, jumlah);
        });
        if (baris == 0) {
            throw new SQLException("Stok batch " + idStok + " tidak mencukupi untuk " + jumlah + " unit.");
        }
    }

    public void pasangiPromosi(String idStok, String kodePromosi) throws SQLException {
        DatabaseConnection.update("UPDATE stok SET promosi_kode_promosi = ? WHERE id_stok = ?", ps -> {
            ps.setString(1, kodePromosi);
            ps.setString(2, idStok);
        });
    }

    public void lepasPromosi(String kodePromosi) throws SQLException {
        DatabaseConnection.update(
                "UPDATE stok SET promosi_kode_promosi = NULL WHERE promosi_kode_promosi = ?",
            ps -> ps.setString(1, kodePromosi));
    }

    public int hitungDekatKedaluwarsa(int hari) throws SQLException {
        return DatabaseConnection.query(
                "SELECT COUNT(*) FROM stok WHERE stok > 0 AND " + SQL_TANGGAL_DEKAT,
            ps -> {
                ps.setInt(1, hari);
                try (ResultSet rs = ps.executeQuery()) {
                    rs.next();
                    return rs.getInt(1);
                }
            });
    }

    public List<Stok> cariDekatKedaluwarsa(int hari) throws SQLException {
        return DatabaseConnection.query(
                PILIH_BATCH + "WHERE s.stok > 0 AND s." + SQL_TANGGAL_DEKAT
              + " ORDER BY s.tanggal_exp ASC",
            ps -> {
                ps.setInt(1, hari);
                return kumpulkan(ps);
            });
    }

    private List<Stok> kumpulkan(java.sql.PreparedStatement ps) throws SQLException {
        List<Stok> hasil = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                hasil.add(peta(rs));
            }
        }
        return hasil;
    }

    private Stok peta(ResultSet rs) throws SQLException {
        Stok s = new Stok(rs.getString("id_stok"), rs.getString("produk_barcode"),
                rs.getInt("stok"), rs.getDate("tanggal_exp").toLocalDate());
        s.setNamaProduk(rs.getString("nama_produk"));
        return s;
    }
}
