package controller;

import java.sql.SQLException;
import java.util.List;
import model.ItemKeranjang;
import model.Produk;
import model.Transaksi;
import service.PenjualanService;

/**
 * Controller kasir: keranjang, potongan promo otomatis, dan penyimpanan
 * transaksi penjualan secara atomik dikerjakan di service.
 */
public class PenjualanController extends BasisController {

    private final PenjualanService service = new PenjualanService();

    // konstruktor penjualan
    public PenjualanController() {
        super("Penjualan");
    }

    // saran produk kasir
    public List<Produk> saranProduk(String keyword) throws SQLException {
        return service.saranProduk(keyword);
    }

    // cari item keranjang
    public ItemKeranjang cariItem(String barcode, int jumlah) throws SQLException {
        return service.cariItem(barcode, jumlah);
    }

    // proses penjualan
    public Transaksi proses(List<ItemKeranjang> keranjang, String metode, long uangDiterima)
            throws SQLException {
        return service.proses(keranjang, metode, uangDiterima);
    }
}
