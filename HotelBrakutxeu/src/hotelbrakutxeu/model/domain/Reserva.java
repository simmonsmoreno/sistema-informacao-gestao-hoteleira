package hotelbrakutxeu.model.domain;

/**
 *
 * @author pc
 */
public class Reserva {
    
    private int idreserva;
    private String datareserva;
    private int codigoreserva;
    private String checkin;
    private String checkout;
    private int idfuncionario;

    @Override
    public String toString() {
        return "Reserva{" + "idreserva=" + idreserva + ", datareserva=" + datareserva + ", codigoreserva=" + codigoreserva + ", checkin=" + checkin + ", checkout=" + checkout + ", idfuncionario=" + idfuncionario + '}';
    }

    
    
    public int getIdreserva() {
        return idreserva;
    }

    public void setIdreserva(int idreserva) {
        this.idreserva = idreserva;
    }

    public String getDatareserva() {
        return datareserva;
    }

    public void setDatareserva(String datareserva) {
        this.datareserva = datareserva;
    }

    public int getCodigoreserva() {
        return codigoreserva;
    }

    public void setCodigoreserva(int codigoreserva) {
        this.codigoreserva = codigoreserva;
    }

    public String getCheckin() {
        return checkin;
    }

    public void setCheckin(String checkin) {
        this.checkin = checkin;
    }

    public String getCheckout() {
        return checkout;
    }

    public void setCheckout(String checkout) {
        this.checkout = checkout;
    }

    public int getIdfuncionario() {
        return idfuncionario;
    }

    public void setIdfuncionario(int idfuncionario) {
        this.idfuncionario = idfuncionario;
    }
    
    
    
}
