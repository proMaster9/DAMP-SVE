
package modelo;

/**
 *
 * @author daniel
 */
public class Voto {
    
    private int idVoto;
    private int idJrv;
    private int idUsuario;

    public Voto() {
    }

    public Voto(int idVoto, int idJrv, int idUsuario) {
        this.idVoto = idVoto;
        this.idJrv = idJrv;
        this.idUsuario = idUsuario;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdVoto() {
        return idVoto;
    }

    public void setIdVoto(int idVoto) {
        this.idVoto = idVoto;
    }

    public int getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(int idJrv) {
        this.idJrv = idJrv;
    }



}
