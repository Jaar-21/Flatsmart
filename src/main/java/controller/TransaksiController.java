package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.DetailTransaksi;
import model.TagihanTransaksi;
import model.Transaksi;
import service.RiwayatService;

public class TransaksiController extends BasisController {

    private final RiwayatService service = new RiwayatService();

    // konstruktor transaksi
    public TransaksiController() {
        super("Transaksi");
    }

    // cari transaksi riwayat
    public List<Transaksi> cari(LocalDate awal, LocalDate akhir, String jenis, String keyword)
            throws SQLException {
        return service.cari(awal, akhir, jenis, keyword);
    }

    // cari semua transaksi
    public List<Transaksi> cariSemua(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.cariSemua(awal, akhir);
    }

    // cari detail transaksi
    public List<DetailTransaksi> cariDetail(int idTransaksi) throws SQLException {
        return service.cariDetail(idTransaksi);
    }

    // detail semua transaksi
    public List<DetailTransaksi> cariDetailSemua(LocalDate awal, LocalDate akhir)
            throws SQLException {
        return service.cariDetailSemua(awal, akhir);
    }

    // cari data tagihan
    public TagihanTransaksi cariTagihan(int idTransaksi) throws SQLException {
        return service.cariTagihan(idTransaksi);
    }

    // buat teks struk
    public String buatStruk(Transaksi trx) throws SQLException {
        return service.generateStruk(trx);
    }
}
