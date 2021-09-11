package hotelbrakutxeu.model.domain;

/**
 *
 * @author pc
 */
public class Funcionario {
    
    private int idfuncionario;
    private String nome;
    private String datanasc;
    private String cargo;
    private String senha;
    private String endereco;
    private int telefone;
    private String email;

    @Override
    public String toString() {
        return "Funcionario{" + "idfuncionario=" + idfuncionario + ", nome=" + nome + ", datanasc=" + datanasc + ", cargo=" + cargo + ", senha=" + senha + ", endereco=" + endereco + ", telefone=" + telefone + ", email=" + email + '}';
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDatanasc() {
        return datanasc;
    }

    public void setDatanasc(String datanasc) {
        this.datanasc = datanasc;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
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
