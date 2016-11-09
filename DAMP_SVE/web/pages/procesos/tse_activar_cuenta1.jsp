<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Activar Cuenta | TSE </title>
        <!-- Favicon-->
        <link rel="icon" href="../images/tse_logo.png" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="../../plugins/iconfont/material-icons.css" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="../../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="../../plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="../../plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Bootstrap Select Css -->
        <link href="../../plugins/bootstrap-select/css/bootstrap-select.css" rel="stylesheet" />
        <!-- Custom Css -->
        <link href="../../css/style.css" rel="stylesheet">

        <link href="../../css/themes/all-themes.css" rel="stylesheet" />

        <!-- Jquery Core Js -->
        <script src="../../plugins/jquery/jquery.min.js"></script>
        <script>
            $(document).on("ready", function () {
                $("#btnValidar").on("click", function () {
                    var user = $("#txtUser").val();
                    var pass = $("#txtPass").val();
                    $.post('../../SerActivarCuenta', {
                        txtPrincipal: 'true',
                        txtUser: user,
                        txtPass: pass
                    }, function (data) {
                        if (data === "1") {
                            $('#mensaje').html("Su cuenta no esta activada.<br>");
                            $('#mensaje').append("Se comenzará con el proceso de activación");
                            $('#modalNoti').modal('show');
                            $("#divDis").html("");
                            $.post('../../SerActivarCuenta', {divHtml: 'true'}, function (data) {
                                $('#divHtml').html(data);
                            });
                        } else if (data === "2") {
                            $('#modalNoti').modal('show');
                            $('#mensaje').html("La cuenta ya esta activada.<br>");
                            $('#mensaje').append("<br><center><a href='../login/principal.jsp'><i class='material-icons icons-align col-light-blue'>contacts</i> Iniciar Sesión</a></center>");
                            $('#divBtn').html('<button type="button" class="btn col-xs-offset-4 col-xs-4 col-white bg-light-blue waves-effect" data-dismiss="modal">OK, Cerrar</button>');
                        } else {
                            $('#modalNoti').modal('show');
                            $('#mensaje').html("Ha dejado campos vacios, que son requeridos");
                            $('#divBtn').html('<button type="button" class="btn col-xs-offset-4 col-xs-4 col-white bg-light-blue waves-effect" data-dismiss="modal">OK, Cerrar</button>');
                        }

                    });

                });
                $("#btnValidar2").on("click", function () {
                    var user = $("#txtUser").val();
                    var pass = $("#txtPass").val();
                    var pass2 = $("#txtPass2").val();
                    var pregunta = $("#slPregunta").val();
                    var respuesta = $("#txtRespuesta").val();
                    $.post('../../SerActivarCuenta', {
                        txtActivar: 'true',
                        txtUser: user,
                        txtPass: pass,
                        txtPass2: pass2,
                        idPregunta: pregunta,
                        txtRespuesta: respuesta

                    }, function (data) {
                         $('#modalNoti').modal('show');
                         $('#mensaje').html(data);
                    });
                    
                });
            });
        </script>
    </head>
    <body class="login-page">
        <div class="login-box">
            <div class="card">
                <div class="logo">
                    <br>
                    <div class="msg"><img src="../login/tse-logo.jpg"/></div>
                </div>
                <div class="body" >
                    <form method="post">
                        <div class="input-group">
                            <span class="input-group-addon">
                                <small>ACTIVAR CUENTA USUARIOS PRINCIPALES</small>
                            </span>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons col-light-blue">person</i>
                            </span>
                            <div class="form-line">
                                <input type="text" class="form-control" name="txtUser" id="txtUser" maxlength="10" minlength="10" placeholder="Ingrese su número de dui" required autofocus>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons col-light-blue">lock</i>
                            </span>
                            <div class="form-line">
                                <input type="password" class="form-control" name="txtPass" id="txtPass" placeholder="Ingrese su contraseña" required>
                            </div>
                        </div>
                        <div id="divHtml">


                        </div>
                        <div class="row">
                            <div class=" col-xs-offset-4 col-xs-4">
                                <div id="divDis">
                                <button class="btn btn-block bg-light-blue waves-effect" type="button" name="btnValidar" id="btnValidar">Verificar</button>
                                </div>
                                <br>
                                <button class="btn btn-block bg-light-blue waves-effect" type="button" name="btnValidar2" id="btnValidar2">enviar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal Dialogs ====================================================================================================================== -->
        <!-- Modal Notificacion -->
        <div class="modal fade" id="modalNoti" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="defaultModalLabel"><i class="material-icons icons-align col-light-blue">info</i> NOTIFICACIÓN</h5>
                    </div>
                    <div class="modal-body text-center">
                        <div id="mensaje" class="m-r-30 m-l-30 p-b-10 p-t-10 p-r-5 p-l-5 text-justify">

                        </div>
                    </div>
                    <div class="modal-footer">
                        <div id="divBtn">
                            <button type="button" class="btn col-xs-offset-4 col-xs-4 col-white bg-light-blue waves-effect" data-dismiss="modal">OK, Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- #END# Modal Notificacion -->

        <!-- Bootstrap Core Js -->
        <script src="../../plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Select Plugin Js -->
        <script src="../../plugins/bootstrap-select/js/bootstrap-select.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="../../plugins/node-waves/waves.js"></script>

        <!-- Validation Plugin Js -->
        <script src="../../plugins/jquery-validation/jquery.validate.js"></script>

        <!-- Custom Js -->
        <script src="../../js/admin.js"></script>
        <script src="../../js/pages/login/sign-in.js"></script>
        <script src="../../js/pages/ui/modals.js"></script>
    </body>
</html>

