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
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NACHO
 */
public class SupervisorDTO {

    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();

    public SupervisorDTO() {
    }

    public static SupervisorExt entrarSupervisor(String identificacion, String contrasenia) {
        SupervisorExt sup = new SupervisorExt();
        String query = "select * from entrarSupervisor(?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, identificacion);
            pst.setString(2, contrasenia);
            rs = pst.executeQuery();
            while (rs.next()) {
                sup.setIdUsuario(rs.getInt("id_usuario"));
                sup.setIdentificacion(rs.getString("identificacion"));
                sup.setContrasenia(rs.getString("contrasenia"));
                sup.setNombre(rs.getString("nombre"));
                sup.setApellido(rs.getString("apellido"));
                sup.setSexo(rs.getString("sexo"));
                sup.setConfirmacion(rs.getInt("confirmacion"));
                sup.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                sup.setPais(rs.getString("pais"));
                sup.setOrganizacion(rs.getString("organizacion"));
                sup.setRol(rs.getString("tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return sup;
    }

    public static boolean agregarSupervisor(SupervisorExt sup) {
        String query = "select agregarSupervisor(?,?,?,?,?,?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, sup.getIdentificacion());
            pst.setString(2, sup.getContrasenia());
            pst.setString(3, sup.getNombre());
            pst.setString(4, sup.getApellido());
            pst.setString(5, sup.getSexo());
            pst.setString(6, sup.getPais());
            pst.setString(7, sup.getOrganizacion());
            pst.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.desconectar();
        }
    }

    public static boolean modificarSupervisor(SupervisorExt sup) {
        String query = "select modificarSupervisor(?,?,?,?,?,?,?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, sup.getIdUsuario());
            pst.setString(2, sup.getIdentificacion());
            pst.setString(3, sup.getContrasenia());
            pst.setString(4, sup.getNombre());
            pst.setString(5, sup.getApellido());
            pst.setString(6, sup.getSexo());
            pst.setString(7, sup.getPais());
            pst.setString(8, sup.getOrganizacion());
            pst.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.desconectar();
        }
    }

    public static boolean eliminarSupervisor(int idUsuario) {
        String query = "select eliminarSupervisor(?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idUsuario);
            pst.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } finally {
            con.desconectar();
        }
    }

    public static SupervisorExt mostrarSupervisor(int idSupervisor) {
        String query = "select * from infosupext sup inner join usuario u on sup.id_usuario = u.id_usuario where u.id_usuario = ?";
        SupervisorExt sup = new SupervisorExt();
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idSupervisor);
            rs = pst.executeQuery();
            while (rs.next()) {
                sup.setIdUsuario(rs.getInt("id_usuario"));
                sup.setIdentificacion(rs.getString("identificacion"));
                sup.setContrasenia(rs.getString("contrasenia"));
                sup.setNombre(rs.getString("nombre"));
                sup.setApellido(rs.getString("apellido"));
                sup.setSexo(rs.getString("sexo"));
                sup.setConfirmacion(rs.getInt("confirmacion"));
                sup.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                sup.setPais(rs.getString("pais"));
                sup.setOrganizacion(rs.getString("organizacion"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return sup;
    }

    public static ArrayList<SupervisorExt> mostrarSupervisores() {
        String query = "select * from infosupext sup inner join usuario u on sup.id_usuario = u.id_usuario";
        ArrayList<SupervisorExt> supervisores = new ArrayList();
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                SupervisorExt sup = new SupervisorExt();
                sup.setIdUsuario(rs.getInt("id_usuario"));
                sup.setIdentificacion(rs.getString("identificacion"));
                sup.setContrasenia(rs.getString("contrasenia"));
                sup.setNombre(rs.getString("nombre"));
                sup.setApellido(rs.getString("apellido"));
                sup.setSexo(rs.getString("sexo"));
                sup.setConfirmacion(rs.getInt("confirmacion"));
                sup.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                sup.setPais(rs.getString("pais"));
                sup.setOrganizacion(rs.getString("organizacion"));
                supervisores.add(sup);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SupervisorDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return supervisores;
    }

    public static void main(String[] args) {
        SupervisorExt sup = entrarSupervisor("00-00-00","12345");
        if(sup.getIdUsuario() != 0) {
            System.out.println("TIpo de usuario: " + sup.getRol());
        }
        /*
        ArrayList<SupervisorExt> supervisor = mostrarSupervisores();
        if (supervisor.size() > 0) {
            for (SupervisorExt sup : supervisor) {
                System.out.println("idUsuario: " + sup.getIdUsuario());
                System.out.println("Identificacion: " + sup.getIdentificacion());
                System.out.println("Contrasenia: " + sup.getContrasenia());
                System.out.println("Nombre: " + sup.getNombre());
                System.out.println("Apellido: " + sup.getApellido());
                System.out.println("Sexo: " + sup.getSexo());
                System.out.println("Confirmacion: " + sup.getConfirmacion());
                System.out.println("Tipo usuario: " + sup.getTipoUsuario());
                System.out.println("Pais: " + sup.getPais());
                System.out.println("Organizacion: " + sup.getOrganizacion());
                System.out.println("-----------------");
            }
        }
        else {
            System.out.println("No hay datos en el arraylist");
        }
        */
        
        
        /*
         SupervisorExt sup = mostrarSupervisor(108);
         if(sup.getIdUsuario() != 0) {
         System.out.println("idUsuario: " + sup.getIdUsuario());
         System.out.println("Identificacion: " + sup.getIdentificacion());
         System.out.println("Contrasenia: " + sup.getContrasenia());
         System.out.println("Nombre: " + sup.getNombre());
         System.out.println("Apellido: " + sup.getApellido());
         System.out.println("Sexo: " + sup.getSexo());
         System.out.println("Confirmacion: " + sup.getConfirmacion());
         System.out.println("Tipo usuario: " + sup.getTipoUsuario());
         System.out.println("Pais: " + sup.getPais());
         System.out.println("Organizacion: " + sup.getOrganizacion());
             System.out.println("Tipo: " + sup.getRol());
         }
         else {
         System.out.println("Usuario no encontrado");
         }
         */

    }

}
