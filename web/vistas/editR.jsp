<%@page import="Modelo.SoliMuestra"%>
<%@page import="ModeloDAO.SoliMuestraDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Modificar Solicitud</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .navbar {
                padding: 10px;
                position: relative;
                z-index: 10;
            }

            .navbar a, .dropbtn {
                color: white;
                text-align: center;
                padding: 14px 20px;
                text-decoration: none;
                font-size: 18px;
                background-color: #e38d13;
                border: none;
                cursor: pointer;
                display: inline-block;
                transition: background-color 0.3s;
            }

            .navbar a:hover, .dropbtn:hover {
                background-color: #ffb74d;
            }

            .dropdown {
                display: inline-block;
                position: relative;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #e38d13;
                min-width: 160px;
                box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
                z-index: 999;
            }

            .dropdown-content a {
                color: white;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
                text-align: left;
            }

            .dropdown-content a:hover {
                background-color: #ffb74d;
            }

            /* Mostrar dropdown solo al pasar el mouse sobre el elemento correspondiente */
            .dropdown:hover > .dropdown-content {
                display: block;
            }

            /* Estilo para el submenú dentro de "Servicios" */
            .dropdown-content .dropdown {
                position: relative;
            }

            .dropdown-content .dropdown-content {
                display: none; /* Ocultar el submenú inicialmente */
                top: 0;
                left: 100%; /* Muestra el submenú a la derecha */
                margin-left: 1px;
            }

            .dropdown-content .dropdown:hover .dropdown-content {
                display: block; /* Mostrar el submenú al pasar el mouse */
            }

        </style>
    </head>
    <body>
        <div class="navbar">
            <div class="dropdown">
                <button class="dropbtn">Menú</button>
                <div class="dropdown-content">
                    <a href="Controlador?menu=inicio&accion=init">Inicio</a>
                    <div class="dropdown">
                        <a href="#" class="dropbtn">Servicios</a>
                        <div class="dropdown-content">
                            <div class="dropdown">
                                <a href="#" class="dropbtn">SCM</a>
                                <div class="dropdown-content">
                                    <a href="Controlador?menu=usuarios&accion=listar">Mantenimiento de Usuarios</a>
                                    <a href="Controlador?menu=listaent&accion=entidades">Mantenimiento de Catálogo</a>
                                    <a href="Controlador?menu=solicit&accion=listarr">Bandeja de Laboratorio</a>
                                    <a href="Controlador?menu=visualizarSolicitud&accion=visualizarSolicitudes">Visualización de Solicitudes</a>
                                    <a href="Controlador?menu=reasignar&accion=listReasignar">Reasignacion de Solicitudes</a>
                                </div>
                            </div>
                        </div>
                    </div>
                    <a href="Controlador?accion=reportes">Reportes</a>
                </div>
            </div>
            <a href="Controlador?accion=index">Cerrar Sesión</a>
        </div>

        <div class="container">
            <div class="col-lg-6">
                <%
                    // Obtén el objeto SoliMuestra de la solicitud
                    SoliMuestra sl = (SoliMuestra) request.getAttribute("soliMuestra");
                %>
                <h1>Modificar Solicitud</h1><a class="btn btn-secondary" href="Controlador?menu=solicit&accion=listarr">Regresar</a>
                <form action="Controlador?menu=solicit" method="POST">
                    <div class="row">
                        <div class="col-md-6">
                            Tipo de Solicitud:<br>
                            <input class="form-control" type="text" name="txtSoli" value="<%= sl.getTipoSolicitud()%>" readonly><br>
                            id soli:<br>
                            <input class="form-control" type="text" name="txtcorre" value="<%= sl.getIdSolicitud()%>" readonly><br>

                            Fecha Solicitud:<br>
                            <input class="form-control" type="text" name="txtFecha" value="<%= sl.getFechaSolicitud()%>" readonly><br>

                            Tipo de Documento:<br>
                            <input class="form-control" type="text" name="txtDoc" value="<%= sl.getTipodeDocumento()%>" readonly><br>

                            No de Documento:<br>
                            <input class="form-control" type="text" name="txtNodoc" value="<%= sl.getNoDedocumento()%>" readonly><br>

                            Nit Proveedor:<br>
                            <input class="form-control" type="text" name="txtNitProv" value="<%= sl.getNitProveedor()%>" readonly><br>

                            Nombre Proveedor:<br>
                            <input class="form-control" type="text" name="txtNomProv" value="<%= sl.getNombreProveedor()%>" readonly><br>

                            Correo Proveedor:<br>
                            <input class="form-control" type="text" name="txtCorProv" value="<%= sl.getCorreoProveedor()%>" readonly><br>
                        </div>

                        <div class="col-md-6">
                            Correo Solicitante:<br>
                            <input class="form-control" type="text" name="txtCorSol" value="<%= sl.getCorreoSolicitante()%>" readonly><br>

                            Dirección Proveedor:<br>
                            <input class="form-control" type="text" name="txtDiProv" value="<%= sl.getDireccionProveedor()%>" readonly><br>

                            Teléfono Proveedor:<br>
                            <input class="form-control" type="text" name="txtTelProv" value="<%= sl.getTelefonoProveedor()%>" readonly><br>

                            Nit Solicitante:<br>
                            <input class="form-control" type="text" name="txtNitSol" value="<%= sl.getNitSolicitante()%>" readonly><br>

                            Nombre Solicitante:<br>
                            <input class="form-control" type="text" name="txtNomSol" value="<%= sl.getNombreSolicitante()%>" readonly><br>

                            No de Muestra:<br>
                            <input class="form-control" type="text" name="txtNoMuestra" value="<%= sl.getNoMuestra()%>" readonly><br>

                            Descripción de Producto:<br>
                            <input class="form-control" type="text" name="txtDescProd" value="<%= sl.getDescripcionProducto()%>" readonly><br>
                        </div>
                    </div>

                    <input type="hidden" name="txtIdSol" value="<%= sl.getIdSolicitud()%>">
                     <button type="submit" name="accion" value="generarpdf" class="btn btn-primary">Generar PDF</button>
                    


                </form>

            </div>
        </div>
    </body>
</html>
