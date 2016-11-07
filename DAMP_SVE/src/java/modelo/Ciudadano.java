
package modelo;

/**
 *
 * @author daniel
 */
public class Ciudadano extends Usuario{
    
    private String numDui;
    private String fechaNac;
    private String direccion;
    private int idMunicipio;
    private int idDepartamento;

    public Ciudadano() {
    }

    public Ciudadano(int idUsuario, String contrasenia, String nombre, String apellido, String sexo, int confirmacion, int tipoUsuario, String rol) {
        super(idUsuario, contrasenia, nombre, apellido, sexo, confirmacion, tipoUsuario, rol);
    }

    public Ciudadano(String numDui, String fechaNac, String direccion, int idMunicipio, int idDepartamento) {
        this.numDui = numDui;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.idMunicipio = idMunicipio;
        this.idDepartamento = idDepartamento;
    }

    public Ciudadano(String numDui, String fechaNac, String direccion, int idMunicipio, int idDepartamento, int idUsuario, String contrasenia, String nombre, String apellido, String sexo, int confirmacion, int tipoUsuario, String rol) {
        super(idUsuario, contrasenia, nombre, apellido, sexo, confirmacion, tipoUsuario, rol);
        this.numDui = numDui;
        this.fechaNac = fechaNac;
        this.direccion = direccion;
        this.idMunicipio = idMunicipio;
        this.idDepartamento = idDepartamento;
    }

    public String getNumDui() {
        return numDui;
    }

    public void setNumDui(String numDui) {
        this.numDui = numDui;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

 
}
