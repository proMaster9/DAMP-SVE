<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>
<%
    HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("usuario") != null) {
        response.sendRedirect("../notificaciones/tse_acceso_denegado.jsp");
%>
<nav class="navbar">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="panel-usuario.jsp"><img style="width: 90px; height: 25px;" src="../images/tse-logo-nav.png"/></a>
            <a href="javascript:void(0);" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse" aria-expanded="false"></a>
            <a href="javascript:void(0);" class="bars"></a>

        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <!-- User -->
                <li class="dropdown">
                    <jsp:include page="user_info.jsp"  flush="true"/>
                </li>
                <!-- #END# User -->
                <li class="pull-right"><a href="javascript:void(0);" class="js-right-sidebar" data-close="true"><i class="material-icons">more_vert</i></a></li>
            </ul>
        </div>
    </div>
</nav>
<%
    } else {
        response.sendRedirect("../notificaciones/tse_acceso_denegado.jsp");
    }
%>