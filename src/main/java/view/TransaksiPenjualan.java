package view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.ItemKeranjang;
import model.Produk;
import model.Transaksi;
import controller.PenjualanController;
import controller.TransaksiController;
import util.Format;
import util.GayaTabel;
import util.Pemeriksa;
import util.Pesan;
import util.SaranKetik;

public class TransaksiPenjualan extends javax.swing.JPanel {

    private static final int KOLOM_HAPUS = 6;

    private final List<ItemKeranjang> keranjang = new ArrayList<>();
    private final PenjualanController penjualanController = new PenjualanController();
    private final TransaksiController transaksiController = new TransaksiController();
    private DefaultTableModel tabelModel;

    private SaranKetik saran;
    private final List<Produk> saranAktif = new ArrayList<>();

    public TransaksiPenjualan() {
        initComponents();

        tabelModel = (DefaultTableModel) tblKeranjang.getModel();
        GayaTabel.rapikan(tblKeranjang, new int[]{2, 4, 5}, new int[]{3});
        GayaTabel.pasangAksi(tblKeranjang, "Hapus", "Hapus");
        GayaTabel.aturLebar(tblKeranjang, new int[]{190, 100, 78, 42, 60, 106, GayaTabel.lebarMinimumAksi(1)});
        tblKeranjang.setRowHeight(56);
        pasangAutocomplete();

        resetForm();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTambah = new javax.swing.JPanel();
        lblTambah = new javax.swing.JLabel();
        sepTambah = new javax.swing.JSeparator();
        lblScan = new javax.swing.JLabel();
        txtScan = new javax.swing.JTextField();
        lblJumlah = new javax.swing.JLabel();
        spnJumlah = new javax.swing.JSpinner();
        btnTambah = new javax.swing.JButton();
        pnlKeranjang = new javax.swing.JPanel();
        lblKeranjang = new javax.swing.JLabel();
        sepKeranjang = new javax.swing.JSeparator();
        scrollKeranjang = new javax.swing.JScrollPane();
        tblKeranjang = new javax.swing.JTable();
        btnKosongkan = new javax.swing.JButton();
        pnlRingkasan = new javax.swing.JPanel();
        pnlTotal = new javax.swing.JPanel();
        lblTotalTeks = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        sepRingkasan = new javax.swing.JSeparator();
        lblRingkasan = new javax.swing.JLabel();
        lblSubtotalTeks = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblPromoTeks = new javax.swing.JLabel();
        lblPromo = new javax.swing.JLabel();
        lblMetode = new javax.swing.JLabel();
        cmbMetode = new javax.swing.JComboBox<>();
        lblUang = new javax.swing.JLabel();
        txtUang = new javax.swing.JTextField();
        lblKembalianTeks = new javax.swing.JLabel();
        lblKembalian = new javax.swing.JLabel();
        btnBayar = new javax.swing.JButton();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 245, 250));
        setPreferredSize(new java.awt.Dimension(1095, 805));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTambah.setBackground(new java.awt.Color(255, 255, 255));
        pnlTambah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlTambah.setForeground(new java.awt.Color(226, 231, 240));
        pnlTambah.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTambah.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTambah.setForeground(new java.awt.Color(15, 30, 60));
        lblTambah.setText("Tambah Produk");
        pnlTambah.add(lblTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 12, 300, 24));
        pnlTambah.add(sepTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 48, 709, 2));

        lblScan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblScan.setForeground(new java.awt.Color(15, 30, 60));
        lblScan.setText("Scan barcode / cari produk");
        pnlTambah.add(lblScan, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 60, 300, 18));

        txtScan.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtScan.setForeground(new java.awt.Color(15, 30, 60));
        txtScan.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtScan.setCaretColor(new java.awt.Color(15, 30, 60));
        txtScan.setSelectionColor(new java.awt.Color(220, 229, 245));
        txtScan.addActionListener(this::txtScanActionPerformed);
        pnlTambah.add(txtScan, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 82, 488, 38));

        lblJumlah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblJumlah.setForeground(new java.awt.Color(15, 30, 60));
        lblJumlah.setText("Jumlah");
        pnlTambah.add(lblJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 60, 70, 18));

        spnJumlah.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        spnJumlah.setModel(new javax.swing.SpinnerNumberModel(1, 1, 999, 1));
        spnJumlah.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlTambah.add(spnJumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(513, 82, 72, 38));

        btnTambah.setBackground(new java.awt.Color(15, 30, 60));
        btnTambah.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTambah.setForeground(new java.awt.Color(255, 255, 255));
        btnTambah.setText("Tambah");
        btnTambah.setBorderPainted(false);
        btnTambah.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTambah.setFocusPainted(false);
        btnTambah.setOpaque(true);
        btnTambah.addActionListener(this::btnTambahActionPerformed);
        pnlTambah.add(btnTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(594, 82, 96, 38));

        add(pnlTambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 87, 709, 137));

        pnlKeranjang.setBackground(new java.awt.Color(255, 255, 255));
        pnlKeranjang.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlKeranjang.setForeground(new java.awt.Color(226, 231, 240));
        pnlKeranjang.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblKeranjang.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblKeranjang.setForeground(new java.awt.Color(15, 30, 60));
        lblKeranjang.setText("Keranjang belanja");
        pnlKeranjang.add(lblKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 14, 300, 24));
        pnlKeranjang.add(sepKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 709, 2));

        scrollKeranjang.setBackground(new java.awt.Color(255, 255, 255));
        scrollKeranjang.setBorder(null);
        scrollKeranjang.setForeground(new java.awt.Color(100, 116, 139));

        tblKeranjang.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblKeranjang.setForeground(new java.awt.Color(15, 30, 60));
        tblKeranjang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produk", "Batch Exp", "Harga", "Qty", "Diskon", "Subtotal", "Hapus"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblKeranjang.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblKeranjang.setFillsViewportHeight(true);
        tblKeranjang.setGridColor(new java.awt.Color(226, 231, 240));
        tblKeranjang.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblKeranjang.setRowHeight(56);
        tblKeranjang.setSelectionBackground(new java.awt.Color(232, 240, 254));
        tblKeranjang.setSelectionForeground(new java.awt.Color(15, 30, 60));
        tblKeranjang.setShowHorizontalLines(true);
        tblKeranjang.getTableHeader().setReorderingAllowed(false);
        tblKeranjang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKeranjangMouseClicked(evt);
            }
        });
        scrollKeranjang.setViewportView(tblKeranjang);

        pnlKeranjang.add(scrollKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 82, 679, 260));

        btnKosongkan.setBackground(new java.awt.Color(16, 40, 79));
        btnKosongkan.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnKosongkan.setForeground(new java.awt.Color(255, 255, 255));
        btnKosongkan.setText("Kosongkan keranjang");
        btnKosongkan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        btnKosongkan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnKosongkan.setFocusPainted(false);
        btnKosongkan.setOpaque(true);
        btnKosongkan.addActionListener(this::btnKosongkanActionPerformed);
        pnlKeranjang.add(btnKosongkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 360, 154, 34));

        add(pnlKeranjang, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 241, 709, 541));

        pnlRingkasan.setBackground(new java.awt.Color(255, 255, 255));
        pnlRingkasan.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlRingkasan.setForeground(new java.awt.Color(226, 231, 240));
        pnlRingkasan.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlTotal.setBackground(new java.awt.Color(16, 40, 79));
        pnlTotal.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblTotalTeks.setFont(new java.awt.Font("Segoe UI", 1, 11)); // NOI18N
        lblTotalTeks.setForeground(new java.awt.Color(170, 190, 225));
        lblTotalTeks.setText("TOTAL BAYAR");
        pnlTotal.add(lblTotalTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 10, 200, 16));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(255, 255, 255));
        lblTotal.setText("Rp0");
        pnlTotal.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 32, 250, 44));

        pnlRingkasan.add(pnlTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 160, 280, 88));
        pnlRingkasan.add(sepRingkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 316, 2));

        lblRingkasan.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblRingkasan.setForeground(new java.awt.Color(15, 30, 60));
        lblRingkasan.setText("Ringkasan pembayaran");
        pnlRingkasan.add(lblRingkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 200, 20));

        lblSubtotalTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtotalTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtotalTeks.setText("Subtotal (0 unit)");
        pnlRingkasan.add(lblSubtotalTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, -1, -1));

        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(15, 30, 60));
        lblSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSubtotal.setText("Rp0");
        pnlRingkasan.add(lblSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 66, 148, 20));

        lblPromoTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPromoTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblPromoTeks.setText("Potongan promo");
        pnlRingkasan.add(lblPromoTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 94, 150, 20));

        lblPromo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPromo.setForeground(new java.awt.Color(31, 122, 77));
        lblPromo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPromo.setText("Rp0");
        pnlRingkasan.add(lblPromo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 94, 148, 20));

        lblMetode.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblMetode.setForeground(new java.awt.Color(15, 30, 60));
        lblMetode.setText("Metode Pembayaran");
        pnlRingkasan.add(lblMetode, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 266, 280, 18));

        cmbMetode.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbMetode.setForeground(new java.awt.Color(15, 30, 60));
        cmbMetode.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tunai", "QRIS", "Debit" }));
        cmbMetode.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        cmbMetode.addActionListener(this::cmbMetodeActionPerformed);
        pnlRingkasan.add(cmbMetode, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 288, 280, 38));

        lblUang.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblUang.setForeground(new java.awt.Color(15, 30, 60));
        lblUang.setText("Uang diterima");
        pnlRingkasan.add(lblUang, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 340, 280, 18));

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
        pnlRingkasan.add(txtUang, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 362, 280, 38));

        lblKembalianTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblKembalianTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblKembalianTeks.setText("Kembalian");
        pnlRingkasan.add(lblKembalianTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 416, 150, 20));

        lblKembalian.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        lblKembalian.setForeground(new java.awt.Color(31, 122, 77));
        lblKembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKembalian.setText("Rp0");
        pnlRingkasan.add(lblKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 416, 148, 20));

        btnBayar.setBackground(new java.awt.Color(16, 40, 79));
        btnBayar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnBayar.setForeground(new java.awt.Color(255, 255, 255));
        btnBayar.setText("Proses Bayar");
        btnBayar.setBorderPainted(false);
        btnBayar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnBayar.setFocusPainted(false);
        btnBayar.setOpaque(true);
        btnBayar.addActionListener(this::btnBayarActionPerformed);
        pnlRingkasan.add(btnBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 460, 280, 34));

        add(pnlRingkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(754, 87, 316, 695));

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(15, 30, 60));
        lblJudul.setText("Transaksi Penjualan");
        add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 18, 280, 40));

        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Scan produk, periksa keranjang, lalu selesaikan pembayaran.");
        add(lblSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 60, 600, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void txtScanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtScanActionPerformed
        if (saran != null && saran.terbuka() && saran.indeksTerpilih() >= 0) {
            saran.pilihTerpilih();
        } else {
            tambahItem();
        }
    }//GEN-LAST:event_txtScanActionPerformed

    private void btnTambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahActionPerformed
        tambahItem();
    }//GEN-LAST:event_btnTambahActionPerformed

    private void btnKosongkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKosongkanActionPerformed
        if (keranjang.isEmpty()) return;
        if (Pesan.konfirmasi(this, "Kosongkan seluruh keranjang?")) {
            keranjang.clear();
            refreshKeranjang();
        }
    }//GEN-LAST:event_btnKosongkanActionPerformed

    private void cmbMetodeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbMetodeActionPerformed
        toggleUang();
    }//GEN-LAST:event_cmbMetodeActionPerformed

    private void txtUangKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUangKeyReleased
        hitungKembalian();
    }//GEN-LAST:event_txtUangKeyReleased

    private void btnBayarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBayarActionPerformed
        prosesBayar();
    }//GEN-LAST:event_btnBayarActionPerformed

    private void tblKeranjangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblKeranjangMouseClicked
        int baris = tblKeranjang.rowAtPoint(evt.getPoint());
        if (baris < 0 || baris >= keranjang.size()) {
            return;
        }
        if (GayaTabel.aksiDi(tblKeranjang, KOLOM_HAPUS, evt.getPoint()) >= 0) {
            keranjang.remove(baris);
            refreshKeranjang();
        }
    }//GEN-LAST:event_tblKeranjangMouseClicked


    private javax.swing.JButton btnBayar;
    private javax.swing.JButton btnKosongkan;
    private javax.swing.JButton btnTambah;
    private javax.swing.JComboBox<String> cmbMetode;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblJumlah;
    private javax.swing.JLabel lblKembalian;
    private javax.swing.JLabel lblKembalianTeks;
    private javax.swing.JLabel lblKeranjang;
    private javax.swing.JLabel lblMetode;
    private javax.swing.JLabel lblPromo;
    private javax.swing.JLabel lblPromoTeks;
    private javax.swing.JLabel lblRingkasan;
    private javax.swing.JLabel lblScan;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotalTeks;
    private javax.swing.JLabel lblTambah;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTeks;
    private javax.swing.JLabel lblUang;
    private javax.swing.JPanel pnlKeranjang;
    private javax.swing.JPanel pnlRingkasan;
    private javax.swing.JPanel pnlTambah;
    private javax.swing.JPanel pnlTotal;
    private javax.swing.JScrollPane scrollKeranjang;
    private javax.swing.JSeparator sepKeranjang;
    private javax.swing.JSeparator sepRingkasan;
    private javax.swing.JSeparator sepTambah;
    private javax.swing.JSpinner spnJumlah;
    private javax.swing.JTable tblKeranjang;
    private javax.swing.JTextField txtScan;
    private javax.swing.JTextField txtUang;

    private void refreshKeranjang() {
        tabelModel.setRowCount(0);
        int totalUnit = 0;
        long subtotal = 0, totalDiskon = 0;
        for (ItemKeranjang item : keranjang) {
            String batchExp = item.getTanggalExp() == null
                ? "<html>" + item.getIdStok() + "</html>"
                : "<html>" + item.getIdStok() + "<br>exp " + Format.tanggal(item.getTanggalExp()) + "</html>";
            tabelModel.addRow(new Object[]{
                item.getNamaProduk(), batchExp,
                Format.rupiah(item.getHargaJual()), item.getJumlah(),
                item.getDiskonPersen() > 0 ? item.getDiskonPersen() + "%" : "-",
                Format.rupiah(item.getTotal()), ""
            });
            totalUnit += item.getJumlah();
            subtotal += item.getSubtotal();
            totalDiskon += item.getPotongan();
        }
        long totalBayar = subtotal - totalDiskon;

        lblSubtotalTeks.setText("Subtotal (" + totalUnit + " unit)");
        lblSubtotal.setText(Format.rupiah(subtotal));
        lblPromo.setText(totalDiskon > 0 ? "\u2212" + Format.rupiah(totalDiskon) : "Rp0");
        lblTotal.setText(Format.rupiah(totalBayar));

        hitungKembalian();
    }

    private void tambahItem() {
        String kode = txtScan.getText().trim();
        int jumlah = (Integer) spnJumlah.getValue();
        try {
            ItemKeranjang ada = cariDiKeranjang(kode);
            if (ada == null) {
                keranjang.add(penjualanController.cariItem(kode, jumlah));
            } else if (ada.getJumlah() + jumlah > ada.getStokTersedia()) {
                Pesan.peringatan(this, "Stok tersedia hanya " + ada.getStokTersedia() + " unit.");
                return;
            } else {
                ada.setJumlah(ada.getJumlah() + jumlah);
            }
            refreshKeranjang();
            txtScan.setText("");
            spnJumlah.setValue(1);
            txtScan.requestFocus();
        } catch (IllegalArgumentException ex) {
            Pesan.peringatan(this, ex.getMessage());
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    private ItemKeranjang cariDiKeranjang(String barcode) {
        for (ItemKeranjang item : keranjang) {
            if (item.getBarcode().equals(barcode)) return item;
        }
        return null;
    }

    private void hitungKembalian() {
        long kembalian = bacaUang() - getTotalBayar();
        lblKembalian.setText(kembalian > 0 ? Format.rupiah(kembalian) : "Rp0");
    }

    private void toggleUang() {
        boolean tunai = "Tunai".equals(cmbMetode.getSelectedItem());
        txtUang.setEnabled(tunai);
        if (!tunai) {
            txtUang.setText(String.valueOf(getTotalBayar()));
        }
        hitungKembalian();
    }

    private long getTotalBayar() {
        long subtotal = 0;
        long potongan = 0;
        for (ItemKeranjang item : keranjang) {
            subtotal += item.getSubtotal();
            potongan += item.getPotongan();
        }
        return subtotal - potongan;
    }

    private long bacaUang() {
        String digit = txtUang.getText().replaceAll("[^0-9]", "");
        if (digit.isEmpty()) return 0;
        try {
            return Long.parseLong(digit);
        } catch (NumberFormatException e) {
            return Pemeriksa.MAKS_HARGA;
        }
    }

    private void prosesBayar() {
        if (keranjang.isEmpty()) {
            Pesan.peringatan(this, "Keranjang masih kosong.");
            return;
        }
        try {
            String metode = (String) cmbMetode.getSelectedItem();
            long uang = "Tunai".equalsIgnoreCase(metode)
                    ? Pemeriksa.uang(txtUang.getText(), "Uang diterima", 1, Pemeriksa.MAKS_HARGA)
                    : bacaUang();
            Transaksi trx = penjualanController.proses(keranjang, metode, uang);

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
        keranjang.clear();
        tabelModel.setRowCount(0);
        lblSubtotalTeks.setText("Subtotal (0 unit)");
        lblSubtotal.setText("Rp0");
        lblPromo.setText("Rp0");
        lblTotal.setText("Rp0");
        lblKembalian.setText("Rp0");
        txtUang.setText("");
        txtScan.setText("");
        spnJumlah.setValue(1);
        cmbMetode.setSelectedIndex(0);
        txtUang.setEnabled(true);
        txtScan.requestFocus();
    }


    private void pasangAutocomplete() {
        saran = SaranKetik.pasang(txtScan, 488, 240, 2, keyword -> {
            List<Produk> hasil = penjualanController.saranProduk(keyword);
            saranAktif.clear();
            saranAktif.addAll(hasil);
            List<String> baris = new ArrayList<>();
            for (Produk p : hasil) {
                baris.add(p.getNamaProduk() + " (" + Format.rupiah(p.getHargaJual())
                        + ", stok " + p.getStokTotal() + ")");
            }
            return baris;
        }, indeks -> {
            if (indeks >= 0 && indeks < saranAktif.size()) {
                txtScan.setText(saranAktif.get(indeks).getBarcode());
                tambahItem();
            }
        });
    }
}
