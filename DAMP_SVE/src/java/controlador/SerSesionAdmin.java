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
import javax.servlet.http.HttpSession;
import modelo.Ciudadano;
import static modelo.CiudadanoDTO.entrarAdmi;

/**
 *
 * @author Eliiza
 */
public class SerSesionAdmin extends HttpServlet {

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
//            out.println("<title>Servlet SerSesionAdmin</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SerSesionAdmin at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect("pages/notificaciones/tse_advertencia.jsp");
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

        /**
         * posibles errores que se pueden dar y los diferentes mensajes que se
         * habilitaran modalError=1: campos vacios modalError=2: cuenta no
         * activada modalError=3: datos incorrectos
         */
        try {
            HttpSession sesion = request.getSession();
            if (request.getParameter("btnEntrar") != null) {
                //validacion para verificar que los parametros si estan haciendo enviados desde la pagina del admin
                //para evitar que otra persona intente ingresar por medio de la url, o de algun otro sitio que no sea
                //la propia pagina del administrador, por el motivo que si entra como administrador el sistema estaria
                //expuesto cualquier alteracion
                //chiave significa llave en idioma italiano
                String key1 = request.getParameter("txtChiave1");//retorna la url donde se ubica el login del admin
                String key2 = request.getParameter("txtChiave2");//retorna el color del body del login del admin

                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");

                //verificando que los campos claves no esten vacios
                if (!key1.equals("") && !key2.equals("")) {

                    //validando la informacion
                    if (key1.equals("/DAMP_SVE/pages/login/admin/admin.jsp") && key2.equals("rgb(255, 255, 255)")) {

                        //veficando que los campos no esten vacios
                        if (!user.equals("") && !pass.equals("")) {
                            Ciudadano c = entrarAdmi(user, pass);
                            if (user.equals(c.getNumDui()) && pass.equals(c.getContrasenia())) {
                                if (c.getConfirmacion() == 1) {
                                    ArrayList<Ciudadano> usuario = new ArrayList<>();
                                    usuario.add(c);
                                    sesion.setAttribute("usuario", usuario);
                                    response.sendRedirect("pages/tse.jsp");
                                } else {
                                    //datos incorrectos
                                    response.sendRedirect("pages/login/admin/admin.jsp?modalError=2");
                                }
                            } else {
                                //activar modal notificando que la cuenta no esta activa
                                response.sendRedirect("pages/login/admin/admin.jsp?modalError=3");
                            }

                        } else {
                            //debes completar campos
                            response.sendRedirect("pages/login/admin/admin.jsp?modalError=3");
                        }
                    } else {
                        //redirecciona al login del admin, sin notificar nada, porque si los datos no coinciden, puede que el usuario intento modificar
                        //el html, por traer datos el parametro se asume que la persona que intenta ingresar esta en el login, pero no se le notifica nada
                        //solo se recarga la pagina
                        response.sendRedirect("pages/login/admin/admin.jsp");
                    }
                } else {
                    //redireccina al index sin notificar al que intenta entrar por otro medio
                    response.sendRedirect("index.jsp");
                }
            }
        } catch (Exception e) {
            response.sendRedirect("pages/notificaciones/tse_sin_servidor.jsp");
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
