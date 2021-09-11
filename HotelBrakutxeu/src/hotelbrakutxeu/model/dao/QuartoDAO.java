package hotelbrakutxeu.model.dao;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.database.ConnectionFactory;
import hotelbrakutxeu.model.domain.Quarto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class QuartoDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Quarto q){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO quarto( "
                    + "numeroquarto, tipoquarto, status, preco) "
                    + "VALUES (?, ?, ?, ?);");
            stmt.setString(1, q.getNumeroquarto());
            stmt.setString(2, q.getTipoquarto());
            stmt.setBoolean(3, q.isStatus());
            stmt.setFloat(4, q.getPreco());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Adicionado", "Quarto registrado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao registrar Quarto: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Quarto q){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE quarto SET "
                    + "numeroquarto=?, tipoquarto=?, status=?, preco=? "
                    + "WHERE idquarto=?;");
            stmt.setString(1, q.getNumeroquarto());
            stmt.setString(2, q.getTipoquarto());
            stmt.setBoolean(3, q.isStatus());
            stmt.setFloat(4, q.getPreco());
            stmt.setInt(5, q.getIdquarto());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Atualizado", "Quarto atualizado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao atualizar quarto: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Quarto c){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE * FROM quarto WHERE idquarto = ?");
            stmt.setInt(1, c.getIdquarto());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Removido", "Quarto removido com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao remover quarto: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Quarto> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Quarto> quartos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM quarto");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Quarto q = new Quarto();
                q.setIdquarto(rs.getInt("idquarto"));
                q.setNumeroquarto(rs.getString("numeroquarto"));
                q.setTipoquarto(rs.getString("tipoquarto"));
                q.setStatus(rs.getBoolean("status"));
                q.setPreco(rs.getInt("preco"));
                
                quartos.add(q);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return quartos;
    }
    
    public ArrayList<Quarto> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Quarto> quartos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM quarto WHERE numeroquarto LIKE ? "
                    + "or SELECT * FROM quarto WHERE tipoquarto LIKE ?"
                    + "or SELECT * FROM quarto WHERE status LIKE ?"
                    + "or SELECT * FROM quarto WHERE preco LIKE ?");
            stmt.setString(1, "%'+palavraChave+'%");
            stmt.setString(2, "%'+palavraChave+'%");
            stmt.setString(3, "%'+palavraChave+'%");
            stmt.setString(4, "%'+palavraChave+'%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Quarto q = new Quarto();
                q.setIdquarto(rs.getInt("idquarto"));
                q.setNumeroquarto(rs.getString("numeroquarto"));
                q.setTipoquarto(rs.getString("tipoquarto"));
                q.setStatus(rs.getBoolean("status"));
                q.setPreco(rs.getInt("preco"));
                
                quartos.add(q);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return quartos;
    }
    
}
