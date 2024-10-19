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

    <!-- Contenido principal -->
    <div class="container">
        <h1>Lista de Usuarios</h1>
        <a class="btn btn-primary" href="Controlador?menu=usuarios&accion=add">Agregar</a>
        <br>
        <br>
        <table class="table table-bordered">
            <thead>
                <tr>
                    <th class="text-center">ID Usuario</th>
                    <th class="text-center">Nombre Completo</th>
                    <th class="text-center">Login</th>
                    <th class="text-center">Contraseña</th>
                    <th class="text-center">NIT</th>
                    <th class="text-center">Puesto</th>
                    <!--<th class="text-center">Rol</th>-->
                    <th class="text-center">Rol</th>
                    <th class="text-center">Correo</th>    
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
                    <td class="text-center"><%= user.getPrimerNombre() %> <%= user.getSegundoNombre() %> <%= user.getPrimerApellido() %> <%= user.getSegundoApellido() %></td>
                    <td class="text-center"><%= user.getLogin() %></td>
                    <td class="text-center"><%= user.getContrasenia() %></td>
                    <td class="text-center"><%= user.getNitpersona() %></td>
                    <td class="text-center"><%= user.getPuesto() %></td>
                    <!--<td class="text-center"><%= user.getIdRol()%></td>-->
                        <td class="text-center"><%= user.getNombreRol()%></td>
                        <td class="text-center"><%= user.getCorreo()%></td>
                    <td class="text-center"><%= user.getEstado() %></td>
                    <td class="text-center">
                        <a class="btn btn-warning" href="Controlador?menu=usuarios&accion=editar&idusuario=<%= user.getIdusuario() %>">Editar</a>
                        <a class="btn btn-danger" href="Controlador?menu=usuarios&accion=eliminar&id=<%= user.getIdusuario() %>">Eliminar</a>
                    </td>
                </tr>
                <% } %>
            </tbody>
        </table>
    </div>

</body>
</html>
