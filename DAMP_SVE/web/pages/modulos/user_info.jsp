<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>
<%@page import="modelo.Ciudadano"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("usuario") != null) {
        response.sendRedirect("../notificaciones/tse_acceso_denegado.jsp");
        ArrayList<Ciudadano> usuario = (ArrayList<Ciudadano>) sesion.getAttribute("usuario");
%>

<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button">
    <span style="border:1px solid white;border-radius: 4px;" class="p-b-5 p-t-5 p-l-5 p-r-5">
        <img src="../images/avatar.png" class="ico-user" alt="User">
        <%= usuario.get(0).getNumDui()%> , <%= usuario.get(0).getApellido()%>
    </span>
</a>
<ul class="dropdown-menu">
    <li class="header text-center">
        <%= usuario.get(0).getRol()%>
    </li>
    <li class="body">
        <ul class="menu">
            <li>
                <a href="javascript:void(0);">
                    <div class="icon-circle bg-light-green">
                        <i class="material-icons">account_circle</i>
                    </div>
                    <div class="menu-info">
                        <h4>Perfil</h4>
                    </div>
                </a>
            </li>
            <li>
                <a href="../SerCerrarSesion?cerrar=true">
                    <div class="icon-circle bg-light-green">
                        <i class="material-icons">exit_to_app</i>
                    </div>
                    <div class="menu-info">
                        <h4>Cerrar Sesión</h4>
                    </div>
                </a>
            </li>
        </ul>
    </li>
    <li class="footer">
        <a href="javascript:void(0);">No olvides Cerrar Sesión</a>
    </li>
</ul>
<%
    } else {
        response.sendRedirect("../notificaciones/tse_acceso_denegado.jsp");
    }
%>
