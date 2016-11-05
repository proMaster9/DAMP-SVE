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
                                $("#" + div).append("<div class='m-t-15 text-justify'><label>Dui Repetido</label></div>");
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
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4 col-sm-offset-0 col-md-offset-4 col-lg-4">
                    <div class="card m-l-15">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                IMPORTAR REGISTROS CNR
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
                            <form method="post" action="../SerCiudadano" id='frmCnr' enctype="multipart/form-data">
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
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>SQL:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="drag-drop">
                                                <input type="file" name="btnArchivo" id="btnSubir" class="form-control">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix acciones">
                                    <div class="col-md-12 align-center">
                                        <button type="button" id="btnImportar" class="btn bg-light-blue waves-effect waves-light">Importar Datos</button>
                                        <button type="reset" id="btnLimpiar" class="btn bg-cyan waves-effect waves-light">Cancelar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <!-- #END# Formulario -->
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="modulos/scripts.jsp"/>
    </body>
</html>
