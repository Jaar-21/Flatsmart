package controller;

import java.sql.SQLException;
import java.util.List;
import model.Transaksi;
import service.TagihanService;

/**
 * Controller pembayaran tagihan (pulsa, listrik, air, dan layanan lainnya).
 */
public class TagihanController extends BasisController {

    public static final long BIAYA_ADMIN = TagihanService.BIAYA_ADMIN;

    private final TagihanService service = new TagihanService();

    // konstruktor tagihan
    public TagihanController() {
        super("Tagihan");
    }

    // proses pembayaran tagihan
    public Transaksi proses(String jenisLayanan, String provider, String noPelanggan,
                            String namaPelanggan, long nominal, String metode, long uangDiterima)
            throws SQLException {
        return service.proses(jenisLayanan, provider, noPelanggan, namaPelanggan,
                nominal, metode, uangDiterima);
    }

    // daftar jenis layanan
    public List<String> daftarJenis() throws SQLException {
        return service.daftarJenis();
    }

    // daftar provider layanan
    public List<String> daftarProvider(String jenis) throws SQLException {
        return service.daftarProvider(jenis);
    }
}
