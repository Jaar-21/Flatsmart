package controller;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import service.AnalisisService;
import service.LaporanExportService;
import service.RiwayatService;

public class LaporanController extends BasisController {

    private final LaporanExportService service =
            new LaporanExportService(new AnalisisService(), new RiwayatService());

    public LaporanController() {
        super("Laporan");
    }

    public void ekspor(LocalDate awal, LocalDate akhir, File tujuan, String penyusun)
            throws SQLException, IOException {
        service.ekspor(awal, akhir, tujuan, penyusun);
    }
}
