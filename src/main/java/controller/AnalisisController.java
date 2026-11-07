package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import model.BarisAnalisis;
import model.PenjualanProduk;
import model.Stok;
import service.AnalisisService;


public class AnalisisController extends BasisController {

    private final AnalisisService service = new AnalisisService();

    public AnalisisController() {
        super("Analisis");
    }

    public long omzet(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.omzet(awal, akhir);
    }

    public int jumlahTransaksi(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.jumlahTransaksi(awal, akhir);
    }

    public int unitTerjual(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.unitTerjual(awal, akhir);
    }

    public int jenisProdukTerjual(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.jenisProdukTerjual(awal, akhir);
    }

    public long totalDiskon(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.totalDiskon(awal, akhir);
    }

    public Map<LocalDate, Long> trenOmzetHarian(LocalDate awal, LocalDate akhir)
            throws SQLException {
        return service.trenOmzetHarian(awal, akhir);
    }

    public List<BarisAnalisis> rincianPerProduk(LocalDate awal, LocalDate akhir)
            throws SQLException {
        return service.rincianPerProduk(awal, akhir);
    }

    public List<Stok> prioritasBatch() throws SQLException {
        return service.prioritasBatch();
    }

    public List<PenjualanProduk> riwayatProduk(String barcode, LocalDate awal, LocalDate akhir)
            throws SQLException {
        return service.riwayatProduk(barcode, awal, akhir);
    }
}
