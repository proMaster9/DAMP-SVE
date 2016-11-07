package modelo;

/**
 *
 * @author daniel
 */
public class SupervisorExt extends Usuario{
    
    private String identificacion;
    private String pais;
    private String organizacion;

    public SupervisorExt() {
    }

    public SupervisorExt(int idUsuario, String contrasenia, String nombre, String apellido, String sexo, int confirmacion, int tipoUsuario, String rol) {
        super(idUsuario, contrasenia, nombre, apellido, sexo, confirmacion, tipoUsuario, rol);
    }

    public SupervisorExt(String identificacion, String pais, String organizacion) {
        this.identificacion = identificacion;
        this.pais = pais;
        this.organizacion = organizacion;
    }

    public SupervisorExt(String identificacion, String pais, String organizacion, int idUsuario, String contrasenia, String nombre, String apellido, String sexo, int confirmacion, int tipoUsuario, String rol) {
        super(idUsuario, contrasenia, nombre, apellido, sexo, confirmacion, tipoUsuario, rol);
        this.identificacion = identificacion;
        this.pais = pais;
        this.organizacion = organizacion;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    
    
}
