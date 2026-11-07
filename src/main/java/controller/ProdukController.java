package controller;

import java.sql.SQLException;
import java.util.List;
import model.Produk;
import model.Stok;
import service.ProdukService;

/**
 * Controller modul produk. View hanya berbicara ke kelas ini; urusan validasi
 * dan query tetap berjalan di service dan DAO.
 */
public class ProdukController extends BasisController implements CrudController<Produk> {

    private final ProdukService service = new ProdukService();

    // konstruktor produk
    public ProdukController() {
        super("Produk");
    }

    // ambil semua produk
    @Override
    public List<Produk> semua() throws SQLException {
        return service.cari("", null);
    }

    // cari produk lewat barcode
    @Override
    public Produk cari(String barcode) throws SQLException {
        return service.cariByBarcode(idWajib(barcode));
    }

    // tambah produk
    @Override
    public void tambah(Produk produk) throws SQLException {
        service.simpanBaru(produk.getBarcode(), produk.getNamaProduk(), produk.getHargaBeli(),
                produk.getHargaJual(), produk.getStokTotal(), produk.getKedaluwarsaBatch());
    }

    // ubah produk
    @Override
    public void ubah(Produk produk) throws SQLException {
        service.ubah(produk.getBarcode(), produk.getNamaProduk(),
                produk.getHargaBeli(), produk.getHargaJual());
    }

    // hapus produk
    @Override
    public void hapus(String barcode) throws SQLException {
        service.hapus(idWajib(barcode));
    }

    // cari produk bertapis
    public List<Produk> cari(String keyword, String filterStok) throws SQLException {
        return service.cari(keyword, filterStok);
    }

    // cari batch produk
    public List<Stok> cariBatch(String barcode) throws SQLException {
        return service.cariBatch(idWajib(barcode));
    }

    // hitung jumlah produk
    public int hitungProduk() throws SQLException {
        return service.hitungProduk();
    }

    // hitung stok menipis
    public int hitungStokMenipis() throws SQLException {
        return service.hitungStokMenipis();
    }

    // hitung dekat kedaluwarsa
    public int hitungDekatKedaluwarsa() throws SQLException {
        return service.hitungDekatKedaluwarsa();
    }

    // total unit stok
    public int totalUnit() throws SQLException {
        return service.totalUnit();
    }
}
