/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Bitacora;
import modelo.BitacoraDTO;
import modelo.Ciudadano;
import modelo.CiudadanoDTO;
import modelo.ConsultasDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


/**
 *
 * @author Icchigo
 */
public class SerCiudadano extends HttpServlet {

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
        //Procesando el archivo .sql con los datos del cnr
        //en esta ruta se guarda temporalmente el archivo .sql
        Bitacora b = new Bitacora();
        String ruta = getServletContext().getRealPath("/") + "js/";
        if (ServletFileUpload.isMultipartContent(request)) {
            FileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            diskFileItemFactory.setSizeThreshold(40960);
            File repositoryPath = new File("/temp");
            diskFileItemFactory.setRepository(repositoryPath);
            ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
            servletFileUpload.setSizeMax(81920); // bytes
            upload.setSizeMax(307200); // 1024 x 300 = 307200 bytes = 300 Kb
            List listUploadFiles = null;
            FileItem item = null;
            try {
                listUploadFiles = upload.parseRequest(request);
                Iterator it = listUploadFiles.iterator();
                while (it.hasNext()) {
                    item = (FileItem) it.next();
                    //este es el archivo que se envia en el campo file
                    if (!item.isFormField()) {
                        if (item.getSize() > 0) {
                            String nombre = item.getName();
                            String tipo = item.getContentType();
                            long tamanio = item.getSize();
                            String extension = nombre.substring(nombre.lastIndexOf("."));
                            File archivo = new File(ruta, nombre);
                            item.write(archivo);
                            if (archivo.exists()) {
                                String script = ruta + "" + nombre;
                                //consulta para importar registros
                                if (ConsultasDTO.ejecutar("copy padronelectoral from '" + script + "' with (delimiter ',')")) {
                                    out.print("Registros importados correctamente");
                                } else {
                                    out.print("Hubo un error");
                                }

                            } else {
                                out.println("FALLO AL GUARDAR. NO EXISTE " + archivo.getAbsolutePath() + "</p>");
                            }
                        }
                    } else {
                        //ac recogemos los duis de los magistrados
                        if (item.getFieldName().equals("dui1")) {
                            b.setMagistrado1(item.getString());
                        }
                        if (item.getFieldName().equals("dui2")) {
                            b.setMagistrado2(item.getString());
                        }
                        if (item.getFieldName().equals("dui3")) {
                            b.setMagistrado3(item.getString());
                        }

                    }
                }
                //registramos en la base de datos los duis de los magistrados
                //que autorizaron la insercion de datos CNR
                b.setAccion("Registro de datos CNR");
                if (BitacoraDTO.agregarBitacora(b)) {
                    out.print("<br>Bitacora agregada");
                } else {
                    out.print("<br>Hubo un error al agregar la bitacora");
                }

            } catch (FileUploadException e) {
                out.println("Error Upload: " + e.getMessage());
                e.printStackTrace();
            } catch (Exception e) {
                out.println("Error otros: " + e.getMessage());
                e.printStackTrace();
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
        PrintWriter out = response.getWriter();
        if (request.getParameter("dui") != null && request.getParameter("tipo") != null) {
            if (Integer.parseInt(request.getParameter("tipo")) == 0) {
                Ciudadano c = CiudadanoDTO.mostrarVotante(request.getParameter("dui"));
                if (c.getIdMunicipio() != 0) {
                    out.print("<input type='hidden' name='txtResultado' id='txtResultado' value='1'>");
                    out.print("Nombre: " + c.getNombre() + "<br>");
                    out.print("Apellido: " + c.getApellido() + "<br>");
                    if (c.getSexo().equals("m")) {
                        out.print("Genero: Masculino<br>");
                    } else {
                        out.print("Genero: Femenino<br>");
                    }
                } else {
                    out.print("Ciudadano no encontrado<br>");
                    out.print("<input type='hidden' name='txtResultado' id='txtResultado' value='0'>");
                }
            } else {
                Ciudadano c = CiudadanoDTO.mostrarVotante(request.getParameter("dui"));
                if (c.getIdMunicipio() != 0) {
                    out.print("<input type='hidden' name='txtResultado' id='txtResultado' value='1'>");
                    out.print("Correcto");
                } else {
                    out.print("<input type='hidden' name='txtResultado' id='txtResultado' value='0'>");
                    out.print("Incorrecto");
                }
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
