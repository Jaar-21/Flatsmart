package service;

import model.BarisAnalisis;
import model.DetailTransaksi;
import model.Transaksi;
import util.ExcelWriter;
import util.Format;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class LaporanExportService {

    private static final DateTimeFormatter JAM = DateTimeFormatter.ofPattern("HH:mm");

    private final AnalisisService analisis;
    private final RiwayatService riwayat;

    // siapkan dependensi ekspor
    public LaporanExportService(AnalisisService analisis, RiwayatService riwayat) {
        this.analisis = analisis;
        this.riwayat = riwayat;
    }


    // export laporan excel
    public void ekspor(LocalDate awal, LocalDate akhir, File tujuan, String penyusun)
            throws SQLException, IOException {
        long omzet = analisis.omzet(awal, akhir);
        long diskon = analisis.totalDiskon(awal, akhir);
        int unit = analisis.unitTerjual(awal, akhir);
        int transaksi = analisis.jumlahTransaksi(awal, akhir);
        int jenis = analisis.jenisProdukTerjual(awal, akhir);
        List<BarisAnalisis> rincian = analisis.rincianPerProduk(awal, akhir);
        Map<LocalDate, Long> tren = analisis.trenOmzetHarian(awal, akhir);
        String periode = Format.tanggal(awal) + " s/d " + Format.tanggal(akhir);

        ExcelWriter w = new ExcelWriter();
        ringkasan(w, periode, omzet, diskon, unit, transaksi, jenis, penyusun);
        rincian(w, periode, rincian, omzet, diskon, unit);
        tren(w, periode, tren);
        daftarTransaksi(w, periode, awal, akhir);
        w.simpan(tujuan);
    }

    // lembar ringkasan periode
    private void ringkasan(ExcelWriter w, String periode, long omzet, long diskon,
                           int unit, int transaksi, int jenis, String penyusun) {
        ExcelWriter.Lembar s = w.lembarBaru("Ringkasan");
        s.lebar(0, 30);
        s.lebar(1, 22);

        s.baris().teks("LAPORAN ANALISIS PENJUALAN", ExcelWriter.JUDUL);
        s.gabung(0, 0, 0, 1);
        s.baris().teks("FlatSmart: Sistem Informasi Operasional Minimarket", ExcelWriter.HALUS);
        s.gabung(1, 0, 1, 1);
        s.barisKosong();

        s.baris().teks("Periode", ExcelWriter.LABEL).teks(periode, ExcelWriter.TEKS);
        s.baris().teks("Dibuat pada", ExcelWriter.LABEL)
                .teks(Format.tanggalJam(LocalDateTime.now()), ExcelWriter.TEKS);
        s.baris().teks("Disusun oleh", ExcelWriter.LABEL).teks(penyusun, ExcelWriter.TEKS);
        s.barisKosong();

        s.baris().teks("RINGKASAN PERIODE", ExcelWriter.SUBJUDUL);
        s.gabung(7, 0, 7, 1);
        s.baris().teks("Indikator", ExcelWriter.KEPALA).teks("Nilai", ExcelWriter.KEPALA);
        s.baris().teks("Omzet penjualan produk", ExcelWriter.TEKS).angka(omzet, ExcelWriter.RUPIAH);
        s.baris().teks("Total potongan promo", ExcelWriter.TEKS).angka(diskon, ExcelWriter.RUPIAH);
        s.baris().teks("Unit terjual", ExcelWriter.TEKS).angka(unit, ExcelWriter.ANGKA);
        s.baris().teks("Jumlah transaksi produk", ExcelWriter.TEKS).angka(transaksi, ExcelWriter.ANGKA);
        s.baris().teks("Jenis produk terjual", ExcelWriter.TEKS).angka(jenis, ExcelWriter.ANGKA);
        s.barisKosong();
        s.baris().teks("Nominal layanan tagihan tidak dihitung sebagai omzet produk.", ExcelWriter.HALUS);
        s.gabung(s.barisJumlah() - 1, 0, s.barisJumlah() - 1, 1);
    }

    // lembar rincian produk
    private void rincian(ExcelWriter w, String periode, List<BarisAnalisis> rincian,
                         long omzet, long diskon, int unit) {
        ExcelWriter.Lembar s = w.lembarBaru("Rincian Produk");
        int[] lebar = {6, 18, 36, 12, 18, 18, 14};
        for (int i = 0; i < lebar.length; i++) {
            s.lebar(i, lebar[i]);
        }

        s.baris().teks("RINCIAN PRODUK TERJUAL", ExcelWriter.JUDUL).lewati().lewati()
                .lewati().lewati().lewati().lewati();
        s.gabung(0, 0, 0, 6);
        s.baris().teks("Periode " + periode, ExcelWriter.HALUS).lewati().lewati()
                .lewati().lewati().lewati().lewati();
        s.gabung(1, 0, 1, 6);
        s.barisKosong();
        s.baris().teks("No", ExcelWriter.KEPALA).teks("Barcode", ExcelWriter.KEPALA)
                .teks("Nama Produk", ExcelWriter.KEPALA).teks("Unit", ExcelWriter.KEPALA)
                .teks("Omzet", ExcelWriter.KEPALA).teks("Diskon", ExcelWriter.KEPALA)
                .teks("Label", ExcelWriter.KEPALA);

        int no = 1;
        for (BarisAnalisis r : rincian) {
            s.baris().teks(String.valueOf(no++), ExcelWriter.TEKS)
                    .teks(r.getBarcode(), ExcelWriter.TEKS)
                    .teks(r.getNamaProduk(), ExcelWriter.TEKS)
                    .angka(r.getJumlahTerjual(), ExcelWriter.ANGKA)
                    .angka(r.getOmzet(), ExcelWriter.RUPIAH)
                    .angka(r.getTotalDiskon(), ExcelWriter.RUPIAH)
                    .teks(r.getLabel(), ExcelWriter.TEKS);
        }

        s.baris().teks("TOTAL", ExcelWriter.LABEL).kosong(ExcelWriter.LABEL)
                .kosong(ExcelWriter.LABEL)
                .angka(unit, ExcelWriter.ANGKA)
                .angka(omzet, ExcelWriter.RUPIAH)
                .angka(diskon, ExcelWriter.RUPIAH)
                .kosong(ExcelWriter.LABEL);
    }

    // lembar tren harian
    private void tren(ExcelWriter w, String periode, Map<LocalDate, Long> tren) {
        ExcelWriter.Lembar s = w.lembarBaru("Tren Harian");
        s.lebar(0, 16);
        s.lebar(1, 20);

        s.baris().teks("TREN OMZET HARIAN", ExcelWriter.JUDUL).lewati();
        s.gabung(0, 0, 0, 1);
        s.baris().teks("Periode " + periode, ExcelWriter.HALUS).lewati();
        s.gabung(1, 0, 1, 1);
        s.barisKosong();
        s.baris().teks("Tanggal", ExcelWriter.KEPALA).teks("Omzet", ExcelWriter.KEPALA);
        for (Map.Entry<LocalDate, Long> hari : tren.entrySet()) {
            s.baris().tanggal(hari.getKey(), ExcelWriter.TANGGAL)
                    .angka(hari.getValue(), ExcelWriter.RUPIAH);
        }
    }

    // lembar daftar transaksi
    private void daftarTransaksi(ExcelWriter w, String periode, LocalDate awal, LocalDate akhir)
            throws SQLException {
        List<Transaksi> daftar = riwayat.cariSemua(awal, akhir);
        Map<Integer, List<DetailTransaksi>> itemPerTransaksi = new LinkedHashMap<>();
        for (DetailTransaksi d : riwayat.cariDetailSemua(awal, akhir)) {
            itemPerTransaksi.computeIfAbsent(d.getIdTransaksi(), k -> new ArrayList<>()).add(d);
        }

        ExcelWriter.Lembar s = w.lembarBaru("Detail Transaksi");
        int[] lebar = {5, 13, 12, 8, 22, 11, 10, 34, 10, 7, 14, 13, 16};
        for (int i = 0; i < lebar.length; i++) {
            s.lebar(i, lebar[i]);
        }

        s.baris().teks("DAFTAR TRANSAKSI & PRODUK", ExcelWriter.JUDUL);
        s.gabung(0, 0, 0, 12);
        s.baris().teks("Periode " + periode + " (seluruh kasir)", ExcelWriter.HALUS);
        s.gabung(1, 0, 1, 12);
        s.barisKosong();
        s.baris().teks("No", ExcelWriter.KEPALA).teks("ID Transaksi", ExcelWriter.KEPALA)
                .teks("Tanggal", ExcelWriter.KEPALA).teks("Jam", ExcelWriter.KEPALA)
                .teks("Kasir", ExcelWriter.KEPALA).teks("Jenis", ExcelWriter.KEPALA)
                .teks("Metode", ExcelWriter.KEPALA).teks("Produk", ExcelWriter.KEPALA)
                .teks("Batch", ExcelWriter.KEPALA).teks("Qty", ExcelWriter.KEPALA)
                .teks("Harga", ExcelWriter.KEPALA).teks("Diskon", ExcelWriter.KEPALA)
                .teks("Subtotal", ExcelWriter.KEPALA);

        int nomor = 1;
        long totalUnit = 0, totalDiskon = 0, totalOmzet = 0;
        for (Transaksi trx : daftar) {
            if (trx.isPenjualan()) {
                for (DetailTransaksi d : itemPerTransaksi.getOrDefault(trx.getIdTransaksi(), List.of())) {
                    long potongan = d.getHargaSebelumDiskon() * d.getJumlah() - d.getSubtotal();
                    barisTransaksi(s, nomor++, trx, Format.judul(d.getNamaProduk()),
                            d.getIdStok(), d.getJumlah(), d.getHargaSebelumDiskon(),
                            potongan, d.getSubtotal());
                    totalUnit += d.getJumlah();
                    totalDiskon += potongan;
                    totalOmzet += d.getSubtotal();
                }
            } else {
                String ket = trx.getKeteranganTagihan() == null
                        ? "Layanan tagihan" : trx.getKeteranganTagihan().replace(" | ", ", ");
                long adm = trx.getTotalBayar() - trx.getSubtotal();
                barisTransaksi(s, nomor++, trx, Format.judul(ket), "-", 1,
                        trx.getSubtotal(), -adm, trx.getTotalBayar());
            }
        }

        int barisTotal = s.barisJumlah();
        s.baris().teks("TOTAL PENJUALAN PRODUK", ExcelWriter.LABEL)
                .kosong(ExcelWriter.LABEL).kosong(ExcelWriter.LABEL).kosong(ExcelWriter.LABEL)
                .kosong(ExcelWriter.LABEL).kosong(ExcelWriter.LABEL).kosong(ExcelWriter.LABEL)
                .kosong(ExcelWriter.LABEL).kosong(ExcelWriter.LABEL)
                .angka(totalUnit, ExcelWriter.ANGKA)
                .kosong(ExcelWriter.LABEL)
                .angka(totalDiskon, ExcelWriter.RUPIAH)
                .angka(totalOmzet, ExcelWriter.RUPIAH);
        s.gabung(barisTotal, 0, barisTotal, 8);

        s.barisKosong();
        int barisCatatan = s.barisJumlah();
        s.baris().teks("Baris Jenis Tagihan tidak ikut terhitung pada total omzet produk. "
                + "Kolom Diskon bernilai negatif untuk tagihan berarti biaya administrasi.", ExcelWriter.HALUS);
        s.gabung(barisCatatan, 0, barisCatatan, 12);
    }

    // tulis baris transaksi
    private void barisTransaksi(ExcelWriter.Lembar s, int nomor, Transaksi trx, String produk,
                                String batch, int qty, long harga, long diskon, long subtotal) {
        s.baris().teks(String.valueOf(nomor), ExcelWriter.TEKS)
                .angka(trx.getIdTransaksi(), ExcelWriter.ANGKA)
                .tanggal(trx.getWaktu().toLocalDate(), ExcelWriter.TANGGAL)
                .teks(trx.getWaktu().format(JAM), ExcelWriter.TEKS)
                .teks(trx.getNamaKasir(), ExcelWriter.TEKS)
                .teks(Format.judul(trx.getJenis()), ExcelWriter.TEKS)
                .teks(Format.metode(trx.getMetodeBayar()), ExcelWriter.TEKS)
                .teks(produk, ExcelWriter.TEKS)
                .teks(batch, ExcelWriter.TEKS)
                .angka(qty, ExcelWriter.ANGKA)
                .angka(harga, ExcelWriter.RUPIAH)
                .angka(diskon, ExcelWriter.RUPIAH)
                .angka(subtotal, ExcelWriter.RUPIAH);
    }
}
