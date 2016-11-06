<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>

<%
    HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("usuario") != null) {
        response.sendRedirect("../../tse.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>LOGIN | TSE </title>
        <!-- Favicon-->
        <link rel="icon" href="" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="../../../plugins/iconfont/material-icons.css" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="../../../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="../../../plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="../../../plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="../../../css/style.css" rel="stylesheet">

        <!-- Jquery Core Js -->
        <script src="../../../plugins/jquery/jquery.min.js"></script>

        <script>
            $(document).on("ready", function () {
                $("#btnEntrar").on("click", function () {
                    var chiave1 = window.location.pathname;
                    var chiave2 = $("body").css("background-color");
                    $("#txtChiave1").val(chiave1);
                    $("#txtChiave2").val(chiave2);
                });
            <%
            //Al recibir una respuesta de error desde el servlet se activara el modalAdvertencia
            //Variable que almacenara el mensaje segun el error
            String mensaje = "";
            if (request.getParameter("modalError") != null) {
                String script = "$('#modalAdvertencia').modal('show');";
                out.print(script);
                if (Integer.valueOf(request.getParameter("modalError")) == 1) {
                    mensaje = "Has dejado algun campo vacio que es requerido.<br>";
                    mensaje += "Asegurate de llenar todos los campos";
                } else if (Integer.valueOf(request.getParameter("modalError")) == 2) {
                    mensaje = "Tu cuenta no ha sido activada, es importante que la actives,<br>";
                    mensaje += "es un proceso necesario para tener acceso al sistema.";
                } else if (Integer.valueOf(request.getParameter("modalError")) == 3) {
                    mensaje = "El usuario o la contraseña no eran correctos.<br>";
                    mensaje += "Asegurate que los datos sean correctos";
                } else {
                    mensaje = "Ha ocurrido un error, intenta ingresar otra vez";
                }
            }
            %>
                
            });
        </script>

    </head>
    <body class="login-page">
        <div class="login-box">
            <div class="card">
                <div class="logo">
                    <br>
                    <div class="msg"><img src="../../../images/avatar_admin.png"/></div>
                </div>
                <div class="body" >
                    <form id="sign_in" method="POST" action="../../../SerSesionAdmin">                 
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons col-light-blue">person</i>
                            </span>
                            <div class="form-line">
                                <input type="hidden" class="form-control" name="txtChiave1" id="txtChiave1">
                                <input type="text" class="form-control" name="txtUser" placeholder="Ingresar usuario" required autofocus>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons col-light-blue">lock</i>
                            </span>
                            <div class="form-line">
                                <input type="hidden" class="form-control" name="txtChiave2" id="txtChiave2">
                                <input type="password" class="form-control" name="txtPass" placeholder="Ingresar credencial" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 p-t-5">
                                <input type="checkbox" name="rememberme" id="rememberme" class="filled-in chk-col-light-blue">
                                <label for="rememberme">Recuerdame</label>
                            </div>
                            <div class="col-xs-4">
                                <button class="btn btn-block bg-light-blue waves-effect" type="submit" id="btnEntrar" name="btnEntrar">Entrar</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- Modal Dialogs ====================================================================================================================== -->
        <!-- Modal Notificacion -->
        <div class="modal fade" id="modalAdvertencia" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="defaultModalLabel"><i class="material-icons icons-align col-light-blue">info</i> NOTIFICACIÓN</h5>
                    </div>
                    <div class="modal-body text-center">
                        <div id="mensaje" class="m-r-30 m-l-30 p-b-10 p-t-10">
                            <%= mensaje%>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn col-xs-offset-4 col-xs-4 col-white bg-light-blue waves-effect" data-dismiss="modal">OK, Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- #END# Modal Notificacion -->

        <!-- Bootstrap Core Js -->
        <script src="../../../plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="../../../plugins/node-waves/waves.js"></script>

        <!-- Validation Plugin Js -->
        <script src="../../../plugins/jquery-validation/jquery.validate.js"></script>

        <!-- Custom Js -->
        <script src="../../../js/admin.js"></script>
        <script src="../../../js/pages/login/sign-in.js"></script>
        <script src="../../../js/pages/ui/modals.js"></script>
    </body>
</html>