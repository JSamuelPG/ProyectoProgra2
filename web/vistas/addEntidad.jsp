<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ModeloDAO.EntidadDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Entidad"%>
<%@page import="java.util.Iterator"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Filtrar Entidades</title>
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
    
<!-- Formulario de búsqueda -->
<div class="container">
    <h1>Buscar Entidad por Nit</h1>
    <form action="Controlador" method="get">
        <input type="hidden" name="menu" value="listaent">
        <label for="nitEntidad">NIT:</label>
        <input type="text" name="nitEntidad" id="nitEntidad" required>
        <button type="submit" name="accion" value="obtenerpornit" class="btn btn-info">Buscar</button>
        <a class="btn btn-secondary" href="Controlador?menu=listaent&accion=entidades">Regresar</a>
    </form>

    <!-- Mostrar resultados de búsqueda -->
    <% 
    Entidad entidadPorNit = (Entidad) request.getAttribute("entidadPorNit");
    String mensaje = (String) request.getAttribute("mensaje");
    if (entidadPorNit != null) { 
    %>
        <h3>Resultado de la Búsqueda por NIT</h3>
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>NIT</th>
                    <th>Nombre</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><%= entidadPorNit.getEntidadId() %></td>
                    <td><%= entidadPorNit.getEntidadNit() %></td>
                    <td><%= entidadPorNit.getEntidadNombre() %></td>
                    <td>
                        <!-- Botón para agregar entidad -->
                        <form action="Controlador" method="get">
                            <input type="hidden" name="menu" value="listaent">
                            <input type="hidden" name="nit" value="<%= entidadPorNit.getEntidadNit() %>">
                            <input type="hidden" name="nombreEntidad" value="<%= entidadPorNit.getEntidadNombre() %>">                                
                            <input type="hidden" name="correoEntidad" value="<%= entidadPorNit.getEntidadCorreo() %>">
                            <input type="hidden" name="direccionEntidad" value="<%= entidadPorNit.getEntidadDireccion() %>">
                            <input type="hidden" name="telefonoEntidad" value="<%= entidadPorNit.getEntidadTelefono() %>">
                            
                            <label for="tipoEntidad">Tipo de Entidad:</label>
                            <select name="tipoEntidad" id="tipoEntidad" required>
                                <option value="Privada">Privada</option>
                                <option value="Publica">Pública</option>
                            </select>

                            <button type="submit" name="accion" value="agregarEntidad" class="btn btn-success">Agregar Entidad</button>
                        </form>
                    </td>
                </tr>
            </tbody>
        </table>
    <% 
    } else if (mensaje != null) { 
    %>
        <p><%= mensaje %></p>
    <% 
    } 
    %>
</div>
</body>
</html>
