package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Produk;
import model.Stok;
import controller.ProdukController;
import util.Format;
import util.GayaTabel;
import util.Pesan;
import util.SaranKetik;

public class KelolaProduk extends javax.swing.JPanel {

    private static final Logger LOG = Logger.getLogger("view");

    private final ProdukController produkController = new ProdukController();
    private List<Produk> daftarProduk = List.of();
    private DefaultTableModel tabelModel;
    private int kolomAksi = -1;
    private SaranKetik saranCari;
    private final List<Produk> saranAktif = new ArrayList<>();

    public KelolaProduk() {
        initComponents();

        tabelModel = new DefaultTableModel(
            new String[]{"Barcode", "Nama produk", "Harga beli", "Harga jual", "Stok total", "Aksi"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblProduk.setModel(tabelModel);
        GayaTabel.rapikan(tblProduk, new int[]{2, 3, 4}, new int[]{5});
        kolomAksi = GayaTabel.pasangAksi(tblProduk, "Aksi");
        GayaTabel.aturLebar(tblProduk, new int[]{
            140, 250, 155, 155, 137, GayaTabel.lebarMinimumAksi(2)});

        pasangSaranCari();

        tblProduk.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                if (kolomAksi < 0 || e.getClickCount() != 1) {
                    return;
                }
                int baris = tblProduk.rowAtPoint(e.getPoint());
                if (baris < 0 || baris >= daftarProduk.size()) {
                    return;
                }
                int aksi = GayaTabel.aksiDi(tblProduk, kolomAksi, e.getPoint());
                if (aksi == GayaTabel.UBAH) {
                    bukaFormProduk(daftarProduk.get(baris));
                } else if (aksi == GayaTabel.HAPUS) {
                    hapusDenganPopup(baris);
                }
            }
        });
        tblProduk.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) tampilBatch();
        });

        muatData();
    }

    private void pasangSaranCari() {
        saranCari = SaranKetik.pasang(txtCari, 628, 210, 1, keyword -> {
            List<Produk> hasil = produkController.cari(keyword, (String) cmbFilter.getSelectedItem());
            saranAktif.clear();
            saranAktif.addAll(hasil);
            List<String> baris = new ArrayList<>();
            for (Produk p : hasil) {
                baris.add(Format.judul(p.getNamaProduk()) + " (" + Format.rupiah(p.getHargaJual())
                        + ", stok " + p.getStokTotal() + " unit)");
            }
            return baris;
        }, indeks -> {
            if (indeks >= 0 && indeks < saranAktif.size()) {
                txtCari.setText(saranAktif.get(indeks).getNamaProduk());
            }
            muatData();
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        pnlStatProduk = new javax.swing.JPanel();
        lblIkonProduk = new javax.swing.JLabel();
        lblJudulProduk = new javax.swing.JLabel();
        lblNilaiProduk = new javax.swing.JLabel();
        pnlStatMenipis = new javax.swing.JPanel();
        lblJudulMenipis = new javax.swing.JLabel();
        lblNilaiMenipis = new javax.swing.JLabel();
        lblIkonMenipis = new javax.swing.JLabel();
        pnlStatExp = new javax.swing.JPanel();
        lblIkonExp = new javax.swing.JLabel();
        lblJudulExp = new javax.swing.JLabel();
        lblNilaiExp = new javax.swing.JLabel();
        pnlDaftar = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        lblFilter = new javax.swing.JLabel();
        cmbFilter = new javax.swing.JComboBox<>();
        btnCari = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        scrollProduk = new javax.swing.JScrollPane();
        tblProduk = new javax.swing.JTable();
        pnlBatch = new javax.swing.JPanel();
        lblInfoJudulBarcode = new javax.swing.JLabel();
        lblInfoBarcode = new javax.swing.JLabel();
        lblInfoJudulNama = new javax.swing.JLabel();
        lblInfoNama = new javax.swing.JLabel();
        lblInfoJudulBeli = new javax.swing.JLabel();
        lblInfoBeli = new javax.swing.JLabel();
        lblInfoJudulJual = new javax.swing.JLabel();
        lblInfoJual = new javax.swing.JLabel();
        lblInfoJudulStok = new javax.swing.JLabel();
        lblInfoStok = new javax.swing.JLabel();
        lblInfoJudulBatch = new javax.swing.JLabel();
        lblInfoBatch = new javax.swing.JLabel();
        lblInfoJudulRincian = new javax.swing.JLabel();
        lblBatch = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 245, 250));
        setPreferredSize(new java.awt.Dimension(1095, 805));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(15, 30, 60));
        lblJudul.setText("Kelola Produk");
        add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 18, 500, 40));

        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Kelola identitas produk, harga, dan persediaan per batch.");
        add(lblSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 60, 600, 20));

        btnTambah.setBackground(new java.awt.Color(16, 40, 79));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("+ Tambah Produk");
        btnTambah.setBorderPainted(false);
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setFocusPainted(false);
        btnTambah.setOpaque(true);
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(877, 30, 175, 38));

        pnlStatProduk.setBackground(new java.awt.Color(255, 255, 255));
        pnlStatProduk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlStatProduk.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIkonProduk.setBackground(new java.awt.Color(232, 240, 254));
        lblIkonProduk.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIkonProduk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/shopping-bag.png"))); // NOI18N
        lblIkonProduk.setOpaque(true);
        pnlStatProduk.add(lblIkonProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 36, 36));

        lblJudulProduk.setBackground(new java.awt.Color(232, 240, 254));
        lblJudulProduk.setForeground(new java.awt.Color(100, 116, 139));
        lblJudulProduk.setText("Produk terdaftar");
        pnlStatProduk.add(lblJudulProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 14, 260, 18));

        lblNilaiProduk.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNilaiProduk.setForeground(new java.awt.Color(15, 30, 60));
        lblNilaiProduk.setText("0 Produk");
        pnlStatProduk.add(lblNilaiProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 34, 260, 32));

        add(pnlStatProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 87, 338, 99));

        pnlStatMenipis.setBackground(new java.awt.Color(255, 255, 255));
        pnlStatMenipis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlStatMenipis.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulMenipis.setBackground(new java.awt.Color(232, 240, 254));
        lblJudulMenipis.setForeground(new java.awt.Color(100, 116, 139));
        lblJudulMenipis.setText("Stok menipis");
        pnlStatMenipis.add(lblJudulMenipis, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 14, 160, 18));

        lblNilaiMenipis.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNilaiMenipis.setForeground(new java.awt.Color(15, 30, 60));
        lblNilaiMenipis.setText("0 Produk");
        pnlStatMenipis.add(lblNilaiMenipis, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 34, 260, 32));

        lblIkonMenipis.setBackground(new java.awt.Color(232, 240, 254));
        lblIkonMenipis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIkonMenipis.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/inventory-alt.png"))); // NOI18N
        lblIkonMenipis.setOpaque(true);
        pnlStatMenipis.add(lblIkonMenipis, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 36, 36));

        add(pnlStatMenipis, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 87, 338, 99));

        pnlStatExp.setBackground(new java.awt.Color(255, 255, 255));
        pnlStatExp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlStatExp.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIkonExp.setBackground(new java.awt.Color(232, 240, 254));
        lblIkonExp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIkonExp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/expired.png"))); // NOI18N
        lblIkonExp.setOpaque(true);
        pnlStatExp.add(lblIkonExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 36, 36));

        lblJudulExp.setBackground(new java.awt.Color(232, 240, 254));
        lblJudulExp.setForeground(new java.awt.Color(100, 116, 139));
        lblJudulExp.setText("Mendekati kedaluwarsa");
        pnlStatExp.add(lblJudulExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 14, 260, 18));

        lblNilaiExp.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblNilaiExp.setForeground(new java.awt.Color(15, 30, 60));
        lblNilaiExp.setText("0 Batch");
        pnlStatExp.add(lblNilaiExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 34, 260, 32));

        add(pnlStatExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 87, 337, 99));

        pnlDaftar.setBackground(new java.awt.Color(255, 255, 255));
        pnlDaftar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlDaftar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCari.setForeground(new java.awt.Color(15, 30, 60));
        lblCari.setText("Cari produk");
        pnlDaftar.add(lblCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 14, 300, 18));

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(15, 30, 60));
        txtCari.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtCari.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlDaftar.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 38, 628, 38));

        lblFilter.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblFilter.setForeground(new java.awt.Color(15, 30, 60));
        lblFilter.setText("Filter stok");
        pnlDaftar.add(lblFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 14, 200, 18));

        cmbFilter.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbFilter.setForeground(new java.awt.Color(15, 30, 60));
        cmbFilter.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Normal", "Menipis (≤10)", "Habis" }));
        cmbFilter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        cmbFilter.addActionListener(this::cmbFilterActionPerformed);
        pnlDaftar.add(cmbFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 38, 208, 38));

        btnCari.setBackground(new java.awt.Color(16, 40, 79));
        btnCari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCari.setForeground(new java.awt.Color(255, 255, 255));
        btnCari.setText("Cari");
        btnCari.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.setFocusPainted(false);
        btnCari.setOpaque(true);
        btnCari.addActionListener(this::btnCariActionPerformed);
        pnlDaftar.add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(873, 38, 80, 38));

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnReset.setForeground(new java.awt.Color(15, 30, 60));
        btnReset.setText("Reset");
        btnReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.setFocusPainted(false);
        btnReset.setOpaque(true);
        btnReset.addActionListener(this::btnResetActionPerformed);
        pnlDaftar.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(958, 38, 70, 38));

        scrollProduk.setBackground(new java.awt.Color(255, 255, 255));
        scrollProduk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));

        tblProduk.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblProduk.setForeground(new java.awt.Color(15, 30, 60));
        tblProduk.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Barcode", "Nama produk", "Harga beli", "Harga jual", "Stok total", "Aksi"
            }
        ));
        tblProduk.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblProduk.setFillsViewportHeight(true);
        tblProduk.setGridColor(new java.awt.Color(226, 231, 240));
        tblProduk.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblProduk.setRowHeight(46);
        tblProduk.setSelectionBackground(new java.awt.Color(232, 240, 254));
        tblProduk.setSelectionForeground(new java.awt.Color(15, 30, 60));
        tblProduk.setShowHorizontalLines(true);
        scrollProduk.setViewportView(tblProduk);

        pnlDaftar.add(scrollProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 87, 1012, 380));

        pnlBatch.setBackground(new java.awt.Color(242, 245, 250));
        pnlBatch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInfoJudulBarcode.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulBarcode.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulBarcode.setText("Barcode");
        pnlBatch.add(lblInfoJudulBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 120, 16));

        lblInfoBarcode.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoBarcode.setForeground(new java.awt.Color(16, 40, 79));
        pnlBatch.add(lblInfoBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 26, 120, 20));

        lblInfoJudulNama.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulNama.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulNama.setText("Nama produk");
        pnlBatch.add(lblInfoJudulNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 8, 250, 16));

        lblInfoNama.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoNama.setForeground(new java.awt.Color(16, 40, 79));
        pnlBatch.add(lblInfoNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(148, 26, 250, 20));

        lblInfoJudulBeli.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulBeli.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulBeli.setText("Harga beli");
        pnlBatch.add(lblInfoJudulBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 8, 110, 16));

        lblInfoBeli.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoBeli.setForeground(new java.awt.Color(16, 40, 79));
        pnlBatch.add(lblInfoBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 26, 110, 20));

        lblInfoJudulJual.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulJual.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulJual.setText("Harga jual");
        pnlBatch.add(lblInfoJudulJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 8, 110, 16));

        lblInfoJual.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoJual.setForeground(new java.awt.Color(16, 40, 79));
        pnlBatch.add(lblInfoJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(532, 26, 110, 20));

        lblInfoJudulStok.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulStok.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulStok.setText("Stok total");
        pnlBatch.add(lblInfoJudulStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 8, 110, 16));

        lblInfoStok.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoStok.setForeground(new java.awt.Color(16, 40, 79));
        pnlBatch.add(lblInfoStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(654, 26, 110, 20));

        lblInfoJudulBatch.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulBatch.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulBatch.setText("Batch stok");
        pnlBatch.add(lblInfoJudulBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 8, 220, 16));

        lblInfoBatch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoBatch.setForeground(new java.awt.Color(16, 40, 79));
        pnlBatch.add(lblInfoBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(776, 26, 220, 20));

        lblInfoJudulRincian.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulRincian.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulRincian.setText("Rincian batch (exp terdekat lebih dulu)");
        pnlBatch.add(lblInfoJudulRincian, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 52, 320, 14));

        lblBatch.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lblBatch.setForeground(new java.awt.Color(15, 30, 60));
        pnlBatch.add(lblBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(344, 50, 652, 20));

        pnlDaftar.add(pnlBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 477, 1012, 86));

        add(pnlDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 203, 1043, 579));
        pnlDaftar.getAccessibleContext().setAccessibleDescription("");
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        bukaFormProduk(null);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        muatData();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtCari.setText("");
        cmbFilter.setSelectedIndex(0);
        muatData();
    }//GEN-LAST:event_btnResetActionPerformed

    private void cmbFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbFilterActionPerformed
        muatData();
    }//GEN-LAST:event_cmbFilterActionPerformed


    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbFilter;
    private javax.swing.JLabel lblBatch;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblFilter;
    private javax.swing.JLabel lblIkonExp;
    private javax.swing.JLabel lblIkonMenipis;
    private javax.swing.JLabel lblIkonProduk;
    private javax.swing.JLabel lblInfoBarcode;
    private javax.swing.JLabel lblInfoBatch;
    private javax.swing.JLabel lblInfoBeli;
    private javax.swing.JLabel lblInfoJudulBarcode;
    private javax.swing.JLabel lblInfoJudulBatch;
    private javax.swing.JLabel lblInfoJudulBeli;
    private javax.swing.JLabel lblInfoJudulJual;
    private javax.swing.JLabel lblInfoJudulNama;
    private javax.swing.JLabel lblInfoJudulRincian;
    private javax.swing.JLabel lblInfoJudulStok;
    private javax.swing.JLabel lblInfoJual;
    private javax.swing.JLabel lblInfoNama;
    private javax.swing.JLabel lblInfoStok;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblJudulExp;
    private javax.swing.JLabel lblJudulMenipis;
    private javax.swing.JLabel lblJudulProduk;
    private javax.swing.JLabel lblNilaiExp;
    private javax.swing.JLabel lblNilaiMenipis;
    private javax.swing.JLabel lblNilaiProduk;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBatch;
    private javax.swing.JPanel pnlDaftar;
    private javax.swing.JPanel pnlStatExp;
    private javax.swing.JPanel pnlStatMenipis;
    private javax.swing.JPanel pnlStatProduk;
    private javax.swing.JScrollPane scrollProduk;
    private javax.swing.JTable tblProduk;
    private javax.swing.JTextField txtCari;

    private void muatData() {
        try {
            daftarProduk = produkController.cari(txtCari.getText(),
                    (String) cmbFilter.getSelectedItem());
            tabelModel.setRowCount(0);
            for (Produk p : daftarProduk) {
                tabelModel.addRow(new Object[]{
                    p.getBarcode(), Format.judul(p.getNamaProduk()),
                    Format.rupiah(p.getHargaBeli()), Format.rupiah(p.getHargaJual()),
                    p.getStokTotal() + " unit", ""
                });
            }
            muatStatistik();
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    private void muatStatistik() {
        try {
            lblNilaiProduk.setText(produkController.hitungProduk() + " produk");
            lblNilaiMenipis.setText(produkController.hitungStokMenipis() + " produk");
            lblNilaiExp.setText(produkController.hitungDekatKedaluwarsa() + " batch");
        } catch (SQLException ex) {
            LOG.log(Level.WARNING, "Gagal memuat statistik produk", ex);
        }
    }

    private void tampilBatch() {
        int baris = tblProduk.getSelectedRow();
        if (baris < 0 || baris >= daftarProduk.size()) {
            kosongkanInfo();
            return;
        }
        Produk p = daftarProduk.get(baris);
        lblInfoBarcode.setText(p.getBarcode());
        lblInfoNama.setText(Format.judul(p.getNamaProduk()));
        lblInfoBeli.setText(Format.rupiah(p.getHargaBeli()));
        lblInfoJual.setText(Format.rupiah(p.getHargaJual()));
        lblInfoStok.setText(p.getStokTotal() + " unit");
        try {
            List<Stok> batch = produkController.cariBatch(p.getBarcode());
            lblInfoBatch.setText(batch.size() + " batch");
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < batch.size(); i++) {
                Stok s = batch.get(i);
                if (i > 0) {
                    sb.append("; ");
                }
                sb.append(s.getIdStok()).append(": ").append(s.getJumlah())
                  .append(" unit, exp ").append(Format.tanggal(s.getTglKedaluwarsa()));
            }
            lblBatch.setText(sb.length() == 0 ? "Belum ada batch stok." : sb.toString());
        } catch (SQLException ex) {
            LOG.log(Level.WARNING, "Gagal memuat batch produk", ex);
            lblInfoBatch.setText("-");
            lblBatch.setText("Gagal memuat data batch.");
        }
    }

    private void kosongkanInfo() {
        lblInfoBarcode.setText("");
        lblInfoNama.setText("");
        lblInfoBeli.setText("");
        lblInfoJual.setText("");
        lblInfoStok.setText("");
        lblInfoBatch.setText("");
        lblBatch.setText("");
    }

    private void bukaFormProduk(Produk produk) {
        FormProdukDialog dialog = new FormProdukDialog(
                (java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        if (produk != null) {
            dialog.setEditMode(produk.getBarcode(), produk.getNamaProduk(),
                    produk.getHargaBeli(), produk.getHargaJual());
        }
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        muatData();
    }

    private void hapusDenganPopup(int baris) {
        Produk p = daftarProduk.get(baris);
        boolean setuju = Pesan.konfirmasi(this,
                "Hapus produk " + p.getNamaProduk() + " (" + p.getBarcode() + ")?\n\n"
              + "Seluruh batch stok produk ini ikut terhapus."
              + (p.getStokTotal() > 0 ? "\nStok saat ini: " + p.getStokTotal() + " unit." : "")
              + "\n\nProduk yang pernah ditransaksikan tidak dapat dihapus.");
        if (!setuju) {
            return;
        }
        try {
            produkController.hapus(p.getBarcode());
            Pesan.sukses(this, "Produk " + p.getNamaProduk() + " berhasil dihapus.");
            muatData();
        } catch (IllegalArgumentException | SQLException ex) {
            Pesan.peringatan(this, ex.getMessage());
        }
    }
}
