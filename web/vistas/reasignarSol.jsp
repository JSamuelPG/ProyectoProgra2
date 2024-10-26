<%@page import="java.util.Iterator"%>
<%@page import="Modelo.SoliMuestra"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.SoliMuestraDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Lista de Solicitudes y Muestras</title>
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
            transition: background-color 0.3s;
            cursor: pointer;
            display: inline-block;
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
            top: 50px; /* Ajuste para la posición */
            left: 0;
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
        /* Mostrar el contenido del menú al pasar el mouse por el dropdown */
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
            display: block; /* Mostrar el submenú al pasar el mouse sobre "SCM" */
        }
        /* Estilos para la tabla */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
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
        <h1>Reasignación de Solicitudes</h1>
        <br>
        <a class="btn btn-primary" href="Controlador?menu=solicit&accion=addr">Nueva Solicitud de Muestra</a>
        <br>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">Fecha Solicitado</th>
                    <th class="text-center">Tipo de Documento</th>
                    <th class="text-center">No de documento</th>
                    <th class="text-center">Nit Proveedor</th>
                    <th class="text-center">Nombre del Proveedor</th>
                    <th class="text-center">Correo Proveedor</th>
                    <th class="text-center">Correo Solicitante</th>
                    <th class="text-center">Direccion</th>
                    <th class="text-center">Telefono</th>
                    <th class="text-center">Nit Solicitante</th>
                    <th class="text-center">Nombre Solicitante</th>
                    <th class="text-center">No de Muestra</th>
                    <th class="text-center">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    SoliMuestraDAO dao = new SoliMuestraDAO();
                    List<SoliMuestra> list = dao.listarR();
                    Iterator<SoliMuestra> iter = list.iterator();
                    SoliMuestra sm = null;
                    while (iter.hasNext()) {
                        sm = iter.next();
                %>
                <tr>
                    <td class="text-center"><%= sm.getFechaSolicitud()%></td>
                    <td class="text-center"><%= sm.getTipodeDocumento()%></td>
                    <td class="text-center"><%= sm.getNoDedocumento()%></td>
                    <td class="text-center"><%= sm.getNitProveedor()%></td>
                    <td class="text-center"><%= sm.getNombreProveedor()%></td>
                    <td class="text-center"><%= sm.getCorreoProveedor() %></td>
                    <td class="text-center"><%= sm.getCorreoSolicitante() %></td>
                    <td class="text-center"><%= sm.getDireccionProveedor() %></td>
                    <td class="text-center"><%= sm.getTelefonoProveedor() %></td>
                    <td class="text-center"><%= sm.getNitSolicitante() %></td>
                    <td class="text-center"><%= sm.getNombreSolicitante() %></td>
                    <td class="text-center"><%= sm.getNoMuestra() %></td>
                    <td class="text-center">
                        <input type="hidden" name="menu" value="solicit">
                        <a class="btn btn-warning" href="Controlador?menu=solicit&accion=visualizar&idSolicitud=<%= sm.getIdSolicitud() %>">Visualizar</a>
                        <a class="btn btn-danger" href="Controlador?menu=solicit&accion=eliminarr&idsolicitud=<%= sm.getIdSolicitud() %>">Eliminar</a>
  
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

</body>
</html>