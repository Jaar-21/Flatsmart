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

    public Produk cariByBarcode(String barcode) throws SQLException {
        return produkDAO.cariByBarcode(barcode);
    }

    public List<Produk> cari(String keyword, String filterStok) throws SQLException {
        return produkDAO.cari(keyword, filterStok);
    }

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
        if (hargaJual <= hargaBeli) {
            throw new IllegalArgumentException("Harga jual harus lebih tinggi dari harga beli.");
        }
        if (stokAwal < 1) {
            throw new IllegalArgumentException("Stok awal minimal 1 unit.");
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
        if (hargaJual <= hargaBeli) {
            throw new IllegalArgumentException("Harga jual harus lebih tinggi dari harga beli.");
        }
        produkDAO.ubah(new Produk(barcode, nama.trim(), hargaBeli, hargaJual));
    }

    public void hapus(String barcode) throws SQLException {
        if (produkDAO.pernahDitransaksikan(barcode)) {
            throw new IllegalArgumentException(
                    "Produk ini pernah ditransaksikan, jadi tidak dapat dihapus. "
                  + "Habiskan stoknya atau ubah datanya bila perlu.");
        }
        produkDAO.hapus(barcode);
    }

    public List<Stok> cariBatch(String barcode) throws SQLException {
        return stokDAO.cariByBarcode(barcode);
    }

    public int hitungProduk() throws SQLException {
        return produkDAO.hitungProduk();
    }

    public int hitungStokMenipis() throws SQLException {
        return produkDAO.hitungMenipis();
    }

    public int hitungDekatKedaluwarsa() throws SQLException {
        return stokDAO.hitungDekatKedaluwarsa(StokDAO.HARI_DEKAT_KEDALUWARSA);
    }

    public int totalUnit() throws SQLException {
        return produkDAO.totalUnit();
    }
}
