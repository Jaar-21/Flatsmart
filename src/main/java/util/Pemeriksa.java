package util;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;


public final class Pemeriksa {

    public static final long MAKS_HARGA = 99_999_999L;

    // konstruktor privat util
    private Pemeriksa() {
    }

    // validasi teks
    public static String teks(String nilai, String label, int maksimum) {
        String bersih = nilai == null ? "" : nilai.trim();
        if (bersih.isEmpty()) {
            throw new IllegalArgumentException(label + " wajib diisi.");
        }
        if (bersih.length() > maksimum) {
            throw new IllegalArgumentException(label + " maksimal " + maksimum
                    + " karakter, saat ini " + bersih.length() + " karakter.");
        }
        return bersih;
    }

    // validasi uang
    public static long uang(String nilai, String label, long minimum, long maksimum) {
        String bersih = nilai == null ? "" : nilai.trim();
        if (bersih.isEmpty()) {
            throw new IllegalArgumentException(label + " wajib diisi.");
        }
        String digit = bersih.replace(".", "");
        if (!digit.matches("\\d+")) {
            throw new IllegalArgumentException(label + " harus angka rupiah, contoh 15.000.");
        }
        long hasil;
        try {
            hasil = Long.parseLong(digit);
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(label + " terlalu besar untuk diterima sistem.");
        }
        if (hasil < minimum || hasil > maksimum) {
            throw new IllegalArgumentException(label + " harus antara " + Format.angka(minimum)
                    + " dan " + Format.angka(maksimum) + ".");
        }
        return hasil;
    }

    // validasi barcode
    public static String barcode(String nilai) {
        String bersih = nilai == null ? "" : nilai.trim();
        if (bersih.isEmpty()) {
            throw new IllegalArgumentException("Barcode wajib diisi.");
        }
        if (!bersih.matches("\\d+") || bersih.length() > 10
                || Long.parseLong(bersih) > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(
                    "Barcode harus berupa angka, maksimal 10 digit.");
        }
        return bersih;
    }

    // validasi tanggal
    public static LocalDate tanggal(String nilai, String label) {
        String bersih = nilai == null ? "" : nilai.trim();
        LocalDate hasil;
        try {
            hasil = Format.bacaTanggal(bersih);
        } catch (DateTimeParseException | NullPointerException ex) {
            throw new IllegalArgumentException(label + " harus berformat dd/MM/yyyy.");
        }
        if (!Format.tanggal(hasil).equals(bersih)) {
            throw new IllegalArgumentException(label + " bukan tanggal yang benar, "
                    + "misalnya 31/02 tidak ada di kalender.");
        }
        return hasil;
    }

    // validasi rentang tanggal
    public static void rentang(LocalDate awal, LocalDate akhir, String labelAkhir) {
        if (akhir.isBefore(awal)) {
            throw new IllegalArgumentException(labelAkhir + " tidak boleh mendahului tanggal mulai.");
        }
    }
}
