package hotelbrakutxeu.model.domain;

/**
 *
 * @author pc
 */
public class Cliente {
    
    private int idcliente;
    private String nome;
    private String nacionalidade;
    private int telefone;
    private String email;

    @Override
    public String toString() {
        return "Cliente{" + "idcliente=" + idcliente + ", nome=" + nome + ", nacionalidade=" + nacionalidade + ", telefone=" + telefone + ", email=" + email + '}';
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    
}
