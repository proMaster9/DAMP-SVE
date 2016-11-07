<%-- 
    Document   : tse
    Created on : 09-oct-2016, 23:39:55
    Author     : JH
--%>

<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Supervisor Externo | TSE</title>
        <jsp:include page="modulos/head.jsp"/>
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
            <div class="row clearfix">
                <!--Formulario-->
                <div class="col-xs-12 col-sm-12 col-md-4 col-lg-4">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    <i class="material-icons icons-align col-light-blue">person_add</i>
                                    AGREGAR SUPERVISOR EXTERNO
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
                                <form id="formulario_magistrado">
                                    <div class="row clearfix">
                                        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                            <label>Identificación:</label>
                                        </div>
                                        <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                            <div class="form-group">
                                                <div class="form-line">
                                                    <input type="text" id="txtDuiMagistrado" class="form-control" name="txtDuiMagistrado" placeholder="Ingresar identificación">
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
                                                    <input type="text" class="form-control" id="txtNomMagistrado" name="txtNomMagistrado" placeholder="Ingresar nombre">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row clearfix">
                                        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                            <label>Apellido:</label>
                                        </div>
                                        <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                            <div class="form-group">
                                                <div class="form-line">
                                                    <input type="text" class="form-control" id="txtApeMagistradO" name="txtApeMagistrado" placeholder="Ingresar apellido">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row clearfix">
                                        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                            <label>Género:</label>
                                        </div>
                                        <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                            <div class="form-group">
                                                <input type="radio" name="txtSexo" value="M" id="male" class="with-gap radio-col-light-blue">
                                                <label for="male">Hombre</label>
                                                <input type="radio" name="txtSexo" id="female"  value="F"  class="with-gap radio-col-light-blue">
                                                <label for="female" class="m-l-20">Mujer</label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row clearfix">
                                        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                            <label>País:</label>
                                        </div>
                                        <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                            <div class="form-group">
                                                <div class="form-line">
                                                    <input type="text" class="form-control" name="txtPais" placeholder="Ingresar país">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row clearfix">
                                        <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                            <label>Organización:</label>
                                        </div>
                                        <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                            <div class="form-group">
                                                <div class="form-line">
                                                    <input type="text" class="form-control" name="txtOrganizacion" placeholder="Ingresar organización">
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row clearfix">
                                        <div class="col-md-12 align-center">
                                            <button type="button" class="btn bg-light-blue waves-effect waves-light" id="btnAg">Guardar</button>
                                            <button type="reset" class="btn bg-cyan waves-effect waves-light">Limpiar</button>
                                            <button type="button" class="btn  bg-cyan waves-effect waves-light">Cancelar</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                </div>
                <!-- #END# Formulario -->
                <!--Tabla-->
                <div class="col-lg-8 col-md-8 col-sm-12 col-xs-12">
                        <div class="card">
                            <div class="header">
                                <h2>
                                    <i class="material-icons icons-align col-light-blue">assignment_ind</i>
                                    SUPERVIORES EXTERNOS REGISTRADOS EN EL SISTEMA
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
                            <div class="body">
                                <table class="table table-bordered table-striped table-hover js-basic-example dataTable">
                                    <thead>
                                        <tr>
                                            <th>#</th>
                                            <th>Nombre:</th>
                                            <th>Sexo:</th>
                                            <th>País:</th>
                                            <th>Organización:</th>
                                            <th>Opciones:</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td>1</td>
                                            <td>Tiger Nixon</td>
                                            <td>n</td>
                                            <td>Edinburdededededegh</td>
                                            <td>ONU</td>
                                            <td>
                                                <button type="button" class="btn btn2 bg-cyan waves-effect m-r-0 waves-light" data-toggle="modal" data-target="#modalModificar" >MODIFICAR</button>
                                                <button type="button" class="btn btn2 bg-grey waves-effect m-r-0 waves-light" data-toggle="modal" data-target="#modalEliminar" >ELIMINAR</button>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
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
                            <h4 class="modal-title" id="defaultModalLabel">Modificar</h4>
                        </div>
                        <div class="modal-body">
                            Formulario para modificar
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-link waves-effect">Guardar</button>
                            <button type="button" class="btn btn-link waves-effect" data-dismiss="modal">Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Modificar -->
            <!-- Modal Eliminar -->
            <div class="modal fade" id="modalEliminar" tabindex="-1" role="dialog">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h4 class="modal-title" id="defaultModalLabel">Eliminar</h4>
                        </div>
                        <div class="modal-body">
                            Formulario para eliminar
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-link waves-effect">Si, Eliminar</button>
                            <button type="button" class="btn btn-link waves-effect" data-dismiss="modal">No, Cancelar</button>
                        </div>
                    </div>
                </div>
            </div>
            <!-- #END# Modal Eliminar -->
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