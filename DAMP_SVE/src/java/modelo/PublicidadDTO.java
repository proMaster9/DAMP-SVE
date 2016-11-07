/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;
import modelo.Publicidad;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
/**
 *
 * @author roberto
 */
public class PublicidadDTO {
    PreparedStatement ps;
    ResultSet rs;
    private static final Conexion con = Conexion.conectar();
    
    public  boolean agregarPublicidad(Publicidad pub){
        String consu = "insert into publicidad(id_publicidad , descripcion , codigo , id_usuario) "
                + "values(?,?,?,?)";
        
        try{
            ps = con.getCnn().prepareStatement(consu);
            ps.setInt(1, pub.getIdPublicidad());
            ps.setString(2, pub.getDescripcion());
            ps.setString(3, pub.getCodigo());
            ps.setInt(4,pub.getIdUsuario());
            ps.execute();
            System.out.println("Agregar exitoso");
            
        
        
        }catch(Exception e){
            System.err.println("error al ingresar publicidad "+e);
            return false;
        }
    
        return true;
    }
    
    
    public  boolean modificarPublicidad(Publicidad pub){
        String consu = "alter table publicidad set "
                + "descripcion =? , "
                + "codigo =? , "
                + "id_usuario =?"
                + "where id_publicidad =?";
        try{
            ps = con.getCnn().prepareStatement(consu);
            ps.setString(2, pub.getDescripcion());
            ps.setString(3, pub.getCodigo());
            ps.setInt(4, pub.getIdUsuario());
            
            ps.setInt(1, pub.getIdPublicidad());
            ps.execute();
             System.out.println("Modificar exitoso");
            
        }catch(Exception e){
        System.err.println("error al modificar publicidad "+e);
            return false;
        
        }
        return true;
    }


    public boolean eliminarPublicidad(Publicidad pub){
        String consu = "delete from publicidad where id_publicidad =? ";
        
        try{
            ps = con.getCnn().prepareStatement(consu);
            ps.setInt(1, pub.getIdPublicidad());
            ps.execute();
             System.out.println("Eliminar exitoso");
             
        
        }catch(Exception e){
            System.err.println("error al eliminar publicidad "+e);
            return false;
        
        }
    return true;
    }
    
    
public ArrayList<Publicidad> mostrar (){
ArrayList<Publicidad> lista = new ArrayList<>();
    
try{
    String consu = "select * from publicidad";
    ps= con.getCnn().prepareStatement(consu);
    rs = ps.executeQuery();
    
    while(rs.next()){
      Publicidad pub = new Publicidad();
      
      pub.setIdPublicidad(rs.getInt("id_publicidad"));
      pub.setDescripcion(rs.getString("descripcion"));
      pub.setCodigo(rs.getString("codigo"));
      pub.setIdUsuario(rs.getInt("id_usuario"));
    
    lista.add(pub);
    }
    con.desconectar();
    
}catch(Exception e){
System.err.println("error al mostrar publicidad "+e);

}

return lista;
}
 
public Publicidad mostrarAnuncio(int idpublicidad){
//falta trabajar esta parte de aca
    
return null;
}

}
