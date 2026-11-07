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

    // konstruktor analisis
    public AnalisisController() {
        super("Analisis");
    }

    // hitung omzet
    public long omzet(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.omzet(awal, akhir);
    }

    // hitung jumlah transaksi
    public int jumlahTransaksi(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.jumlahTransaksi(awal, akhir);
    }

    // hitung unit terjual
    public int unitTerjual(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.unitTerjual(awal, akhir);
    }

    // hitung jenis produk terjual
    public int jenisProdukTerjual(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.jenisProdukTerjual(awal, akhir);
    }

    // hitung total diskon
    public long totalDiskon(LocalDate awal, LocalDate akhir) throws SQLException {
        return service.totalDiskon(awal, akhir);
    }

    // tren omzet harian
    public Map<LocalDate, Long> trenOmzetHarian(LocalDate awal, LocalDate akhir)
            throws SQLException {
        return service.trenOmzetHarian(awal, akhir);
    }

    // rincian per produk
    public List<BarisAnalisis> rincianPerProduk(LocalDate awal, LocalDate akhir)
            throws SQLException {
        return service.rincianPerProduk(awal, akhir);
    }

    // prioritas batch stok
    public List<Stok> prioritasBatch() throws SQLException {
        return service.prioritasBatch();
    }

    // riwayat penjualan produk
    public List<PenjualanProduk> riwayatProduk(String barcode, LocalDate awal, LocalDate akhir)
            throws SQLException {
        return service.riwayatProduk(barcode, awal, akhir);
    }
}
