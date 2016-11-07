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
 * @author NACHO
 */
public class CiudadanoDTO {

    static PreparedStatement pst;
    static ResultSet rs;
    private static final Conexion con = Conexion.conectar();

    /*
     inicio de sesion del administrador, recibe como parametros el usuarios
     y la contrasenia, esta funcion retorna un objeto Ciudadano.
     en caso de que las credenciales sean correctas el objeto retornado
     contiene los datos basicos del admi, para que puedan ser manipulados
     mediante sesiones segun sea la necesidad
     */
    public static Ciudadano entrarAdmi(String credencial, String contrasenia) {
        Ciudadano c = new Ciudadano();
        String query = "select * from entrarAdministrador(?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, credencial);
            pst.setString(2, contrasenia);
            rs = pst.executeQuery();
            while (rs.next()) {
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNumDui(rs.getString("num_dui"));
                c.setContrasenia(rs.getString("contrasenia"));
                c.setConfirmacion(rs.getInt("confirmacion"));
                c.setTipoUsuario(rs.getInt("tipo_usuario"));
                c.setRol(rs.getString("tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return c;
    }

    /*
     esta funcion es utilizada por los usuarios principales del sistema que son
     los magistrados, usuario del cnr y director del tse.
     recibe como parametros las credenciales de usuario y retorna un objeto tipo
     Ciudadano con la informacion del usuario en caso de que las credenciales sean
     correctas, en caso contrario retorna un objeto vacio.
     son usuarios principales porque no necesitan aparecer en la tabla con los
     datos proporcionados del cnr para poder tener una cuenta en el sistema
     */
    public static Ciudadano entrarPrincipal(String credencial, String contrasenia) {
        Ciudadano c = new Ciudadano();
        String query = "select * from entrarPrincipal(?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, credencial);
            pst.setString(2, contrasenia);
            rs = pst.executeQuery();
            while (rs.next()) {
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNumDui(rs.getString("num_dui"));
                c.setContrasenia(rs.getString("contrasenia"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setConfirmacion(rs.getInt("confirmacion"));
                c.setTipoUsuario(rs.getInt("tipo_usuario"));
                c.setRol(rs.getString("tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return c;
    }

    /*
     esta funcion es usada por los usuarios secundarios del sistema, que son
     el representante de partido, director de centro de votaciones, publicista,
     presidente de jrv y gestor de jrv.
     recibe como parametro las credenciales de usuario y retorna un objeto Ciudadano
     con los datos del usuario en caso de que las credenciales sean correctas,
     en caso contrario retorna un objeto vacio.
     son usuarios secundarios ya que para que posean una cuenta es necesario que
     aparezcan en los registros que el cnr nos proporciona
     */
    public static Ciudadano entrarSecundario(String credencial, String contrasenia) {
        Ciudadano c = new Ciudadano();
        String query = "select * from entrarSecundario(?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, credencial);
            pst.setString(2, contrasenia);
            rs = pst.executeQuery();
            while (rs.next()) {
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNumDui(rs.getString("num_dui"));
                c.setContrasenia(rs.getString("contrasenia"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setConfirmacion(rs.getInt("confirmacion"));
                c.setTipoUsuario(rs.getInt("tipo_usuario"));
                c.setIdMunicipio(rs.getInt("id_municipio"));
                c.setIdDepartamento(rs.getInt("id_departamento"));
                c.setRol(rs.getString("tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return c;
    }

    /*
     funcion en la que inician sesion todos los usuarios que aparezcan en los registros
     del cnr, no importa si tienen una cuenta de usuario secundario, por esta funcion
     entrar como votantes.
     se reciben como parametros las credenciales de usuario, y se retorna un objeto
     con el tipo Ciudadano que contiene la información del votante, en caso contrario
     retorna el objeto vacio
     */
    public static Ciudadano entrarVotante(String credencial, String contrasenia) {
        Ciudadano c = new Ciudadano();
        String query = "select * from entrarVotante(?,?)";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, credencial);
            pst.setString(2, contrasenia);
            rs = pst.executeQuery();
            while (rs.next()) {
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNumDui(rs.getString("num_dui"));
                c.setContrasenia(rs.getString("contrasenia"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setConfirmacion(rs.getInt("confirmacion"));
                c.setTipoUsuario(rs.getInt("tipo_usuario"));
                c.setIdMunicipio(rs.getInt("id_municipio"));
                c.setIdDepartamento(rs.getInt("id_departamento"));
                c.setRol(rs.getString("tipo"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return c;
    }

    /*
     en esta funcion se registran todos los usuarios del sistema a excepcion
     del supervisor externo.
     se recibe como parametro un objeto del tipo ciudadano y segun el valor 
     que tenga en su atributo tipoUsuario se registra como usuarioPrincipal
     o usuarioSecundario.
     se retorna true cuando el registro es un exito, en caso de fallas se
     retorna un false
     */
    public static boolean agregarUsuario(Ciudadano c) {
        int tipo = c.getTipoUsuario();
        String query;
        //usuarios principales
        if (tipo == 2 || tipo == 3 || tipo == 6) {
            query = "select agregarPrincipal(?,?,?,?,?,?,?,?,?)";
            try {
                pst = con.getCnn().prepareStatement(query);
                pst.setString(1, c.getNumDui());
                pst.setString(2, c.getContrasenia());
                pst.setString(3, c.getNombre());
                pst.setString(4, c.getApellido());
                pst.setString(5, String.valueOf(c.getFechaNac()));
                pst.setString(6, c.getSexo());
                pst.setString(7, c.getDireccion());
                pst.setInt(8, c.getIdMunicipio());
                pst.setInt(9, tipo);
                rs = pst.executeQuery();
                /*
                 el procedimiento almacenado agregar principal 
                 */
                while (rs.next()) {
                    if (rs.getString("agregarPrincipal").equals("t")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } //usuarios secundarios
        else if (tipo == 5 || (tipo >= 7 && tipo <= 11)) {
            query = "select agregarSecundario(?,?,?)";
            try {
                pst = con.getCnn().prepareStatement(query);
                pst.setString(1, c.getNumDui());
                pst.setString(2, c.getContrasenia());
                pst.setInt(3, tipo);
                rs = pst.executeQuery();
                while (rs.next()) {
                    if (rs.getString("agregarSecundario").equals("t")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            } finally {
                con.desconectar();
            }
        }

        return false;
    }

    /*
     solo se pueden modificar usuarios principales, obteniendo los datos
     del objeto que se para por parametro y retornando true si se actualizo 
     correctamente o false si hubo un error.
     no se pueden modificar usuarios secundarios, ya que el procedimiento
     agregarUsuario que esta arriba, cuando se trata de un usuario secundario
     no hace registros, solo actualiza el tipo de usuario
     */
    public static boolean modificarUsuario(Ciudadano c) {
        int tipo = c.getTipoUsuario();
        String query;
        //usuarios principales
        if (tipo == 2 || tipo == 3 || tipo == 6) {
            query = "select modificarPrincipal(?,?,?,?,?,?,?,?,?,?)";
            try {
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, c.getIdUsuario());
                pst.setString(2, c.getNumDui());
                pst.setString(3, c.getContrasenia());
                pst.setString(4, c.getNombre());
                pst.setString(5, c.getApellido());
                pst.setString(6, String.valueOf(c.getFechaNac()));
                pst.setString(7, c.getSexo());
                pst.setString(8, c.getDireccion());
                pst.setInt(9, c.getIdMunicipio());
                pst.setInt(10, tipo);
                rs = pst.executeQuery();
                /*
                 el procedimiento almacenado agregar principal 
                 */
                while (rs.next()) {
                    if (rs.getString("modificarPrincipal").equals("t")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            } catch (SQLException ex) {
                System.out.println(pst);
                Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
                return false;
            }
        } else {
            return false;
        }
        return false;
    }
    /*
     se recibe como parametro el id de un usuario, por medio del cual se hace
     una consulta retornando un objeto Ciudadano con la informacion de este usuario,
     en caso de que no encuentre ese registro, retorna un objeto vacio
     el parametro hace una diferencia cuando el usuario que se busca es administrador
     retornando solo id_usuario, dui, contraseña, confirmacion y el tipo de usuario
     cuando se encuentra un usuario principal, ademas de estos datos, retorna los datos
     corresdientes de la tabla excepcionusuario, con su informacion
     cuando es un usuario secundario o un votante, retorna la misma informacion que un
     usuario principal, pero se toman de la tabla padronelectoral
     */

    public static Ciudadano mostrarUsuario(int idUsuario) {
        Ciudadano c = new Ciudadano();
        int tipo = 0;
        String query = "select * from usuario where id_usuario =  ?";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setInt(1, idUsuario);
            rs = pst.executeQuery();
            while (rs.next()) {
                tipo = rs.getInt("id_tipo_usuario");
            }
            //obteniendo la informacion del administrador
            if (tipo == 1) {
                query = "select u.id_usuario, u.id_tipo_usuario, u.contrasenia, u.confirmacion, ct.num_dui from usuario u inner join credencialtemporal ct on u.id_usuario = ct.id_usuario where u.id_usuario = ?";
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, idUsuario);
                rs = pst.executeQuery();
                while (rs.next()) {
                    c.setIdUsuario(rs.getInt("id_usuario"));
                    c.setNumDui(rs.getString("num_dui"));
                    c.setContrasenia(rs.getString("contrasenia"));
                    c.setConfirmacion(rs.getInt("confirmacion"));
                    c.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                }
                return c;
            } //obteniendo los datos de un magistrado, representante del CNR o director del TSE
            else if (tipo == 2 || tipo == 3 || tipo == 6) {
                query = "select u.id_usuario, ct.num_dui, u.contrasenia, ex.nombre, ex.apellido, ex.sexo, u.confirmacion, u.id_tipo_usuario, ex.fecha_nac, ex.direccion_especifica, ex.id_municipio from usuario u inner join credencialtemporal ct on ct.id_usuario = u.id_usuario inner join excepcionusuario ex on ex.id_usuario = u.id_usuario where u.id_usuario = ?";
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, idUsuario);
                rs = pst.executeQuery();
                while (rs.next()) {
                    c.setIdUsuario(rs.getInt("id_usuario"));
                    c.setNumDui(rs.getString("num_dui"));
                    c.setContrasenia(rs.getString("contrasenia"));
                    c.setNombre(rs.getString("nombre"));
                    c.setApellido(rs.getString("apellido"));
                    c.setSexo(rs.getString("sexo"));
                    c.setConfirmacion(rs.getInt("confirmacion"));
                    c.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                    c.setFechaNac(rs.getString("fecha_nac"));
                    c.setDireccion(rs.getString("direccion_especifica"));
                    c.setIdMunicipio(rs.getInt("id_municipio"));
                }
                return c;
            } //obteniendo los datos de un usuario secundario y votante
            else if (tipo == 5 || (tipo >= 7 && tipo <= 10) || tipo == 11) {
                query = "select u.id_usuario, p.num_dui, u.contrasenia, p.nombre, p.apellido, p.sexo, u.confirmacion, u.id_tipo_usuario, p.fecha_nac, p.direccion_especifica, p.id_municipio, m.id_departamento from usuario u inner join usuariopadron up on u.id_usuario = up.id_usuario inner join padronelectoral p on p.num_dui = up.num_dui inner join municipio m on m.id_municipio = p.id_municipio where u.id_usuario = ?";
                pst = con.getCnn().prepareStatement(query);
                pst.setInt(1, idUsuario);
                rs = pst.executeQuery();
                while (rs.next()) {
                    c.setIdUsuario(rs.getInt("id_usuario"));
                    c.setNumDui(rs.getString("num_dui"));
                    c.setContrasenia(rs.getString("contrasenia"));
                    c.setNombre(rs.getString("nombre"));
                    c.setApellido(rs.getString("apellido"));
                    c.setSexo(rs.getString("sexo"));
                    c.setConfirmacion(rs.getInt("confirmacion"));
                    c.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                    c.setFechaNac(rs.getString("fecha_nac"));
                    c.setDireccion(rs.getString("direccion_especifica"));
                    c.setIdMunicipio(rs.getInt("id_municipio"));
                    c.setIdDepartamento(rs.getInt("id_departamento"));
                }
                return c;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return c;
    }

    /*
     la funcion recibe como parametro el tipo de usuario que quiere mostrar, y el departamento
     al que debe pertenecer, retorna un arraylist con los usuarios encontrados en la consulta
     cuando se ingresa 2, 3 o 6 en el primer parametro se hace una busqueda de los usuarios principales, en la tabla
     excepcionusuario
     cuando se ingresa 5, 7, 8, 9 o 10 se hace una busqueda de los usuarios secundarios del sistema,
     sus datos toman de la tabla padronelectoral 
     cuando se ingresa cualquier numero distinto a los anteriores, se hace una busqueda
     de los votantes que hay registrados en el sistema, teniendo la posibilidad de filtrarlos por
     departamento ingresando el id_departamento en el segundo parametro
     cuando se ingresa 0 en el segundo parmetro, se omite el filtre de busqueda de departamento
     */
    public static ArrayList<Ciudadano> mostrarUsuarios(int tipoUsuario, int departamento) {
        String query;
        String condicion = "";
        ArrayList<Ciudadano> lista = new ArrayList();
        //busqueda por departamento
        if (departamento != 0) {
            condicion = "m.id_departamento = " + departamento;
        } //aca se omite la busqueda por departmento
        else {
            condicion = "1 = 1";
        }
        //usuarios principales
        if (tipoUsuario == 2 || tipoUsuario == 3 || tipoUsuario == 6) {
            query = "select u.id_usuario, ct.num_dui, u.contrasenia, ex.nombre, ex.apellido, ex.sexo, u.confirmacion, u.id_tipo_usuario, ex.fecha_nac, ex.direccion_especifica, ex.id_municipio, m.id_departamento, t.tipo_usuario from usuario u inner join credencialtemporal ct on ct.id_usuario = u.id_usuario inner join excepcionusuario ex on ex.id_usuario = u.id_usuario inner join municipio m on m.id_municipio = ex.id_municipio inner join tipousuario t on t.id_tipo_usuario = u.id_tipo_usuario where u.id_tipo_usuario = " + tipoUsuario + " and " + condicion;

        } //usuarios secundarios
        else if (tipoUsuario == 5 || (tipoUsuario >= 7 && tipoUsuario <= 10)) {
            query = "select u.id_usuario, p.num_dui, u.contrasenia, p.nombre, p.apellido, p.sexo, u.confirmacion, u.id_tipo_usuario, p.fecha_nac, p.direccion_especifica, p.id_municipio, m.id_departamento, t.tipo_usuario from usuario u inner join usuariopadron up on u.id_usuario = up.id_usuario inner join padronelectoral p on p.num_dui = up.num_dui inner join municipio m on m.id_municipio = p.id_municipio inner join tipousuario t on t.id_tipo_usuario = u.id_tipo_usuario where u.id_tipo_usuario = " + tipoUsuario + " and " + condicion;
        } //votantes
        else {
            query = "select u.id_usuario, p.num_dui, u.contrasenia, p.nombre, p.apellido, p.sexo, u.confirmacion, u.id_tipo_usuario, p.fecha_nac, p.direccion_especifica, p.id_municipio, m.id_departamento, t.tipo_usuario from usuario u inner join usuariopadron up on u.id_usuario = up.id_usuario inner join padronelectoral p on p.num_dui = up.num_dui inner join municipio m on m.id_municipio = p.id_municipio inner join tipousuario t on t.id_tipo_usuario = u.id_tipo_usuario where " + condicion;
        }
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                Ciudadano c = new Ciudadano();
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNumDui(rs.getString("num_dui"));
                c.setContrasenia(rs.getString("contrasenia"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setSexo(rs.getString("sexo"));
                c.setConfirmacion(rs.getInt("confirmacion"));
                c.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                c.setFechaNac(rs.getString("fecha_nac"));
                c.setDireccion(rs.getString("direccion_especifica"));
                c.setIdMunicipio(rs.getInt("id_municipio"));
                c.setIdDepartamento(rs.getInt("id_departamento"));
                c.setRol(rs.getString("tipo_usuario"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return lista;
    }
    /*metodo para mostrar todos los usuarios principales en el sistema*/

    public static ArrayList<Ciudadano> mostrarUsuariosPrincipales() {
        String query;

        ArrayList<Ciudadano> lista = new ArrayList();
        query = "select u.id_usuario, ct.num_dui, u.contrasenia, ex.nombre, ex.apellido, ex.sexo, u.confirmacion, "
                + "u.id_tipo_usuario, ex.fecha_nac, ex.direccion_especifica, ex.id_municipio, m.id_departamento, t.tipo_usuario  "
                + "from usuario u inner join credencialtemporal ct on ct.id_usuario = u.id_usuario "
                + "inner join excepcionusuario ex on ex.id_usuario = u.id_usuario inner join municipio m on m.id_municipio = ex.id_municipio "
                + "inner join tipousuario t on t.id_tipo_usuario = u.id_tipo_usuario "
                + "where u.id_tipo_usuario =2 or u.id_tipo_usuario =3 or u.id_tipo_usuario =6 order by u.id_usuario desc";
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                Ciudadano c = new Ciudadano();
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNumDui(rs.getString("num_dui"));
                c.setContrasenia(rs.getString("contrasenia"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setSexo(rs.getString("sexo"));
                c.setConfirmacion(rs.getInt("confirmacion"));
                c.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                c.setFechaNac(rs.getString("fecha_nac"));
                c.setDireccion(rs.getString("direccion_especifica"));
                c.setIdMunicipio(rs.getInt("id_municipio"));
                c.setIdDepartamento(rs.getInt("id_departamento"));
                c.setRol(rs.getString("tipo_usuario"));
                lista.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            con.desconectar();
        }
        return lista;
    }
    /*
     el metodo busca a los ciudadanos que estan en los registros de padronelecoral
     mediante el diu, que es el parametro de entrada
     cuando se encuentra el registra, se retorna un objet Ciudadano con la informacion
     del votante, en caso contrario se retorna un objeto vacio
     */

    public static Ciudadano mostrarVotante(String dui) {
        Ciudadano c = new Ciudadano();
        String query = "select u.id_usuario, p.num_dui, u.contrasenia, p.nombre, p.apellido, p.sexo, u.confirmacion, u.id_tipo_usuario, p.fecha_nac, p.direccion_especifica, p.id_municipio, m.id_departamento from usuario u inner join usuariopadron up on u.id_usuario = up.id_usuario inner join padronelectoral p on p.num_dui = up.num_dui inner join municipio m on m.id_municipio = p.id_municipio where p.num_dui = ?";
        try {
            pst = con.getCnn().prepareStatement(query);
            pst.setString(1, dui);
            rs = pst.executeQuery();
            while (rs.next()) {
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNumDui(rs.getString("num_dui"));
                c.setContrasenia(rs.getString("contrasenia"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setSexo(rs.getString("sexo"));
                c.setConfirmacion(rs.getInt("confirmacion"));
                c.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                c.setFechaNac(rs.getString("fecha_nac"));
                c.setDireccion(rs.getString("direccion_especifica"));
                c.setIdMunicipio(rs.getInt("id_municipio"));
                c.setIdDepartamento(rs.getInt("id_departamento"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

    /*
     busqueda de usuarios principales, segun el dui
     se retorna un objeto tipo Ciudadano
     */
    public static Ciudadano mostrarPrincipal(String dui) {
        Ciudadano c = new Ciudadano();
        String query = "select u.id_usuario, ct.num_dui, u.contrasenia, ex.nombre, ex.apellido, ex.sexo, u.confirmacion, "
                + "u.id_tipo_usuario, ex.fecha_nac, ex.direccion_especifica, ex.id_municipio, m.id_departamento, t.tipo_usuario  "
                + "from usuario u inner join credencialtemporal ct on ct.id_usuario = u.id_usuario "
                + "inner join excepcionusuario ex on ex.id_usuario = u.id_usuario inner join municipio m on m.id_municipio = ex.id_municipio "
                + "inner join tipousuario t on t.id_tipo_usuario = u.id_tipo_usuario "
                + "where (u.id_tipo_usuario =2 or u.id_tipo_usuario =3 or u.id_tipo_usuario =6) and ct.num_dui = '" + dui + "'";
        try {
            pst = con.getCnn().prepareStatement(query);
            rs = pst.executeQuery();
            while (rs.next()) {
                c.setIdUsuario(rs.getInt("id_usuario"));
                c.setNumDui(rs.getString("num_dui"));
                c.setContrasenia(rs.getString("contrasenia"));
                c.setNombre(rs.getString("nombre"));
                c.setApellido(rs.getString("apellido"));
                c.setSexo(rs.getString("sexo"));
                c.setConfirmacion(rs.getInt("confirmacion"));
                c.setTipoUsuario(rs.getInt("id_tipo_usuario"));
                c.setFechaNac(rs.getString("fecha_nac"));
                c.setDireccion(rs.getString("direccion_especifica"));
                c.setIdMunicipio(rs.getInt("id_municipio"));
                c.setIdDepartamento(rs.getInt("id_departamento"));
                c.setRol(rs.getString("tipo_usuario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(CiudadanoDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }

}
