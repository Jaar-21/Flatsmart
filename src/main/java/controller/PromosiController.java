package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import model.Promosi;
import model.Stok;
import service.PromosiService;

/**
 * Controller modul promosi: menambah, mengubah, dan menghapus promo otomatis
 * memasang/melepas kaitannya ke batch stok lewat service.
 */
public class PromosiController extends BasisController implements CrudController<Promosi> {

    private final PromosiService service = new PromosiService();

    // konstruktor promosi
    public PromosiController() {
        super("Promosi");
    }

    // ambil semua promosi
    @Override
    public List<Promosi> semua() throws SQLException {
        return service.cari("", null);
    }

    // cari promosi lewat kode
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

    // tambah promosi
    @Override
    public void tambah(Promosi promosi) throws SQLException {
        service.simpanBaru(promosi.getKodePromosi(), promosi.getNamaPromosi(), promosi.getIdStok(),
                promosi.getDiskonPersen(), promosi.getTglMulai(), promosi.getTglSelesai());
    }

    // ubah promosi
    @Override
    public void ubah(Promosi promosi) throws SQLException {
        service.ubah(promosi.getKodePromosi(), promosi.getNamaPromosi(), promosi.getIdStok(),
                promosi.getDiskonPersen(), promosi.getTglMulai(), promosi.getTglSelesai());
    }

    // hapus promosi
    @Override
    public void hapus(String kode) throws SQLException {
        service.hapus(idWajib(kode));
    }

    // cari promosi bertapis
    public List<Promosi> cari(String keyword, LocalDate tanggal) throws SQLException {
        return service.cari(keyword, tanggal);
    }

    // buat kode promosi
    public String buatKode() throws SQLException {
        return service.buatKode();
    }

    // cari batch promosi
    public List<Stok> cariBatch(String barcode) throws SQLException {
        return service.cariBatch(idWajib(barcode));
    }

    // hitung total promosi
    public int hitungTotal() throws SQLException {
        return service.hitungTotal();
    }

    // hitung batch target
    public int hitungBatchTarget() throws SQLException {
        return service.hitungBatchTarget();
    }

    // hitung batch dekat kedaluwarsa
    public int hitungBatchDekatKedaluwarsa() throws SQLException {
        return service.hitungBatchDekatKedaluwarsa();
    }
}
