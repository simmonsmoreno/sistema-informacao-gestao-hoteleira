package hotelbrakutxeu.model.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pc
 */
public class ConnectionFactory {
    
    private static final String url = "jdbc:postgresql://localhost/brakutxeu";
    private static final String user = "postgres";
    private static final String password = "root";
 
    /**
     * Connect to the PostgreSQL database
     *
     * @return a ConnectionFactory object
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado a PostgreSQL server com successo.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
    
    public static void closeConnection(Connection conn){
        
        try {
            if(conn!=null) conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt){
        
        closeConnection(conn);
        
        try {
            if(stmt!=null) stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void closeConnection(Connection conn, PreparedStatement stmt, ResultSet rs){
        
        closeConnection(conn, stmt);
        
        try {
            if(rs!=null) rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

 