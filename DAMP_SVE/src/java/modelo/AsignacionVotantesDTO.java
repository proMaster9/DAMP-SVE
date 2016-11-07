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
 * @author carlos
 */
public class AsignacionVotantesDTO {

    private static PreparedStatement pst;
    private static ResultSet rs;
    private static final Conexion con = Conexion.conectar();

    public AsignacionVotantesDTO() {
    }

    public static void asignarVotantes() {
        for (int dep = 1; dep <= 14; dep++) {
            String query = "select d.id_departamento, d.nombre_departamento,m.id_municipio,m.nombre_municipio, count(m.id_municipio) as \"Total Centros\", "
                    + "(select count(p.id_municipio) from padronelectoral p where p.id_municipio=m.id_municipio) as \"#Votantes\"  from departamento d "
                    + "inner join municipio m on d.id_departamento=m.id_departamento "
                    + "inner join centrovotacion c on c.id_municipio=m.id_municipio "
                    + "where d.id_departamento = " + dep
                    + "group by d.nombre_departamento,m.id_municipio, d.id_departamento";
            try {
                pst = con.getCnn().prepareStatement(query);
                rs = pst.executeQuery();
                int cont = 0;
                while (rs.next()) {
                    int acumPersonas = 0;
                    int votantes = rs.getInt("#Votantes");
                    int centros = rs.getInt("Total centros");
                    //peronas ara cada centro de votacion, tiene que ser division entera
                    int personasCentro = votantes / centros;
                    //las personas que son parte del residuo se asignan al primer centro de votacion
                    int residuoPersonas = votantes % centros;
                    PreparedStatement pst2;
                    ResultSet rs2;
                    //se filtran los centros de votacion por municipio
                    String queryCentro = "select * from centrovotacion where id_municipio = ?";
                    pst2 = con.getCnn().prepareStatement(queryCentro);
                    pst2.setInt(1, rs.getInt("id_municipio"));
                    System.out.println("");
                    rs2 = pst2.executeQuery();
                    int cont2 = 0;
                    //se recorre cada centro de votacion
                    while (rs2.next()) {
                        PreparedStatement pst3;
                        ResultSet rs3;
                        int contJrv = 0;
                        cont2++;
                        //las peronas asignadas son en igual cantidad para cada centro, normalmente si distribuimos
                        //en igual cantidades, quedara un sobrante de personas no asignadas, esas son agregadas al primer
                        //centro de votacion
                        int personasAsignadasCentro;
                        if (cont2 == 1) {
                            personasAsignadasCentro = personasCentro + residuoPersonas;
                        } else {
                            personasAsignadasCentro = personasCentro;
                        }
                        int personaPorJrv = personasAsignadasCentro / rs2.getInt("num_jrv_disponibles");
                        int residuoPersonaJrv = personasAsignadasCentro % rs2.getInt("num_jrv_disponibles");
                        //si el numero de personas es mejor al numero de jrv disponibles, las personas
                        //son asignadas a la primer jrv
                        String consultaPersona;
                        if (personaPorJrv > 0) {
                            for (int i = 0; i < rs2.getInt("num_jrv_disponibles"); i++) {
                                cont++;
                                contJrv++;
                                int idJrv = crearJrv(rs2.getInt("id_centro_votacion"), acronimo(rs.getInt("id_departamento")) + "-" + cont);
                                if (i == 0) {
                                    consultaPersona = "select * from padronelectoral where id_municipio = " + rs.getInt("id_municipio") + " limit " + (personaPorJrv + residuoPersonaJrv) + " offset " + acumPersonas;
                                    acumPersonas += (personaPorJrv + residuoPersonaJrv);
                                } else {
                                    consultaPersona = "select * from padronelectoral where id_municipio = " + rs.getInt("id_municipio") + " limit " + personaPorJrv + " offset " + acumPersonas;
                                    acumPersonas += personaPorJrv;
                                }
                                pst3 = con.getCnn().prepareStatement(consultaPersona);
                                rs3 = pst3.executeQuery();
                                //se asignan los ciudadanos a las jrv de los centros de votacion correspondientes
                                while (rs3.next()) {
                                    asignarPersona(rs3.getString("num_dui"), idJrv);
                                }
                            }
                        } else {
                            //cuando las personas son menores a las jrv asignadas, solo se utiliza una jrv en donde se
                            //asignan las pocas personas de ese centro
                            if (contJrv == 0) {
                                cont++;
                                contJrv++;
                                personaPorJrv = personasAsignadasCentro;
                            }
                            //creacion de una jrv
                            int idJrv = crearJrv(rs2.getInt("id_centro_votacion"), acronimo(rs.getInt("id_departamento")) + "-" + cont);
                            consultaPersona = "select * from padronelectoral where id_municipio = " + rs.getInt("id_municipio") + " limit " + personaPorJrv + " offset " + acumPersonas;
                            acumPersonas += personasAsignadasCentro;
                            pst3 = con.getCnn().prepareStatement(consultaPersona);
                            rs3 = pst3.executeQuery();
                            //se asignan los ciudadanos a las jrv de los centros de votacion correspondientes
                            while (rs3.next()) {
                                asignarPersona(rs3.getString("num_dui"), idJrv);
                            }
                        }
                    }
                }

            } catch (SQLException ex) {
                Logger.getLogger(AsignacionVotantesDTO.class
                        .getName()).log(Level.SEVERE, null, ex);
            } finally {
                con.desconectar();
            }
        }
    }

    //encargado de agregar las personas a las jrv correspondientes
    private static void asignarPersona(String dui, int idJrv) {
        PreparedStatement pst;
        String agregarCiudadano = "insert into votantePorJrv(num_dui,id_jrv,estado) values (?,?,0)";
        try {
            pst = con.getCnn().prepareStatement(agregarCiudadano);
            pst.setString(1, dui);
            pst.setInt(2, idJrv);
            pst.execute();
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionVotantesDTO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    //Se crea una jrv y se retorna su id autoincrementable
    private static int crearJrv(int idCentro, String acronimo) {
        String agregarJrv = "insert into jrv(id_centro_votacion,correlativo_jrv) values (?,?)";
        ResultSet id;
        int jrv = 0;
        try {
            //creamos una jrv
            pst = con.getCnn().prepareStatement(agregarJrv);
            pst.setInt(1, idCentro);
            pst.setString(2, acronimo);
            pst.execute();
            //obtenemos el id de la jrv creada
            String recuperarJrv = "select * from jrv order by id_jrv desc limit 1";
            pst = con.getCnn().prepareStatement(recuperarJrv);
            id = pst.executeQuery();
            while (id.next()) {
                jrv = id.getInt("id_jrv");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionVotantesDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jrv;
    }

    private static String acronimo(int id) {
        //ahuachapan, santa ana, sonsonate, chalatenango, cuscatlan, san salvador
        //la libertad, san vicente, cabañas, la paz, usulutan, san miguel, morazan, la union
        String[] dep = {"AH", "SA", "SO", "CH", "CT", "SS", "LL", "SV", "CB", "LP", "US", "SM", "MZ", "LU"};
        return dep[id];
    }

    //muestra una vista general de las asignaciones de personas a los centros de votacion
    //cuando el parametro es 0 muestra las asignaciones de todos los departamentos
    //cuando el parametro esta entre 1 y 14, muestra las asignaciones por departamento
    public static ArrayList<AsignacionCentro> mostrarAsignacion(int dpto) {
        ArrayList<AsignacionCentro> asignacion = new ArrayList();
        String query = "select d.id_departamento, d.nombre_departamento,m.id_municipio,m.nombre_municipio, count(m.id_municipio) as \"Total Centros\", \n"
                + "(select count(p.id_municipio) from padronelectoral p where p.id_municipio=m.id_municipio) as \"#Votantes\"  from departamento d \n"
                + "inner join municipio m on d.id_departamento=m.id_departamento \n"
                + "inner join centrovotacion c on c.id_municipio=m.id_municipio ";

        //busqueda por departamento
        if (dpto >= 1 && dpto <= 14) {
            query += " where d.id_departamento = " + dpto;
        }
        query += "group by d.nombre_departamento,m.id_municipio, d.id_departamento";
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                AsignacionCentro a = new AsignacionCentro();
                a.setDepartamento(rs.getString("nombre_departamento"));
                a.setMunicipio(rs.getString("nombre_municipio"));
                a.setTotalCentros(rs.getInt("Total Centros"));
                a.setTotalVotantes(rs.getInt("#Votantes"));
                a.setVotantesPorCentro(a.getTotalVotantes() / a.getTotalCentros());
                asignacion.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionVotantesDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return asignacion;
    }

    //mostrar en mas detalle la asignacion de los ciudadanos a los centros
    //la consulta se puede filtrar por departamento, municipio y centro de votacion
    //cuando se filtra por departamento, se ingresa un numero del 1 al 14 en el parametro 1, los demas de dejan a 0
    //cuando se filtra por municipio, se ingresa un numero del 1 al 262, los demas parametros se dejan a 0
    //cuando se filtra por centro, se ingresa el id del centro en el tercer parametro, los demas parametros se dejan a 0
    public static ArrayList<DetalleAsignacion> mostrarDetalleAsignacion(int dpto, int mun, int idCentro, String dui) {
        ArrayList<DetalleAsignacion> detalle = new ArrayList();
        String query = "select d.nombre_departamento, m.nombre_municipio, cv.nombre_centro, cv.id_centro_votacion, j.correlativo_jrv, p.num_dui, p.apellido || ' ,' || p.nombre as ciudadano from padronelectoral p \n"
                + "inner join votantePorJrv v on p.num_dui = v.num_dui\n"
                + "inner join jrv j on j.id_jrv = v.id_jrv\n"
                + "inner join centrovotacion cv on cv.id_centro_votacion =j.id_centro_votacion\n"
                + "inner join municipio m on m.id_municipio = p.id_municipio\n"
                + "inner join departamento d on d.id_departamento = m.id_departamento ";
        //se verifica que sea un departamento valido
        if (dpto >= 1 && dpto <= 14) {
            query += "where d.id_departamento = " + dpto;
        } //se verifica que sea un municipio valido
        else if (mun >= 1 && mun <= 262) {
            query += "where m.id_municipio = " + mun;
        } else if (idCentro != 0) {
            query += "where cv.id_centro_votacion = " + idCentro;
        } else if (!dui.equals("")) {
            query += "where v.num_dui = '" + dui + "'";
        }
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                DetalleAsignacion dt = new DetalleAsignacion();
                dt.setDepartamento(rs.getString("nombre_departamento"));
                dt.setMunicipio(rs.getString("nombre_municipio"));
                dt.setCentro(rs.getString("nombre_centro"));
                dt.setCorrelativo(rs.getString("correlativo_jrv"));
                dt.setIdCentro(rs.getInt("id_centro_votacion"));
                dt.setCiudadano(rs.getString("num_dui") + ", " + rs.getString("ciudadano"));
                detalle.add(dt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AsignacionVotantesDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detalle;
    }

    public static void main(String[] args) {
        //asignarVotantes();
        /*
         for (AsignacionCentro a : mostrarAsignacion(0)) {
         System.out.println("Departamento: " + a.getDepartamento());
         System.out.println("Municipio: " + a.getMunicipio());
         System.out.println("Total centros: " + a.getTotalCentros());
         System.out.println("#Votantes: " + a.getTotalVotantes());
         System.out.println("Votantes por centro: " + a.getVotantesPorCentro());
         }
         */
        /*
         for(DetalleAsignacion dt:mostrarDetalleAsignacion(0,0,0,"00000445-0")) {
         System.out.println("Departamento: " + dt.getDepartamento());
         System.out.println("Municipio: " + dt.getMunicipio());
         System.out.println("Centro: " + dt.getCentro());
         System.out.println("Correlativo: "+ dt.getCorrelativo());
         System.out.println("Ciudadano: " + dt.getCiudadano());
         System.out.println("-------------------");
         }
         */
    }
}
