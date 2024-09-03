<%@page import="Modelo.Users"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>Modificar Usuario</title>
    </head>
    <body>
        <div class="container">
            <div class="col-lg-6">
                <%
                  PersonaDAO dao=new PersonaDAO();
                  int idusuario=Integer.parseInt((String)request.getAttribute("idusuario"));
                  Users user=(Users)dao.list(idusuario);
                %>
                <h1>Modificar Usuario</h1>
                <form action="Controlador">
                    Primer Nombre:<br>
                    <input class="form-control" type="text" name="primerNombre" value="<%= user.getPrimerNombre() %>"><br>
                    Segundo Nombre:<br>
                    <input class="form-control" type="text" name="segundoNombre" value="<%= user.getSegundoNombre() %>"><br>
                    Primer Apellido:<br>
                    <input class="form-control" type="text" name="primerApellido" value="<%= user.getPrimerApellido() %>"><br>
                    Segundo Apellido:<br>
                    <input class="form-control" type="text" name="segundoApellido" value="<%= user.getSegundoApellido() %>"><br>
                    Login:<br>
                    <input class="form-control" type="text" name="login" value="<%= user.getLogin() %>"><br>
                    Contraseña:<br>
                    <input class="form-control" type="password" name="contrasenia" value="<%= user.getContrasenia() %>"><br>
                    NIT Persona:<br>
                    <input class="form-control" type="text" name="nitpersona" value="<%= user.getNitpersona() %>"><br>
                    Puesto:<br>
                    <input class="form-control" type="text" name="puesto" value="<%= user.getPuesto() %>"><br>
                    Rol:<br>
                    <input class="form-control" type="number" name="rol" value="<%= user.getRoles() %>"><br>

                    <input type="hidden" name="idusuario" value="<%= user.getIdusuario() %>">
                    <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 
                    <a href="Controlador?accion=listar">Regresar</a>
                </form>
            </div>
        </div>
    </body>
</html>

