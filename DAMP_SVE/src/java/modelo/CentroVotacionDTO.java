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
 * @author JMendez
 *
 * DUDA ARRAY---------
 */
public class CentroVotacionDTO {

    static PreparedStatement ps;
    static ResultSet rs;
    private static final Conexion c = Conexion.conectar();

    /*
    se recibe como parametro un objeto CentroVotacion, en el que se 
    extraen el valor de sus atributos, y se ingresa el centro en la base
    se retorna true si se ingreso correctamente y false si hubo algun error
    */
    public static boolean agregarCentro(CentroVotacion obj) {

        String consulta = "insert into centrovotacion(id_municipio,direccion_especifica,num_dui,nombre_centro,num_jrv_disponibles) values(?,?,?,?,?)";
        try {
            ps = c.getCnn().prepareStatement(consulta);
            ps.setInt(1, obj.getIdMunicipio());
            ps.setString(2, obj.getDireccion());
            ps.setString(3, obj.getNumDui());
            ps.setString(4, obj.getNombreCentro());
            ps.setInt(5, obj.getNumJrvDisponible());
            ps.executeUpdate();
            Ciudadano ciudadano = new Ciudadano();
            ciudadano.setNumDui(obj.getNumDui());
            ciudadano.setContrasenia("12345");
            ciudadano.setTipoUsuario(8);
            //se actualiza la informacion del director de centro de votacion
            if (CiudadanoDTO.agregarUsuario(ciudadano)) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return false;

        } finally {
            c.desconectar();
        }
    }
    
    /*
    se modifica un centro de votacion mediante el objeto que se recibe como parametro.
    estos objetos pór obligacion tienen que llevar valor en su clave primaria
    cuando se modifica el centro de votacion, se da de baja al antiguo director
    de centro de votacion, quitandole sus privilegios para entrar, y se le
    otorgan permisos de acceso al nuevo director del centro de votacion
    */
    public static boolean modificarCentro(CentroVotacion obj) {

        String consulta = "update centrovotacion set id_municipio=?, direccion_especifica=?, num_dui=?, nombre_centro=?, num_jrv_disponibles=? where id_centro_votacion=?";

        try {
            //dando de baja al antiguo director del centro
            Ciudadano exdirector = CiudadanoDTO.mostrarVotante(mostrarCentro(obj.getIdCentroVotacion()).getNumDui());
            exdirector.setTipoUsuario(11);
            CiudadanoDTO.agregarUsuario(exdirector);
            
            ps = c.getCnn().prepareStatement(consulta);
            ps.setInt(1, obj.getIdMunicipio());
            ps.setString(2, obj.getDireccion());
            ps.setString(3, obj.getNumDui());
            ps.setString(4, obj.getNombreCentro());
            ps.setInt(5, obj.getNumJrvDisponible());
            ps.setInt(6, obj.getIdCentroVotacion());
            ps.executeUpdate();
            
            //modificando la informacion del nuevo director del centro
            Ciudadano director = CiudadanoDTO.mostrarVotante(obj.getNumDui());
            director.setTipoUsuario(8);
            CiudadanoDTO.agregarUsuario(director);
        } catch (Exception e) {
             Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            c.desconectar();
        }

        return true;
    }

    /*
    se elimina el centro de votacion con el id que se recibe como parametro
    tambien se retiran los provilegios del director del centro de votacion eliminado
    se retorna true cuando se elimina correctamento, y false cuando hay un error
    */
    public static boolean eliminarCentro(int idCentro) {

        String consulta = "delete from centrovotacion where id_centro_votacion=?";

        try {
            //dando de baja al antiguo director del centro
            Ciudadano exdirector = CiudadanoDTO.mostrarVotante(mostrarCentro(idCentro).getNumDui());
            exdirector.setTipoUsuario(11);
            CiudadanoDTO.agregarUsuario(exdirector);
            
            ps = c.getCnn().prepareStatement(consulta);
            ps.setInt(1, idCentro);
            ps.executeUpdate();

        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return false;
        } finally {
            c.desconectar();
        }

        return true;
    }

    public static ArrayList<CentroVotacion> mostrarCentros() {

        ArrayList<CentroVotacion> centrovotacion = new ArrayList<>();
        String consulta = "select * from centrovotacion";
        try {
            ps = c.getCnn().prepareStatement(consulta);
            rs = ps.executeQuery();

            while (rs.next()) {
                CentroVotacion v = new CentroVotacion();

                v.setIdCentroVotacion(rs.getInt("id_centro_votacion"));
                v.setIdMunicipio(rs.getInt("id_municipio"));
                v.setDireccion(rs.getString("direccion_especifica"));
                v.setNumDui(rs.getString("num_dui"));
                v.setNombreCentro(rs.getString("nombre_centro"));
                v.setNumJrvDisponible(rs.getInt("num_jrv_disponibles"));

                centrovotacion.add(v);
            }
            return centrovotacion;

        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return centrovotacion;
        }
    }

    public static ArrayList<CentroVotacion> mostrarCentrosMun(int idMunicipio) {

        ArrayList<CentroVotacion> cv = new ArrayList<>();
        String consulta = "select * from centrovotacion where id_municipio = ?";

        try {
            ps = c.getCnn().prepareStatement(consulta);
            ps.setInt(1, idMunicipio);
            rs = ps.executeQuery();

            while (rs.next()) {
                CentroVotacion v = new CentroVotacion();
                v.setIdCentroVotacion(rs.getInt("id_centro_votacion"));
                v.setIdMunicipio(rs.getInt("id_municipio"));
                v.setDireccion(rs.getString("direccion_especifica"));
                v.setNumDui(rs.getString("num_dui"));
                v.setNombreCentro(rs.getString("nombre_centro"));
                v.setNumJrvDisponible(rs.getInt("num_jrv_disponibles"));

                cv.add(v);
            }
            return cv;

        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return cv;
        }
    }

    public static ArrayList<CentroVotacion> mostrarCentrosDep(int idDepartamento) {

        ArrayList<CentroVotacion> cv = new ArrayList<>();
        String consulta = "select * from centrovotacion where id_municipio in (select distinct id_municipio from municipio where id_departamento = ?)";
        try {
            ps = c.getCnn().prepareStatement(consulta);
            ps.setInt(1, idDepartamento);
            rs = ps.executeQuery();
            while (rs.next()) {
                CentroVotacion v = new CentroVotacion();

                v.setIdCentroVotacion(rs.getInt("id_centro_votacion"));
                v.setIdMunicipio(rs.getInt("id_municipio"));
                v.setDireccion(rs.getString("direccion_especifica"));
                v.setNumDui(rs.getString("num_dui"));
                v.setNombreCentro(rs.getString("nombre_centro"));
                v.setNumJrvDisponible(rs.getInt("num_jrv_disponibles"));
                cv.add(v);
            }
            return cv;

        } catch (Exception e) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, e);
            return cv;
        }
    }

    public static CentroVotacion mostrarCentro(int idCentro) {
        CentroVotacion v = new CentroVotacion();
        String query = "select * from centrovotacion where id_centro_votacion = ?";
        try {
            ps = c.getCnn().prepareStatement(query);
            ps.setInt(1, idCentro);
            rs = ps.executeQuery();
            while (rs.next()) {
                v.setIdCentroVotacion(rs.getInt("id_centro_votacion"));
                v.setIdMunicipio(rs.getInt("id_municipio"));
                v.setDireccion(rs.getString("direccion_especifica"));
                v.setNumDui(rs.getString("num_dui"));
                v.setNombreCentro(rs.getString("nombre_centro"));
                v.setNumJrvDisponible(rs.getInt("num_jrv_disponibles"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CentroVotacionDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v;
    }

    public static void main(String[] args) {

        //CentroVotacion centro = mostrarCentro(24);
        /*
         if(centro.getIdCentroVotacion() != 0) {
         System.out.println("id centro votacion "+centro.getIdCentroVotacion());
         System.out.println("id Municipio: "+centro.getIdMunicipio());
         System.out.println("direccion "+centro.getDireccion());
         System.out.println("num dui"+centro.getNumDui());
         System.out.println("nombre centro "+centro.getDireccion());
         System.out.println("Jrv Disp "+centro.getNumJrvDisponible());    
         System.out.println();   
         } else {
         System.out.println("Centro no encontrado");
         }
         */
        /*
         //e.setIdCentroVotacion(24);
         e.setIdMunicipio(5);
         e.setDireccion("Colonia soyapango city");
         e.setNumDui("00000008-0");
         e.setNombreCentro("Col. Soya II");
         e.setNumJrvDisponible(12);
              
         if (agregarCentro(e)) {
         System.out.println("SI");
         }else{
         System.out.println("NO");
         }
         */

        /*--- Modificar */
        /*
        CentroVotacion e = new CentroVotacion();
        e.setIdCentroVotacion(24);
        e.setIdMunicipio(5);
        e.setDireccion("Col. Escalon redon. masferrer");
        e.setNumDui("00000010-0");
        e.setNombreCentro("Com. El Carm");
        e.setNumJrvDisponible(80);

        if (modificarCentro(e)) {
            System.out.println("SI");

        } else {
            System.out.println("NO");
        }
        */
        /* eliminar

         if (eliminarCentro(4)) {
         System.out.println("SI");
         }else{
         System.out.println("NO");
         }
         */
        /*Mostrar
         ArrayList<CentroVotacion> centro=mostrarCentros();
        
         if(centro.size()>0){
            
         for (int i = 0; i < centro.size(); i++) {
         System.out.println("id centro votacion "+centro.get(i).getIdCentroVotacion());
         System.out.println("id Municipio: "+centro.get(i).getIdMunicipio());
         System.out.println("direccion "+centro.get(i).getDireccion());
         System.out.println("num dui"+centro.get(i).getNumDui());
         System.out.println("nombre centro "+centro.get(i).getDireccion());
         System.out.println("Jrv Disp "+centro.get(i).getNumJrvDisponible());    
         System.out.println();                
         }
         }else{
         System.out.println("No hay centros ");
         }        
         */
    }

}
