package util;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Format {

    private static final Locale ID = Locale.forLanguageTag("id-ID");
    private static final DateTimeFormatter TANGGAL_PENDEK = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter TANGGAL_JAM =
            DateTimeFormatter.ofPattern("d MMM yyyy, HH:mm", ID);

    private Format() {
    }

    public static String angka(long n) {
        return NumberFormat.getInstance(ID).format(n);
    }

    public static String rupiah(long n) {
        return "Rp" + angka(n);
    }

    public static String rupiahPendek(long n) {
        if (n >= 1_000_000_000L) {
            return "Rp" + desimal(n / 1_000_000_000.0) + " m";
        }
        if (n >= 1_000_000L) {
            return "Rp" + desimal(n / 1_000_000.0) + " jt";
        }
        if (n >= 1_000L) {
            return "Rp" + Math.round(n / 1_000.0) + " rb";
        }
        return rupiah(n);
    }

    private static String desimal(double v) {
        return String.format(java.util.Locale.US, v >= 10 ? "%.0f" : "%.1f", v).replace('.', ',');
    }

    public static String judul(String s) {
        if (s == null || s.isBlank()) {
            return "";
        }
        StringBuilder hasil = new StringBuilder();
        for (String kata : s.trim().split("\\s+")) {
            if (hasil.length() > 0) {
                hasil.append(' ');
            }
            hasil.append(Character.toUpperCase(kata.charAt(0))).append(kata.substring(1));
        }
        return hasil.toString();
    }

    public static String metode(String s) {
        if (s == null || s.isBlank()) {
            return "";
        }
        return "qris".equalsIgnoreCase(s.trim()) ? "QRIS" : judul(s.trim());
    }

    public static String tanggalPanjang(LocalDate d) {
        return d.format(DateTimeFormatter.ofPattern("EEEE, d MMMM yyyy", ID));
    }

    public static String tanggalJam(LocalDateTime t) {
        return t.format(TANGGAL_JAM);
    }

    public static String tanggal(LocalDate d) {
        return d.format(TANGGAL_PENDEK);
    }

    public static LocalDate bacaTanggal(String teks) {
        return LocalDate.parse(teks.trim(), TANGGAL_PENDEK);
    }
}
