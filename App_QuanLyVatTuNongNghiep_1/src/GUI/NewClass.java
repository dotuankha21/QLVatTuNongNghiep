///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
// */
//package GUI;
//
//import java.awt.Container;
//import java.util.HashMap;
//import javaswingdev.Notification;
//import javax.swing.JFrame;
//import javax.swing.SwingUtilities;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.swing.JRViewer;
//JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
//Notification panel = new Notification(frame, Notification.Type.INFO, Notification.Location.TOP_RIGHT, "Cần phải có ít nhất 1 chi tiết hóa đơn!!!");
//            panel.showNotification();
//
///**
// *
// * @author vanhu
// */
//public class NewClass extends JFrame{
//    public  NewClass()
//    {}
//    public NewClass(String name,HashMap p)       
//    {
//        try {
//            
//            JasperPrint rp=JasperFillManager.fillReport(name, p,Conect_Database.getInstance().Open());
//            JRViewer viewer=new JRViewer(rp);
//            Container c=getContentPane();
//            c.add(viewer);
//        } catch (Exception e) {
//        }
//        
//        setBounds(10,10,600,500);
//        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
//    }
//    
//}
