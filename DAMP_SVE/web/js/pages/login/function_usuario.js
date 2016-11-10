/* 
 *Funciones aplicadas al login del usuarios secundario
 */
$(document).on("ready", function () {
    limpiarLogin();
    var key1 = window.location.pathname;
    var key2 = $("#btnEntrar").css("background-color");
    $("#btnEntrar").on("click", function () {
        var user = $("#txtUser").val();
        var pass = $("#txtPass").val();
        $.post('../../SerSesionPrincipal', {btnEntrar: true, key1: key1, key2: key2, user: user, pass: pass}, function (data) {
            validarAcceso(data);
        });
    }); 
});
