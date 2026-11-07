package model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Stok {

    private String idStok;
    private String barcode;
    private int jumlah;
    private LocalDate tglKedaluwarsa;

    private String namaProduk;

    // buat objek stok
    public Stok(String idStok, String barcode, int jumlah, LocalDate tglKedaluwarsa) {
        this.idStok = idStok;
        this.barcode = barcode;
        this.jumlah = jumlah;
        this.tglKedaluwarsa = tglKedaluwarsa;
    }

    // id stok
    public String getIdStok() {
        return idStok;
    }

    // barcode produk
    public String getBarcode() {
        return barcode;
    }

    // jumlah stok
    public int getJumlah() {
        return jumlah;
    }

    // tanggal kedaluwarsa
    public LocalDate getTglKedaluwarsa() {
        return tglKedaluwarsa;
    }

    // nama produk
    public String getNamaProduk() {
        return namaProduk;
    }

    // simpan nama produk
    public void setNamaProduk(String namaProduk) {
        this.namaProduk = namaProduk;
    }

    // sisa hari kedaluwarsa
    public long sisaHari() {
        return ChronoUnit.DAYS.between(LocalDate.now(), tglKedaluwarsa);
    }
}
