
package modelo;

/**
 *
 * @author daniel
 */
public class VotoAbstenido extends Voto{
    
    private int idVotoAbstenido;

    public VotoAbstenido() {
    }

    public VotoAbstenido(int idVoto, int idJrv) {
        super(idVoto, idJrv);
    }

    public VotoAbstenido(int idVotoAbstenido) {
        this.idVotoAbstenido = idVotoAbstenido;
    }

    public VotoAbstenido(int idVotoAbstenido, int idVoto, int idJrv) {
        super(idVoto, idJrv);
        this.idVotoAbstenido = idVotoAbstenido;
    }

    public int getIdVotoAbstenido() {
        return idVotoAbstenido;
    }

    public void setIdVotoAbstenido(int idVotoAbstenido) {
        this.idVotoAbstenido = idVotoAbstenido;
    }
    
    
}
