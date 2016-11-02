<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>

<%@page import="modelo.Ciudadano"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession sesion = request.getSession();
    ArrayList<Ciudadano> usuario = (ArrayList<Ciudadano>)sesion.getAttribute("usuario");
    
    if(usuario.get(0).getTipoUsuario()==6){
%>
<div class="menu">
    <ul class="list">
        <li class="header">MENÚ</li>
        <li class="active">
            <a href="tse.jsp">
                <i class="material-icons">home</i>
                <span>Inicio</span>
            </a>
        </li>
        <li>
            <a href="javascript:void(0);" class="menu-toggle">
                <i class="material-icons col-black">person</i>
                <span>Usuarios</span>
            </a>
            <ul class="ml-menu">
                <li>
                    <a href="supervisor_externo.jsp">Supervisor Externo</a>
                </li>
            </ul>
        </li>
        <li class="header">Configuración del Sistema</li>
    </ul>
</div>
<%
} else {
%>
<div class="menu">
    <ul class="list">
        <li class="header">MENÚ</li>
    </ul>
</div>
<%
    }
%>
