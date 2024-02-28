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
public class NguonHang extends GetFieldName{
    private String MaNCC;
    private String MaCTLH;
    private int GiaNhap;
    
    private NhaCungCap nhaCungCap = null;
    private ChiTietLoaiHang chiTietLoaiHang = null;

    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhaCungCap", nhaCungCap.getParameter(remove_fieldNames));
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameter(remove_fieldNames));        
        return data;
    }
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhaCungCap", nhaCungCap.getParameterAs(fieldNames));
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameterAs(fieldNames));       
        return data;
    }
    
    @Override
    public synchronized HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();        
        data.put("nhaCungCap", nhaCungCap.includeAs(tblNames,fieldNames));        
        data.put("chiTietLoaiHang", chiTietLoaiHang.includeAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhaCungCap", nhaCungCap.getParameter());
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameter());  
        return data;
    }
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(NguonHang.class.getDeclaredFields());
    }
    
    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public ChiTietLoaiHang getChiTietLoaiHang() {
        return chiTietLoaiHang;
    }

    public void setChiTietLoaiHang(ChiTietLoaiHang chiTietLoaiHang) {
        this.chiTietLoaiHang = chiTietLoaiHang;
    }
    

    public NguonHang() {
    }

    public NguonHang(String MaNCC, String MaCTLH, int GiaNhap) {
        this.MaNCC = MaNCC;
        this.MaCTLH = MaCTLH;
        this.GiaNhap = GiaNhap;
        this.updateKeyOld();
    }
    
    public NguonHang(ResultSet rs) throws SQLException {
        this.MaNCC = rs.getString("MaNCC");
        this.MaCTLH = rs.getString("MaCTLH");
        this.GiaNhap = rs.getInt("GiaNhap");
        this.updateKeyOld();
    }
    public NguonHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.MaNCC = (String) hm.get("MaNCC");
        this.MaCTLH = (String) hm.get("MaCTLH");
        this.GiaNhap = (int) hm.get("GiaNhap");
        this.updateKeyOld();
    }
    
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.MaNCC = (String)  parameter.get(0);
        this.MaCTLH = (String)  parameter.get(1);
        this.GiaNhap = (int) parameter.get(2);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaNCC);
            params.add(this.MaCTLH);
            params.add(this.GiaNhap);
        return params;        
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaNCC);
            params.add(this.MaCTLH);                        
        return params;
    }
    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getMaCTLH() {
        return MaCTLH;
    }

    public void setMaCTLH(String MaCTLH) {
        this.MaCTLH = MaCTLH;
    }

    public int getGiaNhap() {
        return GiaNhap;
    }

    public void setGiaNhap(int GiaNhap) {
        this.GiaNhap = GiaNhap;
    }
    
}
