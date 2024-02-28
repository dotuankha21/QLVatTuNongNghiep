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
public class HangHoa extends GetFieldName{
    private String MaHang;
    private String TenHang;
    private String AnhHangHoa;
    private String MaLoaiHang;
    private String DonViTinh;
    private LoaiHang loaiHang =null; 
    private ArrayLists<ChiTietLoaiHang> chiTietLoaiHangs = null; // new ArrayLists<ChiTietLoaiHang>();

    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietHoaDons", chiTietLoaiHangs.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietHoaDons", chiTietLoaiHangs.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietHoaDons", chiTietLoaiHangs.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietHoaDons", chiTietLoaiHangs.getData());
        return data;
    }
    
    public void khoiTaoLienKet(){
        chiTietLoaiHangs = new ArrayLists<ChiTietLoaiHang>(task);
    };
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(HangHoa.class.getDeclaredFields());
    }
    
    public ArrayLists<ChiTietLoaiHang> getChiTietLoaiHangs() {
        return chiTietLoaiHangs;
    }

    public void setChiTietLoaiHangs(ArrayLists<ChiTietLoaiHang> chiTietLoaiHangs) {
        this.chiTietLoaiHangs = chiTietLoaiHangs;
    }
        
    public LoaiHang getLoaiHang() {
        return loaiHang;
    }

    public void setLoaiHang(LoaiHang loaiHang) {
        this.loaiHang = loaiHang;
    }           
    public HangHoa() {
    }

    public HangHoa(String MaHang, String TenHang, String AnhHangHoa, String MaLoaiHang, String DonViTinh) {
        this.MaHang = MaHang;
        this.TenHang = TenHang;
        this.AnhHangHoa = AnhHangHoa;
        this.MaLoaiHang = MaLoaiHang;
        this.DonViTinh = DonViTinh;
        this.updateKeyOld();
    }    
    
    public HangHoa(ResultSet rs) throws SQLException  {
        this.MaHang = rs.getString("MaHang");
        this.TenHang = rs.getString("TenHang");
        this.AnhHangHoa = rs.getString("AnhHangHoa");
        this.MaLoaiHang = rs.getString("MaLoaiHang");
        this.DonViTinh = rs.getString("DonViTinh");
        this.updateKeyOld();
    }
    public HangHoa(HashMap hm,TaskDongBoLienKet task)  {
        super(hm,task);
        this.MaHang = (String) hm.get("MaHang");
        this.TenHang = (String) hm.get("TenHang");
        this.AnhHangHoa = (String) hm.get("AnhHangHoa");
        this.MaLoaiHang = (String) hm.get("MaLoaiHang");
        this.DonViTinh = (String) hm.get("DonViTinh");
        khoiTaoLienKet();
        this.updateKeyOld();
    }
    @Override
    public void setValue(ArrayList<Object> parameter) {
       this.MaHang = (String) parameter.get(0);
        this.TenHang = (String) parameter.get(1);
        this.AnhHangHoa = (String) parameter.get(2);
        this.MaLoaiHang = (String) parameter.get(3);
        this.DonViTinh = (String) parameter.get(4);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaHang);
            params.add(this.TenHang);
            params.add(this.AnhHangHoa);
            params.add(this.MaLoaiHang);
            params.add(this.DonViTinh);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaHang);
        return params;
    }    

    public String getAnhHangHoa() {
        return AnhHangHoa;
    }

    public void setAnhHangHoa(String AnhHangHoa) {
        this.AnhHangHoa = AnhHangHoa;
    }
    
    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public String getTenHang() {
        return TenHang;
    }

    public void setTenHang(String TenHang) {
        this.TenHang = TenHang;
    }

    public String getMaLoaiHang() {
        return MaLoaiHang;
    }

    public void setMaLoaiHang(String MaLoaiHang) {
        this.MaLoaiHang = MaLoaiHang;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String DonViTinh) {
        this.DonViTinh = DonViTinh;
    }
    
}
