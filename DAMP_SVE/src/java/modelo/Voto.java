
package modelo;

/**
 *
 * @author daniel
 */
public class Voto {
    
    protected int idVoto;
    protected int idJrv;

    public Voto() {
    }

    public Voto(int idVoto, int idJrv) {
        this.idVoto = idVoto;
        this.idJrv = idJrv;
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
