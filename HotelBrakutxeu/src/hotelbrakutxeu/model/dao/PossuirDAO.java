package hotelbrakutxeu.model.dao;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.database.ConnectionFactory;
import hotelbrakutxeu.model.domain.Possuir;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class PossuirDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Possuir p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO possuir( "
                    + "idreserva, numeroquarto) "
                    + "VALUES (?, ?);");
            stmt.setInt(1, p.getIdreserva());
            stmt.setInt(2, p.getIdquarto());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao criar tabela Possuir: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Possuir p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE possuir SET "
                    + "numeroquarto=? WHERE idreserva=?;");
            
            stmt.setInt(1, p.getIdquarto());
            stmt.setInt(2, p.getIdreserva());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao atualizar tabela possuir: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Possuir p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE * FROM possuir WHERE idreserva = ?");
            stmt.setInt(1, p.getIdreserva());
            
            stmt.executeUpdate();
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao remover tabela possuir: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Possuir> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Possuir> possuir = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM possuir");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Possuir p = new Possuir();
                p.setIdreserva(rs.getInt("idreserva"));
                p.setIdquarto(rs.getInt("numeroquarto"));
                
                possuir.add(p);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return possuir;
    }
    
    public ArrayList<Possuir> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Possuir> possuir = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM possuir WHERE idreserva LIKE ?"
                    + "or SELECT * FROM possuir WHERE numeroquarto LIKE ?");
            stmt.setString(1, "%'+palavraChave+'%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Possuir p = new Possuir();
                p.setIdreserva(rs.getInt("idreserva"));
                p.setIdquarto(rs.getInt("numeroquarto"));
                
                possuir.add(p);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return possuir;
    }
    
}
