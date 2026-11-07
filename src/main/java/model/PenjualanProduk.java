package model;

import java.time.LocalDateTime;

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

    public int getIdTransaksi()            { return idTransaksi; }
    public void setIdTransaksi(int v)      { this.idTransaksi = v; }

    public LocalDateTime getWaktu()        { return waktu; }
    public void setWaktu(LocalDateTime v)  { this.waktu = v; }

    public String getNamaKasir()           { return namaKasir; }
    public void setNamaKasir(String v)     { this.namaKasir = v; }

    public String getMetodeBayar()         { return metodeBayar; }
    public void setMetodeBayar(String v)   { this.metodeBayar = v; }

    public String getIdStok()              { return idStok; }
    public void setIdStok(String v)        { this.idStok = v; }

    public int getJumlah()                 { return jumlah; }
    public void setJumlah(int v)           { this.jumlah = v; }

    public long getHargaSatuan()           { return hargaSatuan; }
    public void setHargaSatuan(long v)     { this.hargaSatuan = v; }

    public long getHargaSebelumDiskon()    { return hargaSebelumDiskon; }
    public void setHargaSebelumDiskon(long v) { this.hargaSebelumDiskon = v; }

    public long getSubtotal()              { return subtotal; }
    public void setSubtotal(long v)        { this.subtotal = v; }

    public long getPotongan() {
        return hargaSebelumDiskon * jumlah - subtotal;
    }
}
