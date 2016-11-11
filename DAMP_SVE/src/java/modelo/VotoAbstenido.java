
package modelo;

/**
 *
 * @author daniel
 */
public class VotoAbstenido extends Voto{
    
    private int idVotoAbstenido;

    public int getIdVotoAbstenido() {
        return idVotoAbstenido;
    }

    public void setIdVotoAbstenido(int idVotoAbstenido) {
        this.idVotoAbstenido = idVotoAbstenido;
    }

    public VotoAbstenido(int idVotoAbstenido) {
        this.idVotoAbstenido = idVotoAbstenido;
    }

    public VotoAbstenido(int idVotoAbstenido, int idVoto, int idJrv, int idUsuario) {
        super(idVoto, idJrv, idUsuario);
        this.idVotoAbstenido = idVotoAbstenido;
    }


    
    
}
