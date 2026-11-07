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

    public Promosi(String kodePromosi, String namaPromosi, String idStok,
                   int diskonPersen, LocalDate tglMulai, LocalDate tglSelesai) {
        this.kodePromosi = kodePromosi;
        this.namaPromosi = namaPromosi;
        this.idStok = idStok;
        this.diskonPersen = diskonPersen;
        this.tglMulai = tglMulai;
        this.tglSelesai = tglSelesai;
    }

    public String getKodePromosi()           { return kodePromosi; }
    public void setKodePromosi(String v)     { this.kodePromosi = v; }

    public String getNamaPromosi()           { return namaPromosi; }
    public void setNamaPromosi(String v)     { this.namaPromosi = v; }

    public String getIdStok()                { return idStok; }
    public void setIdStok(String v)          { this.idStok = v; }

    public int getDiskonPersen()             { return diskonPersen; }
    public void setDiskonPersen(int v)       { this.diskonPersen = v; }

    public LocalDate getTglMulai()           { return tglMulai; }
    public void setTglMulai(LocalDate v)     { this.tglMulai = v; }

    public LocalDate getTglSelesai()         { return tglSelesai; }
    public void setTglSelesai(LocalDate v)   { this.tglSelesai = v; }

    public String getBarcode()               { return barcode; }
    public void setBarcode(String v)         { this.barcode = v; }

    public String getNamaProduk()            { return namaProduk; }
    public void setNamaProduk(String v)      { this.namaProduk = v; }

    public String getStatus() {
        LocalDate now = LocalDate.now();
        if (now.isBefore(tglMulai))   return "Akan datang";
        if (now.isAfter(tglSelesai))  return "Berakhir";
        return "Aktif";
    }
}
