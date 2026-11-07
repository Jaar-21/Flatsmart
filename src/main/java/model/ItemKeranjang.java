package model;

import java.time.LocalDate;

public class ItemKeranjang {
    private String barcode;
    private String idStok;
    private String namaProduk;
    private long hargaJual;
    private int jumlah;
    private int diskonPersen;
    private String namaPromo;
    private int stokTersedia;
    private LocalDate tanggalExp;

    // buat objek item keranjang
    public ItemKeranjang(String barcode, String idStok, String namaProduk,
                         long hargaJual, int jumlah, int diskonPersen,
                         String namaPromo, int stokTersedia) {
        this.barcode = barcode;
        this.idStok = idStok;
        this.namaProduk = namaProduk;
        this.hargaJual = hargaJual;
        this.jumlah = jumlah;
        this.diskonPersen = diskonPersen;
        this.namaPromo = namaPromo;
        this.stokTersedia = stokTersedia;
    }

    // subtotal harga
    public long getSubtotal() { return hargaJual * jumlah; }


    // potongan diskon
    public long getPotongan() { return getSubtotal() * diskonPersen / 100; }

    // total dibayar
    public long getTotal() { return getSubtotal() - getPotongan(); }

    // ── getter / setter ──

    public String getBarcode()           { return barcode; }
    // simpan barcode produk
    public void setBarcode(String v)     { this.barcode = v; }

    // id stok
    public String getIdStok()            { return idStok; }
    // simpan id stok
    public void setIdStok(String v)      { this.idStok = v; }

    // nama produk
    public String getNamaProduk()        { return namaProduk; }
    // simpan nama produk
    public void setNamaProduk(String v)  { this.namaProduk = v; }

    // harga jual
    public long getHargaJual()           { return hargaJual; }
    // simpan harga jual
    public void setHargaJual(long v)     { this.hargaJual = v; }

    // jumlah beli
    public int getJumlah()               { return jumlah; }
    // simpan jumlah beli
    public void setJumlah(int v)         { this.jumlah = v; }

    // persen diskon
    public int getDiskonPersen()         { return diskonPersen; }
    // simpan persen diskon
    public void setDiskonPersen(int v)   { this.diskonPersen = v; }

    // nama promosi
    public String getNamaPromo()         { return namaPromo; }
    // simpan nama promosi
    public void setNamaPromo(String v)   { this.namaPromo = v; }

    // stok tersedia
    public int getStokTersedia()         { return stokTersedia; }
    // simpan stok tersedia
    public void setStokTersedia(int v)   { this.stokTersedia = v; }

    // tanggal kedaluwarsa
    public LocalDate getTanggalExp()         { return tanggalExp; }
    // simpan tanggal kedaluwarsa
    public void setTanggalExp(LocalDate v)   { this.tanggalExp = v; }
}
