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
public class ChiTietHoaDon extends GetFieldName{
    private String SoHoaDon;
    private String MaCTLH;
    private int SoLuong;
    private int DonGia ;
    private HoaDon hoaDon = null;
    private ChiTietLoaiHang chiTietLoaiHang = null;

    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameter(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameterAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietLoaiHang", chiTietLoaiHang.includeAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameter());
        return data;
    }
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(ChiTietHoaDon.class.getDeclaredFields());
    }
    public HoaDon getHoaDon() {
        return hoaDon;
    }

    public void setHoaDon(HoaDon hoaDon) {
        this.hoaDon = hoaDon;
    }

    public ChiTietLoaiHang getChiTietLoaiHang() {
        return chiTietLoaiHang;
    }

    public void setChiTietLoaiHang(ChiTietLoaiHang chiTietLoaiHang) {
        this.chiTietLoaiHang = chiTietLoaiHang;
    }

    public String getSoHoaDon() {
        return SoHoaDon;
    }

    public void setSoHoaDon(String SoHoaDon) {
        this.SoHoaDon = SoHoaDon;
    }

    public String getMaCTLH() {
        return MaCTLH;
    }

    public void setMaCTLH(String MaCTLH) {
        this.MaCTLH = MaCTLH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public int getDonGia() {
        return DonGia;
    }

    public void setDonGia(int DonGia) {
        this.DonGia = DonGia;
    }
     public ChiTietHoaDon() {
    }

    public ChiTietHoaDon(String SoHoaDon, String MaCTLH, int SoLuong, int DonGia) {
        this.SoHoaDon = SoHoaDon;
        this.MaCTLH = MaCTLH;
        this.SoLuong = SoLuong;
        this.DonGia = DonGia;
        this.updateKeyOld();
    }
    public ChiTietHoaDon(ResultSet rs) throws SQLException {
        this.SoHoaDon = rs.getString("SoHoaDon");
        this.MaCTLH = rs.getString("MaCTLH");
        this.SoLuong = rs.getInt("SoLuong");
        this.DonGia =  rs.getInt("DonGia");
        this.updateKeyOld();
    }
    public ChiTietHoaDon(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.SoHoaDon = (String) hm.get("SoHoaDon");
        this.MaCTLH = (String) hm.get("MaCTLH");
        this.SoLuong = (int) hm.get("SoLuong");
        this.DonGia =  (int) hm.get("DonGia");
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoHoaDon);
            params.add(this.MaCTLH);
            params.add(this.SoLuong);
            params.add(this.DonGia);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoHoaDon);
            params.add(this.MaCTLH);
        return params;
    }
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.SoHoaDon = (String) parameter.get(0);
        this.MaCTLH = (String) parameter.get(1);
        this.SoLuong = (int) parameter.get(2);
        this.DonGia =  (int) parameter.get(3);
        this.updateKeyOld();
    }
    
}
