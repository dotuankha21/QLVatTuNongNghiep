/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Admin
 */
public class TaskDongBo <T extends GetFieldName> extends Thread {
    DBQuanLyVatTuNongNghiep db = null;
    private T objUpdate = null;
    private boolean xoa = false;
        
    public TaskDongBo(DBQuanLyVatTuNongNghiep db,T objUpdate, boolean xoa)
    {
        this.db = db;          
        this.xoa = xoa;
        this.objUpdate = objUpdate;
        this.setName("task");
    }    
    
    public TaskDongBo(DBQuanLyVatTuNongNghiep db,T objUpdate)
    {        
        this.db = db;   
        this.objUpdate = objUpdate;
        this.setName("task");
    }    
    
    @Override
    public void run() {
        super.run(); 
        synchronized(this)
        {
            if(objUpdate!=null)
            if(!xoa)
            {                     
                switch (objUpdate.getClassName()) {
                    case "ChiTietDatHang":
                        ChiTietDatHang dh = (ChiTietDatHang)objUpdate;
                        this.db.lienKetChiTietDatHang_FK(dh);
                        try{this.db.getPhieuDatHangs().parallelStream().filter(p->p.getSoDonDatHang().equalsIgnoreCase(dh.getSoDonDatHang())).findFirst().get().getChiTietDatHangs().add(dh);
                        }catch(Exception e){}
                        try{this.db.getChiTietLoaiHangs().parallelStream().filter(p->p.getMaCTLH().equalsIgnoreCase(dh.getMaCTLH())).findFirst().get().getChiTietDatHangs().add(dh);
                        }catch(Exception e){}
                        break;
                    case "ChiTietGiaoHang":
                        ChiTietGiaoHang gh = (ChiTietGiaoHang)objUpdate;
                        this.db.lienKetChiTietGiaoHang_FK(gh);
                        try{this.db.getPhieuGiaoHangs().parallelStream().filter(p->p.getSoPhieuGiao().equalsIgnoreCase(gh.getSoPhieuGiao())).findFirst().get().getChiTietGiaoHangs().add(gh);
                        }catch(Exception e){}                            
                        try{this.db.getChiTietLoaiHangs().parallelStream().filter(p->p.getMaCTLH().equalsIgnoreCase(gh.getMaCTLH())).findFirst().get().getChiTietGiaoHangs().add(gh);
                        }catch(Exception e){}
                        break;
                    case "ChiTietHoaDon":
                        ChiTietHoaDon cthd = (ChiTietHoaDon)objUpdate;
                        this.db.lienKetChiTietHoaDon_FK(cthd);
                        try{this.db.getHoaDons().parallelStream().filter(p->p.getSoHoaDon().equalsIgnoreCase(cthd.getSoHoaDon())).findFirst().get().getChiTietHoaDons().add(cthd);
                        }catch(Exception e){}
                        try{this.db.getChiTietLoaiHangs().parallelStream().filter(p->p.getMaCTLH().equalsIgnoreCase(cthd.getMaCTLH())).findFirst().get().getChiTietHoaDons().add(cthd);
                        }catch(Exception e){}
                        break;
                    case "ChiTietLoaiHang":
                        ChiTietLoaiHang ctlh = (ChiTietLoaiHang)objUpdate;
                        this.db.lienKetChiTietLoaiHang_FK(ctlh);
                        try{this.db.getHangHoas().parallelStream().filter(p->p.getMaHang().equalsIgnoreCase(ctlh.getMaHang())).findFirst().get().getChiTietLoaiHangs().add(ctlh);
                        }catch(Exception e){}
                        break;
                    case "GiaMatHang":
                        GiaMatHang gmh = (GiaMatHang)objUpdate;
                        this.db.lienKetGiaMatHang_FK(gmh);
                        try{this.db.getChiTietLoaiHangs().parallelStream().filter(p->p.getMaCTLH().equalsIgnoreCase(gmh.getMaCTLH())).findFirst().get().getGiaMatHangs().add(gmh);
                        }catch(Exception e){}
                        break;
                    case "HangHoa":
                        HangHoa hh = (HangHoa)objUpdate;
                        this.db.lienKetHangHoa_FK(hh);
                        try{this.db.getLoaiHangs().parallelStream().filter(p->p.getMaLoaiHang().equalsIgnoreCase(hh.getMaLoaiHang())).findFirst().get().getHangHoas().add(hh);
                        }catch(Exception e){}
                        break;
                    case "HoaDon":
                        HoaDon hd = (HoaDon)objUpdate;
                        this.db.lienKetHoaDon_FK(hd);
                        try{this.db.getNhanViens().parallelStream().filter(p->p.getMaNV().equalsIgnoreCase(hd.getMaNV())).findFirst().get().getHoaDons().add(hd);
                        }catch(Exception e){}
                        try{this.db.getKhachHangs().parallelStream().filter(p->p.getMaKH().equalsIgnoreCase(hd.getMaKH())).findFirst().get().getHoaDons().add(hd);
                        }catch(Exception e){}
                        break;
                    case "KhachHang":
                        break;
                    case "LoaiHang":
                        break;
                    case "NguonHang":
                        NguonHang nh = (NguonHang)objUpdate;
                        this.db.lienKetNguonHang_FK(nh);
                        try{this.db.getNhaCungCaps().parallelStream().filter(p->p.getMaNCC().equalsIgnoreCase(nh.getMaNCC())).findFirst().get().getNguonHangs().add(nh);
                        }catch(Exception e){}
                        try{this.db.getChiTietLoaiHangs().parallelStream().filter(p->p.getMaCTLH().equalsIgnoreCase(nh.getMaCTLH())).findFirst().get().getNguonHangs().add(nh);
                        }catch(Exception e){}                        
                        break;
                    case "NhaCungCap":
                        break;
                    case "NhanVien":                            
                        break;
                    case "PhieuDatHang": 
                        PhieuDatHang pdh = (PhieuDatHang)objUpdate;
                        this.db.lienKetPhieuDatHangHang_FK(pdh);
                        try{this.db.getNhanViens().parallelStream().filter(p->p.getMaNV().equalsIgnoreCase(pdh.getMaNV())).findFirst().get().getPhieuDatHangs().add(pdh);
                        }catch(Exception e){}
                        try{this.db.getNhaCungCaps().parallelStream().filter(p->p.getMaNCC().equalsIgnoreCase(pdh.getMaNCC())).findFirst().get().getPhieuDatHangs().add(pdh);
                        }catch(Exception e){}
                        break;
                    case "PhieuGiaoHang":
                        PhieuGiaoHang pgh = (PhieuGiaoHang)objUpdate;
                        this.db.lienKetPhieuGiaoHang_FK(pgh);
                        try{this.db.getPhieuDatHangs().parallelStream().filter(p->p.getSoDonDatHang().equalsIgnoreCase(pgh.getSoDonDatHang())).findFirst().get().getPhieuGiaoHangs().add(pgh);
                        }catch(Exception e){}
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            else
            {
                switch (objUpdate.getClassName()) {
                    case "ChiTietDatHang":
                        ChiTietDatHang dh = (ChiTietDatHang)objUpdate;
                        dh.getPhieuDatHang().getChiTietDatHangs().remove(dh);
                        dh.getChiTietLoaiHang().getChiTietDatHangs().remove(dh);                                
                        break;
                    case "ChiTietGiaoHang":
                        ChiTietGiaoHang gh = (ChiTietGiaoHang)objUpdate;
                        gh.getPhieuGiaoHang().getChiTietGiaoHangs().remove(gh);
                        gh.getChiTietLoaiHang().getChiTietGiaoHangs().remove(gh);                                
                        break;
                    case "ChiTietHoaDon":
                        ChiTietHoaDon cthd = (ChiTietHoaDon)objUpdate;
                        cthd.getHoaDon().getChiTietHoaDons().remove(cthd);
                        cthd.getChiTietLoaiHang().getChiTietHoaDons().remove(cthd);                                
                        break;
                    case "ChiTietLoaiHang":
                        ChiTietLoaiHang ctlh = (ChiTietLoaiHang)objUpdate;
                        ctlh.getHangHoa().getChiTietLoaiHangs().remove(ctlh);                                
                        break;
                    case "GiaMatHang":
                        GiaMatHang gmh = (GiaMatHang)objUpdate;
                        gmh.getChiTietLoaiHang().getGiaMatHangs().remove(gmh);                                
                        break;
                    case "HangHoa":
                        HangHoa hh = (HangHoa)objUpdate;
                        hh.getLoaiHang().getHangHoas().remove(hh);                                
                        break;
                    case "HoaDon":
                        HoaDon hd = (HoaDon)objUpdate;
                        hd.getNhanVien().getHoaDons().remove(hd);
                        hd.getKhachHang().getHoaDons().remove(hd);                                
                        break;
                    case "KhachHang":
                        break;
                    case "LoaiHang":
                        break;
                    case "NguonHang":
                        NguonHang nh = (NguonHang)objUpdate;
                        nh.getNhaCungCap().getNguonHangs().remove(nh);
                        nh.getChiTietLoaiHang().getNguonHangs().remove(nh);                                
                        break;
                    case "NhaCungCap":
                        break;
                    case "NhanVien":                            
                        break;
                    case "PhieuDatHang": 
                        PhieuDatHang pdh = (PhieuDatHang)objUpdate;
                        pdh.getNhanVien().getPhieuDatHangs().remove(pdh);
                        pdh.getNhaCungCap().getPhieuDatHangs().remove(pdh);                                
                        break;
                    case "PhieuGiaoHang":
                        PhieuGiaoHang pgh = (PhieuGiaoHang)objUpdate;
                        pgh.getPhieuDatHang().getPhieuGiaoHangs().remove(pgh);                                
                        break;
                    default:
                        throw new AssertionError();
                }
            }                    
        }
    }
}
