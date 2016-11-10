/* 
 *Funciones aplicadas al login del administrador
 */
/*=============Funcion para limpiar las cajas de texto de los login===============*/
function limpiarLogin() {
    $("#txtUser").val("");
    $("#txtPass").val("");
}
$(document).on("ready", function () {
    limpiarLogin();
    var key1 = window.location.pathname;
    var key2 = $("body").css("background-color");
    $("#btnEntrar").on("click", function () {
        var user = $("#txtUser").val();
        var pass = $("#txtPass").val();
        $.post('../../../SerSesionAdmin', {btnEntrar: true, key1: key1, key2: key2, user: user, pass: pass}, function (data) {
            if(data ==="1"){
                limpiarLogin();
                window.location.href = "../../../pages/notificaciones/tse_advertencia.jsp";
            }else if(data ==="2"){
                limpiarLogin();
                window.location.href = "../../../pages/notificaciones/tse_advertencia.jsp";
            }else if (data === "3") {
                $(".grafico").html("<img src=\"../../../images/acciones_ico/campo_vacio.png\"/>");
                $(".texto").html("Has dejado algún campo vacio");
                $('#modalAdvertencia').modal('show');
            }else if(data === "4"){
                limpiarLogin();
                $(".grafico").html("<img src=\"../../../images/acciones_ico/credenciales_incorrectas.png\"/>");
                $(".texto").html("Tus crendenciales son incorrectas, asegurate escribrilas correctamente");
                $('#modalAdvertencia').modal('show');
            }else if(data === "5"){
                limpiarLogin();
                $(".grafico").html("<img src=\"../../../images/acciones_ico/inactivo.png\"/>");
                $(".texto").html("Tu cuenta esta inactiva, comunicate con el administrador del sistema");
                $('#modalAdvertencia').modal('show');
            }else if(data === "6"){
                limpiarLogin();
                window.location.href = "../../../pages/tse.jsp";
            }else {
                limpiarLogin();
                window.location.href = "../../../pages/notificaciones/tse_sin_servidor.jsp";
            }
        });
    });
    
});


