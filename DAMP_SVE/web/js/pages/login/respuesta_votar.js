/* 
 * Funciones compartidas por varias vistas
 */

/*=============Funcion para limpiar las cajas de texto de los login===============*/
function limpiarLogin() {
    $("#txtUser").val("");
    $("#txtPass").val("");
    $("#txtRes").val("");
}
/*=============Funcion validar acceso===============*/
function validarAcceso(data) {
    alert(data);
    if (data === "1") {
        limpiarLogin();
        window.location.href = "../../pages/notificaciones/tse_advertencia.jsp";
    } else if (data === "2") {
        limpiarLogin();
        window.location.href = "../../pages/notificaciones/tse_advertencia.jsp";
    } else if (data === "3") {
        $(".grafico").html("<img src=\"../../images/acciones_ico/campo_vacio.png\"/>");
        $(".texto").html("Has dejado algún campo vacio");
        $('#modalAdvertencia').modal('show');
    } else if (data === "4") {
        limpiarLogin();
        $(".grafico").html("<img src=\"../../images/acciones_ico/credenciales_incorrectas.png\"/>");
        $(".texto").html("Tus crendenciales son incorrectas, asegurate escribrilas correctamente");
        $('#modalAdvertencia').modal('show');
    } else if (data === "5") {
        limpiarLogin();
        $(".grafico").html("<img src=\"../../images/acciones_ico/inactivo.png\"/>");
        $(".texto").html("Tu cuenta esta inactiva, puedes activarla en el siguiente enlace: <a href='../../pages/procesos/tse_activar_cuenta.jsp'><i class='material-icons icons-align col-light-blue'>security</i> Activar Cuenta</a>");
        $(".texto").append("<center></center>");
        $('#modalAdvertencia').modal('show');
        limpiarLogin();
    } else if (data === "6") {
        limpiarLogin();
        $(".grafico").html("<img src=\"../../images/acciones_ico/inactivo.png\"/>");
        $(".texto").html("Tu cuenta esta inactiva, comunicate con el administrador del sistema<br>");
        $(".texto").append("<center><a href='../../pages/procesos/tse_activar_cuenta.jsp'><i class='material-icons icons-align col-light-blue'>security</i> Activar Cuenta</a></center>");
        $('#modalAdvertencia').modal('show');
        limpiarLogin();
    }else if (data === "7") {
        limpiarLogin();
        window.location.href = "../../pages/papeleta.jsp";
    } else {
        limpiarLogin();
        window.location.href = "../../pages/notificaciones/tse_sin_servidor.jsp";
    }
}

