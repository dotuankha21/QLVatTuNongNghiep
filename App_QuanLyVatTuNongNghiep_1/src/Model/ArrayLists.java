/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import MeThods.KetNoi;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Spliterator;
import java.util.Vector;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.UnaryOperator;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

/**
 *
 * @author Admin
 */
public class ArrayLists<T extends GetFieldName> extends AbstractList<T> {

    private ArrayList<T> list = new ArrayList<T>();
    TaskDongBoLienKet task = null;    
    /**
     *
     * @param e
     * @return
     */
    
    public ArrayLists(TaskDongBoLienKet task)
    {            
        this.task  = task;
    }  
        
    public ArrayLists(TaskDongBoLienKet task,Type classOfT,String tblName)
    {
        this.task  = task;
        loadData(classOfT,tblName);
        if(list!=null)
            this.list.parallelStream().forEach(t->{
                t.setTask(task);
                t.updateKeyOld();
                    });
    }

    private ArrayLists() {        
    }
    
    private void loadData(Type classOfT, String tblName)
    {        
        Gson gson = new Gson();        
        String json = KetNoi.getInstance().SelectAllAsJson(tblName);
        this.list =  gson.fromJson(json, classOfT);
    }
    
    //Trả về số lượng phần tử insert thành công, arr.size() tức thành thành công tất cả
    public int insert(ArrayList<T> arr)
    {
        int size = arr.size();
        for (int i = 0; i < size; i++) {
            if(!insert(arr.get(i)))
                return i;
        }
        return size;
    }
    
    public boolean insert(T e) {                  
        Vector<String> fnames = e.GetFields();
        String sql = "insert into "+e.getClassName()+"("+fnames.get(0);
        String values = "?";
        for (int i = 1;i<fnames.size();i++) 
        {
            sql += ","+fnames.get(i);        
            values+=",?";
        }
        sql += ") values("+values+")";        
        if(KetNoi.getInstance().CapNhatDuLieu(sql, e.getParameter())>0)
        {   
            e.setTask(this.task);                       
            this.add(e); 
            this.task.newRunTask(this, e);             
            return true;
        }                         
        return false;
    }
    
    public boolean update(T e)
    {            
        if(task.objMain!=null)                               
            return false;                
        try {this.list.parallelStream().filter(t->t==e).findFirst().get();} catch(Exception ex) {return false;}
        Vector<String> fnames = e.GetFields();
        Vector<Object> params = e.getParameter();
        Vector<Object> keys = e.getKey();
        Vector<Object> ps = new Vector<Object>();
        String sql = "update "+e.getClassName()+" set ";        
        String wher = "where "; 
        System.out.println(sql);
        int size = fnames.size();
        for (int i = 0;i<size;i++)
        {
            sql += fnames.get(i)+" = ? ";
            ps.add(params.get(i));
            if(i<size-1)            
                sql+=", ";
            if(keys.indexOf(params.get(i))!=-1)
            {      
                wher += fnames.get(i)+" = ?";   
                wher+=" and ";
            }                        
        }        
        ps.addAll(e.getKeyOld());
        sql += wher +" 1 = 1";           
        if(KetNoi.getInstance().CapNhatDuLieu(sql,ps)>0)
        {
            e.updateKeyOld();
            return true;
        }
        return false;
    }
    
    
    public boolean delete(T e)
    {   
        if(task.objMain!=null)
            return false;
        T valDelete;
        try {valDelete = this.list.parallelStream().filter(t->t==e).findFirst().get();}catch(Exception ex){return false;}
        Vector<String> fnames = e.GetFields();
        Vector<Object> params = e.getParameter();
        Vector<Object> pskey = e.getKey();        
        String sql = "delete "+e.getClassName()+" where ";        
        int size = fnames.size();
        for (int i = 0;i<size;i++) {
            if(pskey.indexOf(params.get(i))!=-1)
               sql+= fnames.get(i)+" = ? and ";
        }                     
        sql +="1=1";           
        if(KetNoi.getInstance().CapNhatDuLieu(sql,pskey)>0)
        {
            this.list.remove(valDelete);            
            this.task.newRunTask(this, valDelete, true);
            return true;
        }                        
       return false;
    }
    private void waitTask()
    {
        if(task.arr!=null || task.objMain!=null)
            synchronized(this)
            {
                try {
                    task.arr = this;                 
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ArrayLists.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
    }
    
    
    public  Vector includes(String tblName)
    {
        Vector data = new Vector();
        if(!tblName.endsWith("s"))        
            for (T t : this.list) 
                data.add(t.include(tblName));                    
        else        
            for (T t : this.list) 
                for (Object item : t.include(tblName)) {
                    Vector v = (Vector) item;
                    data.add(v);
                }
        return data;
    }
    
    public Vector includes(String[] tblNames,String[] remove_fieldNames)
            
    {
        Vector data = new Vector();
        for (T t : this.list) 
            for (Object item : t.include(tblNames, remove_fieldNames)) {
                Vector v = (Vector) item;
                data.add(v);
            }
        return data;
    }       
    
    public Vector includesAs(String[] tblNames,String[] fieldNames)
            
    {
        Vector data = new Vector();
        for (T t : this.list) 
            for (Object item : t.includeAs(tblNames, fieldNames)) {
                Vector v = (Vector) item;
                data.add(v);
            }
        return data;
    }
    
private Vector<String> copy(Vector<String> fieldNames)
{
    int size = fieldNames.size();
    String[] tam = new String[size]; 
      Vector<String> moi = new Vector<String>();
    for (int i = 0;i<size;i++) {
        tam[i] = fieldNames.get(i);
        moi.add(tam[i]);
    }   
//    String[] tam2 = Arrays.copyOfRange(tam, 0, size);      
//    for (String t : tam2) {
//        moi.add(t);
//    }
    return moi;
}
    
    public Vector includesAs(Vector<String> tblNames, Vector<String> fieldNames)
            
    {
        Vector data = new Vector();                        
        for (T t : this.list) 
        {            
            Vector tam = copy(fieldNames);
            for (Object item : t.includeAs(tblNames, tam)) {                
                Vector v = (Vector) item;                
                data.add(v);
            }                        
        }
        return data;
    }
            
    public  Vector includes(String tblName,String[] remove_fieldNames)
    {
        Vector data = new Vector();
        if(!tblName.endsWith("s"))        
            for (T t : this.list) 
                data.add(t.include(tblName,remove_fieldNames));                    
        else        
            for (T t : this.list) 
                for (Object item : t.include(tblName,remove_fieldNames)) {
                    Vector v = (Vector) item;
                    data.add(v);
                }
        return data;
    }
    
    
    public Vector getData()
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector l = new Vector();                 
        this.parallelStream().forEach(t->l.add(t.getParameter()));                    
        return l;
    }
    
    public Vector getData(int idxRemove)
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector l = new Vector();                 
        this.parallelStream().forEach(t->l.add(t.getParameter(idxRemove)));                    
        return l;
    }
    
    public Vector getData(int[] idxRemoves)
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector l = new Vector();                 
        this.parallelStream().forEach(t->l.add(t.getParameter(idxRemoves)));                    
        return l;
    }
    
//    public Vector getData(Predicate<? super Object> prdct)
//    {        
//        //Đợi luồng cập nhật liên kết hoàn tất
//        waitTask();
//        Vector l = new Vector();                 
//        this.parallelStream().forEach(t->l.add(t.getParameter(prdct)));                    
//        return l;
//    }

    public Vector getData(String remove_fieldName)
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector l = new Vector();                 
        this.parallelStream().forEach(t->l.add(t.getParameter(remove_fieldName)));                    
        return l;
    }   
    
    public Vector getData(Predicate<? super T> prdct)
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector l = new Vector();                 
        this.list.parallelStream().filter(prdct).forEach(t->l.add(t.getParameter()));                    
        return l;
    }
    
    public Vector getData(Predicate<? super T> prdct, String[] tblNames, String[] fieldNames)
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector data = new Vector();                        
        this.list.stream().filter(prdct).forEach(t->{
            for (Object obj : t.includeAs(tblNames, fieldNames)) {
                Vector v = (Vector)obj;
                data.add(v);
            }
        });        
        return data;
    }        
    
    public Vector getData(Predicate<? super T> prdct, Vector<String> tblNames, Vector<String> fieldNames)
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector data = new Vector();                
        this.list.stream().filter(prdct).forEach(t->{  
            Vector tam = copy(fieldNames);
            for (Object item : t.includeAs(tblNames, tam)) {                
                Vector v = (Vector) item;                
                data.add(v);
            }
        });        
        return data;
    } 
            
    public Vector getData(String[] remove_fieldNames)
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector l = new Vector();                 
        this.parallelStream().forEach(t->l.add(t.getParameter(remove_fieldNames)));                    
        return l;
    }  
    
    public Vector getDataAs(String[] fieldNames)
    {        
        //Đợi luồng cập nhật liên kết hoàn tất
        waitTask();
        Vector l = new Vector();                 
        this.parallelStream().forEach(t->l.add(t.getParameterAs(fieldNames)));                    
        return l;
    }   
    
    public boolean add(T e)
    {        
        return list.add(e);
    }

    public void Add(int i,T e)
    {
        list.add(i, e);
    }
    public void clear()
    {
        list.clear();
    }
    public boolean contains(T e)
    {
        return list.contains(e);
    }
    public boolean remove(T e)
    {
        return list.remove(e);
    }
    public int indexOf(T e)
    {
        return list.indexOf(e);
    }
    public T set(int i,T e)
    {
        return list.set(i,e);
    }
    public T[] toArray()
    {
        return (T[]) list.toArray();
    }
    public String toString()
    {
        return list.toString();
    }
    @Override
    public T get(int i) {        
        return list.get(i);
    }     
    public T get(Predicate<? super T> dieuKienLoc) {           
        try
        {
            return this.list.parallelStream().filter(dieuKienLoc).findFirst().get();
        }catch(Exception e)
        {
            return null;
        }        
    }
    public T max(Predicate<? super T> dieuKienLoc, ToIntFunction<? super T> funCotXetMax_int) {           
        try
        {
            return this.list.parallelStream().filter(dieuKienLoc).max(Comparator.comparingInt(funCotXetMax_int)).get();
        }catch(Exception e)
        {
            return null;
        }        
    }
    
    public T max(ToIntFunction<? super T> funCotXetMax_int) {           
        try
        {
            return this.list.parallelStream().max(Comparator.comparingInt(funCotXetMax_int)).get();
        }catch(Exception e)
        {
            return null;
        }        
    }
    
    public T min(Predicate<? super T> dieuKienLoc, ToIntFunction<? super T> funCotXetMin_int) {           
        try
        {
            return this.list.parallelStream().filter(dieuKienLoc).min(Comparator.comparingInt(funCotXetMin_int)).get();
        }catch(Exception e)
        {
            return null;
        }        
    }
    
    public T min(ToIntFunction<? super T> funCotXetMin_int) {           
        try
        {
            return this.list.parallelStream().min(Comparator.comparingInt(funCotXetMin_int)).get();
        }catch(Exception e)
        {
            return null;
        }        
    }
    
    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean removeIf(Predicate<? super T> prdct) {
        return this.list.removeIf(prdct); 
    }

    @Override
    public Stream<T> stream() {
        return this.list.stream(); 
    }

    @Override
    public Stream<T> parallelStream() {
        return this.list.parallelStream(); 
    }

    @Override
    public Spliterator<T> spliterator() {
        return this.list.spliterator();
    }

    @Override
    public void replaceAll(UnaryOperator<T> uo) {
        this.list.replaceAll(uo); 
    }

    @Override
    public void sort(Comparator<? super T> cmprtr) {
        this.list.sort(cmprtr); 
    }    
    
    @Override
    public void forEach(Consumer<? super T> cnsmr) {
        this.list.forEach(cnsmr);
    }
    
}
