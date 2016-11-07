<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:30:00
    Author     : EQUIPO DAMP-2016
--%>
<%@page import="modelo.Ciudadano"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("usuario") != null) {
        ArrayList<Ciudadano> usuario = (ArrayList<Ciudadano>) sesion.getAttribute("usuario");
%>
<%@page import="modelo.Departamento"%>
<%@page import="modelo.DepartamentoDTO"%>
<%@page import="modelo.CiudadanoDTO"%>
<%@page import="modelo.Candidato"%>
<%@page import="modelo.CandidatoDTO"%>
<%@page import="modelo.Partido"%>
<%@page import="modelo.PartidoDTO"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Candidatos Independientes | TSE</title>
        <jsp:include page="modulos/head.jsp"/>
        <script src="../js/funciones.js"></script>
                <script>
            $(document).on("ready", function () {
                $("#btnModificar").prop("disabled", "disabled");
                enviarForm('frmCandidato', 'btnAgregar');
                cargarImagen("btnImagen", "divImg");
                //evitar la actualizacion si no se selecciona un registro
                $("#btnModificar").on("click", function () {
                    var modificar = $("#txtId").val();
                    if (modificar != 0) {
                        $("#frmCandidato").submit();
                    }
                    else {
                        alert("No puedes modificar");
                    }
                });
                $("#txtDuiCandidato").on("keyup", function () {
                    var dui = $("#txtDuiCandidato").val();
                    var longitud = dui.length;
                    if (longitud >= 10) {
                        $.post('../SerCandidato', {
                            txtDuiCandidato: dui
                        }, function (data) {
                            $("#divCiudadano").html(data);
                        });
                    }
                    else {
                        $("#divCiudadano").html("<input type='hidden' name='txtResultado' id='txtResultado'>");
                    }
                });
            });
            function modificar(idCandidato, idPartido, idDepartamento, dui, foto) {
                $("#txtId").val(idCandidato);
                $("#slPartido").val(idPartido);
                $("#slDepartamento").val(idDepartamento);
                $("#txtDuiCandidato").val(dui);
                $("#divImg").html(foto);
                $("#btnAgregar").prop("disabled", "disabled");
                $("#btnModificar").prop("disabled", false);
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
            <div class="row clearfix" >
                <!--Formulario-->
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                    <div class="card m-l-15">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                AGREGAR CANDIDATO INDEPENDIENTE
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
                            <form method="post" name="frmCandidato" id="frmCandidato" action="../SerCandidato" enctype="multipart/form-data">
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Departamento:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <select class="form-control show-tick" data-live-search="true" name="slDepartamento" id="slDepartamento">
                                                <option value="0">Seleccione departamento</option>
                                                <% for (Departamento d : DepartamentoDTO.mostrarDepartamentos()) {%>
                                                <option value="<%= d.getIdDepartamento()%>"><%= d.getDepartamento()%></option>
                                                <% } %>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>DUI:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="hidden" name="txtId" id="txtId" value="0">
                                                <input type="text" name="txtDui" id="txtDuiCandidato" autocomplete="off" class="form-control" placeholder="Ingresar numero de DUI">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Foto:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="drag-drop">
                                                <input type="file" name="btnImagen" id="btnImagen" class="form-control">
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
                                    <div class="col-lg-offset-3 col-lg-6">
                                        <!--Mostrando imagen seleccionada-->
                                        <div id="divImg" class="img-responsive thumbnail">Vista Previa</div>
                                        <!-- EDN# imagen seleccionada-->
                                    </div>
                                </div>
                            </form>
                            <div class="row clearfix acciones">
                                <div class="col-md-12 align-center">
                                    <input type="button" value="Agregar" class="btn bg-light-blue waves-effect waves-light" id="btnAgregar">
                                    <button type="button" value="Modificar" id="btnModificar" class="btn bg-light-blue waves-effect waves-light">Modificar</button>
                                    <button type="reset" id="btnLimpiar" class="btn bg-cyan waves-effect waves-light">Limpiar</button>
                                </div>
                            </div>
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
                                CANDIDATOS REGISTRADOS EN EL SISTEMA
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
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable text-center">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Departamento</th>
                                        <th>Candidato</th>
                                        <th>Nombre</th>
                                        <th>Foto</th>
                                        <th>Opciones</th>
                                    </tr>
                                </thead>
                                <tbody id="divCandidato">
                                    <% int listador = 1;
                                        for (Candidato c : CandidatoDTO.mostrarCandidatos()) {
                                            if (c.getTipo() == 2) {
                                    %>

                                    <tr>
                                        <td><%= listador%></td>
                                        <td><%= DepartamentoDTO.mostrarDepartamento(c.getIdDepartamento()).getDepartamento()%></td>
                                        <td><%= c.getNumDui()%></td>
                                        <td>
                                            <%= CiudadanoDTO.mostrarVotante(c.getNumDui()).getApellido()%>, 
                                            <%= CiudadanoDTO.mostrarVotante(c.getNumDui()).getNombre()%>
                                        </td>
                                        <td><img src="../images/files/candidatos/<%= c.getFoto()%>" style="width:32px;height:32px;" ></td>
                                        <td>
                                            <a class="btn bg-cyan waves-effect m-r-0 waves-light" href="javascript:modificar('<%= c.getIdCandidato()%>','<%= c.getIdPartido()%>','<%= c.getIdDepartamento()%>','<%= c.getNumDui()%>','<img src=../images/files/candidatos/<%= c.getFoto()%>>')"><i class="material-icons">create</i></a>
                                            <a class="btn bg-grey waves-effect m-r-0 waves-light" href="../SerCandidato?idCandidato=<%= c.getIdCandidato()%>"><i class="material-icons">delete_forever</i></a>
                                        </td>
                                    </tr>
                                    <% listador++;
                                            }
                                        }%>

                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <!-- #END# Tabla -->
            </div>
            <!-- Modal Dialogs ====================================================================================================================== -->
            <!-- Modal Modificar -->
            <div class="modal fade" id="modalModificar" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="defaultModalLabel">
                                <i class="material-icons icons-align col-light-blue">loop</i>
                                Modificar
                            </h3>
                        </div>
                        <div class="modal-body">
                            <h4>
                                Desea guardar la modificacion realiizada
                            </h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary waves-effect" id="btnModificarG">Aceptar</button>
                            <button type="button" class="btn btn-primary waves-effect" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Modificar -->

            <!-- Modal Eliminar -->
            <div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="defaultModalLabel">
                                <i class="material-icons icons-align col-light-blue">delete</i>
                                Eliminar
                            </h3>
                        </div>
                        <div class="modal-body">
                            <h4>
                                ¿Desea eliminar el usuario seleccionado? 
                            </h4>
                        </div>
                        <div class="modal-footer">
                            <input type="hidden" id="txtIdCandidatoE">
                            <button type="button" class="btn btn-primary waves-effect" id="btnEliminar">Si, Eliminar</button>
                            <button type="button" class="btn btn-primary waves-effect" data-dismiss="modal">No, Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Eliminar -->
            <!-- Modal Confirmaacion de datos guardados con exito-->
            <div class="modal fade" id="modalDatosGuardados" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="defaultModalLabel">
                                <i class="material-icons icons-align col-light-blue">done</i>
                                Datos Guardados Exitosamente
                            </h3>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary waves-effect">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Confirmaacion de datos guardados con exito -->


            <!-- Modal Info F-->
            <div class="modal fade" id="modalInfoF" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="defaultModalLabel">Informacion sobre el formulario</h4>
                        </div>
                        <div class="modal-body">
                            Algunos datos que le podrian ayudar al usuario a registrar otros usuarios
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-link waves-effect" data-dismiss="modal">Ok</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Info T -->
            <!-- Modal Info F-->
            <div class="modal fade" id="modalInfoT" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="defaultModalLabel">Informacion sobre el Tabla</h4>
                        </div>
                        <div class="modal-body">
                            Datos sobre la tabla y los permisos que tienen esos usuarios
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-link waves-effect" data-dismiss="modal">Ok</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Info T -->
        </section>
        <jsp:include page="modulos/scripts.jsp"/>
    </body>
</html>
<% } else {
        response.sendRedirect("login/usuario.jsp");
    }
%>