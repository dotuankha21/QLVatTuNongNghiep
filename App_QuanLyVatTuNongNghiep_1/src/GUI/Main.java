/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import MeThods.KetNoi;
import Model.ArrayLists;
import Model.ChiTietDatHang;
import Model.ChiTietGiaoHang;
import Model.DBQuanLyVatTuNongNghiep;
import Model.GetFieldName;
import Model.HoaDon;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.PhieuGiaoHang;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.ObjectInputStream;
import java.io.ObjectInputStream.GetField;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import textfield.EventCallBack;
import textfield.EventTextField;

/**
 *
 * @author OP15
 */
public class Main extends javax.swing.JFrame {

    /**
     * Creates new form Main
     */
    DBQuanLyVatTuNongNghiep db = null;
    NhanVien user = null;
    private JPanel childPanel;    
    public Main(DBQuanLyVatTuNongNghiep db, NhanVien user) {
        initComponents();        
        this.db = db;
        this.user = user;
        
        
        
        KhoiTao();
        TrangChu1MouseClicked(null);                    
        
    }
    public Main()
    {
        initComponents();                
        this.db = new DBQuanLyVatTuNongNghiep();       
        user = db.getNhanViens().get(0);            
        
        
        
        KhoiTao();
        TrangChu1MouseClicked(null);            
    }
    
    private void KhoiTao()
    {
//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
                
        this.setSize(1500, 1000);
        this.setLocationRelativeTo(this);              
        this.setResizable(false);
        
        RunChuong rc = new RunChuong();
        rc.start();
        
        if(user.getChucVu().equals("Nhân Viên"))
            QuanLyNhanVien.setVisible(false);
        label_taiKhoan.setText(user.getHoTen());
        jLabel2.setIcon(ResizeImage("/Report/cherry.jpg"));
        this.getContentPane().setComponentZOrder(menu, 0);        
        //this.getContentPane().setComponentZOrder(this.childPanel, 1);
        this.repaint();
//        this.menu.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseEntered(MouseEvent e) {
//                // Ẩn bảng trong JPanel
//                Main.this.childPanel.setVisible(false);
//            }
//
//            @Override
//            public void mouseExited(MouseEvent e) {
//                // Hiển thị lại bảng trong JPanel
//                Main.this.childPanel.setVisible(true);
//            }
//        });
//        this.menu.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentShown(ComponentEvent e) {
//                super.componentShown(e);
//                int width = (int) menu.getSize().getWidth();
//                if(width>300)
//                    Main.this.Body.setVisible(false); // Ẩn bảng khi panel mới hiển thị
//                else
//                     Main.this.Body.setVisible(true); // Hiển thị lại bảng khi panel mới bị ẩn
//            }
//
//            @Override
//            public void componentHidden(ComponentEvent e) {
//                super.componentHidden(e);
//               
//            }
//        });
                         
    }
    
    ImageIcon ResizeImage(String path)
    {
        ImageIcon myImg = new ImageIcon(path);
        Image img = myImg.getImage();
        Image newImg = img.getScaledInstance(jLabel2.getWidth(), jLabel2.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon image = new ImageIcon(newImg);
        return image;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tableCustom1 = new table.TableCustom();
        tableCustom2 = new table.TableCustom();
        menu = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        QuanLyHangHoa = new javax.swing.JLabel();
        QuanLyBanHang = new javax.swing.JLabel();
        DanhMucHoaDon = new javax.swing.JLabel();
        QuanLyNhaCungCap = new javax.swing.JLabel();
        TaiKhoanCaNhan = new javax.swing.JLabel();
        QuanLyNguonCung = new javax.swing.JLabel();
        Thongke = new javax.swing.JLabel();
        QuanLyNhanVien = new javax.swing.JLabel();
        CloseMenu = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        DangXuat = new javax.swing.JLabel();
        TrangChu1 = new javax.swing.JLabel();
        DatHang = new javax.swing.JLabel();
        QuanLyDonDatHang = new javax.swing.JLabel();
        PhieuGiaoHang = new javax.swing.JLabel();
        label_thongBao = new javax.swing.JLabel();
        QuanLyLoaiHang = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        OpenMEnu = new javax.swing.JLabel();
        label_taiKhoan = new javax.swing.JLabel();
        label_nhacnho = new javax.swing.JLabel();
        home2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        txt_timKiem = new textfield.TextFieldAnimation();
        Body = new javax.swing.JPanel();
        tableScrollButton1 = new table.TableScrollButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Xin chào");
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        setPreferredSize(new java.awt.Dimension(1500, 1000));

        menu.setBackground(new java.awt.Color(45, 153, 174));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Screenshot 2023-04-01 161920.png"))); // NOI18N

        QuanLyHangHoa.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        QuanLyHangHoa.setForeground(new java.awt.Color(255, 255, 255));
        QuanLyHangHoa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/sanphamcjinh.png"))); // NOI18N
        QuanLyHangHoa.setText("           Hàng hóa");
        QuanLyHangHoa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuanLyHangHoaMouseClicked(evt);
            }
        });

        QuanLyBanHang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        QuanLyBanHang.setForeground(new java.awt.Color(255, 255, 255));
        QuanLyBanHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Add to basket.png"))); // NOI18N
        QuanLyBanHang.setText("           Bán Hàng");
        QuanLyBanHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuanLyBanHangMouseClicked(evt);
            }
        });

        DanhMucHoaDon.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        DanhMucHoaDon.setForeground(new java.awt.Color(255, 255, 255));
        DanhMucHoaDon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/bill.png"))); // NOI18N
        DanhMucHoaDon.setText("   Danh Mục Hóa Đơn");
        DanhMucHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DanhMucHoaDonMouseClicked(evt);
            }
        });

        QuanLyNhaCungCap.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        QuanLyNhaCungCap.setForeground(new java.awt.Color(255, 255, 255));
        QuanLyNhaCungCap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/kk.png"))); // NOI18N
        QuanLyNhaCungCap.setText("  Quản Lý Nhà Cung Cấp");
        QuanLyNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuanLyNhaCungCapMouseClicked(evt);
            }
        });

        TaiKhoanCaNhan.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        TaiKhoanCaNhan.setForeground(new java.awt.Color(255, 255, 255));
        TaiKhoanCaNhan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/account.png"))); // NOI18N
        TaiKhoanCaNhan.setText("           Tài Khoản");
        TaiKhoanCaNhan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TaiKhoanCaNhanMouseClicked(evt);
            }
        });

        QuanLyNguonCung.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        QuanLyNguonCung.setForeground(new java.awt.Color(255, 255, 255));
        QuanLyNguonCung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/kho.png"))); // NOI18N
        QuanLyNguonCung.setText("     Nguồn cung vật tư");
        QuanLyNguonCung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuanLyNguonCungMouseClicked(evt);
            }
        });

        Thongke.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        Thongke.setForeground(new java.awt.Color(255, 255, 255));
        Thongke.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Statistics.png"))); // NOI18N
        Thongke.setText("           Thống Kê");
        Thongke.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThongkeMouseClicked(evt);
            }
        });

        QuanLyNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        QuanLyNhanVien.setForeground(new java.awt.Color(255, 255, 255));
        QuanLyNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Users.png"))); // NOI18N
        QuanLyNhanVien.setText("     Quản Lý Nhân Viên");
        QuanLyNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuanLyNhanVienMouseClicked(evt);
            }
        });

        CloseMenu.setForeground(new java.awt.Color(255, 255, 255));
        CloseMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/delete-icon.png"))); // NOI18N
        CloseMenu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CloseMenuMouseClicked(evt);
            }
        });

        DangXuat.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        DangXuat.setForeground(new java.awt.Color(255, 255, 255));
        DangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/User-Interface-Login-icon.png"))); // NOI18N
        DangXuat.setText("Đăng Xuất");
        DangXuat.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DangXuatMouseClicked(evt);
            }
        });

        TrangChu1.setFont(new java.awt.Font("Bungee Inline", 0, 24)); // NOI18N
        TrangChu1.setForeground(new java.awt.Color(255, 255, 255));
        TrangChu1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Home.png"))); // NOI18N
        TrangChu1.setText("            Trang Chủ");
        TrangChu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TrangChu1MouseClicked(evt);
            }
        });

        DatHang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        DatHang.setForeground(new java.awt.Color(255, 255, 255));
        DatHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Add.png"))); // NOI18N
        DatHang.setText("             Đặt hàng");
        DatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DatHangMouseClicked(evt);
            }
        });

        QuanLyDonDatHang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        QuanLyDonDatHang.setForeground(new java.awt.Color(255, 255, 255));
        QuanLyDonDatHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Notes.png"))); // NOI18N
        QuanLyDonDatHang.setText("     Quản lý đơn đặt hàng");
        QuanLyDonDatHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuanLyDonDatHangMouseClicked(evt);
            }
        });

        PhieuGiaoHang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        PhieuGiaoHang.setForeground(new java.awt.Color(255, 255, 255));
        PhieuGiaoHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Price list.png"))); // NOI18N
        PhieuGiaoHang.setText("        Phiếu giao hàng");
        PhieuGiaoHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PhieuGiaoHangMouseClicked(evt);
            }
        });

        label_thongBao.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Chuong7.png"))); // NOI18N

        QuanLyLoaiHang.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        QuanLyLoaiHang.setForeground(new java.awt.Color(255, 255, 255));
        QuanLyLoaiHang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/List.png"))); // NOI18N
        QuanLyLoaiHang.setText("     Quản Lý Loại Hàng");
        QuanLyLoaiHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                QuanLyLoaiHangMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                        .addComponent(CloseMenu)
                        .addGap(35, 35, 35))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(94, 94, 94))))
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(DangXuat, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(44, 44, 44))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(QuanLyBanHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(menuLayout.createSequentialGroup()
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(TrangChu1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                                    .addComponent(QuanLyHangHoa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(31, 31, 31)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, menuLayout.createSequentialGroup()
                                    .addComponent(PhieuGiaoHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(121, 121, 121))
                                .addComponent(DanhMucHoaDon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(QuanLyNhaCungCap, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(menuLayout.createSequentialGroup()
                                    .addComponent(QuanLyDonDatHang, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGap(31, 31, 31))
                                .addComponent(Thongke, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(TaiKhoanCaNhan, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(QuanLyNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(menuLayout.createSequentialGroup()
                                    .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(DatHang, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(QuanLyNguonCung, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(label_thongBao)
                                    .addGap(64, 64, 64)))
                            .addComponent(QuanLyLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(CloseMenu)
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(35, 35, 35)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(TrangChu1)
                .addGap(18, 18, 18)
                .addComponent(QuanLyBanHang)
                .addGap(18, 18, 18)
                .addComponent(QuanLyHangHoa)
                .addGap(18, 18, 18)
                .addComponent(QuanLyLoaiHang)
                .addGap(18, 18, 18)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(label_thongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addComponent(DanhMucHoaDon)
                        .addGap(18, 18, 18)
                        .addComponent(QuanLyNhaCungCap)
                        .addGap(18, 18, 18)
                        .addComponent(QuanLyNguonCung)
                        .addGap(18, 18, 18)
                        .addComponent(DatHang)
                        .addGap(18, 18, 18)
                        .addComponent(QuanLyDonDatHang)
                        .addGap(18, 18, 18)
                        .addComponent(PhieuGiaoHang)
                        .addGap(12, 12, 12)
                        .addComponent(Thongke)
                        .addGap(18, 18, 18)
                        .addComponent(TaiKhoanCaNhan)
                        .addGap(18, 18, 18)
                        .addComponent(QuanLyNhanVien)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(DangXuat)
                .addGap(31, 31, 31))
        );

        jPanel1.setBackground(new java.awt.Color(12, 87, 118));

        OpenMEnu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/menu-icon.png"))); // NOI18N
        OpenMEnu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                OpenMEnuMouseClicked(evt);
            }
        });

        label_taiKhoan.setFont(new java.awt.Font("Segoe UI", 2, 20)); // NOI18N
        label_taiKhoan.setForeground(new java.awt.Color(255, 255, 255));
        label_taiKhoan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/User.png"))); // NOI18N
        label_taiKhoan.setText("Lê Phát Đạt");
        label_taiKhoan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                label_taiKhoanMouseClicked(evt);
            }
        });

        label_nhacnho.setBackground(new java.awt.Color(255, 153, 153));
        label_nhacnho.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        label_nhacnho.setForeground(new java.awt.Color(255, 102, 102));
        label_nhacnho.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        label_nhacnho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/chuong5.png"))); // NOI18N

        home2.setForeground(new java.awt.Color(255, 255, 255));
        home2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Hinh/Images/Home.png"))); // NOI18N
        home2.setText("Trang chủ");
        home2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                home2MouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Bungee Inline", 0, 26)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(248, 218, 208));
        jLabel1.setText("X");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        txt_timKiem.setHintText("Tìm kiếm ...");
        txt_timKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timKiemActionPerformed(evt);
            }
        });
        txt_timKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txt_timKiemKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(OpenMEnu)
                .addGap(0, 0, 0)
                .addComponent(label_nhacnho)
                .addGap(350, 350, 350)
                .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 735, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(53, 53, 53)
                .addComponent(home2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(label_taiKhoan)
                .addGap(38, 38, 38)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(73, 73, 73))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(OpenMEnu)
                    .addComponent(label_nhacnho)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(home2)
                            .addComponent(label_taiKhoan)
                            .addComponent(jLabel1)
                            .addComponent(txt_timKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(10, 10, 10))
        );

        Body.setBackground(new java.awt.Color(255, 255, 255));
        Body.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BodyMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout BodyLayout = new javax.swing.GroupLayout(Body);
        Body.setLayout(BodyLayout);
        BodyLayout.setHorizontalGroup(
            BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BodyLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 563, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        BodyLayout.setVerticalGroup(
            BodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(BodyLayout.createSequentialGroup()
                .addGap(103, 103, 103)
                .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(684, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(Body, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Body, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
void open_Menu()
{
    new Thread(new Runnable()
    {
        @Override
        public void run() {  
     
            int width = (int) Body.getSize().getWidth();
            int height = (int) Body.getSize().getHeight();
            int toadoY = (int) Body.getLocation().getY(); 
            //Body.setEnabled(false);
            Body.setVisible(false);
            for(int i=0;i<=400;i+=10)
            {
                try {
                    menu.setSize(i, (int) menu.getSize().getHeight());
                    //Body.setSize(--width, height);
                    //Body.setLocation(i, toadoY);
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }            
    }).start();
    //OpenMEnu.setVisible(false);
}
    private synchronized void close_Menu(int s)
    {
        new Thread(new Runnable()
        {
            @Override
            public synchronized void run() {
                Body.setVisible(true); 
                int width = (int) Body.getSize().getWidth();
                int height = (int) Body.getSize().getHeight();
                int toadoY = (int) Body.getLocation().getY();  
                //Body.setEnabled(true);
                //Body.setSize(400+width, height);                
                for(int i=400;i>=0;i=i-s)
                {
                    
                    try {
                        menu.setSize(i, (int) menu.getSize().getHeight()); 
                        //Body.setLocation(i, toadoY);
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    
                }                
            }
            
        }
        ).start();
        //OpenMEnu.setVisible(true);
    }
    
    private void runChuong()
    {
        new Thread(new Runnable()
        {
            @Override            
            public synchronized void run() {
                
                int x = (int) label_thongBao.getLocation().getX();
                int y = (int) label_thongBao.getLocation().getY();                
                while(true)
                {
                    for (int i = 0; i <= 3; i++) {
                        try {
                            label_thongBao.setLocation(x++, y);
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }  
                    for (int i = 3; i >= 0; i--) {
                        try {
                            label_thongBao.setLocation(x--, y);
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                }
            }
            
        }
        ).start();
    }
    
    //1544.692
    private void CloseMenuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CloseMenuMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
          Body.setVisible(true); 
    }//GEN-LAST:event_CloseMenuMouseClicked
    void reSetClickFrom()
    {        
        Body.setVisible(true); 
        Body.removeAll();
        Body.setLayout(new BorderLayout());
        Body.add(childPanel);
        Body.validate();
        Body.repaint();
    }
    
    private void QuanLyBanHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuanLyBanHangMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showBanHang();
    }//GEN-LAST:event_QuanLyBanHangMouseClicked

    public void showBanHang()
    {          
        childPanel = new BanHang_GUI(db,user);
        reSetClickFrom();
    }
    private void QuanLyHangHoaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuanLyHangHoaMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showHangHoa();
    }//GEN-LAST:event_QuanLyHangHoaMouseClicked
    public void showHangHoa()
    {
        childPanel = new QuanLyHangHoa_GUI(db, user);
        reSetClickFrom();       
    }
    private void DanhMucHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DanhMucHoaDonMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showHoaDon();
    }//GEN-LAST:event_DanhMucHoaDonMouseClicked
    public void showHoaDon()
    {
        childPanel = new QuanLyHoaDon_GUI(db,user, txt_timKiem);
        reSetClickFrom();       
    }
    private void QuanLyNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuanLyNhaCungCapMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showNhaCungCap();
    }//GEN-LAST:event_QuanLyNhaCungCapMouseClicked
    public void showNhaCungCap()
    {
        childPanel = new QuanLyNhaCungCap_GUI(db, user,txt_timKiem);
        reSetClickFrom();       
    }
    private void QuanLyNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuanLyNhanVienMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showNhanVien();
    }//GEN-LAST:event_QuanLyNhanVienMouseClicked
    public void showNhanVien()
    {
        childPanel = new QuanLyNhanVien_GUI(db, user,txt_timKiem);
        reSetClickFrom();      
    }
    private void TaiKhoanCaNhanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TaiKhoanCaNhanMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showCaNhan();
    }//GEN-LAST:event_TaiKhoanCaNhanMouseClicked
    public void showCaNhan()
    {
        childPanel = new ThongTinCaNhan_GUI(db, user);
        reSetClickFrom();        
    }
    private void QuanLyNguonCungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuanLyNguonCungMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
         showNguonCung();
    }//GEN-LAST:event_QuanLyNguonCungMouseClicked
    public void showNguonCung()
    {
        childPanel = new QuanLyNguonCung_GUI(db, user,txt_timKiem);
        reSetClickFrom();       
    }
    private void ThongkeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ThongkeMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showThongKe();
    }//GEN-LAST:event_ThongkeMouseClicked

    public void showThongKe()
    {
        childPanel = new ThongKe_GUI(db, user,txt_timKiem);
        reSetClickFrom();       
    }
    private void DangXuatMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DangXuatMouseClicked
        // TODO add your handling code here:        
        new DangNhap_GUI().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_DangXuatMouseClicked
    
    private void OpenMEnuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_OpenMEnuMouseClicked
        // TODO add your handling code here:
        open_Menu();
        label_nhacnho.setVisible(false);
    }//GEN-LAST:event_OpenMEnuMouseClicked

    private void BodyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_BodyMouseClicked
        // TODO add your handling code here:
        if(menu.getSize().getWidth()==0)
            return;
        close_Menu(10);
    }//GEN-LAST:event_BodyMouseClicked

    private void TrangChu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TrangChu1MouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        childPanel=new TrangChu_GUI(db,user,txt_timKiem,this);
        reSetClickFrom();
    }//GEN-LAST:event_TrangChu1MouseClicked

    private void DatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_DatHangMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showDatHang();
    }//GEN-LAST:event_DatHangMouseClicked
public void showDatHang()
    {
        childPanel=new DatHang_GUI(db, user,txt_timKiem);
        reSetClickFrom();        
    }
    private void QuanLyDonDatHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuanLyDonDatHangMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showDonDatHang();
    }//GEN-LAST:event_QuanLyDonDatHangMouseClicked
public void showDonDatHang()
    {
       childPanel=new QuanLyPhieuDatHang_GUI(db,user);
        reSetClickFrom();       
    }
    private void PhieuGiaoHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PhieuGiaoHangMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showPhieuGiaoHang();
    }//GEN-LAST:event_PhieuGiaoHangMouseClicked
public void showPhieuGiaoHang()
    {
        label_thongBao.setVisible(false);
        childPanel=new QuanLyPhieuGiaoHang_GUI(db,txt_timKiem);
        reSetClickFrom();       
    }
    private void home2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_home2MouseClicked
        // TODO add your handling code here:
        childPanel=new TrangChu_GUI(db,user,txt_timKiem,this);
        Body.removeAll();
        Body.setLayout(new BorderLayout());
        Body.add(childPanel);
        Body.validate();
        Body.repaint();
    }//GEN-LAST:event_home2MouseClicked

    private void label_taiKhoanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_label_taiKhoanMouseClicked
        // TODO add your handling code here:
        childPanel = new ThongTinCaNhan_GUI(db, user);
        Body.removeAll();
        Body.setLayout(new BorderLayout());
        Body.add(childPanel);
        Body.validate();
        Body.repaint();
    }//GEN-LAST:event_label_taiKhoanMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txt_timKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timKiemActionPerformed
        // TODO add your handling code here:        
    }//GEN-LAST:event_txt_timKiemActionPerformed

    private void txt_timKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyTyped
        // TODO add your handling code here:        
    }//GEN-LAST:event_txt_timKiemKeyTyped

    private void txt_timKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyReleased
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_timKiemKeyReleased

    private void txt_timKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_timKiemKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyChar()==KeyEvent.VK_ENTER)
            txt_timKiem.callEvent();
    }//GEN-LAST:event_txt_timKiemKeyPressed

    private void QuanLyLoaiHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_QuanLyLoaiHangMouseClicked
        // TODO add your handling code here:
        close_Menu(10);
        showLoaiHang();
    }//GEN-LAST:event_QuanLyLoaiHangMouseClicked

    public void showLoaiHang()
    {
        label_thongBao.setVisible(false);
        childPanel=new QuanLyLoaiHang_GUI(db,user,txt_timKiem);
        reSetClickFrom();       
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
    
    
    class Chuong extends Thread
    {

        @Override
        public void run() {
            super.run();
            label_thongBao.setVisible(false);
            label_nhacnho.setVisible(false);
            int x = (int) label_thongBao.getLocation().getX();
                int y = (int) label_thongBao.getLocation().getY();                
                while(true)
                {                    
                    for (int i = 0; i <= 3; i++) {
                        try {
                            label_thongBao.setLocation(x++, y);
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }  
                    for (int i = 3; i >= 0; i--) {
                        try {
                            label_thongBao.setLocation(x--, y);
                            Thread.sleep(1);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } 
                }
        }
        
    }
    
    
    class RunChuong extends Thread
    {

        @Override
        public void run() {
            super.run();
            new Chuong().start();
            while(true)
            {
                try {                    
//                    if(db.getPhieuGiaoHangs().get(p->p.getTrangThai().equals("Chờ xác nhận"))==null)
//                    {
//                        label_thongBao.setVisible(false);
//                        label_nhacnho.setVisible(false);
//                    }
//                    else
//                    {
//                        label_thongBao.setVisible(true);
//                        label_nhacnho.setVisible(true);
//                    }
                    ArrayList<HashMap> data = KetNoi.getInstance().TruyVans("select * from PhieuGiaoHang");
                    if(data.size()>db.getPhieuGiaoHangs().size())
                    {                                                    
                        String sqlWhere = "";                        
                        for (HashMap hm : data) 
                        {
                            if(db.getPhieuGiaoHangs().get(p->p.getSoPhieuGiao().equals((String)hm.get("SoPhieuGiao")))==null)
                            {
                                PhieuGiaoHang pghMoi = new PhieuGiaoHang(hm,db.getTask());
                                db.getPhieuGiaoHangs().add(pghMoi);
                                
                                ArrayList<HashMap> data_ct = KetNoi.getInstance().TruyVans("select * from ChiTietGiaoHang where SoPhieuGiao = '"+pghMoi.getSoPhieuGiao()+"'");
                                for (HashMap hm_ct : data_ct)
                                {
                                    ChiTietGiaoHang ctghMoi = new ChiTietGiaoHang(hm_ct, db.getTask());
                                    db.getChiTietGiaoHangs().add(ctghMoi);
                                    db.lienKetChiTietGiaoHang_FK(ctghMoi);
                                }
                                db.lienKetPhieuGiaoHang_FK(pghMoi);
                                db.lienKetPhieuGiaoHang_PK(pghMoi);
                            }
                        }
                        if(childPanel instanceof QuanLyPhieuGiaoHang_GUI == false)
                        {
                            label_thongBao.setVisible(true);
                            label_nhacnho.setVisible(true); 
                        }
                        else
                        {
                            ((QuanLyPhieuGiaoHang_GUI)childPanel).ThongBaoCoPhieuGiaoHangMoi();
                        }
                    }
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Body;
    private javax.swing.JLabel CloseMenu;
    private javax.swing.JLabel DangXuat;
    private javax.swing.JLabel DanhMucHoaDon;
    private javax.swing.JLabel DatHang;
    private javax.swing.JLabel OpenMEnu;
    private javax.swing.JLabel PhieuGiaoHang;
    private javax.swing.JLabel QuanLyBanHang;
    private javax.swing.JLabel QuanLyDonDatHang;
    private javax.swing.JLabel QuanLyHangHoa;
    private javax.swing.JLabel QuanLyLoaiHang;
    private javax.swing.JLabel QuanLyNguonCung;
    private javax.swing.JLabel QuanLyNhaCungCap;
    private javax.swing.JLabel QuanLyNhanVien;
    private javax.swing.JLabel TaiKhoanCaNhan;
    private javax.swing.JLabel Thongke;
    private javax.swing.JLabel TrangChu1;
    private javax.swing.JLabel home2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel label_nhacnho;
    private javax.swing.JLabel label_taiKhoan;
    private javax.swing.JLabel label_thongBao;
    private javax.swing.JPanel menu;
    private table.TableCustom tableCustom1;
    private table.TableCustom tableCustom2;
    private table.TableScrollButton tableScrollButton1;
    private textfield.TextFieldAnimation txt_timKiem;
    // End of variables declaration//GEN-END:variables
}
