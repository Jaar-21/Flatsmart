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

    public Transaksi() {}

    public int getIdTransaksi()               { return idTransaksi; }
    public void setIdTransaksi(int v)         { this.idTransaksi = v; }

    public String getUsername()               { return username; }
    public void setUsername(String v)          { this.username = v; }

    public String getJenis()                  { return jenis; }
    public void setJenis(String v)            { this.jenis = v; }

    public String getMetodeBayar()            { return metodeBayar; }
    public void setMetodeBayar(String v)      { this.metodeBayar = v; }

    public long getSubtotal()                 { return subtotal; }
    public void setSubtotal(long v)           { this.subtotal = v; }

    public long getTotalDiskon()              { return totalDiskon; }
    public void setTotalDiskon(long v)        { this.totalDiskon = v; }

    public long getTotalBayar()               { return totalBayar; }
    public void setTotalBayar(long v)         { this.totalBayar = v; }

    public long getUangDiterima()             { return uangDiterima; }
    public void setUangDiterima(long v)       { this.uangDiterima = v; }

    public long getKembalian()                { return kembalian; }
    public void setKembalian(long v)          { this.kembalian = v; }

    public LocalDateTime getWaktu()           { return waktu; }
    public void setWaktu(LocalDateTime v)     { this.waktu = v; }

    public String getNamaKasir()              { return namaKasir; }
    public void setNamaKasir(String v)         { this.namaKasir = v; }

    public String getKeteranganTagihan()      { return keteranganTagihan; }
    public void setKeteranganTagihan(String v) { this.keteranganTagihan = v; }

    public boolean isPenjualan() { return "penjualan".equalsIgnoreCase(jenis); }
    public boolean isTagihan()   { return "tagihan".equalsIgnoreCase(jenis); }
}
