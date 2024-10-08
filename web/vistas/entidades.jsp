<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ModeloDAO.EntidadDAO"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.Entidad"%>
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
    
    <h1>Buscar Entidad</h1>
    
    <form action="Controlador" method="get">
    <input type="hidden" name="menu" value="listaent">
    
    <label for="tipoEntidad">Selecciona el tipo de entidad:</label>
    <select name="tipoEntidad">
        <option value="privada">Privada</option>
        <option value="publica">Pública</option>
    </select>

    <button type="submit" name="accion" value="obtenerportipo" class="btn btn-info">Buscar</button>
    <a class="btn btn-info" href="Controlador?menu=listaent&accion=agregarEnt">Agregar</a>
</form>
    
    

    <%
        // Recupera el mensaje y la lista de entidades de la solicitud
        String mensaje = (String) request.getAttribute("mensaje");
        List<Entidad> listaEntidades = (List<Entidad>) request.getAttribute("listaEntidadesPorTipo");
        
        if (mensaje != null) {
    %>
        <div class="mensaje"><%= mensaje %></div>
    <%
        }

        if (listaEntidades != null && !listaEntidades.isEmpty()) {
    %>
        <h2>Resultados de la búsqueda:</h2>
        <table border="1">
            <thead>
                <tr>
                    <th>Nombre</th>
                    <th>NIT</th>
                </tr>
            </thead>
            <tbody>
    <%
            for (Entidad entidad : listaEntidades) {
    %>
                <tr>
                    <td><%= entidad.getEntidadNombre() %></td>
                    <td><%= entidad.getEntidadNit() %></td>
                </tr>
    <%
            }
    %>
            </tbody>
        </table>
    <%
        } else {
    %>
        <h2>No hay entidades para mostrar.</h2>
    <%
        }
    %>
</body>
</html>
