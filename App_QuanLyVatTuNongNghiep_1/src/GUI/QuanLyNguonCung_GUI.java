/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Model.ChiTietLoaiHang;
import Model.ColorCustom;
import Model.DBQuanLyVatTuNongNghiep;
import Model.HangHoa;
import Model.NguonHang;
import Model.NhaCungCap;
import Model.NhanVien;
import java.util.ArrayList;
import java.util.Arrays;
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
 * @author Admin
 */
public class QuanLyNguonCung_GUI extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyNguonCung_GUI
     */
    DBQuanLyVatTuNongNghiep db = null;
    DefaultTableModel dtm = null;
    TextFieldAnimation txt_timKiem = null;
    NhanVien user = null;
    Vector header = null;
    Vector<String> tblNames = new Vector<String>();
    Vector<String> fiedlNames = new Vector<String>();
    int idxSua = -1;
    public QuanLyNguonCung_GUI(DBQuanLyVatTuNongNghiep db,NhanVien user,TextFieldAnimation txt_timKiem) {
        this.db = db;
        this.user = user;
        this.txt_timKiem = txt_timKiem;
        this.khoiTao();
        cbo_hangHoa.setEnabled(false);
        cbo_nhaCungCap.setEnabled(false);
        chk_tatCa.setSelected(true); 
    }
    
    
//    public QuanLyNguonCung_GUI(DBQuanLyVatTuNongNghiep db,NhanVien user,ChiTietLoaiHang ctlh) {
//        this.db = db;
//        this.user = user;
//        this.khoiTao();
//        cbo_nhaCungCap.setSelectedIndex(0);
//        for (int i = 0;i<cbo_hangHoa.getItemCount();i++) {            
//            if(cbo_hangHoa.getItemAt(i).contains(ctlh.getMaCTLH()))
//            {
//                cbo_hangHoa.setSelectedIndex(i);
//                break;
//            }
//        }
//        showNguonCungLoc();
//    }
    
    private void khoiTao()
    {
        initComponents();
        
        tblNames.add("NguonHang");
        tblNames.add("nhaCungCap");
        tblNames.add("chiTietLoaiHang");
        tblNames.add("hangHoa");
        fiedlNames.add("MaNCC");
        fiedlNames.add("TenNCC");
        fiedlNames.add("MaCTLH");
        fiedlNames.add("GiaNhap");
        fiedlNames.add("TenLoai");
        fiedlNames.add("TenHang");


        
        dtm = new DefaultTableModel();
        tbl_nguonHang.setModel(dtm);
        header = new Vector();
        header.add("Mã NCC");
        header.add("Mã CTLH");
        header.add("Giá nhập");        
        header.add("Tên nhà cung cấp");  
        header.add("Loại hàng");   
        header.add("Tên hàng");                       
        Vector data = getAllNguonHang();
        dtm.setDataVector(data, header);        
        loadCombobox(); 
        btn_huy.setEnabled(false);
        btn_xoa.setEnabled(false);
        btn_capNhat.setEnabled(false);
        cbo_hhChuaCungCap.setEnabled(false);
        txt_maNCC.setEditable(false);
        txt_maCTLH.setEditable(false);
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        
        
        txt_timKiem.addEvent(new EventTextField(){
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    String value = txt_timKiem.getText().toLowerCase();
                    dtm.setDataVector(db.getNguonHangs().getData(nh->nh.getMaNCC().toLowerCase().contains(value)
                            ||nh.getMaCTLH().toLowerCase().contains(value)
                            ||nh.getNhaCungCap().getTenNCC().toLowerCase().contains(value)
                            ||nh.getChiTietLoaiHang().getTenLoai().toLowerCase().contains(value)
                            ||nh.getChiTietLoaiHang().getHangHoa().getTenHang().toLowerCase().contains(value)
                            ||String.valueOf(nh.getGiaNhap()).contains(value),tblNames,fiedlNames), header);                    
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
    
    
    private Vector getAllNguonHang()
    {
        return db.getNguonHangs().includesAs(tblNames, fiedlNames);
    }
    
    void loadCombobox()
    {        
        for (NhaCungCap ncc : db.getNhaCungCaps()) {
            String item = ncc.getMaNCC()+" - "+ncc.getTenNCC();
            cbo_nhaCungCap.addItem(item);
        } 
        for (HangHoa hh : db.getHangHoas()) {
            String item = hh.getMaHang()+" - "+hh.getTenHang();
            cbo_hangHoa.addItem(item);
        }
    }
    private String getMaNCC()
    {
        return Arrays.asList(cbo_nhaCungCap.getSelectedItem().toString().split(" - ")).get(0).trim();
    }
    
    private String getMaHangHoa()
    {
        return Arrays.asList(cbo_hangHoa.getSelectedItem().toString().split(" - ")).get(0).trim();
    }
    
    private String getMaCTLHChuaCungCap()
    {
        return Arrays.asList(cbo_hhChuaCungCap.getSelectedItem().toString().split(" - ")).get(0).trim();
    }
    void resetText()
    {        
        txt_maCTLH.setText("");
        txt_giaNhap.setText("");
        txt_maNCC.setText("");
        btn_capNhat.setText("Thêm");
        idxSua = -1;
        btn_huy.setEnabled(false);
        btn_xoa.setEnabled(false);
        if(!chk_tatCa.isSelected())
        {
            cbo_hhChuaCungCap.setEnabled(true);
            btn_capNhat.setEnabled(true);
            btn_capNhat.setBackground(ColorCustom.XANH_DAM);        
        }
        else
            btn_capNhat.setEnabled(false);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comboSuggestionUI1 = new combo_suggestion.ComboSuggestionUI();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        chk_tatCa = new checkbox.JCheckBoxCustom();
        cbo_nhaCungCap = new combo_suggestion.ComboBoxSuggestion();
        cbo_hangHoa = new combo_suggestion.ComboBoxSuggestion();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        cbo_hhChuaCungCap = new combo_suggestion.ComboBoxSuggestion();
        btn_capNhat = new button.Button();
        btn_xoa = new button.Button();
        btn_huy = new button.Button();
        txt_maCTLH = new textfield_suggestion.TextFieldSuggestion();
        txt_giaNhap = new textfield_suggestion.TextFieldSuggestion();
        txt_maNCC = new textfield_suggestion.TextFieldSuggestion();
        jLabel7 = new javax.swing.JLabel();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_nguonHang = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204)), "Danh mục nguồn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 0, 12), new java.awt.Color(0, 153, 204))); // NOI18N
        jPanel1.setPreferredSize(new java.awt.Dimension(1100, 800));

        jLabel4.setText("Giá nhập:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1), "", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Nhà cung cấp");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Hàng hóa");

        chk_tatCa.setBackground(new java.awt.Color(0, 204, 204));
        chk_tatCa.setText("Tất cả");
        chk_tatCa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        chk_tatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_tatCaActionPerformed(evt);
            }
        });

        cbo_nhaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_nhaCungCapActionPerformed(evt);
            }
        });

        cbo_hangHoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_hangHoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(chk_tatCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_hangHoa, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(chk_tatCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_nhaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_hangHoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jLabel5.setText("Hàng hóa chưa cung cấp:");

        jLabel6.setText("Mã nhà cung cấp:");

        cbo_hhChuaCungCap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_hhChuaCungCapActionPerformed(evt);
            }
        });

        btn_capNhat.setBackground(new java.awt.Color(12, 87, 118));
        btn_capNhat.setForeground(new java.awt.Color(255, 255, 255));
        btn_capNhat.setText("Thêm");
        btn_capNhat.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_capNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capNhatActionPerformed(evt);
            }
        });

        btn_xoa.setBackground(new java.awt.Color(45, 153, 174));
        btn_xoa.setForeground(new java.awt.Color(255, 255, 255));
        btn_xoa.setText("Xóa");
        btn_xoa.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });

        btn_huy.setText("Hủy");
        btn_huy.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });

        txt_giaNhap.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_giaNhapKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_giaNhapKeyTyped(evt);
            }
        });

        jLabel7.setText("Mã chi tiết loại hàng:");

        tbl_nguonHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_nguonHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_nguonHang.setRowHeight(30);
        tbl_nguonHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nguonHangMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tbl_nguonHangMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_nguonHang);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(45, 45, 45)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(cbo_hhChuaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                    .addComponent(txt_maNCC, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(64, 64, 64)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(txt_maCTLH, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txt_giaNhap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(58, 58, 58))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(cbo_hhChuaCungCap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_maNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addGap(3, 3, 3)
                        .addComponent(txt_maCTLH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_giaNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(0, 102, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("QUẢN LÝ NGUỒN CUNG");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(524, 524, 524)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(15, 15, 15))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(300, 300, 300)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 903, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(300, 300, 300))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(29, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    void setenabled(boolean b)
    {
        cbo_hangHoa.setEnabled(b);
        cbo_nhaCungCap.setEnabled(b);
        cbo_hhChuaCungCap.setEnabled(b);
        btn_capNhat.setEnabled(b);
        if(b)
            btn_capNhat.setBackground(ColorCustom.XANH_DAM);
    }    
    void refreshCboNhaCungCap()
    {
        getHangHoaChuaCungCapTuNCC();
        //txt_maNCC.setText(getMaNCC());
        if(cbo_nhaCungCap.getSelectedIndex() <1)
        {
            txt_maCTLH.setText("");
            btn_capNhat.setEnabled(false);
            return;
        }
        txt_maNCC.setText(getMaNCC());
        if(cbo_hhChuaCungCap.getItemCount()==0)
        {
            btn_capNhat.setEnabled(false);
            cbo_hhChuaCungCap.addItem("Không còn hàng hóa mới để cung cấp");
            cbo_hhChuaCungCap.setEnabled(false);
        }
        else
        {
            btn_capNhat.setEnabled(true);
            btn_capNhat.setBackground(ColorCustom.XANH_DAM);
            cbo_hhChuaCungCap.setEnabled(true);
        }
    }
    
    private void getHangHoaChuaCungCapTuNCC()
    {
        cbo_hhChuaCungCap.removeAllItems();
        if(cbo_nhaCungCap.getSelectedIndex() <= 0)
            return;
        String maNCC = getMaNCC();       
        NhaCungCap ncc = db.getNhaCungCaps().get(nc->nc.getMaNCC().contains(maNCC));  
        db.getChiTietLoaiHangs().forEach(ctlh->{
            for(NguonHang nh : ncc.getNguonHangs())
                if(ctlh.getMaCTLH().equals(nh.getMaCTLH()))
                    return;
            cbo_hhChuaCungCap.addItem(ctlh.getMaCTLH()+" - "+ctlh.getHangHoa().getTenHang()+" - "+ctlh.getTenLoai());
        });                     
            
                
    }
    
    private void showNguonCungLoc()
    {
        resetText();
        String maNCC = cbo_nhaCungCap.getSelectedIndex()>0 ? getMaNCC(): "";
        String maHH = cbo_hangHoa.getSelectedIndex()>0 ? getMaHangHoa() : "";         
        Vector data = db.getNguonHangs().getData(t->t.getMaNCC().contains(maNCC)&&t.getChiTietLoaiHang().getHangHoa().getMaHang().contains(maHH),tblNames,fiedlNames);
        dtm.setDataVector(data, header);
    }
    
    private void tbl_nguonHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nguonHangMouseClicked
        // TODO add your handling code here:
        int idxtam;
        if((idxtam = tbl_nguonHang.getSelectedRow()) == idxSua)
            return;
        idxSua = idxtam;
        btn_capNhat.setText("Cập nhật");
        btn_capNhat.setEnabled(true);
        btn_huy.setEnabled(true);
        btn_xoa.setEnabled(true);
        btn_capNhat.setBackground(ColorCustom.XANH_DAM);
        btn_xoa.setBackground(ColorCustom.XANH_NHAT);
        btn_huy.setBackground(ColorCustom.TRANG);
        txt_giaNhap.setText(dtm.getValueAt(idxSua, 2).toString());
        txt_maNCC.setText(dtm.getValueAt(idxSua, 0).toString());
        txt_maCTLH.setText(dtm.getValueAt(idxSua, 1).toString());                          
    }//GEN-LAST:event_tbl_nguonHangMouseClicked

    private void tbl_nguonHangMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nguonHangMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tbl_nguonHangMouseEntered

    private void chk_tatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_tatCaActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
        if(chk_tatCa.isSelected())
        {            
            dtm.setDataVector(getAllNguonHang(), header);
            setenabled(false);
        }
        else
        {
            setenabled(true);
            cbo_nhaCungCapActionPerformed(null);
            showNguonCungLoc();
        }
    }//GEN-LAST:event_chk_tatCaActionPerformed

    private void cbo_nhaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_nhaCungCapActionPerformed
        // TODO add your handling code here:
        showNguonCungLoc();  
        refreshCboNhaCungCap();
    }//GEN-LAST:event_cbo_nhaCungCapActionPerformed

    private void cbo_hangHoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_hangHoaActionPerformed
        // TODO add your handling code here:
        showNguonCungLoc();
        resetText();
        chk_tatCaActionPerformed(null);
    }//GEN-LAST:event_cbo_hangHoaActionPerformed

    private void cbo_hhChuaCungCapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_hhChuaCungCapActionPerformed
        // TODO add your handling code here:
        try
        {
            txt_maCTLH.setText(getMaCTLHChuaCungCap());
        }catch(Exception e){}
    }//GEN-LAST:event_cbo_hhChuaCungCapActionPerformed

    private void btn_capNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capNhatActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here
JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        String maNCC = txt_maNCC.getText();
        String maCTLH = txt_maCTLH.getText();
        if(maNCC.isEmpty())
        {
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Mã nhà cung cấp không được để trống!!!");
            panel.showNotification();
            //JOptionPane.showMessageDialog(this, "Mã nhà cung cấp không được để trống!!!");            
            txt_maNCC.requestFocus();
            return;
        }   
        if(maCTLH.isEmpty())
        {
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Mã chi tiết loại hàng không được để trống!!!");
            panel.showNotification();
            //JOptionPane.showMessageDialog(this, "Mã chi tiết loại hàng không được để trống!!!");            
            txt_maCTLH.requestFocus();
            return;
        }                 
        String strGia = txt_giaNhap.getText().trim();
        if(strGia.isEmpty())
        {
            //JOptionPane.showMessageDialog(this, "Giá nhập không được để trống!!!");  
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT,  "Giá nhập không được để trống!!!");
            panel.showNotification();
            txt_giaNhap.requestFocus();
            return;
        }
        int giaNhap = Integer.parseInt(strGia);
        if(giaNhap <= 0)
        {
            //JOptionPane.showMessageDialog(this, "Giá nhập phải là số ngyên dương!!!");   
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT,  "Giá nhập phải là số ngyên dương!!!");
            panel.showNotification();
            txt_giaNhap.setText("");
            txt_giaNhap.requestFocus();
            return;  
        }        
            
        if(JOptionPane.showConfirmDialog(this, "Xác nhận cập nhật dữ liệu","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {        
            boolean chk;
            NguonHang nh;
            if(idxSua == -1)
            {                
                System.out.println("vào thêm");
                nh = new NguonHang(maNCC, maCTLH, giaNhap);
                chk =  db.getNguonHangs().insert(nh);
            }
            else
            {
                nh = db.getNguonHangs().get(t->t.getMaNCC().equalsIgnoreCase(maNCC) && t.getMaCTLH().equalsIgnoreCase(maCTLH));
                nh.setGiaNhap(giaNhap);                                
                chk = db.getNguonHangs().update(nh);
            }      
            if(chk)            
            {   
                cbo_nhaCungCapActionPerformed(null);
                cbo_hhChuaCungCapActionPerformed(null);             
                //JOptionPane.showMessageDialog(this, "Thao tác thành công"); 
                Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT,  "Thao tác thành công");
            panel.showNotification();
            }
            else
            {
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT,  "Đã xảy ra lỗi, vui lòng thử lại!");
            panel.showNotification();
            }
                //JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi, vui lòng thử lại!");
        }
    }//GEN-LAST:event_btn_capNhatActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
                // TODO add your handling code here:    
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        String tenHang = dtm.getValueAt(idxSua, 5).toString()+dtm.getValueAt(idxSua, 4);
        String tenNCC = dtm.getValueAt(idxSua, 3).toString();
        if(JOptionPane.showConfirmDialog(this, "Xác nhận xóa nguồn cung mặt hàng '"+tenHang+"' của nhà cung cấp '"+tenNCC+"' ?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {       
            String maNCC = txt_maNCC.getText();
            String maCTLH = txt_maCTLH.getText();
            NguonHang nh = db.getNguonHangs().get(t->t.getMaNCC().equalsIgnoreCase(maNCC)&&t.getMaCTLH().equalsIgnoreCase(maCTLH));
            if(db.getNguonHangs().delete(nh))
            {                
                dtm.removeRow(idxSua);
                cbo_nhaCungCapActionPerformed(null);
                //JOptionPane.showMessageDialog(this, "Xóa thành công!!!");
                Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Xóa thành công!!!");
            panel.showNotification();
            }
            else
                //JOptionPane.showMessageDialog(this, "Xóa không thành công!!!");
            {
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Xóa không thành công!!!");
            panel.showNotification();
            return;
            }
        }
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
                // TODO add your handling code here:
        if(JOptionPane.showConfirmDialog(this, "Xác nhận hoàn tác?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {
            resetText();
            chk_tatCaActionPerformed(null);
        }
    }//GEN-LAST:event_btn_huyActionPerformed

    private void txt_giaNhapKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_giaNhapKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_giaNhapKeyReleased

    private void txt_giaNhapKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_giaNhapKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()<'0'||evt.getKeyChar()>'9')
            evt.consume();
        if(txt_giaNhap.getText().isEmpty()&&evt.getKeyChar()=='0')
            evt.consume();
    }//GEN-LAST:event_txt_giaNhapKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btn_capNhat;
    private button.Button btn_huy;
    private button.Button btn_xoa;
    private combo_suggestion.ComboBoxSuggestion cbo_hangHoa;
    private combo_suggestion.ComboBoxSuggestion cbo_hhChuaCungCap;
    private combo_suggestion.ComboBoxSuggestion cbo_nhaCungCap;
    private checkbox.JCheckBoxCustom chk_tatCa;
    private combo_suggestion.ComboSuggestionUI comboSuggestionUI1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tbl_nguonHang;
    private textfield_suggestion.TextFieldSuggestion txt_giaNhap;
    private textfield_suggestion.TextFieldSuggestion txt_maCTLH;
    private textfield_suggestion.TextFieldSuggestion txt_maNCC;
    // End of variables declaration//GEN-END:variables
}
