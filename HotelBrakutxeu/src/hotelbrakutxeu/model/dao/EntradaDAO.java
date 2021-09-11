package hotelbrakutxeu.model.dao;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.database.ConnectionFactory;
import hotelbrakutxeu.model.domain.Cliente;
import hotelbrakutxeu.model.domain.Entrada;
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
public class EntradaDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Entrada e, Cliente c, Quarto q){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO entrada( "
                    + "icliente, idquarto, dataentrada, datasaida) VALUES (?,?,?,?);");
            stmt.setInt(1, c.getIdcliente());
            stmt.setInt(2, q.getIdquarto());
            stmt.setString(3, e.getDataentrada());
            stmt.setString(4, e.getDatasaida());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Adicionado", "Tabela entrada criado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao criar tabela entrada: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Entrada e){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE entrada SET "
                    + "dataentrada=?, datasaida=?, WHERE idcliente=?;");
            stmt.setInt(1, e.getIdcliente());
            stmt.setString(3, e.getDataentrada());
            stmt.setString(4, e.getDatasaida());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Atualizado", "Tabela entrada atualizado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao atualizar a tabela entrada: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Entrada e){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE * FROM entrada WHERE idcliente = ?");
            stmt.setInt(1, e.getIdcliente());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Removido", "Tabela entrada removido com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao remover a tabela entrada: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Entrada> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Entrada> entradas = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM entrada");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Entrada e = new Entrada();
                e.setIdcliente(rs.getInt("idcliente"));
                e.setDataentrada(rs.getString("dataentrada"));
                e.setDatasaida(rs.getString("datasaida"));
                e.setIdquarto(rs.getInt("idquarto"));
                
                entradas.add(e);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return entradas;
    }
    
    public ArrayList<Entrada> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Entrada> entradas = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM entrada WHERE dataentrada LIKE ?"
                    + "or SELECT * FROM entrada WHERE datasaida LIKE ?"
                    + "or SELECT * FROM entrada WHERE idcliente LIKE ?"
                    + "or SELECT * FROM entrada WHERE idquarto LIKE ?");
            stmt.setString(1, "%'+palavraChave+'%");
            stmt.setString(2, "%'+palavraChave+'%");
            stmt.setString(3, "%'+palavraChave+'%");
            stmt.setString(4, "%'+palavraChave+'%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Entrada e = new Entrada();
                e.setIdcliente(rs.getInt("idcliente"));
                e.setDataentrada(rs.getString("dataentrada"));
                e.setDatasaida(rs.getString("datasaida"));
                e.setIdquarto(rs.getInt("idquarto"));
                
                entradas.add(e);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return entradas;
    }
    
}
