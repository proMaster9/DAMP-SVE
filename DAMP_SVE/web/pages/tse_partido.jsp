<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:30:00
    Author     : EQUIPO DAMP-2016
--%>

<%@page import="modelo.Partido"%>
<%@page import="modelo.PartidoDTO"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Partidos Políticos | TSE</title>
        <jsp:include page="modulos/head.jsp"/>
        <script src="../js/funciones.js"></script>
        <script>
            $(document).on("ready", function () {
                cargarImagen("btnImagen", "divImg");

                /*cuando se presiona el boton de modificar, se comprueba primero
                 que se haya selecciona un registro, y que sus datos esten cargados*/
                $("#btnModificar").on("click", function () {
                    var modificar = $("#txtId").val();
                    if (modificar != 0) {
                        $("#frmPartido").submit();
                    }
                    else {
                        alert("No puedes modificar");
                    }
                });

                mostrarCiudadano('txtDui', 'divCiudadano', '../SerCiudadano', 0);
                enviarForm('frmPartido', 'btnAgregar');
            });
            //Cargar datos en el formulario
            function modificar(id, acronimo, nombre, dui, imagen) {
                $("input[name=txtId]").val(id);
                $("input[name=txtAcronimo]").val(acronimo);
                $("input[name=txtNombre]").val(nombre);
                $("input[name=txtDui]").val(dui);
                $("#divImg").html(imagen);
                
            }
        

    </script>
    </head>

    <body class="theme-light-blue">
        <!-- Overlay For Sidebars -->
        <div class="overlay"></div>
        <!-- #END# Overlay For Sidebars -->
        <!-- Top Bar -->
        <jsp:include page="modulos/navbar.jsp"/>
        <!-- #Top Bar -->

        <section>
            <!-- Left Sidebar -->
            <aside id="leftsidebar" class="sidebar">
                <!-- Menu -->
                <jsp:include page="modulos/menu.jsp"/>
                <!-- #Menu -->
                <!-- Footer -->
                <jsp:include page="modulos/footer.jsp"/>
                <!-- #Footer -->
            </aside>
            <!-- #END# Left Sidebar -->
            <!-- Right Sidebar -->
            <aside id="rightsidebar" class="right-sidebar">
                <jsp:include page="modulos/opciones.jsp"/>
            </aside>
            <!-- #END# Right Sidebar -->
        </section>

        <section class="content">
            <div class="container-fluid">
                <div class="block-header">
                    <h2></h2>
                </div>
            </div>
            <div class="row clearfix" >
                <!--Formulario-->
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                    <div class="card m-l-15">
                        <div class="header jsdemo-notification-button">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                PARTIDO POLÍTICO
                                
                                <button type="button" class="btn bg-blue btn-block waves-effect col-lg-1 col-xs-1" data-placement-from="bottom" data-placement-align="center"
                                            data-animate-enter="" data-animate-exit="" data-color-name="bg-blue">
                                        BLUE
                                    </button>
                            </h2>
                            <ul class="header-dropdown m-r-0">
                                <li>
                                    <a data-toggle="modal" href="#modalInfoF" >
                                        <i class="material-icons col-light-blue">info_outline</i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="body">
                            <form method="post" name="frmPartido" id="frmPartido" action="../SerPartido" enctype="multipart/form-data"> 
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Acrónimo:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="hidden" name="txtId" id="txtId" value="0">
                                                <input type="text" name="txtAcronimo" id="txtAcronimo" class="form-control" placeholder="Ingresar acrónimo del partido">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Nombre:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" name="txtNombre" id="txtNombre" class="form-control" placeholder="Ingresar nombre del partido">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Bandera:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">                                          
                                            <input type="file" name="btnBandera" id="btnImagen" class="form-control">
                                           
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Representante:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">                     
                                                <input type="text" name="txtDui" id="txtDui" autocomplete="off" class="form-control" placeholder="Ingresar número de DUI">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-12 align-center">
                                        <!--Impresion de resultados de busqueda-->
                                        <div id="divCiudadano"> 
                                            <input type="hidden" name="txtResultado" id="txtResultado">
                                        </div>
                                        <!--Fin de impresion de resultados-->
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-offset-3 col-lg-6">
                                        <!--Mostrando imagen seleccionada-->
                                        <div id="divImg" class="img-responsive thumbnail">Visualización</div>
                                    </div>
                                </div>

                                <div class="row clearfix acciones">
                                    <div class="col-md-12 align-center">
                                        <button type="button" id="btnAgregar" class="btn bg-light-blue waves-effect waves-light">Agregar</button>
                                        <button type="button" id="btnModificar" class="btn bg-light-blue waves-effect waves-light">Modificar</button>
                                        <button type="reset" class="btn bg-cyan waves-effect waves-light">Limpiar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
                <!-- #END# Formulario -->
                <!--Tabla-->
                <div class="col-xs-12 col-sm-12 col-md-8 col-lg-8">
                    <div class="card">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">assignment_ind</i>
                                USUARIOS PRINCIPALES REGISTRADOS EN EL SISTEMA
                            </h2>
                            <ul class="header-dropdown m-r-0">
                                <li>
                                    <a href="javascript:void(0);" >
                                        <i class="material-icons col-light-blue">save</i>
                                    </a>
                                </li>
                                <li>
                                    <a href="javascript:void(0);">
                                        <i class="material-icons col-light-blue">print</i>
                                    </a>
                                </li>
                                <li>
                                    <a data-toggle="modal" href="#modalInfoT" >
                                        <i class="material-icons col-light-blue">info_outline</i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="body table-responsive">
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>Acronimo</th>
                                        <th>Nombre</th>
                                        <th>Bandera</th>
                                        <th>Representante</th>
                                        <th></th>
                                    </tr>
                                </thead>
                                <tbody>
                                <div id="divPartido">
                                    <% int listador=1;for (Partido p : PartidoDTO.mostrarPartidos()) {%>
                                    <tr>
                                        <td><%= listador%></td>
                                        <td><%= p.getAcronimo()%></td>
                                        <td><%= p.getNombre()%></td>
                                        <td><img src="../images/files/banderas/<%= p.getImagen()%>" style="width:32px;height:32px;" ></td>
                                        <td><%= p.getNumDui()%></td>
                                        <td>
                                            <a class="btn bg-cyan waves-effect m-r-0 waves-light" href="javascript:modificar('<%= p.getIdPartido()%>','<%= p.getAcronimo()%>','<%= p.getNombre()%>','<%= p.getNumDui()%>','<img src=../images/files/banderas/<%= p.getImagen()%> >')"><i class="material-icons">create</i></a>
                                            <a class="btn bg-grey waves-effect m-r-0 waves-light" href="../SerPartido?idPartido=<%= p.getIdPartido()%>"><i class="material-icons">delete_forever</i></a>                                                                       
                                        </td>
                                    </tr>
                                    <% listador++;}%>
                              

                                    </tbody>
                            </table>
                            <div id="mostrarUsuarios"></div>
                        </div>
                    </div>
                </div>
                <!-- #END# Tabla -->
            </div>
            <!-- Modal Dialogs ====================================================================================================================== -->
            
        </section>
        <jsp:include page="modulos/scripts.jsp"/>
        <script>
            $(function () {
    $('.jsdemo-notification-button button').on('click', function () {
        var placementFrom = $(this).data('placement-from');
        var placementAlign = $(this).data('placement-align');
        var animateEnter = $(this).data('animate-enter');
        var animateExit = $(this).data('animate-exit');
        var colorName = $(this).data('color-name');

        showNotification(colorName, null, placementFrom, placementAlign, animateEnter, animateExit);
    });
});

function showNotification(colorName, text, placementFrom, placementAlign, animateEnter, animateExit) {
    if (colorName === null || colorName === '') { colorName = 'bg-black'; }
    if (text === null || text === '') { text = "vvv"; }
    if (animateEnter === null || animateEnter === '') { animateEnter = 'animated fadeInDown'; }
    if (animateExit === null || animateExit === '') { animateExit = 'animated fadeOutUp'; }
    var allowDismiss = true;

    $.notify({
        message: text
    },
        {
            type: colorName,
            allow_dismiss: allowDismiss,
            newest_on_top: true,
            timer: 1000,
            placement: {
                from: placementFrom,
                align: placementAlign
            },
            animate: {
                enter: animateEnter,
                exit: animateExit
            },
            template: '<div data-notify="container" class="bootstrap-notify-container alert alert-dismissible {0} ' + (allowDismiss ? "p-r-35" : "") + '" role="alert">' +
            '<button type="button" aria-hidden="true" class="close" data-notify="dismiss">×</button>' +
            '<span data-notify="icon"></span> ' +
            '<span data-notify="title">{1}</span> ' +
            '<span data-notify="message">{2}</span>' +
            '<div class="progress" data-notify="progressbar">' +
            '<div class="progress-bar progress-bar-{0}" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100" style="width: 0%;"></div>' +
            '</div>' +
            '<a href="{3}" target="{4}" data-notify="url"></a>' +
            '</div>'
        });
}
        </script>
    </body>
</html>
