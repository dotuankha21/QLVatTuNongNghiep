/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Model.ChiTietHoaDon;
import Model.DBQuanLyVatTuNongNghiep;
import Model.NhanVien;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswingdev.Notification;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;
import textfield.EventCallBack;
import textfield.EventTextField;
import textfield.TextFieldAnimation;

/**
 *
 * @author Crustea
 */
public class ThongKe_GUI extends javax.swing.JPanel {

    DBQuanLyVatTuNongNghiep db = null;
    NhanVien user;    
    TextFieldAnimation txt_timKiem = null;
    DefaultTableModel dtm = null;
    Vector header = new Vector();
    Vector<String> tblNames = new Vector<String>();
    Vector<String> fiedlNames = new Vector<String>();
    
    public ThongKe_GUI(DBQuanLyVatTuNongNghiep db,NhanVien user,TextFieldAnimation txt_timKiem) {
        initComponents();
        this.db = db;
        this.user = user;
        this.txt_timKiem = txt_timKiem;
        khoiTao();
    }

    public  void khoiTao()
    {
        dtm = new DefaultTableModel();
        tbl_thongKe.setModel(dtm);
                
        tblNames.add("ChiTietLoaiHang");        
        tblNames.add("hangHoa");
                
        
        fiedlNames.add("MaCTLH");        
        fiedlNames.add("TenLoai");
        fiedlNames.add("SoLuongTon");
        fiedlNames.add("MaHang");
        fiedlNames.add("TenHang");
        
                
        header.add("Mã chi tiết");
        header.add("Loại");
        header.add("Số lượng tồn");
        header.add("Mã hàng");
        header.add("Tên hàng");      
        header.add("Tỏng số lượng đã bán");
        header.add("Tổng tiền");
        chk_tatCa.setSelected(true);
        thongKe();        
        txt_batDau.setEditable(false);
        txt_ketThuc.setEditable(false);
        
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        chonNgayBatDau.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {                
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    chonNgayBatDau.hidePopup();                    
                }
            }
        });
        chonNgayKetThuc.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {                
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    chonNgayKetThuc.hidePopup();                    
                }
            }
        });
        txt_timKiem.addEvent(new EventTextField(){
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    String value = txt_timKiem.getText().trim().toLowerCase();                                                            
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
    private void thongKe()
    {
        Vector data ;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(chonNgayBatDau.getDateFormat());        
        LocalDate batdau= LocalDate.parse(txt_batDau.getText(), formatter);
        LocalDate ketthuc= LocalDate.parse(txt_ketThuc.getText(), formatter);
        if(chk_tatCa.isSelected())
        {
            txt_batDau.setEnabled(false);
            txt_ketThuc.setEnabled(false);
            data = db.getChiTietLoaiHangs().getData(ct->ct.getChiTietHoaDons().size()>0,tblNames, fiedlNames);
        }
        else
        {            
            txt_batDau.setEnabled(true);
            txt_ketThuc.setEnabled(true);
            Duration duration = Duration.between(batdau.atStartOfDay(), ketthuc.atStartOfDay());
            long SoNgay = duration.toDays();
            if(SoNgay<0)
            {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Ngày bắt đầu phải lớn hơn ngày kết thúc!");
                panel.showNotification();  
                return;
            }
            if(LocalDate.now().isBefore(ketthuc))
            {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Ngày kết thúc không được lơn hơn ngày hiện tại!");
                panel.showNotification();  
                return;
            }
            data = db.getChiTietLoaiHangs().getData(ct->{
                if(ct.getChiTietHoaDons().size()>0)
                    {
                        for (ChiTietHoaDon cthd : ct.getChiTietHoaDons()) {
                            LocalDate ngayMua = cthd.getHoaDon().getNgayMua().toLocalDateTime().toLocalDate();
                            if(ngayMua.isAfter(batdau.minusDays(1))&&ngayMua.isBefore(ketthuc.plusDays(1)))
                                return true;
                        }
                    }
                return false;
            },tblNames, fiedlNames);
        }
        for (Object obj : data) {
            Vector item = (Vector)obj;
            String ma = (String) item.get(0);
            int tong = 0;
            int tongTien = 0;
            for (ChiTietHoaDon cthd : db.getChiTietHoaDons()) {
                LocalDate ngayMua = cthd.getHoaDon().getNgayMua().toLocalDateTime().toLocalDate();
                if(cthd.getMaCTLH().equals(ma)&&(chk_tatCa.isSelected()||(!chk_tatCa.isSelected()&&ngayMua.isAfter(batdau.minusDays(1))&&ngayMua.isBefore(ketthuc.plusDays(1)))))
                {
                    tong+=cthd.getSoLuong();
                    tongTien+=cthd.getSoLuong()*cthd.getDonGia();
                }
            }
            item.add(tong);
            item.add(tongTien);
            
        }
        dtm.setDataVector(data, header);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        chonNgayBatDau = new com.raven.datechooser.DateChooser();
        chonNgayKetThuc = new com.raven.datechooser.DateChooser();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thongKe = new javax.swing.JTable();
        txt_batDau = new textfield.TextField();
        txt_ketThuc = new textfield.TextField();
        chk_tatCa = new checkbox.JCheckBoxCustom();

        chonNgayBatDau.setForeground(new java.awt.Color(0, 51, 153));
        chonNgayBatDau.setDateFormat("dd/MM/yyyy");
        chonNgayBatDau.setTextRefernce(txt_batDau);
        chonNgayBatDau.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                chonNgayBatDauAncestorRemoved(evt);
            }
        });

        chonNgayKetThuc.setForeground(new java.awt.Color(0, 0, 102));
        chonNgayKetThuc.setDateFormat("dd/MM/yyyy");
        chonNgayKetThuc.setTextRefernce(txt_ketThuc);
        chonNgayKetThuc.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                chonNgayKetThucAncestorRemoved(evt);
            }
        });

        setBackground(new java.awt.Color(255, 255, 255));

        tbl_thongKe.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tbl_thongKe);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        txt_batDau.setLabelText("Từ ngày");
        txt_batDau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_batDauActionPerformed(evt);
            }
        });

        txt_ketThuc.setLabelText("Đến ngày");
        txt_ketThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_ketThucActionPerformed(evt);
            }
        });

        chk_tatCa.setText("Tất cả");
        chk_tatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_tatCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(278, 278, 278)
                        .addComponent(chk_tatCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(204, 204, 204)
                        .addComponent(txt_batDau, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(119, 119, 119)
                        .addComponent(txt_ketThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(104, 104, 104)
                        .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(100, 100, 100)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_batDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_ketThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_tatCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 574, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(148, 148, 148))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_batDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_batDauActionPerformed
        // TODO add your handling code here:
        thongKe();

    }//GEN-LAST:event_txt_batDauActionPerformed

    private void txt_ketThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ketThucActionPerformed
        // TODO add your handling code here:
        thongKe();

    }//GEN-LAST:event_txt_ketThucActionPerformed

    private void chonNgayBatDauAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_chonNgayBatDauAncestorRemoved
        // TODO add your handling code here:
        thongKe();
        
    }//GEN-LAST:event_chonNgayBatDauAncestorRemoved

    private void chonNgayKetThucAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_chonNgayKetThucAncestorRemoved
        // TODO add your handling code here:
        thongKe();
        
    }//GEN-LAST:event_chonNgayKetThucAncestorRemoved

    private void chk_tatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_tatCaActionPerformed
        // TODO add your handling code here:        
        thongKe();
    }//GEN-LAST:event_chk_tatCaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private checkbox.JCheckBoxCustom chk_tatCa;
    private com.raven.datechooser.DateChooser chonNgayBatDau;
    private com.raven.datechooser.DateChooser chonNgayKetThuc;
    private javax.swing.JScrollPane jScrollPane1;
    private table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tbl_thongKe;
    private textfield.TextField txt_batDau;
    private textfield.TextField txt_ketThuc;
    // End of variables declaration//GEN-END:variables
}
