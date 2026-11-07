package service;

import config.DatabaseConnection;
import dao.PromosiDAO;
import dao.StokDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Promosi;
import model.Stok;
import util.Sesi;

public class PromosiService {

    private final PromosiDAO promosiDAO = new PromosiDAO();
    private final StokDAO stokDAO = new StokDAO();

    // kode promosi baru
    public String buatKode() throws SQLException {
        return promosiDAO.buatKodeBerikutnya();
    }

    // cari promosi
    public List<Promosi> cari(String keyword, LocalDate tanggal) throws SQLException {
        return promosiDAO.cari(keyword, tanggal);
    }

    // simpan promosi baru
    public void simpanBaru(String kode, String nama, String idStok, int diskonPersen,
                           LocalDate mulai, LocalDate selesai) throws SQLException {
        validasi(nama, idStok, diskonPersen, mulai, selesai);
        if (ada(kode)) {
            throw new IllegalArgumentException("Kode promosi " + kode + " sudah dipakai.");
        }

        DatabaseConnection.transaction(() -> {
            promosiDAO.simpan(new Promosi(kode, nama.trim(), idStok, diskonPersen, mulai, selesai),
                    Sesi.username());
            stokDAO.pasangiPromosi(idStok, kode);
            return null;
        });
    }

    // ubah promosi
    public void ubah(String kode, String nama, String idStok, int diskonPersen,
                     LocalDate mulai, LocalDate selesai) throws SQLException {
        validasi(nama, idStok, diskonPersen, mulai, selesai);

        DatabaseConnection.transaction(() -> {
            promosiDAO.ubah(new Promosi(kode, nama.trim(), idStok, diskonPersen, mulai, selesai));
            stokDAO.lepasPromosi(kode);
            stokDAO.pasangiPromosi(idStok, kode);
            return null;
        });
    }

    // hapus promosi
    public void hapus(String kode) throws SQLException {
        DatabaseConnection.transaction(() -> {
            stokDAO.lepasPromosi(kode);
            promosiDAO.hapus(kode);
            return null;
        });
    }

    // batch per produk
    public List<Stok> cariBatch(String barcode) throws SQLException {
        return stokDAO.cariByBarcode(barcode);
    }

    // hitung total promosi
    public int hitungTotal() throws SQLException {
        return promosiDAO.hitungTotal();
    }

    // hitung batch bertarget
    public int hitungBatchTarget() throws SQLException {
        return promosiDAO.hitungBatchTarget();
    }

    // hitung batch menipis
    public int hitungBatchDekatKedaluwarsa() throws SQLException {
        return stokDAO.hitungDekatKedaluwarsa(StokDAO.HARI_DEKAT_KEDALUWARSA);
    }

    // cek kode promosi ada
    private boolean ada(String kode) throws SQLException {
        return Boolean.TRUE.equals(DatabaseConnection.query(
                "SELECT 1 FROM promosi WHERE kode_promosi = ?", ps -> {
                    ps.setString(1, kode);
                    try (ResultSet rs = ps.executeQuery()) {
                        return rs.next();
                    }
                }));
    }

    // validasi data promosi
    private void validasi(String nama, String idStok, int diskonPersen,
                          LocalDate mulai, LocalDate selesai) {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama promosi wajib diisi.");
        }
        if (idStok == null || idStok.isBlank()) {
            throw new IllegalArgumentException("Batch stok target wajib dipilih.");
        }
        if (diskonPersen < 1 || diskonPersen > 100) {
            throw new IllegalArgumentException("Diskon harus 1-100%.");
        }
        if (mulai == null || selesai == null) {
            throw new IllegalArgumentException("Tanggal mulai dan selesai wajib diisi.");
        }
        if (selesai.isBefore(mulai)) {
            throw new IllegalArgumentException(
                    "Tanggal selesai harus sama atau setelah tanggal mulai.");
        }
    }
}
