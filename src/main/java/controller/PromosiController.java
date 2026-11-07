package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Promosi;
import model.Stok;
import service.PromosiService;

public class PromosiController extends BasisController implements CrudController<Promosi> {

    private final PromosiService service = new PromosiService();

    public PromosiController() {
        super("Promosi");
    }

    @Override
    public List<Promosi> semua() throws SQLException {
        return service.cari("", null);
    }

    @Override
    public Promosi cari(String kode) throws SQLException {
        String id = idWajib(kode);
        for (Promosi promosi : service.cari(id, null)) {
            if (promosi.getKodePromosi().equals(id)) {
                return promosi;
            }
        }
        return null;
    }

    @Override
    public void tambah(Promosi promosi) throws SQLException {
        service.simpanBaru(promosi.getKodePromosi(), promosi.getNamaPromosi(), promosi.getIdStok(),
                promosi.getDiskonPersen(), promosi.getTglMulai(), promosi.getTglSelesai());
    }

    @Override
    public void ubah(Promosi promosi) throws SQLException {
        service.ubah(promosi.getKodePromosi(), promosi.getNamaPromosi(), promosi.getIdStok(),
                promosi.getDiskonPersen(), promosi.getTglMulai(), promosi.getTglSelesai());
    }

    @Override
    public void hapus(String kode) throws SQLException {
        service.hapus(idWajib(kode));
    }

    public List<Promosi> cari(String keyword, LocalDate tanggal) throws SQLException {
        return service.cari(keyword, tanggal);
    }

    public String buatKode() throws SQLException {
        return service.buatKode();
    }

    public List<Stok> cariBatch(String barcode) throws SQLException {
        return service.cariBatch(idWajib(barcode));
    }

    public int hitungTotal() throws SQLException {
        return service.hitungTotal();
    }

    public int hitungBatchTarget() throws SQLException {
        return service.hitungBatchTarget();
    }

    public int hitungBatchDekatKedaluwarsa() throws SQLException {
        return service.hitungBatchDekatKedaluwarsa();
    }
}
