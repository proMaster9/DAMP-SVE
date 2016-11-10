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
import static modelo.CiudadanoDTO.entrarVotante;
import modelo.Respuesta;
import modelo.RespuestaDTO;

/**
 *
 * @author Eliiza
 */
public class SerSesionVotante extends HttpServlet {

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
//            out.println("<title>Servlet SerSesionVotante</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet SerSesionVotante at " + request.getContextPath() + "</h1>");
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
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html;charset=UTF-8");
            /**
             * Los datos son recibidos desde el archivo function_admin.js
             * enviados por jquery Cuando retorne a la pagina del login puede devolver 
             * 1: si las dos llaves de validacion principla estan vacias 
             * 2: si las dos llaves de validacion principal son incorrectas 
             * 3: si el usuario y password vienen vacios 
             * 4: si el usuario y el password no son  correctos 
             * 5: si el usuario no esta activo 
             * 6: si la pregunta y la respuesta no son correctos
             * 7: si todo esta bien redirecciona a la pagina papeleta.jsp
             */
            if (request.getParameter("btnEntrarV") != null) {
                String key1 = request.getParameter("key1");//captura la url donde se ubica el login del admin
                String key2 = request.getParameter("key2");//captura el color del body del login del admin
                String user = request.getParameter("user");//captura el usuario
                String pass = request.getParameter("pass");//captura la contraseña
                int preg = Integer.parseInt(request.getParameter("preg"));//captura la id pregunta
                String res = request.getParameter("res");//captura la respuesta
                //verificando que los campos claves no esten vacios
                if (!key1.equals("") && !key2.equals("")) {
                    //validando la informacion
                    if (key1.equals("/DAMP_SVE/pages/login/principal.jsp") || key2.equals("rgb(3, 169, 244)")) {
                        //veficando que los campos no esten vacios
                        if (!user.equals("") && !pass.equals("") && !res.equals("")) {
                            Ciudadano c = entrarVotante(user,pass,preg,res);
                            Respuesta r= RespuestaDTO.verificarRespuesta(c.getIdUsuario());
                            //verificando que la contraseña y el password sean correctos
                             System.out.println(""+c.getNumDui()+ c.getContrasenia()+ r.getIdPregunta() + r.getRespuesta());
                            if (user.equals(c.getNumDui()) && pass.equals(c.getContrasenia())){
                                //verificando que el usuario este activo en el sistema
                                if (c.getConfirmacion() == 1) {
                                    //verificando que la pregunta y respuesta sean correctas
                                    if (preg==r.getIdPregunta() && res.equals(r.getRespuesta())){
                                        //contruyendo el arraylist de sesion
                                        HttpSession sesion = request.getSession();
                                        ArrayList<Ciudadano> usuario = new ArrayList<>();
                                        usuario.add(c);
                                        sesion.setAttribute("usuario", usuario);
                                        out.print("7");//acceso permitido
                                    }else {
                                        out.print("6");//pregunta y respuesta incorrectas
                                    }
                                } else {
                                    out.print("5");//usuario inactivo
                                }
                            } else {
                                out.print("4");//usuario y contraseña incorrectos
                            }
                        } else {
                            out.print("3");//campos vacios
                        }
                    } else {
                        out.print("2");//redirecciona a la pagina de advertencia
                    }
                } else {
                    out.print("1");//redirecciona a la pagina de advertencia 
                }
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
