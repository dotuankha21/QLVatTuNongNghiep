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
public class PhieuDatHang extends GetFieldName{
    private String SoDonDatHang;
    private String MaNCC;
    private String MaNV;
    private Timestamp NgayDat;
    private int TongSL;
    private int ThanhTien;
    
    private NhaCungCap nhaCungCap = null;
    private NhanVien nhanVien = null;
    private ArrayLists<ChiTietDatHang> chiTietDatHangs = null;// new ArrayLists<ChiTietDatHang>();
    private ArrayLists<PhieuGiaoHang> phieuGiaoHangs = null;// new ArrayLists<PhieuGiaoHang>();

    
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhaCungCap", nhaCungCap.getParameter(remove_fieldNames));
        data.put("nhanVien", nhanVien.getParameter(remove_fieldNames));
        data.put("chiTietDatHangs", chiTietDatHangs.getData(remove_fieldNames));
        data.put("phieuGiaoHangs", phieuGiaoHangs.getData(remove_fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames)
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhaCungCap", nhaCungCap.getParameterAs(fieldNames));
        data.put("nhanVien", nhanVien.getParameterAs(fieldNames));
        data.put("chiTietDatHangs", chiTietDatHangs.getDataAs(fieldNames));
        data.put("phieuGiaoHangs", phieuGiaoHangs.getDataAs(fieldNames));
        return data;
    }
    
    @Override
    public HashMap<String, Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames) {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhaCungCap", nhaCungCap.includeAs(tblNames,fieldNames));
        data.put("nhanVien", nhanVien.includeAs(tblNames,fieldNames));
        data.put("chiTietDatHangs", chiTietDatHangs.includesAs(tblNames,fieldNames));
        data.put("phieuGiaoHangs", phieuGiaoHangs.includesAs(tblNames,fieldNames));
        return data;
    }
    
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        data.put("nhaCungCap", nhaCungCap.getParameter());
        data.put("nhanVien", nhanVien.getParameter());
        data.put("chiTietDatHangs", chiTietDatHangs.getData());
        data.put("phieuGiaoHangs", phieuGiaoHangs.getData());
        return data;
    }
    
    public void khoiTaoLienKet(){
        chiTietDatHangs = new ArrayLists<ChiTietDatHang>(this.task);
        phieuGiaoHangs = new ArrayLists<PhieuGiaoHang>(this.task);
    };
    
    public static Vector<String> getFields()
    {
        return  GetFieldName.GetFields(PhieuDatHang.class.getDeclaredFields());
    }
        
    public ArrayLists<ChiTietDatHang> getChiTietDatHangs() {
        return chiTietDatHangs;
    }

    public void setDhiChiTietDatHangs(ArrayLists<ChiTietDatHang> chiTietDatHangs) {
        this.chiTietDatHangs = chiTietDatHangs;
    }

    public ArrayLists<PhieuGiaoHang> getPhieuGiaoHangs() {
        return phieuGiaoHangs;
    }

    public void setPhieuGiaoHangs(ArrayLists<PhieuGiaoHang> phiuGiaoHangs) {
        this.phieuGiaoHangs = phiuGiaoHangs;
    }    

    public NhaCungCap getNhaCungCap() {
        return nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap nhaCungCap) {
        this.nhaCungCap = nhaCungCap;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }
    
    public PhieuDatHang() {
    }

    public PhieuDatHang(String SoDonDatHang, String MaNCC, String MaNV, Timestamp NgayDat, int TongSL, int ThanhTien) {
        this.SoDonDatHang = SoDonDatHang;
        this.MaNCC = MaNCC;
        this.MaNV = MaNV;
        this.NgayDat = NgayDat;
        this.TongSL = TongSL;
        this.ThanhTien = ThanhTien;
        this.updateKeyOld();
    }
    
    
    public PhieuDatHang(String SoDonDatHang, String MaNCC, String MaNV, int TongSL, int ThanhTien) {
        this.SoDonDatHang = SoDonDatHang;
        this.MaNCC = MaNCC;
        this.MaNV = MaNV;
        this.NgayDat = new Timestamp(new Date().getTime());
        this.TongSL = TongSL;
        this.ThanhTien = ThanhTien;
        this.updateKeyOld();
    }
    
    public PhieuDatHang(String SoDonDatHang, String MaNCC, String MaNV, int TongSL) {
        this.SoDonDatHang = SoDonDatHang;
        this.MaNCC = MaNCC;
        this.MaNV = MaNV;
        this.NgayDat = new Timestamp(new Date().getTime());
        this.TongSL = TongSL;
        this.ThanhTien = 0;
        this.updateKeyOld();
    }
    
    
    public PhieuDatHang(String SoDonDatHang, String MaNCC, String MaNV) {
        this.SoDonDatHang = SoDonDatHang;
        this.MaNCC = MaNCC;
        this.MaNV = MaNV;
        this.NgayDat = new Timestamp(new Date().getTime());
        this.TongSL = 0;
        this.ThanhTien = 0;
        this.updateKeyOld();
    }   
    
    public PhieuDatHang(ResultSet rs) throws SQLException {
        this.SoDonDatHang = rs.getString("SoDonDatHang");
        this.MaNCC = rs.getString("MaNCC");
        this.MaNV = rs.getString("MaNV");
        this.NgayDat = rs.getTimestamp("NgayDat");
        this.TongSL = rs.getInt("TongSL");
        this.ThanhTien = rs.getInt("ThanhTien");
        this.updateKeyOld();
    }
    public PhieuDatHang(HashMap hm,TaskDongBoLienKet task) {
        super(hm,task);
        this.SoDonDatHang = (String) hm.get("SoDonDatHang");
        this.MaNCC = (String) hm.get("MaNCC");
        this.MaNV = (String) hm.get("MaNV");
        this.NgayDat = (Timestamp) hm.get("NgayDat");
        this.TongSL = (int) hm.get("TongSL");
        this.ThanhTien = (int) hm.get("ThanhTien");
        khoiTaoLienKet();
        this.updateKeyOld();
    }
    
    @Override
    public void setValue(ArrayList<Object> parameter) {
        this.SoDonDatHang = (String) parameter.get(0);
        this.MaNCC = (String) parameter.get(1);
        this.MaNV = (String) parameter.get(2);
        this.NgayDat = (Timestamp) parameter.get(3);
        this.TongSL = (int) parameter.get(4);
        this.ThanhTien = (int) parameter.get(5);
        this.updateKeyOld();
    }
    @Override
    public Vector<Object> getParameter() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoDonDatHang);
            params.add(this.MaNCC);
            params.add(this.MaNV);
            params.add(this.NgayDat);
            params.add(this.TongSL);
            params.add(this.ThanhTien);
        return params;
    }
    @Override
    public Vector<Object> getKey() {
        Vector<Object> params = new Vector<Object>();
            params.add(this.SoDonDatHang);                                                         
        return params;
    }
    
    public String getSoDonDatHang() {
        return SoDonDatHang;
    }

    public void setSoDonDatHang(String SoDonDatHang) {
        this.SoDonDatHang = SoDonDatHang;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String MaNCC) {
        this.MaNCC = MaNCC;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public Timestamp getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(Timestamp NgayDat) {
        this.NgayDat = NgayDat;
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
    
    public int getSoLuongDaGiao(String maCTLH)
    {
        if(this.getPhieuGiaoHangs().size()==0)
            return 0;
        int sl = 0;
        //System.out.println("số lượng "+this.getPhieuGiaoHangs().size());
        for (PhieuGiaoHang pgh : this.getPhieuGiaoHangs()) {
            if(!pgh.getTrangThai().equalsIgnoreCase("Bị hủy"))
            {
                //System.out.println("ssadasd "+pgh.getChiTietGiaoHangs().size());
                ChiTietGiaoHang ctgh = pgh.getChiTietGiaoHangs().get(ct->ct.getMaCTLH().trim().equals(maCTLH.trim()));
                if(ctgh!=null)
                    sl += ctgh.getSoLuong();
            }
        }
        return sl;
    }
}
