/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Admin
 */
public class PhieuGiaoHang extends GetFieldName{
    private String SoPhieuGiao;
    private String SoDonDatHang;
    private Timestamp NgayGiao;
    private int TongSL;
    private int ThanhTien;
    private String TrangThai;
    private String GhiChu;
    
    private PhieuDatHang phieuDatHang = null;
    private ArrayLists<ChiTietGiaoHang> chiTietGiaoHangs = null; //new ArrayLists<ChiTietGiaoHang>();

    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuDatHang", phieuDatHang.getParameter(remove_fieldNames));
        data.put("chiTietGiaoHangs", chiTietGiaoHangs.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuDatHang", phieuDatHang.getParameterAs(fieldNames));
        data.put("chiTietGiaoHangs", chiTietGiaoHangs.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuDatHang", phieuDatHang.includeAs(tblNames,fieldNames));
        data.put("chiTietGiaoHangs", chiTietGiaoHangs.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("phieuDatHang", phieuDatHang.getParameter());
        data.put("chiTietGiaoHangs", chiTietGiaoHangs.getData());
        return data;
    }
    
    public void khoiTaoLienKet(){
        chiTietGiaoHangs = new ArrayLists<ChiTietGiaoHang>(this.task);
    };
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(PhieuGiaoHang.class.getDeclaredFields());
    }
    
    public PhieuDatHang getPhieuDatHang() {
        return phieuDatHang;
    }

    public void setPhieuDatHang(PhieuDatHang phieuDatHang) {
        this.phieuDatHang = phieuDatHang;
    }

    public ArrayLists<ChiTietGiaoHang> getChiTietGiaoHangs() {
        return chiTietGiaoHangs;
    }

    public void setChiTietGiaoHangs(ArrayLists<ChiTietGiaoHang> chiTietGiaoHangs) {
        this.chiTietGiaoHangs = chiTietGiaoHangs;
    }    

    public PhieuGiaoHang() {
    }

    public PhieuGiaoHang(String SoPhieuGiao, String SoDonDatHang, Timestamp NgayGiao, int TongSL, int ThanhTien, String TrangThai,String GhiChu) {
        this.SoPhieuGiao = SoPhieuGiao;
        this.SoDonDatHang = SoDonDatHang;
        this.NgayGiao = NgayGiao;
        this.TongSL = TongSL;
        this.ThanhTien = ThanhTien;
        this.TrangThai = TrangThai;
        this.GhiChu = GhiChu;
        this.updateKeyOld();
    }
    
    public PhieuGiaoHang(String SoPhieuGiao, String SoDonDatHang, Timestamp NgayGiao, int TongSL, int ThanhTien) {
        this.SoPhieuGiao = SoPhieuGiao;
        this.SoDonDatHang = SoDonDatHang;
        this.NgayGiao = NgayGiao;
        this.TongSL = TongSL;
        this.ThanhTien = ThanhTien;
        this.TrangThai = "Chờ xác nhận";
        this.GhiChu = "";
        this.updateKeyOld();
    }
    
    public PhieuGiaoHang(String SoPhieuGiao, String SoDonDatHang, int TongSL, int ThanhTien) {
        this.SoPhieuGiao = SoPhieuGiao;
        this.SoDonDatHang = SoDonDatHang;
        this.NgayGiao = new Timestamp(new Date().getTime());
        this.TongSL = TongSL;
        this.ThanhTien = ThanhTien;
        this.TrangThai = "Chờ xác nhận";
        this.GhiChu = null;
        this.updateKeyOld();
    }
    
    public PhieuGiaoHang(String SoPhieuGiao, String SoDonDatHang) {
        this.SoPhieuGiao = SoPhieuGiao;
        this.SoDonDatHang = SoDonDatHang;
        this.NgayGiao = new Timestamp(new Date().getTime());
        this.TongSL = 0;
        this.ThanhTien = 0;
        this.TrangThai = "Chờ xác nhận";
        this.GhiChu = null;
        this.updateKeyOld();
    }
    
    public PhieuGiaoHang(ResultSet rs) throws SQLException {
        this.SoPhieuGiao = rs.getString("SoPhieuGiao");
        this.SoDonDatHang = rs.getString("SoDonDatHang");
        this.NgayGiao = rs.getTimestamp("NgayGiao");
        this.TongSL = rs.getInt("TongSL");
        this.ThanhTien = rs.getInt("ThanhTien");
        this.TrangThai = rs.getString("TrangThai");
        this.GhiChu = rs.getString("GhiChu");
        this.updateKeyOld();
    }
    public PhieuGiaoHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.SoPhieuGiao = (String) hm.get("SoPhieuGiao");
        this.SoDonDatHang = (String) hm.get("SoDonDatHang");
        this.NgayGiao = (Timestamp) hm.get("NgayGiao");
        this.TongSL = (int) hm.get("TongSL");
        this.ThanhTien = (int) hm.get("ThanhTien");
        this.TrangThai = (String) hm.get("TrangThai");
        this.GhiChu = (String) hm.get("GhiChu");
        khoiTaoLienKet();
        this.updateKeyOld();
    }
    
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.SoPhieuGiao = (String) parameter.get(0);
        this.SoDonDatHang = (String) parameter.get(1);
        this.NgayGiao = (Timestamp) parameter.get(2);
        this.TongSL = (int) parameter.get(3);
        this.ThanhTien = (int) parameter.get(4);
        this.TrangThai = (String) parameter.get(5);
        this.GhiChu = (String) parameter.get(6);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoPhieuGiao);
            params.add(this.SoDonDatHang);
            params.add(this.NgayGiao);
            params.add(this.TongSL);
            params.add(this.ThanhTien);
            params.add(this.TrangThai);
            params.add(this.GhiChu);
        return params;
    }
     @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoPhieuGiao);                                                         
        return params;
    }
    
    public String getSoPhieuGiao() {
        return SoPhieuGiao;
    }

    public void setSoPhieuGiao(String SoPhieuGiao) {
        this.SoPhieuGiao = SoPhieuGiao;
    }

    public String getSoDonDatHang() {
        return SoDonDatHang;
    }

    public void setSoDonDatHang(String SoDonDatHang) {
        this.SoDonDatHang = SoDonDatHang;
    }

    public Timestamp getNgayGiao() {
        return NgayGiao;
    }

    public void setNgayGiao(Timestamp NgayGiao) {
        this.NgayGiao = NgayGiao;
    }

    public int getTongSL() {
        return TongSL;
    }

    public void setTongSL(int TongSL) {
        this.TongSL = TongSL;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int ThanhTien) {
        this.ThanhTien = ThanhTien;
    }    

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String TrangThai) {
        this.TrangThai = TrangThai;
    }    

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String GhiChu) {
        this.GhiChu = GhiChu;
    }
    
}
