package view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.SpinnerNumberModel;
import model.Produk;
import model.Promosi;
import model.Stok;
import controller.ProdukController;
import controller.PromosiController;
import util.Format;
import util.Pemeriksa;
import util.Pesan;
import util.PilihTanggal;

public class FormPromosiDialog extends javax.swing.JDialog {

    private final PromosiController promosiController = new PromosiController();
    private final ProdukController produkController = new ProdukController();
    private boolean editMode;
    private String kodeAsli;

    public FormPromosiDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        java.awt.Insets in = getInsets();
        setPreferredSize(new java.awt.Dimension(722 + in.left + in.right, 600 + in.top + in.bottom));
        pack();
        getRootPane().setDefaultButton(btnSimpan);

        spnDiskon.setModel(new SpinnerNumberModel(10, 1, 100, 5));
        cmbBatch.setModel(new DefaultComboBoxModel<>());
        txtMulai.setText(Format.tanggal(LocalDate.now()));
        txtSelesai.setText(Format.tanggal(LocalDate.now().plusDays(30)));
        PilihTanggal.pasang(txtMulai);
        PilihTanggal.pasang(txtSelesai);
        usulkanKode();
        setLocationRelativeTo(parent);
    }

    public void setEditMode(String kode, String nama, String idStok,
                            int diskon, LocalDate mulai, LocalDate selesai) {
        editMode = true;
        kodeAsli = kode;

        lblJudul.setText("Ubah Promosi");
        lblSubDialog.setText("Perbarui nama, diskon, atau masa berlaku promosi.");
        btnSimpan.setText("Simpan Perubahan");

        txtKode.setText(kode);
        txtNama.setText(nama);
        spnDiskon.setValue(diskon);
        txtMulai.setText(Format.tanggal(mulai));
        txtSelesai.setText(Format.tanggal(selesai));

        cmbBatch.setModel(new DefaultComboBoxModel<>(new String[]{idStok}));
        cmbBatch.setEnabled(false);
        txtProduk.setEditable(false);
        btnCari.setEnabled(false);
    }

    private void usulkanKode() {
        try {
            txtKode.setText(promosiController.buatKode());
        } catch (SQLException ex) {
            txtKode.setText("");
            Pesan.gagalDatabase(this, ex);
        }
    }

    private void cariProduk() {
        String kata = txtProduk.getText().trim();
        if (kata.isEmpty()) {
            Pesan.peringatan(this, "Isi barcode atau nama produk lebih dulu.");
            return;
        }
        try {
            Produk produk = produkController.cari(kata);
            if (produk == null) {
                List<Produk> kandidat = produkController.cari(kata, null);
                if (kandidat.isEmpty()) {
                    Pesan.peringatan(this, "Produk \"" + kata + "\" tidak ditemukan.");
                    return;
                }
                produk = kandidat.get(0);
            }
            txtProduk.setText(produk.getBarcode() + " - " + produk.getNamaProduk());
            muatBatch(produk.getBarcode());
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    public void praIsiBatch(Stok stok) {
        txtProduk.setText(stok.getBarcode() + " - " + stok.getNamaProduk());
        try {
            muatBatch(stok.getBarcode());
            pilihBatch(stok.getIdStok());
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
        txtNama.requestFocusInWindow();
    }

    private void pilihBatch(String idStok) {
        for (int i = 0; i < cmbBatch.getItemCount(); i++) {
            if (cmbBatch.getItemAt(i).startsWith(idStok)) {
                cmbBatch.setSelectedIndex(i);
                return;
            }
        }
    }

    private void muatBatch(String barcode) throws SQLException {
        List<Stok> batch = promosiController.cariBatch(barcode);
        DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
        if (batch.isEmpty()) {
            model.addElement("(Produk ini belum punya batch stok)");
        } else {
            for (Stok s : batch) {
                model.addElement(s.getIdStok() + " | stok " + s.getJumlah()
                        + " unit | exp " + Format.tanggal(s.getTglKedaluwarsa()));
            }
        }
        cmbBatch.setModel(model);
    }

    private void simpan() {
        String batch = (String) cmbBatch.getSelectedItem();
        if (batch == null || batch.startsWith("(")) {
            Pesan.peringatan(this, "Pilih produk dan batch stok target.");
            return;
        }
        String idStok = batch.split("\\|")[0].trim();
        int diskon = (int) spnDiskon.getValue();

        String nama;
        LocalDate mulai, selesai;
        try {
            nama = Pemeriksa.teks(txtNama.getText(), "Nama promosi", 20);
            mulai = Pemeriksa.tanggal(txtMulai.getText(), "Tanggal mulai");
            selesai = Pemeriksa.tanggal(txtSelesai.getText(), "Tanggal selesai");
            Pemeriksa.rentang(mulai, selesai, "Tanggal selesai");
        } catch (IllegalArgumentException ex) {
            Pesan.peringatan(this, ex.getMessage());
            return;
        }

        try {
            String kode = editMode ? kodeAsli : txtKode.getText().trim();
            if (!editMode && kode.isEmpty()) {
                Pesan.peringatan(this, "Kode promosi belum tersedia, buka ulang form ini.");
                return;
            }
            Promosi promosi = new Promosi(kode, nama, idStok, diskon, mulai, selesai);
            if (editMode) {
                promosiController.ubah(promosi);
                Pesan.sukses(this, "Promosi " + kode + " diperbarui.");
            } else {
                promosiController.tambah(promosi);
                Pesan.sukses(this, "Promosi " + kode + " disimpan.");
            }
            dispose();
        } catch (IllegalArgumentException ex) {
            Pesan.peringatan(this, ex.getMessage());
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSubDialog = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        lblKode = new javax.swing.JLabel();
        txtKode = new javax.swing.JTextField();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblProduk = new javax.swing.JLabel();
        txtProduk = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        lblBatch = new javax.swing.JLabel();
        cmbBatch = new javax.swing.JComboBox<>();
        lblHintBatch = new javax.swing.JLabel();
        lblDiskon = new javax.swing.JLabel();
        spnDiskon = new javax.swing.JSpinner();
        lblMulai = new javax.swing.JLabel();
        txtMulai = new javax.swing.JTextField();
        lblSelesai = new javax.swing.JLabel();
        txtSelesai = new javax.swing.JTextField();
        pnlFooter = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlatSmart — Promosi");
        setBackground(new java.awt.Color(242, 245, 250));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(16, 40, 79));
        pnlHeader.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(255, 255, 255));
        lblJudul.setText("Tambah Promosi");
        pnlHeader.add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 16, 500, 30));

        lblSubDialog.setForeground(new java.awt.Color(170, 190, 225));
        lblSubDialog.setText("Tentukan produk, batch stok target, dan masa berlaku diskon.");
        pnlHeader.add(lblSubDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 48, 600, 18));

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 722, 80));

        pnlBody.setBackground(new java.awt.Color(242, 245, 250));
        pnlBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblKode.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblKode.setForeground(new java.awt.Color(16, 40, 79));
        lblKode.setText("Kode Promosi");
        pnlBody.add(lblKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 16, 200, 18));

        txtKode.setEditable(false);
        txtKode.setBackground(new java.awt.Color(255, 255, 255));
        pnlBody.add(txtKode, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 38, 220, 38));

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNama.setForeground(new java.awt.Color(16, 40, 79));
        lblNama.setText("Nama Promosi *");
        pnlBody.add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 16, 434, 18));
        pnlBody.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(266, 38, 434, 38));

        lblProduk.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblProduk.setForeground(new java.awt.Color(16, 40, 79));
        lblProduk.setText("Cari Barcode / Produk *");
        pnlBody.add(lblProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 114, 400, 18));
        pnlBody.add(txtProduk, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 136, 560, 38));

        btnCari.setBackground(new java.awt.Color(16, 40, 79));
        btnCari.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnCari.setForeground(new java.awt.Color(255, 255, 255));
        btnCari.setText("Cari Produk");
        btnCari.setBorderPainted(false);
        btnCari.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCari.setFocusPainted(false);
        btnCari.setOpaque(true);
        btnCari.addActionListener(this::btnCariActionPerformed);
        pnlBody.add(btnCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 136, 106, 38));

        lblBatch.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblBatch.setForeground(new java.awt.Color(16, 40, 79));
        lblBatch.setText("Stok Target  *");
        pnlBody.add(lblBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 190, 400, 18));
        pnlBody.add(cmbBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 212, 678, 38));

        lblHintBatch.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblHintBatch.setForeground(new java.awt.Color(100, 116, 139));
        lblHintBatch.setText("Promo hanya berlaku untuk batch ini.");
        pnlBody.add(lblHintBatch, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 254, 678, 16));

        lblDiskon.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDiskon.setForeground(new java.awt.Color(16, 40, 79));
        lblDiskon.setText("Diskon (%) *");
        pnlBody.add(lblDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 288, 200, 18));
        pnlBody.add(spnDiskon, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 310, 200, 38));

        lblMulai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMulai.setForeground(new java.awt.Color(16, 40, 79));
        lblMulai.setText("Tanggal Mulai *");
        pnlBody.add(lblMulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 288, 214, 18));
        pnlBody.add(txtMulai, new org.netbeans.lib.awtextra.AbsoluteConstraints(246, 310, 214, 38));

        lblSelesai.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSelesai.setForeground(new java.awt.Color(16, 40, 79));
        lblSelesai.setText("Tanggal Selesai *");
        pnlBody.add(lblSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 288, 216, 18));
        pnlBody.add(txtSelesai, new org.netbeans.lib.awtextra.AbsoluteConstraints(484, 310, 216, 38));

        getContentPane().add(pnlBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 722, 440));

        pnlFooter.setBackground(new java.awt.Color(242, 245, 250));
        pnlFooter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(16, 40, 79));
        btnBatal.setText("Batal");
        btnBatal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.setOpaque(true);
        btnBatal.addActionListener(this::btnBatalActionPerformed);
        pnlFooter.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(444, 20, 80, 40));

        btnSimpan.setBackground(new java.awt.Color(16, 40, 79));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan Promosi");
        btnSimpan.setBorderPainted(false);
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setFocusPainted(false);
        btnSimpan.setOpaque(true);
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);
        pnlFooter.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 160, 40));

        getContentPane().add(pnlFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 520, 722, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        cariProduk();
    }//GEN-LAST:event_btnCariActionPerformed

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JComboBox<String> cmbBatch;
    private javax.swing.JLabel lblBatch;
    private javax.swing.JLabel lblDiskon;
    private javax.swing.JLabel lblHintBatch;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKode;
    private javax.swing.JLabel lblMulai;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblProduk;
    private javax.swing.JLabel lblSelesai;
    private javax.swing.JLabel lblSubDialog;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JSpinner spnDiskon;
    private javax.swing.JTextField txtKode;
    private javax.swing.JTextField txtMulai;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtProduk;
    private javax.swing.JTextField txtSelesai;
}
