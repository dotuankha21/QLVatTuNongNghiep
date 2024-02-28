/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.sql.Date;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Vector;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Admin
 */
public abstract class GetFieldName {
    private HashMap dict = null;
    protected TaskDongBoLienKet task = null;
    protected Vector<Object> KeyOld = null;
    public static String PACKAGE_NAME = "Model";
    
    
    public Vector<Object> getKeyOld()
    {
        return this.KeyOld;
    }
    
    public void updateKeyOld()
    {
        this.KeyOld = this.getKey();
    }
    
    public void setTask(TaskDongBoLienKet task) {
        this.task = task;
        khoiTaoLienKet();
    }    
    public static Vector<String> GetFields(Field[] fields)
    {                    
        Vector<String> Fieldnames = new Vector<String>();
        for(Field field : fields)
        {
            String name = field.getType().getName();
            if(!name.contains(PACKAGE_NAME))
            {
                Fieldnames.add(field.getName());
            }
        }
        return Fieldnames;       
    }
    
    public String getClassName()
    {
        
        String[] strName = this.getClass().getName().split("\\Q.\\E");
        ArrayList<String> nameCls = new ArrayList<String>(); 
        nameCls.addAll(Arrays.asList(strName));                 
        return nameCls.get(nameCls.size()-1);
    }
    
    public static String getClassName(Class cls)
    {
        
        String[] strName = cls.getName().split("\\Q.\\E");
        ArrayList<String> nameCls = new ArrayList<String>(); 
        nameCls.addAll(Arrays.asList(strName));                 
        return nameCls.get(nameCls.size()-1);
    }

    public GetFieldName(HashMap dict,TaskDongBoLienKet task) {
        this.dict = dict;
        this.task = task;
    }

    public GetFieldName() {
    }    
    
    public abstract Vector<Object> getParameter();            
    public abstract Vector<Object> getKey();
    public abstract void setValue(ArrayList<Object> parameter);   
    public void khoiTaoLienKet(){};
    //public abstract void setValue(HashMap hm);
    
    public Vector<Object> getParameter(int idxRemove)
    {
        Vector<Object> params = this.getParameter();
        params.remove(idxRemove);
        return params;
    }
    
    public Vector<Object> getParameter(int[] idxRemoves)
    {        
        Vector<Object> params = this.getParameter();
        if(idxRemoves.length> params.size())
            return null;
        for (int idx : idxRemoves)
            params.remove(idx);        
        return params;
    }
    
    public Vector<Object> getParameter(Predicate<? super Object> prdct)
    {        
        Vector<Object> params = this.getParameter();
        params.removeIf(prdct);               
        return params;
    }
    public Vector<Object> getParameter(String remove_fieldName)
    {        
        Vector<Object> params = this.getParameter();
        int idx = this.GetFields().indexOf(remove_fieldName);
            if(idx!=-1)
                params.remove(idx);
        return params;
    }

    public Vector<Object> getParameter(String[] remove_fieldNames)
    {        
        Vector<Object> params = this.getParameter();
        Vector<Object> del = new Vector<Object>();        
        for (String fieldName : remove_fieldNames) {            
            int idx = this.GetFields().indexOf(fieldName);
            if(idx!=-1)            
                del.add(params.get(idx));                        
        } 
        params.removeAll(del);
        return params;
    }
    
    public Vector<Object> getParameterAs(String[] fieldNames)
    {        
        Vector<Object> params = this.getParameter();
        Vector<Object> lay = new Vector<Object>();        
        for (String fieldName : fieldNames) {            
            int idx = this.GetFields().indexOf(fieldName);
            if(idx!=-1) 
            {
                fieldName = null;
                lay.add(params.get(idx));                        
            }
        } 
        return lay;
    }
    
     public Vector<Object> getParameterAs(Vector<String> fieldNames)
    {        
        Vector<Object> params = this.getParameter();
        Vector<Object> lay = new Vector<Object>(); 
        Vector<String> xoafn = new Vector<String>();        
        for (String fieldName : fieldNames) {            
            int idx = this.GetFields().indexOf(fieldName);
            if(idx!=-1) 
            {
                xoafn.add(fieldName);
                lay.add(params.get(idx));                   
            }
        } 
        fieldNames.removeAll(xoafn);
        return lay;
    }
     
     
    
    private boolean checkFieldName(String tblName)
    {
        int idx = getNameFieldsALL().indexOf(tblName.trim());
        if(idx == -1 || !getFieldsALL().get(idx).getType().getName().contains(PACKAGE_NAME))
            return false;
        return true;
    }
    public Vector include(String tblName)
    {                
        if(!checkFieldName(tblName))
            return null;     
        waitTask();
        HashMap<String,Vector<Object>> dataNodes = getDataNodes();
        Vector<Object> params = getParameter();
//        if(dataNodes.containsKey((String)"chiTietHoaDons"))
//            return null;  
        Vector<Object> datavalues = dataNodes.get(tblName.trim());        
        return incluData(tblName, params, datavalues);
    }
    
    public Vector include(String tblName, String[] remove_fieldNames)
    {                
        if(!checkFieldName(tblName))
            return null;
        waitTask();
        HashMap<String,Vector<Object>> dataNodes = getDataNodes(remove_fieldNames);
        Vector<Object> params = getParameter(remove_fieldNames);
//        if(dataNodes.containsKey((String)"chiTietHoaDons"))
//            return null;  
        Vector<Object> datavalues = dataNodes.get(tblName.trim());        
        return incluData(tblName, params, datavalues);
    } 
    
    private  Vector incluData(String tblName,Vector<Object> params,Vector<Object> datavalues)
    {
        if(!tblName.endsWith("s"))       
        {
           
            datavalues.forEach(t->params.add(t));
            return params;
        }
        else
        {
            Vector data = new Vector();
            for(Object v : datavalues)
            {
                Vector item = (Vector)v;
                Vector row = new Vector();
                params.forEach(t->row.add(t)); 
                item.forEach(t->
                {
                    if(this.getKey().indexOf(t)==-1)
                        row.add(t);
                        });                               
                data.add(row);                
            }            
            return data;
        }
    }
    
    public Vector include(String[] tblNames, String[] remove_fieldNames)
    {           
        Vector data = new Vector();
        data.add(getParameter());
        for (String tblName : tblNames) {
            if(!checkFieldName(tblName))
               return data;
            waitTask();
           HashMap<String,Vector<Object>> dataNodes = getDataNodes(remove_fieldNames);
           Vector<Object> datavalues = dataNodes.get(tblName.trim()); 
            if(!tblName.endsWith("s"))               
                data = incluDataSingle(data, datavalues); 
            else
                incluDatas(data, datavalues);
        } 
        return data;
    }
    
    public Vector includeAs(String[] tblNames, String[] fieldNames)
    {           
        Vector data = new Vector();
        data.add(getParameter(fieldNames));                
        for (String tblName : tblNames) {
            if(!checkFieldName(tblName))
               continue;
            waitTask();
           HashMap<String,Vector<Object>> dataNodes = getDataNodesAs(fieldNames);            
           Vector<Object> datavalues = dataNodes.get(tblName.trim());             
            if(!tblName.endsWith("s"))               
                data = incluDataSingle(data, datavalues); 
            else
                incluDatas(data, datavalues);
        } 
        return data;
    } 
    
    private String checkTblName(Vector<String> tblNames)
    {
        for (String tblName : tblNames)
            if(tblName.equalsIgnoreCase(this.getClassName()))
                return tblName;
        return null;
    }
    
private void waitTask()
    {       
        if(task.objMain !=null|| task.arr!=null)
        {                       
            synchronized(this)
            {
                try {
                    task.objMain = this;
                    wait();
                } catch (InterruptedException ex) {
                    Logger.getLogger(ArrayLists.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    public synchronized Vector includeAs(Vector<String> tblNames, Vector<String> fieldNames)
    {                
        
        Vector data = new Vector();
        Vector tam = new Vector();
        String name = null;
        if((name = checkTblName(tblNames))==null)
            return data;        
        tblNames.remove(name);
        int size = fieldNames.size();        
        data.add(getParameterAs(fieldNames));    
        ArrayList<String> FiledNameALL = this.getNameFieldsALL();
        if(size == fieldNames.size())
            return data;
        waitTask();
        HashMap<String,Vector<Object>> dataNodes = getDataNodesAs(tblNames,fieldNames);
        tblNames.add(name);
        for (String tblName : tblNames) {
            if(FiledNameALL.indexOf(tblName)!=-1)
            {
               Vector<Object> datavalues = dataNodes.get(tblName.trim());                       
               data = incluDatas(data, datavalues); 
            }
        }  
        return data;
    } 
    
    private Vector incluDataSingle(Vector<Object> data,Vector<Object> datavalues)
    {
        Vector newData = new Vector();
        for (Object obj : data) {
            Vector v = (Vector)obj;
            datavalues.forEach(t->v.add(t));
            newData.add(v);
        }                            
        return newData;
    }
     
    private Vector incluDatas(Vector<Object> data,Vector<Object> datavalues)
    {
        Vector newData = new Vector();
        for (Object obj : data) {
            Vector row = (Vector)obj;
            for(Object vt : datavalues)
            {
                Vector item = (Vector)vt;
                Vector v = new Vector();
                row.forEach(t->v.add(t)); 
                item.forEach(t->
                {
                    if(this.getKey().indexOf(t)==-1)
                        v.add(t);
                        });                               
                newData.add(v);                
            }
        }        
        return newData;
    }
          
    
    public ArrayList<Field> getFieldsALL()
    {
       ArrayList<Field> fields = new ArrayList<Field>();
       fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));
       return fields;
    }
    
    public ArrayList<String> getNameFieldsALL()
    {
       ArrayList<Field> fields = new ArrayList<Field>();
       fields.addAll(Arrays.asList(this.getClass().getDeclaredFields()));
       ArrayList<String> nameColumns = new ArrayList<String>();
       fields.forEach(t->nameColumns.add(t.getName()));
       return nameColumns;
    }
    public Vector<String> GetFields()
    {              
        return GetFieldName.GetFields(this.getClass().getDeclaredFields());
    }
//    public HashMap<String,Vector<Object>> getDataNodeSingle()
//    {
//        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();       
//        return data;
//    }
    public HashMap<String,Vector<Object>> getDataNodes()
    {
        HashMap<String,Vector<Object>> data = new HashMap<String,Vector<Object>>();
        return data;
    }
    public HashMap<String,Vector<Object>> getDataNodes(String[] remove_fieldNames)
    {
        return null;
    }
    public abstract HashMap<String,Vector<Object>> getDataNodesAs(String[] fieldNames);
    
    public abstract HashMap<String,Vector<Object>> getDataNodesAs(Vector<String> tblNames, Vector<String> fieldNames);
    
    public HashMap<String,Vector<Object>> getDataNodeAllAs(Vector<String> tblNames, Vector<String> fieldNames)
    {
        return getDataNodesAs(tblNames,fieldNames);
    }
    public String[] chuyenDoiMang( Vector<String> fieldNames)
    {
        String[] tn = new String[fieldNames.size()];
        for (int i = 0; i <fieldNames.size();i++) {
            tn[i] = fieldNames.get(i);
        }
        return tn;
    }
}
