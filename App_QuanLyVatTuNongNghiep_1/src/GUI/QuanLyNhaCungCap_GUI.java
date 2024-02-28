/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Model.ColorCustom;
import Model.DBQuanLyVatTuNongNghiep;
import Model.NhaCungCap;
import Model.NhanVien;
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
 * @author Admin
 */
public class QuanLyNhaCungCap_GUI extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyNhaCungCap_GUI
     */
    DBQuanLyVatTuNongNghiep db = null;
    DefaultTableModel dtm = null;
    TextFieldAnimation txt_timKiem = null;
    NhanVien user = null;
    int idxSuaNCC = -1;
    public QuanLyNhaCungCap_GUI(DBQuanLyVatTuNongNghiep db,NhanVien user,TextFieldAnimation txt_timKiem) {
        initComponents();
        this.db = db;
        this.user = user;
        this.txt_timKiem = txt_timKiem;
        dtm = new DefaultTableModel();        
        tbl_nhaCungCap.setModel(dtm);
        txt_maNCC.setEditable(false);
        btn_xoa.setEnabled(false);
        btn_huy.setEnabled(false);
        txt_maNCC.setText(taoMaNhaCungCapMoi());
        dtm.setDataVector(db.getNhaCungCaps().getData(), NhaCungCap.getFields());
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        
        
        txt_timKiem.addEvent(new EventTextField(){
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    String value = txt_timKiem.getText().toLowerCase();
                    dtm.setDataVector(db.getNhaCungCaps().getData(ncc->ncc.getMaNCC().toLowerCase().contains(value)
                            ||ncc.getTenNCC().toLowerCase().contains(value)
                            ||ncc.getDiaChi().toLowerCase().contains(value)                            
                            ||String.valueOf(ncc.getSoDienThoai()).contains(value)), NhaCungCap.getFields());                    
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
    String taoMaNhaCungCapMoi()
    {
        int sl = 1;
        while(true)
        {
            String maNCC = "NCC00"+String.valueOf(db.getNhaCungCaps().size()+sl++);
            if(db.getNhaCungCaps().get(t->t.getMaNCC().equalsIgnoreCase(maNCC))==null)
                return maNCC;
        }                    
   }
    
    void resetText()
    {
        txt_maNCC.setText(taoMaNhaCungCapMoi());
        txt_diaChi.setText("");
        txt_tenNCC.setText("");
        txt_soDienThoai.setText("");
        btn_xoa.setEnabled(false);
        btn_huy.setEnabled(false);
        btn_capnhat.setText("Thêm");
        idxSuaNCC=-1;
   }   
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableCustom1 = new table.TableCustom();
        hoverIndex1 = new table.HoverIndex();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btn_capnhat = new button.Button();
        btn_xoa = new button.Button();
        btn_huy = new button.Button();
        txt_soDienThoai = new textfield_2.TextField();
        txt_diaChi = new textfield_2.TextField();
        txt_maNCC = new textfield_2.TextField();
        txt_tenNCC = new textfield_2.TextField();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_nhaCungCap = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Danh mục nhà cung cấp", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 204, 204))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Mã nhà cung cấp");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Đại chỉ");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tên nhà cung cấp");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Số điện thoại");

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

        txt_soDienThoai.setBackground(new java.awt.Color(204, 255, 255));
        txt_soDienThoai.setShadowColor(new java.awt.Color(153, 153, 153));
        txt_soDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_soDienThoaiKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_soDienThoaiKeyTyped(evt);
            }
        });

        txt_diaChi.setBackground(new java.awt.Color(204, 255, 255));
        txt_diaChi.setShadowColor(new java.awt.Color(153, 153, 153));
        txt_diaChi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_diaChiKeyReleased(evt);
            }
        });

        txt_maNCC.setBackground(new java.awt.Color(204, 255, 255));
        txt_maNCC.setShadowColor(new java.awt.Color(153, 153, 153));

        txt_tenNCC.setBackground(new java.awt.Color(204, 255, 255));
        txt_tenNCC.setForeground(new java.awt.Color(0, 0, 0));
        txt_tenNCC.setShadowColor(new java.awt.Color(153, 153, 153));
        txt_tenNCC.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_tenNCCKeyReleased(evt);
            }
        });

        tbl_nhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_nhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_nhaCungCap.setRowHeight(30);
        tbl_nhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_nhaCungCap);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_maNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_diaChi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(52, 52, 52)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel4)
                        .addGap(41, 41, 41))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txt_tenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE))
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
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(txt_maNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_tenNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_capnhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 153, 204));
        jPanel2.setForeground(new java.awt.Color(0, 153, 255));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("QUẢN LÝ NHÀ CUNG CẤP");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel5)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(181, 181, 181))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
    }// </editor-fold>//GEN-END:initComponents

    void checkInputNCC()
    {
        if(txt_diaChi.getText().trim().isEmpty()&&txt_soDienThoai.getText().trim().isEmpty()&&txt_tenNCC.getText().trim().isEmpty())
            btn_huy.setEnabled(false);
        else
        {
            btn_huy.setEnabled(true);
            btn_huy.setBackground(ColorCustom.TRANG);
        }
    }
            
    private void tbl_nhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhaCungCapMouseClicked
        // TODO add your handling code here:
        idxSuaNCC = tbl_nhaCungCap.getSelectedRow();
        txt_maNCC.setText(dtm.getValueAt(idxSuaNCC, 0).toString());
        txt_tenNCC.setText(dtm.getValueAt(idxSuaNCC, 1).toString());
        txt_diaChi.setText(dtm.getValueAt(idxSuaNCC, 2).toString());
        txt_soDienThoai.setText(dtm.getValueAt(idxSuaNCC, 3).toString());        
        btn_capnhat.setText("Cập nhật");
        btn_xoa.setEnabled(true);
        btn_huy.setEnabled(true);        
        btn_xoa.setBackground(ColorCustom.XANH_NHAT);
        btn_huy.setBackground(ColorCustom.TRANG);
    }//GEN-LAST:event_tbl_nhaCungCapMouseClicked

    private void btn_capnhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatActionPerformed
        // TODO add your handling code here:
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
         String maNCC = txt_maNCC.getText().trim();
        String tenNCC = txt_tenNCC.getText().trim();                        
        if(tenNCC.isEmpty())
        {
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Tên phân nhà cung cấp không được để trống!!!");
            panel.showNotification();
            //JOptionPane.showMessageDialog(this, "Tên phân nhà cung cấp không được để trống!!!");            
            txt_tenNCC.requestFocus();
            return;
        }
        else            
            if(idxSuaNCC ==-1 && db.getNhaCungCaps().get(t->t.getTenNCC().equalsIgnoreCase(tenNCC)) != null)
            {
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Tên nhà cung cấp này đã tồn tại!!!");
            panel.showNotification();
                //JOptionPane.showMessageDialog(this, "Tên nhà cung cấp này đã tồn tại!!!");
                txt_tenNCC.setText("");
                txt_tenNCC.requestFocus();
                return;
            }
        String diaChi = txt_diaChi.getText().trim();         
        if(diaChi.isEmpty())
        {
            //JOptionPane.showMessageDialog(this, "Địa chỉ của nhà cung cấp không được để trống!!!");  
             Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Địa chỉ của nhà cung cấp không được để trống!!!");
            panel.showNotification();
            txt_diaChi.requestFocus();
            return;
        }
        String soDienThoai = txt_soDienThoai.getText().trim();
        if(soDienThoai.isEmpty())
        {
            //JOptionPane.showMessageDialog(this, "Số điện thoại của nhà cung cấp không được để trống!!!");    
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "số điện thoại của nhà cung cấp không được để trống!!!");
            panel.showNotification();
            txt_soDienThoai.requestFocus();
            return;  
        }
        else if(soDienThoai.length()!=10)
        {
            //JOptionPane.showMessageDialog(this, "Số điện thoại phải có đủ 10 kí tự!!!");       
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Số điện thoại phải có đủ 10 kí tự!!!");
            panel.showNotification();
            txt_soDienThoai.requestFocus();
            return;
        }
            
        if(JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật dữ liệu","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {        
            boolean chk;
            NhaCungCap ncc;
            if(idxSuaNCC == -1)
            {                
                ncc = new NhaCungCap(maNCC, tenNCC, diaChi, soDienThoai);
                chk =  db.getNhaCungCaps().insert(ncc);            
            }
            else
            {
                ncc = db.getNhaCungCaps().get(t->t.getMaNCC().trim().equalsIgnoreCase(maNCC));
                ncc.setTenNCC(tenNCC);
                ncc.setDiaChi(diaChi);
                ncc.setSoDienThoai(soDienThoai);
                chk = db.getNhaCungCaps().update(ncc);
            }                         
            if(chk)            
            {                                                            
                if(idxSuaNCC==-1)
                    dtm.addRow(ncc.getParameter());
                else
                {
                    dtm.removeRow(idxSuaNCC);
                    dtm.insertRow(idxSuaNCC, ncc.getParameter());                    
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
        String tenNCC = dtm.getValueAt(idxSuaNCC, 1).toString();
        if(JOptionPane.showConfirmDialog(this, "Xác nhận xóa nhà cung cấp '"+tenNCC+"'?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {      
            String maNCC = txt_maNCC.getText();
            NhaCungCap ncc = db.getNhaCungCaps().get(t->t.getMaNCC().equalsIgnoreCase(maNCC));
            if(db.getNhaCungCaps().delete(ncc))
            {                
                dtm.removeRow(idxSuaNCC);
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

    private void txt_tenNCCKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_tenNCCKeyReleased
        // TODO add your handling code here:
        checkInputNCC();
    }//GEN-LAST:event_txt_tenNCCKeyReleased

    private void txt_soDienThoaiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_soDienThoaiKeyReleased
        // TODO add your handling code here:
        checkInputNCC();
    }//GEN-LAST:event_txt_soDienThoaiKeyReleased

    private void txt_soDienThoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_soDienThoaiKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()<'0' || evt.getKeyChar() > '9' || txt_soDienThoai.getText().trim().length()>9)
            evt.consume();
    }//GEN-LAST:event_txt_soDienThoaiKeyTyped

    private void txt_diaChiKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_diaChiKeyReleased
        // TODO add your handling code here:
        checkInputNCC();
    }//GEN-LAST:event_txt_diaChiKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btn_capnhat;
    private button.Button btn_huy;
    private button.Button btn_xoa;
    private table.HoverIndex hoverIndex1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private table.TableCustom tableCustom1;
    private table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tbl_nhaCungCap;
    private textfield_2.TextField txt_diaChi;
    private textfield_2.TextField txt_maNCC;
    private textfield_2.TextField txt_soDienThoai;
    private textfield_2.TextField txt_tenNCC;
    // End of variables declaration//GEN-END:variables
}