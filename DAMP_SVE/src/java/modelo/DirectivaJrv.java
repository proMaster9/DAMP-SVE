
package modelo;

/**
 *
 * @author daniel
 */
public class DirectivaJrv {
    
    private String numDui;
    private int idJrv;
    private int idPartido;
    private int idCargoDirectiva;
    private int tipo;

    public DirectivaJrv() {
    }

    public DirectivaJrv(String numDui, int idJrv, int idPartido, int idCargoDirectiva, int tipo) {
        this.numDui = numDui;
        this.idJrv = idJrv;
        this.idPartido = idPartido;
        this.idCargoDirectiva = idCargoDirectiva;
        this.tipo = tipo;
    }

    public String getNumDui() {
        return numDui;
    }

    public void setNumDui(String numDui) {
        this.numDui = numDui;
    }

    public int getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(int idJrv) {
        this.idJrv = idJrv;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public int getIdCargoDirectiva() {
        return idCargoDirectiva;
    }

    public void setIdCargoDirectiva(int idCargoDirectiva) {
        this.idCargoDirectiva = idCargoDirectiva;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
 
}
