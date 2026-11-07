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

    public long getSubtotal() { return hargaJual * jumlah; }


    public long getPotongan() { return getSubtotal() * diskonPersen / 100; }

    public long getTotal() { return getSubtotal() - getPotongan(); }


    public String getBarcode()           { return barcode; }
    public void setBarcode(String v)     { this.barcode = v; }

    public String getIdStok()            { return idStok; }
    public void setIdStok(String v)      { this.idStok = v; }

    public String getNamaProduk()        { return namaProduk; }
    public void setNamaProduk(String v)  { this.namaProduk = v; }

    public long getHargaJual()           { return hargaJual; }
    public void setHargaJual(long v)     { this.hargaJual = v; }

    public int getJumlah()               { return jumlah; }
    public void setJumlah(int v)         { this.jumlah = v; }

    public int getDiskonPersen()         { return diskonPersen; }
    public void setDiskonPersen(int v)   { this.diskonPersen = v; }

    public String getNamaPromo()         { return namaPromo; }
    public void setNamaPromo(String v)   { this.namaPromo = v; }

    public int getStokTersedia()         { return stokTersedia; }
    public void setStokTersedia(int v)   { this.stokTersedia = v; }

    public LocalDate getTanggalExp()         { return tanggalExp; }
    public void setTanggalExp(LocalDate v)   { this.tanggalExp = v; }
}
