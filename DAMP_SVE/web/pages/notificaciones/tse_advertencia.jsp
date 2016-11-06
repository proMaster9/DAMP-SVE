<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:30:00
    Author     : EQUIPO DAMP-2016
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Advertencia | TSE </title>
        <!-- Favicon-->
        <link rel="icon" href="../../favicon.ico" type="image/x-icon">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" type="text/css">

        <!-- Bootstrap Core Css -->
        <link href="../../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">

        <!-- Waves Effect Css -->
        <link href="../../plugins/node-waves/waves.css" rel="stylesheet" />

        <!-- Custom Css -->
        <link href="../../css/style.css" rel="stylesheet">
        
        <!-- Jquery Core Js -->
        <script src="../../plugins/jquery/jquery.min.js"></script>
        
        <script>
            $(document).on("ready", function () {
                //cerrar sesion como parte de proteccion de los modulos
                $.get("../../SerCerrarSesion?cerrar=true");
                $("#btnRegresar").on("click", function () {
                    //cambiar por un modal mucho mejor
                   alert("Por seguridad tu sesion fue cerrada, esperamos seguir el reglamento sobre el no fraude");
                   //redireccionar al index
                    window.location.href = "../../index.jsp";
                });
            });
        </script>
    </head>

    <body class="four-zero-four">
        <div class="four-zero-four-container">
            <div class="error-code"><i class="material-icons md-48">error</i></div>
            <div class="error-message">Es contra la ley tratar de entrar al sistema sin ser un usario con credenciales, puedes guardar prisión hasta 10 años</div>
            <div class="button-place">
                <button class="btn btn-default btn-lg waves-effect" id="btnRegresar">Regresar</button>
            </div>
        </div>
        <!-- Bootstrap Core Js -->
        <script src="../../plugins/bootstrap/js/bootstrap.js"></script>

        <!-- Waves Effect Plugin Js -->
        <script src="../../plugins/node-waves/waves.js"></script>
    </body>

</html>