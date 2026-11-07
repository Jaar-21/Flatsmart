package view;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.Promosi;
import model.Stok;
import controller.PromosiController;
import util.Format;
import util.GayaTabel;
import util.Pesan;
import util.PilihTanggal;

public class KelolaPromosi extends javax.swing.JPanel {

    private static final Logger LOG = Logger.getLogger("view");

    private final PromosiController promosiController = new PromosiController();
    private List<Promosi> daftarPromosi = List.of();
    private DefaultTableModel tabelModel;
    private int kolomAksi = -1;

    public KelolaPromosi() {
        initComponents();

        tabelModel = new DefaultTableModel(
            new String[]{"Kode", "Nama", "Produk", "Batch", "Diskon", "Mulai", "Selesai", "Status", "Aksi"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblPromosi.setModel(tabelModel);
        GayaTabel.rapikan(tblPromosi, new int[]{4}, new int[]{5, 6, 7});
        kolomAksi = GayaTabel.pasangAksi(tblPromosi, "Aksi", "Hapus");
        GayaTabel.aturLebar(tblPromosi, new int[]{
            80, 210, 165, 90, 70, 95, 95, 104, GayaTabel.lebarMinimumAksi(1)});
        PilihTanggal.pasang(txtTanggal, this::muatData);

        tblPromosi.addMouseListener(new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) {
                int baris = tblPromosi.rowAtPoint(e.getPoint());
                if (baris < 0 || baris >= daftarPromosi.size()) {
                    return;
                }
                if (e.getClickCount() == 2) {
                    bukaFormPromosi(daftarPromosi.get(baris));
                    return;
                }
                if (kolomAksi < 0 || e.getClickCount() != 1) {
                    return;
                }
                if (GayaTabel.aksiDi(tblPromosi, kolomAksi, e.getPoint()) == 0) {
                    hapusDenganPopup(baris);
                }
            }
        });
        tblPromosi.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) tampilInfo();
        });
        tblPromosi.setToolTipText("Klik Hapus untuk menghapus; klik dua kali pada baris untuk mengubah promosi.");

        muatData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        btnTambah = new javax.swing.JButton();
        pnlStatAktif = new javax.swing.JPanel();
        lblIkonTotal = new javax.swing.JLabel();
        lblJudulTotal = new javax.swing.JLabel();
        lblNilaiTotal = new javax.swing.JLabel();
        pnlStatTarget = new javax.swing.JPanel();
        lblIkonTarget = new javax.swing.JLabel();
        lblJudulTarget = new javax.swing.JLabel();
        lblNilaiTarget = new javax.swing.JLabel();
        pnlStatBatch = new javax.swing.JPanel();
        lblIkonBatch = new javax.swing.JLabel();
        lblJudulBatch = new javax.swing.JLabel();
        lblNilaiBatch = new javax.swing.JLabel();
        pnlDaftar = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        lblFilter = new javax.swing.JLabel();
        txtTanggal = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        scrollPromosi = new javax.swing.JScrollPane();
        tblPromosi = new javax.swing.JTable();
        pnlInfo = new javax.swing.JPanel();
        lblInfoJudulKode = new javax.swing.JLabel();
        lblInfoKode = new javax.swing.JLabel();
        lblInfoJudulProduk = new javax.swing.JLabel();
        lblInfoProduk = new javax.swing.JLabel();
        lblInfoJudulBatch = new javax.swing.JLabel();
        lblInfoBatch = new javax.swing.JLabel();
        lblInfoJudulDiskon = new javax.swing.JLabel();
        lblInfoDiskon = new javax.swing.JLabel();
        lblInfoJudulPeriode = new javax.swing.JLabel();
        lblInfoPeriode = new javax.swing.JLabel();
        lblInfoJudulStatus = new javax.swing.JLabel();
        lblInfoStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 245, 250));
        setForeground(new java.awt.Color(242, 245, 250));
        setPreferredSize(new java.awt.Dimension(1095, 805));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(16, 40, 79));
        lblJudul.setText("Kelola Promosi");
        add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 18, 500, 40));

        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Atur diskon berbasis batch stok dan periode berlakunya.");
        add(lblSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 60, 600, 20));

        btnTambah.setBackground(new java.awt.Color(16, 40, 79));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("+ Tambah Promosi");
        btnTambah.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(892, 24, 178, 38));

        pnlStatAktif.setBackground(new java.awt.Color(255, 255, 255));
        pnlStatAktif.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        pnlStatAktif.setForeground(new java.awt.Color(255, 255, 255));
        pnlStatAktif.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIkonTotal.setBackground(new java.awt.Color(16, 40, 79));
        lblIkonTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIkonTotal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/badge-percent.png"))); // NOI18N
        lblIkonTotal.setOpaque(true);
        pnlStatAktif.add(lblIkonTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 36, 36));

        lblJudulTotal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblJudulTotal.setForeground(new java.awt.Color(100, 116, 139));
        lblJudulTotal.setText("Promo aktif");
        pnlStatAktif.add(lblJudulTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 14, 260, 18));

        lblNilaiTotal.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblNilaiTotal.setForeground(new java.awt.Color(16, 40, 79));
        lblNilaiTotal.setText("0 Promosi");
        pnlStatAktif.add(lblNilaiTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 34, 260, 32));

        add(pnlStatAktif, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 87, 338, 99));

        pnlStatTarget.setBackground(new java.awt.Color(255, 255, 255));
        pnlStatTarget.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        pnlStatTarget.setForeground(new java.awt.Color(255, 255, 255));
        pnlStatTarget.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIkonTarget.setBackground(new java.awt.Color(16, 40, 79));
        lblIkonTarget.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIkonTarget.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calendar.png"))); // NOI18N
        lblIkonTarget.setOpaque(true);
        pnlStatTarget.add(lblIkonTarget, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 36, 36));

        lblJudulTarget.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblJudulTarget.setForeground(new java.awt.Color(100, 116, 139));
        lblJudulTarget.setText("Batch bertarget promosi");
        pnlStatTarget.add(lblJudulTarget, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 14, 260, 18));

        lblNilaiTarget.setBackground(new java.awt.Color(255, 255, 255));
        lblNilaiTarget.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblNilaiTarget.setForeground(new java.awt.Color(16, 40, 79));
        lblNilaiTarget.setText("0 Batch");
        pnlStatTarget.add(lblNilaiTarget, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 34, 260, 32));

        add(pnlStatTarget, new org.netbeans.lib.awtextra.AbsoluteConstraints(379, 87, 338, 99));

        pnlStatBatch.setBackground(new java.awt.Color(255, 255, 255));
        pnlStatBatch.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        pnlStatBatch.setForeground(new java.awt.Color(255, 255, 255));
        pnlStatBatch.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIkonBatch.setBackground(new java.awt.Color(16, 40, 79));
        lblIkonBatch.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblIkonBatch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons/calendar-clock.png"))); // NOI18N
        lblIkonBatch.setOpaque(true);
        pnlStatBatch.add(lblIkonBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 16, 36, 36));

        lblJudulBatch.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblJudulBatch.setForeground(new java.awt.Color(100, 116, 139));
        lblJudulBatch.setText("Batch dekat kedaluwarsa");
        pnlStatBatch.add(lblJudulBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 14, 260, 18));

        lblNilaiBatch.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        lblNilaiBatch.setForeground(new java.awt.Color(16, 40, 79));
        lblNilaiBatch.setText("0 Batch");
        pnlStatBatch.add(lblNilaiBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(65, 34, 260, 32));

        add(pnlStatBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(732, 87, 337, 99));

        pnlDaftar.setBackground(new java.awt.Color(255, 255, 255));
        pnlDaftar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        pnlDaftar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCari.setForeground(new java.awt.Color(16, 40, 79));
        lblCari.setText("Cari promosi");
        pnlDaftar.add(lblCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 14, 300, 18));

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtCari.setForeground(new java.awt.Color(16, 40, 79));
        txtCari.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtCari.setCaretColor(new java.awt.Color(16, 40, 79));
        txtCari.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlDaftar.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 38, 628, 38));

        lblFilter.setBackground(new java.awt.Color(16, 40, 79));
        lblFilter.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblFilter.setText("Berlaku pada tanggal");
        pnlDaftar.add(lblFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 14, 200, 18));

        txtTanggal.setEditable(false);
        txtTanggal.setBackground(new java.awt.Color(255, 255, 255));
        txtTanggal.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        txtTanggal.setForeground(new java.awt.Color(16, 40, 79));
        txtTanggal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtTanggal.setCaretColor(new java.awt.Color(16, 40, 79));
        txtTanggal.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlDaftar.add(txtTanggal, new org.netbeans.lib.awtextra.AbsoluteConstraints(657, 38, 208, 38));

        btnCari.setBackground(new java.awt.Color(16, 40, 79));
        btnCari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCari.setForeground(new java.awt.Color(255, 255, 255));
        btnCari.setText("Cari");
        btnCari.setBorderPainted(false);
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.setFocusPainted(false);
        btnCari.setOpaque(true);
        btnCari.addActionListener(this::btnCariActionPerformed);
        pnlDaftar.add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 38, 75, 38));

        btnReset.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnReset.setForeground(new java.awt.Color(16, 40, 79));
        btnReset.setText("Reset");
        btnReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(16, 40, 79)));
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.setFocusPainted(false);
        btnReset.setOpaque(true);
        btnReset.addActionListener(this::btnResetActionPerformed);
        pnlDaftar.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(966, 38, 62, 38));

        scrollPromosi.setBackground(new java.awt.Color(255, 255, 255));
        scrollPromosi.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));

        tblPromosi.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblPromosi.setForeground(new java.awt.Color(16, 40, 79));
        tblPromosi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPromosi.setFillsViewportHeight(true);
        tblPromosi.setGridColor(new java.awt.Color(214, 222, 236));
        tblPromosi.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblPromosi.setRowHeight(52);
        tblPromosi.setSelectionBackground(new java.awt.Color(220, 229, 245));
        tblPromosi.setSelectionForeground(new java.awt.Color(16, 40, 79));
        tblPromosi.setShowHorizontalLines(true);
        scrollPromosi.setViewportView(tblPromosi);

        pnlDaftar.add(scrollPromosi, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 87, 1012, 411));

        pnlInfo.setBackground(new java.awt.Color(242, 245, 250));
        pnlInfo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(250, 226, 180)));
        pnlInfo.setForeground(new java.awt.Color(16, 40, 79));
        pnlInfo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblInfoJudulKode.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulKode.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulKode.setText("Kode promosi");
        pnlInfo.add(lblInfoJudulKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 8, 120, 16));

        lblInfoKode.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoKode.setForeground(new java.awt.Color(16, 40, 79));
        pnlInfo.add(lblInfoKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 26, 120, 22));

        lblInfoJudulProduk.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulProduk.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulProduk.setText("Produk");
        pnlInfo.add(lblInfoJudulProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 8, 230, 16));

        lblInfoProduk.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoProduk.setForeground(new java.awt.Color(16, 40, 79));
        pnlInfo.add(lblInfoProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(152, 26, 230, 22));

        lblInfoJudulBatch.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulBatch.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulBatch.setText("Batch stok");
        pnlInfo.add(lblInfoJudulBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 8, 120, 16));

        lblInfoBatch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoBatch.setForeground(new java.awt.Color(16, 40, 79));
        pnlInfo.add(lblInfoBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 26, 120, 22));

        lblInfoJudulDiskon.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulDiskon.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulDiskon.setText("Diskon");
        pnlInfo.add(lblInfoJudulDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 8, 80, 16));

        lblInfoDiskon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoDiskon.setForeground(new java.awt.Color(16, 40, 79));
        pnlInfo.add(lblInfoDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(536, 26, 80, 22));

        lblInfoJudulPeriode.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulPeriode.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulPeriode.setText("Periode berlaku");
        pnlInfo.add(lblInfoJudulPeriode, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 8, 230, 16));

        lblInfoPeriode.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoPeriode.setForeground(new java.awt.Color(16, 40, 79));
        pnlInfo.add(lblInfoPeriode, new org.netbeans.lib.awtextra.AbsoluteConstraints(632, 26, 230, 22));

        lblInfoJudulStatus.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        lblInfoJudulStatus.setForeground(new java.awt.Color(100, 116, 139));
        lblInfoJudulStatus.setText("Status");
        pnlInfo.add(lblInfoJudulStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 8, 120, 16));

        lblInfoStatus.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblInfoStatus.setForeground(new java.awt.Color(16, 40, 79));
        pnlInfo.add(lblInfoStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(878, 26, 120, 22));

        pnlDaftar.add(pnlInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(16, 508, 1012, 56));

        add(pnlDaftar, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 203, 1043, 579));
    }// </editor-fold>//GEN-END:initComponents

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        bukaFormPromosi(null);
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        muatData();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtCari.setText("");
        txtTanggal.setText("");
        muatData();
    }//GEN-LAST:event_btnResetActionPerformed

    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnTambah;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblFilter;
    private javax.swing.JLabel lblIkonBatch;
    private javax.swing.JLabel lblIkonTarget;
    private javax.swing.JLabel lblIkonTotal;
    private javax.swing.JLabel lblInfoBatch;
    private javax.swing.JLabel lblInfoDiskon;
    private javax.swing.JLabel lblInfoJudulBatch;
    private javax.swing.JLabel lblInfoJudulDiskon;
    private javax.swing.JLabel lblInfoJudulKode;
    private javax.swing.JLabel lblInfoJudulPeriode;
    private javax.swing.JLabel lblInfoJudulProduk;
    private javax.swing.JLabel lblInfoJudulStatus;
    private javax.swing.JLabel lblInfoKode;
    private javax.swing.JLabel lblInfoPeriode;
    private javax.swing.JLabel lblInfoProduk;
    private javax.swing.JLabel lblInfoStatus;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblJudulBatch;
    private javax.swing.JLabel lblJudulTarget;
    private javax.swing.JLabel lblJudulTotal;
    private javax.swing.JLabel lblNilaiBatch;
    private javax.swing.JLabel lblNilaiTarget;
    private javax.swing.JLabel lblNilaiTotal;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlDaftar;
    private javax.swing.JPanel pnlInfo;
    private javax.swing.JPanel pnlStatAktif;
    private javax.swing.JPanel pnlStatBatch;
    private javax.swing.JPanel pnlStatTarget;
    private javax.swing.JScrollPane scrollPromosi;
    private javax.swing.JTable tblPromosi;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtTanggal;

    private void muatData() {
        LocalDate aktifPada;
        try {
            String teks = txtTanggal.getText().trim();
            aktifPada = teks.isEmpty() ? null : Format.bacaTanggal(teks);
        } catch (java.time.format.DateTimeParseException ex) {
            Pesan.peringatan(this, "Tanggal filter harus berformat dd/MM/yyyy.");
            return;
        }
        try {
            daftarPromosi = promosiController.cari(txtCari.getText(), aktifPada);
            tabelModel.setRowCount(0);
            for (Promosi p : daftarPromosi) {
                tabelModel.addRow(new Object[]{
                    p.getKodePromosi(), p.getNamaPromosi(),
                    p.getNamaProduk() != null ? Format.judul(p.getNamaProduk()) : "-",
                    p.getIdStok(), p.getDiskonPersen() + "%",
                    Format.tanggal(p.getTglMulai()), Format.tanggal(p.getTglSelesai()),
                    p.getStatus(), ""
                });
            }
            muatStatistik();
            tampilInfo();
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    private void muatStatistik() {
        try {
            lblNilaiTotal.setText(promosiController.hitungTotal() + " promosi");
            lblNilaiTarget.setText(promosiController.hitungBatchTarget() + " batch");
            lblNilaiBatch.setText(promosiController.hitungBatchDekatKedaluwarsa() + " batch");
        } catch (SQLException ex) {
            LOG.log(Level.WARNING, "Gagal memuat statistik promosi", ex);
        }
    }

    private void tampilInfo() {
        int baris = tblPromosi.getSelectedRow();
        if (baris < 0 || baris >= daftarPromosi.size()) {
            lblInfoKode.setText("");
            lblInfoProduk.setText("");
            lblInfoBatch.setText("");
            lblInfoDiskon.setText("");
            lblInfoPeriode.setText("");
            lblInfoStatus.setText("");
            return;
        }
        Promosi p = daftarPromosi.get(baris);
        lblInfoKode.setText(p.getKodePromosi());
        lblInfoProduk.setText(p.getNamaProduk() != null ? Format.judul(p.getNamaProduk()) : "-");
        lblInfoBatch.setText(p.getIdStok());
        lblInfoDiskon.setText(p.getDiskonPersen() + "%");
        lblInfoPeriode.setText(Format.tanggal(p.getTglMulai()) + " - " + Format.tanggal(p.getTglSelesai()));
        lblInfoStatus.setText(p.getStatus());
    }

    public void bukaFormBatch(Stok stok) {
        FormPromosiDialog dialog = new FormPromosiDialog(
                (java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        dialog.praIsiBatch(stok);
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        muatData();
    }

    private void bukaFormPromosi(Promosi promosi) {
        FormPromosiDialog dialog = new FormPromosiDialog(
                (java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
        if (promosi != null) {
            dialog.setEditMode(promosi.getKodePromosi(), promosi.getNamaPromosi(),
                    promosi.getIdStok(), promosi.getDiskonPersen(),
                    promosi.getTglMulai(), promosi.getTglSelesai());
        }
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
        muatData();
    }

    private void hapusDenganPopup(int baris) {
        Promosi p = daftarPromosi.get(baris);
        boolean setuju = Pesan.konfirmasi(this,
                "Hapus promosi " + p.getNamaPromosi() + " (" + p.getKodePromosi() + ")?\n\n"
              + "Diskon " + p.getDiskonPersen() + "% atas batch " + p.getIdStok()
              + " berhenti berlaku dan harga kembali seperti semula.");
        if (!setuju) {
            return;
        }
        try {
            promosiController.hapus(p.getKodePromosi());
            Pesan.sukses(this, "Promosi " + p.getNamaPromosi() + " berhasil dihapus.");
            muatData();
        } catch (IllegalArgumentException | SQLException ex) {
            Pesan.peringatan(this, ex.getMessage());
        }
    }
}
