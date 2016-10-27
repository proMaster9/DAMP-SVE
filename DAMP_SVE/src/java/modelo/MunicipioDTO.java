
package modelo;

import conexion.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * EQUIPO DAMP-2016
 */

public class MunicipioDTO {

    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();

    public static ArrayList<Municipio> mostrarMunicipios() {
        String query = "select * from municipio";
        ArrayList<Municipio> mun = new ArrayList();
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                Municipio m = new Municipio();
                m.setIdMunicipio(rs.getInt("id_municipio"));
                m.setIdDepartmento(rs.getInt("id_departamento"));
                m.setNombreMunicipio(rs.getString("nombre_municipio"));
                mun.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MunicipioDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return mun;
    }

    public static Municipio mostrarUnMunicipio(int idMunicipio) {
        int id = idMunicipio;
        String query = "select * from municipio where id_municipio = " + id;
        Municipio m = new Municipio();
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                m.setIdMunicipio(rs.getInt("id_municipio"));
                m.setIdDepartmento(rs.getInt("id_departamento"));
                m.setNombreMunicipio(rs.getString("nombre_municipio"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(MunicipioDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return m;
    }

    public static ArrayList<Municipio> mostrarMunicipiosDep(int idDepartmento) {
        String query = "select * from municipio where id_departamento = ?";
        ArrayList<Municipio> mun = new ArrayList();
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idDepartmento);
            rs = pst.executeQuery();
            while (rs.next()) {
                Municipio m = new Municipio();
                m.setIdMunicipio(rs.getInt("id_municipio"));
                m.setIdDepartmento(rs.getInt("id_departamento"));
                m.setNombreMunicipio(rs.getString("nombre_municipio"));
                mun.add(m);
            }
        } catch (SQLException ex) {
            Logger.getLogger(MunicipioDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return mun;
    }

}
