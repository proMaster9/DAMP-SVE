/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Ciudadano;
import modelo.CiudadanoDTO;
import modelo.DepartamentoDTO;
import modelo.Municipio;
import modelo.MunicipioDTO;
import modelo.SupervisorDTO;
import modelo.SupervisorExt;

/**
 *
 * @author Icchigo
 */
public class SerUsuariosPrincipales extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //registro de usuarios
        if (request.getParameter("btnAgregar") != null) {
            Ciudadano c = new Ciudadano();
            c.setContrasenia("12345");
            c.setNumDui(request.getParameter("txtDui"));
            c.setNombre(request.getParameter("txtNombre"));
            c.setApellido(request.getParameter("txtApellido"));
            c.setSexo(request.getParameter("rdbSexo"));
            c.setFechaNac(request.getParameter("txtFecha"));
            c.setIdDepartamento(Integer.parseInt(request.getParameter("slDepartamento")));
            c.setIdMunicipio(Integer.parseInt(request.getParameter("slMunicipio")));
            c.setDireccion(request.getParameter("txtDireccion"));
            c.setTipoUsuario(Integer.parseInt(request.getParameter("slTipo")));
            if (CiudadanoDTO.agregarUsuario(c) == false) {
                out.print("hubo un error");
            }
            //Impresion de filas 
            for (Ciudadano us : CiudadanoDTO.mostrarUsuariosPrincipales()) {
                out.print("<tr>");
                out.print("<td>" + us.getNumDui() + "</td>");
                out.print("<td>" + us.getApellido() + ", " + us.getNombre() + "</td>");
                if (us.getSexo().equals("m")) {
                    out.print("<td>Masculino</td>");
                } else {
                    out.print("<td>Femenino</td>");
                }
                out.print("<td data-rol='" + us.getTipoUsuario() + "'>" + us.getRol() + "</td>");
                out.print("<td>" + us.getFechaNac() + "</td>");
                out.print("<td>" + DepartamentoDTO.mostrarDepartamento(us.getIdDepartamento()).getDepartamento() + ", " + MunicipioDTO.mostrarUnMunicipio(us.getIdMunicipio()).getNombreMunicipio() + "</td>");
                out.print("<td>" + us.getDireccion() + "</td>");
                out.print("<td>");
                out.print("<a href=\"javascript:cargar('"+us.getIdUsuario()+"','"+us.getNumDui()+"','"+us.getNombre()+"','"+us.getApellido()+"','"+us.getSexo()+"','"+us.getFechaNac()+"','"+us.getIdDepartamento()+"','"+us.getIdMunicipio()+"','"+us.getDireccion()+"','"+us.getTipoUsuario()+"')\">Modificar</a>");
                out.print("</td>");
                out.print("</tr>");
            }
            
        }
        //busqueda de dui en los registros
        if (request.getParameter("btnBuscar") != null) {
            Ciudadano c = CiudadanoDTO.mostrarPrincipal(request.getParameter("txtDui"));
            //evita que se registre un dui repetido
            if (c.getIdUsuario() != 0) {
                out.print("Dui en uso");
                out.print("<input type='hidden' id='txtResultado' value='0'>");
            } else { //solo deja pasar numeros de dui disponibles
                out.print("Dui disponible");
                out.print("<input type='hidden' id='txtResultado' value='1'>");
            }
        }
        
        if (request.getParameter("btnModificar") != null) {
            Ciudadano c = new Ciudadano();
            c.setIdUsuario(Integer.parseInt(request.getParameter("txtId")));
            c.setContrasenia("12345");
            c.setNumDui(request.getParameter("txtDui"));
            c.setNombre(request.getParameter("txtNombre"));
            c.setApellido(request.getParameter("txtApellido"));
            c.setSexo(request.getParameter("rdbSexo"));
            c.setFechaNac(request.getParameter("txtFecha"));
            c.setIdDepartamento(Integer.parseInt(request.getParameter("slDepartamento")));
            c.setIdMunicipio(Integer.parseInt(request.getParameter("slMunicipio")));
            c.setDireccion(request.getParameter("txtDireccion"));
            c.setTipoUsuario(Integer.parseInt(request.getParameter("slTipo")));
            if(CiudadanoDTO.modificarUsuario(c) == false) {
                out.print("Hubo un error");
            }
            //Impresion de filas
            int listador=1;
            for (Ciudadano us : CiudadanoDTO.mostrarUsuariosPrincipales()) {
                
                out.print("<tr>");
                out.print("<td>"+listador+"</td>");
                out.print("<td>" + us.getNumDui() + "</td>");
                out.print("<td>" + us.getApellido() + ", " + us.getNombre() + "</td>");
                if (us.getSexo().equals("M")) {
                    out.print("<td>Masculino</td>");
                } else {
                    out.print("<td>Femenino</td>");
                }
                out.print("<td data-rol='" + us.getTipoUsuario() + "'>" + us.getRol() + "</td>");
                out.print("<td>" + DepartamentoDTO.mostrarDepartamento(us.getIdDepartamento()).getDepartamento() + ", " + MunicipioDTO.mostrarUnMunicipio(us.getIdMunicipio()).getNombreMunicipio() + "</td>");
                out.print("<td>" + us.getDireccion() + "</td>");
                out.print("<td>");
                out.print("<a href=\"javascript:cargar('"+us.getIdUsuario()+"','"+us.getNumDui()+"','"+us.getNombre()+"','"+us.getApellido()+"','"+us.getSexo()+"','"+us.getFechaNac()+"','"+us.getIdDepartamento()+"','"+us.getIdMunicipio()+"','"+us.getDireccion()+"','"+us.getTipoUsuario()+"')\">Modificar</a>");
                out.print("</td>");
                out.print("</tr>");
                listador++;
            }
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
