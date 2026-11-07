package service;

import config.DatabaseConnection;
import dao.TransaksiDAO;
import model.TagihanTransaksi;
import model.Transaksi;
import util.Sesi;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class TagihanService {

    public static final long BIAYA_ADMIN = 2500;

    private final TransaksiDAO transaksiDAO = new TransaksiDAO();

    public Transaksi proses(String jenisLayanan, String provider, String noPelanggan,
                            String namaPelanggan, long nominal, String metode, long uangDiterima)
            throws SQLException {

        if (jenisLayanan == null || jenisLayanan.isBlank())
            throw new IllegalArgumentException("Jenis layanan wajib diisi.");
        if (provider == null || provider.isBlank())
            throw new IllegalArgumentException("Provider wajib diisi.");
        if (noPelanggan == null || noPelanggan.isBlank())
            throw new IllegalArgumentException("Nomor pelanggan wajib diisi.");
        if (namaPelanggan == null || namaPelanggan.isBlank())
            throw new IllegalArgumentException("Nama pelanggan wajib diisi.");
        if (nominal <= 0)
            throw new IllegalArgumentException("Nominal tagihan harus lebih dari 0.");
        if (metode == null || metode.isBlank())
            throw new IllegalArgumentException("Metode pembayaran belum dipilih.");

        long totalBayar = nominal + BIAYA_ADMIN;
        if (!"Tunai".equalsIgnoreCase(metode)) {
            uangDiterima = totalBayar;
        }
        if (uangDiterima < totalBayar) {
            throw new IllegalArgumentException("Uang diterima kurang dari total pembayaran.");
        }

        Transaksi trx = new Transaksi();
        trx.setUsername(Sesi.username());
        trx.setJenis("tagihan");
        trx.setMetodeBayar(metode);
        trx.setSubtotal(nominal);
        trx.setTotalDiskon(0);
        trx.setTotalBayar(totalBayar);
        trx.setUangDiterima(uangDiterima);
        trx.setKembalian(uangDiterima - totalBayar);
        trx.setWaktu(LocalDateTime.now());

        TagihanTransaksi tagihan = new TagihanTransaksi();
        tagihan.setJenisLayanan(jenisLayanan.trim());
        tagihan.setProvider(provider.trim());
        tagihan.setNomorPelanggan(noPelanggan.trim());
        tagihan.setNamaPelanggan(namaPelanggan.trim());
        tagihan.setNominal(nominal);

        DatabaseConnection.transaction(() -> {
            tagihan.setIdTransaksi(transaksiDAO.simpanHeader(trx));
            transaksiDAO.simpanTagihan(tagihan);
            trx.setIdTransaksi(tagihan.getIdTransaksi());
            return trx;
        });
        return trx;
    }

    public List<String> daftarJenis() throws SQLException {
        return transaksiDAO.daftarJenisTagihan();
    }

    public List<String> daftarProvider(String jenis) throws SQLException {
        return transaksiDAO.daftarProviderTagihan(jenis);
    }
}
