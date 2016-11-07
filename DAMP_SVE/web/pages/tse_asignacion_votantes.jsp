<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>
<%@page import="controlador.SerAsignacionVotantes"%>
<%@page import="modelo.Ciudadano"%>
<%@page import="java.util.ArrayList"%>
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
        <title>Asignación de Votantes | TSE</title>
        <jsp:include page="modulos/head.jsp"/>
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

                $("#btnAsignar").on("click", function () {
                    var coincidencia = 0;
                    //se recorren los campos ocultos, para verificar que todos tengan un resultado correcto
                    $("input[data-tipo=resultado]").each(function (i) {
                        if ($(this).val() == 0) {
                            coincidencia++;
                        }
                    });
                    if (coincidencia == 0) {
                        var dui1 = $("#dui1").val();
                        var dui2 = $("#dui2").val();
                        var dui3 = $("#dui3").val();
                        $.post('../SerAsignacionVotantes', {
                            btnAsignacion: true,
                            txtDui1: dui1,
                            txtDui2: dui2,
                            txtDui3: dui3
                        }, function (data) {
                            //Se muestra una alerta con un mensaje de exito
                            alert(data);
                            //se oculta el formulario y cambia el mensaje
                            $("#divNoticia").html("Nota: los votantes han sido asignados correctamente a los centros de votacion");
                            $("#frmAsignacion").hide();
                        });
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
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                    <div class="card m-l-15">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                ASIGNAR VOTANTES
                            </h2>
                            <ul class="header-dropdown m-r-0">
                                <li>
                                    <a data-toggle="modal" href="#modalInfoF" >
                                        <i class="material-icons col-light-blue">info_outline</i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="body" id="divAsignacion">
                            <% if (SerAsignacionVotantes.cantidadAsignacion() == 0) {%>
                            <label id="divNoticia">
                                Nota: los ciudadanos aun no han sido asignados a los centros de votacion<br>

                            </label>
                            <form id="frmAsignacion" enctype="multipart/form-data">
                                <div class="row clearfix text-justify">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="demo-google-material-icon">
                                            <i class="material-icons col-green icons-align">assignment_ind</i>
                                            <span>DUI:</span>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="demo-google-material-icon">
                                            <i class="material-icons col-green icons-align">vpn_key</i>
                                            <span>CONTRASEÑA:</span>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="demo-google-material-icon">
                                            <i class="material-icons col-green icons-align">error</i>
                                            <span>ESTADO</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix text-center">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" id="dui1" data-tipo="dui" name="dui1" autocomplete="off" class="form-control" placeholder="Ingresar numero de DUI">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="password" class="form-control" id="password1" placeholder="Ingrese su contraseña" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">

                                        <div id="divMagistrado1">
                                            <input type="hidden" value="0" data-tipo="resultado">
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix text-center">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" id="dui2" data-tipo="dui" name="dui2" autocomplete="off" class="form-control" placeholder="Ingresar numero de DUI">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="password" class="form-control" id="password2" placeholder="Ingrese su contraseña" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">

                                        <div id="divMagistrado2">
                                            <input type="hidden" value="0" data-tipo="resultado">
                                        </div>

                                    </div>
                                </div>
                                <div class="row clearfix text-center">
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" id="dui3" data-tipo="dui" name="dui3" autocomplete="off" class="form-control" placeholder="Ingresar numero de DUI">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="password" class="form-control" id="password3" placeholder="Ingrese su contraseña" required>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
                                        <div id="divMagistrado3">
                                            <input type="hidden" value="0" data-tipo="resultado">
                                        </div>

                                    </div>
                                </div>
                                <div class="row clearfix acciones">
                                    <div class="col-md-12 align-center">
                                        <button type="button"  id="btnAsignar" class="btn bg-light-blue waves-effect waves-light">Asignar</button>
                                        <button type="reset" id="btnLimpiar" class="btn bg-cyan waves-effect waves-light">Cancelar</button>
                                    </div>
                                </div>
                            </form>
                            <!--Fin de accion-->
                            <!--Accion en caso de que los votantes esten asignados-->
                            <% } else {%>
                            Nota: los votantes han sido asignados correctamente a los centros de votacion<br>
                            <!--Impresion de datos estadisticos-->
                            Total de centros de votacion: <%= SerAsignacionVotantes.totalCentros()%><br>
                            Total de votantes: <%= SerAsignacionVotantes.totalVotantes()%><br>

                            <% }%>
                            <!--Fin de la accion-->
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
                                VOTANTES ASIGNADOS
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
                                        <th>Departamento:</th>
                                        <th>Municipio:</th>
                                        <th>Total de Centros:</th>
                                        <th>Num. Votantes:</th>
                                        <th>Votantes por Centro:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%= SerAsignacionVotantes.mostrarDistribucion()%>
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