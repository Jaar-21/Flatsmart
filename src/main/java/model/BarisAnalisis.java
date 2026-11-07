package model;

public class BarisAnalisis {

    private static final int MIN_TERLARIS = 50;
    private static final int MAKS_LAMBAT = 20;

    private String barcode;
    private String namaProduk;
    private int jumlahTerjual;
    private long omzet;          
    private long totalDiskon;

    // buat objek baris analisis
    public BarisAnalisis(String barcode, String namaProduk, int jumlahTerjual,
                         long omzet, long totalDiskon) {
        this.barcode = barcode;
        this.namaProduk = namaProduk;
        this.jumlahTerjual = jumlahTerjual;
        this.omzet = omzet;
        this.totalDiskon = totalDiskon;
    }

    // barcode produk
    public String getBarcode()            { return barcode; }
    // simpan barcode produk
    public void setBarcode(String v)      { this.barcode = v; }

    // nama produk
    public String getNamaProduk()         { return namaProduk; }
    // simpan nama produk
    public void setNamaProduk(String v)   { this.namaProduk = v; }

    // jumlah terjual
    public int getJumlahTerjual()         { return jumlahTerjual; }
    // simpan jumlah terjual
    public void setJumlahTerjual(int v)   { this.jumlahTerjual = v; }

    // omzet
    public long getOmzet()                { return omzet; }
    // simpan omzet
    public void setOmzet(long v)          { this.omzet = v; }

    // total diskon
    public long getTotalDiskon()          { return totalDiskon; }
    // simpan total diskon
    public void setTotalDiskon(long v)    { this.totalDiskon = v; }

    
    // label kategori produk
    public String getLabel() {
        if (jumlahTerjual >= MIN_TERLARIS) return "Terlaris";
        if (jumlahTerjual <= MAKS_LAMBAT)  return "Lambat";
        return "Normal";
    }
}
