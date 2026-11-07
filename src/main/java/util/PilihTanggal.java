package util;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 * Pemasang kalender di atas JTextField tanggal, pengganti isian manual.
 * Dipakai karena proyek ini tidak menambah dependensi eksternal baru.
 */
public final class PilihTanggal {

    private static final Locale ID = Locale.forLanguageTag("id-ID");
    private static final DateTimeFormatter NAMA_BULAN =
            DateTimeFormatter.ofPattern("MMMM yyyy", ID);
    private static final String[] HARI = {"Min", "Sen", "Sel", "Rab", "Kam", "Jum", "Sab"};
    private static final Color AKSEN = new Color(16, 40, 79);

    // konstruktor privat
    private PilihTanggal() {
    }

    /** Membuat kolom hanya-baca dan memunculkan kalender saat diklik. */
    public static void pasang(JTextField kolom) {
        pasang(kolom, null);
    }

    // pasang kalender ke kolom
    public static void pasang(JTextField kolom, Runnable setelahPilih) {
        kolom.setEditable(false);
        kolom.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        kolom.setToolTipText("Klik untuk memilih tanggal");
        kolom.setBackground(Color.WHITE);
        kolom.setBorder(BorderFactory.createCompoundBorder(
                kolom.getBorder(), BorderFactory.createEmptyBorder(2, 4, 2, 28)));

        JLabel ikon = new JLabel(new IkonKalender());
        ikon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        kolom.add(ikon);
        tempatkanIkon(kolom, ikon);
        kolom.addComponentListener(new ComponentAdapter() {
            // sesuaikan ikon saat resized
            @Override public void componentResized(ComponentEvent e) {
                tempatkanIkon(kolom, ikon);
            }
        });

        MouseAdapter buka = new MouseAdapter() {
            // buka kalender saat diklik
            @Override public void mousePressed(MouseEvent e) {
                tampilkanKalender(kolom, setelahPilih);
            }
        };
        kolom.addMouseListener(buka);
        ikon.addMouseListener(buka);
    }

    // tempatkan ikon kalender
    private static void tempatkanIkon(JTextField kolom, JLabel ikon) {
        int tinggi = Math.max(16, kolom.getHeight() - 10);
        ikon.setBounds(kolom.getWidth() - tinggi - 8, 5, tinggi, tinggi);
    }

    // tampilkan popup kalender
    private static void tampilkanKalender(JTextField kolom, Runnable setelahPilih) {
        LocalDate awal;
        try {
            awal = Format.bacaTanggal(kolom.getText());
        } catch (RuntimeException ex) {
            awal = LocalDate.now();
        }

        JPopupMenu popup = new JPopupMenu();
        popup.add(new Kalender(awal, tanggal -> {
            kolom.setText(Format.tanggal(tanggal));
            popup.setVisible(false);
            if (setelahPilih != null) {
                setelahPilih.run();
            }
        }));
        popup.show(kolom, 0, kolom.getHeight());
    }

    /** Isi popup: navigasi bulan dan kisi tanggal. */
    private static final class Kalender extends JPanel {

        private final LocalDate acuan;
        private final java.util.function.Consumer<LocalDate> pilih;
        private LocalDate bulan;
        private final JPanel kisi = new JPanel(new GridLayout(0, 7, 2, 2));

        // konstruktor kalender
        Kalender(LocalDate acuan, java.util.function.Consumer<LocalDate> pilih) {
            this.acuan = acuan;
            this.pilih = pilih;
            this.bulan = acuan.withDayOfMonth(1);

            setLayout(new BorderLayout(0, 6));
            setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            setPreferredSize(new Dimension(232, 240));
            add(kepala(), BorderLayout.NORTH);
            add(kisi, BorderLayout.CENTER);
            gambarUlang();
        }

        // panel kepala kalender
        private JPanel kepala() {
            JPanel panel = new JPanel(new BorderLayout(4, 0));
            panel.setOpaque(false);
            JLabel judul = new JLabel(bulan.format(NAMA_BULAN), SwingConstants.CENTER);
            judul.setFont(new Font("Segoe UI", Font.BOLD, 12));
            judul.setForeground(new Color(15, 30, 60));

            panel.add(tombolNav("«", -12), BorderLayout.WEST);
            panel.add(tombolNav("‹", -1), BorderLayout.LINE_START);
            panel.add(judul, BorderLayout.CENTER);
            panel.add(tombolNav("›", 1), BorderLayout.LINE_END);
            panel.add(tombolNav("»", 12), BorderLayout.EAST);
            return panel;
        }

        // tombol navigasi bulan
        private JButton tombolNav(String teks, int geserBulan) {
            JButton tombol = new JButton(teks);
            tombol.setFont(new Font("Segoe UI", Font.BOLD, 12));
            tombol.setMargin(new Insets(1, 4, 1, 4));
            tombol.setFocusPainted(false);
            tombol.setBorderPainted(false);
            tombol.setContentAreaFilled(false);
            tombol.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            tombol.addActionListener(e -> {
                bulan = bulan.plusMonths(geserBulan);
                removeAll();
                add(kepala(), BorderLayout.NORTH);
                add(kisi, BorderLayout.CENTER);
                gambarUlang();
                revalidate();
                repaint();
            });
            return tombol;
        }

        // gambar ulang kisi tanggal
        private void gambarUlang() {
            kisi.setOpaque(false);
            kisi.removeAll();
            for (String nama : HARI) {
                kisi.add(labelKecil(nama, new Color(100, 116, 139), true));
            }
            LocalDate hariPertama = bulan.withDayOfMonth(1);
            LocalDate mulai = hariPertama.minusDays(hariPertama.getDayOfWeek().getValue() % 7);
            LocalDate hariIni = LocalDate.now();
            for (int i = 0; i < 42; i++) {
                LocalDate tanggal = mulai.plusDays(i);
                boolean bulanIni = tanggal.getMonth() == bulan.getMonth();
                kisi.add(sel(tanggal, bulanIni, tanggal.equals(hariIni), tanggal.equals(acuan)));
            }
        }

        // label kecil hari
        private JLabel labelKecil(String teks, Color warna, boolean tebal) {
            JLabel label = new JLabel(teks, SwingConstants.CENTER);
            label.setFont(new Font("Segoe UI", tebal ? Font.BOLD : Font.PLAIN, 10));
            label.setForeground(warna);
            return label;
        }

        // tombol sel tanggal
        private JButton sel(LocalDate tanggal, boolean bulanIni, boolean hariIni, boolean terpilih) {
            JButton sel = new JButton(String.valueOf(tanggal.getDayOfMonth()));
            sel.setFont(new Font("Segoe UI", hariIni || terpilih ? Font.BOLD : Font.PLAIN, 11));
            sel.setMargin(new Insets(1, 0, 1, 0));
            sel.setFocusPainted(false);
            sel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            sel.setOpaque(true);
            sel.setBackground(terpilih ? AKSEN : Color.WHITE);
            sel.setForeground(!bulanIni ? new Color(190, 199, 213)
                    : terpilih ? Color.WHITE : new Color(15, 30, 60));
            sel.setBorder(BorderFactory.createLineBorder(
                    hariIni && !terpilih ? AKSEN : new Color(226, 231, 240)));
            sel.setToolTipText(Format.tanggalPanjang(tanggal));
            sel.addActionListener(e -> pilih.accept(tanggal));
            return sel;
        }
    }

    private static final class IkonKalender implements Icon {

        // lebar ikon
        @Override public int getIconWidth() {
            return 16;
        }

        // tinggi ikon
        @Override public int getIconHeight() {
            return 16;
        }

        // gambar ikon kalender
        @Override public void paintIcon(Component c, Graphics g, int x, int y) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(AKSEN);
            g2.fillRoundRect(x, y + 2, 16, 14, 4, 4);
            g2.setColor(Color.WHITE);
            g2.fillRect(x + 2, y + 6, 12, 8);
            g2.setColor(AKSEN);
            for (int baris = 0; baris < 2; baris++) {
                for (int kolom = 0; kolom < 3; kolom++) {
                    g2.fillRect(x + 3 + kolom * 4, y + 7 + baris * 4, 2, 2);
                }
            }
            g2.setColor(new Color(15, 30, 60));
            g2.fillRect(x + 3, y, 2, 4);
            g2.fillRect(x + 11, y, 2, 4);
            g2.dispose();
        }
    }
}
