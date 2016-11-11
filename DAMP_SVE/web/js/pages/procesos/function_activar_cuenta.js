/* 
 *Funciones aplicadas al activar cuenta
 */
/*=============Funcion para limpiar las cajas de texto al activar cuenta===============*/
function limpiarLogin() {
    $("#txtUser").val("00000003-0");
    $("#txtPass").val("");
    $("#txtPass2").val("");
    $("#txtRes").val("");
    
}
$(document).on("ready", function () {
    $("#divPaso2").hide();
    limpiarLogin();
    $("#btnVerificar").on("click", function () {
        var user = $("#txtUser").val();
        $.post('../../SerActivarCuenta', {btnVerificar: true, user: user}, function (data) {
            alert(data);
            if (data === "1") {
                limpiarLogin();
                window.location.href = "../../pages/notificaciones/tse_advertencia.jsp";
            } else if (data === "2") {
                $(".grafico").html("<img src=\"../../images/acciones_ico/campo_vacio.png\"/>");
                $(".texto").html("Has dejado algún campo vacio");
                $('#modalAdvertencia').modal('show');
            } else if (data === "3") {
                limpiarLogin();
                $(".grafico").html("<img src=\"../../images/acciones_ico/credenciales_incorrectas.png\"/>");
                $(".texto").html("Tus crendenciales son incorrectas, asegurate escribrilas correctamente");
                $('#modalAdvertencia').modal('show');
            } else if (data === "4") {
                limpiarLogin();
                $(".grafico").html("<img src=\"../../images/acciones_ico/inactivo.png\"/>");
                $(".texto").html("Tu cuenta esta activa, ya puedes iniciar sesión en el siguiente enlace: <br><a href='../../pages/login/votante.jsp'><i class='material-icons icons-align col-light-blue'>person</i> Iniciar Sesion</a>");
                $('#modalAdvertencia').modal('show');
            } else if (data === "5") {
                limpiarLogin();
                $("#divPaso2").show();
                $("#divVerificar").hide();
            } else {
                limpiarLogin();
                window.location.href = "../../pages/notificaciones/tse_sin_servidor.jsp";
            }
        });
    });
    $("#btnActivar").on("click", function () {
        if ($("#txtPass").val() == $("#txtPass2").val()) {
            if ($("#slPregunta").val() === "0") {
                $(".grafico").html("<img src=\"../../images/acciones_ico/campo_vacio.png\"/>");
                $(".texto").html("No ha seleccionado la pregunta");
                $('#modalAdvertencia').modal('show');
            } else {
                var user = $("#txtUser").val();
                var pass = $("#txtPass").val();
                var preg = $("#slPregunta").val();
                var res = $("#txtRes").val();
                $.post('../../SerActivarCuenta', {btnActivar: true, user: user, pass: pass, preg: preg, res: res}, function (data) {
                    if (data === "1") {
                        limpiarLogin();
                        window.location.href = "../../pages/notificaciones/tse_advertencia.jsp";
                    } else if (data === "2") {
                        $(".grafico").html("<img src=\"../../images/acciones_ico/campo_vacio.png\"/>");
                        $(".texto").html("Has dejado algún campo vacio");
                        $('#modalAdvertencia').modal('show');
                    }else if (data === "3") {
                        limpiarLogin();
                        $(".grafico").html("<img src=\"../../images/acciones_ico/inactivo.png\"/>");
                        $(".texto").html("Tu cuenta esta activa, ya puedes iniciar sesión en el siguiente enlace: <br><a href='../../pages/login/votante.jsp'><i class='material-icons icons-align col-light-blue'>person</i> Iniciar Sesion</a>");
                        $('#modalAdvertencia').modal('show');
                    } else if (data === "4") {
                        $("#divPaso2").hide();
                        $(".grafico").html("<img src=\"../../images/acciones_ico/inactivo.png\"/>");
                        $(".texto").html("Tu cuenta fue activada con exito");
                        $('#modalAdvertencia').modal('show');

                    } else {
                        limpiarLogin();
                        window.location.href = "../../pages/notificaciones/tse_sin_servidor.jsp";
                    }
                });
            }
        } else {
            $(".grafico").html("<img src=\"../../images/acciones_ico/credenciales_incorrectas.png\"/>");
            $(".texto").html("Las contraseñas son incorrectas");
            $('#modalAdvertencia').modal('show');
        }

    });

});


