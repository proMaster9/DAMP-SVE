
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

    public VotoBandera(int idVotoBandera, int idPartido) {
        this.idVotoBandera = idVotoBandera;
        this.idPartido = idPartido;
    }

    public VotoBandera(int idVotoBandera, int idPartido, int idVoto, int idJrv, int idUsuario) {
        super(idVoto, idJrv, idUsuario);
        this.idVotoBandera = idVotoBandera;
        this.idPartido = idPartido;
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
