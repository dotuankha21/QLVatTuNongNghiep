/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class NhanVien extends GetFieldName{
    private String MaNV;
    private String HoTen;
    private String TenDangNhap;
    private String MatKhau;
    private Date NgaySinh;
    private String GioiTinh;
    private String SoDienThoai;
    private String DiaChi ;
    private String ChucVu;
    
    private ArrayLists<HoaDon> hoaDons = null;// new ArrayLists<HoaDon>();
    private ArrayLists<PhieuDatHang> phieuDatHangs = null;// new ArrayLists<PhieuDatHang>();

    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hoaDons", hoaDons.getData(remove_fieldNames));
        data.put("phieuDatHangs", phieuDatHangs.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hoaDons", hoaDons.getDataAs(fieldNames));
        data.put("phieuDatHangs", phieuDatHangs.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hoaDons", hoaDons.includesAs(tblNames,fieldNames));
        data.put("phieuDatHangs", phieuDatHangs.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hoaDons", hoaDons.getData());
        data.put("phieuDatHangs", phieuDatHangs.getData());
        return data;
    }
    
    public void khoiTaoLienKet(){
        hoaDons = new ArrayLists<HoaDon>(this.task);
        phieuDatHangs = new ArrayLists<PhieuDatHang>(this.task);
    };
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(NhanVien.class.getDeclaredFields());
    }
        
    public ArrayLists<PhieuDatHang> getPhieuDatHangs() {
        return phieuDatHangs;
    }

    public void setPhieuDatHangs(ArrayLists<PhieuDatHang> phieuDatHangs) {
        this.phieuDatHangs = phieuDatHangs;
    }    

    public ArrayLists<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String TenDangNhap) {
        this.TenDangNhap = TenDangNhap;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String MatKhau) {
        this.MatKhau = MatKhau;
    }

    
    
    public void setHoaDons(ArrayLists<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }    

    public NhanVien() {
    }

    public NhanVien(String MaNV, String HoTen, String TenDangNhap, String MatKhau, Date NgaySinh, String GioiTinh, String SoDienThoai, String DiaChi, String ChucVu) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = MatKhau;
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.DiaChi = DiaChi;
        this.ChucVu = ChucVu;
        this.updateKeyOld();
    }
    
    public NhanVien(String MaNV, String HoTen, String TenDangNhap, Date NgaySinh, String GioiTinh, String SoDienThoai, String DiaChi, String ChucVu) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.TenDangNhap = TenDangNhap;
        this.MatKhau = "123";
        this.NgaySinh = NgaySinh;
        this.GioiTinh = GioiTinh;
        this.SoDienThoai = SoDienThoai;
        this.DiaChi = DiaChi;
        this.ChucVu = ChucVu;
        this.updateKeyOld();
    }

    public NhanVien(ResultSet rs) throws SQLException {
        this.MaNV = rs.getString("MaNV");
        this.HoTen = rs.getString("HoTen");
        this.TenDangNhap = rs.getString("TenDangNhap");
        this.MatKhau = rs.getString("MatKhau");
        this.NgaySinh = rs.getDate("NgaySinh");
        this.GioiTinh = rs.getString("GioiTinh");
        this.SoDienThoai = rs.getString("SoDienThoai");
        this.DiaChi = rs.getString("DiaChi");
        this.ChucVu = rs.getString("ChucVu");
        this.updateKeyOld();
    }
     public NhanVien(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.MaNV = (String) hm.get("MaNV");
        this.HoTen = (String) hm.get("HoTen");
        this.TenDangNhap = (String) hm.get("TenDangNhap");
        this.MatKhau = (String) hm.get("MatKhau");
        this.NgaySinh = (Date) hm.get("NgaySinh");
        this.GioiTinh = (String) hm.get("GioiTinh");
        this.SoDienThoai = (String) hm.get("SoDienThoai");
        this.DiaChi = (String) hm.get("DiaChi");
        this.ChucVu = (String) hm.get("ChucVu");
        khoiTaoLienKet();
        this.updateKeyOld();
    }
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.MaNV = (String) parameter.get(0);
        this.HoTen = (String) parameter.get(1);
        this.TenDangNhap = (String) parameter.get(2);
        this.MatKhau = (String) parameter.get(3);
        this.NgaySinh = (Date) parameter.get(4);
        this.GioiTinh = (String) parameter.get(5);
        this.SoDienThoai = (String) parameter.get(6);
        this.DiaChi = (String) parameter.get(7);
        this.ChucVu = (String) parameter.get(8);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaNV);
            params.add(this.HoTen);
            params.add(this.TenDangNhap);
            params.add(this.MatKhau);
            params.add(this.NgaySinh);
            params.add(this.GioiTinh);
            params.add(this.SoDienThoai);
            params.add(this.DiaChi);
            params.add(this.ChucVu);   
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaNV);                                              
        return params;
    }
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }
    
}
