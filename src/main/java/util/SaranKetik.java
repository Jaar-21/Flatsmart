package util;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public final class SaranKetik {

    public interface Pencari {
        List<String> cari(String keyword) throws Exception;
    }

    public interface Pilihan {
        void pilih(int indeks);
    }

    private final JTextField kolom;
    private final Pencari pencari;
    private final Pilihan pilihan;
    private final int minimum;
    private final DefaultListModel<String> isiModel = new DefaultListModel<>();
    private final JList<String> daftar;
    private final JPopupMenu popup;
    private final Timer jeda;

    private SaranKetik(JTextField kolom, int lebar, int tinggi, int minimum,
                       Pencari pencari, Pilihan pilihan) {
        this.kolom = kolom;
        this.pencari = pencari;
        this.pilihan = pilihan;
        this.minimum = minimum;

        daftar = new JList<>(isiModel);
        daftar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        daftar.setFixedCellHeight(34);
        daftar.setFont(new java.awt.Font("Segoe UI", 0, 13));
        daftar.setBackground(Color.WHITE);
        daftar.setForeground(new Color(16, 40, 79));
        daftar.setSelectionBackground(new Color(232, 240, 254));
        daftar.setSelectionForeground(new Color(16, 40, 79));
        daftar.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    e.consume();
                    if (daftar.getSelectedIndex() >= 0) {
                        pilih(daftar.getSelectedIndex());
                    }
                } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    e.consume();
                    sembunyikan();
                }
            }
        });
        daftar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                if (daftar.getSelectedIndex() >= 0) {
                    pilih(daftar.getSelectedIndex());
                }
            }
        });

        JScrollPane gulung = new JScrollPane(daftar);
        gulung.setBorder(BorderFactory.createLineBorder(new Color(226, 231, 240)));
        gulung.getViewport().setBackground(Color.WHITE);
        popup = new JPopupMenu();
        popup.setBorder(null);
        popup.add(gulung);
        popup.setPopupSize(lebar, tinggi);

        jeda = new Timer(180, e -> muat());
        jeda.setRepeats(false);

        kolom.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { jedaMuat(); }
            @Override public void removeUpdate(DocumentEvent e) { jedaMuat(); }
            @Override public void changedUpdate(DocumentEvent e) { jedaMuat(); }
        });

        kolom.addKeyListener(new KeyAdapter() {
            @Override public void keyPressed(KeyEvent e) {
                if (!popup.isVisible()) {
                    return;
                }
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_DOWN -> { geser(1); e.consume(); }
                    case KeyEvent.VK_UP -> { geser(-1); e.consume(); }
                    case KeyEvent.VK_ENTER -> {
                        if (daftar.getSelectedIndex() >= 0) {
                            pilihTerpilih();
                            e.consume();
                        }
                    }
                    case KeyEvent.VK_ESCAPE -> { sembunyikan(); e.consume(); }
                    default -> { }
                }
            }
        });
    }

    public static SaranKetik pasang(JTextField kolom, int lebar, int tinggi, int minimum,
                                    Pencari pencari, Pilihan pilihan) {
        return new SaranKetik(kolom, lebar, tinggi, minimum, pencari, pilihan);
    }

    private void pilih(int indeks) {
        sembunyikan();
        pilihan.pilih(indeks);
    }

    private void jedaMuat() {
        if (kolom.getText().trim().length() < minimum) {
            sembunyikan();
            return;
        }
        jeda.restart();
    }

    private void muat() {
        String keyword = kolom.getText().trim();
        if (keyword.length() < minimum || !kolom.hasFocus()) {
            sembunyikan();
            return;
        }
        try {
            List<String> hasil = pencari.cari(keyword);
            if (hasil == null || hasil.isEmpty()) {
                sembunyikan();
                return;
            }
            isiModel.clear();
            for (String baris : hasil) {
                isiModel.addElement(baris);
            }
            daftar.setSelectedIndex(0);
            popup.show(kolom, 0, kolom.getHeight());
            kolom.requestFocusInWindow();
        } catch (Exception ex) {
            sembunyikan();
        }
    }

    private void geser(int delta) {
        int n = isiModel.getSize();
        if (n == 0) {
            return;
        }
        int i = daftar.getSelectedIndex();
        int baru = Math.max(0, Math.min(n - 1, (i < 0 ? 0 : i + delta)));
        daftar.setSelectedIndex(baru);
        daftar.ensureIndexIsVisible(baru);
    }

    public void sembunyikan() {
        jeda.stop();
        popup.setVisible(false);
    }

    public boolean terbuka() {
        return popup.isVisible();
    }

    public int indeksTerpilih() {
        return daftar.getSelectedIndex();
    }

    public void pilihTerpilih() {
        int i = daftar.getSelectedIndex();
        if (i >= 0) {
            pilih(i);
        }
    }
}
