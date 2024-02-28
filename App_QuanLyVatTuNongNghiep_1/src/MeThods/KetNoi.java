
package MeThods;
//
/**
 *
 * @author OP15
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author OP15
 */
public final class KetNoi {
    private static KetNoi instance;

    public static KetNoi getInstance() {
        if (instance == null) instance = new KetNoi(); return KetNoi.instance;
    }
    private Connection conn;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    private String NameSever="localhost";
    private String NameDatabase="QLCuaHangVatTuNongNghiep";
    private String UserName="sa";
    private String PassWord="123";
      
    public int CapNhatDuLieu(String strSql)
    {
        int n = -1;
        try {
            preparedStatement = conn.prepareStatement(strSql); 
            n = preparedStatement.executeUpdate(strSql);
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
        return n;
    }
    public int CapNhatDuLieu(String strSql,Vector<Object> parameter)
    {
        int n = -1;
        try {
            preparedStatement = conn.prepareStatement(strSql);
            int i = 1;
            for (Object pra : parameter) {             
                preparedStatement.setObject(i,pra);
                i++;
            }
            n = preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);   
        }
        return n;
    }
    public Vector TruyVan(String strSql, int soCot)
    {
        Vector data = new Vector();
        try {
            preparedStatement = conn.prepareStatement(strSql);
            rs = preparedStatement.executeQuery();
            data = DocDuLieu(rs, soCot);
        } catch (SQLException ex) {              
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return data;        
    }

    /**
     *
     * @param strSql
     * @param prameter
     * @return
     */
    public Vector TruyVan(String strSql, Object[] prameter, int soCot)
    {
        Vector data = new Vector();
        try {
            preparedStatement = conn.prepareStatement(strSql);
            int i = 1;
            for (Object pra : prameter) {
                preparedStatement.setObject(i,pra);
                i++;
            }
            rs = preparedStatement.executeQuery();                   
           data = DocDuLieu(rs, soCot);
        } catch (SQLException ex) {              
            System.out.println(ex.getMessage());
            return null;
        }
        return data;
        
    }
    public Vector TruyVan(String strSql, Object[] prameter)
    {
        Vector data = new Vector();
        try {
            preparedStatement = conn.prepareStatement(strSql);
            int i = 1;
            for (Object pra : prameter) {
                preparedStatement.setObject(i,pra);
                i++;
            }
            rs = preparedStatement.executeQuery();  
            ResultSetMetaData rsdata = rs.getMetaData();  
           int soCot = rsdata.getColumnCount();   
           data = DocDuLieu(rs, soCot);
        } catch (SQLException ex) {              
            System.out.println(ex.getMessage());
            return null;
        }
        return data;        
    }
    Vector DocDuLieu(ResultSet rs, int soCot) throws SQLException
    {
        Vector data = new Vector();
        boolean chk = false;
        while(rs.next())
           {        
               chk = true;
               Vector vt = new Vector();
               for(int i = 1; i <=soCot; i++)
                    vt.add(rs.getObject(i));                
                data.add(vt);                
           }
        return chk ? data : null;
    }
    public Vector TruyVan(String sql)
    {
        Vector data = new Vector();
        try {
           statement = conn.createStatement();
           rs = statement.executeQuery(sql);
           ResultSetMetaData rsdata = rs.getMetaData();  
           int soCot = rsdata.getColumnCount();          
           data = DocDuLieu(rs, soCot);
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        return null;       
    }
    public ArrayList<HashMap> SelectALL(String TenBang)
    {
        String sql = "select * from "+TenBang;
        return TruyVans(sql);
    }
    public ArrayList<HashMap> TruyVans(String sql)
    {        
        ArrayList<HashMap> data = new ArrayList<HashMap>();
        boolean chk = false;
        try {
           statement = conn.createStatement();
           rs = statement.executeQuery(sql);           
           ResultSetMetaData rsMeta = rs.getMetaData();  
           int soCot = rsMeta.getColumnCount();                    
            while(rs.next())
            {       
                   chk = true;
                   HashMap hm = new HashMap();
                   for(int i = 1; i <=soCot; i++)                   
                       hm.put(rsMeta.getColumnName(i), rs.getObject(i));                   
                    data.add(hm);                                            
           } 
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }                       
        return chk ? data : null;  
    }
    
    public String SelectAllAsJson(String TenBang)
    {
        String sql = "select * from "+TenBang;
        return TruyVanAsJson(sql);
    }
    public String TruyVanAsJson(String sql)
    {        
        String json = "[";
        boolean chk = false;
        try {
           statement = conn.createStatement();
           rs = statement.executeQuery(sql);           
           ResultSetMetaData rsMeta = rs.getMetaData();  
           int soCot = rsMeta.getColumnCount();                    
            while(rs.next())
            {                       
                chk = true;
                json+="{";
                for(int i = 1; i <=soCot; i++)                   
                {
                    json+="\""+rsMeta.getColumnName(i)+"\":\""+rs.getObject(i).toString().trim()+"\"";                   
                    if(i<soCot)
                        json+=",";
                }
                 json+="},";                       
           } 
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        json = json.substring(0, json.length()-1);
        json +="]";      
        return chk ? json : null;  
    }
    
    public KetNoi()
    {
        try {
            Open();
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void Open() throws SQLException
    {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connect_URL="jdbc:sqlserver://"+NameSever+":1433;databaseName="+NameDatabase+";";
            conn=conn=DriverManager.getConnection(connect_URL,UserName,PassWord);
            if(conn!=null)
            {
                System.out.println("Ket noi thanh cong");
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void Close()
    {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(KetNoi.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}

