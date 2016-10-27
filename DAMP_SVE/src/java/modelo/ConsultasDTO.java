
package modelo;

/**
 * EQUIPO DAMP-2016
 */

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConsultasDTO {
    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();
    
    public static boolean ejecutar(String query) {
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ConsultasDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
