
package modelo;

import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class Jrv {
    
    private int idJrv;
    private int idCentroVotacion;
    private String correlativoJrv;
    private ArrayList<DirectivaJrv> directiva;

    public Jrv() {
    }

    public Jrv(int idJrv, int idCentroVotacion, String correlativoJrv, ArrayList<DirectivaJrv> directiva) {
        this.idJrv = idJrv;
        this.idCentroVotacion = idCentroVotacion;
        this.correlativoJrv = correlativoJrv;
        this.directiva = directiva;
    }

    public int getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(int idJrv) {
        this.idJrv = idJrv;
    }

    public int getIdCentroVotacion() {
        return idCentroVotacion;
    }

    public void setIdCentroVotacion(int idCentroVotacion) {
        this.idCentroVotacion = idCentroVotacion;
    }

    public String getCorrelativoJrv() {
        return correlativoJrv;
    }

    public void setCorrelativoJrv(String correlativoJrv) {
        this.correlativoJrv = correlativoJrv;
    }

    public ArrayList<DirectivaJrv> getDirectiva() {
        return directiva;
    }

    public void setDirectiva(ArrayList<DirectivaJrv> directiva) {
        this.directiva = directiva;
    }
    
    
    
}
