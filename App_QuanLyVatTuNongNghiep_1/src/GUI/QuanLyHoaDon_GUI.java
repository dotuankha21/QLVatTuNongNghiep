/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Model.ArrayLists;
import Model.ChiTietHoaDon;
import Model.DBQuanLyVatTuNongNghiep;
import Model.HoaDon;
import Model.NhanVien;
import java.lang.reflect.Array;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;
import textfield.EventCallBack;
import textfield.EventTextField;
import textfield.TextFieldAnimation;

/**
 *
 * @author Admin
 */
public class QuanLyHoaDon_GUI extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyHoaDon_GUI
     */
    DBQuanLyVatTuNongNghiep db = null;
    NhanVien user = null;
    DefaultTableModel dtm =null;
    DefaultTableModel dtm_cthd =null;
    TextFieldAnimation txt_timKiem = null;
     
    Vector<String> cthd_tblNames = new Vector<String>();
    Vector<String> cthd_fiedlNames = new Vector<String>();
    Vector<String> header = new Vector<String>();
    Vector<String> cthd_header = new Vector<String>();
    
    public QuanLyHoaDon_GUI(DBQuanLyVatTuNongNghiep db, NhanVien user, TextFieldAnimation txt_timKiem ) {
        initComponents();
       cthd_tblNames.add("ChiTietHoaDon");
        cthd_tblNames.add("chiTietLoaiHang");
        cthd_tblNames.add("hangHoa");
        
        cthd_fiedlNames.add("SoHoaDon");
        cthd_fiedlNames.add("MaCTLH");
        cthd_fiedlNames.add("SoLuong");
        cthd_fiedlNames.add("DonGia");
        cthd_fiedlNames.add("TenHang");
        cthd_fiedlNames.add("MaHang");
        cthd_fiedlNames.add("TenLoai");
        
        header.add("Số hóa đơn");
        header.add("Mã nhân viên");
        header.add("Mã khách hàng");
        header.add("Ngày mua");
        header.add("Thành tiền");
        
        cthd_header.add("Số hóa đơn");
        cthd_header.add("Mã chi tiết");
        cthd_header.add("Số lượng");
        cthd_header.add("Đơn giá");
        cthd_header.add("Mã hàng");
        cthd_header.add("Tên loại");        
        cthd_header.add("Tên hàng");
        
        
        this.db = db;
        this.user = user;
        this.txt_timKiem = txt_timKiem;        
        cbo_nam.setEnabled(false);
        cbo_thang.setEnabled(false);
        chk_homnay.setEnabled(false);
        chk_homnay.setSelected(false);
        chk_tatca.setSelected(true);
        chk_homnay.setSelected(true);
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();
        cbo_thang.addItem("Tất cả tháng");
        for(int i = 0;i<12;i++)    
        {
            cbo_thang.addItem("Tháng "+(i+1));
            cbo_nam.addItem("Năm " + (year - i));                  
        }
        dtm = new DefaultTableModel();
        tbl_hoadon.setModel(dtm);
        dtm.setDataVector(db.getHoaDons().getData(), header);        
        
        dtm_cthd = new DefaultTableModel();
        tbl_cthd.setModel(dtm_cthd);
        dtm_cthd.setDataVector(db.getChiTietHoaDons().includesAs(cthd_tblNames, cthd_fiedlNames),cthd_header); 
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
        
        
        txt_timKiem.addEvent(new EventTextField(){
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    String value = txt_timKiem.getText().toLowerCase();
                    dtm.setDataVector(db.getHoaDons().getData(hd->hd.getSoHoaDon().toLowerCase().contains(value)
                            ||hd.getMaNV().toLowerCase().contains(value)
                            ||hd.getMaKH().toLowerCase().contains(value)
                            ||String.valueOf(hd.getNgayMua()).toLowerCase().contains(value)
                            ||String.valueOf(hd.getThanhTien()).toLowerCase().contains(value)), header);
                    ArrayList<String> soHDs = new ArrayList<String>();
                    for (Object obj : dtm.getDataVector()) {
                        Vector row = (Vector) obj;
                        String soHD = row.get(0).toString().trim();
                        soHDs.add(soHD);                                                
                    }
                    dtm_cthd.setDataVector(db.getChiTietHoaDons().getData(ct->soHDs.indexOf(ct.getSoHoaDon().trim())!=-1, cthd_tblNames, cthd_fiedlNames), cthd_header);
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
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        comboSuggestionUI1 = new combo_suggestion.ComboSuggestionUI();
        jPanel1 = new javax.swing.JPanel();
        chk_tatca = new checkbox.JCheckBoxCustom();
        chk_homnay = new checkbox.JCheckBoxCustom();
        cbo_nam = new combobox.Combobox();
        cbo_thang = new combobox.Combobox();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hoadon = new javax.swing.JTable();
        tableScrollButton2 = new table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_cthd = new javax.swing.JTable();

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        jMenu3.setText("File");
        jMenuBar2.add(jMenu3);

        jMenu4.setText("Edit");
        jMenuBar2.add(jMenu4);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        chk_tatca.setBackground(new java.awt.Color(0, 102, 102));
        chk_tatca.setText("Tất cả");
        chk_tatca.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chk_tatca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_tatcaActionPerformed(evt);
            }
        });

        chk_homnay.setBackground(new java.awt.Color(0, 153, 153));
        chk_homnay.setText("Hôm nay");
        chk_homnay.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        chk_homnay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_homnayActionPerformed(evt);
            }
        });

        cbo_nam.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_nam.setLabeText("Chọn năm");
        cbo_nam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_namActionPerformed(evt);
            }
        });

        cbo_thang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        cbo_thang.setLabeText("Chọn tháng");
        cbo_thang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_thangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(224, Short.MAX_VALUE)
                .addComponent(chk_tatca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86)
                .addComponent(chk_homnay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(72, 72, 72)
                .addComponent(cbo_nam, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cbo_thang, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(173, 173, 173))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_tatca, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_homnay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_thang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(48, Short.MAX_VALUE))
        );

        tbl_hoadon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_hoadon.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_hoadon.setRowHeight(30);
        tbl_hoadon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hoadonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_hoadon);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tbl_cthd.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_cthd.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_cthd.setRowHeight(30);
        jScrollPane2.setViewportView(tbl_cthd);

        tableScrollButton2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 670, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, 633, Short.MAX_VALUE)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(122, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private boolean checkDateNow(String dateA, String dateB)
    {
        Timestamp timestamp = new Timestamp(new Date().getTime()); 
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if(sdf.parse(dateA).equals(sdf.parse(dateB)))
                return true;
        } catch (ParseException ex) {
            Logger.getLogger(QuanLyHoaDon_GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    void showData()
    {
        int index = cbo_nam.getSelectedIndex(); 
        if(index==-1)
            return;
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//        LocalDate localDate = LocalDate.now();
        //System.out.println(localDate.getYear());
        try
        {
            dtm.setRowCount(0);
            dtm_cthd.setRowCount(0);
            Timestamp timestamp = new Timestamp(new Date().getTime());    
            int nam = timestamp.getYear()-index;            
            dtm.setDataVector(db.getHoaDons().getData(hd->hd.getNgayMua().getYear()==nam), header);
            dtm_cthd.setDataVector(db.getChiTietHoaDons().getData(ct->ct.getHoaDon().getNgayMua().getYear() == nam, cthd_tblNames, cthd_fiedlNames), cthd_header);

        }catch(Exception e){}
    }
    private boolean checkTonTai(Vector<String> v, String a)
    {
        for (String str : v) {
            if(str.trim().equalsIgnoreCase(a))
                return true;
        }
        return false;
    }
    private HoaDon getHoaDon(String soHD)
    {
        return db.getHoaDons().get(hd->hd.getSoHoaDon().contains(soHD));
    }
    
    private void tbl_hoadonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hoadonMouseClicked
        // TODO add your handling code here:
        int row = tbl_hoadon.getSelectedRow();
        if(row==-1)
            return;
        String soHD = dtm.getValueAt(row, 0).toString();        
        HoaDon hd = getHoaDon(soHD);       
        dtm_cthd.setDataVector(hd.getChiTietHoaDons().includesAs(cthd_tblNames, cthd_fiedlNames), cthd_header);
    }//GEN-LAST:event_tbl_hoadonMouseClicked

    private void chk_tatcaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_tatcaActionPerformed
        // TODO add your handling code here:                
        if(!chk_tatca.isSelected())
        {
            chk_homnay.setEnabled(true);
            chk_homnayActionPerformed(null);
        }
        else
        {
            cbo_nam.setEnabled(false);
            cbo_thang.setEnabled(false);
            chk_homnay.setEnabled(false);
            dtm.setDataVector(db.getHoaDons().getData(), header);
            dtm_cthd.setDataVector(db.getChiTietHoaDons().includesAs(cthd_tblNames, cthd_fiedlNames),cthd_header);
        }
    }//GEN-LAST:event_chk_tatcaActionPerformed

    private void chk_homnayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_homnayActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        if(!chk_homnay.isSelected())
        {
            cbo_nam.setEnabled(true);
            cbo_thang.setEnabled(true);
            cbo_thang.setSelectedIndex(0);
            cbo_namActionPerformed(null);
        }
        else
        {
            cbo_nam.setEnabled(false);
            cbo_thang.setEnabled(false);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Timestamp timestamp = new Timestamp(new Date().getTime());
            LocalDate localDate = LocalDate.now();
            String dateNow = timestamp.toString();
            dtm.setRowCount(0);
            dtm_cthd.setRowCount(0);
            for (HoaDon hd : db.getHoaDons()) {
                try {
                    if(sdf.parse(hd.getNgayMua().toString()).equals(sdf.parse(dateNow)))
                    dtm.addRow(hd.getParameter());
                } catch (ParseException ex) {
                    Logger.getLogger(QuanLyHoaDon_GUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            dtm_cthd.setDataVector(db.getChiTietHoaDons().getData(ct->checkDateNow(ct.getHoaDon().getNgayMua().toString(),dateNow), cthd_tblNames, cthd_fiedlNames), cthd_header);
        }

    }//GEN-LAST:event_chk_homnayActionPerformed

    private void cbo_namActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_namActionPerformed
       
        cbo_thang.setSelectedIndex(0);
        showData();
    }//GEN-LAST:event_cbo_namActionPerformed

    private void cbo_thangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_thangActionPerformed
        // TODO add your handling code here:
                int index = cbo_thang.getSelectedIndex();
        if(index==-1)
        return;
        showData();
        if(index == 0)
        return;
        //        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        //        LocalDate localDate = LocalDate.now();
        //System.out.println(localDate.getYear());
        Vector vt = dtm.getDataVector();
        Vector<String> soHDLay = new Vector<String>();
        for (int i = 0;i<vt.size();i++) {
            Vector item = (Vector) vt.get(i);
            int month = ((Timestamp)item.get(3)).getMonth();
            if(month != (index-1))
            {
                dtm.removeRow(i);
                i--;
            }
            else
            {
                String soHD = item.get(0).toString();
                soHDLay.add(soHD);
            }
        }

        vt = dtm_cthd.getDataVector();
        for (int i = 0;i<vt.size();i++) {
            Vector item = (Vector) vt.get(i);
            String soHD = item.get(0).toString();
            if(!checkTonTai(soHDLay, soHD))
            {
                dtm_cthd.removeRow(i);
                i--;
            }
        }
        tbl_hoadon.updateUI();
        tbl_cthd.updateUI();
    }//GEN-LAST:event_cbo_thangActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private combobox.Combobox cbo_nam;
    private combobox.Combobox cbo_thang;
    private checkbox.JCheckBoxCustom chk_homnay;
    private checkbox.JCheckBoxCustom chk_tatca;
    private combo_suggestion.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private table.TableScrollButton tableScrollButton1;
    private table.TableScrollButton tableScrollButton2;
    private javax.swing.JTable tbl_cthd;
    private javax.swing.JTable tbl_hoadon;
    // End of variables declaration//GEN-END:variables
}
