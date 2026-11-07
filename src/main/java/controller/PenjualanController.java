package controller;

import java.sql.SQLException;
import java.util.List;
import model.ItemKeranjang;
import model.Produk;
import model.Transaksi;
import service.PenjualanService;

public class PenjualanController extends BasisController {

    private final PenjualanService service = new PenjualanService();

    public PenjualanController() {
        super("Penjualan");
    }

    public List<Produk> saranProduk(String keyword) throws SQLException {
        return service.saranProduk(keyword);
    }

    public ItemKeranjang cariItem(String barcode, int jumlah) throws SQLException {
        return service.cariItem(barcode, jumlah);
    }

    public Transaksi proses(List<ItemKeranjang> keranjang, String metode, long uangDiterima)
            throws SQLException {
        return service.proses(keranjang, metode, uangDiterima);
    }
}
