<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>
<%@page import="controlador.SerMunicipio"%>
<%@page import="modelo.CentroVotacion"%>
<%@page import="controlador.SerAsignacionVotantes"%>
<%@page import="modelo.Ciudadano"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("usuario") != null) {
        ArrayList<Ciudadano> usuario = (ArrayList<Ciudadano>) sesion.getAttribute("usuario");

        //el id del director de centros de votacion que ha iniciado sesion
        //si lo probas asi, revisa que id de usuario tienen los directores de centro de votacion
        int idUsuario = usuario.get(0).getIdUsuario();
        int idCentro = SerAsignacionVotantes.getIdCentroVotacion(idUsuario);
        //se almacena la informacion del centro votacion
        CentroVotacion c = SerAsignacionVotantes.informacionCentro(idCentro);

%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Detalle Centro Votación | TSE</title>
        <jsp:include page="modulos/head.jsp"/>
    </head>
    <body class="theme-light-blue">
        <!-- Overlay For Sidebars -->
        <div class="overlay"></div>
        <!-- #END# Overlay For Sidebars -->
        <!-- Top Bar -->
        <jsp:include page="modulos/navbar.jsp"/>
        <!-- #Top Bar -->

        <section>
            <!-- Left Sidebar -->
            <aside id="leftsidebar" class="sidebar">
                <!-- Menu -->
                <jsp:include page="modulos/menu.jsp"/>
                <!-- #Menu -->
                <!-- Footer -->
                <jsp:include page="modulos/footer.jsp"/>
                <!-- #Footer -->
            </aside>
            <!-- #END# Left Sidebar -->
            <!-- Right Sidebar -->
            <aside id="rightsidebar" class="right-sidebar">
                <jsp:include page="modulos/opciones.jsp"/>
            </aside>
            <!-- #END# Right Sidebar -->
        </section>

        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2></h2>
                </div>
            </div>
            <div class="row clearfix" >
                <!--Tabla-->
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="card m-l-15">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">assignment_ind</i>
                                DETALLE CENTRO VOTACIÓN
                            </h2>
                            <ul class="header-dropdown m-r-0">
                                <li>
                                    <a href="javascript:void(0);" >
                                        <i class="material-icons col-light-blue">save</i>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="material-icons col-light-blue">print</i>
                                    </a>
                                </li>
                                <li>
                                    <a data-toggle="modal" href="#modalInfoT" >
                                        <i class="material-icons col-light-blue">info_outline</i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="body">
                            <div class="row clearfix" >
                                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                                    <label class="text-left"> 
                                        Centro: <%= c.getNombreCentro()%><br>
                                        Departamento: <%= SerMunicipio.obtenerDepartamento(c.getIdMunicipio())%><br>
                                        Municipio: <%= SerMunicipio.obtenerMunicipio(c.getIdMunicipio())%><br>
                                        Direccion: <%= c.getDireccion()%><br>
                                        Total de personas: <%= SerAsignacionVotantes.totalPersonasCentro(idCentro)%>
                                    </label>
                                </div>
                            </div>
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable text-center">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Correlativo JRV:</th>
                                        <th>Ciudadano:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%= SerAsignacionVotantes.mostrarAsignacionCentro(idCentro) %>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- #END# Tabla -->
            </div>
        </section>
        <jsp:include page="modulos/scripts.jsp"/>
    </body>
</html>
<% } else {
        response.sendRedirect("login/usuario.jsp");
    }
%>