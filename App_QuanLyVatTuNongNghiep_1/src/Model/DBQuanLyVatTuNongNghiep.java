/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import MeThods.KetNoi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class DBQuanLyVatTuNongNghiep {
    
    ArrayLists<ChiTietDatHang> chiTietDatHangs = null;
    ArrayLists<ChiTietGiaoHang> chiTietGiaoHangs = null;
    ArrayLists<ChiTietHoaDon> chiTietHoaDons = null;
    ArrayLists<ChiTietLoaiHang> chiTietLoaiHangs = null;
    ArrayLists<GiaMatHang> giaMatHangs = null;
    ArrayLists<HangHoa> hangHoas = null;
    ArrayLists<HoaDon> hoaDons = null;
    ArrayLists<KhachHang> khachHangs = null;
    ArrayLists<LoaiHang> loaiHangs = null;
    ArrayLists<NguonHang> nguonHangs = null;
    ArrayLists<NhaCungCap> nhaCungCaps = null;
    ArrayLists<NhanVien> nhanViens = null;
    ArrayLists<PhieuDatHang> phieuDatHangs = null;
    ArrayLists<PhieuGiaoHang> phieuGiaoHangs = null;
    TaskDongBoLienKet task = null;

    public TaskDongBoLienKet getTask() {
        return task;
    }                 
    public static <T> String getClassName(T cls)
    {
        String[] strName = cls.getClass().getName().split("\\Q.\\E");
        ArrayList<String> nameCls = new ArrayList<String>(); 
//        nameCls.addAll(nameCls.size(), nameCls);
        for (String name : strName)
            nameCls.add(name);                  
        return nameCls.get(nameCls.size()-1);
    }
//    public DBQuanLyVatTuNongNghiep() {
//        KetNoi.getInstance();       
//        task = new TaskDongBoLienKet(this);
//        task.start();
//        chiTietDatHangs = new ArrayLists<ChiTietDatHang>(task);        
//        ArrayList<HashMap> data = KetNoi.getInstance().SelectALL("ChiTietDatHang");
//        if(data !=null)
//            for (HashMap hm : data) 
//                chiTietDatHangs.add(new ChiTietDatHang(hm,task));                     
//        chiTietGiaoHangs = new ArrayLists<ChiTietGiaoHang>(task) ;
//        data = KetNoi.getInstance().SelectALL("ChiTietGiaoHang");
//        if(data !=null)
//            for (HashMap hm : data) 
//                chiTietGiaoHangs.add(new ChiTietGiaoHang(hm,task));         
//        
//        chiTietHoaDons = new ArrayLists<ChiTietHoaDon>(task);
//        data = KetNoi.getInstance().SelectALL("ChiTietHoaDon");
//        if(data !=null)
//            for (HashMap hm : data) 
//                chiTietHoaDons.add(new ChiTietHoaDon(hm,task));                 
//        chiTietLoaiHangs = new  ArrayLists<ChiTietLoaiHang>(task);
//        data = KetNoi.getInstance().SelectALL("ChiTietLoaiHang"); 
//        if(data !=null)
//            for (HashMap hm : data) 
//                chiTietLoaiHangs.add(new ChiTietLoaiHang(hm,task));         
//        
//        giaMatHangs = new ArrayLists<GiaMatHang> (task);
//        data = KetNoi.getInstance().SelectALL("GiaMatHang");    
//        if(data !=null)
//            for (HashMap hm : data) 
//                giaMatHangs.add(new GiaMatHang(hm,task));         
//        
//        hangHoas = new ArrayLists<HangHoa> (task);
//        data = KetNoi.getInstance().SelectALL("HangHoa"); 
//        if(data !=null)
//            for (HashMap hm : data) 
//                hangHoas.add(new HangHoa(hm,task));         
//        
//        hoaDons = new ArrayLists<HoaDon> (task);
//        data = KetNoi.getInstance().SelectALL("HoaDon"); 
//        if(data !=null)
//            for (HashMap hm : data) 
//                hoaDons.add(new HoaDon(hm,task));         
//            
//        khachHangs = new ArrayLists<KhachHang> (task);
//        data = KetNoi.getInstance().SelectALL("KhachHang"); 
//        if(data !=null)
//            for (HashMap hm : data) 
//                khachHangs.add(new KhachHang(hm,task));         
//            
//        loaiHangs = new ArrayLists<LoaiHang> (task);
//        data = KetNoi.getInstance().SelectALL("LoaiHang");    
//        if(data !=null)
//            for (HashMap hm : data) 
//                loaiHangs.add(new LoaiHang(hm,task));         
//            
//        nguonHangs = new ArrayLists<NguonHang> (task);
//        data = KetNoi.getInstance().SelectALL("NguonHang");
//        if(data !=null)
//            for (HashMap hm : data) 
//                nguonHangs.add(new NguonHang(hm,task));         
//            
//        nhanViens = new ArrayLists<NhanVien> (task);
//        data = KetNoi.getInstance().SelectALL("NhanVien"); 
//        if(data !=null)
//            for (HashMap hm : data) 
//                nhanViens.add(new NhanVien(hm,task));         
//            
//        phieuDatHangs = new ArrayLists<PhieuDatHang> (task);
//        data = KetNoi.getInstance().SelectALL("PhieuDatHang");    
//        if(data !=null)
//            for (HashMap hm : data) 
//                phieuDatHangs.add(new PhieuDatHang(hm,task));         
//        
//        phieuGiaoHangs = new ArrayLists<PhieuGiaoHang> (task);
//        data = KetNoi.getInstance().SelectALL("PhieuGiaoHang");  
//        if(data !=null)
//            for (HashMap hm : data) 
//                phieuGiaoHangs.add(new PhieuGiaoHang(hm,task));         
//            
//        nhaCungCaps = new ArrayLists<NhaCungCap>(task);
//        data = KetNoi.getInstance().SelectALL("NhaCungCap");  
//        if(data !=null)
//            for (HashMap hm : data) 
//                nhaCungCaps.add(new NhaCungCap(hm,task));  
//        LienKetThucThe();         
//    }
//    
    
    public DBQuanLyVatTuNongNghiep() {
        KetNoi.getInstance();       
//        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
//        int threadCount = threadGroup.activeCount();
//        Thread threadList[] = new Thread[threadCount];        
//        threadGroup.enumerate(threadList);
//        System.out.println("ban đầu: "+threadCount);
//        for (int i = 0; i < threadCount; i++)
//            System.out.println(threadList[i].getName());        
        task = new TaskDongBoLienKet(this);
        task.start(); 
        chiTietDatHangs = new ArrayLists<ChiTietDatHang>(task,new TypeToken<ArrayList<ChiTietDatHang>>(){}.getType(),"ChiTietDatHang");
        chiTietGiaoHangs = new ArrayLists<ChiTietGiaoHang>(task,new TypeToken<ArrayList<ChiTietGiaoHang>>(){}.getType(),"ChiTietGiaoHang") ;        
        chiTietHoaDons = new ArrayLists<ChiTietHoaDon>(task,new TypeToken<ArrayList<ChiTietHoaDon>>(){}.getType(),"ChiTietHoaDon");                  
        chiTietLoaiHangs = new  ArrayLists<ChiTietLoaiHang>(task,new TypeToken<ArrayList<ChiTietLoaiHang>>(){}.getType(),"ChiTietLoaiHang");        
        giaMatHangs = new ArrayLists<GiaMatHang> (task,new TypeToken<ArrayList<GiaMatHang>>(){}.getType(),"GiaMatHang");
        hangHoas = new ArrayLists<HangHoa> (task,new TypeToken<ArrayList<HangHoa>>(){}.getType(),"HangHoa");                       
        //hoaDons = new ArrayLists<HoaDon> (task,new TypeToken<ArrayList<HoaDon>>(){}.getType(),"HoaDon");        
        hoaDons = new ArrayLists<HoaDon> (task);
        ArrayList<HashMap> data = KetNoi.getInstance().SelectALL("HoaDon"); 
        if(data !=null)
            for (HashMap hm : data) 
                hoaDons.add(new HoaDon(hm,task));
        khachHangs = new ArrayLists<KhachHang> (task,new TypeToken<ArrayList<KhachHang>>(){}.getType(),"KhachHang");        
        loaiHangs = new ArrayLists<LoaiHang> (task,new TypeToken<ArrayList<LoaiHang>>(){}.getType(),"LoaiHang");                  
        nguonHangs = new ArrayLists<NguonHang> (task,new TypeToken<ArrayList<NguonHang>>(){}.getType(),"NguonHang");
        //nhanViens = new ArrayLists<NhanVien> (task,new TypeToken<ArrayList<NhanVien>>(){}.getType(),"NhanVien");        
//        phieuDatHangs = new ArrayLists<PhieuDatHang> (task,new TypeToken<ArrayList<PhieuDatHang>>(){}.getType(),"PhieuDatHang");                       
//        phieuGiaoHangs = new ArrayLists<PhieuGiaoHang> (task,new TypeToken<ArrayList<PhieuGiaoHang>>(){}.getType(),"PhieuGiaoHang");    
        nhanViens = new ArrayLists<NhanVien> (task);
        data = KetNoi.getInstance().SelectALL("NhanVien"); 
        if(data !=null)
            for (HashMap hm : data) 
                nhanViens.add(new NhanVien(hm,task)); 
        phieuDatHangs = new ArrayLists<PhieuDatHang> (task);
        data = KetNoi.getInstance().SelectALL("PhieuDatHang");    
        if(data !=null)
            for (HashMap hm : data) 
                phieuDatHangs.add(new PhieuDatHang(hm,task));                 
        phieuGiaoHangs = new ArrayLists<PhieuGiaoHang>(task);
        data = KetNoi.getInstance().SelectALL("PhieuGiaoHang");  
        if(data !=null)
            for (HashMap hm : data) 
                phieuGiaoHangs.add(new PhieuGiaoHang(hm,task));
        else
            System.out.println("null");
        nhaCungCaps = new ArrayLists<NhaCungCap>(task,new TypeToken<ArrayList<NhaCungCap>>(){}.getType(),"NhaCungCap");
        LienKetThucThe();         
    }  
    private synchronized void LienKetThucThe()
    {
        this.chiTietDatHangs.parallelStream().forEach(t->{
            lienKetChiTietDatHang_FK(t);            
        });
        this.chiTietGiaoHangs.parallelStream().forEach(t->{
            lienKetChiTietGiaoHang_FK(t);           
        });
        
        this.chiTietHoaDons.parallelStream().forEach(t->{
            lienKetChiTietHoaDon_FK(t);                         
        });
        
        this.chiTietLoaiHangs.parallelStream().forEach(t->{            
            lienKetChiTietLoaiHang_FK(t);              
            lienKetChiTietLoaiHang_PK(t);
        });
        
        this.giaMatHangs.parallelStream().forEach(t->{            
            lienKetGiaMatHang_FK(t);
        });
        
        this.hangHoas.parallelStream().forEach(t->{
            lienKetHangHoa_FK(t);
            lienKetHangHoa_PK(t);
        });
        
        this.hoaDons.parallelStream().forEach(t->{
            lienKetHoaDon_FK(t);            
            lienKetHoaDon_PK(t);
        });
        
        this.khachHangs.parallelStream().forEach(t->{                        
            lienKetKhachHang_PK(t);
        });
        
        this.loaiHangs.parallelStream().forEach(t->{                        
            lienKetLoaiHang_PK(t);
        });
        
        this.nguonHangs.parallelStream().forEach(t->{                        
            lienKetNguonHang_FK(t);             
        });
        
        this.nhaCungCaps.parallelStream().forEach(t->{                        
            lienKetNhaCungCap_PK(t);
        });
        this.nhanViens.parallelStream().forEach(t->{                        
            lienKetNhanVien_PK(t);
        });
        
        this.phieuDatHangs.parallelStream().forEach(t->{                                    
            lienKetPhieuDatHang_PK(t);
            lienKetPhieuDatHangHang_FK(t);
        });
        
        this.phieuGiaoHangs.parallelStream().forEach(t->{                                    
            lienKetPhieuGiaoHang_FK(t);
            lienKetPhieuGiaoHang_PK(t);
        });
    }
    
    public void lienKetChiTietDatHang_FK(ChiTietDatHang t)
    {
        try{t.setChiTietLoaiHang(this.chiTietLoaiHangs.parallelStream().filter(lh->lh.getMaCTLH().trim().equals(t.getMaCTLH())).findFirst().get());
            }catch (Exception ex){}
        
        try{t.setPhieuDatHang(this.phieuDatHangs.parallelStream().filter(p->p.getSoDonDatHang().trim().equals(t.getSoDonDatHang())).findFirst().get());
            }catch (Exception ex){}
    }
    
    public void lienKetChiTietGiaoHang_FK(ChiTietGiaoHang t)
    {        
        try{t.setChiTietLoaiHang(this.chiTietLoaiHangs.parallelStream().filter(lh->lh.getMaCTLH().trim().equals(t.getMaCTLH().trim())).findFirst().get());
            }catch (Exception ex){}
        try{t.setPhieuGiaoHang(this.phieuGiaoHangs.parallelStream().filter(p->p.getSoPhieuGiao().trim().equals(t.getSoPhieuGiao().trim())).findFirst().get());
            }catch (Exception ex){} 
    }
   
    public void lienKetChiTietHoaDon_FK(ChiTietHoaDon t)
    {
        try{t.setChiTietLoaiHang(this.chiTietLoaiHangs.parallelStream().filter(lh->lh.getMaCTLH().equals(t.getMaCTLH())).findFirst().get());
            }catch (Exception ex){}              
        try{t.setHoaDon(this.hoaDons.parallelStream().filter(p->p.getSoHoaDon().trim().equals(t.getSoHoaDon())).findFirst().get());
            }catch (Exception ex){}         
    }
    public void lienKetChiTietLoaiHang_FK(ChiTietLoaiHang t)
    {
        try{t.setHangHoa(this.hangHoas.parallelStream().filter(lh->lh.getMaHang().trim().equals(t.getMaHang())).findFirst().get());
            }catch (Exception ex){}                        
    }
    public void lienKetChiTietLoaiHang_PK(ChiTietLoaiHang t)
    {        
            //this.chiTietDatHangs.forEach(v->System.out.println(t.getMaCTLH()));
            this.chiTietDatHangs.parallelStream().filter(p->p.getMaCTLH().equals(t.getMaCTLH())).forEach(v->{                
                t.getChiTietDatHangs().add(v);
            });
            this.chiTietGiaoHangs.parallelStream().filter(g->g.getMaCTLH().equals(t.getMaCTLH())).forEach(v->{
                t.getChiTietGiaoHangs().add(v);
            });            
                                   
            this.chiTietHoaDons.parallelStream().filter(g->g.getMaCTLH().equals(t.getMaCTLH())).forEach(v->{                
                t.getChiTietHoaDons().add(v);
            });
            this.giaMatHangs.parallelStream().filter(g->g.getMaCTLH().equals(t.getMaCTLH())).forEach(v->{
                t.getGiaMatHangs().add(v);
            });
            this.nguonHangs.parallelStream().filter(g->g.getMaCTLH().equals(t.getMaCTLH())).forEach(v->{
                t.getNguonHangs().add(v);
            });        
    }
    
    //FK: tham chiếu khóa chính cúa đối tượng khác đến nó (khoái khoại của chính nó)
    //PK: tham chiếu khóa chính từ nó đến các đối tượng khác(List các đối tượng tham chiếu khóa ngoại đến nó)
    public void lienKetGiaMatHang_FK(GiaMatHang t)
    {
        try{t.setChiTietLoaiHang(this.chiTietLoaiHangs.parallelStream().filter(lh->lh.getMaCTLH().equals(t.getMaCTLH())).findFirst().get());
            }catch (Exception ex){}  
    }
    public void lienKetHangHoa_FK(HangHoa t)
    {
         try{t.setLoaiHang(this.loaiHangs.parallelStream().filter(lh->lh.getMaLoaiHang().equals(t.getMaLoaiHang())).findFirst().get());   
            }catch (Exception ex){}
    }
    public void lienKetHangHoa_PK(HangHoa t)
    {   
        this.chiTietLoaiHangs.parallelStream().filter(g->g.getMaHang().equals(t.getMaHang())).forEach(v->{
            t.getChiTietLoaiHangs().add(v);
        });
    }
    public void lienKetHoaDon_FK(HoaDon t)
    {
        try{t.setKhachHang(this.khachHangs.parallelStream().filter(lh->lh.getMaKH().trim().equals(t.getMaKH())).findFirst().get());   
            }catch (Exception ex){}            
        try{t.setNhanVien(this.nhanViens.parallelStream().filter(lh->lh.getMaNV().equals(t.getMaNV())).findFirst().get());   
            }catch (Exception ex){}            
    }
    public void lienKetHoaDon_PK(HoaDon t)
    {
        this.chiTietHoaDons.parallelStream().filter(g->g.getSoHoaDon().equals(t.getSoHoaDon().trim())).forEach(v->{
            t.getChiTietHoaDons().add(v);
        });
    }
    public void lienKetKhachHang_PK(KhachHang t)
    {
         this.hoaDons.parallelStream().filter(g->g.getMaKH().equals(t.getMaKH())).forEach(v->{
                t.getHoaDons().add(v);
            });
    }
    public void lienKetLoaiHang_PK(LoaiHang t)
    {
         this.hangHoas.parallelStream().filter(g->g.getMaLoaiHang().equals(t.getMaLoaiHang())).forEach(v->{
                t.getHangHoas().add(v);
            });
    }
    public void lienKetNguonHang_FK(NguonHang t)
    {
        try{t.setNhaCungCap(this.nhaCungCaps.parallelStream().filter(lh->lh.getMaNCC().equals(t.getMaNCC())).findFirst().get());
            }catch (Exception ex){}              
        try{t.setChiTietLoaiHang(this.chiTietLoaiHangs.parallelStream().filter(lh->lh.getMaCTLH().equals(t.getMaCTLH())).findFirst().get());
            }catch (Exception ex){}     
    }
    public void lienKetNhaCungCap_PK(NhaCungCap t)
    {
        this.nguonHangs.parallelStream().filter(g->g.getMaNCC().equals(t.getMaNCC())).forEach(v->{
                t.getNguonHangs().add(v);
            });  
        this.phieuDatHangs.parallelStream().filter(g->g.getMaNCC().trim().equals(t.getMaNCC())).forEach(v->{
                t.getPhieuDatHangs().add(v);
            });  
    }
    public void lienKetNhanVien_PK(NhanVien t)
    {
        this.hoaDons.parallelStream().filter(g->g.getMaNV().equals(t.getMaNV())).forEach(v->{
                t.getHoaDons().add(v);
            });
        this.phieuDatHangs.parallelStream().filter(g->g.getMaNV().equals(t.getMaNV())).forEach(v->{
                t.getPhieuDatHangs().add(v);
            });  
    }
    public void lienKetPhieuDatHang_PK(PhieuDatHang t)
    {
        this.phieuGiaoHangs.parallelStream().filter(g->g.getSoDonDatHang().trim().equals(t.getSoDonDatHang().trim())).forEach(v->{
                t.getPhieuGiaoHangs().add(v);
            });            
        this.chiTietDatHangs.parallelStream().filter(g->g.getSoDonDatHang().trim().equals(t.getSoDonDatHang().trim())).forEach(v->{
                t.getChiTietDatHangs().add(v);
            });
    }  
    
    public void lienKetPhieuDatHangHang_FK(PhieuDatHang t)
    {
        try{t.setNhaCungCap(this.nhaCungCaps.parallelStream().filter(lh->lh.getMaNCC().trim().equals(t.getMaNCC())).findFirst().get());   
            }catch (Exception ex){}
    }
    
    public void lienKetPhieuGiaoHang_FK(PhieuGiaoHang t)
    {
        try{t.setPhieuDatHang(this.phieuDatHangs.parallelStream().filter(lh->lh.getSoDonDatHang().equals(t.getSoDonDatHang())).findFirst().get());   
            }catch (Exception ex){}
    }
    public void lienKetPhieuGiaoHang_PK(PhieuGiaoHang t)
    {
        this.chiTietGiaoHangs.parallelStream().filter(g->g.getSoPhieuGiao().equals(t.getSoPhieuGiao())).forEach(v->{
            t.getChiTietGiaoHangs().add(v);
        });
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

    public ArrayLists<ChiTietLoaiHang> getChiTietLoaiHangs() {
        return chiTietLoaiHangs;
    }

    public void setChiTietLoaiHangs(ArrayLists<ChiTietLoaiHang> chiTietLoaiHangs) {
        this.chiTietLoaiHangs = chiTietLoaiHangs;
    }

    public ArrayLists<GiaMatHang> getGiaMatHangs() {
        return giaMatHangs;
    }

    public void setGiaMatHangs(ArrayLists<GiaMatHang> giaMatHangs) {
        this.giaMatHangs = giaMatHangs;
    }

    public ArrayLists<HangHoa> getHangHoas() {
        return hangHoas;
    }

    public void setHangHoas(ArrayLists<HangHoa> hangHoas) {
        this.hangHoas = hangHoas;
    }

    public ArrayLists<HoaDon> getHoaDons() {
        return hoaDons;
    }

    public void setHoaDons(ArrayLists<HoaDon> hoaDons) {
        this.hoaDons = hoaDons;
    }

    public ArrayLists<KhachHang> getKhachHangs() {
        return khachHangs;
    }

    public void setKhachHangs(ArrayLists<KhachHang> khachHangs) {
        this.khachHangs = khachHangs;
    }

    public ArrayLists<LoaiHang> getLoaiHangs() {
        return loaiHangs;
    }

    public void setLoaiHangs(ArrayLists<LoaiHang> loaiHangs) {
        this.loaiHangs = loaiHangs;
    }

    public ArrayLists<NguonHang> getNguonHangs() {
        return nguonHangs;
    }

    public void setNguonHangs(ArrayLists<NguonHang> nguonHangs) {
        this.nguonHangs = nguonHangs;
    }

    public ArrayLists<NhaCungCap> getNhaCungCaps() {
        return nhaCungCaps;
    }

    public void setNhaCungCaps(ArrayLists<NhaCungCap> nhaCungCaps) {
        this.nhaCungCaps = nhaCungCaps;
    }

    public ArrayLists<NhanVien> getNhanViens() {
        return nhanViens;
    }

    public void setNhanViens(ArrayLists<NhanVien> nhanViens) {
        this.nhanViens = nhanViens;
    }

    public ArrayLists<PhieuDatHang> getPhieuDatHangs() {
        return phieuDatHangs;
    }

    public void setPhieuDatHangs(ArrayLists<PhieuDatHang> phieuDatHangs) {
        this.phieuDatHangs = phieuDatHangs;
    }

    public ArrayLists<PhieuGiaoHang> getPhieuGiaoHangs() {
        return phieuGiaoHangs;
    }

    public void setPhieuGiaoHangs(ArrayLists<PhieuGiaoHang> phieuGiaoHangs) {
        this.phieuGiaoHangs = phieuGiaoHangs;
    }                   
    
}
