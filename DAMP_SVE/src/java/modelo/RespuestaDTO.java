/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eliiza
 */
public class RespuestaDTO {

    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();
    
    public static Respuesta verificarRespuesta(int idUsuario) {
        Respuesta r = new Respuesta();
        String query = "select * from respuesta where id_usuario=?";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();
            while (rs.next()) {
                r.setIdUsuario(rs.getInt("id_usuario"));
                r.setIdPregunta(rs.getInt("id_pregunta"));
                r.setRespuesta(rs.getString("respuesta")); 
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return r;
        
    }
}
