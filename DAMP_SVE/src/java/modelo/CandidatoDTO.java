package modelo;

/**
 * EQUIPO DAMP-2016
 */

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CandidatoDTO {

    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();

    /*
    se recibe como parametro un objeto Candidato, en cual extrae la informacion
    de sus atributos y las inserta en un registro en la base de datos
    retorna true cuando se agrego correctamente el registro,
    retorna false cuando hubo algun error
    */
    public static boolean agregarCandidato(Candidato can) {
        String query = "insert into candidato (num_dui, foto, tipo, id_departamento, id_partido ) values (?,?,?,?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, can.getNumDui());
            pst.setString(2, can.getFoto());
            pst.setInt(3, can.getTipo());
            pst.setInt(4, can.getIdDepartamento());
            pst.setInt(5, can.getIdPartido());
            pst.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            con.desconectar();
        }
        return true;
    }

    /*
    se recibe como parametro un objeto Candidato del cual se extrae el valor de sus
    atributos para podes hacer la actualizacion del registro.
    como requisito para esta clase, el objeto pasado por parametro debe poseer un valor
    en su atributo idCandidato
    retorna true cuando modifica correctamente, y false cuando ocurre algun error
    */
    public static boolean modificarCandidato(Candidato can) {
        String query = "update candidato set num_dui=?, foto=?, tipo=?, id_departamento=?, id_partido=?  where id_candidato=?";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, can.getNumDui());
            pst.setString(2, can.getFoto());
            pst.setInt(3, can.getTipo());
            pst.setInt(4, can.getIdDepartamento());
            pst.setInt(5, can.getIdPartido());
            pst.setInt(6, can.getIdCandidato());
            pst.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            con.desconectar();
        }
        return true;
    }

    /*
    se recibe como paremtro un entero que contiene el id del candidato
    el id se utiliza para eliminar ese registro.
    se retorna true cuando se elimina correctamente y se 
    retorna false cuando hubo algun error
    */
    public static boolean eliminarCandidato(int idCandidato) {
        String query = "delete from candidato where id_candidato=?";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idCandidato);
            pst.executeUpdate();

        } catch (Exception ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;

        } finally {
            con.desconectar();
        }
        return true;
    }

    /*
    se recibe como parametro el id de un candidato, se realiza una busqueda
    y retorna la informacion de este candidato en un objeto tipo Candidato
    cuando no se encuentran resultados, se retorna un objeto vacio
    */
    public static Candidato mostrarCandidato(int idCandidato) {
        String query = "select * from candidato where id_candidato=?";
        Candidato can = new Candidato();
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idCandidato);
            rs = pst.executeQuery();
            while (rs.next()) {
                can.setIdCandidato(rs.getInt("id_candidato"));
                can.setNumDui(rs.getString("num_dui"));
                can.setFoto(rs.getString("foto"));
                can.setTipo(rs.getInt("tipo"));
                can.setIdDepartamento(rs.getInt("id_departamento"));
                can.setIdPartido(rs.getInt("id_partido"));
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return can;
    }
    
    /*
    esta funcion retorna un arraylist con todos los candidatos que estan 
    registrados en el sistema
    */
    public static ArrayList<Candidato> mostrarCandidatos() {
        ArrayList<Candidato> candidatos = new ArrayList<Candidato>();
        String query = "select * from candidato";

        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Candidato can = new Candidato();
                can.setIdCandidato(rs.getInt("id_candidato"));
                can.setNumDui(rs.getString("num_dui"));
                can.setFoto(rs.getString("foto"));
                can.setTipo(rs.getInt("tipo"));
                can.setIdDepartamento(rs.getInt("id_departamento"));
                can.setIdPartido(rs.getInt("id_partido"));
                candidatos.add(can);
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidatos;
    }
    
    /*
    esta funcion recibe como parametro un entero con el idDepartamento para 
    retornar los candidatos que hay registrados en determinado departamento
    */
    public static ArrayList<Candidato> mostrarCandidatosDep(int idDepartamento) {
        ArrayList<Candidato> candidatos = new ArrayList<Candidato>();
        String query = "select * from candidato where id_departamento = " + idDepartamento;

        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();

            while (rs.next()) {
                Candidato can = new Candidato();
                can.setIdCandidato(rs.getInt("id_candidato"));
                can.setNumDui(rs.getString("num_dui"));
                can.setFoto(rs.getString("foto"));
                can.setTipo(rs.getInt("tipo"));
                can.setIdDepartamento(rs.getInt("id_departamento"));
                can.setIdPartido(rs.getInt("id_partido"));
                candidatos.add(can);
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidatos;
    }
    
}
