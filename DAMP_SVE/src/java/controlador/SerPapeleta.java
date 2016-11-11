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
import javax.servlet.http.HttpSession;
import modelo.Candidato;
import modelo.CandidatoDTO;
import modelo.Ciudadano;
import modelo.CiudadanoDTO;
import modelo.Partido;
import modelo.PartidoDTO;
import modelo.VotoBandera;
import modelo.VotoDTO;

/**
 *
 * @author carlos
 */
public class SerPapeleta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //obtener el departamento al que pertenece el usuario
    public static int departamentoUsuario(int idUsuario) {
        Ciudadano c = CiudadanoDTO.mostrarUsuario(idUsuario);
        return c.getIdDepartamento();
    }

    public static String nombreDepartamento(int idUsuario) {
        Ciudadano c = CiudadanoDTO.mostrarUsuario(idUsuario);
        return SerMunicipio.obtenerDepartamento(c.getIdMunicipio());
    }

    public static String mostrarPapeleta(int idDepartamento) {
        String html = "";
        for (Partido p : PartidoDTO.mostrarPartidos()) {
            html += "<div class=\"col-lg-3 col-md-3 col-sm-6 col-xs-12\">"
                    + "   <div id='divPartido' class=\"info-box hover-expand-effect partido\">"
                    + "       <div class=\"icon\">"
                    + "              <img src='../images/files/partidos/" + p.getImagen() + "' class='img-responsive img-thumbnail' style=\"width: 100%;height: 100%;\" data-tipo='partido' data-id-partido='" + p.getIdPartido() + "' data-estado='habilitado' data-seleccion='deseleccionado'/>"
                    + "       </div>"
                    + "       <div class=\"content\">"
                    + "               <div class=\"text\">" + p.getAcronimo() + "</div>"
                    + "       </div>"
                    + "   </div>";
            for (Candidato c : CandidatoDTO.mostrarCandidatosDep(idDepartamento)) {
                if (c.getIdPartido() == p.getIdPartido()) {
                    html += "<div class=\"info-box hover-expand-effect\">"
                            + "       <div class=\"icon\">"
                            + "              <img src='../images/files/candidatos/" + c.getFoto() + "' class='img-responsive img-thumbnail deseleccionado' style=\"width: 100%;height: 100%;\" data-tipo='candidato'  data-id-candidato='" + c.getIdCandidato() + "' data-partido='" + p.getIdPartido() + "' data-estado='habilitado' data-seleccion='deseleccionado'>"
                            + "       </div>"
                            + "       <div class=\"content\">"
                            + "               <div class=\"text\">" + CiudadanoDTO.mostrarVotante(c.getNumDui()).getApellido() + "<br>" + CiudadanoDTO.mostrarVotante(c.getNumDui()).getNombre() + "</div>"
                            + "       </div>"
                            + "   </div>";
                }
            }
            html += "</div>";
        }
        return html;
    }

    public static int estadoVotante(int idUsuario) {
        return VotoDTO.estadoVotante(idUsuario);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (request.getParameter("votoBandera") != null) {
            int partido = Integer.parseInt(request.getParameter("txtPartido"));
            int votante = Integer.parseInt(request.getParameter("txtIdUsuario"));
            VotoBandera v = new VotoBandera();
            v.setIdPartido(partido);
            v.setIdJrv(VotoDTO.jrvVotante(votante).getIdJrv());
            v.setIdUsuario(votante);
            if (VotoDTO.agregarVotoBandera(v)) {
                //se realiza con exito el voto por bandera
                out.print("1");
                HttpSession sesion = request.getSession(true);
                sesion.invalidate();
            } else {
                //ocurre algun error con al registrar el voto
                out.print("0");
                HttpSession sesion = request.getSession(true);
                sesion.invalidate();
            }
        }

        //realizar voto
        if (request.getParameter("voto") != null) {
            int votante = Integer.parseInt(request.getParameter("txtIdUsuario"));
            String candidatos = request.getParameter("txtCandidatos");
            //si el voto es correcto
            if (VotoDTO.agregarVotoMarca(votante, candidatos)) {
                out.print("1");
                HttpSession sesion = request.getSession(true);
                sesion.invalidate();
            } //en caso de que ocurra un error
            else {
                out.print("0");
                HttpSession sesion = request.getSession(true);
                sesion.invalidate();
            }
        }

        //abstener voto
        if (request.getParameter("votoAbstenido") != null) {
            int votante = Integer.parseInt(request.getParameter("txtIdUsuario"));
            //si el voto es correcto
            if (VotoDTO.agregarVotoAbstenido(votante)) {
                out.print("1");
                HttpSession sesion = request.getSession(true);
                sesion.invalidate();
            } //en caso de que ocurra un error
            else {
                out.print("0");
                HttpSession sesion = request.getSession(true);
                sesion.invalidate();
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
