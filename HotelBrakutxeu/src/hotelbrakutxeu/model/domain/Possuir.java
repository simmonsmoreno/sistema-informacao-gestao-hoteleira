package hotelbrakutxeu.model.domain;

/**
 *
 * @author pc
 */
public class Possuir {
    
    private int idreserva;
    private int idquarto;

    @Override
    public String toString() {
        return "Possuir{" + "idreserva=" + idreserva + ", idquarto=" + idquarto + '}';
    }

    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public int getIdquarto() {
        return idquarto;
    }

    public void setIdquarto(int idquarto) {
        this.idquarto = idquarto;
    } 
    
}
