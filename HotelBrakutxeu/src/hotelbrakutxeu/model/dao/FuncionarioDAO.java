package hotelbrakutxeu.model.dao;

import hotelbrakutxeu.Notificacao;
import hotelbrakutxeu.model.database.ConnectionFactory;
import hotelbrakutxeu.model.domain.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author pc
 */
public class FuncionarioDAO {
    
    private final Notificacao notific = new Notificacao();
    
    public void adicionar(Funcionario f){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("INSERT INTO funcionario (nome,datanasc,cargo,senha,endereco,telefone,email) "
                    + "VALUES (?,?,?,?,?,?,?)");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getDatanasc());
            stmt.setString(3, f.getCargo());
            stmt.setString(4, f.getSenha());
            stmt.setString(5, f.getEndereco());
            stmt.setInt(6, f.getTelefone());
            stmt.setString(7, f.getEmail());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Adicionado", "Funcionario adicionado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao adicionar funcionario: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void atualizar(Funcionario f){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("UPDATE funcionario SET nome = ? , datanasc = ?, cargo = ?, senha = ? "
                    + "endereco = ?, telefone = ?, email = ? WHERE idfuncionario = ?");
            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getDatanasc());
            stmt.setString(3, f.getCargo());
            stmt.setString(4, f.getSenha());
            stmt.setString(5, f.getEndereco());
            stmt.setInt(6, f.getTelefone());
            stmt.setString(7, f.getEmail());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Atualizado", "Funcionario atualizado com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao atualizar funcionario: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public void remover(Funcionario f){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try {
            stmt = conn.prepareStatement("DELETE * FROM funcionario WHERE idfuncionario = ?");
            stmt.setInt(1, f.getIdfuncionario());
            
            stmt.executeUpdate();
            
            notific.showNotificacao("Removido", "Funcionario removido com sucesso");
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao remover funcionario: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt);
        }
    }
    
    public ArrayList<Funcionario> listar(){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionario");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setIdfuncionario(rs.getInt("idfuncionario"));
                f.setNome(rs.getString("nome"));
                f.setSenha(rs.getString("senha"));
                f.setCargo(rs.getString("cargo"));
                f.setDatanasc(rs.getString("datanasc"));
                f.setEndereco(rs.getString("endereco"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getInt("telefone"));
                
                funcionarios.add(f);
            }
            
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao recuperar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return funcionarios;
    }
    
    public ArrayList<Funcionario> pesquisar(String palavraChave){
        
        Connection conn = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
                
        try {
            stmt = conn.prepareStatement("SELECT * FROM funcionario WHERE nome LIKE ?");
            stmt.setString(1, "%'+palavraChave+'%");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Funcionario f = new Funcionario();
                f.setIdfuncionario(rs.getInt("idfuncionario"));
                f.setNome(rs.getString("nome"));
                f.setSenha(rs.getString("senha"));
                f.setCargo(rs.getString("cargo"));
                f.setDatanasc(rs.getString("dataNasc"));
                f.setEndereco(rs.getString("morada"));
                f.setEmail(rs.getString("email"));
                f.setTelefone(rs.getInt("telefone"));
                funcionarios.add(f);
            }
        } catch (SQLException ex) {
            
            notific.showErro("Erro ao pesquisar dados: \n"+ex.getMessage());
            
        } finally {
            ConnectionFactory.closeConnection(conn, stmt, rs);
        }
        return funcionarios;
    }
    
}
