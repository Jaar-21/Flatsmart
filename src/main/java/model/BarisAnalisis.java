package model;

public class BarisAnalisis {

    private static final int MIN_TERLARIS = 50;
    private static final int MAKS_LAMBAT = 20;

    private String barcode;
    private String namaProduk;
    private int jumlahTerjual;
    private long omzet;          
    private long totalDiskon;

    public BarisAnalisis(String barcode, String namaProduk, int jumlahTerjual,
                         long omzet, long totalDiskon) {
        this.barcode = barcode;
        this.namaProduk = namaProduk;
        this.jumlahTerjual = jumlahTerjual;
        this.omzet = omzet;
        this.totalDiskon = totalDiskon;
    }

    public String getBarcode()            { return barcode; }
    public void setBarcode(String v)      { this.barcode = v; }

    public String getNamaProduk()         { return namaProduk; }
    public void setNamaProduk(String v)   { this.namaProduk = v; }

    public int getJumlahTerjual()         { return jumlahTerjual; }
    public void setJumlahTerjual(int v)   { this.jumlahTerjual = v; }

    public long getOmzet()                { return omzet; }
    public void setOmzet(long v)          { this.omzet = v; }

    public long getTotalDiskon()          { return totalDiskon; }
    public void setTotalDiskon(long v)    { this.totalDiskon = v; }

    
    public String getLabel() {
        if (jumlahTerjual >= MIN_TERLARIS) return "Terlaris";
        if (jumlahTerjual <= MAKS_LAMBAT)  return "Lambat";
        return "Normal";
    }
}
