package main;

import config.DatabaseConnection;
import javax.swing.SwingUtilities;
import util.Pesan;
import view.LoginFrame;

public class Main {

    public static void main(String[] args) {
        System.setProperty("sun.java2d.uiScale", "1");
        SwingUtilities.invokeLater(() -> {
            try {
                DatabaseConnection.periksaKoneksi();
                new LoginFrame().setVisible(true);
            } catch (Exception e) {
                Pesan.galat(null, "Tidak dapat terhubung ke database db_flatsmart.\n"
                        + "Pastikan MySQL sudah berjalan.", e);
            }
        });
    }
}
