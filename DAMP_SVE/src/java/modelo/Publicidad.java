
package modelo;

/**
 *
 * @author daniel
 */
public class Publicidad {
    
    private int idPublicidad;
    private String descripcion;
    private String codigo;
    private int idUsuario;

    public Publicidad() {
    }

    public Publicidad(int idPublicidad, String descripcion, String codigo, int idUsuario) {
        this.idPublicidad = idPublicidad;
        this.descripcion = descripcion;
        this.codigo = codigo;
        this.idUsuario = idUsuario;
    }

    public int getIdPublicidad() {
        return idPublicidad;
    }

    public void setIdPublicidad(int idPublicidad) {
        this.idPublicidad = idPublicidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
   
}
