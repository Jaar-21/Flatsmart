package model;

import java.time.LocalDateTime;

/** Satu baris penjualan produk beserta transaksi induknya, untuk rincian per produk. */
public class PenjualanProduk {

    private int idTransaksi;
    private LocalDateTime waktu;
    private String namaKasir;
    private String metodeBayar;
    private String idStok;
    private int jumlah;
    private long hargaSatuan;
    private long hargaSebelumDiskon;
    private long subtotal;

    // id transaksi
    public int getIdTransaksi()            { return idTransaksi; }
    // simpan id transaksi
    public void setIdTransaksi(int v)      { this.idTransaksi = v; }

    // waktu transaksi
    public LocalDateTime getWaktu()        { return waktu; }
    // simpan waktu transaksi
    public void setWaktu(LocalDateTime v)  { this.waktu = v; }

    // nama kasir
    public String getNamaKasir()           { return namaKasir; }
    // simpan nama kasir
    public void setNamaKasir(String v)     { this.namaKasir = v; }

    // metode bayar
    public String getMetodeBayar()         { return metodeBayar; }
    // simpan metode bayar
    public void setMetodeBayar(String v)   { this.metodeBayar = v; }

    // id stok
    public String getIdStok()              { return idStok; }
    // simpan id stok
    public void setIdStok(String v)        { this.idStok = v; }

    // jumlah beli
    public int getJumlah()                 { return jumlah; }
    // simpan jumlah beli
    public void setJumlah(int v)           { this.jumlah = v; }

    // harga satuan
    public long getHargaSatuan()           { return hargaSatuan; }
    // simpan harga satuan
    public void setHargaSatuan(long v)     { this.hargaSatuan = v; }

    // harga sebelum diskon
    public long getHargaSebelumDiskon()    { return hargaSebelumDiskon; }
    // simpan harga sebelum diskon
    public void setHargaSebelumDiskon(long v) { this.hargaSebelumDiskon = v; }

    // subtotal baris
    public long getSubtotal()              { return subtotal; }
    // simpan subtotal baris
    public void setSubtotal(long v)        { this.subtotal = v; }

    /** Total potongan baris ini: harga normal x jumlah dikurangi yang benar-benar dibayar. */
    public long getPotongan() {
        return hargaSebelumDiskon * jumlah - subtotal;
    }
}
