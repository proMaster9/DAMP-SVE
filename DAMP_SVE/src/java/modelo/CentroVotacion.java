
package modelo;

/**
 *
 * @author daniel
 */
public class CentroVotacion {
    
    private int idCentroVotacion;
    private int idMunicipio;
    private String direccion;
    private String numDui;
    private String nombreCentro;
    private int numJrvDisponible;

    public CentroVotacion() {
    }

    public CentroVotacion(int idCentroVotacion, int idMunicipio, String direccion, String numDui, String nombreCentro, int numJrvDisponible) {
        this.idCentroVotacion = idCentroVotacion;
        this.idMunicipio = idMunicipio;
        this.direccion = direccion;
        this.numDui = numDui;
        this.nombreCentro = nombreCentro;
        this.numJrvDisponible = numJrvDisponible;
    }

    public int getIdCentroVotacion() {
        return idCentroVotacion;
    }

    public void setIdCentroVotacion(int idCentroVotacion) {
        this.idCentroVotacion = idCentroVotacion;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumDui() {
        return numDui;
    }

    public void setNumDui(String numDui) {
        this.numDui = numDui;
    }

    public String getNombreCentro() {
        return nombreCentro;
    }

    public void setNombreCentro(String nombreCentro) {
        this.nombreCentro = nombreCentro;
    }

    public int getNumJrvDisponible() {
        return numJrvDisponible;
    }

    public void setNumJrvDisponible(int numJrvDisponible) {
        this.numJrvDisponible = numJrvDisponible;
    }
    
    
    
}
