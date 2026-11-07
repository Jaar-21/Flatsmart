package view;

import java.awt.print.PrinterException;
import util.Pesan;

public class StrukDialog extends javax.swing.JDialog {

    public StrukDialog(java.awt.Window parent, String judul, String isi) {
        super(parent, judul, java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        initComponents();
        setTitle(judul);
        lblJudul.setText(judul);
        txtStruk.setText(isi);
        txtStruk.setCaretPosition(0);

        java.awt.Insets in = getInsets();
        setPreferredSize(new java.awt.Dimension(390 + in.left + in.right, 640 + in.top + in.bottom));
        pack();
        setLocationRelativeTo(parent);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlHeader = new javax.swing.JPanel();
        lblJudul = new javax.swing.JLabel();
        pnlBody = new javax.swing.JPanel();
        scrollStruk = new javax.swing.JScrollPane();
        txtStruk = new javax.swing.JTextArea();
        pnlFooter = new javax.swing.JPanel();
        btnCetak = new javax.swing.JButton();
        btnTutup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("FlatSmart: Struk Transaksi");
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pnlHeader.setBackground(new java.awt.Color(16, 40, 79));
        pnlHeader.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblJudul.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblJudul.setForeground(new java.awt.Color(255, 255, 255));
        lblJudul.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblJudul.setText("Struk Transaksi");
        pnlHeader.add(lblJudul, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 388, 26));

        getContentPane().add(pnlHeader, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 388, 70));

        pnlBody.setBackground(new java.awt.Color(255, 255, 255));
        pnlBody.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollStruk.setBorder(null);

        txtStruk.setEditable(false);
        txtStruk.setBackground(new java.awt.Color(248, 251, 253));
        txtStruk.setColumns(40);
        txtStruk.setFont(new java.awt.Font("Monospaced", 0, 13)); // NOI18N
        txtStruk.setForeground(new java.awt.Color(15, 30, 60));
        txtStruk.setRows(26);
        scrollStruk.setViewportView(txtStruk);

        pnlBody.add(scrollStruk, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 360, 476));

        getContentPane().add(pnlBody, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 388, 504));

        pnlFooter.setBackground(new java.awt.Color(242, 245, 250));
        pnlFooter.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(226, 231, 240)));
        pnlFooter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCetak.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnCetak.setForeground(new java.awt.Color(16, 40, 79));
        btnCetak.setText("Cetak Struk");
        btnCetak.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(214, 222, 236)));
        btnCetak.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCetak.setFocusPainted(false);
        btnCetak.setOpaque(true);
        btnCetak.addActionListener(this::btnCetakActionPerformed);
        pnlFooter.add(btnCetak, new org.netbeans.lib.awtextra.AbsoluteConstraints(14, 14, 175, 38));

        btnTutup.setBackground(new java.awt.Color(16, 40, 79));
        btnTutup.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnTutup.setForeground(new java.awt.Color(255, 255, 255));
        btnTutup.setText("Tutup");
        btnTutup.setBorderPainted(false);
        btnTutup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnTutup.setFocusPainted(false);
        btnTutup.setOpaque(true);
        btnTutup.addActionListener(this::btnTutupActionPerformed);
        pnlFooter.add(btnTutup, new org.netbeans.lib.awtextra.AbsoluteConstraints(199, 14, 175, 38));

        getContentPane().add(pnlFooter, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 574, 388, 64));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTutupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTutupActionPerformed
        dispose();
    }//GEN-LAST:event_btnTutupActionPerformed

    private void btnCetakActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCetakActionPerformed
        try {
            java.text.MessageFormat header =
                new java.text.MessageFormat("Halaman {0,number,integer}");
            if (!txtStruk.print(null, header)) {
                Pesan.info(this, "Cetak struk dibatalkan.");
            }
        } catch (PrinterException ex) {
            Pesan.galat(this, "Gagal mencetak struk.", ex);
        }
    }//GEN-LAST:event_btnCetakActionPerformed

    private javax.swing.JButton btnCetak;
    private javax.swing.JButton btnTutup;
    private javax.swing.JLabel lblJudul;
    private javax.swing.JPanel pnlBody;
    private javax.swing.JPanel pnlFooter;
    private javax.swing.JPanel pnlHeader;
    private javax.swing.JScrollPane scrollStruk;
    private javax.swing.JTextArea txtStruk;
}
