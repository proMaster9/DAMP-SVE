
package modelo;

/**
 * EQUIPO DAMP-2016
 */
public class Municipio {
    private int idMunicipio;
    private int idDepartmento;
    private String nombreMunicipio;

    public Municipio() {
    }

    public Municipio(int idMunicipio, int idDepartmento, String nombreMunicipio) {
        this.idMunicipio = idMunicipio;
        this.idDepartmento = idDepartmento;
        this.nombreMunicipio = nombreMunicipio;
    }

    public int getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(int idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public int getIdDepartmento() {
        return idDepartmento;
    }

    public void setIdDepartmento(int idDepartmento) {
        this.idDepartmento = idDepartmento;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }
    
}
