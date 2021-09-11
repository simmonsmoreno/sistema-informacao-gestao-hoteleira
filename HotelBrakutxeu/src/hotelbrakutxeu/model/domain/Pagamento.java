package hotelbrakutxeu.model.domain;

/**
 *
 * @author pc
 */
public class Pagamento {
    
    private int idpagamento;
    private String datapagamento;
    private float valor;
    private int idreserva;

    @Override
    public String toString() {
        return "Pagamento{" + "idpagamento=" + idpagamento + ", datapagamento=" + datapagamento + ", valor=" + valor + ", idreserva=" + idreserva + '}';
    }

    public int getIdpagamento() {
        return idpagamento;
    }

    public void setIdpagamento(int idpagamento) {
        this.idpagamento = idpagamento;
    }

    public String getDatapagamento() {
        return datapagamento;
    }

    public void setDatapagamento(String datapagamento) {
        this.datapagamento = datapagamento;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }
    
    
    
}
