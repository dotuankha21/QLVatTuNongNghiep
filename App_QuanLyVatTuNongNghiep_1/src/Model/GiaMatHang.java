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
public class GiaMatHang extends GetFieldName{
    private String MaCTLH;
    private int GiaBan;
    private int DieuKien;
    
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
        return  GetFieldName.GetFields(GiaMatHang.class.getDeclaredFields());
    }
    public ChiTietLoaiHang getChiTietLoaiHang() {
        return chiTietLoaiHang;
    }

    public void setChiTietLoaiHang(ChiTietLoaiHang chiTietLoaiHang) {
        this.chiTietLoaiHang = chiTietLoaiHang;
    }            

    public GiaMatHang(String MaCTLH, int GiaBan, int DieuKien) {
        this.MaCTLH = MaCTLH;
        this.GiaBan = GiaBan;
        this.DieuKien = DieuKien;
        this.updateKeyOld();
    }
    public GiaMatHang(ResultSet rs) throws SQLException {
        this.MaCTLH = rs.getString("MaCTLH");
        this.GiaBan = rs.getInt("GiaBan");
        this.DieuKien = rs.getInt("DieuKien");
        this.updateKeyOld();
    }
    public GiaMatHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.MaCTLH = (String) hm.get("MaCTLH");
        this.GiaBan = (int) hm.get("GiaBan");
        this.DieuKien = (int) hm.get("DieuKien");
        this.updateKeyOld();
    }
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.MaCTLH = (String) parameter.get(0);
        this.GiaBan = (int) parameter.get(1);
        this.DieuKien = (int) parameter.get(2);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaCTLH);
            params.add(this.GiaBan);
            params.add(this.DieuKien);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaCTLH);
            params.add(this.DieuKien);
        return params;
    }
    public String getMaCTLH() {
        return MaCTLH;
    }

    public void setMaCTLH(String MaCTLH) {
        this.MaCTLH = MaCTLH;
    }

    public int getGiaBan() {
        return GiaBan;
    }

    public void setGiaBan(int GiaBan) {
        this.GiaBan = GiaBan;
    }

    public int getDieuKien() {
        return DieuKien;
    }

    public void setDieuKien(int DieuKien) {
        this.DieuKien = DieuKien;
    }
    public boolean checkDieuKien(int dieuKien)
    {
        return this.DieuKien == dieuKien ? true:false;
    }
}
