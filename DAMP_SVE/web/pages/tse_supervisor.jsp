<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>
<%@page import="modelo.SupervisorExt"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("usuario_externo") != null) {
        ArrayList<SupervisorExt> usuario_externo = (ArrayList<SupervisorExt>) sesion.getAttribute("usuario_externo");
%>
<!DOCTYPE html>
<html>

    <head>
        <meta charset="UTF-8">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Restringido | TSE </title>
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
    </head>

    <body class="four-zero-four">
        <div class="four-zero-four-container">
            <div class="error-code"><i class="material-icons md-48">error</i></div>
            <div class="error-message">No tienes permitido navegar por este sitio puedes ser multado</div>
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
<%
    } else {
        response.sendRedirect("../login/supervisor.jsp");
    }
%>