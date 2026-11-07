package service;

import config.DatabaseConnection;
import dao.ProdukDAO;
import dao.PromosiDAO;
import dao.StokDAO;
import dao.TransaksiDAO;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import model.DetailTransaksi;
import model.ItemKeranjang;
import model.Produk;
import model.Promosi;
import model.Stok;
import model.Transaksi;
import util.Sesi;

public class PenjualanService {

    private final ProdukDAO produkDAO = new ProdukDAO();
    private final StokDAO stokDAO = new StokDAO();
    private final PromosiDAO promosiDAO = new PromosiDAO();
    private final TransaksiDAO transaksiDAO = new TransaksiDAO();


    public List<Produk> saranProduk(String keyword) throws SQLException {
        if (keyword == null || keyword.isBlank()) {
            return List.of();
        }
        List<Produk> hasil = new ArrayList<>();
        for (Produk p : produkDAO.cari(keyword.trim(), ProdukDAO.FILTER_SEMUA)) {
            if (p.getStokTotal() > 0) {
                hasil.add(p);
                if (hasil.size() >= 8) break;
            }
        }
        return hasil;
    }

    public ItemKeranjang cariItem(String barcode, int jumlah) throws SQLException {
        if (barcode == null || barcode.isBlank()) {
            throw new IllegalArgumentException("Barcode tidak boleh kosong.");
        }
        if (jumlah <= 0) {
            throw new IllegalArgumentException("Jumlah harus minimal 1.");
        }

        String kode = barcode.trim();
        Produk produk = produkDAO.cariByBarcode(kode);
        if (produk == null) {
            throw new IllegalArgumentException("Produk dengan barcode " + kode + " tidak ditemukan.");
        }

        Stok batch = stokDAO.cariBatchTerkeluar(kode);
        if (batch == null) {
            throw new IllegalArgumentException("Stok " + produk.getNamaProduk() + " sedang habis.");
        }
        if (jumlah > batch.getJumlah()) {
            throw new IllegalArgumentException("Stok tersedia hanya " + batch.getJumlah()
                    + " unit pada batch " + batch.getIdStok() + ".");
        }

        Promosi promo = promosiDAO.cariAktifByStok(batch.getIdStok());
        ItemKeranjang item = new ItemKeranjang(produk.getBarcode(), batch.getIdStok(), produk.getNamaProduk(),
                produk.getHargaJual(), jumlah,
                promo == null ? 0 : promo.getDiskonPersen(),
                promo == null ? null : promo.getNamaPromosi(),
                batch.getJumlah());
        item.setTanggalExp(batch.getTglKedaluwarsa());
        return item;
    }

    public Transaksi proses(List<ItemKeranjang> keranjang, String metode, long uangDiterima)
            throws SQLException {
        if (keranjang == null || keranjang.isEmpty()) {
            throw new IllegalArgumentException("Keranjang masih kosong.");
        }
        if (metode == null || metode.isBlank()) {
            throw new IllegalArgumentException("Metode pembayaran belum dipilih.");
        }

        long subtotal = 0;
        long totalDiskon = 0;
        int totalUnit = 0;
        for (ItemKeranjang item : keranjang) {
            subtotal += item.getSubtotal();
            totalDiskon += item.getPotongan();
            totalUnit += item.getJumlah();
        }
        long totalBayar = subtotal - totalDiskon;
        int unit = totalUnit;

        if (!"Tunai".equalsIgnoreCase(metode)) {
            uangDiterima = totalBayar;
        }
        if (uangDiterima < totalBayar) {
            throw new IllegalArgumentException("Uang diterima kurang dari total bayar.");
        }

        Transaksi trx = new Transaksi();
        trx.setUsername(Sesi.username());
        trx.setJenis("penjualan");
        trx.setMetodeBayar(metode);
        trx.setSubtotal(subtotal);
        trx.setTotalDiskon(totalDiskon);
        trx.setTotalBayar(totalBayar);
        trx.setUangDiterima(uangDiterima);
        trx.setKembalian(uangDiterima - totalBayar);
        trx.setWaktu(LocalDateTime.now());

        DatabaseConnection.transaction(() -> {
            int idTransaksi = transaksiDAO.simpanHeader(trx);
            trx.setIdTransaksi(idTransaksi);
            transaksiDAO.catatPenjualan(idTransaksi, unit);
            for (ItemKeranjang item : keranjang) {
                transaksiDAO.simpanDetail(detail(idTransaksi, item));
                stokDAO.kurangi(item.getIdStok(), item.getJumlah());
            }
            return null;
        });
        return trx;
    }

    private DetailTransaksi detail(int idTransaksi, ItemKeranjang item) {
        DetailTransaksi d = new DetailTransaksi();
        d.setIdTransaksi(idTransaksi);
        d.setIdStok(item.getIdStok());
        d.setNamaProduk(item.getNamaProduk());
        d.setHargaSatuan(item.getTotal() / item.getJumlah());
        d.setJumlah(item.getJumlah());
        d.setSubtotal(item.getTotal());
        return d;
    }
}
