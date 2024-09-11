
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Users"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
        <style>
            /* Estilos para la cinta de opciones */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .navbar {
                overflow: hidden;
                background: #000000; /* Color negro */
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
                margin-bottom: 20px; /* Espacio debajo de la cinta */
            }
            .navbar a {
                float: left;
                display: block;
                color: white;
                text-align: center;
                padding: 14px 20px;
                text-decoration: none;
                font-size: 18px;
                position: relative;
                transition: all 0.3s ease;
            }
            .navbar a:hover {
                background-color: #7f8c8d; /* Color gris al pasar el ratón */
                color: white;
            }
            .navbar a.active {
                background-color: #3498db; /* Color azul para el Home */
                color: white;
            }
        </style>
    </head>
    <body>
         <!-- Cinta de opciones -->
        <div class="navbar">
            <a class="active" href="#home">Lista de Usuarios</a>
            <a href="Controlador?accion=add">Agregar Usuarios</a>
            <a href="Controlador?accion=index">Salir</a>
        </div>
        <div class="container">
            <h1>Personas</h1>
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
                        <th class="text-center">NIT Persona</th>
                        <th class="text-center">Puesto</th>
                        <th class="text-center">Rol</th>
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
                        <td class="text-center"><%= user.getRoles() %></td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="Controlador?accion=editar&idusua=<%= user.getIdusuario() %>">Editar</a>
                            <a class="btn btn-danger" href="Controlador?accion=eliminar&id=<%= user.getIdusuario() %>">Remove</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>

        </div>
    </body>
</html>
