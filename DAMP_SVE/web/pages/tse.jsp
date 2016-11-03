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
    </head>

    <body class="theme-light-blue">

        <jsp:include page="modulos/loader.jsp"/>
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
                        <div class="header">
                            <h2>
                                <i class="material-icons icons-align col-light-blue">person_add</i>
                                USUARIOS PRINCIPALES
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
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable text-center">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>DUI:</th>
                                        <th>Nombre:</th>
                                        <th>Sexo:</th>
                                        <th>Tipo Usuario:</th>
                                        <th>Ubicación:</th>
                                        <th>Dirección:</th>
                                        <th>Opciones:</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>1</td>
                                        <td>0000000-0</td>
                                        <td>Juan Carlos</td>
                                        <td>M</td>
                                        <td>CNR</td>
                                        <td>La Libertad, Santa Tecla</td>
                                        <td>frfrfrf</td>
                                        <td>
                                            <button type="button" class="btn btn2 bg-cyan waves-effect m-r-0 waves-light" data-toggle="modal" data-target="#modalModificar" ><i class="material-icons">create</i></button>
                                            <button type="button" class="btn btn2 bg-grey waves-effect m-r-0 waves-light" data-toggle="modal" data-target="#modalEliminar" ><i class="material-icons">delete_forever</i></button>
                                        </td>
                                    </tr>
                                    <tr>
                                    
                                </tbody>
                            </table>
                            <div id="mostrarUsuarios"></div>
                        </div>
                    </div>
                </div>
                <!-- #END# Tabla -->
            </div>
        </section>
        <jsp:include page="modulos/scripts.jsp"/>
    </body>
</html>
