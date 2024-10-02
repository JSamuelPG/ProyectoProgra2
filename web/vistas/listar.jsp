<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Users"%>
<%@page import="Modelo.Roles"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Lista de Usuarios</title>
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
                <a href="Controlador?accion=listar">Lista de Usuarios</a>
                <a href="Controlador?accion=add">Agregar Usuario</a>
                <a href="Controlador?accion=listarr">Lista de Solicitud y Muestra</a>
                <a href="#home2">Reportes</a>
            </div>
        </div>

        <!-- Opción de Cerrar Sesión que siempre estará visible -->
        <a href="Controlador?accion=index">Cerrar Sesión</a>
    </div>

    <!-- Contenido principal -->
    <div class="container">
        <h1>Lista de Usuarios</h1>
        <br>
        <br>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">ID Usuario</th>
                    <th class="text-center">Primer Nombre</th>
                    <th class="text-center">Segundo Nombre</th>
                    <th class="text-center">Primer Apellido</th>
                    <th class="text-center">Segundo Apellido</th>
                    <th class="text-center">Login</th>
                    <th class="text-center">Contraseña</th>
                    <th class="text-center">NIT</th>
                    <th class="text-center">Puesto</th>
                    <!--<th class="text-center">Rol</th>-->
                        <th class="text-center">Rol</th>
                    <th class="text-center">Estado</th>
                    <th class="text-center">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    PersonaDAO dao = new PersonaDAO();
                    List<Users> list = dao.listar();
                    Iterator<Users> iter = list.iterator();
                    Users user = null;
                    while (iter.hasNext()) {
                        user = iter.next();
                %>
                <tr>
                    <td class="text-center"><%= user.getIdusuario() %></td>
                    <td class="text-center"><%= user.getPrimerNombre() %></td>
                    <td class="text-center"><%= user.getSegundoNombre() %></td>
                    <td class="text-center"><%= user.getPrimerApellido() %></td>
                    <td class="text-center"><%= user.getSegundoApellido() %></td>
                    <td class="text-center"><%= user.getLogin() %></td>
                    <td class="text-center"><%= user.getContrasenia() %></td>
                    <td class="text-center"><%= user.getNitpersona() %></td>
                    <td class="text-center"><%= user.getPuesto() %></td>
                    <!--<td class="text-center"><%= user.getIdRol()%></td>-->
                        <td class="text-center"><%= user.getNombreRol()%></td>
                    <td class="text-center"><%= user.getEstado() %></td>
                    <td class="text-center">
                        <a class="btn btn-warning" href="Controlador?accion=editar&idusua=<%= user.getIdusuario() %>">Editar</a>
                        <a class="btn btn-danger" href="Controlador?accion=eliminar&id=<%= user.getIdusuario() %>">Eliminar</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

</body>
</html>
