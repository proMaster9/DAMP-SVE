
package modelo;

import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class AccionJrv {
    
    private int idJrv;
    private String fecha;
    private String hora;
    private ArrayList<Ciudadano> responsable;

    public AccionJrv() {
    }

    public AccionJrv(int idJrv, String fecha, String hora, ArrayList<Ciudadano> responsable) {
        this.idJrv = idJrv;
        this.fecha = fecha;
        this.hora = hora;
        this.responsable = responsable;
    }

    public int getIdJrv() {
        return idJrv;
    }

    public void setIdJrv(int idJrv) {
        this.idJrv = idJrv;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public ArrayList<Ciudadano> getResponsable() {
        return responsable;
    }

    public void setResponsable(ArrayList<Ciudadano> responsable) {
        this.responsable = responsable;
    }

}
