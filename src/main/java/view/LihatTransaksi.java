package view;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import model.DetailTransaksi;
import model.TagihanTransaksi;
import model.Transaksi;
import controller.TransaksiController;
import util.Format;
import util.GayaTabel;
import util.Pesan;
import util.PilihTanggal;

public class LihatTransaksi extends javax.swing.JPanel {

    private static final int HARI_RIWAYAT = 30;

    private final TransaksiController transaksiController = new TransaksiController();
    private List<Transaksi> daftarTrx = List.of();
    private DefaultTableModel tabelModel;
    private DefaultTableModel tabelItemModel;

    public LihatTransaksi() {
        initComponents();

        txtTglAwal.setText(Format.tanggal(LocalDate.now().minusDays(HARI_RIWAYAT - 1)));
        txtTglAkhir.setText(Format.tanggal(LocalDate.now()));
        PilihTanggal.pasang(txtTglAwal);
        PilihTanggal.pasang(txtTglAkhir);

        tabelModel = new DefaultTableModel(
            new String[]{"ID transaksi", "Jenis", "Total", "Metode", "Waktu"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblRiwayat.setModel(tabelModel);
        GayaTabel.rapikan(tblRiwayat, new int[]{2}, new int[]{1});
        GayaTabel.aturLebar(tblRiwayat, new int[]{95, 105, 110, 110, 240});

        tabelItemModel = new DefaultTableModel(
            new String[]{"Produk", "Qty", "Subtotal"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        tblItem.setModel(tabelItemModel);

        clearDetail();
        tblRiwayat.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) tampilDetail();
        });

        muatData();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlFilter = new javax.swing.JPanel();
        lblCari = new javax.swing.JLabel();
        txtCari = new javax.swing.JTextField();
        lblTglAwal = new javax.swing.JLabel();
        txtTglAwal = new javax.swing.JTextField();
        lblTglAkhir = new javax.swing.JLabel();
        txtTglAkhir = new javax.swing.JTextField();
        lblJenis = new javax.swing.JLabel();
        cmbJenis = new javax.swing.JComboBox<>();
        btnTampilkan = new javax.swing.JButton();
        pnlRiwayat = new javax.swing.JPanel();
        lblRiwayat = new javax.swing.JLabel();
        sepRiwayat = new javax.swing.JSeparator();
        scrollRiwayat = new javax.swing.JScrollPane();
        tblRiwayat = new javax.swing.JTable();
        pnlDetail = new javax.swing.JPanel();
        lblDetail = new javax.swing.JLabel();
        sepJudulDetail = new javax.swing.JSeparator();
        lblIdTrx = new javax.swing.JLabel();
        lblMetaTrx = new javax.swing.JLabel();
        sepMetaDetail = new javax.swing.JSeparator();
        lblJenisRingkas = new javax.swing.JLabel();
        scrollItem = new javax.swing.JScrollPane();
        tblItem = new javax.swing.JTable();
        sepRingkasan = new javax.swing.JSeparator();
        lblSubtotalTeks = new javax.swing.JLabel();
        lblSubtotal = new javax.swing.JLabel();
        lblPromoTeks = new javax.swing.JLabel();
        lblPromo = new javax.swing.JLabel();
        lblTotalTeks = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        lblDiterimaTeks = new javax.swing.JLabel();
        lblDiterima = new javax.swing.JLabel();
        lblKembalianTeks = new javax.swing.JLabel();
        lblKembalian = new javax.swing.JLabel();
        btnStruk = new javax.swing.JButton();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();

        setBackground(new java.awt.Color(242, 245, 250));
        setPreferredSize(new java.awt.Dimension(1095, 805));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlFilter.setBackground(new java.awt.Color(255, 255, 255));
        pnlFilter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlFilter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCari.setBackground(new java.awt.Color(255, 255, 255));
        lblCari.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblCari.setForeground(new java.awt.Color(15, 30, 60));
        lblCari.setText("Cari ID transaksi");
        pnlFilter.add(lblCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 12, 300, 18));

        txtCari.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtCari.setForeground(new java.awt.Color(15, 30, 60));
        txtCari.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtCari.setSelectionColor(new java.awt.Color(220, 229, 245));
        txtCari.addActionListener(this::txtCariActionPerformed);
        pnlFilter.add(txtCari, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 35, 347, 38));

        lblTglAwal.setBackground(new java.awt.Color(255, 255, 255));
        lblTglAwal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTglAwal.setForeground(new java.awt.Color(15, 30, 60));
        lblTglAwal.setText("Tanggal awal");
        pnlFilter.add(lblTglAwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 12, 160, 18));

        txtTglAwal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglAwal.setForeground(new java.awt.Color(15, 30, 60));
        txtTglAwal.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtTglAwal.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlFilter.add(txtTglAwal, new org.netbeans.lib.awtextra.AbsoluteConstraints(374, 35, 162, 38));

        lblTglAkhir.setBackground(new java.awt.Color(255, 255, 255));
        lblTglAkhir.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblTglAkhir.setForeground(new java.awt.Color(15, 30, 60));
        lblTglAkhir.setText("Tanggal akhir");
        pnlFilter.add(lblTglAkhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 12, 160, 18));

        txtTglAkhir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtTglAkhir.setForeground(new java.awt.Color(15, 30, 60));
        txtTglAkhir.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)), javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12)));
        txtTglAkhir.setSelectionColor(new java.awt.Color(220, 229, 245));
        pnlFilter.add(txtTglAkhir, new org.netbeans.lib.awtextra.AbsoluteConstraints(549, 35, 162, 38));

        lblJenis.setBackground(new java.awt.Color(255, 255, 255));
        lblJenis.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblJenis.setForeground(new java.awt.Color(15, 30, 60));
        lblJenis.setText("Jenis transaksi");
        pnlFilter.add(lblJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 12, 180, 18));

        cmbJenis.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        cmbJenis.setForeground(new java.awt.Color(15, 30, 60));
        cmbJenis.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semua", "Penjualan", "Tagihan" }));
        cmbJenis.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        cmbJenis.addActionListener(this::cmbJenisActionPerformed);
        pnlFilter.add(cmbJenis, new org.netbeans.lib.awtextra.AbsoluteConstraints(724, 35, 181, 38));

        btnTampilkan.setBackground(new java.awt.Color(16, 40, 79));
        btnTampilkan.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTampilkan.setForeground(new java.awt.Color(255, 255, 255));
        btnTampilkan.setText("Tampilkan");
        btnTampilkan.setBorderPainted(false);
        btnTampilkan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTampilkan.setFocusPainted(false);
        btnTampilkan.setOpaque(true);
        btnTampilkan.addActionListener(this::btnTampilkanActionPerformed);
        pnlFilter.add(btnTampilkan, new org.netbeans.lib.awtextra.AbsoluteConstraints(917, 35, 112, 38));

        add(pnlFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 87, 1043, 87));

        pnlRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        pnlRiwayat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlRiwayat.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblRiwayat.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblRiwayat.setForeground(new java.awt.Color(15, 30, 60));
        lblRiwayat.setText("Riwayat transaksi");
        pnlRiwayat.add(lblRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 12, 300, 24));
        pnlRiwayat.add(sepRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 68, 709, 2));

        scrollRiwayat.setBackground(new java.awt.Color(255, 255, 255));
        scrollRiwayat.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));

        tblRiwayat.setForeground(new java.awt.Color(15, 30, 60));
        tblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID transaksi", "Jenis", "Total", "Metode", "Waktu"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRiwayat.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblRiwayat.setFillsViewportHeight(true);
        tblRiwayat.setGridColor(new java.awt.Color(226, 231, 240));
        tblRiwayat.setIntercellSpacing(new java.awt.Dimension(0, 1));
        tblRiwayat.setRowHeight(45);
        tblRiwayat.setSelectionBackground(new java.awt.Color(232, 240, 254));
        tblRiwayat.setSelectionForeground(new java.awt.Color(15, 30, 60));
        tblRiwayat.setShowGrid(false);
        tblRiwayat.setShowHorizontalLines(true);
        scrollRiwayat.setViewportView(tblRiwayat);

        pnlRiwayat.add(scrollRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 83, 679, 306));

        add(pnlRiwayat, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 191, 709, 439));

        pnlDetail.setBackground(new java.awt.Color(255, 255, 255));
        pnlDetail.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlDetail.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblDetail.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblDetail.setForeground(new java.awt.Color(15, 30, 60));
        lblDetail.setText("Detail transaksi");
        pnlDetail.add(lblDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 14, 200, 24));
        pnlDetail.add(sepJudulDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 316, 2));

        lblIdTrx.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblIdTrx.setForeground(new java.awt.Color(15, 30, 60));
        pnlDetail.add(lblIdTrx, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 66, 280, 22));

        lblMetaTrx.setForeground(new java.awt.Color(100, 116, 139));
        pnlDetail.add(lblMetaTrx, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 92, 280, 18));
        pnlDetail.add(sepMetaDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 120, 280, 2));

        lblJenisRingkas.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblJenisRingkas.setForeground(new java.awt.Color(15, 30, 60));
        pnlDetail.add(lblJenisRingkas, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 130, 280, 18));

        scrollItem.setBackground(new java.awt.Color(255, 255, 255));
        scrollItem.setBorder(null);

        tblItem.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblItem.setForeground(new java.awt.Color(100, 116, 139));
        tblItem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Produk", "Qty", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblItem.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        tblItem.setFocusable(false);
        tblItem.setRowHeight(28);
        tblItem.setRowSelectionAllowed(false);
        tblItem.setSelectionBackground(new java.awt.Color(255, 255, 255));
        scrollItem.setViewportView(tblItem);

        pnlDetail.add(scrollItem, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 152, 280, 120));
        pnlDetail.add(sepRingkasan, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 278, 280, 2));

        lblSubtotalTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSubtotalTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblSubtotalTeks.setText("Subtotal");
        pnlDetail.add(lblSubtotalTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 288, 150, 20));

        lblSubtotal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblSubtotal.setForeground(new java.awt.Color(15, 30, 60));
        lblSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSubtotal.setText("Rp0");
        pnlDetail.add(lblSubtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 288, 148, 20));

        lblPromoTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblPromoTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblPromoTeks.setText("Potongan promo");
        pnlDetail.add(lblPromoTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 317, 150, 20));

        lblPromo.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblPromo.setForeground(new java.awt.Color(31, 122, 77));
        lblPromo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPromo.setText("Rp0");
        pnlDetail.add(lblPromo, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 317, 148, 20));

        lblTotalTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblTotalTeks.setForeground(new java.awt.Color(15, 30, 60));
        lblTotalTeks.setText("Total bayar");
        pnlDetail.add(lblTotalTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 346, 150, 20));

        lblTotal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblTotal.setForeground(new java.awt.Color(15, 30, 60));
        lblTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblTotal.setText("Rp0");
        pnlDetail.add(lblTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 346, 148, 22));

        lblDiterimaTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblDiterimaTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblDiterimaTeks.setText("Uang diterima");
        pnlDetail.add(lblDiterimaTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 376, 150, 20));

        lblDiterima.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblDiterima.setForeground(new java.awt.Color(15, 30, 60));
        lblDiterima.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDiterima.setText("Rp0");
        pnlDetail.add(lblDiterima, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 376, 148, 20));

        lblKembalianTeks.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblKembalianTeks.setForeground(new java.awt.Color(100, 116, 139));
        lblKembalianTeks.setText("Kembalian");
        pnlDetail.add(lblKembalianTeks, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 405, 150, 20));

        lblKembalian.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        lblKembalian.setForeground(new java.awt.Color(31, 122, 77));
        lblKembalian.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblKembalian.setText("Rp0");
        pnlDetail.add(lblKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 405, 148, 20));

        btnStruk.setBackground(new java.awt.Color(16, 40, 79));
        btnStruk.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnStruk.setForeground(new java.awt.Color(255, 255, 255));
        btnStruk.setText("Lihat / Cetak Struk");
        btnStruk.setBorderPainted(false);
        btnStruk.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStruk.setFocusPainted(false);
        btnStruk.setOpaque(true);
        btnStruk.addActionListener(this::btnStrukActionPerformed);
        pnlDetail.add(btnStruk, new org.netbeans.lib.awtextra.AbsoluteConstraints(18, 437, 280, 40));

        add(pnlDetail, new org.netbeans.lib.awtextra.AbsoluteConstraints(754, 191, 316, 591));

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(15, 30, 60));
        lblJudul.setText("Lihat Transaksi");
        add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 18, 500, 40));

        lblSub.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        lblSub.setForeground(new java.awt.Color(100, 116, 139));
        lblSub.setText("Telusuri transaksi Anda dan cetak kembali struk bila diperlukan.");
        add(lblSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(26, 60, 700, 20));
    }// </editor-fold>//GEN-END:initComponents

    private void txtCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariActionPerformed
        muatData();
    }//GEN-LAST:event_txtCariActionPerformed

    private void cmbJenisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbJenisActionPerformed
        muatData();
    }//GEN-LAST:event_cmbJenisActionPerformed

    private void btnTampilkanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTampilkanActionPerformed
        muatData();
    }//GEN-LAST:event_btnTampilkanActionPerformed

    private void btnStrukActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStrukActionPerformed
        cetakStruk();
    }//GEN-LAST:event_btnStrukActionPerformed


    private javax.swing.JButton btnStruk;
    private javax.swing.JButton btnTampilkan;
    private javax.swing.JComboBox<String> cmbJenis;
    private javax.swing.JLabel lblCari;
    private javax.swing.JLabel lblDetail;
    private javax.swing.JLabel lblDiterima;
    private javax.swing.JLabel lblDiterimaTeks;
    private javax.swing.JLabel lblIdTrx;
    private javax.swing.JLabel lblJenis;
    private javax.swing.JLabel lblJenisRingkas;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblKembalian;
    private javax.swing.JLabel lblKembalianTeks;
    private javax.swing.JLabel lblMetaTrx;
    private javax.swing.JLabel lblPromo;
    private javax.swing.JLabel lblPromoTeks;
    private javax.swing.JLabel lblRiwayat;
    private javax.swing.JLabel lblSub;
    private javax.swing.JLabel lblSubtotal;
    private javax.swing.JLabel lblSubtotalTeks;
    private javax.swing.JLabel lblTglAkhir;
    private javax.swing.JLabel lblTglAwal;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JLabel lblTotalTeks;
    private javax.swing.JPanel pnlDetail;
    private javax.swing.JPanel pnlFilter;
    private javax.swing.JPanel pnlRiwayat;
    private javax.swing.JScrollPane scrollItem;
    private javax.swing.JScrollPane scrollRiwayat;
    private javax.swing.JSeparator sepJudulDetail;
    private javax.swing.JSeparator sepMetaDetail;
    private javax.swing.JSeparator sepRingkasan;
    private javax.swing.JSeparator sepRiwayat;
    private javax.swing.JTable tblItem;
    private javax.swing.JTable tblRiwayat;
    private javax.swing.JTextField txtCari;
    private javax.swing.JTextField txtTglAkhir;
    private javax.swing.JTextField txtTglAwal;

    private void muatData() {
        LocalDate awal;
        LocalDate akhir;
        try {
            awal = Format.bacaTanggal(txtTglAwal.getText());
            akhir = Format.bacaTanggal(txtTglAkhir.getText());
        } catch (DateTimeParseException ex) {
            Pesan.peringatan(this, "Tanggal harus berformat dd/MM/yyyy.");
            return;
        }
        if (akhir.isBefore(awal)) {
            Pesan.peringatan(this, "Tanggal akhir harus sama atau setelah tanggal awal.");
            return;
        }

        String jenis = String.valueOf(cmbJenis.getSelectedItem());
        String keyword = txtCari.getText().trim();
        try {
            daftarTrx = transaksiController.cari(awal, akhir,
                "Semua".equals(jenis) ? null : jenis,
                keyword.isEmpty() ? null : keyword);

            tabelModel.setRowCount(0);
            for (Transaksi t : daftarTrx) {
                tabelModel.addRow(new Object[]{
                    String.valueOf(t.getIdTransaksi()),
                    Format.judul(t.getJenis()),
                    Format.rupiah(t.getTotalBayar()),
                    Format.metode(t.getMetodeBayar()),
                    Format.tanggalJam(t.getWaktu())
                });
            }
            clearDetail();
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    private void tampilDetail() {
        int baris = tblRiwayat.getSelectedRow();
        if (baris < 0 || baris >= daftarTrx.size()) return;
        try {
            Transaksi trx = daftarTrx.get(baris);
            lblIdTrx.setText(String.valueOf(trx.getIdTransaksi()));
            lblMetaTrx.setText(Format.tanggalJam(trx.getWaktu()) + " ("
                    + Format.metode(trx.getMetodeBayar()) + ")");
            lblJenisRingkas.setText("Jenis: " + Format.judul(trx.getJenis()));

            tabelItemModel.setRowCount(0);
            if (trx.isPenjualan()) {
                for (DetailTransaksi d : transaksiController.cariDetail(trx.getIdTransaksi())) {
                    tabelItemModel.addRow(new Object[]{
                        d.getNamaProduk(), d.getJumlah(), Format.rupiah(d.getSubtotal())
                    });
                }
            } else {
                TagihanTransaksi tg = transaksiController.cariTagihan(trx.getIdTransaksi());
                if (tg != null) {
                    tabelItemModel.addRow(new Object[]{
                        Format.judul(tg.getJenisLayanan()) + " (" + Format.judul(tg.getProvider()) + ")",
                        1, Format.rupiah(tg.getNominal())
                    });
                }
            }

            lblSubtotal.setText(Format.rupiah(trx.getSubtotal()));
            lblPromo.setText(trx.getTotalDiskon() > 0 ? "\u2212" + Format.rupiah(trx.getTotalDiskon()) : "Rp0");
            lblTotal.setText(Format.rupiah(trx.getTotalBayar()));
            lblDiterima.setText(Format.rupiah(trx.getUangDiterima()));
            lblKembalian.setText(Format.rupiah(trx.getKembalian()));
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    private void cetakStruk() {
        int baris = tblRiwayat.getSelectedRow();
        if (baris < 0 || baris >= daftarTrx.size()) {
            Pesan.peringatan(this, "Pilih transaksi terlebih dahulu.");
            return;
        }
        try {
            Transaksi trx = daftarTrx.get(baris);
            new StrukDialog(SwingUtilities.getWindowAncestor(this),
                    "Struk #" + trx.getIdTransaksi(),
                    transaksiController.buatStruk(trx)).setVisible(true);
        } catch (SQLException ex) {
            Pesan.gagalDatabase(this, ex);
        }
    }

    private void clearDetail() {
        lblIdTrx.setText(""); lblMetaTrx.setText("");
        lblJenisRingkas.setText(""); tabelItemModel.setRowCount(0);
        lblSubtotal.setText("Rp0"); lblPromo.setText("Rp0");
        lblTotal.setText("Rp0"); lblDiterima.setText("Rp0");
        lblKembalian.setText("Rp0");
    }
}
