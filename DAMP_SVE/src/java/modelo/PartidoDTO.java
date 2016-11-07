package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import static modelo.CentroVotacionDTO.mostrarCentro;

public class PartidoDTO {

    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();

    /*
    la funcion recibe como parametro un objeto tipo partido, del cual se extrae el valor
    de sus atributos y se hace una insercion en la tabla partido.
    ademas de eso, se actualiza la informacion del representante del partido utilizando
    la funcion agregarUsuario de la clase  CiudadanoDTO
    cuando el proceso se ejecuta correctamente se retorna verdadero, en caso contrario 
    se retorna false
    */
    public static boolean agregarPartido(Partido p) {
        String query = "insert into partido (nombre, acronimo, num_dui, imagen)values (?,?,?,?)";
        try {
            //se inserta la informacion del partido
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, p.getNombre());
            pst.setString(2, p.getAcronimo());
            pst.setString(3, p.getNumDui());
            pst.setString(4, p.getImagen());
            pst.executeUpdate();
            //se actualiza la infomacion del representante del partido
            Ciudadano c = new Ciudadano();
            c.setNumDui(p.getNumDui());
            c.setContrasenia("12345");
            c.setTipoUsuario(5); //5 es representante de partido
            if (CiudadanoDTO.agregarUsuario(c)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return false;

        } finally {
            con.desconectar();
        }
    }
    
    /*
    la funcion recibe como parametro un objeto tipo Partido de donde
    se extrae el valor de sus atributos para poder hacer una actualizacion,
    aca el objeto que se pasa por parametros obligatoriamente debe llevar un 
    valor en un atributo idPartido para poder hacer la operacion.
    cuando no hay errores se retorna verdadero, en caso contrario 
    la funcion retorna falso.
    tambien se quita el privilegio al antiguo director de centro de votaciones,
    y se le da ese privilegio al nuevo director de centro de votaciones
    */
    public static boolean modificarPartido(Partido p) {
        String query = "update partido set nombre=?, acronimo=?, num_dui=?, imagen=?  where id_partido=?";
        try {
            //quitando privilegios a ex representante de partido
            Ciudadano exrepresentante = CiudadanoDTO.mostrarVotante(mostrarPartido(p.getIdPartido()).getNumDui());
            exrepresentante.setTipoUsuario(11);
            CiudadanoDTO.agregarUsuario(exrepresentante);
            
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, p.getNombre());
            pst.setString(2, p.getAcronimo());
            pst.setString(3, p.getNumDui());
            pst.setString(4, p.getImagen());
            pst.setInt(5, p.getIdPartido());
            pst.executeUpdate();

            //dando privilegios al nuevo representante de partido
            Ciudadano representante = CiudadanoDTO.mostrarVotante(p.getNumDui());
            representante.setTipoUsuario(5);
            CiudadanoDTO.agregarUsuario(representante);
        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return false;

        } finally {
            con.desconectar();
        }
        return true;
    }

    /*
    se recibe como parametro entero el id del partido, que se usa para
    poder eliminarlo de la base de datos. cuando todo es correcto se
    retorna verdadero, en caso contrario se retorna falso
    */
    public static boolean eliminarPartido(int idPartido) {
        String query = "delete from partido where id_partido=?";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idPartido);
            pst.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return false;

        } finally {
            con.desconectar();
        }
        return true;
    }

    /*
    se recibe como parametro el id del partido, que es un entero, para
    que se haga una busqueda y se retorne un objeto Partido con la
    informacion de dicho partido, en caso de no encontrar resultados
    se retorna un objeto vacio
    */
    public static Partido mostrarPartido(int idPartido) {
        String query = "select * from partido where id_partido=?";
        Partido par = new Partido();
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idPartido);
            rs = pst.executeQuery();
            while (rs.next()) {
                par.setIdPartido(rs.getInt("id_partido"));
                par.setNombre(rs.getString("nombre"));
                par.setAcronimo(rs.getString("acronimo"));
                par.setNumDui(rs.getString("num_dui"));
                par.setImagen(rs.getString("imagen"));
            }
        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
        }
        return par;
    }

    /*
    se retorna un arraylist con todos los partidos registrados
    */
    public static ArrayList<Partido> mostrarPartidos() {
        ArrayList<Partido> partido = new ArrayList<Partido>();
        String query = "select * from partido";

        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                Partido par = new Partido();
                par.setIdPartido(rs.getInt("id_partido"));
                par.setNombre(rs.getString("nombre"));
                par.setAcronimo(rs.getString("acronimo"));
                par.setNumDui(rs.getString("num_dui"));
                par.setImagen(rs.getString("imagen"));
                partido.add(par);
            }
        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
        }
        return partido;
    }


}
