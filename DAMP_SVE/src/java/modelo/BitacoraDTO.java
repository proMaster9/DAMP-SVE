/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.util.ArrayList;
import java.sql.*;
import conexion.Conexion;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Bitacora;

/**
 *
 * @author roberto
 */
public class BitacoraDTO {
    private static PreparedStatement pst;
    private static ResultSet rs;
     private static final Conexion con = Conexion.conectar();
    
    public static boolean agregarBitacora(Bitacora b){
        String query = "select agregarBitacora(?,?,?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, b.getAccion());
            pst.setString(2, b.getMagistrado1());
            pst.setString(3, b.getMagistrado2());
            pst.setString(4, b.getMagistrado3());
            pst.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(BitacoraDTO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public static ArrayList<Bitacora> mostrarBitacoras() {
        String query = "select * from bitacoraacciones";
        ArrayList<String> magistrado = new ArrayList();
        ArrayList<Bitacora> bitacora = new ArrayList();
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while(rs.next()) {
                Bitacora b = new Bitacora();
                b.setIdBitacora(rs.getInt("id_bitacora"));
                b.setFecha(rs.getString("fecha"));
                b.setHora(rs.getString("hora"));
                b.setAccion(rs.getString("accion"));
                //obtengo los magistrados que aparecen en esta bitacora
                String magistrados = "select * from detallebitacora where id_bitacora = "+b.getIdBitacora();
                pst = con.getCnn().prepareStatement(magistrados);
                rs = pst.executeQuery();
                while(rs.next()) {
                    magistrado.add(rs.getString("num_dui"));
                }
                b.setMagistrado1(magistrado.get(0));
                b.setMagistrado2(magistrado.get(1));
                b.setMagistrado3(magistrado.get(2));
                bitacora.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BitacoraDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bitacora;
    }
    
}
