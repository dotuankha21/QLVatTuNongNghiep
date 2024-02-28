/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package GUI;

import GUI.Chart.DatabaseConnection;
import GUI.Chart.ModelData;
import Model.ArrayLists;
import Model.ChiTietDatHang;
import Model.ChiTietGiaoHang;
import Model.ChiTietHoaDon;
import Model.ChiTietLoaiHang;
import Model.DBQuanLyVatTuNongNghiep;
import Model.GetFieldName;
import Model.GiaMatHang;
import Model.HangHoa;
import Model.HoaDon;
import Model.KhachHang;
import Model.LoaiHang;
import Model.NguonHang;
import Model.NhaCungCap;
import Model.NhanVien;
import Model.PhieuDatHang;
import Model.PhieuGiaoHang;
import com.raven.datechooser.EventDateChooser;
import com.raven.datechooser.SelectedAction;
import com.raven.datechooser.SelectedDate;
import java.awt.Color;
import java.awt.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import raven.chart.ModelChart;
import table.TableCustom;
import textfield.EventCallBack;
import textfield.EventTextField;
import textfield.TextFieldAnimation;

/**
 *
 * @author OP15
 */
public class TrangChu_GUI extends javax.swing.JPanel {

    /**
     * Creates new form TrangSanPham_GUI
     */
    DBQuanLyVatTuNongNghiep db = null;
    NhanVien user;    
    TextFieldAnimation txt_timKiem = null;
    Main main = null;
        public TrangChu_GUI(DBQuanLyVatTuNongNghiep db,NhanVien user,TextFieldAnimation txt_timKiem,Main main) {
        initComponents();
        this.db = db;
        this.user = user; 
        this.txt_timKiem = txt_timKiem;
        this.main = main;
        
        chart_n.addLegend("Hàng hóa 1", new Color(12, 84, 175), new Color(0, 108, 247));
        chart_n.addLegend("Hàng hóa 2", new Color(54, 4, 143), new Color(104, 49, 200));
        chart_n.addLegend("Hàng hóa 3", new Color(5, 125, 0), new Color(95, 209, 69));        
//        chart_n.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));  
//        chart_n.addLegend("Cost", new Color(136, 17, 37), new Color(241, 10, 120));  
//        
//        
//        chart_n.addData(new com.raven.chart.ModelChart("January", new double[]{500, 200, 80, 89,23}));
//        chart_n.addData(new com.raven.chart.ModelChart("February", new double[]{1000, 750, 90, 150,35}));
//        chart_n.addData(new com.raven.chart.ModelChart("March", new double[]{200, 350, 460, 900,345}));
//        chart_n.addData(new com.raven.chart.ModelChart("April", new double[]{480, 150, 750, 700,546}));
//        chart_n.addData(new com.raven.chart.ModelChart("May", new double[]{350, 540, 300, 150,546}));
//        chart_n.addData(new com.raven.chart.ModelChart("June", new double[]{190, 280, 81, 200,678}));
//        chart_n.start();
        txt_batDau.setText(LocalDate.now().minusDays(90).format(DateTimeFormatter.ofPattern(chonNgayBatDau.getDateFormat())));
        top3SanPhambanChay();                                
        khoitaodothi();
        khoiTao();
        
    }
        private void khoitaodothi()
    {
            CHART.setTitle("Con đường tương lai của cửa hàng bạn");
            CHART.addLegend("Tất cả", Color.decode("#7b4397"), Color.decode("#dc2430")); 
            loadBieuDo();
    }
//    private void setData() {
//        try {            
//            List<ModelData> lists = new ArrayList<>();
////            DatabaseConnection.getInstance().Open();
////            String sql = "SELECT MONTH(NgayMua) AS'MONTH',SUM(CT.SoLuong) AS 'TONG SO LUONG' FROM HoaDon HD,ChiTietHoaDon CT WHERE HD.SoHoaDon=CT.SoHoaDon GROUP BY MONTH(NgayMua) ORDER BY MONTH(NgayMua) ASC";
////            PreparedStatement p = DatabaseConnection.getInstance().getConnection().prepareStatement(sql);
////            ResultSet r = p.executeQuery();
////            while (r.next()) {
////                String month = r.getString("MONTH");
////                double count_soluong = r.getDouble("TONG SO LUONG");
////                
////                lists.add(new ModelData(month,count_soluong));
////            }
////            r.close();
////            p.close();
//            lists.add(new ModelData("tháng 1",3));
//            lists.add(new ModelData("tháng 3",3));
//            lists.add(new ModelData("tháng 4",4));
//            lists.add(new ModelData("tháng 5",7));
//            lists.add(new ModelData("tháng 6",99));
//            //  Add Data to chart
//            for (int i = lists.size() - 1; i >= 0; i--) {
//                ModelData d = lists.get(i);
//                CHART.addData(new ModelChart(d.getMonth(), new double[]{d.getSoluong()}));
//            }
//            //  Start to show data with animation
//            CHART.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void loadBieuDo()
    {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(chonNgayBatDau.getDateFormat());
//        
//        LocalDate batdau= LocalDate.parse(txt_batDau.getText(), formatter);
//        LocalDate ketthuc= LocalDate.parse(txt_ketThuc.getText(), formatter);

//        Period period = Period.between(date1, date2);
//
//        int diffYears = period.getYears();
//        int diffMonths = period.getMonths();
//        int diffDays = period.getDays();
        
        LocalDate batdau = null;
        for(HoaDon hd: db.getHoaDons())
            if(batdau==null||hd.getNgayMua().toLocalDateTime().toLocalDate().isBefore(batdau))
                batdau = hd.getNgayMua().toLocalDateTime().toLocalDate();
        CHART.clear();
        if(batdau==null)
            return;
        LocalDate ketthuc=LocalDate.now();
        Duration duration = Duration.between(batdau.atStartOfDay(), ketthuc.atStartOfDay());       
        long SoNgay = duration.toDays();
        int buocnhay = 0;
        String LoaiMoc ;        
        if(SoNgay<=0)                   
            return;        
        if (SoNgay < 10)
            buocnhay = 1;
        else if(SoNgay<31)
            buocnhay = 5;
        else if (SoNgay < 100)
            buocnhay = 10;
        else if (SoNgay < 366)
            buocnhay = 30;
        else if (SoNgay < 1096)
            buocnhay = 100;
        else if (SoNgay<5000)
            buocnhay = 365;
        else
            buocnhay = 2000;  
        int ttt = 0;
        for (int i = 0; i < SoNgay; i += buocnhay)
        {           
            LocalDate moc = batdau.plusDays(buocnhay);            
            int thanhtien = 0;            
            for(HoaDon hd: db.getHoaDons())
            {
                ttt+=hd.getThanhTien();
                LocalDate ngayHD = hd.getNgayMua().toLocalDateTime().toLocalDate();
                if(ngayHD.isAfter(batdau.minusDays(1))&&ngayHD.isBefore(moc.plusDays(1)))
                {
                    thanhtien+=hd.getThanhTien();
                    System.out.println(String.valueOf(thanhtien));
                }
            } 
            moc = batdau.plusDays((moc.toEpochDay() - batdau.toEpochDay()) / 2);
            if(moc.isAfter(ketthuc))
                moc=ketthuc;
            batdau = batdau.plusDays(buocnhay);            
            String cotmoc = moc.format(formatter); 
            if(buocnhay<=5)
                cotmoc = cotmoc.substring(0,2);
            else if(buocnhay >=5&&buocnhay<=100)
                cotmoc = cotmoc.substring(4, 10);
            else if (buocnhay == 365 )
                cotmoc = cotmoc.substring(6, 10);            
            CHART.addData(new ModelChart(cotmoc, new double[]{thanhtien}));
        }     
        CHART.start();        
    }
    
    public void top3SanPhambanChay()
    {
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(chonNgayBatDau.getDateFormat());
        
        LocalDate batdau= LocalDate.parse(txt_batDau.getText(), formatter);
        LocalDate ketthuc= LocalDate.parse(txt_ketThuc.getText(), formatter);  
        ArrayLists<HangHoa> top3hh = new ArrayLists<HangHoa>(null);
        for(Component cpo : panel_top3hh.getComponents())        
        {                
            JLabel label = (JLabel)cpo;
            label.setVisible(false);
            HangHoa hhMax = null;
            for(HangHoa hh : db.getHangHoas())
            {                
                int max = -1;
                int tt = -1;
                for(ChiTietLoaiHang ctlh : hh.getChiTietLoaiHangs())
                {
                    for(ChiTietHoaDon cthd : ctlh.getChiTietHoaDons())
                    {
                        try
                        {
                            if(top3hh.get(t->t.getMaHang().equalsIgnoreCase(cthd.getChiTietLoaiHang().getHangHoa().getMaHang()))==null)
                            {
                                LocalDate ngayMua = cthd.getHoaDon().getNgayMua().toLocalDateTime().toLocalDate();
                                if(ngayMua.isAfter(batdau.minusDays(1))&&ngayMua.isBefore(ketthuc.plusDays(1)))
                                    tt+=cthd.getDonGia()*cthd.getSoLuong();
                            }
                        } catch(Exception e)
                        {
                            LocalDate ngayMua = cthd.getHoaDon().getNgayMua().toLocalDateTime().toLocalDate();
                            if(ngayMua.isAfter(batdau.minusDays(1))&&ngayMua.isBefore(ketthuc.plusDays(1)))
                                tt+=cthd.getDonGia()*cthd.getSoLuong();
                        }
                    }                        
                }
                if(tt>max)
                {
                    max = tt;
                    hhMax = hh;
                }
            }
            if(hhMax!=null)
            {
                System.out.println(hhMax.getTenHang());
                top3hh.add(hhMax);                
                label.setVisible(true);
                label.setText(hhMax.getTenHang());
            }
        }      
        Duration duration = Duration.between(batdau.atStartOfDay(), ketthuc.atStartOfDay());
        long SoNgay = duration.toDays();
        int buocnhay = 0;
        String LoaiMoc ;
        chart_n.clear();  
        if(SoNgay<=0)                   
            return;        
        if (SoNgay < 10)
            buocnhay = 1;
        else if(SoNgay<31)
            buocnhay = 5;
        else if (SoNgay < 100)
            buocnhay = 10;
        else if (SoNgay < 366)
            buocnhay = 30;
        else if (SoNgay < 1096)
            buocnhay = 100;
        else if (SoNgay<5000)
            buocnhay = 365;
        else
            buocnhay = 2000;          
        int ttt = 0;
        System.out.println("só bd: đã tới daaya");
        for (int i = 0; i < SoNgay; i += buocnhay)
        {            
            LocalDate moc = batdau.plusDays(buocnhay);            
            int thanhtien = 0;    
            double[] d = new double[3];
            for(int j = 0;j<3;j++)
                d[j]=0;
            for(HoaDon hd: db.getHoaDons())
            {
                ttt+=hd.getThanhTien();
                LocalDate ngayHD = hd.getNgayMua().toLocalDateTime().toLocalDate();
                if(ngayHD.isAfter(batdau.minusDays(1))&&ngayHD.isBefore(moc.plusDays(1)))
                {
                    for(int j = 0;j<top3hh.size();j++)                        
                        for(ChiTietHoaDon ct : hd.getChiTietHoaDons())
                        {
                            if(top3hh.get(j).getMaHang().equals(ct.getChiTietLoaiHang().getHangHoa().getMaHang()))
                                d[j]+=ct.getDonGia()*ct.getSoLuong();
                        }                    
                }
            }                        
            moc = batdau.plusDays((moc.toEpochDay() - batdau.toEpochDay()) / 2);
            if(moc.isAfter(ketthuc))
                moc=ketthuc;
            batdau = batdau.plusDays(buocnhay);            
            String cotmoc = moc.format(formatter); 
            if(buocnhay<=5)
                cotmoc = cotmoc.substring(0,2);
            else if(buocnhay >=5&&buocnhay<=100)
                cotmoc = cotmoc.substring(4, 10);
            else if (buocnhay == 365 )
                cotmoc = cotmoc.substring(6, 10);
            chart_n.addData(new com.raven.chart.ModelChart(cotmoc, d));
        }     
         System.out.println("thanfhsb agweae");
        System.out.println(ttt);
//        chart_n.addData(new com.raven.chart.ModelChart("January", new double[]{500, 200, 80, 89}));
//        chart_n.addData(new com.raven.chart.ModelChart("February", new double[]{1000, 750, 90, 150}));
//        chart_n.addData(new com.raven.chart.ModelChart("March", new double[]{200, 350, 460, 900}));
//        chart_n.addData(new com.raven.chart.ModelChart("April", new double[]{480, 150, 750, 700}));
//        chart_n.addData(new com.raven.chart.ModelChart("May", new double[]{350, 540, 300, 150}));
//        chart_n.addData(new com.raven.chart.ModelChart("June", new double[]{190, 280, 81, 200}));
        chart_n.start();        
    }
    public void khoiTao()
    {
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
                    if(value.isEmpty())
                        return;
                    if("dathang".contains(value)||value.equals("dh"))
                        main.showDatHang();
                    else if("banhang".contains(value)||value.equals("bh"))
                        main.showBanHang();
                    else if("canhan".contains(value)||value.equals("cn"))
                        main.showCaNhan();
                    else if("dondathang".contains(value)||value.equals("ddh"))
                        main.showDonDatHang();
                    else if("hanghoa".contains(value)||value.equals("hh"))
                        main.showHangHoa();
                    else if("hoadon".contains(value)||value.equals("hd"))
                        main.showHoaDon();
                    else if("nguoncung".contains(value)||value.equals("nc"))
                        main.showNguonCung();
                    else if("nhacungcap".contains(value)||value.equals("ncc"))
                        main.showNhaCungCap();
                    else if(("nhanvien".contains(value)||value.equals("nv"))&&user.getChucVu().equals("Quản lý"))
                        main.showNhanVien();
                    else if("phieugiaohang".contains(value)||value.equals("pgh"))
                        main.showPhieuGiaoHang();
                    txt_timKiem.setText("");
                    
                    
                    
                        
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
        
        loadThongKeSanPhamNgayThangNam();
        TableCustom.apply(jScrollPane2, TableCustom.TableType.MULTI_LINE);
    }
    
    public void loadThongKeSanPhamNgayThangNam()
    {
        DefaultTableModel dtm = new DefaultTableModel(); 
        Vector  v = new  Vector();
        v.add("Mã sản phâm");
        v.add("Tên sản phẩm");
        v.add("Tổng số lượng tồn");
        tbl_spChuaCoNguoiMua.setModel(dtm);
        dtm.setColumnIdentifiers(v);
        int maxNgay = -1;
        int maxThang = -1;
        int maxNam = -1;
        int maxTatCa = -1;
        HangHoa hhMaxNgay = null;
        HangHoa hhMaxThang = null;
        HangHoa hhMaxNam = null;
        HangHoa hhMaxTatCa= null;
        int namHienTai = LocalDate.now().getYear();
        int thangHienTai = LocalDate.now().getMonthValue();
        int ngayHienTai = LocalDate.now().getDayOfMonth();
        for(HangHoa hangHoa : db.getHangHoas())
        {            
            int tongHHNgay = 0;
            int tongHHThang = 0;
            int tongHHNam = 0;
            int tongHHTatCa = 0;
            for(ChiTietLoaiHang ctlh: hangHoa.getChiTietLoaiHangs())
            {                                
                for(ChiTietHoaDon cthd: ctlh.getChiTietHoaDons())
                {
                                                         
                    if(cthd.getHoaDon().getNgayMua().getYear()+1900==namHienTai)
                    {
                        tongHHNam+=cthd.getSoLuong();
                        if(cthd.getHoaDon().getNgayMua().getMonth()+1==thangHienTai)
                        {
                            tongHHThang+=cthd.getSoLuong();
                            if(cthd.getHoaDon().getNgayMua().getDate()==ngayHienTai)
                                tongHHNgay+=cthd.getSoLuong();  
                        }
                    }                       
                    tongHHTatCa +=cthd.getSoLuong();                    
                }   
            }            
            if(tongHHTatCa<20)
            {
                Vector row = new Vector();
                row.add(hangHoa.getMaHang());
                row.add(hangHoa.getTenHang());
                row.add(tongHHTatCa);
                dtm.addRow(row);
            }
            if(tongHHNgay>maxNgay)
            {
                maxNgay=tongHHNgay;
                hhMaxNgay = hangHoa;
            }
            if(tongHHThang>maxThang)
            {
                maxThang=tongHHThang;
                hhMaxThang = hangHoa;
            }
            if(tongHHNam>maxNam)
            {
                maxNam=tongHHNam;
                hhMaxNam = hangHoa;
            }
            if(tongHHTatCa>maxTatCa)
            {
                maxTatCa=tongHHTatCa;
                hhMaxTatCa = hangHoa;
            }
        }    
        if(maxNgay>0)
        {
            lb_soLuongBanDuocTrongNgay.setText(String.valueOf(maxNgay));
            label_sanPhamNgay.setText(hhMaxNgay.getTenHang());
        }
        else
        {
            lb_soLuongBanDuocTrongNgay.setText("---");
            label_sanPhamNgay.setText("---");
        }
        if(maxThang>0)
        {
            lb_SoLuongBanDuocTrongThangNay.setText(String.valueOf(maxThang));
            label_sanPhamThang.setText(hhMaxThang.getTenHang());
        }
        else
        {
            lb_SoLuongBanDuocTrongThangNay.setText("---");
            label_sanPhamThang.setText("---");
        }
        if(maxNam>0)
        {
            lb_soLuongBanDuocTrongNamNay.setText(String.valueOf(maxNam));
            label_sanPhamNam.setText(hhMaxNam.getTenHang());
        }
        else
        {
            lb_soLuongBanDuocTrongNamNay.setText("---");
            label_sanPhamNam.setText("---");
        }
        if(maxTatCa>0)
        {
            lb_tatCaSoLuongBanDuoc.setText(String.valueOf(maxTatCa));
            label_tatCaSanPham.setText(hhMaxTatCa.getTenHang());
        }
        else
        {
            lb_tatCaSoLuongBanDuoc.setText("---");
            label_tatCaSanPham.setText("---");
        }
    }    


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        chonNgayBatDau = new com.raven.datechooser.DateChooser();
        chonNgayKetThuc = new com.raven.datechooser.DateChooser();
        jPanel3 = new javax.swing.JPanel();
        roundPanel2 = new com.raven.swing.RoundPanel();
        label_sanPhamNgay = new javax.swing.JLabel();
        lb_soLuongBanDuocTrongNgay = new javax.swing.JLabel();
        roundPanel6 = new com.raven.swing.RoundPanel();
        label_sanPhamThang = new javax.swing.JLabel();
        lb_SoLuongBanDuocTrongThangNay = new javax.swing.JLabel();
        roundPanel7 = new com.raven.swing.RoundPanel();
        label_sanPhamNam = new javax.swing.JLabel();
        lb_soLuongBanDuocTrongNamNay = new javax.swing.JLabel();
        roundPanel8 = new com.raven.swing.RoundPanel();
        label_tatCaSanPham = new javax.swing.JLabel();
        lb_tatCaSoLuongBanDuoc = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        roundPanel1 = new com.raven.swing.RoundPanel();
        chart_n = new com.raven.chart.CurveChart();
        tableScrollButton1 = new table.TableScrollButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_spChuaCoNguoiMua = new javax.swing.JTable();
        panelShadow2 = new raven.panel.PanelShadow();
        CHART = new raven.chart.CurveLineChart();
        txt_batDau = new textfield.TextField();
        txt_ketThuc = new textfield.TextField();
        combobox1 = new combobox.Combobox();
        panel_top3hh = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

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
        setMinimumSize(new java.awt.Dimension(1544, 692));

        roundPanel2.setBackground(new java.awt.Color(0, 51, 51));

        label_sanPhamNgay.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_sanPhamNgay.setForeground(new java.awt.Color(255, 255, 51));
        label_sanPhamNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_sanPhamNgay.setText("BÁNH BÍA");

        lb_soLuongBanDuocTrongNgay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lb_soLuongBanDuocTrongNgay.setForeground(new java.awt.Color(255, 255, 255));
        lb_soLuongBanDuocTrongNgay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_soLuongBanDuocTrongNgay.setText("99");

        javax.swing.GroupLayout roundPanel2Layout = new javax.swing.GroupLayout(roundPanel2);
        roundPanel2.setLayout(roundPanel2Layout);
        roundPanel2Layout.setHorizontalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_sanPhamNgay, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lb_soLuongBanDuocTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        roundPanel2Layout.setVerticalGroup(
            roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_sanPhamNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(roundPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel2Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lb_soLuongBanDuocTrongNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addGap(38, 38, 38)))
        );

        roundPanel6.setBackground(new java.awt.Color(0, 51, 51));

        label_sanPhamThang.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_sanPhamThang.setForeground(new java.awt.Color(255, 255, 51));
        label_sanPhamThang.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_sanPhamThang.setText("BÁNH BÍA");

        lb_SoLuongBanDuocTrongThangNay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lb_SoLuongBanDuocTrongThangNay.setForeground(new java.awt.Color(255, 255, 255));
        lb_SoLuongBanDuocTrongThangNay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_SoLuongBanDuocTrongThangNay.setText("99");

        javax.swing.GroupLayout roundPanel6Layout = new javax.swing.GroupLayout(roundPanel6);
        roundPanel6.setLayout(roundPanel6Layout);
        roundPanel6Layout.setHorizontalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_sanPhamThang, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lb_SoLuongBanDuocTrongThangNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        roundPanel6Layout.setVerticalGroup(
            roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel6Layout.createSequentialGroup()
                .addGap(107, 107, 107)
                .addComponent(label_sanPhamThang, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(roundPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel6Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lb_SoLuongBanDuocTrongThangNay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE)))
        );

        roundPanel7.setBackground(new java.awt.Color(0, 51, 51));

        label_sanPhamNam.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_sanPhamNam.setForeground(new java.awt.Color(255, 255, 51));
        label_sanPhamNam.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_sanPhamNam.setText("BÁNH BÍA");

        lb_soLuongBanDuocTrongNamNay.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lb_soLuongBanDuocTrongNamNay.setForeground(new java.awt.Color(255, 255, 255));
        lb_soLuongBanDuocTrongNamNay.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_soLuongBanDuocTrongNamNay.setText("99");

        javax.swing.GroupLayout roundPanel7Layout = new javax.swing.GroupLayout(roundPanel7);
        roundPanel7.setLayout(roundPanel7Layout);
        roundPanel7Layout.setHorizontalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_sanPhamNam, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
            .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lb_soLuongBanDuocTrongNamNay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        roundPanel7Layout.setVerticalGroup(
            roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(label_sanPhamNam, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
            .addGroup(roundPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(roundPanel7Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(lb_soLuongBanDuocTrongNamNay, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(38, Short.MAX_VALUE)))
        );

        roundPanel8.setBackground(new java.awt.Color(0, 51, 51));

        label_tatCaSanPham.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        label_tatCaSanPham.setForeground(new java.awt.Color(255, 255, 51));
        label_tatCaSanPham.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label_tatCaSanPham.setText("BÁNH BÍA");

        lb_tatCaSoLuongBanDuoc.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lb_tatCaSoLuongBanDuoc.setForeground(new java.awt.Color(255, 255, 255));
        lb_tatCaSoLuongBanDuoc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_tatCaSoLuongBanDuoc.setText("99");

        javax.swing.GroupLayout roundPanel8Layout = new javax.swing.GroupLayout(roundPanel8);
        roundPanel8.setLayout(roundPanel8Layout);
        roundPanel8Layout.setHorizontalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(label_tatCaSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
            .addGroup(roundPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lb_tatCaSoLuongBanDuoc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel8Layout.setVerticalGroup(
            roundPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, roundPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lb_tatCaSoLuongBanDuoc, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(label_tatCaSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );

        jLabel4.setText("Toàn bộ");

        jLabel3.setText("Năm nay");

        jLabel2.setText("Tháng này");

        jLabel1.setText("Hôm nay");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(roundPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(roundPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(roundPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(66, 66, 66)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(115, 115, 115)
                .addComponent(jLabel3)
                .addGap(142, 142, 142)
                .addComponent(jLabel4)
                .addGap(54, 54, 54))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(roundPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(roundPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        roundPanel1.setBackground(new java.awt.Color(0, 0, 0));

        chart_n.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout roundPanel1Layout = new javax.swing.GroupLayout(roundPanel1);
        roundPanel1.setLayout(roundPanel1Layout);
        roundPanel1Layout.setHorizontalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart_n, javax.swing.GroupLayout.DEFAULT_SIZE, 777, Short.MAX_VALUE)
                .addContainerGap())
        );
        roundPanel1Layout.setVerticalGroup(
            roundPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(roundPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(chart_n, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl_spChuaCoNguoiMua.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tbl_spChuaCoNguoiMua);

        tableScrollButton1.add(jScrollPane2, java.awt.BorderLayout.CENTER);

        panelShadow2.setBackground(new java.awt.Color(0, 102, 102));
        panelShadow2.setColorGradient(new java.awt.Color(0, 102, 102));

        CHART.setFillColor(true);
        CHART.setFocusCycleRoot(true);

        javax.swing.GroupLayout panelShadow2Layout = new javax.swing.GroupLayout(panelShadow2);
        panelShadow2.setLayout(panelShadow2Layout);
        panelShadow2Layout.setHorizontalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CHART, javax.swing.GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelShadow2Layout.setVerticalGroup(
            panelShadow2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelShadow2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CHART, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        panel_top3hh.setBackground(new java.awt.Color(0, 0, 0));

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Xanh");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Vàng");

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Đỏ");

        javax.swing.GroupLayout panel_top3hhLayout = new javax.swing.GroupLayout(panel_top3hh);
        panel_top3hh.setLayout(panel_top3hhLayout);
        panel_top3hhLayout.setHorizontalGroup(
            panel_top3hhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel_top3hhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        panel_top3hhLayout.setVerticalGroup(
            panel_top3hhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_top3hhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_top3hhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(roundPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txt_batDau, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txt_ketThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(99, 99, 99)
                                        .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tableScrollButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(panel_top3hh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(147, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelShadow2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txt_batDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txt_ketThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(combobox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tableScrollButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
                    .addComponent(roundPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel_top3hh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txt_batDauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_batDauActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_batDauActionPerformed

    private void txt_ketThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_ketThucActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txt_ketThucActionPerformed

    private void chonNgayBatDauAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_chonNgayBatDauAncestorRemoved
        // TODO add your handling code here:
        //loadBieuDo();        
        top3SanPhambanChay();
    }//GEN-LAST:event_chonNgayBatDauAncestorRemoved

    private void chonNgayKetThucAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_chonNgayKetThucAncestorRemoved
        // TODO add your handling code here:
        //loadBieuDo();
        top3SanPhambanChay();
    }//GEN-LAST:event_chonNgayKetThucAncestorRemoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private raven.chart.CurveLineChart CHART;
    private com.raven.chart.CurveChart chart_n;
    private com.raven.datechooser.DateChooser chonNgayBatDau;
    private com.raven.datechooser.DateChooser chonNgayKetThuc;
    private combobox.Combobox combobox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel label_sanPhamNam;
    private javax.swing.JLabel label_sanPhamNgay;
    private javax.swing.JLabel label_sanPhamThang;
    private javax.swing.JLabel label_tatCaSanPham;
    private javax.swing.JLabel lb_SoLuongBanDuocTrongThangNay;
    private javax.swing.JLabel lb_soLuongBanDuocTrongNamNay;
    private javax.swing.JLabel lb_soLuongBanDuocTrongNgay;
    private javax.swing.JLabel lb_tatCaSoLuongBanDuoc;
    private raven.panel.PanelShadow panelShadow2;
    private javax.swing.JPanel panel_top3hh;
    private com.raven.swing.RoundPanel roundPanel1;
    private com.raven.swing.RoundPanel roundPanel2;
    private com.raven.swing.RoundPanel roundPanel6;
    private com.raven.swing.RoundPanel roundPanel7;
    private com.raven.swing.RoundPanel roundPanel8;
    private table.TableScrollButton tableScrollButton1;
    private javax.swing.JTable tbl_spChuaCoNguoiMua;
    private textfield.TextField txt_batDau;
    private textfield.TextField txt_ketThuc;
    // End of variables declaration//GEN-END:variables
}
