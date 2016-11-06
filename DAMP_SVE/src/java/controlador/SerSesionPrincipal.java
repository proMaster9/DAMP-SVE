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
import static modelo.CiudadanoDTO.entrarPrincipal;

/**
 *
 * @author Eliiza
 */
public class SerSesionPrincipal extends HttpServlet {

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
//            out.println("<title>Servlet SerSesionPrincipal</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SerSesionPrincipal at " + request.getContextPath() + "</h1>");
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
        try{
            
        /**
         * posibles errores que se pueden dar y los diferentes modal que se habilitaran
         * modalError=1: campos vacios
         * modalError=2: cuenta no activada
         * modalError=3: datos incorrectos
         */
         HttpSession sesion = request.getSession();
         if (request.getParameter("btnEntrar") != null) {
                String user = request.getParameter("txtUser");
                String pass = request.getParameter("txtPass");
                if (!user.equals("") && !pass.equals("")) {
                    Ciudadano c = entrarPrincipal(user, pass);
                    if(c.getConfirmacion() == 0 ){
                        if (user.equals(c.getNumDui()) && pass.equals(c.getContrasenia())){
                            ArrayList<Ciudadano> usuario = new ArrayList<>();
                            usuario.add(c);
                            sesion.setAttribute("usuario",usuario);
                            response.sendRedirect("pages/tse.jsp");
                        } else {
                            //datos incorrectos
                            response.sendRedirect("pages/login/principal.jsp?modalError=3");
                        }
                    }else{
                        //activar modal notificando que la cuenta no esta activa
                        response.sendRedirect("pages/login/principal.jsp?modalError=2");
                    }
                } else {
                    //debes completar campos
                    response.sendRedirect("pages/login/principal.jsp?modalError=1");
                }
            }
        }catch(Exception e){}
        
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
