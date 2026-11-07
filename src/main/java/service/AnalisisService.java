package service;

import dao.LaporanDAO;
import dao.StokDAO;
import model.BarisAnalisis;
import model.PenjualanProduk;
import model.Stok;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class AnalisisService {

    private final LaporanDAO laporanDAO = new LaporanDAO();
    private final StokDAO stokDAO = new StokDAO();

    // total omzet
    public long omzet(LocalDate awal, LocalDate akhir) throws SQLException {
        return laporanDAO.omzet(awal, akhir);
    }

    // jumlah transaksi
    public int jumlahTransaksi(LocalDate awal, LocalDate akhir) throws SQLException {
        return laporanDAO.jumlahTransaksi(awal, akhir);
    }

    // unit terjual
    public int unitTerjual(LocalDate awal, LocalDate akhir) throws SQLException {
        return laporanDAO.unitTerjual(awal, akhir);
    }

    // jenis produk terjual
    public int jenisProdukTerjual(LocalDate awal, LocalDate akhir) throws SQLException {
        return laporanDAO.jenisProdukTerjual(awal, akhir);
    }

    // total diskon
    public long totalDiskon(LocalDate awal, LocalDate akhir) throws SQLException {
        return laporanDAO.totalDiskon(awal, akhir);
    }

    // tren omzet harian
    public Map<LocalDate, Long> trenOmzetHarian(LocalDate awal, LocalDate akhir) throws SQLException {
        return laporanDAO.trenOmzetHarian(awal, akhir);
    }

    // rincian per produk
    public List<BarisAnalisis> rincianPerProduk(LocalDate awal, LocalDate akhir) throws SQLException {
        return laporanDAO.rincianPerProduk(awal, akhir);
    }

    // batch prioritas cek
    public List<Stok> prioritasBatch() throws SQLException {
        return stokDAO.cariDekatKedaluwarsa(StokDAO.HARI_DEKAT_KEDALUWARSA);
    }

    // riwayat produk terjual
    public List<PenjualanProduk> riwayatProduk(String barcode, LocalDate awal, LocalDate akhir)
            throws SQLException {
        return laporanDAO.riwayatProduk(barcode, awal, akhir);
    }
}
