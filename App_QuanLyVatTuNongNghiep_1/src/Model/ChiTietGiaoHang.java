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
public class ChiTietGiaoHang extends GetFieldName{
    private String SoPhieuGiao;
    private String MaCTLH;
    private int SoLuong;
    
    private PhieuGiaoHang phieuGiaoHang = null;
    private ChiTietLoaiHang chiTietLoaiHang = null;

    
     
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuGiaoHang", phieuGiaoHang.getParameter(remove_fieldNames));
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameter(remove_fieldNames));
        return data;
    }
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuGiaoHang", phieuGiaoHang.getParameterAs(fieldNames));
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameterAs(fieldNames));
        return data;
    }
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuGiaoHang", phieuGiaoHang.includeAs(tblNames,fieldNames));
        data.put("chiTietLoaiHang", chiTietLoaiHang.includeAs(tblNames,fieldNames));
        return data;
    }
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuGiaoHang", phieuGiaoHang.getParameter());
        data.put("chiTietLoaiHang", chiTietLoaiHang.getParameter());
        return data;
    }
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(ChiTietGiaoHang.class.getDeclaredFields());
    }
    
    public PhieuGiaoHang getPhieuGiaoHang() {
        return phieuGiaoHang;
    }

    public void setPhieuGiaoHang(PhieuGiaoHang phieuGiaoHang) {
        this.phieuGiaoHang = phieuGiaoHang;
    }

    public ChiTietLoaiHang getChiTietLoaiHang() {
        return chiTietLoaiHang;
    }

    public void setChiTietLoaiHang(ChiTietLoaiHang chiTietLoaiHang) {
        this.chiTietLoaiHang = chiTietLoaiHang;
    }
    
    public ChiTietGiaoHang(String SoPhieuGiao, String MaCTLH, int SoLuong) {
        this.SoPhieuGiao = SoPhieuGiao;
        this.MaCTLH = MaCTLH;       
        this.SoLuong = SoLuong;
        this.updateKeyOld();
    }
    public ChiTietGiaoHang(ResultSet rs) throws SQLException {
        this.SoPhieuGiao = rs.getString("SoPhieuGiao");
        this.MaCTLH = rs.getString("MaCTLH");        
        this.SoLuong = rs.getInt("SoLuong");
        this.updateKeyOld();
    }
    public ChiTietGiaoHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.SoPhieuGiao = (String) hm.get("SoPhieuGiao");
        this.MaCTLH = (String) hm.get("MaCTLH");        
        this.SoLuong = (int) hm.get("SoLuong");
        this.updateKeyOld();
        
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoPhieuGiao);
            params.add(this.MaCTLH);            
            params.add(this.SoLuong);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoPhieuGiao);
            params.add(this.MaCTLH);
        return params;
    }

    @Override
    public void setValue(ArrayList<Object> parameter) {
      this.SoPhieuGiao = (String) parameter.get(0);
        this.MaCTLH = (String) parameter.get(1);
        this.SoLuong = (int) parameter.get(3);
        this.updateKeyOld();
    }
    public ChiTietGiaoHang() {
    }

    public String getSoPhieuGiao() {
        return SoPhieuGiao;
    }

    public void setSoPhieuGiao(String SoPhieuGiao) {
        this.SoPhieuGiao = SoPhieuGiao;
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
    
}
