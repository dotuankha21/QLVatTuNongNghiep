/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;



/**
 *
 * @author Admin
 */
public class HoaDon extends GetFieldName{
    private String SoHoaDon;
    private String MaNV;
    private String MaKH;
    private Timestamp NgayMua;    
    private int ThanhTien;
    
    private NhanVien nhanVien = null;
    private KhachHang khachHang = null;
    ArrayLists<ChiTietHoaDon> chiTietHoaDons = null;// new ArrayLists<ChiTietHoaDon>();

    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhanVien", nhanVien.getParameter(remove_fieldNames));
        data.put("khachHang", khachHang.getParameter(remove_fieldNames));
        data.put("chiTietHoaDons", chiTietHoaDons.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhanVien", nhanVien.getParameterAs(fieldNames));
        data.put("khachHang", khachHang.getParameterAs(fieldNames));
        data.put("chiTietHoaDons", chiTietHoaDons.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhanVien", nhanVien.includeAs(tblNames,fieldNames));
        data.put("khachHang", khachHang.includeAs(tblNames,fieldNames));
        data.put("chiTietHoaDons", chiTietHoaDons.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhanVien", nhanVien.getParameter());
        data.put("khachHang", khachHang.getParameter());
        data.put("chiTietHoaDons", chiTietHoaDons.getData());
        return data;
    }
    
    public void khoiTaoLienKet(){
        chiTietHoaDons = new ArrayLists<ChiTietHoaDon>(this.task);
    };
    
    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(HoaDon.class.getDeclaredFields());
    }
    
    public Timestamp getNgayMua() {
        return NgayMua;
    }

    public void setNgayMua(Timestamp NgayMua) {
        this.NgayMua = NgayMua;
    }
   
    public ArrayLists<ChiTietHoaDon> getChiTietHoaDons() {        
        return chiTietHoaDons;
    }

    public void setChiTietHoaDons(ArrayLists<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }
    
    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public HoaDon(String SoHoaDon, String MaNV, String MaKH, Timestamp NgayMua, int ThanhTien) {
        this.SoHoaDon = SoHoaDon;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.NgayMua = NgayMua;
        this.ThanhTien = ThanhTien;
        this.updateKeyOld();
    }
    
    public HoaDon(String SoHoaDon, String MaNV, String MaKH, int ThanhTien) {
        this.SoHoaDon = SoHoaDon;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.NgayMua = new Timestamp(new Date().getTime());
        this.ThanhTien = ThanhTien;
        this.updateKeyOld();
    }
      
    public HoaDon(String SoHoaDon, String MaNV, String MaKH) {
        this.SoHoaDon = SoHoaDon;
        this.MaNV = MaNV;
        this.MaKH = MaKH;
        this.NgayMua = new Timestamp(new Date().getTime());
        this.ThanhTien = 0;
        this.updateKeyOld();
    }
    
    public HoaDon(ResultSet rs) throws SQLException {
        this.SoHoaDon = rs.getString("SoHoaDon");
        this.MaNV = rs.getString("MaNV");
        this.MaKH = rs.getString("MaKH");
        this.NgayMua = rs.getTimestamp("NgayMua");
        this.ThanhTien = rs.getInt("ThanhTien");
        this.updateKeyOld();
    }
    public HoaDon(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.SoHoaDon = (String) hm.get("SoHoaDon");
        this.MaNV = (String) hm.get("MaNV");
        this.MaKH = (String) hm.get("MaKH");
        this.NgayMua = (Timestamp) hm.get("NgayMua");
        this.ThanhTien = (int) hm.get("ThanhTien");
        khoiTaoLienKet();
        this.updateKeyOld();
    }
    
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.SoHoaDon = (String) parameter.get(0);
        this.MaNV = (String) parameter.get(1);
        this.MaKH = (String) parameter.get(2);
        this.NgayMua = (Timestamp) parameter.get(3);
        this.ThanhTien = (int) parameter.get(4);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoHoaDon);
            params.add(this.MaNV);
            params.add(this.MaKH);
            params.add(this.NgayMua);
            params.add(this.ThanhTien);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoHoaDon);
        return params;
    }
    public HoaDon() {
    }

    public String getSoHoaDon() {
        return SoHoaDon;
    }

    public void setSoHoaDon(String SoHoaDon) {
        this.SoHoaDon = SoHoaDon;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }
    
}
