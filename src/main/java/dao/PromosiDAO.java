package dao;

import config.DatabaseConnection;
import model.Promosi;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PromosiDAO {

    private static final String PILIH_PROMOSI =
            "SELECT pr.kode_promosi, pr.nama, pr.diskon, pr.tanggal_mulai, pr.tanggal_selesai, "
          + "MIN(s.id_stok) AS id_stok, MIN(s.produk_barcode) AS produk_barcode, "
          + "MIN(p.nama) AS nama_produk "
          + "FROM promosi pr "
          + "LEFT JOIN stok s ON s.promosi_kode_promosi = pr.kode_promosi "
          + "LEFT JOIN produk p ON p.barcode = s.produk_barcode ";

    private static final String GRUP_PROMOSI = " GROUP BY pr.kode_promosi";

    private static final String SQL_KODE_TERAKHIR =
            "SELECT MAX(CAST(SUBSTRING(kode_promosi, 5) AS UNSIGNED)) FROM promosi";

    private static final String SQL_SIMPAN =
            "INSERT INTO promosi (kode_promosi, nama, diskon, tanggal_mulai, tanggal_selesai, "
          + "pengguna_username) VALUES (?,?,?,?,?,?)";

    private static final String SQL_UBAH =
            "UPDATE promosi SET nama = ?, diskon = ?, tanggal_mulai = ?, tanggal_selesai = ? "
          + "WHERE kode_promosi = ?";


    public String buatKodeBerikutnya() throws SQLException {
        Integer tertinggi = DatabaseConnection.query(SQL_KODE_TERAKHIR, ps -> {
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next() ? rs.getObject(1, Integer.class) : null;
            }
        });
        return String.format("prm-%03d", (tertinggi == null ? 0 : tertinggi) + 1);
    }

    public void simpan(Promosi p, String pembuat) throws SQLException {
        DatabaseConnection.update(SQL_SIMPAN, ps -> {
            ps.setString(1, p.getKodePromosi());
            ps.setString(2, p.getNamaPromosi());
            ps.setInt(3, p.getDiskonPersen());
            ps.setDate(4, Date.valueOf(p.getTglMulai()));
            ps.setDate(5, Date.valueOf(p.getTglSelesai()));
            ps.setString(6, pembuat);
        });
    }

    public void ubah(Promosi p) throws SQLException {
        DatabaseConnection.update(SQL_UBAH, ps -> {
            ps.setString(1, p.getNamaPromosi());
            ps.setInt(2, p.getDiskonPersen());
            ps.setDate(3, Date.valueOf(p.getTglMulai()));
            ps.setDate(4, Date.valueOf(p.getTglSelesai()));
            ps.setString(5, p.getKodePromosi());
        });
    }

    public void hapus(String kodePromosi) throws SQLException {
        DatabaseConnection.update("DELETE FROM promosi WHERE kode_promosi = ?",
            ps -> ps.setString(1, kodePromosi));
    }

    public List<Promosi> cari(String keyword, LocalDate tanggal) throws SQLException {
        StringBuilder sql = new StringBuilder(PILIH_PROMOSI).append("WHERE 1=1 ");
        List<String> kataKunci = new ArrayList<>();

        if (keyword != null && !keyword.isBlank()) {
            sql.append("AND (pr.kode_promosi LIKE ? OR pr.nama LIKE ? OR p.nama LIKE ?) ");
            String pola = "%" + keyword.trim() + "%";
            kataKunci.add(pola);
            kataKunci.add(pola);
            kataKunci.add(pola);
        }
        if (tanggal != null) {
            sql.append("AND pr.tanggal_mulai <= ? AND pr.tanggal_selesai >= ? ");
        }
        sql.append(GRUP_PROMOSI).append(" ORDER BY pr.tanggal_mulai DESC");

        return DatabaseConnection.query(sql.toString(), ps -> {
            int indeks = 1;
            for (String pola : kataKunci) {
                ps.setString(indeks++, pola);
            }
            if (tanggal != null) {
                ps.setDate(indeks++, Date.valueOf(tanggal));
                ps.setDate(indeks, Date.valueOf(tanggal));
            }
            return kumpulkan(ps);
        });
    }

    public Promosi cariAktifByStok(String idStok) throws SQLException {
        return DatabaseConnection.query(
                PILIH_PROMOSI + "WHERE s.id_stok = ? AND pr.tanggal_mulai <= CURDATE() "
              + "AND pr.tanggal_selesai >= CURDATE()" + GRUP_PROMOSI,
            ps -> {
                ps.setString(1, idStok);
                try (ResultSet rs = ps.executeQuery()) {
                    return rs.next() ? peta(rs) : null;
                }
            });
    }

    public int hitungTotal() throws SQLException {
        return hitung("SELECT COUNT(*) FROM promosi");
    }

    public int hitungBatchTarget() throws SQLException {
        return hitung("SELECT COUNT(*) FROM stok WHERE promosi_kode_promosi IS NOT NULL");
    }

    private int hitung(String sql) throws SQLException {
        return DatabaseConnection.query(sql, ps -> {
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                return rs.getInt(1);
            }
        });
    }

    private List<Promosi> kumpulkan(java.sql.PreparedStatement ps) throws SQLException {
        List<Promosi> hasil = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                hasil.add(peta(rs));
            }
        }
        return hasil;
    }

    private Promosi peta(ResultSet rs) throws SQLException {
        Promosi p = new Promosi(rs.getString("kode_promosi"), rs.getString("nama"),
                rs.getString("id_stok"), rs.getInt("diskon"),
                rs.getDate("tanggal_mulai").toLocalDate(),
                rs.getDate("tanggal_selesai").toLocalDate());
        p.setBarcode(rs.getString("produk_barcode"));
        p.setNamaProduk(rs.getString("nama_produk"));
        return p;
    }
}
