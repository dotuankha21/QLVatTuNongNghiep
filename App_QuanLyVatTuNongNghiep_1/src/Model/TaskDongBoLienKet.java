/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public class TaskDongBoLienKet<T extends GetFieldName> extends Thread{
    private DBQuanLyVatTuNongNghiep db = null;
    T objMain = null;
    ArrayLists<T> arr = null;
   
    private int getTaskActiveCount()
    {
        ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
        int threadCount = threadGroup.activeCount();
        Thread threadList[] = new Thread[threadCount];        
        threadGroup.enumerate(threadList);   
        int dem = 0;        
        for (int i = 0; i < threadCount; i++)
            try
            {
                if(threadList[i].getName().equals("task"))
                {                
                    dem++;

                }
            } catch(Exception e){}
        return dem;
    }     
    
    public TaskDongBoLienKet(DBQuanLyVatTuNongNghiep db)
    {
        this.db = db;     
        this.objMain = null;
        this.setName("taskMain");
    } 

    public void newRunTask(T objMain, T objUpdate, boolean xoa)
    {
          this.objMain = objMain;          
//        else if(this.objMain != objMain)
//                synchronized(this) {
//                  this.Wait();
//        }

        new TaskDongBo(this.db,objUpdate,xoa).start();
        synchronized (this) {
            this.notifyAll();
        }
    }
    
    public void newRunTask(T objMain, T objUpdate)
    {
          this.objMain = objMain;
//        else if(this.objMain != objMain)
//                synchronized(this) {
//                  this.Wait();
//        }
        new TaskDongBo(this.db,objUpdate).start();
        synchronized (this) {
            this.notifyAll();
        }     
    }
    
        public void newRunTask(ArrayLists<T> arr, T objUpdate, boolean xoa)
    {
          this.arr = arr; 
//        else if(this.objMain != objMain)
//                synchronized(this) {
//                  this.Wait();
//        }

        new TaskDongBo(this.db,objUpdate,xoa).start();
        synchronized (this) {
            this.notifyAll();
        }
    }
    
    public void newRunTask(ArrayLists<T> arr, T objUpdate)
    {
          this.arr = arr;
//        else if(this.objMain != objMain)
//                synchronized(this) {
//                  this.Wait();
//        }
        new TaskDongBo(this.db,objUpdate).start();
        synchronized (this) {
            this.notifyAll();
        }     
    }
    
    @Override
    public void run() {
        super.run();     
        while(true)
        {       
            synchronized(this)                      
            {
                this.Wait();
                while(this.objMain !=null || this.arr!=null)
                {
                    if( this.getTaskActiveCount() == 0) 
                    {            
                        if(this.objMain!=null)
                            synchronized(this.objMain) {                                                
                                objMain.notifyAll();
                                objMain = null;                            
                        } 
                        if(this.arr!=null)
                            synchronized (this.arr) {
                            arr.notifyAll();
                            arr = null;
                        }
                    }                                                
                }
            }
        }
    }
    private void Wait()
    {
        try {
            wait();
        } catch (InterruptedException ex) {
            Logger.getLogger(TaskDongBoLienKet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
