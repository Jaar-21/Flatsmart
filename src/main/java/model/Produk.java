package model;

import java.time.LocalDate;

public class Produk {
    private String barcode;
    private String namaProduk;
    private long hargaBeli;
    private long hargaJual;
    private int stokTotal;
    private LocalDate kedaluwarsaBatch;

    // buat objek produk
    public Produk(String barcode, String namaProduk, long hargaBeli, long hargaJual) {
        this.barcode = barcode;
        this.namaProduk = namaProduk;
        this.hargaBeli = hargaBeli;
        this.hargaJual = hargaJual;
    }

    // barcode produk
    public String getBarcode()      { return barcode; }
    // simpan barcode produk
    public void setBarcode(String v) { this.barcode = v; }

    // nama produk
    public String getNamaProduk()      { return namaProduk; }
    // simpan nama produk
    public void setNamaProduk(String v) { this.namaProduk = v; }

    // harga beli
    public long getHargaBeli()      { return hargaBeli; }
    // simpan harga beli
    public void setHargaBeli(long v) { this.hargaBeli = v; }

    // harga jual
    public long getHargaJual()      { return hargaJual; }
    // simpan harga jual
    public void setHargaJual(long v) { this.hargaJual = v; }

    // stok total
    public int getStokTotal()      { return stokTotal; }
    // simpan stok total
    public void setStokTotal(int v) { this.stokTotal = v; }

    // kedaluwarsa batch
    public LocalDate getKedaluwarsaBatch()      { return kedaluwarsaBatch; }
    // simpan kedaluwarsa batch
    public void setKedaluwarsaBatch(LocalDate v) { this.kedaluwarsaBatch = v; }
}
