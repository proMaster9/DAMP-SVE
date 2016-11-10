/* 
 *Funciones aplicadas al login del votante
 */
$(document).on("ready", function () {

    var key1 = window.location.pathname;
    var key2 = $("#btnEntrar").css("background-color");
    $("#btnEntrar").on("click", function () {
        var user = $("#txtUser").val();
        var pass = $("#txtPass").val();
        var preg = $("#slPregunta").val();
        var res = $("#txtRes").val();
        $.post('../../SerSesionVotante', {btnEntrarV: true, key1: key1, key2: key2, user: user, pass: pass,preg:preg,res:res}, function (data) {
            validarAcceso(data);
        });
    }); 
});