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
    public static boolean agregarRespuesta(int idUsuario,int idPregunta, String respuesta) {
        String query = "insert into respuesta values(?,?,?);";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idUsuario);
            pst.setInt(2, idPregunta);
            pst.setString(3, respuesta);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.desconectar();
        }
        return true;
        
    }
    public static boolean modificarRespuesta(int idPregunta, String respuesta, int idUsuario) {
        String query = "update respuesta set id_pregunta=?, respuesta=? where id_usuario=?";
        try {
            pst = con.getCnn().prepareStatement(query);
            
            pst.setInt(1, idPregunta);
            pst.setString(2, respuesta);
            pst.setInt(3, idUsuario);
            pst.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.desconectar();
        }
        return true;
        
    }
    /*public static void main(String[] args) {
        
        Respuesta r = RespuestaDTO.verificarRespuesta(3);
        System.out.println(r.getIdPregunta()+r.getRespuesta() +r.getIdUsuario());
    }*/
}
