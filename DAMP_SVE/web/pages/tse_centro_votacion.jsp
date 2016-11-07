<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:30:00
    Author     : EQUIPO DAMP-2016
--%>

<%@page import="modelo.Ciudadano"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.CentroVotacionDTO"%>
<%@page import="modelo.CentroVotacion"%>
<%@page import="modelo.MunicipioDTO"%>
<%@page import="modelo.Municipio"%>
<%@page import="modelo.Departamento"%>
<%@page import="modelo.DepartamentoDTO"%>
<%@page import="modelo.CiudadanoDTO"%>
<%@page import="modelo.Candidato"%>
<%@page import="modelo.CandidatoDTO"%>
<%
    HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("usuario") != null) {
        ArrayList<Ciudadano> usuario = (ArrayList<Ciudadano>) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Centros de Votación | TSE</title>
        <jsp:include page="modulos/head.jsp"/>
        <script src="../js/funciones.js"></script>
        <script>
            $(document).on("ready", function () {
                mostrarCiudadano('txtDui', 'divCiudadano', '../SerCiudadano', 0);
                $("#btnAgregar").on("click", function () {
                    var nombre = $("#txtNombre").val();
                    var departamento = $("#txtDepartamento").val();
                    var jrv = $("#txtJrv").val();
                    var dui = $("#txtDui").val();
                    var municipio = $("#slMunicipio").val();
                    var direccion = $("#txtDireccion").val();
                    if ($("#txtResultado").val() == 0) {
                        alert("No puedes enviar");
                    } else {
                        $.post('../SerCentroVotacion', {
                            btnAgregar: 'true',
                            txtDepartamento: departamento,
                            txtNombre: nombre,
                            txtJrv: jrv,
                            txtDui: dui,
                            slMunicipio: municipio,
                            txtDireccion: direccion
                        }, function (data) {
                            $("#divCentro").html(data);
                        });
                    }
                });

                //modificar un centro de votacion via ajax
                $("#btnModificar").on("click", function () {
                    var idCentro = $("#txtIdCentro").val();
                    var nombre = $("#txtNombre").val();
                    var departamento = $("#txtDepartamento").val();
                    var jrv = $("#txtJrv").val();
                    var dui = $("#txtDui").val();
                    var municipio = $("#slMunicipio").val();
                    var direccion = $("#txtDireccion").val();
                    $.post('../SerCentroVotacion', {
                        btnModificar: 'true',
                        txtIdCentro: idCentro,
                        txtDepartamento: departamento,
                        txtNombre: nombre,
                        txtJrv: jrv,
                        txtDui: dui,
                        slMunicipio: municipio,
                        txtDireccion: direccion
                    }, function (data) {
                        $("#divCentro").html(data);
                    });
                });

            });
            function cargar(idCentro, nombre, numJrv, dui, municipio, direccion) {
                $("#txtIdCentro").val(idCentro);
                $("#txtNombre").val(nombre);
                $("#txtJrv").val(numJrv);
                $("#txtDui").val(dui);
                $("#slMunicipio").val(municipio);
                $("#txtDireccion").val(direccion);
                $("#txtResultado").val(1);
            }
            //eliminar centros de votacion
            function eliminar(idCentro) {
                var departamento = $("#txtDepartamento").val();
                $.post('../SerCentroVotacion', {
                    btnEliminar: 'true',
                    txtIdCentro: idCentro,
                    txtDepartamento: departamento
                }, function (data) {
                    $("#divCentro").html(data);
                });
            }
        </script>
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
            <%
                //hagamos de cuenta que el gestor de jrv es de san ahuachapan,
                //el departamento 1, este valor se tomaria de la variable de sesion
                int departamento = 2;
            %>
            <div class="row clearfix" >
                <!--Formulario-->
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                    <div class="card m-l-15">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                AGREGAR CENTRO DE VOTACIÓN
                            </h2>
                            <ul class="header-dropdown m-r-0">
                                <li>
                                    <a data-toggle="modal" href="#modalInfoF" >
                                        <i class="material-icons col-light-blue">info_outline</i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="body">
                            <form method="post" name="frmCentro" id='frmCentro' enctype="multipart/form-data">
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Nombre:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="hidden" name="txtDepartamento" id="txtDepartamento" value="<%= departamento%>">
                                                <input type='hidden' name='txtIdCentro' id='txtIdCentro'>
                                                <input type="text" name="txtNombre" id="txtNombre" class="form-control" placeholder="Ingresar nombre del centro">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>JRV's Disponibles:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="number" name="txtJrv" id="txtJrv" class="form-control" placeholder="Ingresar número de jrv disponibles">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Representante:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">                     
                                                <input type="text" name="txtDui" id="txtDui" autocomplete="off" class="form-control" placeholder="Ingresar número de DUI">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-12 align-center">
                                        <!--Impresion de resultados de busqueda-->
                                        <div id="divCiudadano"> 
                                            <input type="hidden" name="txtResultado" id="txtResultado">
                                        </div>
                                        <!--Fin de impresion de resultados-->
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Municipio:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <select class="form-control show-tick" data-live-search="true" name="slMunicipio" id="slMunicipio" >
                                                <option value="0">Seleccione municipio</option>
                                                <%
                                                    for (Municipio m : MunicipioDTO.mostrarMunicipiosDep(departamento)) {
                                                %>
                                                <option value='<%= m.getIdMunicipio()%>'><%= m.getNombreMunicipio()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Dirección:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" class="form-control" name="txtDireccion" id="txtDireccion" placeholder="Ingresar domicilio">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix acciones">
                                    <div class="col-md-12 align-center">
                                        <button type="button" name="btnAgregar" id="btnAgregar" class="btn bg-light-blue waves-effect waves-light">Agregar</button>
                                        <button type="button" name="btnModificar" id="btnModificar" class="btn bg-light-blue waves-effect waves-light">Modificar</button>
                                        <button type="reset" class="btn bg-cyan waves-effect waves-light">Cancelar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- #END# Formulario -->
                <!--Tabla-->
                <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                    <div class="card">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">assignment_ind</i>
                                CENTROS DE VOTACIÓN REGISTRADOS
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
                        <div class="body ">
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable text-center">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Centro</th>
                                        <th>JRV</th>
                                        <th>Encargado</th>
                                        <th>Municipio</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody id="divCentro">
                                    <% int listador = 1;
                                        for (CentroVotacion c : CentroVotacionDTO.mostrarCentrosDep(departamento)) {%>
                                    <tr>
                                        <td><%= listador%></td>
                                        <td><%= c.getNombreCentro()%></td>
                                        <td><%= c.getNumJrvDisponible()%></td>
                                        <!--Impresio del numero de dui, apellidos y nombres del director de centro de votacion-->
                                        <td><%= c.getNumDui()%>, <%= CiudadanoDTO.mostrarVotante(c.getNumDui()).getApellido()%> <%= CiudadanoDTO.mostrarVotante(c.getNumDui()).getNombre()%></td>
                                        <td><%= MunicipioDTO.mostrarUnMunicipio(c.getIdMunicipio()).getNombreMunicipio()%></td>
                                        <td>
                                            <a class="btn bg-cyan waves-effect m-r-0 waves-light" href="javascript:cargar('<%= c.getIdCentroVotacion()%>','<%= c.getNombreCentro()%>','<%= c.getNumJrvDisponible()%>','<%= c.getNumDui()%>','<%= c.getIdMunicipio()%>','<%= c.getDireccion()%>')"><i class="material-icons">create</i></a>
                                            <a class="btn bg-grey waves-effect m-r-0 waves-light" href="javascript:eliminar('<%= c.getIdCentroVotacion()%>')"><i class="material-icons">delete_forever</i></a>
                                        </td>
                                    </tr>
                                    <% listador++;
                                        }%>
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