package view;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import model.PenjualanProduk;
import util.Format;
import util.GayaTabel;

public class DetailPenjualanProdukDialog extends javax.swing.JDialog {

    private static final int[] LEBAR_KOLOM = {152, 88, 116, 66, 84, 50, 96, 92, 102};

    public DetailPenjualanProdukDialog(java.awt.Window parent, String namaProduk, String barcode,
                                       LocalDate awal, LocalDate akhir,
                                       List<PenjualanProduk> riwayat) {
        super(parent, "FlatSmart: Detail Transaksi Produk",
              java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();

        lblJudul.setText(Format.judul(namaProduk));
        lblSub.setText("Barcode " + barcode + ", periode " + Format.tanggal(awal)
                + " sampai " + Format.tanggal(akhir));
        muatTabel(riwayat);

        java.awt.Insets in = getInsets();
        setPreferredSize(new java.awt.Dimension(982 + in.left + in.right, 622 + in.top + in.bottom));
        pack();
        bulatkanTinggiTabel();
        setLocationRelativeTo(parent);
    }

    private void bulatkanTinggiTabel() {
        int tinggiBaris = tblRiwayat.getRowHeight();
        if (tinggiBaris <= 0) return;
        int ruang = scrollTabel.getViewport().getHeight();
        int barisPenuh = (ruang / tinggiBaris) * tinggiBaris;
        if (ruang == barisPenuh) return;
        int sisa = ruang - barisPenuh;
        java.awt.Container badan = scrollTabel.getParent();
        badan.remove(scrollTabel);
        badan.add(scrollTabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                12, 12, scrollTabel.getWidth(), scrollTabel.getHeight() - sisa));
        java.awt.Container isi = getContentPane();
        isi.remove(pnlBody);
        isi.remove(pnlFooter);
        isi.add(pnlBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                0, 86, 980, pnlBody.getHeight() - sisa));
        isi.add(pnlFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(
                0, 550 - sisa, 980, pnlFooter.getHeight()));
        java.awt.Insets in = getInsets();
        setPreferredSize(new java.awt.Dimension(982 + in.left + in.right,
                622 - sisa + in.top + in.bottom));
        pack();
        setLocationRelativeTo(getOwner());
    }

    private void muatTabel(List<PenjualanProduk> riwayat) {
        DefaultTableModel model = new DefaultTableModel(
            new String[]{"Waktu", "ID Transaksi", "Kasir", "Bayar", "Batch", "Unit",
                "Harga Satuan", "Potongan", "Subtotal"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        for (PenjualanProduk p : riwayat) {
            model.addRow(new Object[]{
                Format.tanggalJam(p.getWaktu()), p.getIdTransaksi(),
                Format.judul(p.getNamaKasir()),
                Format.metode(p.getMetodeBayar()), p.getIdStok(), p.getJumlah(),
                Format.rupiah(p.getHargaSatuan()),
                Format.rupiah(p.getPotongan()), Format.rupiah(p.getSubtotal())
            });
        }
        tblRiwayat.setModel(model);
        tblRiwayat.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        GayaTabel.rapikan(tblRiwayat, new int[]{6, 7, 8}, new int[]{1, 3, 4, 5});
        for (int i = 0; i < tblRiwayat.getColumnCount() && i < LEBAR_KOLOM.length; i++) {
            tblRiwayat.getColumnModel().getColumn(i).setPreferredWidth(LEBAR_KOLOM[i]);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        lblSub = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        scrollTabel = new javax.swing.JScrollPane();
        tblRiwayat = new javax.swing.JTable();
        pnlFooter = new javax.swing.JPanel();
        btnTutup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlatSmart: Detail Transaksi Produk");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(16, 40, 79));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 28)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(255, 255, 255));
        lblJudul.setText("Detail Transaksi Produk");
        pnlHeader.add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 16, 800, 26));

        lblSub.setForeground(new java.awt.Color(170, 190, 225));
        lblSub.setText("Riwayat penjualan produk pada rentang tanggal yang dipilih.");
        pnlHeader.add(lblSub, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, 48, 800, 20));

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 86));

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollTabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 16, 10, 16));

        tblRiwayat.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        tblRiwayat.setForeground(new java.awt.Color(15, 30, 60));
        tblRiwayat.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Waktu", "ID Transaksi", "Kasir", "Bayar", "Batch", "Unit", "Harga Satuan", "Potongan", "Subtotal"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblRiwayat.getTableHeader().setReorderingAllowed(false);
        scrollTabel.setViewportView(tblRiwayat);

        pnlBody.add(scrollTabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 956, 440));

        getContentPane().add(pnlBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 86, 980, 464));

        pnlFooter.setBackground(new java.awt.Color(242, 245, 250));
        pnlFooter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlFooter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnTutup.setBackground(new java.awt.Color(16, 40, 79));
        btnTutup.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTutup.setForeground(new java.awt.Color(255, 255, 255));
        btnTutup.setText("Tutup");
        btnTutup.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        btnTutup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTutup.setFocusPainted(false);
        btnTutup.setOpaque(true);
        btnTutup.addActionListener(this::btnTutupActionPerformed);
        pnlFooter.add(btnTutup, new org.netbeans.lib.awtextra.AbsoluteConstraints(838, 16, 120, 38));

        getContentPane().add(pnlFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 550, 980, 70));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupActionPerformed
        dispose();
    }//GEN-LAST:event_btnTutupActionPerformed

    private javax.swing.JButton btnTutup;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JLabel lblSub;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JScrollPane scrollTabel;
    private javax.swing.JTable tblRiwayat;
}
