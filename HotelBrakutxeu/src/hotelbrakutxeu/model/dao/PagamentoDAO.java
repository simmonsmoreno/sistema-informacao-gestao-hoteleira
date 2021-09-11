package hotelbrakutxeu.model.dao;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.database.ConnectionFactory;
import hotelbrakutxeu.model.domain.Pagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class PagamentoDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Pagamento p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO pagamento( "
                    + "datapagamento, valor, idreserva) VALUES (?, ?, ?);");
            stmt.setString(1, p.getDatapagamento());
            stmt.setFloat(2, p.getValor());
            stmt.setInt(3, p.getIdreserva());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Adicionado", "Pagamento feito com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao fazer Pagamento: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Pagamento p){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE pagamento SET "
                    + "datapagamento=?, valor=?"
                    + "WHERE idpagamento=?;");
            stmt.setString(1, p.getDatapagamento());
            stmt.setFloat(2, p.getValor());
            
            stmt.setInt(3, p.getIdpagamento());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Atualizado", "Pagamento atualizado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao atualizar pagamento: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Pagamento c){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE * FROM pagamento WHERE idpagamento = ?");
            stmt.setInt(1, c.getIdpagamento());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Removido", "Pagamento removido com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao remover pagamento: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Pagamento> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM pagamento");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamento p = new Pagamento();
                p.setIdpagamento(rs.getInt("idpagamento"));
                p.setDatapagamento(rs.getString("datapagamento"));
                p.setValor(rs.getFloat("valor"));
                p.setIdreserva(rs.getInt("idreserva"));
                
                pagamentos.add(p);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pagamentos;
    }
    
    public ArrayList<Pagamento> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Pagamento> pagamentos = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM pagamento WHERE idreserva LIKE ?"
                    + "or SELECT * FROM pagamento WHERE datapagamento LIKE ?");
            stmt.setString(1, "%'+palavraChave+'%");
            stmt.setString(2, "%'+palavraChave+'%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Pagamento p = new Pagamento();
                p.setIdpagamento(rs.getInt("idpagamento"));
                p.setDatapagamento(rs.getString("datapagamento"));
                p.setValor(rs.getFloat("valor"));
                p.setIdreserva(rs.getInt("idreserva"));
                
                pagamentos.add(p);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return pagamentos;
    }
    
}
