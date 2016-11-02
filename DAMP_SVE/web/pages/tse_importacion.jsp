<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:30:00
    Author     : EQUIPO DAMP-2016
--%>

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
        <title>Importación Datos| TSE</title>
        <jsp:include page="modulos/head.jsp"/>
        <script src="../js/funciones.js"></script>
        <script>
            $(document).on("ready", function () {

                function buscarMagistrado(dui, password, div, servlet) {
                    $("#" + password + ", #" + dui).on("keyup", function () {
                        var coincidencia = 0;
                        //se recorren todos los campos donde se ingresan los duis
                        $("input[data-tipo=dui]").each(function (i) {
                            //evaluamos si hay campos repetidos
                            if ($(this).val() == $("#" + dui).val()) {
                                coincidencia++;
                            }
                        });
                        //si el valor de la caja de texo se repite solo con ella misma
                        //significa que el campo no se ha duplicado
                        if (coincidencia == 1) {
                            //se verifica que el campo dui no este vacio
                            if ($("#" + dui).val() != "") {
                                $.post(servlet, {
                                    dui: $("#" + dui).val(),
                                    password: $("#" + password).val()
                                }, function (data) {
                                    $("#" + div).html(data);
                                });
                            }
                        } else {
                            $("#" + div).html('<input type="hidden" value="0" data-tipo="resultado">');
                            if ($("#" + dui).val() != "") {
                                $("#" + div).append("Dui repetido");
                            }
                        }

                    });
                }

                $("#btnImportar").on("click", function () {
                    var coincidencia = 0;
                    //se recorren los campos ocultos, para verificar que todos tengan un resultado correcto
                    $("input[data-tipo=resultado]").each(function (i) {
                        if ($(this).val() == 0) {
                            coincidencia++;
                        }
                    });
                    if (coincidencia == 0) {
                        $("#frmCnr").submit();
                    } else {
                        alert("No puedes enviar");
                    }
                });
                buscarMagistrado('dui1', 'password1', 'divMagistrado1', '../SerMagistrado');
                buscarMagistrado('dui2', 'password2', 'divMagistrado2', '../SerMagistrado');
                buscarMagistrado('dui3', 'password3', 'divMagistrado3', '../SerMagistrado');
            });
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
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="card">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                IMPORTACION DE DATOS
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
                            <form id="frmCnr" action="../SerCiudadano" method="post" enctype="multipart/form-data">

                                <table>
                                    <tr>
                                        <th>Identificacion</th>
                                        <th>Contraseña</th>
                                        <th>Estado</th>
                                    </tr>
                                    <!--Magistrado 1-->
                                    <tr>
                                        <td><input type="text" id="dui1" data-tipo="dui" name="dui1"></td>
                                        <td><input type="password" id="password1"></td>
                                        <td>
                                            <div id="divMagistrado1">
                                                <input type="hidden" value="0" data-tipo="resultado">
                                            </div>
                                        </td>
                                    </tr>
                                    <!--Magistrado 2-->
                                    <tr>
                                        <td><input type="text" id="dui2" data-tipo="dui" name="dui2"></td>
                                        <td><input type="password" id="password2"></td>
                                        <td>
                                            <div id="divMagistrado2">
                                                <input type="hidden" value="0" data-tipo="resultado">
                                            </div>
                                        </td>
                                    </tr>
                                    <!--Magistrado 3-->
                                    <tr>
                                        <td><input type="text" id="dui3" data-tipo="dui" name="dui3"></td>
                                        <td><input type="password" id="password3"></td>
                                        <td>
                                            <div id="divMagistrado3">
                                                <input type="hidden" value="0" data-tipo="resultado">
                                            </div>
                                        </td>
                                    </tr>
                                </table>
                                <input type="file" name="btnArchivo" id="btnSubir" class="btn bg-light-blue waves-effect waves-light" ><br>
                                
                                <input type="button" id="btnImportar" value="Importar datos" class="btn bg-light-blue waves-effect waves-light" >
                                <button type="reset" class="btn bg-cyan waves-effect waves-light">Limpiar</button>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- #END# Formulario -->
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
                                ¿Desea modificar el usuario seleccionado? 
                            </h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary waves-effect">Aceptar</button>
                            <button type="button" class="btn btn-primary waves-effect" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Modificar -->
            <!-- Modal CONFIRMACION Modificado -->
            <div class="modal fade" id="modalGuardarModificacion" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="defaultModalLabel">
                                <i class="material-icons icons-align col-light-blue">warning</i>
                                Desea guardar la modificacion realiizada
                            </h3>
                        </div>                        
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary waves-effect">Aceptar</button>
                            <button type="button" class="btn btn-primary waves-effect" data-dismiss="modal">Cancelar</button>                  
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# CONFIRMACION Modificado -->
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
                            <button type="button" class="btn btn-primary waves-effect">Si, Eliminar</button>
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
