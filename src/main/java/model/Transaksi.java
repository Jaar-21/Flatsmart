package model;

import java.time.LocalDateTime;

public class Transaksi {
    private int idTransaksi;
    private String username;
    private String jenis;       
    private String metodeBayar;
    private long subtotal;
    private long totalDiskon;
    private long totalBayar;
    private long uangDiterima;
    private long kembalian;
    private LocalDateTime waktu;
    private String namaKasir;
    private String keteranganTagihan;

    // buat objek transaksi
    public Transaksi() {}

    // id transaksi
    public int getIdTransaksi()               { return idTransaksi; }
    // simpan id transaksi
    public void setIdTransaksi(int v)         { this.idTransaksi = v; }

    // username kasir
    public String getUsername()               { return username; }
    // simpan username kasir
    public void setUsername(String v)          { this.username = v; }

    // jenis transaksi
    public String getJenis()                  { return jenis; }
    // simpan jenis transaksi
    public void setJenis(String v)            { this.jenis = v; }

    // metode bayar
    public String getMetodeBayar()            { return metodeBayar; }
    // simpan metode bayar
    public void setMetodeBayar(String v)      { this.metodeBayar = v; }

    // subtotal transaksi
    public long getSubtotal()                 { return subtotal; }
    // simpan subtotal transaksi
    public void setSubtotal(long v)           { this.subtotal = v; }

    // total diskon
    public long getTotalDiskon()              { return totalDiskon; }
    // simpan total diskon
    public void setTotalDiskon(long v)        { this.totalDiskon = v; }

    // total dibayar
    public long getTotalBayar()               { return totalBayar; }
    // simpan total dibayar
    public void setTotalBayar(long v)         { this.totalBayar = v; }

    // uang diterima
    public long getUangDiterima()             { return uangDiterima; }
    // simpan uang diterima
    public void setUangDiterima(long v)       { this.uangDiterima = v; }

    // uang kembalian
    public long getKembalian()                { return kembalian; }
    // simpan uang kembalian
    public void setKembalian(long v)          { this.kembalian = v; }

    // waktu transaksi
    public LocalDateTime getWaktu()           { return waktu; }
    // simpan waktu transaksi
    public void setWaktu(LocalDateTime v)     { this.waktu = v; }

    // nama kasir
    public String getNamaKasir()              { return namaKasir; }
    // simpan nama kasir
    public void setNamaKasir(String v)         { this.namaKasir = v; }

    // keterangan tagihan
    public String getKeteranganTagihan()      { return keteranganTagihan; }
    // simpan keterangan tagihan
    public void setKeteranganTagihan(String v) { this.keteranganTagihan = v; }

    // cek jenis penjualan
    public boolean isPenjualan() { return "penjualan".equalsIgnoreCase(jenis); }
    // cek jenis tagihan
    public boolean isTagihan()   { return "tagihan".equalsIgnoreCase(jenis); }
}
