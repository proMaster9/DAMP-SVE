/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CentroVotacion;
import modelo.CentroVotacionDTO;
import modelo.CiudadanoDTO;
import modelo.MunicipioDTO;

/**
 *
 * @author Icchigo
 */
public class SerCentroVotacion extends HttpServlet {

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
        //agregar centros de votacion
        if (request.getParameter("btnAgregar") != null) {
            int departamento = Integer.parseInt(request.getParameter("txtDepartamento"));
            CentroVotacion c = new CentroVotacion();
            c.setNombreCentro(request.getParameter("txtNombre"));
            c.setNumJrvDisponible(Integer.parseInt(request.getParameter("txtJrv")));
            c.setNumDui(request.getParameter("txtDui"));
            c.setIdMunicipio(Integer.parseInt(request.getParameter("slMunicipio")));
            c.setDireccion(request.getParameter("txtDireccion"));
            if (CentroVotacionDTO.agregarCentro(c)) {
                int listador = 1;
                for (CentroVotacion centro : CentroVotacionDTO.mostrarCentrosDep(departamento)) {
                    out.print("<tr>");
                    out.print("<td>" + listador + "</td>");
                    out.print("<td>" + centro.getNombreCentro() + "</td>");
                    out.print("<td>" + centro.getNumJrvDisponible() + "</td>");
                    out.print("<td>" + centro.getNumDui() + ", " + CiudadanoDTO.mostrarVotante(centro.getNumDui()).getApellido() + " " + CiudadanoDTO.mostrarVotante(centro.getNumDui()).getNombre() + "</td>");
                    out.print("<td>" + MunicipioDTO.mostrarUnMunicipio(centro.getIdMunicipio()).getNombreMunicipio() + "</td>");
                    out.print("<td>");
                    out.print("<a class=\"btn bg-cyan waves-effect m-r-0 waves-light\" href=\"javascript:cargar('"+centro.getIdCentroVotacion()+','+centro.getNombreCentro()+','+ centro.getNumJrvDisponible()+','+ centro.getNumDui()+','+ centro.getIdMunicipio()+','+ centro.getDireccion()+"')\"><i class=\"material-icons\">create</i></a>");
                    out.print("<a class=\"btn bg-grey waves-effect m-r-0 waves-light\" href=\"javascript:eliminar('"+centro.getIdCentroVotacion()+"')\"><i class=\"material-icons\">delete_forever</i></a>");
                    out.print("</td>");
                    out.print("</tr>");
                    
                    listador++;
                }
            } else {
                out.print("Error al agregar");
            }
        }

        //modificar centros de votacion
        if (request.getParameter("btnModificar") != null) {
            int departamento = Integer.parseInt(request.getParameter("txtDepartamento"));
            CentroVotacion c = new CentroVotacion();
            c.setIdCentroVotacion(Integer.parseInt(request.getParameter("txtIdCentro")));
            c.setNombreCentro(request.getParameter("txtNombre"));
            c.setNumJrvDisponible(Integer.parseInt(request.getParameter("txtJrv")));
            c.setNumDui(request.getParameter("txtDui"));
            c.setIdMunicipio(Integer.parseInt(request.getParameter("slMunicipio")));
            c.setDireccion(request.getParameter("txtDireccion"));
            if (CentroVotacionDTO.modificarCentro(c)) {
                int listador = 1;
                for (CentroVotacion centro : CentroVotacionDTO.mostrarCentrosDep(departamento)) {
                    out.print("<tr>");
                    out.print("<td>" + listador + "</td>");
                    out.print("<td>" + centro.getNombreCentro() + "</td>");
                    out.print("<td>" + centro.getNumJrvDisponible() + "</td>");
                    out.print("<td>" + centro.getNumDui() + ", " + CiudadanoDTO.mostrarVotante(centro.getNumDui()).getApellido() + " " + CiudadanoDTO.mostrarVotante(centro.getNumDui()).getNombre() + "</td>");
                    out.print("<td>" + MunicipioDTO.mostrarUnMunicipio(centro.getIdMunicipio()).getNombreMunicipio() + "</td>");
                    out.print("<td>");
                    out.print("<a class=\"btn bg-cyan waves-effect m-r-0 waves-light\" href=\"javascript:cargar('"+centro.getIdCentroVotacion()+','+centro.getNombreCentro()+','+ centro.getNumJrvDisponible()+','+ centro.getNumDui()+','+ centro.getIdMunicipio()+','+ centro.getDireccion()+"')\"><i class=\"material-icons\">create</i></a>");
                    out.print("<a class=\"btn bg-grey waves-effect m-r-0 waves-light\" href=\"javascript:eliminar('"+centro.getIdCentroVotacion()+"')\"><i class=\"material-icons\">delete_forever</i></a>");
                    out.print("</td>");
                    out.print("</tr>");
                    
                    listador++;
                }
            }
            else {
                out.print("Error al modificar");
            }
        }
        
        if(request.getParameter("btnEliminar") != null) {
            int departamento = Integer.parseInt(request.getParameter("txtDepartamento"));
            int idCentro = Integer.parseInt(request.getParameter("txtIdCentro"));
            if(CentroVotacionDTO.eliminarCentro(idCentro)) {
                int listador = 1;
                for (CentroVotacion centro : CentroVotacionDTO.mostrarCentrosDep(departamento)) {
                    out.print("<tr>");
                    out.print("<td>" + listador + "</td>");
                    out.print("<td>" + centro.getNombreCentro() + "</td>");
                    out.print("<td>" + centro.getNumJrvDisponible() + "</td>");
                    out.print("<td>" + centro.getNumDui() + ", " + CiudadanoDTO.mostrarVotante(centro.getNumDui()).getApellido() + " " + CiudadanoDTO.mostrarVotante(centro.getNumDui()).getNombre() + "</td>");
                    out.print("<td>" + MunicipioDTO.mostrarUnMunicipio(centro.getIdMunicipio()).getNombreMunicipio() + "</td>");
                    out.print("<td>");
                    out.print("<a class=\"btn bg-cyan waves-effect m-r-0 waves-light\" href=\"javascript:cargar('"+centro.getIdCentroVotacion()+','+centro.getNombreCentro()+','+ centro.getNumJrvDisponible()+','+ centro.getNumDui()+','+ centro.getIdMunicipio()+','+ centro.getDireccion()+"')\"><i class=\"material-icons\">create</i></a>");
                    out.print("<a class=\"btn bg-grey waves-effect m-r-0 waves-light\" href=\"javascript:eliminar('"+centro.getIdCentroVotacion()+"')\"><i class=\"material-icons\">delete_forever</i></a>");
                    out.print("</td>");
                    out.print("</tr>");
                    
                    listador++;
                }
            }
            else {
                out.print("Error al Eliminar");
            }
        }
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
        processRequest(request, response);
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
