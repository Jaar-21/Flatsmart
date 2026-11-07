package view;

import java.time.LocalDate;
import javax.swing.SpinnerNumberModel;
import controller.CrudController;
import controller.ProdukController;
import model.Produk;
import util.Format;
import util.Pemeriksa;
import util.Pesan;
import util.PilihTanggal;

public class FormProdukDialog extends javax.swing.JDialog {

    private final CrudController<Produk> produkController = new ProdukController();
    private boolean editMode;
    private String originalBarcode;

    public FormProdukDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        java.awt.Insets in = getInsets();
        setPreferredSize(new java.awt.Dimension(668 + in.left + in.right, 590 + in.top + in.bottom));
        pack();
        spnStok.setModel(new SpinnerNumberModel(1, 1, 999999, 1));
        txtExp.setText(Format.tanggal(LocalDate.now().plusMonths(6)));
        PilihTanggal.pasang(txtExp);
        setLocationRelativeTo(parent);
    }

    public void setEditMode(String barcode, String nama, long beli, long jual) {
        editMode = true;
        originalBarcode = barcode;

        lblJudulDialog.setText("Ubah Produk");
        lblSubDialog.setText("Perbarui identitas produk yang sudah ada.");
        btnSimpan.setText("Simpan Perubahan");

        txtBarcode.setText(barcode);
        txtBarcode.setEditable(false);
        txtNama.setText(nama);
        txtBeli.setText(String.valueOf(beli));
        txtJual.setText(String.valueOf(jual));

        sepStok.setVisible(false);
        lblStok.setVisible(false);
        lblStokAwal.setVisible(false);
        spnStok.setVisible(false);
        lblExp.setVisible(false);
        txtExp.setVisible(false);
        lblHintExp.setVisible(false);
        kecilkanUntukUbah();
    }

    private void kecilkanUntukUbah() {
        java.awt.Container isi = getContentPane();
        isi.remove(pnlBody);
        isi.remove(pnlFooter);
        isi.add(pnlBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 668, 240));
        isi.add(pnlFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 668, 80));
        java.awt.Insets in = getInsets();
        setPreferredSize(new java.awt.Dimension(668 + in.left + in.right, 400 + in.top + in.bottom));
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void simpan() {
        try {
            String barcode = Pemeriksa.barcode(txtBarcode.getText());
            String nama = Pemeriksa.teks(txtNama.getText(), "Nama produk", 50);
            long beli = Pemeriksa.uang(txtBeli.getText(), "Harga beli", 0, Pemeriksa.MAKS_HARGA);
            long jual = Pemeriksa.uang(txtJual.getText(), "Harga jual", 1, Pemeriksa.MAKS_HARGA);

            Produk produk = new Produk(editMode ? originalBarcode : barcode, nama, beli, jual);
            if (editMode) {
                produkController.ubah(produk);
                Pesan.sukses(this, "Produk berhasil diperbarui.");
            } else {
                produk.setStokTotal((int) spnStok.getValue());
                produk.setKedaluwarsaBatch(
                        Pemeriksa.tanggal(txtExp.getText(), "Tanggal kedaluwarsa"));
                produkController.tambah(produk);
                Pesan.sukses(this, "Produk baru berhasil disimpan.");
            }
            dispose();
        } catch (IllegalArgumentException ex) {
            Pesan.peringatan(this, ex.getMessage());
        } catch (Exception ex) {
            Pesan.galat(this, "Gagal menyimpan produk.", ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblJudulDialog = new javax.swing.JLabel();
        lblSubDialog = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        lblIdentitas = new javax.swing.JLabel();
        lblBarcode = new javax.swing.JLabel();
        txtBarcode = new javax.swing.JTextField();
        lblHintBarcode = new javax.swing.JLabel();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblBeli = new javax.swing.JLabel();
        txtBeli = new javax.swing.JTextField();
        lblJual = new javax.swing.JLabel();
        txtJual = new javax.swing.JTextField();
        sepStok = new javax.swing.JSeparator();
        lblStok = new javax.swing.JLabel();
        lblStokAwal = new javax.swing.JLabel();
        spnStok = new javax.swing.JSpinner();
        lblExp = new javax.swing.JLabel();
        txtExp = new javax.swing.JTextField();
        lblHintExp = new javax.swing.JLabel();
        pnlFooter = new javax.swing.JPanel();
        btnBatal = new javax.swing.JButton();
        btnSimpan = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlatSmart — Produk");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(16, 40, 79));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudulDialog.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblJudulDialog.setForeground(new java.awt.Color(255, 255, 255));
        lblJudulDialog.setText("Tambah Produk");
        pnlHeader.add(lblJudulDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 16, 500, 30));

        lblSubDialog.setForeground(new java.awt.Color(170, 190, 225));
        lblSubDialog.setText("Lengkapi identitas produk dan persediaan batch pertamanya.");
        pnlHeader.add(lblSubDialog, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 48, 560, 18));

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 668, 80));

        pnlBody.setBackground(new java.awt.Color(242, 245, 250));
        pnlBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblIdentitas.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblIdentitas.setForeground(new java.awt.Color(16, 40, 79));
        lblIdentitas.setText("Identitas produk");
        pnlBody.add(lblIdentitas, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 16, 300, 24));

        lblBarcode.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblBarcode.setForeground(new java.awt.Color(16, 40, 79));
        lblBarcode.setText("Barcode*");
        pnlBody.add(lblBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 58, 300, 18));

        txtBarcode.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBarcode.setForeground(new java.awt.Color(16, 40, 79));
        txtBarcode.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtBarcode.setCaretColor(new java.awt.Color(16, 40, 79));
        txtBarcode.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlBody.add(txtBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 80, 300, 38));

        lblHintBarcode.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblHintBarcode.setForeground(new java.awt.Color(100, 116, 139));
        lblHintBarcode.setText("pindai atau ketik barcode.");
        pnlBody.add(lblHintBarcode, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 122, 300, 16));

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNama.setForeground(new java.awt.Color(16, 40, 79));
        lblNama.setText("Nama Produk *");
        pnlBody.add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 58, 300, 18));

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNama.setForeground(new java.awt.Color(16, 40, 79));
        txtNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtNama.setCaretColor(new java.awt.Color(16, 40, 79));
        txtNama.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlBody.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 80, 300, 38));

        lblBeli.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblBeli.setForeground(new java.awt.Color(16, 40, 79));
        lblBeli.setText("Harga Beli (Rp) *");
        pnlBody.add(lblBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 152, 300, 18));

        txtBeli.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtBeli.setForeground(new java.awt.Color(16, 40, 79));
        txtBeli.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtBeli.setCaretColor(new java.awt.Color(16, 40, 79));
        txtBeli.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlBody.add(txtBeli, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 174, 300, 38));

        lblJual.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblJual.setForeground(new java.awt.Color(16, 40, 79));
        lblJual.setText("Harga Jual (Rp) *");
        pnlBody.add(lblJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 152, 300, 18));

        txtJual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtJual.setForeground(new java.awt.Color(16, 40, 79));
        txtJual.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtJual.setCaretColor(new java.awt.Color(16, 40, 79));
        txtJual.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlBody.add(txtJual, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 174, 300, 38));
        pnlBody.add(sepStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 252, 624, 2));

        lblStok.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblStok.setForeground(new java.awt.Color(16, 40, 79));
        lblStok.setText("Stok ");
        pnlBody.add(lblStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 268, 400, 24));

        lblStokAwal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblStokAwal.setForeground(new java.awt.Color(16, 40, 79));
        lblStokAwal.setText("Stok  *");
        pnlBody.add(lblStokAwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 310, 300, 18));

        spnStok.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spnStok.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        pnlBody.add(spnStok, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 340, 300, 38));

        lblExp.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblExp.setForeground(new java.awt.Color(16, 40, 79));
        lblExp.setText("Tanggal Kadaluwarsa *");
        pnlBody.add(lblExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 310, 300, 18));

        txtExp.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtExp.setForeground(new java.awt.Color(16, 40, 79));
        txtExp.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtExp.setCaretColor(new java.awt.Color(16, 40, 79));
        txtExp.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlBody.add(txtExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 340, 300, 38));

        lblHintExp.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblHintExp.setForeground(new java.awt.Color(100, 116, 139));
        lblHintExp.setText("Contoh: 25/12/2026");
        pnlBody.add(lblHintExp, new org.netbeans.lib.awtextra.AbsoluteConstraints(346, 385, 300, 16));

        getContentPane().add(pnlBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 668, 430));

        pnlFooter.setBackground(new java.awt.Color(242, 245, 250));
        pnlFooter.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 0, 0, 0, new java.awt.Color(214, 222, 236)));
        pnlFooter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnBatal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnBatal.setForeground(new java.awt.Color(16, 40, 79));
        btnBatal.setText("Batal");
        btnBatal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        btnBatal.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBatal.setFocusPainted(false);
        btnBatal.setOpaque(true);
        btnBatal.addActionListener(this::btnBatalActionPerformed);
        pnlFooter.add(btnBatal, new org.netbeans.lib.awtextra.AbsoluteConstraints(394, 20, 80, 40));

        btnSimpan.setBackground(new java.awt.Color(16, 40, 79));
        btnSimpan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnSimpan.setForeground(new java.awt.Color(255, 255, 255));
        btnSimpan.setText("Simpan Produk");
        btnSimpan.setBorderPainted(false);
        btnSimpan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSimpan.setFocusPainted(false);
        btnSimpan.setOpaque(true);
        btnSimpan.addActionListener(this::btnSimpanActionPerformed);
        pnlFooter.add(btnSimpan, new org.netbeans.lib.awtextra.AbsoluteConstraints(486, 20, 160, 40));

        getContentPane().add(pnlFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 510, 668, 80));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBatalActionPerformed
        dispose();
    }//GEN-LAST:event_btnBatalActionPerformed

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
        simpan();
    }//GEN-LAST:event_btnSimpanActionPerformed

    private javax.swing.JButton btnBatal;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JLabel lblBarcode;
    private javax.swing.JLabel lblBeli;
    private javax.swing.JLabel lblExp;
    private javax.swing.JLabel lblHintBarcode;
    private javax.swing.JLabel lblHintExp;
    private javax.swing.JLabel lblIdentitas;
    private javax.swing.JLabel lblJual;
    private javax.swing.JLabel lblJudulDialog;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblStok;
    private javax.swing.JLabel lblStokAwal;
    private javax.swing.JLabel lblSubDialog;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JSeparator sepStok;
    private javax.swing.JSpinner spnStok;
    private javax.swing.JTextField txtBarcode;
    private javax.swing.JTextField txtBeli;
    private javax.swing.JTextField txtExp;
    private javax.swing.JTextField txtJual;
    private javax.swing.JTextField txtNama;
}
