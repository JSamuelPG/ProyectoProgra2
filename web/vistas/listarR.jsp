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

        /* Estilos para el contenedor del menú */
        .navbar {
            padding: 10px;
            position: relative;
            z-index: 10;
        }

        /* Estilos para el botón Menú y Cerrar Sesión */
        .navbar a, .dropbtn {
            color: white;
            text-align: center;
            padding: 14px 20px;
            text-decoration: none;
            font-size: 18px;
            background: #000000;
            border: none;
            transition: background-color 0.3s;
            cursor: pointer;
            display: inline-block;
        }

        /* Hover en los botones de menú y cerrar sesión */
        .navbar a:hover, .dropbtn:hover {
            background-color: #e38d13;
        }

        /* Estilos para el dropdown */
        .dropdown {
            display: inline-block;
            position: relative;
        }

        /* Contenedor del dropdown (escondido por defecto) */
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #000;
            min-width: 160px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 999;
            top: 50px; /* Asegura que el menú se despliegue fuera del botón */
            left: 0;
        }

        /* Estilos para los enlaces dentro del dropdown */
        .dropdown-content a {
            color: white;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        /* Hover en los enlaces del dropdown */
        .dropdown-content a:hover {
            background-color: #555;
        }

        /* Mostrar el dropdown al pasar el ratón */
        .dropdown:hover .dropdown-content {
            display: block;
        }
    </style>
</head>
<body>

    <!-- Solo se muestra el botón Menú y Cerrar Sesión -->
    <div class="navbar">
        <!-- Botón Menú que despliega las demás opciones -->
        <div class="dropdown">
            <button class="dropbtn">Menú</button>
            <div class="dropdown-content">
                <a href="Controlador?accion=init">Inicio</a>
                <a href="Controlador?accion=listar">Lista de Usuarios</a>
                <a href="Controlador?accion=add">Agregar Usuarios</a>
                <a class="active" href="#home">Lista de Solicitud Y Muestra</a>
                <a href="Controlador?menu=solicit&accion=addr">Nueva Solicitud y Muestra</a>
            </div>
        </div>

        <!-- Opción de Cerrar Sesión que siempre estará visible -->
        <a href="Controlador?accion=index">Cerrar Sesión</a>
    </div>

    <div class="container">
        <h1>Registro de Solicitudes y Muestras</h1>
        <br>
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
                        <a class="btn btn-warning" href="Controlador?accion=editarR&idsoli=<%= sm.getIdSolicitud()%>">Editar</a>
                        <a class="btn btn-danger" href="Controlador?accion=eliminarR&idsolicitud=<%= sm.getIdSolicitud() %>">Eliminar</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

</body>
</html>
