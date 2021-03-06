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
        <title>Login Administrador | TSE </title>
        <!-- Favicon-->
        <link rel="icon" href="../../../images/tse_logo_ico.png" type="image/x-icon">
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
    </head>
    <body class="login-page">
        <div class="login-box">
            <div class="card">
                <div class="logo">
                    <br>
                    <div class="msg"><img src="../../../images/avatar_admin.png"/></div>
                </div>
                <div class="body" >
                    <form method="post" action="#">                 
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons col-light-blue">person</i>
                            </span>
                            <div class="form-line">
                                <input type="text" class="form-control" name="txtUser" id="txtUser" placeholder="Ingresar usuario" required autofocus autocomplete="off">
                            </div>
                        </div>
                        <div class="input-group">
                            <span class="input-group-addon">
                                <i class="material-icons col-light-blue">lock</i>
                            </span>
                            <div class="form-line">
                                <input type="password" class="form-control" name="txtPass" id="txtPass" placeholder="Ingresar credencial" required autocomplete="off">
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-8">
                                <label><a href='../../../index.jsp' class='text-right'><i class='material-icons icons-align col-light-blue'>reply</i>REGRESAR</a></label>
                            </div>
                            <div class="col-xs-4">
                                <button class="btn btn-block bg-light-blue waves-effect" type="button" id="btnEntrar" name="btnEntrar">ENTRAR</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- ======================================Modal Dialogs==================================== -->
        <!-- Modal Notificacion -->
        <div class="modal fade animated" id="modalAdvertencia" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="defaultModalLabel"><i class="material-icons icons-align col-light-blue">info</i> NOTIFICACIÓN</h5>
                    </div>
                    <div class="modal-body text-center">
                        <div class="mensaje">
                            <div class="row"><div class="grafico"></div></div>
                            <div class="row"><div class="texto"></div></div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn col-xs-offset-4 col-xs-4 col-white bg-light-blue waves-effect" data-dismiss="modal">OK, Cerrar</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- #END# Modal Notificacion -->

        <!-- Jquery Core Js -->
        <script src="../../../plugins/jquery/jquery.min.js"></script>
        <!-- Bootstrap Core Js -->
        <script src="../../../plugins/bootstrap/js/bootstrap.js"></script>
        <!-- Waves Effect Plugin Js -->
        <script src="../../../plugins/node-waves/waves.js"></script>
        <!-- Validation Plugin Js -->
        <script src="../../../plugins/jquery-validation/jquery.validate.js"></script>
        <!-- Custom Js -->
        <script src="../../../js/admin.js"></script>
        <script src="../../../js/pages/ui/modals.js"></script>
        
        <!-- =====================Script que contiene las peticiones al servidor==================================== -->
        <script src="../../../js/pages/login/function_admin.js"></script> 
        <!-- ================#END#Script que contiene las peticiones al servidor==================================== -->
    </body>
</html>