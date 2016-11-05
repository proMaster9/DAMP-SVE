<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Panel Usuario | TSE</title>
        <jsp:include page="modulos/head.jsp"/>
        <script>
            $(document).on("ready",function(){
                $("#b1").on("click",function(){
                    $("#prueba").val("b1");
                    $("#bandera").css("background", "url()");
                });
                $("#b2").on("click",function(){
                    $("#prueba").val("b2");
                    $("#prueba2").css("background", "url(\"../images/usuario.png\")");
                    $("#b2").css("background", "url(\"../images/usuario.png\")");
                });
                $("#b3").on("click",function(){
                    $("#prueba").val("b3");
                });
   
            });
        </script>
    </head>

    <body class="theme-light-blue">
        <!-- Overlay For Sidebars -->
        <div class="overlay"></div>
        <!-- #END# Overlay For Sidebars -->
        <!-- Top Bar -->
        <jsp:include page="modulos/navbar.jsp"/>
        <!-- #Top Bar -->

        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2></h2>
                </div>
            </div>
            <div class="row clearfix" >
                <!--Formulario-->
                <div class="col-xs-12 col-sm-12 col-md-10 col-lg-10">
                    <div class="card m-l-15">
                        <div class="header">
                            <h2 class="text-center">
                                SAN SALVADOR
                            </h2>
                        </div>
                        <div class="body">
                            <div class="row clearfix">
                                <div class="col-xs-4 col-sm-4 col-md-1 col-lg-1 col-lg-offset-1">
                                    <div class="bandera" id="b1">
                                        
                                        <div id="prueba2" style="width: 100px;height: 100px;z-index: 10000;position: absolute; overflow: hidden" ><img src="../images/avatar.png" alt="..." style="width: 50px;height: 50px;"></div>
                                        <p>Partido 1</p>
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-1 col-lg-1">
                                    <div class="bandera" id="b2">
                                       
                                            <img src="../images/avatar.png" alt="..." style="width: 50px;height: 50px;">
                                            <p>Partido 1</p>
                                        
                                    </div>
                                </div>
                                <div class="col-xs-4 col-sm-4 col-md-1 col-lg-1">
                                    <div class="bandera" id="b3">
                                       
                                            <img src="../images/avatar.png" alt="..." style="width: 50px;height: 50px;">
                                            <p>Partido 1</p>
                                        
                                    </div>
                                </div>
                                <input type="text" id="prueba">
                                <p id="parrafo" style="color:#FF1;">Esto es un párrafo de texto de color rojo.</p>
<p><input type="button" id="boton" value="Clicka para obtener la propiedad"></p>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- #END# Formulario -->

            </div>
        </section>
        <jsp:include page="modulos/scripts.jsp"/>
    </body>
</html>
