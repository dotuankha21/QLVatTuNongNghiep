/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import Model.ColorCustom;
import Model.DBQuanLyVatTuNongNghiep;
import Model.NhaCungCap;
import Model.NhanVien;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
public class QuanLyNhanVien_GUI extends javax.swing.JPanel {

    /**
     * Creates new form QuanLyNhanVien_GUI
     */
    DBQuanLyVatTuNongNghiep db = null;
    DefaultTableModel dtm = null;
    NhanVien user = null;
    TextFieldAnimation txt_timKiem = null;
    int idxSua = -1;
    Vector header = new Vector();
    public QuanLyNhanVien_GUI(DBQuanLyVatTuNongNghiep db, NhanVien user, TextFieldAnimation txt_timKiem) {
        initComponents();
        this.db = db; 
        this.user = user;
        this.txt_timKiem = txt_timKiem;
        KhoiTao();
    }

    private void KhoiTao()
    {
        txt_maNV.setEditable(false);
        txt_maNV.setText(taoMaNhanVienMoi());
        dtm = new DefaultTableModel();
        tbl_nhanVien.setModel(dtm);
        header.add("Mã nhân viên");
        header.add("Họ và tên");
        header.add("Tên đăng nhập");
        header.add("Ngày sinh");
        header.add("Giới tính");
        header.add("Số điện thoại");
        header.add("Địa chỉ");
        header.add("Chức vụ");
        dtm.setDataVector(db.getNhanViens().getData("MatKhau"), header);
        
        
        btn_huy.setEnabled(false);
        btn_xoa.setEnabled(false);
        btn_datLaiMK.setEnabled(false);
        rdo_nam.setSelected(true);
        cbo_chucVu.addItem("Nhân viên");
        cbo_chucVu.addItem("Quản lý");
        
        dateChooser.addEventDateChooser(new EventDateChooser() {
            @Override
            public void dateSelected(SelectedAction action, SelectedDate date) {                
                if (action.getAction() == SelectedAction.DAY_SELECTED) {
                    dateChooser.hidePopup();                    
                }
            }
        });
        txt_ngaySinh.setText("");
        TableCustom.apply(jScrollPane1, TableCustom.TableType.MULTI_LINE);
        
        txt_timKiem.addEvent(new EventTextField(){
            @Override
            public void onPressed(EventCallBack call) {
                try {
                    String value = txt_timKiem.getText().toLowerCase();
                    dtm.setDataVector(db.getNhanViens().getData(t->t.getMaNV().toLowerCase().contains(value)
                            ||t.getHoTen().toLowerCase().contains(value)
                            ||t.getTenDangNhap().toLowerCase().contains(value)
                            ||(t.getGioiTinh()!=null&& t.getGioiTinh().toLowerCase().contains(value))
                            ||(t.getDiaChi()!=null&&t.getDiaChi().toLowerCase().contains(value))
                            ||t.getChucVu().toLowerCase().contains(value)
                            ||(t.getSoDienThoai()!=null&&String.valueOf(t.getSoDienThoai()).contains(value))
                            ||(t.getNgaySinh()!=null&&String.valueOf(t.getNgaySinh()).contains(value))), header);                    
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

    
    private String taoMaNhanVienMoi()
    {        
        int sl = db.getNhanViens().size()+1;
        while(true)
        {
            String maNV = "NV00"+String.valueOf(sl++);
            if(db.getNhanViens().get(t->t.getMaNV().trim().equalsIgnoreCase(maNV))==null)
                return maNV;
        }
    }
    
    private void resetText()
    {
        btn_huy.setEnabled(false);
        btn_xoa.setEnabled(false);
        btn_datLaiMK.setEnabled(false);
        txt_maNV.setText(taoMaNhanVienMoi());
        txt_diaChi.setText("");
        txt_hoTen.setText("");
        txt_ngaySinh.setText("");
        txt_soDienThoai.setText("");
        txt_tenDangNhap.setText("");       
        idxSua = -1;
        btn_capNhat.setText("Thêm");         
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        dateChooser = new com.raven.datechooser.DateChooser();
        btn_gr_gioiTinh = new javax.swing.ButtonGroup();
        jLabel5 = new javax.swing.JLabel();
        btn_capNhat = new button.Button();
        btn_xoa = new button.Button();
        btn_huy = new button.Button();
        btn_datLaiMK = new button.Button();
        txt_maNV = new textfield.TextField();
        txt_tenDangNhap = new textfield.TextField();
        txt_ngaySinh = new textfield.TextField();
        txt_hoTen = new textfield.TextField();
        rdo_nam = new radio_button.RadioButtonCustom();
        rdo_nu = new radio_button.RadioButtonCustom();
        txt_soDienThoai = new textfield.TextField();
        cbo_chucVu = new combobox.Combobox();
        txt_diaChi = new textfield.TextField();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_nhanVien = new javax.swing.JTable();

        dateChooser.setForeground(new java.awt.Color(45, 153, 174));
        dateChooser.setDateFormat("yyyy-MM-dd");
        dateChooser.setTextRefernce(txt_ngaySinh);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setText("Giới tính");

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

        btn_datLaiMK.setBackground(new java.awt.Color(0, 28, 68));
        btn_datLaiMK.setForeground(new java.awt.Color(255, 255, 255));
        btn_datLaiMK.setText("Đặt lại mật khẩu");
        btn_datLaiMK.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        txt_maNV.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_maNV.setLabelText("Mã nhân viên");

        txt_tenDangNhap.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_tenDangNhap.setLabelText("Tên đăng nhập");

        txt_ngaySinh.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_ngaySinh.setLabelText("Ngày sinh");

        txt_hoTen.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_hoTen.setLabelText("Họ và tên");

        rdo_nam.setBackground(new java.awt.Color(0, 28, 68));
        btn_gr_gioiTinh.add(rdo_nam);
        rdo_nam.setText("Nam");

        rdo_nu.setBackground(new java.awt.Color(0, 28, 68));
        btn_gr_gioiTinh.add(rdo_nu);
        rdo_nu.setText("Nữ");

        txt_soDienThoai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_soDienThoai.setLabelText("Số điện thoại");
        txt_soDienThoai.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_soDienThoaiKeyTyped(evt);
            }
        });

        cbo_chucVu.setLabeText("Chức vụ");

        txt_diaChi.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txt_diaChi.setLabelText("Địa chỉ");

        tbl_nhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        tbl_nhanVien.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_nhanVien.setRowHeight(30);
        tbl_nhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_nhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_nhanVien);

        tableScrollButton1.add(jScrollPane1, java.awt.BorderLayout.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 1188, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(30, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_hoTen, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txt_maNV, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txt_tenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(52, 52, 52)
                                .addComponent(rdo_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(34, 34, 34)
                                .addComponent(rdo_nu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(20, 20, 20))
                            .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(26, 26, 26)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cbo_chucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_datLaiMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(109, 109, 109))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txt_hoTen, txt_maNV});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(txt_tenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_ngaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(txt_maNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(txt_hoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txt_diaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cbo_chucVu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(rdo_nam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(rdo_nu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txt_soDienThoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_capNhat, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_xoa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(30, 30, 30)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btn_datLaiMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_huy, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_nhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_nhanVienMouseClicked
        // TODO add your handling code here:
        idxSua = tbl_nhanVien.getSelectedRow();
        txt_maNV.setText(dtm.getValueAt(idxSua, 0).toString());
        txt_hoTen.setText(dtm.getValueAt(idxSua, 1).toString());
        txt_tenDangNhap.setText(dtm.getValueAt(idxSua, 2).toString());
        txt_ngaySinh.setText(dtm.getValueAt(idxSua, 3).toString());
        String gioiTinh = dtm.getValueAt(idxSua, 4).toString();
        if(gioiTinh!=null)
            if(gioiTinh.equals("Nam"))
                rdo_nam.setSelected(true);
            else
                rdo_nu.setSelected(true);
        txt_soDienThoai.setText(dtm.getValueAt(idxSua, 5).toString());
        txt_diaChi.setText(dtm.getValueAt(idxSua, 6).toString());
        String chucVu = dtm.getValueAt(idxSua, 7).toString();
        if(chucVu.equals("Quản lý"))
            cbo_chucVu.setSelectedIndex(1);
        else
            cbo_chucVu.setSelectedIndex(0);
        
        btn_huy.setEnabled(true);
        btn_xoa.setEnabled(true);        
        btn_xoa.setBackground(ColorCustom.XANH_NHAT);
        btn_huy.setBackground(ColorCustom.TRANG);
        btn_capNhat.setText("Cập nhật");
        btn_datLaiMK.setEnabled(true);
        btn_datLaiMK.setBackground(ColorCustom.XANH_CUC_DAM);
    }//GEN-LAST:event_tbl_nhanVienMouseClicked

    public boolean KTKyTuDatBiet(String s) {     
     Pattern p = Pattern.compile("[^A-Za-z0-9]");
     Matcher m = p.matcher(s);
    // boolean b = m.matches();
    return m.find();
     
 }
    
    private void btn_capNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capNhatActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:    
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        String hoTen = txt_hoTen.getText().trim();               
        if(hoTen.isEmpty())
        {
           // JOptionPane.showMessageDialog(this, "Họ tên nhân viên không được để trống!!!");      
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Họ tên nhân viên không được để trống!!!");
            panel.showNotification();
            txt_hoTen.requestFocus();
            return;
        }      
        String tenDN = txt_tenDangNhap.getText().trim();
        if(tenDN.isEmpty())
        {
           // JOptionPane.showMessageDialog(this, "Tên đăng nhập không được để trống!!!");            
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, " tên đăng nhập không được để trống!!!");
            panel.showNotification();
            txt_tenDangNhap.requestFocus();
            return;
        }     
        if (KTKyTuDatBiet(tenDN))
        {
            //JOptionPane.showMessageDialog(this, "Tên đăng nhập không được chứa ký tự đặt biệt!!!");
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Tên đăng nhập không được chứa ký tự đặt biệt!!!");
            panel.showNotification();
            txt_tenDangNhap.setText("");
            txt_tenDangNhap.requestFocus();    
            return;
        }
        if(idxSua == -1 && db.getNhanViens().get(t->t.getTenDangNhap().equalsIgnoreCase(tenDN)) != null)
        {
            //JOptionPane.showMessageDialog(this, "Tên đăng nhập này đã tồn tại, vui lòng chọn 1 tên khác!!!");
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Tên đăng nhập này đã tồn tại, vui lòng chọn 1 tên khác!!!");
            panel.showNotification();
            txt_tenDangNhap.setText("");
            txt_tenDangNhap.requestFocus();
            return;
        }                       
        String diaChi = txt_diaChi.getText().trim();        
        String soDienThoai = txt_soDienThoai.getText().trim();        
        if(!soDienThoai.isEmpty() && soDienThoai.length()!=10)
        {
            //JOptionPane.showMessageDialog(this, "Số điện thoại phải có đủ 10 kí tự!!!");          
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Số điện thoại phải có đủ 10 kí tự!!!");
            panel.showNotification();
            txt_soDienThoai.requestFocus();
            return;
        }
        String gioiTinh = rdo_nam.isSelected() ? "Nam":"Nữ";
        String chucVu = cbo_chucVu.getSelectedItem().toString();
        String ngayS = txt_ngaySinh.getText().trim();
        Date ngaySinh = ngayS.isEmpty() ? null : Date.valueOf(ngayS);        
        LocalDate dateNow = LocalDate.now();
        LocalDate ns = LocalDate.parse(ngayS);
        if(dateNow.getYear()<ns.getYear())
        {
            //JOptionPane.showMessageDialog(this, "Ngày sinh phải nhỏ hơn ngày hiện tại!!!");  
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Ngày sinh phải nhỏ hơn ngày hiện tại!!!");
            panel.showNotification();
            txt_ngaySinh.setText("");
            txt_ngaySinh.requestFocus();
            return;
        }
        if(dateNow.getYear()-ns.getYear()<18)
        {
            //JOptionPane.showMessageDialog(this, "Nhân viên phải đủ 18 tuổi trở lên!!!");  
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Nhân viên phải đủ 18 tuổi trở lên!!!");
            panel.showNotification();
            txt_ngaySinh.setText("");
            txt_ngaySinh.requestFocus();
            return;
        }
        String maNV = txt_maNV.getText();
        if(idxSua!=-1)
        {
            String chucVuCu = db.getNhanViens().get(n->n.getMaNV().equals(maNV)).getChucVu();
            if(chucVuCu.equals("Quản lý")&&chucVu.equals("Nhân viên"))
            {
                //JOptionPane.showMessageDialog(this, "Bạn không có quyền gián chức 1 quản lý nào cả!!!");          
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Bạn không có quyền gián chức 1 quản lý nào cả!!!");
            panel.showNotification();
                return;
            }
        }
        String thongBao = idxSua == -1? "Xác nhận tạo thêm 1 nhân viên có quyền "+chucVu : "Xác nhận cập nhật thông tin nhân viên này?";
        if(JOptionPane.showConfirmDialog(this, thongBao,"Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {        
            boolean chk;
            NhanVien nv;
            if(idxSua == -1)
            {                
                nv = new NhanVien(maNV, hoTen, tenDN, ngaySinh, gioiTinh, soDienThoai, diaChi, chucVu);
                chk =  db.getNhanViens().insert(nv);            
            }
            else
            {
                nv = db.getNhanViens().get(t->t.getMaNV().equalsIgnoreCase(maNV));
                nv.setHoTen(hoTen);
                nv.setTenDangNhap(tenDN);
                nv.setNgaySinh(ngaySinh);
                nv.setGioiTinh(gioiTinh);
                nv.setChucVu(chucVu);
                nv.setDiaChi(diaChi);
                nv.setSoDienThoai(soDienThoai);
                chk = db.getNhanViens().update(nv);
            }                         
            if(chk)            
            {                                                            
                if(idxSua==-1)
                    dtm.addRow(nv.getParameter("MatKhau"));
                else
                {
                    dtm.removeRow(idxSua);
                    dtm.insertRow(idxSua, nv.getParameter("MatKhau"));                    
                }                
                //JOptionPane.showMessageDialog(this, "Thao tác thành công");
                Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "Thao tác thành công");
            panel.showNotification();
                resetText();
            }
            else
                //JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi, vui lòng thử lại!");
            {
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Đã xảy ra lỗi, vui lòng thử lại!");
            panel.showNotification();
            }
        }
    }//GEN-LAST:event_btn_capNhatActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        // TODO add your handling code here:
                // TODO add your handling code here:
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        String maNV = txt_maNV.getText();
        if(this.user.getMaNV().equalsIgnoreCase(maNV))
        {
            //JOptionPane.showMessageDialog(this, "Bạn không thể xóa chính mình");
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Bạn không thể xóa chình mình");
            panel.showNotification();
            return;
        }
        NhanVien nvXoa = db.getNhanViens().get(n->n.getMaNV().equalsIgnoreCase(maNV));
        if(nvXoa.getChucVu().equals("Quản lý"))
        {
            //JOptionPane.showMessageDialog(this, "Bạn không có quyền xóa tải khoản quản lý!!!");
            Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "Bạn không có quyền xóa tài khoản quản lý");
            panel.showNotification();
            return;
        }
            
        String tenDN = dtm.getValueAt(idxSua, 2).toString();
        String hoTen = dtm.getValueAt(idxSua, 1).toString();
        if(JOptionPane.showConfirmDialog(this, "Xác nhận xóa nhân viên '"+hoTen+"' có tên đăng nhập '"+tenDN+"'?","Thông báo",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
        {      
            if(db.getNhanViens().delete(nvXoa))
            {                
                dtm.removeRow(idxSua);
                resetText();
                //JOptionPane.showMessageDialog(this, "Xóa thành công!!!");
                Notification panel = new Notification(frame, Notification.Type.SUCCESS, Notification.Location.TOP_RIGHT, "xóa thành công");
            panel.showNotification();
            }
            else
            {
                Notification panel = new Notification(frame, Notification.Type.WARNING, Notification.Location.TOP_RIGHT, "xóa không thành công");
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

    private void txt_soDienThoaiKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_soDienThoaiKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()<'0' || evt.getKeyChar() > '9')
            evt.consume();
        if(txt_soDienThoai.getText().length()==10)
            evt.consume();
    }//GEN-LAST:event_txt_soDienThoaiKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private button.Button btn_capNhat;
    private button.Button btn_datLaiMK;
    private javax.swing.ButtonGroup btn_gr_gioiTinh;
    private button.Button btn_huy;
    private button.Button btn_xoa;
    private combobox.Combobox cbo_chucVu;
    private com.raven.datechooser.DateChooser dateChooser;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private radio_button.RadioButtonCustom rdo_nam;
    private radio_button.RadioButtonCustom rdo_nu;
    private table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tbl_nhanVien;
    private textfield.TextField txt_diaChi;
    private textfield.TextField txt_hoTen;
    private textfield.TextField txt_maNV;
    private textfield.TextField txt_ngaySinh;
    private textfield.TextField txt_soDienThoai;
    private textfield.TextField txt_tenDangNhap;
    // End of variables declaration//GEN-END:variables
}
