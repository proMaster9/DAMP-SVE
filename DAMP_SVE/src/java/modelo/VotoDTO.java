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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public class VotoDTO {

    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();

    public static boolean agregarVotoBandera(VotoBandera votoBandera) {
        String query1 = "update votantePorJrv set estado=? where num_dui=?";
        try {
            //actualizamos el registro del usuario, para marca que ya voto
            pst = con.getCnn().prepareStatement(query1);
            pst.setInt(1, 1);
            pst.setString(2, CiudadanoDTO.mostrarUsuario(votoBandera.getIdUsuario()).getNumDui());
            pst.execute();
            //obtenemos la jrv asignada al ciudadano
            String query2 = "select * from votantePorJrv where num_dui=?";
            pst = con.getCnn().prepareStatement(query2);
            pst.setString(1, CiudadanoDTO.mostrarUsuario(votoBandera.getIdUsuario()).getNumDui());
            rs = pst.executeQuery();
            int idJrv = 0;
            while (rs.next()) {
                idJrv = rs.getInt("id_jrv");
            }
            //insertamos un registro en la tabla voto
            String query3 = "insert into voto(id_jrv) values (" + idJrv + ")";
            pst = con.getCnn().prepareStatement(query3);
            pst.execute();
            //recuperamos el id de voto que se acaba de generar
            String query4 = "select * from voto order by id_voto desc limit 1";
            pst = con.getCnn().prepareStatement(query4);
            rs = pst.executeQuery();
            int idVoto = 0;
            while (rs.next()) {
                idVoto = rs.getInt("id_voto");
            }
            //obtener la candidad de candidatos del partido seleccionado de este departamento
            String query5 = "select count(id_partido) as votos from candidato c "
                    + "inner join detallepartido dp on dp.id_candidato = c.id_candidato "
                    + "where dp.id_partido = ? and c.id_departamento = ?";
            pst = con.getCnn().prepareStatement(query5);
            pst.setInt(1, votoBandera.getIdPartido());
            pst.setInt(2, CiudadanoDTO.mostrarUsuario(votoBandera.getIdUsuario()).getIdDepartamento());
            rs = pst.executeQuery();
            double totalCandidatos = 0;
            while (rs.next()) {
                totalCandidatos = rs.getDouble("votos");
            }
            //calcular porcentaje para cada candidato
            double porcentaje = 1 / totalCandidatos;
            //se realiza un voto para cada candidato del partido de ese departamento
            String query6 = "select * from candidato c "
                    + "inner join detallepartido dp on dp.id_candidato = c.id_candidato "
                    + "where dp.id_partido = ? and c.id_departamento = ?";
            pst = con.getCnn().prepareStatement(query6);
            pst.setInt(1, votoBandera.getIdPartido());
            pst.setInt(2, CiudadanoDTO.mostrarUsuario(votoBandera.getIdUsuario()).getIdDepartamento());
            rs = pst.executeQuery();
            //ingreso el detalle del voto en la tabla
            String query7 = "";
            while (rs.next()) {
                query7 = "insert into votomarca (id_voto,id_candidato,valor_porcentual) values (?,?,?)";
                pst = con.getCnn().prepareStatement(query7);
                pst.setInt(1, idVoto);
                pst.setInt(2, rs.getInt("id_candidato"));
                pst.setDouble(3, porcentaje);
                pst.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VotoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean agregarVotoMarca(int idUsuario, String candidatos) {
        String query1 = "update votantePorJrv set estado=? where num_dui=?";
        try {
            //actualizamos el registro del usuario, para marca que ya voto
            pst = con.getCnn().prepareStatement(query1);
            pst.setInt(1, 1);
            pst.setString(2, CiudadanoDTO.mostrarUsuario(idUsuario).getNumDui());
            pst.execute();
            //obtenemos la jrv asignada al ciudadano
            String query2 = "select * from votantePorJrv where num_dui=?";
            pst = con.getCnn().prepareStatement(query2);
            pst.setString(1, CiudadanoDTO.mostrarUsuario(idUsuario).getNumDui());
            rs = pst.executeQuery();
            int idJrv = 0;
            while (rs.next()) {
                idJrv = rs.getInt("id_jrv");
            }
            //insertamos un registro en la tabla voto
            String query3 = "insert into voto(id_jrv) values (" + idJrv + ")";
            pst = con.getCnn().prepareStatement(query3);
            pst.execute();
            //recuperamos el id de voto que se acaba de generar
            String query4 = "select * from voto order by id_voto desc limit 1";
            pst = con.getCnn().prepareStatement(query4);
            rs = pst.executeQuery();
            int idVoto = 0;
            while (rs.next()) {
                idVoto = rs.getInt("id_voto");
            }
            //calculamos el porcentaje para cada candidato
            double numeroCandidatos = 0;
            StringTokenizer tokens = new StringTokenizer(candidatos);
            while (tokens.hasMoreTokens()) {
                numeroCandidatos++;
                tokens.nextToken();
            }
            double porcentaje = 1 / numeroCandidatos;
            //ingresamos el detalle del voto en la tabla
            String query5 = "";
            StringTokenizer idCandidato = new StringTokenizer(candidatos);
            while (idCandidato.hasMoreTokens()) {
                query5 = "insert into votomarca (id_voto,id_candidato,valor_porcentual) values (?,?,?)";
                pst = con.getCnn().prepareStatement(query5);
                pst.setInt(1, idVoto);
                pst.setInt(2, Integer.parseInt(idCandidato.nextToken()));
                pst.setDouble(3, porcentaje);
                pst.executeUpdate();
            }
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VotoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static boolean agregarVotoAbstenido(int idUsuario) {
        String query1 = "update votantePorJrv set estado=? where num_dui=?";
        try {
            //actualizamos el registro del usuario, para marcar que ya voto
            pst = con.getCnn().prepareStatement(query1);
            pst.setInt(1, 1);
            pst.setString(2, CiudadanoDTO.mostrarUsuario(idUsuario).getNumDui());
            pst.execute();
            //obtenemos la jrv asignada al ciudadano
            String query2 = "select * from votantePorJrv where num_dui=?";
            pst = con.getCnn().prepareStatement(query2);
            pst.setString(1, CiudadanoDTO.mostrarUsuario(idUsuario).getNumDui());
            rs = pst.executeQuery();
            int idJrv = 0;
            while (rs.next()) {
                idJrv = rs.getInt("id_jrv");
            }
            //insertamos un registro en la tabla voto
            String query3 = "insert into voto(id_jrv) values (" + idJrv + ")";
            pst = con.getCnn().prepareStatement(query3);
            pst.execute();
            //recuperamos el id de voto que se acaba de generar
            String query4 = "select * from voto order by id_voto desc limit 1";
            pst = con.getCnn().prepareStatement(query4);
            rs = pst.executeQuery();
            int idVoto = 0;
            while (rs.next()) {
                idVoto = rs.getInt("id_voto");
            }
            //insertar el voto abstenido
            String query5 = "insert into votoAbstenido (id_voto) values (?)";
            pst = con.getCnn().prepareStatement(query5);
            pst.setInt(1,idVoto);
            pst.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(VotoDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    public static Jrv jrvVotante(int idUsuario) {
        Jrv jrv = new Jrv();
        String query = " select * from votantePorJrv v "
                + "inner join jrv j on j.id_jrv = v.id_jrv where v.num_dui = ?";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, CiudadanoDTO.mostrarUsuario(idUsuario).getNumDui());
            rs = pst.executeQuery();
            while (rs.next()) {
                jrv.setIdJrv(rs.getInt("id_jrv"));
                jrv.setCorrelativoJrv(rs.getString("correlativo_jrv"));
                jrv.setIdCentroVotacion(rs.getInt("id_centro_votacion"));
                jrv.setIdJrv(rs.getInt("id_jrv"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VotoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jrv;
    }

    public static int estadoVotante(int idUsuario) {
        String query = "select * from votantePorJrv where num_dui = ?";
        int estado = 0;
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, CiudadanoDTO.mostrarUsuario(idUsuario).getNumDui());
            rs = pst.executeQuery();
            while(rs.next()) {
                estado = rs.getInt("estado");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VotoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        return estado;
    }
    public static void main(String[] args) {

    }
}
