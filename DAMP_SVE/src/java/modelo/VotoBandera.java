
package modelo;

/**
 *
 * DUDA Constr.
 * 
 * @author daniel
 */
public class VotoBandera extends Voto{
    
    private int idVotoBandera;
    private int idPartido;

    public VotoBandera() {
    }

    public VotoBandera(int idVoto, int idJrv) {
        super(idVoto, idJrv);
    }

    public int getIdVotoBandera() {
        return idVotoBandera;
    }

    public void setIdVotoBandera(int idVotoBandera) {
        this.idVotoBandera = idVotoBandera;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }
    
    
    
}
