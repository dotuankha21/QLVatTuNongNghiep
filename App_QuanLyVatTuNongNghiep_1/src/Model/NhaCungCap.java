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
public class NhaCungCap extends GetFieldName{
    private String MaNCC;
    private String TenNCC;
    private String DiaChi;
    private String SoDienThoai;
    
    private ArrayLists<NguonHang> nguonHangs = null;// new ArrayLists<NguonHang>();   
    private ArrayLists<PhieuDatHang> phieuDatHangs = null;// new ArrayLists<PhieuDatHang>();   

    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nguonHangs", nguonHangs.getData(remove_fieldNames));
        data.put("phieuDatHangs", phieuDatHangs.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nguonHangs", nguonHangs.getDataAs(fieldNames));
        data.put("phieuDatHangs", phieuDatHangs.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nguonHangs", nguonHangs.includesAs(tblNames,fieldNames));
        data.put("phieuDatHangs", phieuDatHangs.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nguonHangs", nguonHangs.getData());
        data.put("phieuDatHangs", phieuDatHangs.getData());
        return data;
    }
    
    public void khoiTaoLienKet(){
        nguonHangs = new ArrayLists<NguonHang>(this.task);
        phieuDatHangs = new ArrayLists<PhieuDatHang>(this.task);  
    };
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(NhaCungCap.class.getDeclaredFields());
    }
    
    public ArrayLists<NguonHang> getNguonHangs() {
        return nguonHangs;
    }
    
    public void setNguonHangs(ArrayLists<NguonHang> nguonHangs) {
        this.nguonHangs = nguonHangs;
    }    

    public ArrayLists<PhieuDatHang> getPhieuDatHangs() {       
        return phieuDatHangs;
    }

    public void setPhieuDatHangs(ArrayLists<PhieuDatHang> phieuDatHangs) {
        this.phieuDatHangs = phieuDatHangs;
    }        

    public NhaCungCap(String MaNCC, String TenNCC, String DiaChi, String SoDienThoai) {
        this.MaNCC = MaNCC;
        this.TenNCC = TenNCC;
        this.DiaChi = DiaChi;
        this.SoDienThoai = SoDienThoai;
        this.updateKeyOld();
    }
    public NhaCungCap(ResultSet rs) throws SQLException  {
        this.MaNCC = rs.getString("MaNCC");
        this.TenNCC = rs.getString("TenNCC");
        this.DiaChi = rs.getString("DiaChi");
        this.SoDienThoai = rs.getString("SoDienThoai");
        this.updateKeyOld();
    }
    public NhaCungCap(HashMap hm,TaskDongBoLienKet task)  {
        super(hm,task);
        this.MaNCC = (String) hm.get("MaNCC");
        this.TenNCC = (String) hm.get("TenNCC");
        this.DiaChi = (String) hm.get("DiaChi");
        this.SoDienThoai = (String) hm.get("SoDienThoai");
        khoiTaoLienKet();
        this.updateKeyOld();
    }
    
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.MaNCC = (String) parameter.get(0);
        this.TenNCC = (String) parameter.get(1);
        this.DiaChi = (String) parameter.get(2);
        this.SoDienThoai = (String) parameter.get(3);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaNCC);
            params.add(this.TenNCC);
            params.add(this.DiaChi);
            params.add(this.SoDienThoai);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaNCC);                                   
        return params;
    }
    public NhaCungCap() {
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String TenNCC) {
        this.TenNCC = TenNCC;
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
    
}
