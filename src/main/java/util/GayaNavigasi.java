package util;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;

public final class GayaNavigasi {

    private static final String AKTIF = "menuNavigasi.aktif";

    private static final Color HOVER = new Color(38, 63, 105);
    private static final Color AKTIF_BG = new Color(47, 78, 128);
    private static final Color AKSEN = new Color(96, 165, 250);
    private static final Color TEKS_REDA = new Color(190, 205, 230);
    private static final Color TEKS_TERANG = Color.WHITE;

    private GayaNavigasi() {
    }

    public static void pasang(JButton... menu) {
        for (JButton tombol : menu) {
            tombol.setContentAreaFilled(false);
            tombol.addMouseListener(new MouseAdapter() {
                @Override public void mouseEntered(MouseEvent e) {
                    warnakan(tombol, true);
                }

                @Override public void mouseExited(MouseEvent e) {
                    warnakan(tombol, false);
                }
            });
            warnakan(tombol, false);
        }
    }

    public static void tandaiAktif(JButton aktif, JButton... menu) {
        for (JButton tombol : menu) {
            tombol.putClientProperty(AKTIF, tombol == aktif);
            warnakan(tombol, false);
        }
    }

    private static void warnakan(JButton tombol, boolean hover) {
        boolean aktif = Boolean.TRUE.equals(tombol.getClientProperty(AKTIF));
        if (aktif) {
            tombol.setOpaque(true);
            tombol.setBackground(AKTIF_BG);
            tombol.setForeground(TEKS_TERANG);
            tombol.setBorder(BorderFactory.createMatteBorder(0, 4, 0, 0, AKSEN));
        } else if (hover) {
            tombol.setOpaque(true);
            tombol.setBackground(HOVER);
            tombol.setForeground(TEKS_TERANG);
            tombol.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
        } else {
            tombol.setOpaque(false);
            tombol.setForeground(TEKS_REDA);
            tombol.setBorder(BorderFactory.createEmptyBorder(0, 4, 0, 0));
        }
    }
}
