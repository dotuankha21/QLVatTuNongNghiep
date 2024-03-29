/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Model.DBQuanLyVatTuNongNghiep;
import Model.NhanVien;
import java.awt.Color;
import javaswingdev.Notification;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Admin
 */
public class DoiMatKhau_GUI extends javax.swing.JFrame {

    /**
     * Creates new form DoiMatKhau_GUI
     */
    DBQuanLyVatTuNongNghiep db = null;
    NhanVien user = null;
    public DoiMatKhau_GUI(DBQuanLyVatTuNongNghiep db, NhanVien user) {
        initComponents();
        this.setLocationRelativeTo(null);
        this.db = db;
        this.user = user;
        getContentPane().setBackground(new Color(255, 255, 255));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_matKhauCu = new textfield.PasswordField();
        txt_matKhauMoi = new textfield.PasswordField();
        txt_nhapLaiMatKhauMoi = new textfield.PasswordField();
        btn_luu = new button.Button();
        btn_huy = new button.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        txt_matKhauCu.setLabelText("Nhập mật khẩu cũ");
        txt_matKhauCu.setShowAndHide(true);

        txt_matKhauMoi.setLabelText("Nhập mật khẩu mới");
        txt_matKhauMoi.setShowAndHide(true);

        txt_nhapLaiMatKhauMoi.setLabelText("Nhập lại mật khẩu mới");
        txt_nhapLaiMatKhauMoi.setShowAndHide(true);

        btn_luu.setBackground(new java.awt.Color(12, 87, 118));
        btn_luu.setForeground(new java.awt.Color(255, 255, 255));
        btn_luu.setText("Lưu");
        btn_luu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_luu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_luuActionPerformed(evt);
            }
        });

        btn_huy.setText("Hủy");
        btn_huy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_nhapLaiMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_matKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_matKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addComponent(txt_matKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_matKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_nhapLaiMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_luu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(37, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_luuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_luuActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
         String mauKhau = txt_matKhauCu.getText().trim();
        System.out.println(mauKhau);
        if(this.user.getMatKhau().equals(mauKhau))
        {
            String matKhauMoi = txt_matKhauMoi.getText().trim();
            if(matKhauMoi.isEmpty())
            {
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Mặt khẩu mới không được để trống");
            panel.showNotification();
                //JOptionPane.showMessageDialog(this, "Mặt khẩu mới không được để trống");
                txt_matKhauMoi.setText("");                
                txt_matKhauMoi.requestFocus();
            }
            String nlMatKhau = txt_nhapLaiMatKhauMoi.getText().trim();            
            if(matKhauMoi.equals(nlMatKhau))
            {
                if(JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật dữ liệu","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
                {
                    this.user.setMatKhau(matKhauMoi);
                    if(this.db.getNhanViens().update(user))
                    {
                        //JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công");
                        Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Đổi mật khẩu thành công");
            panel.showNotification();
                        this.setVisible(false);
                    }
                }
            }
            else
            {
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Mặt khẩu mới không trùng khớp");
            panel.showNotification();
                //JOptionPane.showMessageDialog(this, "Mặt khẩu mới không trùng khớp");
                txt_matKhauMoi.setText("");
                txt_nhapLaiMatKhauMoi.setText("");
                txt_matKhauMoi.requestFocus();
            }
        }
        else
        {
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Mặt khẩu cũ không chính xác");
            panel.showNotification();
            //JOptionPane.showMessageDialog(this, "Mặt khẩu cũ không chính xác");
            txt_matKhauCu.setText("");            
            txt_matKhauCu.requestFocus();
        }
    }//GEN-LAST:event_btn_luuActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this, "Xác nhận thoát và không lưu thay đổi?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)        
            this.setVisible(false);
    }//GEN-LAST:event_btn_huyActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(DoiMatKhau_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(DoiMatKhau_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(DoiMatKhau_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(DoiMatKhau_GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new DoiMatKhau_GUI().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btn_huy;
    private button.Button btn_luu;
    private textfield.PasswordField txt_matKhauCu;
    private textfield.PasswordField txt_matKhauMoi;
    private textfield.PasswordField txt_nhapLaiMatKhauMoi;
    // End of variables declaration//GEN-END:variables
}
