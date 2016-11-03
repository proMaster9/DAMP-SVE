package modelo;

/**
 *
 * @author daniel
 */
public class Partido {
    
    private int idPartido;
    private String nombre;
    private String acronimo;
    private String numDui;
    private String imagen;

    public Partido() {
    }

    public Partido(int idPartido, String nombre, String acronimo, String numDui, String imagen) {
        this.idPartido = idPartido;
        this.nombre = nombre;
        this.acronimo = acronimo;
        this.numDui = numDui;
        this.imagen = imagen;
    }

    public int getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(int idPartido) {
        this.idPartido = idPartido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAcronimo() {
        return acronimo;
    }

    public void setAcronimo(String acronimo) {
        this.acronimo = acronimo;
    }

    public String getNumDui() {
        return numDui;
    }

    public void setNumDui(String numDui) {
        this.numDui = numDui;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
 
}
