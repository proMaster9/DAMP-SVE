package modelo;

/**
 *
 * @author JMndz
 */
public class Usuario {
    
    protected int idUsuario;
    protected String contrasenia;
    protected String nombre;
    protected String apellido;
    protected String sexo;
    protected int confirmacion;
    protected int tipoUsuario;
    protected String rol;
    
    public Usuario() {
    }

    public Usuario(int idUsuario, String contrasenia, String nombre, String apellido, String sexo, int confirmacion, int tipoUsuario, String rol) {
        this.idUsuario = idUsuario;
        this.contrasenia = contrasenia;
        this.nombre = nombre;
        this.apellido = apellido;
        this.sexo = sexo;
        this.confirmacion = confirmacion;
        this.tipoUsuario = tipoUsuario;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(int confirmacion) {
        this.confirmacion = confirmacion;
    }

    public int getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(int tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    } 
    
    public String getRol() {
        return rol;
    }
    
    public void setRol(String rol) {
        this.rol = rol;
    }
}
