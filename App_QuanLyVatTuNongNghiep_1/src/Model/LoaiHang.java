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
public class LoaiHang extends GetFieldName{
    private String MaLoaiHang;
    private String TenLoaiHang;
    
    private ArrayLists<HangHoa> hangHoas  = null;// new ArrayLists<HangHoa>();

    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hangHoas", hangHoas.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hangHoas", hangHoas.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hangHoas", hangHoas.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hangHoas", hangHoas.getData());
        return data;
    }
    
    public void khoiTaoLienKet(){
        hangHoas  = new ArrayLists<HangHoa>(this.task);
    };
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(LoaiHang.class.getDeclaredFields());
    }
    
    public ArrayLists<HangHoa> getHangHoas() {
        return hangHoas;
    }

    public void setHangHoas(ArrayLists<HangHoa> hangHoas) {
        this.hangHoas = hangHoas;
    }    

    public LoaiHang() {
    }

    public LoaiHang(String MaLoaiHang, String TenLoaiHang) {
        this.MaLoaiHang = MaLoaiHang;
        this.TenLoaiHang = TenLoaiHang;  
        this.updateKeyOld();
    }
//    public LoaiHang(ResultSet rs) throws SQLException {
//        this.MaLoaiHang = rs.getString("MaLoaiHang");
//        this.TenLoaiHang = rs.getString("TenLoaiHang");
    //this.updateKeyOld();
//    }
    public LoaiHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.MaLoaiHang = (String) hm.get("MaLoaiHang");
        this.TenLoaiHang = (String) hm.get("TenLoaiHang");
        khoiTaoLienKet();
        this.updateKeyOld();
    }
    
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.MaLoaiHang = (String) parameter.get(0);
        this.TenLoaiHang = (String) parameter.get(1);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaLoaiHang);
            params.add(this.TenLoaiHang);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaLoaiHang);            
        return params;
    }
    public String getMaLoaiHang() {
        return MaLoaiHang;
    }

    public void setMaLoaiHang(String MaLoaiHang) {
        this.MaLoaiHang = MaLoaiHang;
    }

    public String getTenLoaiHang() {
        return TenLoaiHang;
    }

    public void setTenLoaiHang(String TenLoaiHang) {
        this.TenLoaiHang = TenLoaiHang;
    }
    
}
