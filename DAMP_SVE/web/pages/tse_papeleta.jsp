<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>
<%@page import="modelo.Ciudadano"%>
<%@page import="java.util.ArrayList"%>
<%@page import="controlador.SerPapeleta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    HttpSession sesion = request.getSession(true);
    if (sesion.getAttribute("votante") != null) {
        ArrayList<Ciudadano> votante = (ArrayList<Ciudadano>) sesion.getAttribute("votante");
        int idUsuario = votante.get(0).getIdUsuario();
        if (SerPapeleta.estadoVotante(idUsuario) == 1) {
            response.sendRedirect("login/votante.jsp");
        }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">

        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Login Usuario Principal | TSE </title>
        <!-- Favicon-->
        <link rel="icon" href="../images/tse_logo_ico.png" type="image/x-icon">
        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Roboto:400,700&subset=latin,cyrillic-ext" rel="stylesheet" type="text/css">
        <link href="../plugins/iconfont/material-icons.css" rel="stylesheet" type="text/css">
        <!-- Bootstrap Core Css -->
        <link href="../plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
        <!-- Waves Effect Css -->
        <link href="../plugins/node-waves/waves.css" rel="stylesheet" />
        <!-- Animation Css -->
        <link href="../plugins/animate-css/animate.css" rel="stylesheet" />
        <!-- Custom Css -->
        <link href="../css/style.css" rel="stylesheet">
        <link href="../css/themes/all-themes.css" rel="stylesheet" />
        <!-- Jquery Core Js -->
        <script src="../plugins/jquery/jquery.min.js"></script>
        <script>
            $(document).on("ready", function () {
                $("#divAb").hide();
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
                    } else {
                        $(".grafico").html("<img src=\"../images/acciones_ico/hash.png\"/>");
                        $(".texto").html("Solo puedes seleccionar " + votantesxcentro + " Candidatos");
                        $('#modalAdvertencia').modal('show');
                    }
                });
                $("#btnVotar").on("click", function () {
                    $("#divAb").hide();
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
                                        $(".grafico").html("<img src=\"../images/acciones_ico/comprobado.png\"/>");
                                        $(".texto").html("Su voto fue abstenido correctamente");
                                        $('#modalAdvertencia').modal('show');
                                        salir();
                                    } else {
                                        $(".grafico").html("<img src=\"../images/acciones_ico/error1.png\"/>");
                                        $(".texto").html("Ocurrio un error, la pagina de votacion se cerrá e intanta otra vez votar");
                                        $('#modalAdvertencia').modal('show');
                                        salir();
                                    }
                                });
                            }
                        } else {
                            $(".grafico").html("<img src=\"../images/acciones_ico/hash.png\"/>");
                            $(".texto").html("Selecciona al menos un candidato");
                            $('#modalAdvertencia').modal('show');
                        }
                    } else {
                        $(".grafico").html("<img src=\"../images/acciones_ico/hash.png\"/>");
                        $(".texto").html("Solo puedes seleccionar " + votantesxcentro + " Candidatos");
                        $('#modalAdvertencia').modal('show');
                    }
                });

                //abstencion de voto
                $("#btnAbstener").on("click", function () {
                    $("#divAb").show();
                    $(".grafico").html("<img src=\"../images/acciones_ico/pregunta.png\"/>");
                    $(".texto").html("¿Esta seguro que quiere abstener su voto?");
                    $('#modalAdvertencia').modal('show');
                    $("#btnAb").on("click", function () {
                        $.post('../SerPapeleta', {
                            votoAbstenido: 'true',
                            txtIdUsuario: <%= idUsuario%>
                        }, function (data) {
                            if (data == "1") {
                                $("#btnVotar").hide();
                                $("#btnAbstener").hide();
                                $(".grafico").html("<img src=\"../images/acciones_ico/comprobado.png\"/>");
                                $(".texto").html("Su voto fue abstenido correctamente");
                                $('#modalAdvertencia').modal('show');
                                salir();
                                $("#divAb").hide();
                            } else {
                                $(".grafico").html("<img src=\"../images/acciones_ico/error1.png\"/>");
                                $(".texto").html("Ocurrio un error, la pagina de votacion se cerrá e intanta otra vez votar");
                                $('#modalAdvertencia').modal('show');
                                salir();
                            }
                        });
                    });
                    setInterval(ocultar, 6000);

                });
                function salir() {
                    setTimeout("location.href='login/votante.jsp'", 5000);
                }
                function paso1(){
                    $(".grafico").html("<img src=\"../images/paso1.png\" style=\"width: 95%; height: 95%;\"/>");
                }
                function paso2(){
                    $(".grafico").html("<img src=\"../images/paso2.png\" style=\"width: 95%; height: 95%;\"/>");
                }
                function paso3(){
                    $(".grafico").html("<img src=\"../images/paso3.png\" style=\"width: 95%; height: 95%;\"/>");
                }
                function paso4(){
                    $(".grafico").html("<img src=\"../images/paso4.png\" style=\"width: 95%; height: 95%;\"/>");
                }
                function ocultar(){
                    $("#divAb").hide();
                }
                $("#btnInfo").on("click", function () {
                    $(".grafico").html("");
                    setTimeout(paso1, 2000);
                    $(".grafico").html("");
                    setTimeout(paso2, 10000);
                    $(".grafico").html("");
                    setTimeout(paso3, 15000);
                    $(".grafico").html("");
                    setTimeout(paso4, 20000);
                    $(".grafico").html("");
                    $(".texto").html("Guia de como votar");
                    $('#modalAdvertencia').modal('show');
                    
                });
            });
        </script>
        <style>
            .partido {

            }
            .deseleccionada {
                opacity: 0.6;
                border-color: #f9f9f9;
            }
            .seleccionada {
                opacity: 1.0;
                border-color: #03A9F4;
            }
            .deshabilitada {
                filter:grayscale(100%);
            }
        </style>
    </head>

    <body style="background-color: #f9f9f9">

        <div id="divBandera">

        </div>

        <section>

            <div class="row clearfix">
                <!--Formulario-->
                <div class="m-t-20 col-lg-offset-1 col-md-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
                    <div class="row clearfix">
                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6">
                            <div class="col-xs-5 align-left">
                                <div class="info-box hover-expand-effect">
                                    <div class="icon">
                                        <img src="../images/escudo_sv.png" style="width: 99%; height: 99%;"/>
                                    </div>
                                    <div class="content">
                                        <div class="text">¡Consolidando la Democracia!</div>
                                    </div>
                                </div>
                            </div>

                        </div>
                        <div class="col-xs-6 col-sm-6 col-md-6 col-lg-6 align-right">
                            <button type="button" id="btnVotar" class="btn bg-light-blue col-white waves-effect waves-light">Emitir Voto</button>
                            <button type="button" id="btnAbstener" class="btn bg-light-blue col-white  waves-effect waves-light">Abstener Voto</button>
                        </div>
                    </div>

                </div>
            </div>
            <div class="row clearfix m-t-5">
                <!--Formulario-->
                <div class="col-lg-offset-1 col-md-offset-1 col-xs-12 col-sm-12 col-md-10 col-lg-10">
                    <div class="card card-papeleta">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                Elección Electoral 2018
                            </h2>
                            <ul class="header-dropdown m-r-0">
                                <li>
                                    <a data-toggle="modal" id="btnInfo">
                                        <i class="material-icons col-light-blue">info_outline</i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="body table-responsive">
                            <%= SerPapeleta.mostrarPapeleta(SerPapeleta.departamentoUsuario(idUsuario))%>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <!-- ======================================Modal Dialogs==================================== -->
        <!-- Modal Notificacion -->
        <div class="modal fade animated" id="modalAdvertencia" tabindex="-1" role="dialog">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="defaultModalLabel"><i class="material-icons icons-align col-light-blue">info</i> NOTIFICACIÓN</h5>
                    </div>
                    <div class="modal-body text-center">
                        <div class="mensaje">
                            <div class="row"><div class="grafico"></div></div>
                            <div class="row"><div class="texto"></div></div>
                        </div>
                    </div>
                    <div class="modal-footer">   
                        <div class="col-md-6">
                        <button type="button" class="btn col-xs-6 col-white bg-light-blue waves-effect" data-dismiss="modal">Cerrar</button>
                        </div>
                        <div id="divAb" class="col-md-offset-1 col-md-5">
                            <button type="button" class="btn col-xs-6 col-white bg-light-blue waves-effect" id="btnAb">Si, Abstener</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- #END# Modal Notificacion -->

        <!-- Bootstrap Core Js -->
        <script src="../plugins/bootstrap/js/bootstrap.js"></script>
        <!-- Waves Effect Plugin Js -->
        <script src="../plugins/node-waves/waves.js"></script>
        <!-- Custom Js -->
        <script src="../js/admin.js"></script>
        <script src="../js/pages/ui/modals.js"></script>
    </body>
</html>
<%
    } else {
        response.sendRedirect("notificaciones/tse_advertencia.jsp");
    }
%>
