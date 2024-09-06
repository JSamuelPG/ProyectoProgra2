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
                    int id=Integer.parseInt((String)request.getAttribute("idusuario"));
                    Users user=(Users)dao.list(id);
                %>
                <h1>Modificar Usuario</h1>
                <form action="Controlador">
                    Primer Nombre:<br>
                    <input class="form-control" type="text" name="txtNom1" value="<%= user.getPrimerNombre()%>"><br>
                    Segundo Nombre:<br>
                    <input class="form-control" type="text" name="txtNom2" value="<%= user.getSegundoNombre()%>"><br>
                    Primer Apellido:<br>
                    <input class="form-control" type="text" name="txtAp1" value="<%= user.getPrimerApellido()%>"><br>
                    Segundo Apellido:<br>
                    <input class="form-control" type="text" name="txtAp2" value="<%= user.getSegundoApellido()%>"><br>
                    <!--Login:<br>
                    <input class="form-control" type="text" name="txtLog" value="<%= user.getLogin()%>"><br>-->
                    Contraseña:<br>
                    <input class="form-control" type="password" name="txtCont" value="<%= user.getContrasenia()%>"><br>
                    NIT Persona:<br>
                    <input class="form-control" type="text" name="txtNit" value="<%= user.getNitpersona()%>"><br>
                    Puesto:<br>
                    <input class="form-control" type="text" name="txtPuesto" value="<%= user.getPuesto()%>"><br>
                    Rol:<br>
                    <input class="form-control" type="number" name="txtRol" value="<%= user.getRoles()%>"><br>

                    <!-- Campo oculto para el ID del usuario -->
                    <input type="hidden" name="txtidusu" value="<%= user.getIdusuario() %>">
                    
                    <!-- Botón para enviar el formulario -->
                    <input class="btn btn-primary" type="submit" name="accion" value="Actualizar"> 
                    <!-- Enlace para regresar -->
                    <a class="btn btn-secondary" href="Controlador?accion=listar">Regresar</a>
                </form>
                
            </div>
        </div>
    </body>
</html>


