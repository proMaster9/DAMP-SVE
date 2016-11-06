<%-- 
    Document   : pruebaLoginPrincipal
    Created on : 11-04-2016, 01:56:11 PM
    Author     : carlos
--%>

<%@page import="controlador.SerActivacion"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script src="../../plugins/jquery/jquery.js"></script>
        <script>
            $(document).on("ready",function(){
                $("#txtUser").on("keyup",function(){
                    var dui = $("#txtUser").val().length;
                    if(dui >= 10) {
                        var txtDui = $("#txtUser").val();
                        $.post('../../SerActivacion',{
                            btnValidarDui : 'true',
                            txtDui : txtDui,
                            //tipo 1 es para usuarios principales
                            txtTipo : 1
                        }, function(data){
                            $("#divActivacion").html(data);
                        });
                    }
                });
            });
        </script>
    </head>
    <body>
        <h1>Login usuarios principales</h1>
        <!--
            No se envian los datos a ningun servlet, solo tiene las validaciones
            para que dejar iniciar sesion a las cuentas no activadas.
        -->
        <form>
            Dui: <input type="text" name="txtUser" id="txtUser"><br>
            <!--
                si la cuenta esta activada, imprime un mensaje de
                "la cuenta ha sido activada exitosamente", si no esta activada,
                se imprime el formulario para la activacion
            -->
            <div id="divActivacion">
                Contraseña: <input type="password" name="txtPass" id="txtPass"><br>
                <input type="button" name="btnEntrar" value="entrar"><br>
                <a href="../activacionPrincipal.jsp">Activar cuenta</a>
                <a href="#">Olvide mi contraseña</a>
            </div>
        </form>
    </body>
</html>
