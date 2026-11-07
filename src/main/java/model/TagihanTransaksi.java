package model;

public class TagihanTransaksi {
    private int idTransaksi;
    private String jenisLayanan;  
    private String provider;
    private String nomorPelanggan;
    private String namaPelanggan;
    private long nominal;         

    public TagihanTransaksi() {}

    public int getIdTransaksi()                 { return idTransaksi; }
    public void setIdTransaksi(int v)           { this.idTransaksi = v; }

    public String getJenisLayanan()             { return jenisLayanan; }
    public void setJenisLayanan(String v)       { this.jenisLayanan = v; }

    public String getProvider()                 { return provider; }
    public void setProvider(String v)           { this.provider = v; }

    public String getNomorPelanggan()           { return nomorPelanggan; }
    public void setNomorPelanggan(String v)     { this.nomorPelanggan = v; }

    public String getNamaPelanggan()            { return namaPelanggan; }
    public void setNamaPelanggan(String v)      { this.namaPelanggan = v; }

    public long getNominal()                    { return nominal; }
    public void setNominal(long v)              { this.nominal = v; }
}
