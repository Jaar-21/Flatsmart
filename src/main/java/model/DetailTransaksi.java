package model;

public class DetailTransaksi {
    private int idTransaksi;
    private String idStok;
    private String namaProduk;
    private long hargaSebelumDiskon;
    private long hargaSatuan;
    private int jumlah;
    private long subtotal;

    public DetailTransaksi() {}

    public int getIdTransaksi()            { return idTransaksi; }
    public void setIdTransaksi(int v)      { this.idTransaksi = v; }

    public String getIdStok()              { return idStok; }
    public void setIdStok(String v)        { this.idStok = v; }

    public String getNamaProduk()          { return namaProduk; }
    public void setNamaProduk(String v)    { this.namaProduk = v; }

    public long getHargaSebelumDiskon()    { return hargaSebelumDiskon; }
    public void setHargaSebelumDiskon(long v) { this.hargaSebelumDiskon = v; }

    public long getHargaSatuan()           { return hargaSatuan; }
    public void setHargaSatuan(long v)     { this.hargaSatuan = v; }

    public int getJumlah()                 { return jumlah; }
    public void setJumlah(int v)           { this.jumlah = v; }

    public long getSubtotal()              { return subtotal; }
    public void setSubtotal(long v)        { this.subtotal = v; }
}
