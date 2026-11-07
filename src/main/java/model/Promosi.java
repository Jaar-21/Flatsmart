package model;

import java.time.LocalDate;

public class Promosi {
    private String kodePromosi;
    private String namaPromosi;
    private String idStok;
    private int diskonPersen;
    private LocalDate tglMulai;
    private LocalDate tglSelesai;

    private String barcode;
    private String namaProduk;

    // buat objek promosi
    public Promosi(String kodePromosi, String namaPromosi, String idStok,
                   int diskonPersen, LocalDate tglMulai, LocalDate tglSelesai) {
        this.kodePromosi = kodePromosi;
        this.namaPromosi = namaPromosi;
        this.idStok = idStok;
        this.diskonPersen = diskonPersen;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
    }

    // kode promosi
    public String getKodePromosi()           { return kodePromosi; }
    // simpan kode promosi
    public void setKodePromosi(String v)     { this.kodePromosi = v; }

    // nama promosi
    public String getNamaPromosi()           { return namaPromosi; }
    // simpan nama promosi
    public void setNamaPromosi(String v)     { this.namaPromosi = v; }

    // id stok
    public String getIdStok()                { return idStok; }
    // simpan id stok
    public void setIdStok(String v)          { this.idStok = v; }

    // persen diskon
    public int getDiskonPersen()             { return diskonPersen; }
    // simpan persen diskon
    public void setDiskonPersen(int v)       { this.diskonPersen = v; }

    // tanggal mulai
    public LocalDate getTglMulai()           { return tglMulai; }
    // simpan tanggal mulai
    public void setTglMulai(LocalDate v)     { this.tglMulai = v; }

    // tanggal selesai
    public LocalDate getTglSelesai()         { return tglSelesai; }
    // simpan tanggal selesai
    public void setTglSelesai(LocalDate v)   { this.tglSelesai = v; }

    // barcode produk
    public String getBarcode()               { return barcode; }
    // simpan barcode produk
    public void setBarcode(String v)         { this.barcode = v; }

    // nama produk
    public String getNamaProduk()            { return namaProduk; }
    // simpan nama produk
    public void setNamaProduk(String v)      { this.namaProduk = v; }

    // status promosi
    public String getStatus() {
        LocalDate now = LocalDate.now();
        if (now.isBefore(tglMulai))   return "Akan datang";
        if (now.isAfter(tglSelesai))  return "Berakhir";
        return "Aktif";
    }
}
