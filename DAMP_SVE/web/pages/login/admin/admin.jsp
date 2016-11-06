<%-- 
    Document   : admin
    Created on : 14-oct-2016, 11:30:54
    Author     : Icchigo
--%>
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
            $(document).on("ready",function(){
                
                $("#btnEntrar").on("click",function(){
                    var chiave1 = window.location.pathname;
                    var chiave2=$("body").css("background-color");
                    $("#txtChiave1").val(chiave1);
                    $("#txtChiave2").val(chiave2);
                    alert("prue "+ $("#txtChiave2").val());
                    alert("prue  "+ $("#txtChiave1").val());
                });
            });
        </script>
    </head>
    <body class="login-page">
        <div class="login-box">
            <div class="card">
                <div class="logo">
                    <div class="msg"><img src="../../../images/avatar_admin.png"/></div>
                </div>
                <div class="body" >
                    <form id="frmAdmin" method="POST" action="../../../SerSesionAdmin">                 
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