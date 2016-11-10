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
import static modelo.CiudadanoDTO.entrarPrincipal;
import modelo.Pregunta;
import modelo.PreguntaDTO;
import modelo.Respuesta;

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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html;charset=UTF-8;");
        request.setCharacterEncoding("UTF-8");
        try {
            HttpSession sesion = request.getSession();
            if (request.getParameter("txtPrincipal") != null) {
                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");
                if (!user.equals("") && !pass.equals("")) {
                    Ciudadano c = entrarPrincipal(user, pass);
                    if (c.getConfirmacion() == 0) {
                        out.print("1");//si la cuenta no esta activa retorna 1
                    } else {
                        out.print("2");//cuenta activada
                    }
                } else {
                    out.print("3");//campos vacios
                }

            }
            if (request.getParameter("divHtml") != null) {
                String html = "<div class=\"input-group\">"
                        + "<span class=\"input-group-addon\">"
                        + "<i class=\"material-icons col-light-blue\">lock</i>"
                        + "</span>"
                        + "<div class=\"form-line\">"
                        + "<input type=\"password\" class=\"form-control\" name=\"txtPass2\" id=\"txtPass2\" placeholder=\"Repita la contraseña\" required>"
                        + "</div>"
                        + "</div>"
                        + "<div class=\"input-group\">"
                        + "<span class=\"input-group-addon\">"
                        + "<i class=\"material-icons col-light-blue\">lock</i>"
                        + "</span>"
                        + mostrarPreguntas()
                        + "</div>"
                        + "<div class=\"input-group\">"
                        + "<span class=\"input-group-addon\">"
                        + "<i class=\"material-icons col-light-blue\">person</i>"
                        + "</span>"
                        + "<div class=\"form-line\">"
                        + "<input type=\"text\" class=\"form-control\" name=\"txtRespuesta\" id=\"txtRespuesta\" placeholder=\"Ingrese su respuesta\" required autofocus>"
                        + "</div>"
                        + "</div>";
                out.print(html);
            }
            if (request.getParameter("txtActivar") != null) {
                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");
                String pass2 = request.getParameter("txtPass2");
                int idPregunta = Integer.valueOf(request.getParameter("idPregunta"));
                String respuesta = request.getParameter("txtRespuesta");
                /*Ciudadano c = CiudadanoDTO.entrarVotante(user, pass);
                CiudadanoDTO.activarCuenta(c.getIdUsuario());
                System.out.println(c.getIdUsuario());
                Respuesta r =new  Respuesta(c.getIdUsuario(),idPregunta,respuesta);
                if(CiudadanoDTO.ingresarRespuesta(r)==true){
                    out.print("verdadero");
                }else{
                    out.print("Falso");
                }*/
                
            }
        } catch (Exception e) {
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
