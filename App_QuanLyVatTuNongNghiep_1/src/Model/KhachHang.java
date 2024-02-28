/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class KhachHang extends GetFieldName{
    private String MaKH;
    private String TenKH;
    private String DiaChi;
    private String SoDienThoai;   
    private ArrayLists<HoaDon> hoaDons = null; //new ArrayLists<HoaDon>();

    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hoaDons", hoaDons.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hoaDons", hoaDons.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hoaDons", hoaDons.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hoaDons", hoaDons.getData());
        return data;
    }
    
    public void khoiTaoLienKet(){
        hoaDons = new ArrayLists<HoaDon>(this.task);
    };
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(KhachHang.class.getDeclaredFields());
    }
    
    public ArrayLists<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(ArrayLists<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }
    

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String MaKH) {
        this.MaKH = MaKH;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String TenKH) {
        this.TenKH = TenKH;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String DiaChi) {
        this.DiaChi = DiaChi;
    }

    public String getSoDienThoai() {
        return SoDienThoai;
    }

    public void setSoDienThoai(String SoDienThoai) {
        this.SoDienThoai = SoDienThoai;
    }

    public KhachHang(String MaKH, String TenKH, String DiaChi, String SoDienThoai) {
        this.MaKH = MaKH;
        this.TenKH = TenKH;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.updateKeyOld();
    }
//    public KhachHang(ResultSet rs) throws SQLException {
//        this.MaKH = rs.getString("MaKH");
//        this.TenKH = rs.getString("TenKH");
//        this.DiaChi = rs.getString("DiaChi");
//        this.SoDienThoai = rs.getString("SoDienThoai");
    //this.updateKeyOld();
//    }
    public KhachHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.MaKH = (String) hm.get("MaKH");
        this.TenKH = (String) hm.get("TenKH");
        this.DiaChi = (String) hm.get("DiaChi");
        this.SoDienThoai = (String) hm.get("SoDienThoai");
        khoiTaoLienKet();
        this.updateKeyOld();
        
    }
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.MaKH = (String) parameter.get(0);
        this.TenKH = (String) parameter.get(1);
        this.DiaChi = (String) parameter.get(2);
        this.SoDienThoai = (String) parameter.get(3);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaKH);
            params.add(this.TenKH);
            params.add(this.DiaChi);
            params.add(this.SoDienThoai);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaKH);            
        return params;
    }
}
