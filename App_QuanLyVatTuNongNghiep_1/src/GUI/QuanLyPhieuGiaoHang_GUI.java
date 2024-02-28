/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Model.ChiTietLoaiHang;
import Model.ColorCustom;
import Model.DBQuanLyVatTuNongNghiep;
import Model.PhieuGiaoHang;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import table.TableCustom;
import textfield.EventCallBack;
import textfield.EventTextField;
import textfield.TextFieldAnimation;

/**
 *
 * @author Admin
 */
public class QuanLyPhieuGiaoHang_GUI extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyPhieuGiaoHang
     */
    
    DBQuanLyVatTuNongNghiep db = null;
    DefaultTableModel dtm = null;
    DefaultTableModel dtm_ctpg = null; 
    TextFieldAnimation txt_timKiem = null;
    Vector header = new Vector();
    Vector header_ctpg = new Vector();
    int idxSuaNCC = -1;
    
    
    Vector<String> tblNames = new Vector<String>();
    Vector<String> fiedlNames = new Vector<String>();
    
    public QuanLyPhieuGiaoHang_GUI(DBQuanLyVatTuNongNghiep db,TextFieldAnimation txt_timKiem) {
        initComponents();
        this.db = db;                
        this.txt_timKiem = txt_timKiem;
        KhoiTao();
    }

    
    void KhoiTao()
    {        
        
        //sử lý bộ lộc
        chk_tatCa.setSelected(true);
        chk_choXacNhan.setSelected(true);
        chk_hoanTat.setSelected(true);
        chk_biHuy.setSelected(true);
        for (Component com : this.panel_check.getComponents()) {
            JCheckBox chk = (JCheckBox)com;
            chk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                kiemTraCheckAll();
                if(!chk.isSelected() &&  !ktCheck())
                    chk.setSelected(true);
                locTheoTrangThai();
            }
        });
        }
        
        chk_thoiGian.setSelected(true);
        
        
        panel_cuThe.setVisible(false);
        
        
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear();        
        for(int i = 0;i<12;i++)    
        {
            cbo_tuThang.addItem("Tháng "+(i+1));            
            cbo_tuNam.addItem("Năm " + (year - i));                  
        }        
        cbo_tuThang.setEnabled(false);
        
        cbo_denNam.setEnabled(false);
        cbo_denThang.setEnabled(false);
        
        setsetEnabledFalse();
        db.getPhieuDatHangs().parallelStream().forEach(pd->cbo_phieuDatHang.addItem(pd.getSoDonDatHang().trim()+" - " +pd.getNgayDat().toString()));
        
        //xử lý tbl
        
        
        
        dtm = new DefaultTableModel();        
        tbl_phieuGiaoHang.setModel(dtm);               
        dtm_ctpg = new DefaultTableModel();
        tbl_chiTietPhieuGiao.setModel(dtm_ctpg);
        
        header.add("Số phiếu giao");
        header.add("Số đơn đặt hàng");
        header.add("Ngày giao");
        header.add("Tổng số lượng");
        header.add("Thành tiền");
        header.add("Trạng thái");        
        header.add("Ghi chú");        
        dtm.setDataVector(db.getPhieuGiaoHangs().getData(), header);
        
        
        header_ctpg.add("Số phiếu giao");
        header_ctpg.add("Mã chi tiết hàng");
        header_ctpg.add("Số lượng");
        header_ctpg.add("Tên loại");
        header_ctpg.add("Tên hàng");
        dtm_ctpg.setColumnIdentifiers(header_ctpg);
        
        
        tblNames.add("ChiTietGiaoHang");
        tblNames.add("chiTietLoaiHang");
        tblNames.add("hangHoa");
        
        fiedlNames.add("SoPhieuGiao");
        fiedlNames.add("MaCTLH");
        fiedlNames.add("SoLuong");        
        fiedlNames.add("TenLoai");
        fiedlNames.add("TenHang");
        dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().includesAs(tblNames, fiedlNames),header_ctpg);  
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);

        txt_timKiem.addEvent(new EventTextField(){
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    String value = txt_timKiem.getText().toLowerCase();
                    dtm.setDataVector(db.getPhieuGiaoHangs().getData(t->t.getSoPhieuGiao().toLowerCase().contains(value)
                            ||t.getSoDonDatHang().toLowerCase().contains(value)
                            ||t.getTrangThai().toLowerCase().contains(value)
                            ||(t.getGhiChu()!=null&&t.getGhiChu().toLowerCase().contains(value))
                            ||String.valueOf(t.getNgayGiao()).toLowerCase().contains(value)
                            ||String.valueOf(t.getTongSL()).toLowerCase().contains(value)
                            ||String.valueOf(t.getThanhTien()).toLowerCase().contains(value)), header);
                    ArrayList<String> soPGs = new ArrayList<String>();
                    for (Object obj : dtm.getDataVector()) {
                        Vector row = (Vector) obj;
                        String soPG = row.get(0).toString().trim();
                        soPGs.add(soPG);                                                
                    }
                    dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().getData(ct->soPGs.indexOf(ct.getSoPhieuGiao().trim())!=-1, tblNames, fiedlNames), header_ctpg);
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
    
    public void setsetEnabledFalse()
    {
        btn_xacNhan.setEnabled(false);
        btn_tuChoi.setEnabled(false);
    }
    public void locTheoTrangThai()
    {
        locTheoThoiGianHoacDDH();
        ArrayList<String> trangThais = new ArrayList<String>();        
        ArrayList<String> soPGs = new ArrayList<String>();
        if(chk_choXacNhan.isSelected())
            trangThais.add("Chờ xác nhận");
        if(chk_hoanTat.isSelected())
            trangThais.add("Hoàn tất");        
        if(chk_biHuy.isSelected())
            trangThais.add("Bị hủy");
        Vector rows = dtm.getDataVector();
        for (int i = 0;i<rows.size();i++) {
            Vector row = (Vector) rows.get(i);
            if(trangThais.indexOf(row.get(5).toString())==-1)
            {
                dtm.removeRow(i);
                i--;
            }
            else            
                soPGs.add(row.get(0).toString());                                
        }

        Vector ctpg_rows = dtm_ctpg.getDataVector();
        for (int i = 0;i<ctpg_rows.size();i++) {
            Vector row = (Vector) ctpg_rows.get(i);
            if(soPGs.indexOf(row.get(0).toString())==-1)
            {
                dtm_ctpg.removeRow(i);
                i--;
            }
        }
    }
    
    
    private String getSoDonDatHangSelect()
    {
        return Arrays.asList(cbo_phieuDatHang.getSelectedItem().toString().split(" - ")).get(0);
    }
    
     private void locTheoThoiGianHoacDDH()
    {        
        if(chk_thoiGian.isSelected())
        {               
            if(cbo_tuNam.getSelectedIndex()==0)
            {
                dtm.setDataVector(db.getPhieuGiaoHangs().getData(), header);
                dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().includesAs(tblNames, fiedlNames),header_ctpg);   
                return;
            }
            dtm.setDataVector(db.getPhieuGiaoHangs().getData(p->checkNgayGiao(p.getNgayGiao()) ), header);     
            dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().getData(ct-> checkNgayGiao(ct.getPhieuGiaoHang().getNgayGiao()), tblNames, fiedlNames), header_ctpg);
        }  
        else
        {
            if(cbo_phieuDatHang.getSelectedIndex()==0)
            {
                dtm.setDataVector(db.getPhieuGiaoHangs().getData(), header);
                dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().includesAs(tblNames, fiedlNames),header_ctpg);
                return;
            }
            String soDDH = getSoDonDatHangSelect();            
            dtm.setDataVector(db.getPhieuGiaoHangs().getData(p->p.getSoDonDatHang().trim().equals(soDDH)), header);  
            dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().getData(ct->ct.getPhieuGiaoHang().getSoDonDatHang().trim().equals(soDDH), tblNames, fiedlNames), header_ctpg);
        }
        
    }
     
     private boolean checkNgayGiao(Timestamp ts)
     {         
        int tuNam = cbo_tuNam.getSelectedIndex();
        int tuThang = cbo_tuThang.getSelectedIndex()+1;
        int denThang = cbo_denThang.getSelectedIndex()+1;
        int denNam = cbo_denNam.getSelectedIndex();        
        Timestamp timestamp = new Timestamp(new Date().getTime());                        
        int namHT =  timestamp.getYear();
        tuNam = namHT+1-tuNam;                        
        denNam = namHT-denNam;                
        if(ts.getYear()<tuNam||ts.getYear()>denNam)
            return false;
        if(ts.getYear()==tuNam)
            if(ts.getMonth()<tuThang)
                return false;
        if(ts.getYear()==denNam)
            if(ts.getMonth()>denThang+tuThang-1)
                return false;
        return true;
     }
    public void ThongBaoCoPhieuGiaoHangMoi()
    {
        if(JOptionPane.showConfirmDialog(this, "Đã có 1 phiếu giao hàng mới được gửi đến, xác nhận cập nhật lại dữ liệu?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {        
            locTheoTrangThai();
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_gr = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        panel_thoiGian = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbo_tuNam = new combo_suggestion.ComboBoxSuggestion();
        cbo_tuThang = new combo_suggestion.ComboBoxSuggestion();
        cbo_denNam = new combo_suggestion.ComboBoxSuggestion();
        cbo_denThang = new combo_suggestion.ComboBoxSuggestion();
        panel_check = new javax.swing.JPanel();
        chk_biHuy = new checkbox.JCheckBoxCustom();
        chk_hoanTat = new checkbox.JCheckBoxCustom();
        chk_choXacNhan = new checkbox.JCheckBoxCustom();
        panel_cuThe = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        cbo_phieuDatHang = new combo_suggestion.ComboBoxSuggestion();
        chk_thoiGian = new checkbox.JCheckBoxCustom();
        chk_tatCa = new checkbox.JCheckBoxCustom();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_xacNhan = new button.Button();
        btn_tuChoi = new button.Button();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_phieuGiaoHang = new javax.swing.JTable();
        tableScrollButton2 = new table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_chiTietPhieuGiao = new javax.swing.JTable();

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)), "Lộc"));

        jLabel2.setText("Từ năm");

        jLabel3.setText("đến");

        cbo_tuNam.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cbo_tuNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_tuNamActionPerformed(evt);
            }
        });

        cbo_tuThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_tuThangActionPerformed(evt);
            }
        });

        cbo_denNam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_denNamActionPerformed(evt);
            }
        });

        cbo_denThang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_denThangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_thoiGianLayout = new javax.swing.GroupLayout(panel_thoiGian);
        panel_thoiGian.setLayout(panel_thoiGianLayout);
        panel_thoiGianLayout.setHorizontalGroup(
            panel_thoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_thoiGianLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cbo_tuNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_tuThang, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbo_denNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbo_denThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(8, Short.MAX_VALUE))
        );
        panel_thoiGianLayout.setVerticalGroup(
            panel_thoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_thoiGianLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_thoiGianLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(cbo_tuNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_tuThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_denNam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbo_denThang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chk_biHuy.setBackground(new java.awt.Color(12, 87, 118));
        chk_biHuy.setText("Bị hủy");

        chk_hoanTat.setBackground(new java.awt.Color(12, 87, 118));
        chk_hoanTat.setText("Hoàn tất");

        chk_choXacNhan.setBackground(new java.awt.Color(12, 87, 118));
        chk_choXacNhan.setText("Chờ xác nhận");

        javax.swing.GroupLayout panel_checkLayout = new javax.swing.GroupLayout(panel_check);
        panel_check.setLayout(panel_checkLayout);
        panel_checkLayout.setHorizontalGroup(
            panel_checkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_checkLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chk_choXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(chk_hoanTat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(chk_biHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_checkLayout.setVerticalGroup(
            panel_checkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_checkLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_checkLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(chk_biHuy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_hoanTat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_choXacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        jLabel1.setText("Phiếu đặt hàng cụ thể");

        cbo_phieuDatHang.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Tất cả" }));
        cbo_phieuDatHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_phieuDatHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panel_cuTheLayout = new javax.swing.GroupLayout(panel_cuThe);
        panel_cuThe.setLayout(panel_cuTheLayout);
        panel_cuTheLayout.setHorizontalGroup(
            panel_cuTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cuTheLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addComponent(cbo_phieuDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panel_cuTheLayout.setVerticalGroup(
            panel_cuTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_cuTheLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_cuTheLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cbo_phieuDatHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        chk_thoiGian.setBackground(new java.awt.Color(0, 102, 102));
        chk_thoiGian.setText("Theo thời gian");
        chk_thoiGian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_thoiGianActionPerformed(evt);
            }
        });

        chk_tatCa.setBackground(new java.awt.Color(45, 153, 174));
        chk_tatCa.setText("Tất cả");
        chk_tatCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chk_tatCaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panel_thoiGian, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(chk_tatCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panel_check, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52)
                        .addComponent(chk_thoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(panel_cuThe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panel_check, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_thoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chk_tatCa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panel_thoiGian, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_cuThe, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setText("Danh sách phiếu giao hàng");

        jLabel5.setText("Chi tiết phiếu giao hàng");

        btn_xacNhan.setBackground(new java.awt.Color(0, 28, 68));
        btn_xacNhan.setForeground(new java.awt.Color(255, 255, 255));
        btn_xacNhan.setText("Xác nhận");
        btn_xacNhan.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });

        btn_tuChoi.setBackground(new java.awt.Color(45, 153, 174));
        btn_tuChoi.setForeground(new java.awt.Color(255, 255, 255));
        btn_tuChoi.setText("Từ chối");
        btn_tuChoi.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btn_tuChoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_tuChoiActionPerformed(evt);
            }
        });

        tbl_phieuGiaoHang.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_phieuGiaoHang.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_phieuGiaoHang.setRowHeight(30);
        tbl_phieuGiaoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_phieuGiaoHangMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_phieuGiaoHang);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        tbl_chiTietPhieuGiao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_chiTietPhieuGiao.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_chiTietPhieuGiao.setRowHeight(30);
        jScrollPane2.setViewportView(tbl_chiTietPhieuGiao);

        tableScrollButton2.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 735, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(241, 241, 241))
            .addGroup(layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_tuChoi, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btn_xacNhan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_tuChoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)
                    .addComponent(tableScrollButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_phieuGiaoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_phieuGiaoHangMouseClicked
        // TODO add your handling code here:          
        int idx = tbl_phieuGiaoHang.getSelectedRow();
        if(idx==-1)
            return;
        String soPGH = dtm.getValueAt(idx, 0).toString();
        dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().getData(p->p.getSoPhieuGiao().equals(soPGH),tblNames,fiedlNames), header_ctpg);
        
        String trangThai = dtm.getValueAt(idx, 5).toString();
        if(trangThai.equals("Chờ xác nhận"))
        {
            btn_xacNhan.setEnabled(true);
            btn_tuChoi.setEnabled(true);
            btn_xacNhan.setBackground(ColorCustom.XANH_CUC_DAM);
            btn_tuChoi.setBackground(ColorCustom.XANH_NHAT);
        }
        else
        {
            btn_xacNhan.setEnabled(false);
            btn_tuChoi.setEnabled(false);
        }
        
    }//GEN-LAST:event_tbl_phieuGiaoHangMouseClicked

    private void btn_xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanActionPerformed
        // TODO add your handling code here:
        int idx = tbl_phieuGiaoHang.getSelectedRow();
        if(idx==-1)
            return;
        String soPGH = dtm.getValueAt(idx, 0).toString();
        if(JOptionPane.showConfirmDialog(this, "Xác nhận nhận phiếu giao hàng '"+soPGH+"'?\nTất cả các dòng giao hàng sẽ được cập nhật vào kho dữ liệu, xác nhận?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {        
            UpdateSoLuongTonKho();
        }
    }//GEN-LAST:event_btn_xacNhanActionPerformed

    private void btn_tuChoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_tuChoiActionPerformed
        // TODO add your handling code here:
          int row = tbl_phieuGiaoHang.getSelectedRow();
        String soPG = dtm.getValueAt(row, 0).toString();
        PhieuGiaoHang pgh = db.getPhieuGiaoHangs().get(p->p.getSoPhieuGiao().equals(soPG));
        TuChoiPhieuGiaoHang_GUI tc = new TuChoiPhieuGiaoHang_GUI(this,db,pgh);
        tc.pack();
        tc.setLocationRelativeTo(null);
        tc.setVisible(true);
        tc.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
    }//GEN-LAST:event_btn_tuChoiActionPerformed

    private void chk_thoiGianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_thoiGianActionPerformed
        // TODO add your handling code here:
        HoanDoiLoc();
    }//GEN-LAST:event_chk_thoiGianActionPerformed

    private void chk_tatCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chk_tatCaActionPerformed
        // TODO add your handling code here:
        if(chk_tatCa.isSelected())
        {
            setCheckAll(true);
            dtm.setDataVector(db.getPhieuGiaoHangs().getData(), header);  
            dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().includesAs(tblNames, fiedlNames),header_ctpg);            
        }
        else
        {
            setCheckAll(false);           
            chk_choXacNhan.setSelected(true);            
        }
    }//GEN-LAST:event_chk_tatCaActionPerformed

    private void cbo_tuNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_tuNamActionPerformed
        // TODO add your handling code here:
        int idx = cbo_tuNam.getSelectedIndex();
        if(idx==0)
        {
            cbo_tuThang.setEnabled(false);
            cbo_denNam.setEnabled(false);
            cbo_denThang.setEnabled(false);
            dtm.setDataVector(db.getPhieuGiaoHangs().getData(), header);
            dtm_ctpg.setDataVector(db.getChiTietGiaoHangs().includesAs(tblNames, fiedlNames),header_ctpg);   
            return;
        }
        cbo_tuThang.setEnabled(true);
        cbo_denNam.setEnabled(true);        
        cbo_denThang.setEnabled(true);  
        LocalDate localDate = LocalDate.now();
        int namHT =  localDate.getYear();
        int nam = namHT+1-idx;
        int soThang = 12;
        if(idx==1)
            soThang = localDate.getMonthValue();
        cbo_tuThang.removeAllItems();
        cbo_denNam.removeAllItems();        
        int denNam = namHT;
        for(int i = 0;i< soThang;i++) 
        {
            cbo_tuThang.addItem("Tháng "+(i+1)); 
            if(namHT-i>=nam)
                cbo_denNam.addItem("Năm " + (namHT-i));            
        }
        loadThangDen();
        try{locTheoTrangThai();}catch(Exception e){}
    }//GEN-LAST:event_cbo_tuNamActionPerformed

    private void cbo_tuThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_tuThangActionPerformed
        // TODO add your handling code here:
         loadThangDen();
        try{locTheoTrangThai();}catch(Exception e){}
    }//GEN-LAST:event_cbo_tuThangActionPerformed

    private void cbo_denNamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_denNamActionPerformed
        // TODO add your handling code here:
        loadThangDen();
        try{locTheoTrangThai();}catch(Exception e){}
    }//GEN-LAST:event_cbo_denNamActionPerformed

    private void cbo_denThangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_denThangActionPerformed
        // TODO add your handling code here:
        try{locTheoTrangThai();}catch(Exception e){}
    }//GEN-LAST:event_cbo_denThangActionPerformed

    private void cbo_phieuDatHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_phieuDatHangActionPerformed
        // TODO add your handling code here:
        try{locTheoTrangThai();}catch(Exception e){}
    }//GEN-LAST:event_cbo_phieuDatHangActionPerformed
    
    private void UpdateSoLuongTonKho()
    {
        for (int i = 0;i<dtm_ctpg.getRowCount();i++) {
           String maCTLH = dtm_ctpg.getValueAt(i, 1).toString();
           int soLuong = Integer.parseInt(dtm_ctpg.getValueAt(i, 2).toString());
            ChiTietLoaiHang ctlh = db.getChiTietLoaiHangs().get(ct->ct.getMaCTLH().equals(maCTLH));
            ctlh.setSoLuongTon(ctlh.getSoLuongTon()+soLuong);
            db.getChiTietLoaiHangs().update(ctlh);
        }
        int row = tbl_phieuGiaoHang.getSelectedRow();
        String soPG = dtm.getValueAt(row, 0).toString();
        PhieuGiaoHang pgh = db.getPhieuGiaoHangs().get(p->p.getSoPhieuGiao().equals(soPG));
        pgh.setTrangThai("Hoàn tất");
        db.getPhieuGiaoHangs().update(pgh);
        locTheoTrangThai();
    }            
    
    private synchronized void loadThangDen()
    {
        cbo_denThang.removeAllItems();
        int idxTuThang = cbo_tuThang.getSelectedIndex();
        int idxTuNam = cbo_tuNam.getSelectedIndex();
        
        int idxDenThang = cbo_denThang.getSelectedIndex();
        int idxDenNam = cbo_denNam.getSelectedIndex();
        
        LocalDate localDate = LocalDate.now();
        int ktThang = 12;  
        int bdThang = 1;
        
        if(idxTuNam==1)
        {
            bdThang = idxTuThang+1;
            ktThang = localDate.getMonthValue();
        }
        else if(idxDenNam == 0)                                          
            ktThang = localDate.getMonthValue();                    
        
        for(int i = bdThang;i<=ktThang;i++) 
        { 
            cbo_denThang.addItem("Tháng "+(i)); 
        }
            
    }
    private void kiemTraCheckAll()
    {
        for (Component com : this.panel_check.getComponents()) {
            JCheckBox chk = (JCheckBox)com;
            if(!chk.isSelected())
            {
                chk_tatCa.setSelected(false);
                return;
            }                            
        }
        chk_tatCa.setSelected(true);
    }
    
    private void setCheckAll(boolean b)
    {
        for (Component com : this.panel_check.getComponents()) {
            JCheckBox chk = (JCheckBox)com;
            chk.setSelected(b);
        }
    }
    
    private boolean ktCheck()
    {        
        for (Component com : this.panel_check.getComponents()) {
            JCheckBox chk = (JCheckBox)com;
            if(chk.isSelected())                 
                return true;                                     
        }
       return false;
    }
    
    private void HoanDoiLoc()
    {
        boolean tam = panel_thoiGian.isVisible();
        panel_thoiGian.setVisible(!tam);
        panel_cuThe.setVisible(tam);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup btn_gr;
    private button.Button btn_tuChoi;
    private button.Button btn_xacNhan;
    private combo_suggestion.ComboBoxSuggestion cbo_denNam;
    private combo_suggestion.ComboBoxSuggestion cbo_denThang;
    private combo_suggestion.ComboBoxSuggestion cbo_phieuDatHang;
    private combo_suggestion.ComboBoxSuggestion cbo_tuNam;
    private combo_suggestion.ComboBoxSuggestion cbo_tuThang;
    private checkbox.JCheckBoxCustom chk_biHuy;
    private checkbox.JCheckBoxCustom chk_choXacNhan;
    private checkbox.JCheckBoxCustom chk_hoanTat;
    private checkbox.JCheckBoxCustom chk_tatCa;
    private checkbox.JCheckBoxCustom chk_thoiGian;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panel_check;
    private javax.swing.JPanel panel_cuThe;
    private javax.swing.JPanel panel_thoiGian;
    private table.TableScrollButton tableScrollButton1;
    private table.TableScrollButton tableScrollButton2;
    private javax.swing.JTable tbl_chiTietPhieuGiao;
    private javax.swing.JTable tbl_phieuGiaoHang;
    // End of variables declaration//GEN-END:variables
}




