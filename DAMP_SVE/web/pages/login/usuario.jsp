<%-- 
    Document   : principal
    Created on : 14-oct-2016, 12:34:55
    Author     : Icchigo
--%>

<%
    HttpSession sesion = request.getSession(true);
    if(sesion.getAttribute("usuario")!=null){
     response.sendRedirect("../tse.jsp");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Login Usuario | TSE </title>
        <!-- Favicon-->
        <link rel="icon" href="" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="../../plugins/iconfont/material-icons.css" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="../../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="../../plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Animation Css -->
        <link href="../../plugins/animate-css/animate.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="../../css/style.css" rel="stylesheet">
    </head>
    <body class="login-page">
        <div class="login-box">
            <div class="card">
                <div class="logo">
                    <br>
                    <div class="msg"><img src="tse-logo.jpg"/></div>
                </div>
                <div class="body" >
                    <form id="sign_in" method="post" action="../../SerSesionUsuario">                 
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons col-light-blue">person</i>
                            </span>
                            <div class="form-line">
                                <input type="text" class="form-control" name="txtUser" maxlength="10" minlength="10" placeholder="Ingrese su número de dui" required autofocus>
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons col-light-blue">lock</i>
                            </span>
                            <div class="form-line">
                                <input type="password" class="form-control" name="txtPass" placeholder="Ingrese su contraseña" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8 p-t-5">
                                <input type="checkbox" name="rememberme" id="rememberme" class="filled-in chk-col-light-blue">
                                <label for="rememberme">Recuerdame</label>
                            </div>
                            <div class="col-xs-4">
                                <button class="btn btn-block bg-light-blue waves-effect" type="submit" name="btnEntrar">Entrar</button>
                            </div>
                        </div>
                        <div class="row m-t-15 m-b--20">
                            <div class="col-xs-5">
                                <a href="../procesos/activar_cuenta.jsp">Activar cuenta</a>
                            </div>
                            <div class="col-xs-7 align-right">
                                <a href="../procesos/recuperar_cuenta.jsp">¿Olvido su contraseña?</a>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Jquery Core Js -->
        <script src="../../plugins/jquery/jquery.min.js"></script>

        <!-- Bootstrap Core Js -->
        <script src="../../plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="../../plugins/node-waves/waves.js"></script>

        <!-- Validation Plugin Js -->
        <script src="../../plugins/jquery-validation/jquery.validate.js"></script>

        <!-- Custom Js -->
        <script src="../../js/admin.js"></script>
        <script src="../../js/pages/login/sign-in.js"></script>
        <script src="../../../js/pages/ui/modals.js"></script>
    </body>
</html>