<%@page import="Modelo.Users"%>
<%@page import="Modelo.Roles"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Modificar Usuario</title>
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
        <div class="container">
        <div class="col-lg-6">
            <%
                PersonaDAO dao = new PersonaDAO();
                int idUsuario = Integer.parseInt(request.getParameter("idusuario")); // Obtener el ID del usuario desde la URL
                Users user = dao.list(idUsuario); // Obtener el usuario por ID
                List<Roles> listaRoles = dao.listaRoles(); // Obtener la lista de roles
                Iterator<Roles> iter = listaRoles.iterator();
                Roles rolUsuario = null;

                // Buscar el rol del usuario en la lista de roles
                while (iter.hasNext()) {
                    Roles rol = iter.next();
                    if (rol.getIdRol() == user.getIdRol()) {
                        rolUsuario = rol;
                        break;
                    }
                }
            %>

            <h1>Modificar Usuario</h1>

            <form action="Controlador" method="post">
                <input type="hidden" name="accion" value="actualizar"> <!-- Acción para actualizar -->
                <input type="hidden" name="idusua" value="<%= user.getIdusuario() %>"> <!-- ID del usuario -->

                <div class="row">
                    <div class="col-md-6">
                        Primer Nombre:<br>
                        <input class="form-control" type="text" name="txtNom1" value="<%= user.getPrimerNombre() %>" readonly><br>

                        Segundo Nombre:<br>
                        <input class="form-control" type="text" name="txtNom2" value="<%= user.getSegundoNombre() %>" readonly><br>

                        Primer Apellido:<br>
                        <input class="form-control" type="text" name="txtAp1" value="<%= user.getPrimerApellido() %>" readonly><br>

                        Segundo Apellido:<br>
                        <input class="form-control" type="text" name="txtAp2" value="<%= user.getSegundoApellido() %>" readonly><br>

                        Contraseña:<br>
                        <input class="form-control" type="text" name="txtCont" value="<%= user.getContrasenia() %>" readonly><br>

                    </div>

                    <div class="col-md-6">
                        <!-- Mostrar nombre del rol actual -->
                        NIT Persona:<br>
                        <input class="form-control" type="text" name="txtNit" value="<%= user.getNitpersona() %>" readonly><br>

                        Puesto:<br>
                        <input class="form-control" type="text" name="txtPuesto" value="<%= user.getPuesto() %>" readonly><br>
                        
                        Rol Actual:<br>
                        <input class="form-control" type="text" name="Rol" value="<%= rolUsuario != null ? rolUsuario.getNombreRol() : "Sin rol asignado" %>" readonly><br>

                        Estado:<br>
                        <select class="form-control" name="txtEstado" required>
                            <option value="Activo" <%= user.getEstado().equals("Activo") ? "selected" : "" %>>Activo</option>
                            <option value="Inactivo" <%= user.getEstado().equals("Inactivo") ? "selected" : "" %>>Inactivo</option>
                        </select>

                        Motivo del cambio de estado:<br>
                        <input class="form-control" name="txtMotivo" required><br>
                    </div>
                </div>

                <br>
                <button type="submit" class="btn btn-success">Guardar Cambios</button>
                <a class="btn btn-danger" href="Controlador?menu=usuarios&accion=listar">Cancelar</a>
            </form>


        </div>
    </div>
</body>
</html>