package model;

public class DetailTransaksi {
    private int idTransaksi;
    private String idStok;
    private String namaProduk;
    private long hargaSebelumDiskon;
    private long hargaSatuan;
    private int jumlah;
    private long subtotal;

    // buat objek detail transaksi
    public DetailTransaksi() {}

    // id transaksi
    public int getIdTransaksi()            { return idTransaksi; }
    // simpan id transaksi
    public void setIdTransaksi(int v)      { this.idTransaksi = v; }

    // id stok
    public String getIdStok()              { return idStok; }
    // simpan id stok
    public void setIdStok(String v)        { this.idStok = v; }

    // nama produk
    public String getNamaProduk()          { return namaProduk; }
    // simpan nama produk
    public void setNamaProduk(String v)    { this.namaProduk = v; }

    // harga sebelum diskon
    public long getHargaSebelumDiskon()    { return hargaSebelumDiskon; }
    // simpan harga sebelum diskon
    public void setHargaSebelumDiskon(long v) { this.hargaSebelumDiskon = v; }

    // harga satuan
    public long getHargaSatuan()           { return hargaSatuan; }
    // simpan harga satuan
    public void setHargaSatuan(long v)     { this.hargaSatuan = v; }

    // jumlah beli
    public int getJumlah()                 { return jumlah; }
    // simpan jumlah beli
    public void setJumlah(int v)           { this.jumlah = v; }

    // subtotal baris
    public long getSubtotal()              { return subtotal; }
    // simpan subtotal baris
    public void setSubtotal(long v)        { this.subtotal = v; }
}
