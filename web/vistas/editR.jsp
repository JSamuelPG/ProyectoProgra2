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
            overflow: hidden;
            background: #000000;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
            margin-bottom: 20px;
        }
        .navbar a {
            float: left;
            display: block;
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
            font-size: 18px;
            transition: all 0.3s ease;
        }
        .navbar a:hover {
            background-color: #7f8c8d;
            color: white;
        }
        .navbar a.active {
            background-color: #3498db;
            color: white;
        }
    </style>
</head>
<body>
    <div class="navbar">
        <a href="Controlador?accion=init">Inicio</a>
        <a href="Controlador?accion=listar">Lista de Usuarios</a>
        <a href="Controlador?accion=add">Agregar Usuarios</a>
        <a href="#home1">Servicios</a>
        <a href="#home2">Reportes</a>
        <a class="active" href="#home">Editando</a>
        <a href="Controlador?accion=listar">Regresar</a>
        <a href="Controlador?accion=index">Cerrar Sesión</a>
    </div>
    <div class="container">
        <div class="col-lg-6">
            <%
                SoliMuestraDAO dao = new SoliMuestraDAO();
                int id = Integer.parseInt((String) request.getAttribute("idsolicitud"));
                SoliMuestra sl = (SoliMuestra) dao.listR(id);
            %>
            <h1>Modificar Solicitud</h1>
            <form action="Controlador">
                Tipo de Solicitud:<br>
                <select class="form-control" name="textSoli" id="txtSoli">
                    <option value="MuestraparaAnalisis">Muestra para análisis</option>
                    <option value="SolicitudsinMuestra">Solicitud sin muestra</option>
                    <option value="ConNumerodeMuestra">Con número de muestra</option>
                </select><br>

                Tipo de Entidad:<br>
                <select class="form-control" id="textEnti" name="txtEnti">
                    <option value="EntidadPrivada">Entidad privada</option>
                    <option value="EntidadPublica">Entidad pública</option>
                </select><br>

                Fecha Solicitud:<br>
                <input class="form-control" type="text" name="txtFecha" value="<%= sl.getFechaSolicitud() %>"><br>

                Tipo de Documento:<br>
                <input class="form-control" type="text" name="txtDoc" value="<%= sl.getTipodeDocumento() %>"><br>

                No de Documento:<br>
                <input class="form-control" type="text" name="txtNodoc" value="<%= sl.getNoDedocumento() %>"><br>

                Nit Proveedor:<br>
                <input class="form-control" type="text" name="txtNitProv" value="<%= sl.getNitProveedor() %>"><br>

                Nombre Proveedor:<br>
                <input class="form-control" type="text" name="txtNomProv" value="<%= sl.getNombreProveedor() %>"><br>

                Correo Proveedor:<br>
                <input class="form-control" type="text" name="txtCorProv" value="<%= sl.getCorreoProveedor() %>"><br>

                Correo Solicitante:<br>
                <input class="form-control" type="text" name="txtCorSol" value="<%= sl.getCorreoSolicitante() %>"><br>

                Dirección Proveedor:<br>
                <input class="form-control" type="text" name="txtDiProv" value="<%= sl.getDireccionProveedor() %>"><br>

                Teléfono Proveedor:<br>
                <input class="form-control" type="text" name="txtTelProv" value="<%= sl.getTelefonoProveedor() %>"><br>

                Nit Solicitante:<br>
                <input class="form-control" type="text" name="txtNitSol" value="<%= sl.getNitSolicitante() %>"><br>

                Nombre Solicitante:<br>
                <input class="form-control" type="text" name="txtNomSol" value="<%= sl.getNombreSolicitante() %>"><br>

                No de Muestra:<br>
                <input class="form-control" type="text" name="txtNoMuestra" value="<%= sl.getNoMuestra() %>"><br>

                Descripción de Producto:<br>
                <input class="form-control" type="text" name="txtDescProd" value="<%= sl.getDescripcionProducto() %>"><br>

                <input type="hidden" name="txtIdSol" value="<%= sl.getIdSolicitud() %>">

                <input class="btn btn-primary" type="submit" name="accion" value="Actualizar">
            </form>
        </div>
    </div>
</body>
</html>
