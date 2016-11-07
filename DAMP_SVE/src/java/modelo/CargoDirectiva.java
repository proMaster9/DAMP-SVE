
package modelo;

/**
 *
 * @author daniel
 */
public class CargoDirectiva {
    
    private int idCargoDirectiva;
    private String cargo;

    public CargoDirectiva() {
    }

    public CargoDirectiva(int idCargoDirectiva, String cargo) {
        this.idCargoDirectiva = idCargoDirectiva;
        this.cargo = cargo;
    }

    public int getIdCargoDirectiva() {
        return idCargoDirectiva;
    }

    public void setIdCargoDirectiva(int idCargoDirectiva) {
        this.idCargoDirectiva = idCargoDirectiva;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    
    
    
}

