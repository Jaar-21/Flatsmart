package controller;

import java.sql.SQLException;
import java.util.List;
import model.Transaksi;
import service.TagihanService;

public class TagihanController extends BasisController {

    public static final long BIAYA_ADMIN = TagihanService.BIAYA_ADMIN;

    private final TagihanService service = new TagihanService();

    public TagihanController() {
        super("Tagihan");
    }

    public Transaksi proses(String jenisLayanan, String provider, String noPelanggan,
                            String namaPelanggan, long nominal, String metode, long uangDiterima)
            throws SQLException {
        return service.proses(jenisLayanan, provider, noPelanggan, namaPelanggan,
                nominal, metode, uangDiterima);
    }

    public List<String> daftarJenis() throws SQLException {
        return service.daftarJenis();
    }

    public List<String> daftarProvider(String jenis) throws SQLException {
        return service.daftarProvider(jenis);
    }
}
