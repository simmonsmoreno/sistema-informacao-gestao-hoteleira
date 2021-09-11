package hotelbrakutxeu.model.dao;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.database.ConnectionFactory;
import hotelbrakutxeu.model.domain.Quarto;
import hotelbrakutxeu.model.domain.Reserva;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class ReservaDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Reserva r, Quarto q){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO reserva ("
                    + "datareserva, codigoreserva, checkin, checkout, idfuncionario) "
                    + "VALUES (?, ?, ?, ?, ?);");
            stmt.setString(1, r.getDatareserva());
            stmt.setInt(2, r.getCodigoreserva());
            stmt.setString(3, r.getCheckin());
            stmt.setString(4, r.getCheckout());
            stmt.setInt(5, r.getIdfuncionario());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Adicionado", "Reserva criado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao criar Reserva: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Reserva r, Quarto q){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE reserva SET "
                    + "datareserva=?, codigoreserva=?, checkin=?, checkout=?, idfuncionario=? "
                    + "WHERE idreserva=?;");
            stmt.setString(1, r.getDatareserva());
            stmt.setInt(2, r.getCodigoreserva());
            stmt.setString(3, r.getCheckin());
            stmt.setString(4, r.getCheckout());
            stmt.setInt(5, r.getIdfuncionario());
            
            stmt.setInt(6, r.getIdreserva());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Atualizado", "Reserva atualizado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao atualizar reserva: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Reserva c){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE * FROM reserva WHERE idreserva = ?");
            stmt.setInt(1, c.getIdreserva());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Removido", "Reserva removido com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao remover reserva: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Reserva> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Reserva> reservas = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM reserva");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva r = new Reserva();
                r.setIdreserva(rs.getInt("idreserva"));
                r.setDatareserva(rs.getString("datareserva"));
                r.setCodigoreserva(rs.getInt("codigoreserva"));
                r.setCheckin(rs.getString("checkin"));
                r.setCheckout(rs.getString("checkout"));
                r.setIdfuncionario(rs.getInt("idfuncionario"));
                
                reservas.add(r);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return reservas;
    }
    
    public ArrayList<Reserva> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Reserva> reservas = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM reserva WHERE datareserva LIKE ?"
                    + "or SELECT * FROM reserva WHERE codigoreserva LIKE ?"
                    + "or SELECT * FROM reserva WHERE checkin LIKE ?"
                    + "or SELECT * FROM reserva WHERE checkout LIKE ?"
                    + "or SELECT * FROM reserva WHERE idfuncionario LIKE ?");
            stmt.setString(1, "%'+palavraChave+'%");
            stmt.setString(2, "%'+palavraChave+'%");
            stmt.setString(3, "%'+palavraChave+'%");
            stmt.setString(4, "%'+palavraChave+'%");
            stmt.setString(5, "%'+palavraChave+'%");
            
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Reserva r = new Reserva();
                r.setIdreserva(rs.getInt("idreserva"));
                r.setDatareserva(rs.getString("datareserva"));
                r.setCodigoreserva(rs.getInt("codigoreserva"));
                r.setCheckin(rs.getString("checkin"));
                r.setCheckout(rs.getString("checkout"));
                r.setIdfuncionario(rs.getInt("idfuncionario"));
                
                reservas.add(r);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return reservas;
    }
    
}
