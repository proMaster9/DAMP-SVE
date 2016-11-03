
package modelo;

import java.util.ArrayList;

/**
 *
 * @author daniel
 */
public class Bitacora{
    
    private int idBitacora;
    private String fecha;
    private String hora;
    private String accion;
    private String magistrado1;
    private String magistrado2;
    private String magistrado3;

    public Bitacora() {
    }

    public Bitacora(int idBitacora, String fecha, String hora, String accion, String magistrado1, String magistrado2, String magistrado3) {
        this.idBitacora = idBitacora;
        this.fecha = fecha;
        this.hora = hora;
        this.accion = accion;
        this.magistrado1 = magistrado1;
        this.magistrado2 = magistrado2;
        this.magistrado3 = magistrado3;
    }

    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
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

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getMagistrado1() {
        return magistrado1;
    }

    public void setMagistrado1(String magistrado1) {
        this.magistrado1 = magistrado1;
    }

    public String getMagistrado2() {
        return magistrado2;
    }

    public void setMagistrado2(String magistrado2) {
        this.magistrado2 = magistrado2;
    }

    public String getMagistrado3() {
        return magistrado3;
    }

    public void setMagistrado3(String magistrado3) {
        this.magistrado3 = magistrado3;
    }

    
    
}
