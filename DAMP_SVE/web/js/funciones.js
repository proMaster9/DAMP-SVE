//funcion que carga la vista previa de la imagen
function mostrarImagen(input, vistaPrevia) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();
        reader.onload = function (e) {
            //$('#img_destino').attr('src', e.target.result);
            var ruta = e.target.result;
            $("#" + vistaPrevia).html("<img src='" + ruta + "'/>");
        }
        reader.readAsDataURL(input.files[0]);
    }
}

//ejecucion de la vista previa
function cargarImagen(boton, divImagen) {
    $("#" + boton).change(function () {
        mostrarImagen(this, divImagen);
    });
}



//registro de un candidato
function enviarForm(formulario, boton) {
    $("#" + boton).on("click", function () {
        if ($("#txtResultado").val() == 0) {
            alert("No puedes enviar");
        }
        else {
            $("#" + formulario).submit();
        }
    });
}

/*
 1)el parametro caja recibe el id del campo donde se ingresara el dui
 2)el parametro resultado es el id de la division donde se muestra el resultado de la busqueda
 3)el parametro servlet contiene la url del servlet a donde se envian los datos
 4)el parametro tipo puede tener 2 valores: 0 y 1... cuando es 0 significa que en el resultado se imprimira la informacion del dui que se buscó, especificamente nombres, apellidos y sexo
 cuando el parametro es 1, solo imprimira el mensaje "correcto" o "incorrecto"
 */
function mostrarCiudadano(caja, resultado, servlet, tipo) {
    $("#" + caja).on("keyup", function () {
        var dui = $("#" + caja).val();
        var longitud = dui.length;
        if (longitud >= 10) {
            $.post(servlet, {
                tipo: tipo,
                dui: dui
            }, function (data) {
                $("#" + resultado).html(data);
            });
        }
        else {
            $("#" + resultado).html('<input type="hidden" name="txtResultado" id="txtResultado" value="0">');
        }
    });
}
$(document).on("ready", function () {
    //se bloquea la tecla enter en los formularios, para que no se envien al presionar esta tecla
    $('form').keypress(function (e) {
        if (e.which == 13) {
            return false;
        }
    });

    //cargar municipios dinamicamente
    $("#slDepartamento").on("change", function () {
        var departamento = $(this).val();
        $.post('../SerDepartamento', {
            dep: departamento
        }, function (data) {
            $("#divMunicipio").html(data);
        });
    });
});
