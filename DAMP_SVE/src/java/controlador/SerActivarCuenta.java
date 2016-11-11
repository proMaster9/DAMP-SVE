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
import modelo.Ciudadano;
import modelo.CiudadanoDTO;
import static modelo.CiudadanoDTO.mostrarVotante;
import modelo.Pregunta;
import modelo.PreguntaDTO;
import modelo.RespuestaDTO;

/**
 *
 * @author Eliiza
 */
public class SerActivarCuenta extends HttpServlet {

    //Funcion para imprimir la preguna
    public static String mostrarPreguntas() {
        String html = "";
        html = "<select class=\"form-control show-tick\" data-live-search=\"true\" name=\"slPregunta\" id=\"slPregunta\">";
        html += "<option value=\"0\">Seleccione una pregunta</option>";
        for (Pregunta p : PreguntaDTO.mostrar()) {
            html += "<option value='" + p.getIdPregunta() + "'>" + p.getPregunta() + "</option>";
        }
        html += "</select>";
        return html;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet SerActivarCuenta</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SerActivarCuenta at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//    }
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
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");

            /**
             * Los datos son recibidos desde el archivo
             * function_activar_cuenta.js enviados por jquery Cuando retorne a
             * la pagina del login puede devolver 1: si entrar por otro medio 2:
             * si hay campos vacios 3: si el usuario es incorrecto 4: usuario
             * activo 5: comenzar proceso
             */
            if (request.getParameter("btnVerificar") != null) {
                String user = request.getParameter("user");//retornal el usuario
                //verficiando campos vacios
                if (!user.equals("")) {
                    Ciudadano c = mostrarVotante(user);
                    //verificando el usuario
                    if (user.equals(c.getNumDui())) {
                        //verificando que el usuario este activo en el sistema  
                        if (c.getConfirmacion() == 0) {
                            //usuario encontrado
                            out.print("5");//comenzar proceso
                        } else {
                            out.print("4");//usuario activo
                        }
                    } else {
                        out.print("3");//usuario incorrecto
                    }
                } else {
                    out.print("2");//campos vacios
                }
            } else if (request.getParameter("btnActivar") != null) {
                String user = request.getParameter("user");//captura el usuario
                String pass = request.getParameter("pass");//captura la contraseña
                int preg = Integer.parseInt(request.getParameter("preg"));//captura la id pregunta
                String res = request.getParameter("res");//captura la respuesta
                //veficando que los campos no esten vacios
                if (!user.equals("") && !pass.equals("") && !res.equals("")) {
                    Ciudadano c = mostrarVotante(user);
                    if (c.getConfirmacion() == 0) {
                        CiudadanoDTO.activarCuenta(c.getIdUsuario());
                        RespuestaDTO.agregarRespuesta(c.getIdUsuario(), preg, res);
                        CiudadanoDTO.actualizarContra(pass,c.getIdUsuario());
                        out.print("4");//cuenta activada
                    } else {
                        out.print("3");//cuenta ya activada
                    }
                } else {
                    out.print("2");//campos vacios
                }
            } else {
                out.print("1");//redireccinar a pagina de advertencia
            }

        } catch (Exception e) {
            response.sendRedirect("pages/notificaciones/tse_advertencia.jsp");
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
