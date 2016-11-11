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
import modelo.Partido;
import modelo.PartidoDTO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author EQUIPO DAMP-2016
 */
public class SerPartido extends HttpServlet {

    private String redireccionJSP = "pages/tse_partido.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        //ruta relativa en donde se guardan las imagenes de partidos
        String ruta = getServletContext().getRealPath("/")+"images/files/banderas/";//imagenes de los partidos politicos
        Partido p = new Partido();
        int accion = 1; //1=gregar  2=modificar
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
                    if (!item.isFormField()) {
                        if (item.getSize() > 0) {
                            String nombre = item.getName();
                            String tipo = item.getContentType();
                            long tamanio = item.getSize();
                            String extension = nombre.substring(nombre.lastIndexOf("."));
                            File archivo = new File(ruta, nombre);
                            item.write(archivo);
                            if (archivo.exists()) {
                                p.setImagen(nombre);
                            } else {
                                out.println("FALLO AL GUARDAR. NO EXISTE " + archivo.getAbsolutePath() + "</p>");
                            }
                        }
                    } else {
                        //se reciben los campos de texto enviados y se igualan a los atributos del objeto
                        if (item.getFieldName().equals("txtAcronimo")) {
                            p.setAcronimo(item.getString());
                        }
                        if (item.getFieldName().equals("txtNombre")) {
                            p.setNombre(item.getString());
                        }
                        if (item.getFieldName().equals("txtDui")) {
                            p.setNumDui(item.getString());
                        }
                        if(item.getFieldName().equals("txtId")) {
                            p.setIdPartido(Integer.parseInt(item.getString()));
                        }
  
                    }
                }         
                //si no se selecciono una imagen distinta, se conserva la imagen anterior
                if(p.getImagen() == null) {
                    p.setImagen(PartidoDTO.mostrarPartido(p.getIdPartido()).getImagen());
                }

                //cuando se presiona el boton de agregar
                if(p.getIdPartido() == 0) {
                     if (PartidoDTO.agregarPartido(p)) {
                        response.sendRedirect(this.redireccionJSP);
                    } else {
                        //cambiar por alguna accion en caso de error
                        out.print("Error al insertar");
                    }
                } 
                //cuando se presiona el boton de modificar
                else {
                    if(PartidoDTO.modificarPartido(p)) {
                        response.sendRedirect(this.redireccionJSP);
                    } else {
                        out.print("Error al modificar");
                    }
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
        PrintWriter out = response.getWriter();
        //eliminar registro de partido
        if(request.getParameter("idPartido") != null) {
            int id = Integer.parseInt(request.getParameter("idPartido"));
            if(PartidoDTO.eliminarPartido(id)) {
                response.sendRedirect(this.redireccionJSP);
            }
            else {
                out.print("Error al eliminar");
            }
        }
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
