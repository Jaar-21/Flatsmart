package service;

import dao.TransaksiDAO;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.DetailTransaksi;
import model.TagihanTransaksi;
import model.Transaksi;
import util.Format;
import util.Sesi;

public class RiwayatService {

    private static final int LEBAR = 40;

    private final TransaksiDAO dao = new TransaksiDAO();

    // cari riwayat sendiri
    public List<Transaksi> cari(LocalDate awal, LocalDate akhir, String jenis, String keyword)
            throws SQLException {
        return dao.cariByPengguna(Sesi.username(), awal, akhir, jenis, keyword);
    }

    // detail satu transaksi
    public List<DetailTransaksi> cariDetail(int idTransaksi) throws SQLException {
        return dao.cariDetail(idTransaksi);
    }

    // riwayat semua kasir
    public List<Transaksi> cariSemua(LocalDate awal, LocalDate akhir) throws SQLException {
        return dao.cariSemua(awal, akhir);
    }

    // detail semua transaksi
    public List<DetailTransaksi> cariDetailSemua(LocalDate awal, LocalDate akhir) throws SQLException {
        return dao.cariDetailSemua(awal, akhir);
    }

    // ambil data tagihan
    public TagihanTransaksi cariTagihan(int idTransaksi) throws SQLException {
        return dao.cariTagihan(idTransaksi);
    }

    // cetak struk transaksi
    public String generateStruk(Transaksi trx) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(garis()).append(tengah("FLATSMART")).append(tengah("Minimarket Terpercaya"))
          .append(garis())
          .append("ID Transaksi: ").append(trx.getIdTransaksi()).append('\n')
          .append(Format.tanggalJam(trx.getWaktu())).append('\n')
          .append("Kasir : ").append(namaKasir(trx)).append('\n')
          .append(garis('-'));

        if (trx.isPenjualan()) {
            for (DetailTransaksi d : dao.cariDetail(trx.getIdTransaksi())) {
                long potongan = d.getHargaSebelumDiskon() * d.getJumlah() - d.getSubtotal();
                sb.append(kolom(potong(d.getNamaProduk(), 26), Format.rupiah(d.getSubtotal()))).append('\n');
                sb.append(kolom(potong("  x" + d.getJumlah() + " @"
                                + Format.rupiah(d.getHargaSebelumDiskon()), 26),
                                potongan > 0 ? "-" + Format.rupiah(potongan) : "")).append('\n');
            }
        } else {
            TagihanTransaksi tg = dao.cariTagihan(trx.getIdTransaksi());
            if (tg != null) {
                sb.append("Layanan : ").append(Format.judul(tg.getJenisLayanan())).append('\n')
                  .append("Provider: ").append(Format.judul(tg.getProvider())).append('\n')
                  .append("Pelanggan: ").append(Format.judul(tg.getNamaPelanggan())).append('\n')
                  .append("No.     : ").append(tg.getNomorPelanggan()).append('\n')
                  .append(kolom("Nominal :", Format.rupiah(tg.getNominal()))).append('\n')
                  .append(kolom("Adm     :",
                          Format.rupiah(trx.getTotalBayar() - tg.getNominal()))).append('\n');
            }
        }

        sb.append(garis('-'))
          .append(kolom("SUBTOTAL", Format.rupiah(trx.getSubtotal()))).append('\n');
        if (trx.getTotalDiskon() > 0) {
            sb.append(kolom("DISKON PROMO", "-" + Format.rupiah(trx.getTotalDiskon()))).append('\n');
        }
        sb.append(kolom("TOTAL BAYAR", Format.rupiah(trx.getTotalBayar()))).append('\n')
          .append(kolom("UANG DITERIMA", Format.rupiah(trx.getUangDiterima()))).append('\n')
          .append(kolom("KEMBALIAN", Format.rupiah(trx.getKembalian()))).append('\n')
          .append(kolom("METODE", Format.metode(trx.getMetodeBayar()))).append('\n')
          .append(garis())
          .append(tengah("Terima kasih!"))
          .append(tengah("Barang yang sudah dibeli"))
          .append(tengah("tidak dapat dikembalikan"))
          .append(garis());
        return sb.toString();
    }

    // nama kasir tampilan
    private String namaKasir(Transaksi trx) {
        return Sesi.get() == null ? trx.getUsername() : Sesi.get().getNamaTampil();
    }

    // garis pemisah struk
    private String garis() {
        return "=".repeat(LEBAR) + "\n";
    }

    // garis dengan karakter
    private String garis(char karakter) {
        return String.valueOf(karakter).repeat(LEBAR) + "\n";
    }

    // teks rata tengah
    private String tengah(String teks) {
        int sisa = LEBAR - teks.length();
        return sisa <= 0 ? teks + "\n" : " ".repeat(sisa / 2) + teks + "\n";
    }

    // dua kolom struk
    private String kolom(String label, String angka) {
        int sisa = LEBAR - label.length() - angka.length();
        return sisa <= 0 ? label + " " + angka : label + " ".repeat(sisa) + angka;
    }

    // potong teks panjang
    private String potong(String teks, int maksimal) {
        return teks.length() <= maksimal ? teks : teks.substring(0, maksimal - 1) + "...";
    }
}
