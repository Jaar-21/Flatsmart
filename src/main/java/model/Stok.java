package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Stok {

    private String idStok;
    private String barcode;
    private int jumlah;
    private LocalDate tglKedaluwarsa;

    private String namaProduk;

    public Stok(String idStok, String barcode, int jumlah, LocalDate tglKedaluwarsa) {
        this.idStok = idStok;
        this.barcode = barcode;
        this.jumlah = jumlah;
        this.tglKedaluwarsa = tglKedaluwarsa;
    }

    public String getIdStok() {
        return idStok;
    }

    public String getBarcode() {
        return barcode;
    }

    public int getJumlah() {
        return jumlah;
    }

    public LocalDate getTglKedaluwarsa() {
        return tglKedaluwarsa;
    }

    public String getNamaProduk() {
        return namaProduk;
    }

    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    public long sisaHari() {
        return ChronoUnit.DAYS.between(LocalDate.now(), tglKedaluwarsa);
    }
}
