<%-- 
    Document   : tse
    Created on : 10-oct-2016, 7:00:00
    Author     : EQUIPO DAMP-2016
--%>

<%@page import="modelo.CiudadanoDTO"%>
<%@page import="modelo.Ciudadano"%>
<%@page import="modelo.MunicipioDTO"%>
<%@page import="modelo.DepartamentoDTO"%>
<%@page import="modelo.Departamento"%>
<!DOCTYPE html>
<html lang="es">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=Edge">
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
        <title>Usuarios Principales | TSE</title>
        <jsp:include page="modulos/head.jsp"/>
        <script>
            $(document).on("ready", function () {
                $("#rdbMasculino").attr("checked", true);
                $("#btnModificar").attr("disabled", "disabled");
                //alert("Columnas: " + $("td[data-rol=2]").length);
                $("#btnAgregar").on("click", function () {
                    var magistrados = $("td[data-rol=2]").length;
                    var cnr = $("td[data-rol=3]").length;
                    var tse = $("td[data-rol=6]").length;
                    //variables enviadas por ajax
                    var dui = $("#txtDui").val();
                    var nombre = $("#txtNombre").val();
                    var apellido = $("#txtApellido").val();
                    var sexo = $("input[name=rbdSexo]:checked").val();
                    var fecha = $("#txtFecha").val();
                    var departamento = $("#slDepartamento").val();
                    var municipio = $("#slMunicipio").val();
                    var direccion = $("#txtDireccion").val();
                    var tipo = $("#slTipoUsuario").val();
                    //bandera que decide si se envia la informacion
                    var bandera = false;
                    //se comprueba que se puedan ingresar magistrados
                    if (tipo == 2) {
                        //se permiten solo 5 magistrados
                        if (magistrados <= 4) {
                            bandera = true;
                        } else {
                            alert("El maximo de magistrados en 5");
                        }
                    }
                    //se comprueba que se pueda ingresar un representante cnr
                    if (tipo == 3) {
                        //se permite un representante del cnr
                        if (cnr < 1) {
                            bandera = true;
                        } else {
                            alert("El maximo de cnr es 1");
                        }
                    }
                    //se comprueba que se pueda ingresar un director de tse
                    if (tipo == 6) {
                        //Se permite un director de tse
                        if (tse < 1) {
                            bandera = true;
                        } else {
                            alert("El maximo de tse es 1");
                        }
                    }
                    if (bandera == true && $("#txtResultado").val() == "1") {
                        $.post('../SerUsuariosPrincipales', {
                            btnAgregar: 'true',
                            txtDui: dui,
                            txtNombre: nombre,
                            txtApellido: apellido,
                            rdbSexo: sexo,
                            txtFecha: fecha,
                            slDepartamento: departamento,
                            slMunicipio: municipio,
                            txtDireccion: direccion,
                            slTipo: tipo
                        }, function (data) {
                            $("#divUsuarios").html(data);
                            //actualizamos el div que muestra la cantidad de usuarios registrados
                            var magistrados = $("td[data-rol=2]").length;
                            var cnr = $("td[data-rol=3]").length;
                            var tse = $("td[data-rol=6]").length;
                            //impresion de cuenta de usuarios
                            $("#divRegistros").html("Magistrados: " + magistrados + "<br>");
                            $("#divRegistros").append("CNR: " + cnr + "<br>");
                            $("#divRegistros").append("TSE: " + tse);
                            //limpieza del formulario
                            $("#frmUsuarios")[0].reset();
                            $("#divUsuario").html('<input type="hidden" value="0" id="txtResultado">');
                        });
                    }
                    //mensaje en caso de ingresar un dui en uso
                    if ($("#txtResultado").val() == "0") {
                        alert("Ingresa un dui que no este en uso");
                    }
                });

                //verifica que el dui ingresado sea valido
                $("#txtDui").on("keyup", function () {
                    var dui = $("#txtDui").val();
                    var longitud = dui.length;
                    if (longitud >= 10) {
                        $.post("../SerUsuariosPrincipales", {
                            btnBuscar: 'true',
                            txtDui: dui
                        }, function (data) {
                            $("#divUsuario").html(data);
                        });
                    } else {
                        $("#divUsuario").html("<input type='hidden' value='0' id='txtResultado'>");
                    }
                });
                //modificar la informacion del usuario, no se puede cambiar el tipo de usuario
                //si se ha llegado al limite de usuarios creados de ese tipo
                //ya que hay una candidad establecida de usuarios a registrar, no se puede modificar el tipo de usuario
                $("#btnModificar").on("click", function () {
                    if ($("#txtResultado").val() == 1) {
                        //variables enviadas por ajax
                        var id = $("#txtId").val();
                        var dui = $("#txtDui").val();
                        var nombre = $("#txtNombre").val();
                        var apellido = $("#txtApellido").val();
                        var sexo = $("input[name=rbdSexo]:checked").val();
                        var fecha = $("#txtFecha").val();
                        var departamento = $("#slDepartamento").val();
                        var municipio = $("#slMunicipio").val();
                        var direccion = $("#txtDireccion").val();
                        var tipo = $("#slTipoUsuario").val();

                        $.post('../SerUsuariosPrincipales', {
                            btnModificar: 'true',
                            txtId: id,
                            txtDui: dui,
                            txtNombre: nombre,
                            txtApellido: apellido,
                            rdbSexo: sexo,
                            txtFecha: fecha,
                            slDepartamento: departamento,
                            slMunicipio: municipio,
                            txtDireccion: direccion,
                            slTipo: tipo
                        }, function (data) {
                            $("#divUsuarios").html(data);
                            //actualizamos el div que muestra la cantidad de usuarios registrados
                            var magistrados = $("td[data-rol=2]").length;
                            var cnr = $("td[data-rol=3]").length;
                            var tse = $("td[data-rol=6]").length;
                            //impresion de la cuenta de usuarios
                            $("#divRegistros").html("Magistrados: " + magistrados + "<br>");
                            $("#divRegistros").append("CNR: " + cnr + "<br>");
                            $("#divRegistros").append("TSE: " + tse);
                            //desactivacion y activacion de campos
                            $("#slTipoUsuario").prop('disabled', false);
                            $("#btnModificar").prop('disabled', 'disabled');
                            $("#btnAgregar").prop('disabled', false);
                            $("#frmUsuarios")[0].reset();
                            $("#divUsuario").html('<input type="hidden" value="0" id="txtResultado">');
                        });

                    } else {
                        alert("Ingresa un dui que no este en uso");
                    }
                });
            });
            function cargar(idUsuario, dui, nombre, apellido, sexo, fecha, departamento, municipio, direccion, tipoUsuario) {
                $("#txtId").val(idUsuario);
                $("#txtDui").val(dui);
                $("#txtNombre").val(nombre);
                $("#txtApellido").val(apellido);
                //se cargan de nuevo los input, si los seleccionaba de la forma normal, cambiando
                //la propiedad checked, solo se cambiaba una vez, luego ya no funcionaba
                if (sexo === "M") {
                    $("#divSexo").html('<input type="radio" name="rbdSexo" value="M" id="male" checked="checked" class="with-gap radio-col-light-blue">');
                    $("#divSexo").append('<label for="male">Hombre</label>');
                    $("#divSexo").append('<input type="radio" name="rbdSexo" id="female" value="F"  class="with-gap radio-col-light-blue">');
                    $("#divSexo").append('<label for="female" class="m-l-20">Mujer</label>');
                } else {
                    $("#divSexo").html('<input type="radio" name="rbdSexo" value="M" id="male" class="with-gap radio-col-light-blue">');
                    $("#divSexo").append('<label for="male">Hombre</label>');
                    $("#divSexo").append('<input type="radio" name="rbdSexo" id="female" value="F" checked="checked" class="with-gap radio-col-light-blue">');
                    $("#divSexo").append('<label for="female" class="m-l-20">Mujer</label>');
                }
                $("#txtFecha").val(fecha);
                $("#slDepartamento").val(1);
                //luego de seleccionar departamento, debo cargar nuevamente los municipios
                $.post('../SerDepartamento', {
                    dep: departamento
                }, function (data) {
                    //Se cargan los municipios
                    $("#divMunicipio").html(data);
                    //se selecciona el municipio 
                    $("#slMunicipio").val(municipio);
                });
                $("#txtDireccion").val(direccion);
                $("#slTipoUsuario").val(tipoUsuario);
                //Se cambia el valor de este campo a 1, para que no sea necesario buscar de nuevo
                //el numero de dui del usuario seleccionado
                $("#divUsuario").html('<input type="hidden" value="1" id="txtResultado">');
                //deshabilitamos el combo de tipo de usuario, para evitar que cambie ese campo
                $("#slTipoUsuario").prop('disabled', 'disabled');
                $("#btnModificar").prop('disabled', false);
                $("#btnAgregar").prop('disabled', 'disabled');
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
                            <form id="formulario_magistrado">
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Tipo Usuario:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <select class="form-control show-tick" data-live-search="true" name="slTipoUsuario" id="slTipoUsuario">
                                                <option>Seleccione tipo usuario</option>
                                                <option value="2">Magistrado</option>
                                                <option value="3">Director CNR</option>
                                                <option value="6">Director TSE</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>DUI:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="hidden" name="txtId" id="txtId">
                                                <input type="text" class="form-control" name="txtDui" maxlength="10" id="txtDui" placeholder="Ingresar número DUI">
                                            </div>
                                            <label id="divUsuario">
                                                <input type="hidden" value="0" id="txtResultado">
                                            </label>
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
                                                <input type="text" class="form-control" id="txtNombre" name="txtNombre" placeholder="Ingresar nombre">
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
                                                <input type="text" class="form-control" id="txtApellido" name="txtApellido" placeholder="Ingresar apellido">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Género:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group" id="divSexo">
                                            <input type="radio" name="rbdSexo" value="M" id="male" class="with-gap radio-col-light-blue">
                                            <label for="male">Hombre</label>
                                            <input type="radio" name="rbdSexo" id="female"  value="F"  class="with-gap radio-col-light-blue">
                                            <label for="female" class="m-l-20">Mujer</label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Fecha Nacimiento:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="date" class="form-control" name="txtFecha" id="txtFecha"  >
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Departamento:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div >
                                            <select  name="slDepartamento" id="slDepartamento" >
                                                <option value="0">Seleccione departamento</option>
                                                <% for (Departamento dep : DepartamentoDTO.mostrarDepartamentos()) {%>
                                                <option value="<%= dep.getIdDepartamento()%>"><%= dep.getDepartamento()%></option>
                                                <% }%>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Municipio:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div id="divMunicipio">
                                            <select  name="slMunicipio" id="slMunicipio" >
                                                <option>Seleccione municipio</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-lg-4 col-md-4 col-sm-3 col-xs-6 form-control-label">
                                        <label>Dirección:</label>
                                    </div>
                                    <div class="col-lg-8 col-md-8 col-sm-9 col-xs-6">
                                        <div class="form-group">
                                            <div class="form-line">
                                                <input type="text" class="form-control" name="txtDireccion" id="txtDireccion" placeholder="Ingresar domicilio">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="row clearfix">
                                    <div class="col-md-12 align-center">
                                        <button type="button" class="btn bg-light-blue waves-effect waves-light" id="btnAgregar" name="btnAgregar">Guardar</button>
                                        <button type="button" class="btn bg-cyan waves-effect waves-light" id="btnModificar" name="btnModificar">Modificar</button>
                                        <button type="reset" class="btn bg-cyan waves-effect waves-light">Cancelar</button>
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
                            <table class="table table-bordered table-striped table-hover js-basic-example dataTable text-center">
                                <thead>
                                    <tr>
                                        <th>#</th>
                                        <th>DUI:</th>
                                        <th>Nombre:</th>
                                        <th>Sexo:</th>
                                        <th>Usuario:</th>
                                        <th>Ubicación:</th>
                                        <th>Dirección:</th>
                                        <th>Opciones:</th>
                                    </tr>
                                </thead>
                                <tbody id="divUsuarios">
                                    <% int listar=1;for (Ciudadano us : CiudadanoDTO.mostrarUsuariosPrincipales()) {%>
                                    <tr>
                                        <td><%= listar%></td>
                                        <td><%= us.getNumDui()%></td>
                                        <td><%= us.getApellido()%>, <%= us.getNombre()%></td>
                                        <td>
                                            <%
                                                if (us.getSexo().equals("M")) {
                                                    out.print("Masculino");
                                                } else {
                                                    out.print("Femenino");
                                                }
                                            %>
                                        </td>
                                        <td data-rol="<%= us.getTipoUsuario()%>"><%= us.getRol()%></td>
                                        <td><%= DepartamentoDTO.mostrarDepartamento(us.getIdDepartamento()).getDepartamento()%>, <%= MunicipioDTO.mostrarUnMunicipio(us.getIdMunicipio()).getNombreMunicipio()%></td>
                                        <td><%= us.getDireccion()%></td>
                                        <td>
                                            <button type="button" class="btn btn2 bg-cyan waves-effect m-r-0 waves-light" onclick="javascript:cargar('<%=us.getIdUsuario()%>','<%=us.getNumDui()%>','<%=us.getNombre()%>','<%=us.getApellido()%>','<%=us.getSexo()%>','<%=us.getFechaNac()%>','<%=us.getIdDepartamento()%>','<%=us.getIdMunicipio()%>','<%=us.getDireccion()%>','<%=us.getTipoUsuario()%>')" ><i class="material-icons">create</i></button>
                                            
                                        </td>
                                    </tr>
                                    <% listar++;}%>
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
