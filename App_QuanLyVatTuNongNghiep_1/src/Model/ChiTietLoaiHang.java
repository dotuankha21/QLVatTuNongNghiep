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
public class ChiTietLoaiHang extends GetFieldName{
    private String MaCTLH;
    private String MaHang;
    private String TenLoai;
    private int GiaMacDinh;
    private int SoLuongTon;    
    
    private HangHoa hangHoa = null;    
        
    private ArrayLists<ChiTietDatHang> chiTietDatHangs = null; // new ArrayLists<ChiTietDatHang>();
    private ArrayLists<ChiTietGiaoHang> chiTietGiaoHangs = null; // new ArrayLists<ChiTietGiaoHang>();
    private ArrayLists<ChiTietHoaDon> chiTietHoaDons = null; // new ArrayLists<ChiTietHoaDon>();
    private ArrayLists<GiaMatHang> giaMatHangs = null; // new ArrayLists<GiaMatHang>();
    private ArrayLists<NguonHang> nguonHangs = null; // new ArrayLists<NguonHang>();
    
    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hangHoa", hangHoa.getParameter(remove_fieldNames));
        data.put("chiTietDatHangs", chiTietDatHangs.getData(remove_fieldNames));
        data.put("chiTietGiaoHangs", chiTietGiaoHangs.getData(remove_fieldNames));
        data.put("chiTietHoaDons", chiTietHoaDons.getData(remove_fieldNames));
        data.put("giaMatHangs", giaMatHangs.getData(remove_fieldNames));
        data.put("nguonHangs", nguonHangs.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hangHoa", hangHoa.getParameterAs(fieldNames));
        data.put("chiTietDatHangs", chiTietDatHangs.getDataAs(fieldNames));
        data.put("chiTietGiaoHangs", chiTietGiaoHangs.getDataAs(fieldNames));
        data.put("chiTietHoaDons", chiTietHoaDons.getDataAs(fieldNames));
        data.put("giaMatHangs", giaMatHangs.getDataAs(fieldNames));
        data.put("nguonHangs", nguonHangs.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {        
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("hangHoa", hangHoa.includeAs(tblNames,fieldNames));
        data.put("chiTietDatHangs", chiTietDatHangs.includesAs(tblNames,fieldNames));
        data.put("chiTietGiaoHangs", chiTietGiaoHangs.includesAs(tblNames,fieldNames));
        data.put("chiTietHoaDons", chiTietHoaDons.includesAs(tblNames,fieldNames));
        data.put("giaMatHangs", giaMatHangs.includesAs(tblNames,fieldNames));
        data.put("nguonHangs", nguonHangs.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("chiTietDatHangs", chiTietDatHangs.getData());
        data.put("chiTietGiaoHangs", chiTietGiaoHangs.getData());
        data.put("chiTietHoaDons", chiTietHoaDons.getData());
        data.put("giaMatHangs", giaMatHangs.getData());
        data.put("nguonHangs", nguonHangs.getData());
        return data;
    }
    
    public void khoiTaoLienKet()
    {
        chiTietDatHangs = new ArrayLists<ChiTietDatHang>(task);
        chiTietGiaoHangs = new ArrayLists<ChiTietGiaoHang>(task);
        chiTietHoaDons = new ArrayLists<ChiTietHoaDon>(task);
        giaMatHangs = new ArrayLists<GiaMatHang>(task);
        nguonHangs = new ArrayLists<NguonHang>(task);
    }
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(ChiTietLoaiHang.class.getDeclaredFields());
    }

    public ArrayLists<ChiTietDatHang> getChiTietDatHangs() {
        return chiTietDatHangs;
    }

    public void setChiTietDatHangs(ArrayLists<ChiTietDatHang> chiTietDatHangs) {
        this.chiTietDatHangs = chiTietDatHangs;
    }

    public ArrayLists<ChiTietGiaoHang> getChiTietGiaoHangs() {
        return chiTietGiaoHangs;
    }

    public void setChiTietGiaoHangs(ArrayLists<ChiTietGiaoHang> chiTietGiaoHangs) {
        this.chiTietGiaoHangs = chiTietGiaoHangs;
    }

    public ArrayLists<ChiTietHoaDon> getChiTietHoaDons() {
        return chiTietHoaDons;
    }

    public void setChiTietHoaDons(ArrayLists<ChiTietHoaDon> chiTietHoaDons) {
        this.chiTietHoaDons = chiTietHoaDons;
    }

    public ArrayLists<GiaMatHang> getGiaMatHangs() {
        return giaMatHangs;
    }

    public void setGiaMatHangs(ArrayLists<GiaMatHang> giaMatHangs) {
        this.giaMatHangs = giaMatHangs;
    }

    public ArrayLists<NguonHang> getNguonHangs() {
        return nguonHangs;
    }

    public void setNguonHangs(ArrayLists<NguonHang> nguonHangs) {
        this.nguonHangs = nguonHangs;
    }       
    
    public HangHoa getHangHoa() {
        return hangHoa;
    }

    public void setHangHoa(HangHoa hangHoa) {
        this.hangHoa = hangHoa;
    }
    
    public ChiTietLoaiHang() {
    }  
    public String getMaCTLH() {
        return MaCTLH;
    }

    public void setMaCTLH(String MaCTLH) {
        this.MaCTLH = MaCTLH;
    }

    public String getMaHang() {
        return MaHang;
    }

    public void setMaHang(String MaHang) {
        this.MaHang = MaHang;
    }

    public int getGiaMacDinh() {
        return GiaMacDinh;
    }

    public void setGiaMacDinh(int GiaMacDinh) {
        this.GiaMacDinh = GiaMacDinh;
    }
    
    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int SoLuongTon) {
        this.SoLuongTon = SoLuongTon;
    }

    public String getTenLoai() {
        return TenLoai;
    }

    public void setTenLoai(String TenLoai) {
        this.TenLoai = TenLoai;
    }

    

    public ChiTietLoaiHang(String MaCTLH, String MaHang, String TenLoai, int GiaMacDinh, int SoLuongTon) {
        this.MaCTLH = MaCTLH;
        this.MaHang = MaHang;
        this.TenLoai = TenLoai;
        this.GiaMacDinh = GiaMacDinh;
        this.SoLuongTon = SoLuongTon;
        this.updateKeyOld();
    }
    
    public ChiTietLoaiHang(ResultSet rs) throws SQLException {
        this.MaCTLH = rs.getString("MaCTLH");
        this.MaHang = rs.getString("MaHang");
        this.TenLoai = rs.getString("TenLoai");
        this.GiaMacDinh = rs.getInt("GiaMacDinh");
        this.SoLuongTon = rs.getInt("SoLuongTon"); 
        this.updateKeyOld();
    }
    public ChiTietLoaiHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);       
        this.MaCTLH = (String) hm.get("MaCTLH");
        this.MaHang = (String) hm.get("MaHang");
        this.TenLoai = (String) hm.get("TenLoai");
        this.GiaMacDinh = (int) hm.get("GiaMacDinh");
        this.SoLuongTon = (int) hm.get("SoLuongTon");        
        this.khoiTaoLienKet();        
        this.updateKeyOld();
    }
    
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.MaCTLH = (String) parameter.get(0);
        this.MaHang = (String) parameter.get(2);
        this.SoLuongTon = (int) parameter.get(3);
        this.TenLoai = (String) parameter.get(4);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaCTLH);
            params.add(this.MaHang);
            params.add(this.TenLoai);
            params.add(this.GiaMacDinh);
            params.add(this.SoLuongTon);            
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.MaCTLH);
        return params;
    }
    
}
