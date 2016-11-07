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
public class DepartamentoDTO {
    
    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();
    
    public DepartamentoDTO() {
    }
    
    public static ArrayList<Departamento> mostrarDepartamentos() {
        String query = "select * from departamento";
        ArrayList<Departamento> dep = new ArrayList(); 
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()) {
                Departamento d = new Departamento();
                d.setIdDepartamento(rs.getInt("id_departamento"));
                d.setDepartamento(rs.getString("nombre_departamento"));
                dep.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return dep;
    }
    
     public static Departamento mostrarDepartamento(int idDepartamento) {
        String query = "select * from departamento where id_departamento = " + idDepartamento;
        Departamento d = new Departamento();
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()) {
                d.setIdDepartamento(rs.getInt("id_departamento"));
                d.setDepartamento(rs.getString("nombre_departamento"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DepartamentoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return d;
    }

}
