package hotelbrakutxeu.model.domain;

/**
 *
 * @author pc
 */
public class Quarto {
    
    private int idquarto;
    private String numeroquarto;
    private String tipoquarto;
    private float preco;
    private boolean status;

    @Override
    public String toString() {
        return "Quarto{" + "idquarto=" + idquarto + ", numeroquarto=" + numeroquarto + ", tipoquarto=" + tipoquarto + ", preco=" + preco + ", status=" + status + '}';
    }

    public int getIdquarto() {
        return idquarto;
    }

    public void setIdquarto(int idquarto) {
        this.idquarto = idquarto;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getNumeroquarto() {
        return numeroquarto;
    }

    public void setNumeroquarto(String numeroquarto) {
        this.numeroquarto = numeroquarto;
    }

    public String getTipoquarto() {
        return tipoquarto;
    }

    public void setTipoquarto(String tipoquarto) {
        this.tipoquarto = tipoquarto;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    

    
}
