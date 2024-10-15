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
        background: #e38d13; /* Color de fondo de la barra de navegación */
        border: none;
        transition: background-color 0.3s;
        cursor: pointer;
        display: inline-block;
    }
    .navbar a:hover, .dropbtn:hover {
        background-color: #ffb74d; /* Color al pasar el mouse */
    }
    .dropdown {
        display: inline-block;
        position: relative;
    }
    .dropdown-content {
        display: none;
        position: absolute;
        background-color: #e38d13; /* Color de fondo del menú desplegable */
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
        background-color: #ffb74d; /* Color al pasar el mouse sobre los elementos del menú */
    }
    .dropdown:hover > .dropdown-content {
        display: block; /* Mostrar el contenido del menú al pasar el mouse por el dropdown */
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
        margin: 20px auto;
        border-collapse: collapse;
        background-color: white;
    }
    th, td {
        border: 1px solid #ddd;
        padding: 8px; /* Cambiado a 8px para mantener la consistencia */
        text-align: left;
    }
    th {
        background-color: #f2f2f2; /* Color de fondo de las cabeceras */
    }
    tr:hover {
        background-color: #f1f1f1; /* Color al pasar el mouse sobre las filas */
    }
    .container {
        margin-top: 20px; /* Espaciado superior para el contenedor */
    }
    h1, h2 {
        text-align: center; /* Alineación central para los encabezados */
    }
    .dual-column {
        display: flex;
        justify-content: space-around; /* Espaciado entre columnas */
        margin: 20px auto;
        width: 80%; /* Ancho del contenedor de dos columnas */
    }
    .search-section {
        width: 45%; /* Ancho de la sección de búsqueda */
        padding: 10px;
        background-color: white; /* Color de fondo de la sección de búsqueda */
        border-radius: 5px; /* Bordes redondeados */
        box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1); /* Sombra para la sección */
    }
</style>

</head>
<body>
<div class="navbar">
    <div class="dropdown">
        <button class="dropbtn">Menú</button>
        <div class="dropdown-content">
            <a href="Controlador?menu=usuarios&accion=listar">Lista de Usuarios</a>
            <div class="dropdown">
                <a href="#" class="dropbtn">Servicios</a>
                <div class="dropdown-content">
                    <div class="dropdown">
                        <a href="#" class="dropbtn">SCM</a>
                        <div class="dropdown-content">
                            <a href="Controlador?menu=listaent&accion=entidades">Mantenimiento de Catálogo</a>
                            <a href="Controlador?menu=solicit&accion=listarr">Bandeja de Laboratorio</a>
                        </div>
                    </div>
                </div>
            </div>
            <a href="Controlador?accion=reportes">Reportes</a>
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
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
    <%
            for (Entidad entidad : listaEntidades) {
    %>
                <tr>
                    <td><%= entidad.getEntidadNombre() %></td>
                    <td><%= entidad.getEntidadNit() %></td>
                    <td>
                        <!-- Botón para eliminar la entidad -->
                        <form action="Controlador" method="post" style="display:inline;">
                            <input type="hidden" name="menu" value="listaent">
                            <input type="hidden" name="accion" value="eliminarEntidad">
                            <input type="hidden" name="idEntidad" value="<%= entidad.getEntidadId()%>">
                            <button type="submit" class="btn btn-danger">Eliminar</button>
                        </form>
                    </td>
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
