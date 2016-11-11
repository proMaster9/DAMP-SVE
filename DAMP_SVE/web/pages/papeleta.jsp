<%-- 
    Document   : Papeleta
    Created on : 11-06-2016, 01:39:26 PM
    Author     : carlos
--%>

<%@page import="modelo.VotoBandera"%>
<%@page import="controlador.SerPapeleta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            //remplaza este por el id de sesion
            int idUsuario = 13669;
            if (SerPapeleta.estadoVotante(idUsuario) == 1) {
                response.sendRedirect("login/votante.jsp");
            }
        %>
        <jsp:include page="modulos/head.jsp"/>
        <script>
            $(document).on("ready", function () {
                var partidos = $("img[data-tipo=partido]").length;
                var candidatos = $("img[data-tipo=candidato]").length;
                var votantesxcentro = candidatos / partidos;
                $("#lblPartido").html(partidos);
                $("#lblCandidato").html(candidatos);
                $('img[data-seleccion="deseleccionado"]').each(function () {
                    $(this).addClass("deseleccionada");
                });

                $("img[data-tipo='partido']").hover(function () {
                    if ($(this).attr("data-estado") == "habilitado") {
                        $(this).toggleClass("seleccionada");
                        var partido = $(this).attr("data-id-partido");
                        $("img[data-partido='" + partido + "']").each(function () {
                            $(this).toggleClass("seleccionada");
                        });
                    }
                });

                $("img[data-tipo='partido']").on("click", function () {
                    if ($(this).attr("data-estado") == "habilitado") {
                        $(this).addClass("seleccionada");
                        //$("img[data-tipo='candidato' data-partido='"+$(this).attr("data-id-partido")+"']").removeClass("seleccionada");
                        var partido = $(this).attr("data-id-partido");
                        $("img[data-partido!='" + partido + "']").each(function () {
                            $(this).removeClass("seleccionada");
                        });
                        $(this).addClass("seleccionada");

                        $("img[data-id-partido != '" + partido + "']").each(function () {
                            $(this).toggleClass("deshabilitada");
                            var estado = $(this).attr("data-estado");
                            if (estado == "habilitado") {
                                $(this).attr("data-estado", "deshabilidato");
                            }
                            if (estado == "deshabilidato") {
                                $(this).attr("data-estado", "habilitado");
                            }
                        });
                        $("img[data-partido = '" + partido + "']").each(function () {
                            $(this).toggleClass("deshabilitada");
                            $(this).toggleClass("deseleccionada");
                            $(this).toggleClass("seleccionada");
                        });
                    }
                    $("#lblNumCandidatos").html($("img.seleccionada[data-tipo!='partido']").length);
                });

                $("img[data-tipo='candidato']").hover(function () {
                    if ($(this).attr("data-estado") == "habilitado") {
                        $(this).toggleClass("seleccionada");
                    }
                });

                $("img[data-tipo='candidato']").on("click", function () {
                    if ($("img.seleccionada").length <= votantesxcentro) {
                        if ($(this).attr("data-estado") == "habilitado") {
                            $(this).toggleClass("seleccionada");
                            $("#lblNumCandidatos").html($("img.seleccionada[data-tipo!='partido']").length + 1);
                        }
                    }
                    else {
                        alert("Solo puedes seleccionar " + votantesxcentro + " candidatos");
                    }
                });
                $("#btnVotar").on("click", function () {
                    if ($("img.seleccionada").length <= votantesxcentro) {
                        if ($("img.seleccionada").length > 0) {
                            if ($("img.seleccionada").length <= votantesxcentro) {
                                var bandera = false;
                                var partido = 0;
                                var idPartido = $("img.seleccionada").attr("data-partido");
                                var candidatos = "";
                                $("img.seleccionada").each(function () {
                                    candidatos += $(this).attr("data-id-candidato") + " ";
                                });
                                $.post('../SerPapeleta', {
                                    voto: 'true',
                                    txtIdUsuario: <%= idUsuario%>,
                                    txtCandidatos: candidatos
                                }, function (data) {
                                    if (data == "1") {
                                        alert("Voto realizado correctamente");
                                        $("#btnVotar").hide();
                                        $("#btnAbstener").hide();
                                    }
                                    else {
                                        alert("Hubo un error");
                                    }
                                });
                            }
                        }
                        else {
                            alert("Selecciona por lo menos un candidato");
                        }
                    }
                    else {
                        alert("Solo puedes seleccionar " + votantesxcentro + " Candidatos");
                    }

                });

                //abstencion de voto
                $("#btnAbstener").on("click", function () {
                    $.post('../SerPapeleta', {
                        votoAbstenido: 'true',
                        txtIdUsuario: <%= idUsuario%>
                    }, function (data) {
                        if (data == "1") {
                            alert("Voto Abstenido correctamente");
                            $("#btnVotar").hide();
                            $("#btnAbstener").hide();
                        }
                        else {
                            alert("Hubo un error");
                        }
                    });
                });
            });
        </script>
        <style>
            .partido {
                display: inline;
                float: left;
            }
            img {
                height: 50px;
            }
            .deseleccionada {
                opacity: 0.6;
            }
            .seleccionada {
                opacity: 1.0;
            }
            .deshabilitada {
                filter:grayscale(100%);
            }
        </style>
    </head>
    <body>
        <h1>Papeleta</h1>
        <input type="button" id="btnVotar" value="Emitir Voto">
        <input type="button" id="btnAbstener" value="Abstener Voto">
        <div id="divBandera">

        </div>
        <%= SerPapeleta.mostrarPapeleta(SerPapeleta.departamentoUsuario(idUsuario))%>
    </body>
</html>
