package hotelbrakutxeu.model.domain;

/**
 *
 * @author pc
 */
public class Entrada {
    
    private int idcliente;
    private int idquarto;
    private String dataentrada;
    private String datasaida;

    @Override
    public String toString() {
        return "Entrada{" + "idcliente=" + idcliente + ", idquarto=" + idquarto + ", dataentrada=" + dataentrada + ", datasaida=" + datasaida + '}';
    }

    public int getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(int idcliente) {
        this.idcliente = idcliente;
    }

    public int getIdquarto() {
        return idquarto;
    }

    public void setIdquarto(int idquarto) {
        this.idquarto = idquarto;
    }

    public String getDataentrada() {
        return dataentrada;
    }

    public void setDataentrada(String dataentrada) {
        this.dataentrada = dataentrada;
    }

    public String getDatasaida() {
        return datasaida;
    }

    public void setDatasaida(String datasaida) {
        this.datasaida = datasaida;
    }
    
    

}
