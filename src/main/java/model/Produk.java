package model;

import java.time.LocalDate;

public class Produk {
    private String barcode;
    private String namaProduk;
    private long hargaBeli;
    private long hargaJual;
    private int stokTotal;
    private LocalDate kedaluwarsaBatch;

    public Produk(String barcode, String namaProduk, long hargaBeli, long hargaJual) {
        this.barcode = barcode;
        this.namaProduk = namaProduk;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    public String getBarcode()      { return barcode; }
    public void setBarcode(String v) { this.barcode = v; }

    public String getNamaProduk()      { return namaProduk; }
    public void setNamaProduk(String v) { this.namaProduk = v; }

    public long getHargaBeli()      { return hargaBeli; }
    public void setHargaBeli(long v) { this.hargaBeli = v; }

    public long getHargaJual()      { return hargaJual; }
    public void setHargaJual(long v) { this.hargaJual = v; }

    public int getStokTotal()      { return stokTotal; }
    public void setStokTotal(int v) { this.stokTotal = v; }

    public LocalDate getKedaluwarsaBatch()      { return kedaluwarsaBatch; }
    public void setKedaluwarsaBatch(LocalDate v) { this.kedaluwarsaBatch = v; }
}
