package dao;

import config.DatabaseConnection;
import model.Produk;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdukDAO {

    public static final int BATAS_MENIPIS = 10;

    public static final String FILTER_SEMUA = "Semua";
    public static final String FILTER_NORMAL = "Normal";
    public static final String FILTER_MENIPIS = "Menipis (≤" + BATAS_MENIPIS + ")";
    public static final String FILTER_HABIS = "Habis";

    private static final String PILIH_DENGAN_STOK =
            "SELECT p.barcode, p.nama, p.harga_beli, p.harga_jual, "
          + "COALESCE(SUM(s.stok), 0) AS stok_total "
          + "FROM produk p LEFT JOIN stok s ON s.produk_barcode = p.barcode ";

    private static final String SQL_CARI_BARCODE =
            PILIH_DENGAN_STOK + "WHERE p.barcode = ? GROUP BY p.barcode";

    private static final String SQL_SIMPAN =
            "INSERT INTO produk (barcode, nama, harga_beli, harga_jual, pengguna_username) "
          + "VALUES (?,?,?,?,?)";

    private static final String SQL_UBAH =
            "UPDATE produk SET nama = ?, harga_beli = ?, harga_jual = ? WHERE barcode = ?";

    private static final String SQL_PERNAH_TERJUAL =
            "SELECT 1 FROM detail_transaksi d "
          + "JOIN stok s ON s.id_stok = d.stok_id_stok "
          + "WHERE s.produk_barcode = ? LIMIT 1";

    public Produk cariByBarcode(String barcode) throws SQLException {
        return DatabaseConnection.query(SQL_CARI_BARCODE, ps -> {
            ps.setString(1, barcode);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? peta(rs) : null;
            }
        });
    }

    public List<Produk> cari(String keyword, String filterStok) throws SQLException {
        StringBuilder sql = new StringBuilder(PILIH_DENGAN_STOK).append("WHERE 1=1 ");
        List<String> kataKunci = new ArrayList<>();

        if (keyword != null && !keyword.isBlank()) {
            sql.append("AND (p.barcode LIKE ? OR p.nama LIKE ?) ");
            String pola = "%" + keyword.trim() + "%";
            kataKunci.add(pola);
            kataKunci.add(pola);
        }
        sql.append("GROUP BY p.barcode ");
        if (FILTER_MENIPIS.equals(filterStok)) {
            sql.append("HAVING stok_total BETWEEN 1 AND ").append(BATAS_MENIPIS).append(' ');
        } else if (FILTER_HABIS.equals(filterStok)) {
            sql.append("HAVING stok_total = 0 ");
        } else if (FILTER_NORMAL.equals(filterStok)) {
            sql.append("HAVING stok_total > ").append(BATAS_MENIPIS).append(' ');
        }
        sql.append("ORDER BY p.nama");

        return DatabaseConnection.query(sql.toString(), ps -> {
            for (int i = 0; i < kataKunci.size(); i++) {
                ps.setString(i + 1, kataKunci.get(i));
            }
            try (ResultSet rs = ps.executeQuery()) {
                List<Produk> hasil = new ArrayList<>();
                while (rs.next()) {
                    hasil.add(peta(rs));
                }
                return hasil;
            }
        });
    }

    public void simpan(Produk p, String pembuat) throws SQLException {
        DatabaseConnection.update(SQL_SIMPAN, ps -> {
            ps.setString(1, p.getBarcode());
            ps.setString(2, p.getNamaProduk());
            ps.setLong(3, p.getHargaBeli());
            ps.setLong(4, p.getHargaJual());
            ps.setString(5, pembuat);
        });
    }

    public void ubah(Produk p) throws SQLException {
        DatabaseConnection.update(SQL_UBAH, ps -> {
            ps.setString(1, p.getNamaProduk());
            ps.setLong(2, p.getHargaBeli());
            ps.setLong(3, p.getHargaJual());
            ps.setString(4, p.getBarcode());
        });
    }

    public boolean pernahDitransaksikan(String barcode) throws SQLException {
        return Boolean.TRUE.equals(DatabaseConnection.query(SQL_PERNAH_TERJUAL, ps -> {
            ps.setString(1, barcode);
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }));
    }

    public void hapus(String barcode) throws SQLException {
        DatabaseConnection.transaction(() -> {
            DatabaseConnection.update("DELETE FROM stok WHERE produk_barcode = ?",
                ps -> ps.setString(1, barcode));
            DatabaseConnection.update("DELETE FROM produk WHERE barcode = ?",
                ps -> ps.setString(1, barcode));
            return null;
        });
    }

    public int hitungProduk() throws SQLException {
        return ambilSatuInt("SELECT COUNT(*) FROM produk");
    }

    public int hitungMenipis() throws SQLException {
        return ambilSatuInt("SELECT COUNT(*) FROM (" + PILIH_DENGAN_STOK
                + "GROUP BY p.barcode HAVING stok_total BETWEEN 1 AND " + BATAS_MENIPIS + ") sub");
    }

    public int totalUnit() throws SQLException {
        return ambilSatuInt("SELECT COALESCE(SUM(stok), 0) FROM stok");
    }

    private int ambilSatuInt(String sql) throws SQLException {
        return DatabaseConnection.query(sql, ps -> {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        });
    }

    private Produk peta(ResultSet rs) throws SQLException {
        Produk p = new Produk(rs.getString("barcode"), rs.getString("nama"),
                rs.getLong("harga_beli"), rs.getLong("harga_jual"));
        p.setStokTotal(rs.getInt("stok_total"));
        return p;
    }
}
