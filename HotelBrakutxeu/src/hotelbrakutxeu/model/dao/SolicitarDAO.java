package hotelbrakutxeu.model.dao;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.database.ConnectionFactory;
import hotelbrakutxeu.model.domain.Solicitar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class SolicitarDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Solicitar s){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO solicitar( "
                    + "idcliente, idreserva, datasolicitar) "
                    + "VALUES (?, ?, ?);");
            stmt.setInt(1, s.getIdcliente());
            stmt.setInt(2, s.getIdreserva());
            stmt.setString(3, s.getDatasolicitar());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Adicionado", "Tabela solicitar criado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao criar tabela solicitar: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Solicitar s){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE solicitar SET "
                    + "idreserva=?, datasolicitar=?, WHERE idcliente=?;");
            
            stmt.setInt(1, s.getIdreserva());
            stmt.setString(2, s.getDatasolicitar());
            stmt.setInt(3, s.getIdcliente());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Atualizado", "Tabela solicitar atualizado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao atualizar tabela solicitar: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Solicitar p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE * FROM solicitar WHERE idcliente = ?");
            stmt.setInt(1, p.getIdcliente());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Removido", "Tabela solicitar removido com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao remover tabela solicitar: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Solicitar> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Solicitar> solicitar = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM solicitar");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Solicitar s = new Solicitar();
                s.setIdcliente(rs.getInt("idcliente"));
                s.setIdreserva(rs.getInt("idreserva"));
                s.setDatasolicitar(rs.getString("datasolicitar"));
                
                solicitar.add(s);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return solicitar;
    }
    
    public ArrayList<Solicitar> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Solicitar> solicitar = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM solicitar WHERE idcliente LIKE ?"
                    + "or SELECT * FROM solicitar WHERE datasolicitar LIKE ?");
            stmt.setString(1, "%'+palavraChave+'%");
            stmt.setString(2, "%'+palavraChave+'%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Solicitar s = new Solicitar();
                s.setIdcliente(rs.getInt("idcliente"));
                s.setIdreserva(rs.getInt("idreserva"));
                s.setDatasolicitar(rs.getString("datasolicitar"));
                
                solicitar.add(s);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return solicitar;
    }
    
}
