
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
    </head>
    <body>
        <div class="container">
            <h1>Personas</h1>
            <a class="btn btn-success" href="Controlador?accion=add">Agregar Nuevo</a>
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
