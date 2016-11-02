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
        <title>Partidos Pol�ticos | TSE</title>
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
                    } else {
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
                $("#txtResultado").val(1);
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
                    <div class="card">
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                AGREGAR PARTIDO POL�TICO
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
                                        <label>Acr�nimo:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="hidden" name="txtId" id="txtId" value="0">
                                                <input type="text" name="txtAcronimo" id="txtAcronimo" class="form-control" placeholder="Ingresar acr�nimo del partido">
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
                                                <input type="text" name="txtDui" id="txtDui" autocomplete="off" class="form-control" placeholder="Ingresar n�mero de DUI">
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
                                        <div id="divImg" class="img-responsive thumbnail">Visualizaci�n</div>
                                    </div>
                                </div>

                                <div class="row clearfix">
                                    <div class="col-md-12 align-center">
                                        <input type="button" value="Agregar" class="btn bg-light-blue waves-effect waves-light" id="btnAgregar">
                                        <button type="button" value="Modificar" id="btnModificar" class="btn bg-light-blue waves-effect waves-light">Modificar</button>
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
                                        <td><%= p.getNumDui()%></td>
                                        <td><img src="../images/banderas/<%= p.getImagen()%>" style="width:60px;height:60px;" ></td>
                                        <td>
                                            <a class="btn bg-cyan waves-effect m-r-0 waves-light" href="javascript:modificar('<%= p.getIdPartido()%>','<%= p.getAcronimo()%>','<%= p.getNombre()%>','<%= p.getNumDui()%>','<img src=../images/banderas/<%= p.getImagen()%> >')"><i class="material-icons">create</i></a>
                                            <a class="btn bg-grey waves-effect m-r-0 waves-light" href="../SerPartido?idPartido=<%= p.getIdPartido()%>"><i class="material-icons">delete_forever</i></a>
                                        <!--<button type="button" class="btn btn2 bg-cyan waves-effect m-r-0 waves-light" data-toggle="modal" data-target="#modalModificar" ><i class="material-icons">create</i></button>
                                        <button type="button" class="btn btn2 bg-grey waves-effect m-r-0 waves-light" data-toggle="modal" data-target="#modalEliminar" ><i class="material-icons">delete_forever</i></button>
                                        -->
                                        
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
            <!-- Modal Modificar -->
            <div class="modal fade" id="modalModificar" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="defaultModalLabel">
                                <i class="material-icons icons-align col-light-blue">loop</i>
                                Modificar
                            </h3>
                        </div>
                        <div class="modal-body">
                            <h4>
                                �Desea modificar el usuario seleccionado? 
                            </h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary waves-effect">Aceptar</button>
                            <button type="button" class="btn btn-primary waves-effect" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Modificar -->
            <!-- Modal CONFIRMACION Modificado -->
            <div class="modal fade" id="modalGuardarModificacion" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="defaultModalLabel">
                                <i class="material-icons icons-align col-light-blue">warning</i>
                                Desea guardar la modificacion realiizada
                            </h3>
                        </div>                        
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary waves-effect">Aceptar</button>
                            <button type="button" class="btn btn-primary waves-effect" data-dismiss="modal">Cancelar</button>                  
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# CONFIRMACION Modificado -->
            <!-- Modal Eliminar -->
            <div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="defaultModalLabel">
                                <i class="material-icons icons-align col-light-blue">delete</i>
                                Eliminar
                            </h3>
                        </div>
                        <div class="modal-body">
                            <h4>
                                �Desea eliminar el usuario seleccionado? 
                            </h4>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary waves-effect">Si, Eliminar</button>
                            <button type="button" class="btn btn-primary waves-effect" data-dismiss="modal">No, Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Eliminar -->
            <!-- Modal Confirmaacion de datos guardados con exito-->
            <div class="modal fade" id="modalDatosGuardados" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h3 class="modal-title" id="defaultModalLabel">
                                <i class="material-icons icons-align col-light-blue">done</i>
                                Datos Guardados Exitosamente
                            </h3>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-primary waves-effect">Cerrar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Confirmaacion de datos guardados con exito -->


            <!-- Modal Info F-->
            <div class="modal fade" id="modalInfoF" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="defaultModalLabel">Informacion sobre el formulario</h4>
                        </div>
                        <div class="modal-body">
                            Algunos datos que le podrian ayudar al usuario a registrar otros usuarios
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-link waves-effect" data-dismiss="modal">Ok</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Info T -->
            <!-- Modal Info F-->
            <div class="modal fade" id="modalInfoT" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="defaultModalLabel">Informacion sobre el Tabla</h4>
                        </div>
                        <div class="modal-body">
                            Datos sobre la tabla y los permisos que tienen esos usuarios
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-link waves-effect" data-dismiss="modal">Ok</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Info T -->
        </section>
        <jsp:include page="modulos/scripts.jsp"/>
    </body>
</html>