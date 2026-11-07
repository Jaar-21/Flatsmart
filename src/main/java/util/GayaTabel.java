package util;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public final class GayaTabel {

    public static final int UBAH = 0;
    public static final int HAPUS = 1;

    private static final Color HEADER_BG = new Color(16, 40, 79);
    private static final Color HEADER_FG = new Color(255, 255, 255);
    private static final Color BARIS_A = Color.WHITE;
    private static final Color BARIS_B = new Color(247, 249, 252);
    private static final Color GARIS = new Color(226, 231, 240);
    private static final Color TEKS = new Color(30, 41, 59);
    private static final Color TEKS_PILIH = new Color(16, 40, 79);
    private static final Color PILIH_BG = new Color(232, 240, 254);
    private static final Color AKSI_BG = new Color(16, 40, 79);
    private static final Color AKSI_SIAP = new Color(10, 26, 54);

    private static final int TINGGI_BARIS = 38;
    private static final int TINGGI_TOMBOL = 26;
    private static final int LEBAR_TOMBOL = 64;
    private static final int JARAK = 8;
    private static final int BANTAL = 10;

    private GayaTabel() {
    }

    public static void rapikan(JTable tabel, int[] rataKanan, int[] rataTengah) {
        tabel.setRowHeight(TINGGI_BARIS);
        tabel.setShowGrid(false);
        tabel.setIntercellSpacing(new Dimension(0, 0));
        tabel.setGridColor(GARIS);
        tabel.setSelectionBackground(PILIH_BG);
        tabel.setSelectionForeground(TEKS_PILIH);
        tabel.setFillsViewportHeight(true);
        tabel.setRowSelectionAllowed(true);
        tabel.setColumnSelectionAllowed(false);
        tabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        JTableHeader kepala = tabel.getTableHeader();
        kepala.setReorderingAllowed(false);
        kepala.setDefaultRenderer(new KepalaSel(SwingConstants.LEFT));
        kepala.setPreferredSize(new Dimension(Math.max(totalLebar(tabel), 400), 34));

        for (int i = 0; i < tabel.getColumnCount(); i++) {
            int arah = mengandung(rataKanan, i) ? SwingConstants.RIGHT
                    : mengandung(rataTengah, i) ? SwingConstants.CENTER
                            : SwingConstants.LEFT;
            tabel.getColumnModel().getColumn(i).setCellRenderer(new IsiSel(arah));
            tabel.getColumnModel().getColumn(i).setHeaderRenderer(new KepalaSel(arah));
        }
    }

    public static int pasangAksi(JTable tabel, String judul, String... label) {
        final int kolom = kolomBernama(tabel, judul);
        if (kolom < 0) {
            return -1;
        }
        final AksiSel renderer = new AksiSel(label.length == 0
                ? new String[]{"Ubah", "Hapus"} : label);
        tabel.getColumnModel().getColumn(kolom).setCellRenderer(renderer);
        tabel.getColumnModel().getColumn(kolom).setHeaderRenderer(
                new KepalaSel(SwingConstants.RIGHT));
        final int[] terakhir = {-1, -1};

        tabel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override public void mouseMoved(MouseEvent e) {
            perbaruiHaluan(tabel, kolom, renderer, terakhir,
                    tabel.rowAtPoint(e.getPoint()), aksiDi(tabel, kolom, e.getPoint()));
            }
        });
        tabel.addMouseListener(new MouseAdapter() {
            @Override public void mouseExited(MouseEvent e) {
                perbaruiHaluan(tabel, kolom, renderer, terakhir, -1, -1);
            }
        });
        return kolom;
    }

    public static void aturLebar(JTable tabel, int[] lebar) {
        javax.swing.table.TableColumnModel model = tabel.getColumnModel();
        for (int i = 0; i < model.getColumnCount() && i < lebar.length; i++) {
            model.getColumn(i).setPreferredWidth(lebar[i]);
        }
        tabel.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        tabel.getTableHeader().setPreferredSize(
                new Dimension(Math.max(totalLebar(tabel), 400), 34));
    }

    public static int lebarMinimumAksi(int jumlahTombol) {
        return LEBAR_TOMBOL * jumlahTombol + JARAK * (jumlahTombol - 1) + BANTAL * 2;
    }

    public static int aksiDi(JTable tabel, int kolom, Point titik) {
        int baris = tabel.rowAtPoint(titik);
        if (baris < 0 || baris >= tabel.getRowCount()) {
            return -1;
        }
        Rectangle sel = tabel.getCellRect(baris, kolom, false);
        if (!sel.contains(titik)) {
            return -1;
        }
        Rectangle[] tombol = tombolDi(sel.width, sel.height, jumlahTombol(tabel, kolom));
        for (int i = 0; i < tombol.length; i++) {
            Rectangle r = new Rectangle(sel.x + tombol[i].x, sel.y + tombol[i].y,
                    tombol[i].width, tombol[i].height);
            if (r.contains(titik)) {
                return i;
            }
        }
        return -1;
    }

    private static int jumlahTombol(JTable tabel, int kolom) {
        javax.swing.table.TableCellRenderer r =
                tabel.getColumnModel().getColumn(kolom).getCellRenderer();
        return r instanceof AksiSel aksi ? aksi.label.length : 2;
    }

    private static void perbaruiHaluan(JTable tabel, int kolom, AksiSel renderer,
                                       int[] terakhir, int baris, int indeks) {
        if (baris == terakhir[0] && indeks == terakhir[1]) {
            return;
        }
       gambarUlang(tabel, kolom, terakhir[0]);
        renderer.setHaluan(baris, indeks);
        terakhir[0] = baris;
        terakhir[1] = indeks;
        gambarUlang(tabel, kolom, baris);
        tabel.setCursor(Cursor.getPredefinedCursor(
                indeks < 0 ? Cursor.DEFAULT_CURSOR : Cursor.HAND_CURSOR));
    }

    private static void gambarUlang(JTable tabel, int kolom, int baris) {
        if (baris >= 0 && baris < tabel.getRowCount()) {
            tabel.repaint(tabel.getCellRect(baris, kolom, false));
        }
    }

    private static boolean mengandung(int[] angka, int nilai) {
        if (angka == null) {
            return false;
        }
        for (int a : angka) {
            if (a == nilai) {
                return true;
            }
        }
        return false;
    }

    private static int kolomBernama(JTable tabel, String judul) {
        for (int i = 0; i < tabel.getColumnCount(); i++) {
            if (judul.equals(tabel.getColumnName(i))) {
                return i;
            }
        }
        return -1;
    }

    private static int totalLebar(JTable tabel) {
        int total = 0;
        for (int i = 0; i < tabel.getColumnCount(); i++) {
            total += tabel.getColumnModel().getColumn(i).getWidth();
        }
        return total;
    }

    private static Rectangle[] tombolDi(int lebarSel, int tinggiSel, int jumlah) {
        int total = LEBAR_TOMBOL * jumlah + JARAK * (jumlah - 1);
        int x = Math.max(6, lebarSel - total - BANTAL);
        int y = (tinggiSel - TINGGI_TOMBOL) / 2;
        Rectangle[] hasil = new Rectangle[jumlah];
        for (int i = 0; i < jumlah; i++) {
            hasil[i] = new Rectangle(x + i * (LEBAR_TOMBOL + JARAK), y, LEBAR_TOMBOL, TINGGI_TOMBOL);
        }
        return hasil;
    }

    private static final class KepalaSel extends DefaultTableCellRenderer {
        KepalaSel(int arah) {
            setHorizontalAlignment(arah);
        }

        @Override public Component getTableCellRendererComponent(JTable tabel, Object nilai,
                boolean dipilih, boolean fokus, int baris, int kolom) {
            super.getTableCellRendererComponent(tabel, nilai, dipilih, fokus, baris, kolom);
            setBackground(HEADER_BG);
            setForeground(HEADER_FG);
            setFont(new Font("Segoe UI", Font.BOLD, 11));
            setBorder(BorderFactory.createEmptyBorder(0, 8, 0, 8));
            return this;
        }
    }

    private static final class IsiSel extends DefaultTableCellRenderer {
        IsiSel(int arah) {
            setHorizontalAlignment(arah);
        }

        @Override public Component getTableCellRendererComponent(JTable tabel, Object nilai,
                boolean dipilih, boolean fokus, int baris, int kolom) {
            super.getTableCellRendererComponent(tabel, nilai, dipilih, fokus, baris, kolom);
            if (!dipilih) {
                setBackground(baris % 2 == 0 ? BARIS_A : BARIS_B);
            }
            setForeground(dipilih ? TEKS_PILIH : TEKS);
            setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createMatteBorder(0, 0, 1, 0, GARIS),
                    BorderFactory.createEmptyBorder(0, 8, 0, 8)));
            return this;
        }
    }

    private static final class AksiSel extends DefaultTableCellRenderer {

        private final String[] label;
        private int barisDigambar = -1;
        private int haluanBaris = -1;
        private int haluanIndeks = -1;

        AksiSel(String[] label) {
            this.label = label;
        }

        void setHaluan(int baris, int indeks) {
            haluanBaris = baris;
            haluanIndeks = indeks;
        }

        @Override public Component getTableCellRendererComponent(JTable tabel, Object nilai,
                boolean dipilih, boolean fokus, int baris, int kolom) {
            super.getTableCellRendererComponent(tabel, nilai, dipilih, fokus, baris, kolom);
            barisDigambar = baris;
            setText("");
            if (!dipilih) {
                setBackground(baris % 2 == 0 ? BARIS_A : BARIS_B);
            }
            return this;
        }

        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D d = (Graphics2D) g.create();
            d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                    RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
            Rectangle[] tombol = tombolDi(getWidth(), getHeight(), label.length);
            Font huruf = getFont().deriveFont(Font.BOLD, 11f);
            d.setFont(huruf);
            FontMetrics fm = d.getFontMetrics();
            for (int i = 0; i < tombol.length; i++) {
                boolean siap = haluanBaris == barisDigambar && haluanIndeks == i;
                d.setColor(siap ? AKSI_SIAP : AKSI_BG);
                d.fillRoundRect(tombol[i].x, tombol[i].y, tombol[i].width, tombol[i].height, 12, 12);
                d.setColor(Color.WHITE);
                int tx = tombol[i].x + (tombol[i].width - fm.stringWidth(label[i])) / 2;
                int ty = tombol[i].y + (tombol[i].height + fm.getAscent() - fm.getDescent()) / 2;
                d.drawString(label[i], tx, ty);
            }
            d.dispose();
        }
    }
}
