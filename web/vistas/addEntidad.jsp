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
            background: #000000;
            border: none;
            transition: background-color 0.3s;
            cursor: pointer;
            display: inline-block;
        }
        .navbar a:hover, .dropbtn:hover {
            background-color: #e38d13;
        }
        .dropdown {
            display: inline-block;
            position: relative;
        }
        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #000;
            min-width: 160px;
            box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
            z-index: 999;
            top: 50px;
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
            background-color: #555;
        }
        .dropdown:hover .dropdown-content {
            display: block;
        }
        .container {
            margin-top: 20px;
        }
        h1, h2 {
            text-align: center;
        }
        .dual-column {
            display: flex;
            justify-content: space-around;
            margin: 20px auto;
            width: 80%;
        }
        .search-section {
            width: 45%;
            padding: 10px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        table {
            width: 100%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
        }
        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
    </style>
</head>
<body>
    <!-- Barra de navegación -->
    <div class="navbar">
        <div class="dropdown">
            <button class="dropbtn">Menú</button>
            <div class="dropdown-content">
                <a href="Controlador?accion=listar">Lista de Usuarios</a>
                <a href="Controlador?accion=add">Agregar Usuario</a>
                <a href="Controlador?accion=listarr">Lista de Solicitud y Muestra</a>
                <a href="#home2">Reportes</a>
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
                        <th>Tipo</th>
                       <!-- <th>Acción</th>-->
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td><%= entidadPorNit.getEntidadId() %></td>
                        <td><%= entidadPorNit.getEntidadNit() %></td>
                        <td><%= entidadPorNit.getEntidadNombre() %></td>
                        <!--<td><%= entidadPorNit.getEntidadTipo() %></td>-->
                        <td>
                            <!-- Botón para agregar entidad -->
                            <form action="Controlador" method="get">
                                <input type="hidden" name="menu" value="listaent">
                                <input type="hidden" name="nit" value="<%= entidadPorNit.getEntidadNit() %>">
                                <input type="hidden" name="nombreEntidad" value="<%= entidadPorNit.getEntidadNombre() %>">
                                
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
