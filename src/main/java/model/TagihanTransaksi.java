package model;

public class TagihanTransaksi {
    private int idTransaksi;
    private String jenisLayanan;  
    private String provider;
    private String nomorPelanggan;
    private String namaPelanggan;
    private long nominal;         

    // buat objek tagihan transaksi
    public TagihanTransaksi() {}

    // id transaksi
    public int getIdTransaksi()                 { return idTransaksi; }
    // simpan id transaksi
    public void setIdTransaksi(int v)           { this.idTransaksi = v; }

    // jenis layanan
    public String getJenisLayanan()             { return jenisLayanan; }
    // simpan jenis layanan
    public void setJenisLayanan(String v)       { this.jenisLayanan = v; }

    // provider layanan
    public String getProvider()                 { return provider; }
    // simpan provider layanan
    public void setProvider(String v)           { this.provider = v; }

    // nomor pelanggan
    public String getNomorPelanggan()           { return nomorPelanggan; }
    // simpan nomor pelanggan
    public void setNomorPelanggan(String v)     { this.nomorPelanggan = v; }

    // nama pelanggan
    public String getNamaPelanggan()            { return namaPelanggan; }
    // simpan nama pelanggan
    public void setNamaPelanggan(String v)      { this.namaPelanggan = v; }

    // nominal tagihan
    public long getNominal()                    { return nominal; }
    // simpan nominal tagihan
    public void setNominal(long v)              { this.nominal = v; }
}
