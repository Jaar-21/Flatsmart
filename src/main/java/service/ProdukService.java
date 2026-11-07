package service;

import config.DatabaseConnection;
import dao.ProdukDAO;
import dao.StokDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Produk;
import model.Stok;
import util.Sesi;

public class ProdukService {

    private final ProdukDAO produkDAO = new ProdukDAO();
    private final StokDAO stokDAO = new StokDAO();

    // cari via barcode
    public Produk cariByBarcode(String barcode) throws SQLException {
        return produkDAO.cariByBarcode(barcode);
    }

    // cari produk
    public List<Produk> cari(String keyword, String filterStok) throws SQLException {
        return produkDAO.cari(keyword, filterStok);
    }

    // simpan produk baru
    public void simpanBaru(String barcode, String nama, long hargaBeli, long hargaJual,
                           int stokAwal, LocalDate kedaluwarsa) throws SQLException {
        if (barcode == null || barcode.isBlank()) {
            throw new IllegalArgumentException("Barcode wajib diisi.");
        }
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama produk wajib diisi.");
        }
        if (hargaBeli < 0) {
            throw new IllegalArgumentException("Harga beli tidak boleh negatif.");
        }
        if (hargaJual <= 0) {
            throw new IllegalArgumentException("Harga jual harus lebih dari 0.");
        }
        if (stokAwal < 0) {
            throw new IllegalArgumentException("Stok awal tidak boleh negatif.");
        }
        if (kedaluwarsa == null) {
            throw new IllegalArgumentException("Tanggal kedaluwarsa wajib diisi.");
        }
        if (kedaluwarsa.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Tanggal kedaluwarsa tidak boleh sudah lewat.");
        }

        String kode = barcode.trim();
        if (produkDAO.cariByBarcode(kode) != null) {
            throw new IllegalArgumentException("Barcode sudah terdaftar.");
        }

        DatabaseConnection.transaction(() -> {
            produkDAO.simpan(new Produk(kode, nama.trim(), hargaBeli, hargaJual), Sesi.username());
            stokDAO.simpan(new Stok(stokDAO.buatIdBerikutnya(), kode, stokAwal, kedaluwarsa));
            return null;
        });
    }

    // ubah produk
    public void ubah(String barcode, String nama, long hargaBeli, long hargaJual) throws SQLException {
        if (nama == null || nama.isBlank()) {
            throw new IllegalArgumentException("Nama produk wajib diisi.");
        }
        if (hargaBeli < 0) {
            throw new IllegalArgumentException("Harga beli tidak boleh negatif.");
        }
        if (hargaJual <= 0) {
            throw new IllegalArgumentException("Harga jual harus lebih dari 0.");
        }
        produkDAO.ubah(new Produk(barcode, nama.trim(), hargaBeli, hargaJual));
    }

    // hapus produk
    public void hapus(String barcode) throws SQLException {
        if (produkDAO.pernahDitransaksikan(barcode)) {
            throw new IllegalArgumentException(
                    "Produk ini pernah ditransaksikan, jadi tidak dapat dihapus. "
                  + "Habiskan stoknya atau ubah datanya bila perlu.");
        }
        produkDAO.hapus(barcode);
    }

    // batch per produk
    public List<Stok> cariBatch(String barcode) throws SQLException {
        return stokDAO.cariByBarcode(barcode);
    }

    // hitung jumlah produk
    public int hitungProduk() throws SQLException {
        return produkDAO.hitungProduk();
    }

    // hitung stok menipis
    public int hitungStokMenipis() throws SQLException {
        return produkDAO.hitungMenipis();
    }

    // hitung segera kedaluwarsa
    public int hitungDekatKedaluwarsa() throws SQLException {
        return stokDAO.hitungDekatKedaluwarsa(StokDAO.HARI_DEKAT_KEDALUWARSA);
    }

    // total unit stok
    public int totalUnit() throws SQLException {
        return produkDAO.totalUnit();
    }
}
