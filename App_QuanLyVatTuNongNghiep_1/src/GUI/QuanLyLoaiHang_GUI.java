/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Model.ColorCustom;
import Model.DBQuanLyVatTuNongNghiep;
import Model.LoaiHang;
import Model.NhaCungCap;
import Model.NhanVien;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.Notification;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;
import textfield.EventCallBack;
import textfield.EventTextField;
import textfield.TextFieldAnimation;

/**
 *
 * @author vanhu
 */
public class QuanLyLoaiHang_GUI extends javax.swing.JPanel {

    DBQuanLyVatTuNongNghiep db = null;
    DefaultTableModel dtm = null;
    TextFieldAnimation txt_timKiem = null;
    NhanVien user = null;
    int idxSua = -1;
    Vector header = new Vector();
   
    public QuanLyLoaiHang_GUI(DBQuanLyVatTuNongNghiep db, NhanVien user,TextFieldAnimation txt_timKiem ) {
        initComponents();
        this.db = db;
        this.user = user;
        this.txt_timKiem = txt_timKiem;
        khoiTao();
    }

    public String taoMaLoaiHangMoi()
    {
        int sl = db.getLoaiHangs().size()+1;
        do
        {
            String maLoaiMoi = "LH00"+String.valueOf(sl++);
            if(db.getLoaiHangs().get(l->l.getMaLoaiHang().equalsIgnoreCase(maLoaiMoi))==null)
                return maLoaiMoi;
        }while(true);
    }
    public void khoiTao()
    {
        dtm = new DefaultTableModel();        
        tbl_loaiHang.setModel(dtm);
        header.add("Mã Loại");
        header.add("Tên Loại");
        txt_maLoai.setEditable(false);
        btn_xoa.setEnabled(false);
        btn_huy.setEnabled(false);
        txt_maLoai.setText(taoMaLoaiHangMoi());
        dtm.setDataVector(db.getLoaiHangs().getData(), header);
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        
        
        txt_timKiem.addEvent(new EventTextField(){
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    String value = txt_timKiem.getText().toLowerCase();
                    dtm.setDataVector(db.getLoaiHangs().getData(lh->lh.getMaLoaiHang().toLowerCase().contains(value)
                            ||lh.getTenLoaiHang().toLowerCase().contains(value)), NhaCungCap.getFields());                    
                    Thread.sleep(1000);
                    call.done();
                } catch (InterruptedException ex) {
                    Logger.getLogger(QuanLyHoaDon_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void onCancel() {
                
            }
            
        });
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btn_capnhat = new button.Button();
        btn_xoa = new button.Button();
        btn_huy = new button.Button();
        txt_maLoai = new textfield_2.TextField();
        txt_tenLoai = new textfield_2.TextField();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_loaiHang = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Danh mục loại hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã loại");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên loại");

        btn_capnhat.setBackground(new java.awt.Color(12, 87, 118));
        btn_capnhat.setForeground(new java.awt.Color(255, 255, 255));
        btn_capnhat.setText("Thêm");
        btn_capnhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_capnhat.setShadowColor(new java.awt.Color(0, 51, 255));
        btn_capnhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capnhatActionPerformed(evt);
            }
        });

        btn_xoa.setBackground(new java.awt.Color(45, 153, 174));
        btn_xoa.setForeground(new java.awt.Color(255, 255, 255));
        btn_xoa.setText("Xóa");
        btn_xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoa.setShadowColor(new java.awt.Color(0, 204, 255));
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_huy.setText("Hủy");
        btn_huy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_huy.setShadowColor(new java.awt.Color(204, 255, 255));
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });

        txt_maLoai.setBackground(new java.awt.Color(204, 255, 255));
        txt_maLoai.setShadowColor(new java.awt.Color(153, 153, 153));

        txt_tenLoai.setBackground(new java.awt.Color(204, 255, 255));
        txt_tenLoai.setForeground(new java.awt.Color(0, 0, 0));
        txt_tenLoai.setShadowColor(new java.awt.Color(153, 153, 153));
        txt_tenLoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tenLoaiKeyReleased(evt);
            }
        });

        tbl_loaiHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_loaiHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_loaiHang.setRowHeight(30);
        tbl_loaiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_loaiHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_loaiHang);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jLabel1)
                .addGap(34, 34, 34)
                .addComponent(txt_maLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(txt_tenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(302, 302, 302)
                .addComponent(btn_capnhat, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txt_maLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_capnhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1500, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(263, 263, 263)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(326, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 900, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(93, 93, 93)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(93, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    
    void resetText()
    {
        txt_maLoai.setText(taoMaLoaiHangMoi());
        txt_tenLoai.setText("");
        btn_xoa.setEnabled(false);
        btn_huy.setEnabled(false);
        btn_capnhat.setText("Thêm");
        idxSua=-1;
   }   
    
    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        String maLoai = txt_maLoai.getText().trim();
        String tenLoai = txt_tenLoai.getText().trim();
        if(tenLoai.isEmpty())
        {
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Tên loại hàng không được để trống!!!");
            panel.showNotification();
            //JOptionPane.showMessageDialog(this, "Tên phân nhà cung cấp không được để trống!!!");
            txt_tenLoai.requestFocus();
            return;
        }
        else
        if(idxSua ==-1 && db.getLoaiHangs().get(t->t.getTenLoaiHang().equalsIgnoreCase(tenLoai)) != null)
        {
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Tên loại hàng này đã tồn tại!!!");
            panel.showNotification();
            //JOptionPane.showMessageDialog(this, "Tên nhà cung cấp này đã tồn tại!!!");
            txt_tenLoai.setText("");
            txt_tenLoai.requestFocus();
            return;
        }

        if(JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật dữ liệu","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
            boolean chk;
            LoaiHang loaiHang;
            if(idxSua == -1)
            {
                loaiHang = new LoaiHang(maLoai,tenLoai);
                chk =  db.getLoaiHangs().insert(loaiHang);
            }
            else
            {
                loaiHang = db.getLoaiHangs().get(t->t.getMaLoaiHang().trim().equalsIgnoreCase(maLoai));
                loaiHang.setTenLoaiHang(tenLoai);
                chk = db.getLoaiHangs().update(loaiHang);
            }
            if(chk)
            {
                if(idxSua==-1)
                dtm.addRow(loaiHang.getParameter());
                else
                {
                    dtm.removeRow(idxSua);
                    dtm.insertRow(idxSua, loaiHang.getParameter());
                }
                //JOptionPane.showMessageDialog(this, "Thao tác thành công");
                Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Thao tác thành công");
                panel.showNotification();
                resetText();
            }
            else
            {
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Đã xảy ra lỗi, vui lòng thử lại!");
                panel.showNotification();
            }
            // JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi, vui lòng thử lại!");
        }
    }//GEN-LAST:event_btn_capnhatActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        String tenNCC = dtm.getValueAt(idxSua, 1).toString();
        if(JOptionPane.showConfirmDialog(this, "Xác nhận xóa nhà cung cấp '"+tenNCC+"'?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
            String maLoai = txt_maLoai.getText();
            LoaiHang loaiHang = db.getLoaiHangs().get(t->t.getMaLoaiHang().equalsIgnoreCase(maLoai));
            if(db.getLoaiHangs().delete(loaiHang))
            {
                dtm.removeRow(idxSua);
                resetText();
                Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT,"Xóa thành công!!!");
                panel.showNotification();
                // JOptionPane.showMessageDialog(this, "Xóa thành công!!!");
            }
            else
            {

                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT,"Xóa không thành công!!!");
                panel.showNotification();
            }
            //JOptionPane.showMessageDialog(this, "Xóa không thành công!!!");
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
        // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this, "Xác nhận hoàn tác?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
            resetText();
        }
    }//GEN-LAST:event_btn_huyActionPerformed

    public void checkInputLoaiHang()
    {
        if(txt_tenLoai.getText().trim().isEmpty())
            btn_huy.setEnabled(false);
        else
        {
            btn_huy.setEnabled(true);
            btn_huy.setBackground(ColorCustom.TRANG);
        }
    }
    private void txt_tenLoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenLoaiKeyReleased
        // TODO add your handling code here:
        checkInputLoaiHang();
    }//GEN-LAST:event_txt_tenLoaiKeyReleased

    private void tbl_loaiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_loaiHangMouseClicked
        // TODO add your handling code here:
        idxSua = tbl_loaiHang.getSelectedRow();
        txt_maLoai.setText(dtm.getValueAt(idxSua, 0).toString());
        txt_tenLoai.setText(dtm.getValueAt(idxSua, 1).toString());
        btn_capnhat.setText("Cập nhật");
        btn_xoa.setEnabled(true);
        btn_huy.setEnabled(true);
        btn_xoa.setBackground(ColorCustom.XANH_NHAT);
        btn_huy.setBackground(ColorCustom.TRANG);
    }//GEN-LAST:event_tbl_loaiHangMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btn_capnhat;
    private button.Button btn_huy;
    private button.Button btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tbl_loaiHang;
    private textfield_2.TextField txt_maLoai;
    private textfield_2.TextField txt_tenLoai;
    // End of variables declaration//GEN-END:variables
}
