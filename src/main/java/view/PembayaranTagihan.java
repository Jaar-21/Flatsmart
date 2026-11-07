package view;

import java.sql.SQLException;
import javax.swing.SwingUtilities;
import model.Transaksi;
import controller.TransaksiController;
import controller.TagihanController;
import util.Format;
import util.Pemeriksa;
import util.Pesan;

public class PembayaranTagihan extends javax.swing.JPanel {

    private final TagihanController tagihanController = new TagihanController();
    private final TransaksiController transaksiController = new TransaksiController();

    public PembayaranTagihan() {
        initComponents();
        lblAdmin.setText(Format.rupiah(TagihanController.BIAYA_ADMIN));
        muatLayanan();
        resetForm();
        cmbLayanan.addActionListener(e -> muatProvider());
    }

    private void muatLayanan() {
        try {
            cmbLayanan.removeAllItems();
            for (String jenis : tagihanController.daftarJenis()) {
                cmbLayanan.addItem(jenis);
            }
            muatProvider();
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    private void muatProvider() {
        String jenis = (String) cmbLayanan.getSelectedItem();
        if (jenis == null) {
            return;
        }
        try {
            cmbProvider.removeAllItems();
            for (String provider : tagihanController.daftarProvider(jenis)) {
                cmbProvider.addItem(provider);
            }
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        pnlLayanan = new javax.swing.JPanel();
        lblLayanan = new javax.swing.JLabel();
        cmbLayanan = new javax.swing.JComboBox();
        pnlForm = new javax.swing.JPanel();
        lblDataTagihan = new javax.swing.JLabel();
        lblSubForm = new javax.swing.JLabel();
        sepDataTagihan = new javax.swing.JSeparator();
        lblProvider = new javax.swing.JLabel();
        cmbProvider = new javax.swing.JComboBox();
        lblNomor = new javax.swing.JLabel();
        txtNomor = new javax.swing.JTextField();
        lblNama = new javax.swing.JLabel();
        txtNama = new javax.swing.JTextField();
        lblNominal = new javax.swing.JLabel();
        txtNominal = new javax.swing.JTextField();
        lblHintNominal = new javax.swing.JLabel();
        pnlRingkasan = new javax.swing.JPanel();
        lblRingkasan = new javax.swing.JLabel();
        sepRingkasan = new javax.swing.JSeparator();
        lblNominalTeks = new javax.swing.JLabel();
        lblNominalTagihan = new javax.swing.JLabel();
        lblAdminTeks = new javax.swing.JLabel();
        lblAdmin = new javax.swing.JLabel();
        pnlTotal = new javax.swing.JPanel();
        lblTotalTeks = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblMetode = new javax.swing.JLabel();
        cmbMetode = new javax.swing.JComboBox<>();
        lblUang = new javax.swing.JLabel();
        txtUang = new javax.swing.JTextField();
        lblKembalianTeks = new javax.swing.JLabel();
        lblKembalian = new javax.swing.JLabel();
        btnProses = new javax.swing.JButton();

        setBackground(new java.awt.Color(242, 245, 250));
        setPreferredSize(new java.awt.Dimension(1095, 805));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(15, 30, 60));
        lblJudul.setText("Pembayaran Tagihan");
        add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 18, 500, 40));

        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Catat pembayaran layanan dengan data pelanggan yang diisi manual.");
        add(lblSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 60, 600, 20));

        pnlLayanan.setBackground(new java.awt.Color(255, 255, 255));
        pnlLayanan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlLayanan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblLayanan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblLayanan.setForeground(new java.awt.Color(15, 30, 60));
        lblLayanan.setText("Jenis layanan");
        pnlLayanan.add(lblLayanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 10, 300, 18));

        cmbLayanan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbLayanan.setForeground(new java.awt.Color(15, 30, 60));
        cmbLayanan.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 8)));
        cmbLayanan.setMaximumRowCount(8);
        pnlLayanan.add(cmbLayanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 32, 320, 38));

        add(pnlLayanan, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 87, 691, 76));

        pnlForm.setBackground(new java.awt.Color(255, 255, 255));
        pnlForm.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlForm.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDataTagihan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDataTagihan.setForeground(new java.awt.Color(15, 30, 60));
        lblDataTagihan.setText("Data tagihan");
        pnlForm.add(lblDataTagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 14, 400, 24));

        lblSubForm.setForeground(new java.awt.Color(100, 116, 139));
        lblSubForm.setText("Isi sesuai tagihan atau bukti yang dibawa pelanggan.");
        pnlForm.add(lblSubForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 40, 500, 18));
        pnlForm.add(sepDataTagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 68, 691, 2));

        lblProvider.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblProvider.setForeground(new java.awt.Color(15, 30, 60));
        lblProvider.setText("Provider");
        pnlForm.add(lblProvider, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 104, 300, 18));

        cmbProvider.setEditable(true);
        cmbProvider.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cmbProvider.setForeground(new java.awt.Color(15, 30, 60));
        cmbProvider.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 8)));
        cmbProvider.setMaximumRowCount(8);
        pnlForm.add(cmbProvider, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 128, 320, 42));

        lblNomor.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNomor.setForeground(new java.awt.Color(15, 30, 60));
        lblNomor.setText("Nomor pelanggan");
        pnlForm.add(lblNomor, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 104, 300, 18));

        txtNomor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNomor.setForeground(new java.awt.Color(15, 30, 60));
        txtNomor.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtNomor.setCaretColor(new java.awt.Color(15, 30, 60));
        txtNomor.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlForm.add(txtNomor, new org.netbeans.lib.awtextra.AbsoluteConstraints(353, 128, 320, 42));

        lblNama.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNama.setForeground(new java.awt.Color(15, 30, 60));
        lblNama.setText("Nama pelanggan");
        pnlForm.add(lblNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 240, 300, 18));

        txtNama.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNama.setForeground(new java.awt.Color(15, 30, 60));
        txtNama.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtNama.setCaretColor(new java.awt.Color(15, 30, 60));
        txtNama.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlForm.add(txtNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 264, 655, 42));

        lblNominal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNominal.setForeground(new java.awt.Color(15, 30, 60));
        lblNominal.setText("Nominal tagihan");
        pnlForm.add(lblNominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 376, 300, 18));

        txtNominal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNominal.setForeground(new java.awt.Color(15, 30, 60));
        txtNominal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtNominal.setCaretColor(new java.awt.Color(15, 30, 60));
        txtNominal.setSelectionColor(new java.awt.Color(220, 229, 245));
        txtNominal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtNominalKeyReleased(evt);
            }
        });
        pnlForm.add(txtNominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 400, 655, 42));

        lblHintNominal.setFont(new java.awt.Font("Segoe UI", 0, 11)); // NOI18N
        lblHintNominal.setForeground(new java.awt.Color(100, 116, 139));
        lblHintNominal.setText("Masukkan jumlah tagihan pelanggan.");
        pnlForm.add(lblHintNominal, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 450, 655, 16));

        add(pnlForm, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 175, 691, 607));

        pnlRingkasan.setBackground(new java.awt.Color(255, 255, 255));
        pnlRingkasan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlRingkasan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRingkasan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblRingkasan.setForeground(new java.awt.Color(15, 30, 60));
        lblRingkasan.setText("Ringkasan pembayaran");
        pnlRingkasan.add(lblRingkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 14, 290, 24));
        pnlRingkasan.add(sepRingkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 333, 2));

        lblNominalTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblNominalTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblNominalTeks.setText("Nominal tagihan");
        pnlRingkasan.add(lblNominalTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 70, 150, 20));

        lblNominalTagihan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblNominalTagihan.setForeground(new java.awt.Color(15, 30, 60));
        lblNominalTagihan.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblNominalTagihan.setText("Rp0");
        pnlRingkasan.add(lblNominalTagihan, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 70, 148, 20));

        lblAdminTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblAdminTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblAdminTeks.setText("Biaya admin");
        pnlRingkasan.add(lblAdminTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 100, 150, 20));

        lblAdmin.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblAdmin.setForeground(new java.awt.Color(15, 30, 60));
        lblAdmin.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAdmin.setText("Rp0");
        pnlRingkasan.add(lblAdmin, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 100, 148, 20));

        pnlTotal.setBackground(new java.awt.Color(16, 40, 79));
        pnlTotal.setForeground(new java.awt.Color(170, 190, 225));
        pnlTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalTeks.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalTeks.setForeground(new java.awt.Color(170, 190, 225));
        lblTotalTeks.setText("TOTAL PEMBAYARAN");
        pnlTotal.add(lblTotalTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 250, 16));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("Rp0");
        pnlTotal.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 40, 270, 48));

        pnlRingkasan.add(pnlTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 132, 297, 100));

        lblMetode.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMetode.setForeground(new java.awt.Color(15, 30, 60));
        lblMetode.setText("Metode pembayaran");
        pnlRingkasan.add(lblMetode, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 264, 297, 18));

        cmbMetode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbMetode.setForeground(new java.awt.Color(15, 30, 60));
        cmbMetode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tunai", "QRIS", "Debit" }));
        cmbMetode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        cmbMetode.addActionListener(this::cmbMetodeActionPerformed);
        pnlRingkasan.add(cmbMetode, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 288, 297, 42));

        lblUang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUang.setForeground(new java.awt.Color(15, 30, 60));
        lblUang.setText("Uang diterima");
        pnlRingkasan.add(lblUang, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 372, 297, 18));

        txtUang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtUang.setForeground(new java.awt.Color(15, 30, 60));
        txtUang.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtUang.setCaretColor(new java.awt.Color(15, 30, 60));
        txtUang.setSelectionColor(new java.awt.Color(220, 229, 245));
        txtUang.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUangKeyReleased(evt);
            }
        });
        pnlRingkasan.add(txtUang, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 396, 297, 42));

        lblKembalianTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblKembalianTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblKembalianTeks.setText("Kembalian");
        pnlRingkasan.add(lblKembalianTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 470, 150, 20));

        lblKembalian.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblKembalian.setForeground(new java.awt.Color(31, 122, 77));
        lblKembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKembalian.setText("Rp0");
        pnlRingkasan.add(lblKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(167, 470, 148, 20));

        btnProses.setBackground(new java.awt.Color(16, 40, 79));
        btnProses.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnProses.setForeground(new java.awt.Color(255, 255, 255));
        btnProses.setText("Proses Pembayaran");
        btnProses.setBorderPainted(false);
        btnProses.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnProses.setFocusPainted(false);
        btnProses.setOpaque(true);
        btnProses.addActionListener(this::btnProsesActionPerformed);
        pnlRingkasan.add(btnProses, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 530, 297, 48));

        add(pnlRingkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(736, 87, 333, 695));
    }// </editor-fold>//GEN-END:initComponents

    private void txtNominalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNominalKeyReleased
        hitungTotal();
    }//GEN-LAST:event_txtNominalKeyReleased

    private void txtUangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUangKeyReleased
        hitungKembalian();
    }//GEN-LAST:event_txtUangKeyReleased

    private void cmbMetodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMetodeActionPerformed
        toggleUang();
    }//GEN-LAST:event_cmbMetodeActionPerformed

    private void btnProsesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProsesActionPerformed
        prosesPembayaran();
    }//GEN-LAST:event_btnProsesActionPerformed


    private javax.swing.JButton btnProses;
    private javax.swing.JComboBox cmbLayanan;
    private javax.swing.JComboBox<String> cmbMetode;
    private javax.swing.JComboBox cmbProvider;
    private javax.swing.JLabel lblAdmin;
    private javax.swing.JLabel lblAdminTeks;
    private javax.swing.JLabel lblDataTagihan;
    private javax.swing.JLabel lblHintNominal;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKembalian;
    private javax.swing.JLabel lblKembalianTeks;
    private javax.swing.JLabel lblLayanan;
    private javax.swing.JLabel lblMetode;
    private javax.swing.JLabel lblNama;
    private javax.swing.JLabel lblNominal;
    private javax.swing.JLabel lblNominalTagihan;
    private javax.swing.JLabel lblNominalTeks;
    private javax.swing.JLabel lblNomor;
    private javax.swing.JLabel lblProvider;
    private javax.swing.JLabel lblRingkasan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSubForm;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTeks;
    private javax.swing.JLabel lblUang;
    private javax.swing.JPanel pnlForm;
    private javax.swing.JPanel pnlLayanan;
    private javax.swing.JPanel pnlRingkasan;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JSeparator sepDataTagihan;
    private javax.swing.JSeparator sepRingkasan;
    private javax.swing.JTextField txtNama;
    private javax.swing.JTextField txtNominal;
    private javax.swing.JTextField txtNomor;
    private javax.swing.JTextField txtUang;

    private void hitungTotal() {
        long nominal = bacaRupiah(txtNominal.getText());
        lblNominalTagihan.setText(Format.rupiah(nominal));
        long total = nominal + TagihanController.BIAYA_ADMIN;
        lblTotal.setText(Format.rupiah(total));
        hitungKembalian();
    }

    private void hitungKembalian() {
        long total = bacaRupiah(txtNominal.getText()) + TagihanController.BIAYA_ADMIN;
        long uang = bacaRupiah(txtUang.getText());
        lblKembalian.setText(uang >= total ? Format.rupiah(uang - total) : "Rp0");
    }

    private void toggleUang() {
        boolean tunai = "Tunai".equals(cmbMetode.getSelectedItem());
        txtUang.setEnabled(tunai);
        if (!tunai) {
            long total = bacaRupiah(txtNominal.getText()) + TagihanController.BIAYA_ADMIN;
            txtUang.setText(String.valueOf(total));
            hitungKembalian();
        }
    }

    private void prosesPembayaran() {
        try {
            Pemeriksa.teks(txtNomor.getText(), "Nomor pelanggan", 20);
            Pemeriksa.teks(txtNama.getText(), "Nama pelanggan", 50);
            long nominal = Pemeriksa.uang(txtNominal.getText(), "Nominal tagihan",
                    1, Pemeriksa.MAKS_HARGA - TagihanController.BIAYA_ADMIN);
            long uang = Pemeriksa.uang(txtUang.getText(), "Uang diterima",
                    0, Pemeriksa.MAKS_HARGA);
            String metode = (String) cmbMetode.getSelectedItem();
            String jenis = (String) cmbLayanan.getSelectedItem();
            String provider = Pemeriksa.teks(cmbProvider.getSelectedItem() == null
                    ? "" : cmbProvider.getSelectedItem().toString(), "Provider", 20);
            if (jenis == null || jenis.isBlank()) {
                Pesan.peringatan(this, "Jenis layanan belum terpilih.");
                return;
            }

            Transaksi trx = tagihanController.proses(jenis, provider,
                    txtNomor.getText(), txtNama.getText(), nominal, metode, uang);

            PembayaranBerhasilDialog dialog = new PembayaranBerhasilDialog(
                    (java.awt.Frame) SwingUtilities.getWindowAncestor(this), true);
            dialog.isi(trx, transaksiController.buatStruk(trx));
            dialog.setVisible(true);
            resetForm();
        } catch (IllegalArgumentException ex) {
            Pesan.peringatan(this, ex.getMessage());
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    private void resetForm() {
        if (cmbLayanan.getItemCount() > 0) {
            cmbLayanan.setSelectedIndex(0);
        }
        muatProvider();
        txtNomor.setText(""); txtNama.setText("");
        txtNominal.setText(""); txtUang.setText("");
        lblNominalTagihan.setText("Rp0"); lblTotal.setText("Rp0");
        lblKembalian.setText("Rp0");
        cmbMetode.setSelectedIndex(0); txtUang.setEnabled(true);
    }

    private long bacaRupiah(String s) {
        if (s == null || s.isBlank()) return 0;
        try { return Long.parseLong(s.replaceAll("[^0-9]", "")); }
        catch (NumberFormatException e) { return 0; }
    }
}
