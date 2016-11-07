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
import modelo.AsignacionCentro;
import modelo.AsignacionVotantesDTO;
import modelo.Bitacora;
import modelo.BitacoraDTO;
import modelo.CentroVotacion;
import modelo.CentroVotacionDTO;
import modelo.Ciudadano;
import modelo.CiudadanoDTO;
import modelo.DetalleAsignacion;
import modelo.MunicipioDTO;

/**
 *
 * @author carlos
 */
public class SerAsignacionVotantes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //se evalua si ya se hizo la asignacion de votantes
    public static int cantidadAsignacion() {
        return AsignacionVotantesDTO.mostrarDetalleAsignacion(0, 0, 0, "").size();
    }

    //mostrar la distribucion de los votantes por centro
    public static String mostrarDistribucion() {
        String html = "";
        int listador=1;
        for (AsignacionCentro a : AsignacionVotantesDTO.mostrarAsignacion(0)) {
            html += "<tr>";
            html +="<td>"+listador+"</td>";
            html += "<td>" + a.getDepartamento() + "</td>";
            html += "<td>" + a.getMunicipio() + "</td>";
            html += "<td>" + a.getTotalCentros() + "</td>";
            html += "<td>" + a.getTotalVotantes() + "</td>";
            html += "<td>" + a.getVotantesPorCentro() + "</td>";
            html += "</tr>";
            
            listador++;
        }
        return html;
    }

    //se obtiene el total de centros de votaciones
    public static int totalCentros() {
        int centros = 0;
        for (AsignacionCentro a : AsignacionVotantesDTO.mostrarAsignacion(0)) {
            centros += a.getTotalCentros();
        }
        return centros;
    }

    //se obtiene el total de personas asignadas o a asignar
    public static int totalVotantes() {
        int votantes = 0;
        for (AsignacionCentro a : AsignacionVotantesDTO.mostrarAsignacion(0)) {
            votantes += a.getTotalVotantes();
        }
        return votantes;
    }

    //mostrar la asignacion de los votantes en los centros de votacion
    public static String mostrarAsignacion() {
        String html = "";
        int listador=1;
        for (DetalleAsignacion d : AsignacionVotantesDTO.mostrarDetalleAsignacion(0, 0, 0, "")) {
            html += "<tr>";
            html +="<td>"+listador+"</td>";
            html += "<td>" + d.getDepartamento() + "</td>";
            html += "<td>" + d.getMunicipio() + "</td>";
            html += "<td>" + d.getCentro() + "</td>";
            html += "<td>" + d.getMunicipio() + "</td>";
            html += "<td>" + d.getCiudadano() + "</td>";
            html += "</tr>";
            
            listador++;
        }
        return html;
    }

    //mostrar ciudadanos asignados al centro de votacion
    public static String mostrarAsignacionCentro(int idCentro) {
        String html = "";
        for (DetalleAsignacion d : AsignacionVotantesDTO.mostrarDetalleAsignacion(0, 0, idCentro, "")) {
            html += "<tr>";
            html += "<td>" + d.getCorrelativo() + "</td>";
            html += "<td>" + d.getCiudadano() + "</td>";
            html += "</tr>";
        }
        return html;
    }
    
    //total de ciudadanos asignados al centro
    public static int totalPersonasCentro(int idCentro) {
        int totalPersonas = 0;
        for (DetalleAsignacion d : AsignacionVotantesDTO.mostrarDetalleAsignacion(0, 0, idCentro, "")) {
            totalPersonas++;
        }
        return totalPersonas;
    }
    
    //obtenrer la informacion del centro
    public static CentroVotacion informacionCentro(int idCentro) {
        return CentroVotacionDTO.mostrarCentro(idCentro);
    }
    //obtener el id del centro de votacion, segun el id del director
    public static int getIdCentroVotacion(int idDirector) {
        int idCentro = 0;
        for(CentroVotacion cv:CentroVotacionDTO.mostrarCentros()) {
            //buscamos el registro al que pertenezca el director del centro
            if(cv.getNumDui().equals(CiudadanoDTO.mostrarUsuario(idDirector).getNumDui())) {
                idCentro = cv.getIdCentroVotacion();
            }
        }
        return idCentro;
    }
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (request.getParameter("btnAsignacion") != null) {
            String dui1 = request.getParameter("txtDui1");
            String dui2 = request.getParameter("txtDui2");
            String dui3 = request.getParameter("txtDui3");
            Bitacora b = new Bitacora();
            b.setMagistrado1(dui1);
            b.setMagistrado2(dui2);
            b.setMagistrado3(dui3);
            b.setAccion("Asignacion de votantes a centros de votacion");
            BitacoraDTO.agregarBitacora(b);
            AsignacionVotantesDTO.asignarVotantes();
            out.print("La operacion se realizo con exito");
        }
        
        if(request.getParameter("btnBuscarCiudadano") != null) {
            //cabe la posibilidad que se haga esta consulta cuando los ciudadanos aun no tengan
            //un centro de votacion asignado
            //cuando la variable termine al final del ciclo siento false, significa que
            //este usuario aun no tiene un centro asignado
            boolean coincidencia = false;
            String dui = request.getParameter("txtDui");
            String pass = request.getParameter("txtPass");
                Ciudadano c = CiudadanoDTO.entrarVotante(dui, pass);
                if(c.getIdUsuario() != 0) {
                    for(DetalleAsignacion a:AsignacionVotantesDTO.mostrarDetalleAsignacion(0, 0, 0, dui)) {
                        coincidencia = true;
                        CentroVotacion centro = CentroVotacionDTO.mostrarCentro(a.getIdCentro());
                        out.print("Centro de votacion asignado<br>");
                        out.print("Nombre: " + a.getCentro() + "<br>");
                        out.print("Codigo de JRV: " + a.getCorrelativo() + "<br>");
                        out.print("Direccion: " + centro.getDireccion() + "<br>");
                        out.print("Municipio: " + MunicipioDTO.mostrarUnMunicipio(centro.getIdMunicipio()).getNombreMunicipio() + "<br>");
                        out.print("Departamento: " + SerMunicipio.obtenerDepartamento(centro.getIdMunicipio()));
                    }
                    if(!coincidencia) {
                        out.print("Lo sentimos, parece que aun no tienes asignado<br>");
                        out.print("un centro de votacion, espera algunos dias hasta que<br>");
                        out.print("Se realizen las asignaciones");
                    }
                }
                else {
                    out.print("Lo sentimos, Parece que las credenciales<br>son incorrectas :(");
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
