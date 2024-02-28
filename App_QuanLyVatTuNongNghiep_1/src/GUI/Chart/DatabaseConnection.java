package GUI.Chart;

import MeThods.KetNoi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static DatabaseConnection instance;
    

    public static DatabaseConnection getInstance() {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }

    private DatabaseConnection() {

    }
    private Connection conn;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet rs = null;
    private String NameSever="localhost";
    private String NameDatabase="QLCuaHangVatTuNongNghiep";
    private String UserName="sa";
    private String PassWord="123";
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
    public Connection getConnection() {
        return conn;
    }

    public void setConnection(Connection connection) {
        this.conn = connection;
    }
}
