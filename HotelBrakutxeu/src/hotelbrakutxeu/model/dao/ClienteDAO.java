package hotelbrakutxeu.model.dao;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.database.ConnectionFactory;
import hotelbrakutxeu.model.domain.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class ClienteDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Cliente c){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO cliente( "
                    + "nome, nacionalidade, telefone, email) "
                    + "VALUES (?, ?, ?, ?);");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getNacionalidade());
            stmt.setInt(3, c.getTelefone());
            stmt.setString(4, c.getEmail());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Adicionado", "Cliente criado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao criar Cliente: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Cliente c){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE cliente SET "
                    + "nome=?, nacionalidade=?, telefone=?, email=? "
                    + "WHERE idcliente=?;");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getNacionalidade());
            stmt.setInt(3, c.getTelefone());
            stmt.setString(4, c.getEmail());
            stmt.setInt(5, c.getIdcliente());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Atualizado", "Cliente atualizado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao atualizar cliente: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Cliente c){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE * FROM cliente WHERE idcliente = ?");
            stmt.setInt(1, c.getIdcliente());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Removido", "Cliente removido com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao remover cliente: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Cliente> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Cliente> clientes = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM cliente");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente c = new Cliente();
                c.setIdcliente(rs.getInt("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setNacionalidade(rs.getString("nacionalidade"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getInt("telefone"));
                
                
                clientes.add(c);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return clientes;
    }
    
    public ArrayList<Cliente> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Cliente> clientes = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, "%'+palavraChave+'%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente c = new Cliente();
                c.setIdcliente(rs.getInt("idcliente"));
                c.setNome(rs.getString("nome"));
                c.setNacionalidade(rs.getString("nacionalidade"));
                c.setEmail(rs.getString("email"));
                c.setTelefone(rs.getInt("telefone"));
                clientes.add(c);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return clientes;
    }
    
}
