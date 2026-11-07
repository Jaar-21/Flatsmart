package util;

import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public final class Pesan {

    private static final Logger LOG = Logger.getLogger("view");

    // konstruktor privat
    private Pesan() {
    }

    // dialog peringatan
    public static void peringatan(Component parent, String pesan) {
        JOptionPane.showMessageDialog(parent, pesan, "Peringatan", JOptionPane.WARNING_MESSAGE);
    }

    // dialog sukses
    public static void sukses(Component parent, String pesan) {
        JOptionPane.showMessageDialog(parent, pesan, "Sukses", JOptionPane.INFORMATION_MESSAGE);
    }

    // dialog info
    public static void info(Component parent, String pesan) {
        JOptionPane.showMessageDialog(parent, pesan, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    // dialog galat
    public static void galat(Component parent, String pesan, Throwable sebab) {
        LOG.log(Level.SEVERE, pesan, sebab);
        JOptionPane.showMessageDialog(parent, pesan, "Error", JOptionPane.ERROR_MESSAGE);
    }

    // dialog gagal database
    public static void gagalDatabase(Component parent, Exception sebab) {
        galat(parent, "Terjadi masalah pada database:\n" + sebab.getMessage(), sebab);
    }

    // dialog konfirmasi
    public static boolean konfirmasi(Component parent, String pertanyaan) {
        return JOptionPane.showConfirmDialog(parent, pertanyaan, "Konfirmasi",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    // dialog pilihan
    public static int pilihan(Component parent, String pertanyaan, String judul, String... opsi) {
        return JOptionPane.showOptionDialog(parent, pertanyaan, judul, JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, opsi, opsi[0]);
    }

    // dialog isi bebas
    public static void tampilkan(Component parent, Object isi, String judul) {
        JOptionPane.showMessageDialog(parent, isi, judul, JOptionPane.PLAIN_MESSAGE);
    }
}
