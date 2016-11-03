/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import java.sql.*;
import java.util.ArrayList;
import conexion.Conexion;
import java.util.HashSet;
import java.util.Set;
import modelo.Pregunta;
/**
 *
 * @author roberto
 */
public class PreguntaDTO {
    PreparedStatement pst;
    ResultSet rs;
    private static final Conexion con = Conexion.conectar();
    
    public ArrayList<Pregunta> mostrar(){
    ArrayList<Pregunta> lista = new ArrayList<>();
    
    try{
    String consu = "select * from pregunta";
    pst = con.getCnn().prepareStatement(consu);
    rs = pst.executeQuery();
    
    while(rs.next()){
        Pregunta pre = new Pregunta();
        pre.setIdPregunta(rs.getInt("id_pregunta"));
        pre.setPregunta(rs.getString("pregunta"));
        lista.add(pre);
        
    
    }
    con.desconectar();    
    
}   catch(Exception e){
    System.err.println("error al mostrar publicidad "+e);
    }
return lista;
}

}
