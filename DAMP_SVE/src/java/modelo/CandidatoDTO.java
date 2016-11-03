package modelo;

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
     candidato tipo 1 significa partidario
     candidato tipo 2 significa independiente
     */
    public static boolean agregarCandidato(Candidato can) {
        String query = "insert into candidato (num_dui, foto, tipo, id_departamento) values (?,?,?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, can.getNumDui());
            pst.setString(2, can.getFoto());
            pst.setInt(3, can.getTipo());
            pst.setInt(4, can.getIdDepartamento());
            pst.executeUpdate();
            //si el candidato es partidario se agrega a la tabla detallePartido
            if (can.getTipo() == 1) {
                query = "insert into detallepartido (id_candidato,id_partido) values (lastval(),?)";
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, can.getIdPartido());
                pst.executeUpdate();
            }

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
        String query = "update candidato set num_dui=?, foto=?, tipo=?, id_departamento=?  where id_candidato=?";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, can.getNumDui());
            pst.setString(2, can.getFoto());
            pst.setInt(3, can.getTipo());
            pst.setInt(4, can.getIdDepartamento());
            pst.setInt(5, can.getIdCandidato());
            pst.executeUpdate();
            //si el candidato es partidario, se modifican los registros de 'detallepartido' tambien
            if (can.getTipo() == 1) {
                query = "update detallepartido set id_partido = ? where id_candidato = ?";
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, can.getIdPartido());
                pst.setInt(2, can.getIdCandidato());
                pst.executeUpdate();
            }

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
        //obtenemos si el candidato es partidario o independiente
        int tipo = mostrarCandidato(idCandidato).getTipo();

        try {
            /*
             candidato partidario, sus registros se encuentran en 2 tablas, 
             candidato y detallePartido, de deben eliminar primero los registros de
             detallepartido
             */
            if (tipo == 1) {
                String query = "delete from detallepartido where id_candidato = ?";
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, idCandidato);
                pst.executeUpdate();
            }
            //elimino el candidato de la base
            String query = "delete from candidato where id_candidato = ?";
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idCandidato);
            pst.executeUpdate();
            return true;
        } catch (Exception ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
            }
            //si  el candidato es partidario, se obtienen los datos de la tabla detallePartido
            if (can.getTipo() == 1) {
                query = "select p.id_partido from detallepartido dp inner join partido p on dp.id_partido = p.id_partido where dp.id_candidato = ?";
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, can.getIdCandidato());
                rs = pst.executeQuery();
                while (rs.next()) {
                    can.setIdPartido(rs.getInt("id_partido"));
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return can;
    }

    /*
     esta funcion retorna un arraylist con todos los candidatos que estan 
     registrados en el sistema.
     retorna los candidatos partidarios y los independientes
     los candidatos partidarios tienen su atributo tipo = 1,
     mientras que los independientes tienen su atributo tipo = 2;
     */
    public static ArrayList<Candidato> mostrarCandidatos() {
        ArrayList<Candidato> candidatos = new ArrayList();
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
                //si  el candidato es partidario, se obtienen los datos de la tabla detallePartido
                if (can.getTipo() == 1) {
                    query = "select p.id_partido from detallepartido dp inner join partido p on dp.id_partido = p.id_partido where dp.id_candidato = ?";
                    pst = con.getCnn().prepareStatement(query);
                    pst.setInt(1, can.getIdCandidato());
                    //usamos un segundo resultset para no sobreescribir al primero, que lleva la consulta
                    //principal
                    ResultSet rs2 = pst.executeQuery();
                    while (rs2.next()) {
                        can.setIdPartido(rs2.getInt("id_partido"));
                    }
                }
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
     retorna los candidatos partidarios y los independientes
     los candidatos partidarios tienen su atributo tipo = 1,
     mientras que los independientes tienen su atributo tipo = 2;
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
                //si  el candidato es partidario, se obtienen los datos de la tabla detallePartido
                if (can.getTipo() == 1) {
                    query = "select p.id_partido from detallepartido dp inner join partido p on dp.id_partido = p.id_partido where dp.id_candidato = ?";
                    pst = con.getCnn().prepareStatement(query);
                    pst.setInt(1, can.getIdCandidato());
                    //usamos un segundo resultset para no sobreescribir al primero, que lleva la consulta
                    //principal
                    ResultSet rs2 = pst.executeQuery();
                    while (rs2.next()) {
                        can.setIdPartido(rs2.getInt("id_partido"));
                    }
                }
                candidatos.add(can);
            }
        } catch (Exception ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidatos;
    }
    
    public static Candidato mostrarCandidatoDui(String dui) {
        String query = "select * from candidato where num_dui = ?";
        Candidato can = new Candidato();
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1,dui);
            rs = pst.executeQuery();
                        while (rs.next()) {
                can.setIdCandidato(rs.getInt("id_candidato"));
                can.setNumDui(rs.getString("num_dui"));
                can.setFoto(rs.getString("foto"));
                can.setTipo(rs.getInt("tipo"));
                can.setIdDepartamento(rs.getInt("id_departamento"));
            }
            //si  el candidato es partidario, se obtienen los datos de la tabla detallePartido
            if (can.getTipo() == 1) {
                query = "select p.id_partido from detallepartido dp inner join partido p on dp.id_partido = p.id_partido where dp.id_candidato = ?";
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, can.getIdCandidato());
                rs = pst.executeQuery();
                while (rs.next()) {
                    can.setIdPartido(rs.getInt("id_partido"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CandidatoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return can;
    }
}
