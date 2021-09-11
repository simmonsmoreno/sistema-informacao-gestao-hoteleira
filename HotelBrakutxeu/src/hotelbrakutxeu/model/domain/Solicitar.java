package hotelbrakutxeu.model.domain;

/**
 *
 * @author pc
 */
public class Solicitar {
    
    private int idcliente;
    private int idreserva;
    private String datasolicitar;

    @Override
    public String toString() {
        return "Solicitar{" + "idcliente=" + idcliente + ", idreserva=" + idreserva + ", datasolicitar=" + datasolicitar + '}';
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getDatasolicitar() {
        return datasolicitar;
    }

    public void setDatasolicitar(String datasolicitar) {
        this.datasolicitar = datasolicitar;
    }
    
    
    
}
