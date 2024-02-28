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
public class ChiTietDatHang extends GetFieldName{
    private String SoDonDatHang;
    private String MaCTLH;
    private int DonGia;
    private int SoLuong; 
    
    private PhieuDatHang phieuDatHang = null; 
    private ChiTietLoaiHang chiTietLoaiHang = null; 

    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuDatHang", phieuDatHang.getParameter(remove_fieldNames));
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameter(remove_fieldNames));
        return data;
    }
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuDatHang", phieuDatHang.getParameterAs(fieldNames));
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameterAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuDatHang", phieuDatHang.includeAs(tblNames,fieldNames));
        data.put("chiTietLoaiHang", chiTietLoaiHang.includeAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuDatHang", phieuDatHang.getParameter());
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameter());
        return data;
    }
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(ChiTietDatHang.class.getDeclaredFields());
    }
            
    public PhieuDatHang getPhieuDatHang() {
        return phieuDatHang;
    }

    public void setPhieuDatHang(PhieuDatHang phieuDatHang) {
        this.phieuDatHang = phieuDatHang;
    }

    public ChiTietLoaiHang getChiTietLoaiHang() {
        return chiTietLoaiHang;
    }

    public void setChiTietLoaiHang(ChiTietLoaiHang chiTietLoaiHang) {
        this.chiTietLoaiHang = chiTietLoaiHang;
    }
    
    public String getSoDonDatHang() {
        return SoDonDatHang;
    }

    public void setSoDonDatHang(String SoDonDatHang) {
        this.SoDonDatHang = SoDonDatHang;
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

    public ChiTietDatHang() {
    }

    public ChiTietDatHang(String SoDonDatHang, String MaCTLH,int DonGia, int SoLuong) {
        this.SoDonDatHang = SoDonDatHang;
        this.MaCTLH = MaCTLH;
        this.DonGia = DonGia;
        this.SoLuong = SoLuong;
        updateKeyOld();
    }
    public ChiTietDatHang(ResultSet rs) throws SQLException {
        this.SoDonDatHang = rs.getString("SoDonDatHang");
        this.MaCTLH = rs.getString("MaCTLH");
        this.DonGia = rs.getInt("DonGia");
        this.SoLuong = rs.getInt("SoLuong");
        updateKeyOld();
    }
    public ChiTietDatHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.SoDonDatHang = (String) hm.get("SoDonDatHang");
        this.MaCTLH = (String) hm.get("MaCTLH");
        this.DonGia = (int) hm.get("DonGia");
        this.SoLuong = (int) hm.get("SoLuong");
        updateKeyOld();
    }   

    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoDonDatHang);
            params.add(this.MaCTLH);
            params.add(this.DonGia);
            params.add(this.SoLuong);
        return params;
    }

    @Override
    public void setValue(ArrayList<Object> parameter) {
       this.SoDonDatHang = (String) parameter.get(0);
       this.MaCTLH = (String) parameter.get(1);
       this.DonGia = (int) parameter.get(2);
       this.SoLuong = (int) parameter.get(3);
       this.updateKeyOld();
    }

    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoDonDatHang);
            params.add(this.MaCTLH);
        return params;
    }   

   

    
    
}
