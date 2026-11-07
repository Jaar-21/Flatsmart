package controller;

import java.sql.SQLException;
import java.util.List;
import model.Produk;
import model.Stok;
import service.ProdukService;

public class ProdukController extends BasisController implements CrudController<Produk> {

    private final ProdukService service = new ProdukService();

    public ProdukController() {
        super("Produk");
    }

    @Override
    public List<Produk> semua() throws SQLException {
        return service.cari("", null);
    }

    @Override
    public Produk cari(String barcode) throws SQLException {
        return service.cariByBarcode(idWajib(barcode));
    }

    @Override
    public void tambah(Produk produk) throws SQLException {
        service.simpanBaru(produk.getBarcode(), produk.getNamaProduk(), produk.getHargaBeli(),
                produk.getHargaJual(), produk.getStokTotal(), produk.getKedaluwarsaBatch());
    }

    @Override
    public void ubah(Produk produk) throws SQLException {
        service.ubah(produk.getBarcode(), produk.getNamaProduk(),
                produk.getHargaBeli(), produk.getHargaJual());
    }

    @Override
    public void hapus(String barcode) throws SQLException {
        service.hapus(idWajib(barcode));
    }

    public List<Produk> cari(String keyword, String filterStok) throws SQLException {
        return service.cari(keyword, filterStok);
    }

    public List<Stok> cariBatch(String barcode) throws SQLException {
        return service.cariBatch(idWajib(barcode));
    }

    public int hitungProduk() throws SQLException {
        return service.hitungProduk();
    }

    public int hitungStokMenipis() throws SQLException {
        return service.hitungStokMenipis();
    }

    public int hitungDekatKedaluwarsa() throws SQLException {
        return service.hitungDekatKedaluwarsa();
    }

    public int totalUnit() throws SQLException {
        return service.totalUnit();
    }
}
