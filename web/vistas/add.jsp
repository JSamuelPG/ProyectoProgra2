<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page import="Modelo.Users"%>
<%@page import="Modelo.Roles"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>Buscar Usuario por NIT</title>
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
            transition: background-color 0.3s;
            cursor: pointer;
            display: inline-block;
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
            background-color: #ffb74d;
        }
        .dropdown:hover > .dropdown-content {
            display: block;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: white;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
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


<div class="container">
    <h2>Buscar Usuario por NIT</h2>
    <form action="Controlador?menu=usuarios&accion=obtenUsuario" method="post">
        <div class="form-group">
            <label for="nitPersona">NIT:</label>
            <input type="text" class="form-control" id="nitPersona" name="nitPersona" required>
        </div>
        <button type="submit" class="btn btn-primary">Buscar</button>
    </form>

    <c:if test="${not empty usuarioPorNit}">
        <h3>Resultados de la Búsqueda</h3>
        <table>
            <tr>
                <th>ID Usuario</th>
                <th>Nombre Completo</th>
                <th>Login</th>
                <th>Contraseña</th>
                <th>NIT</th>
                <th>Puesto</th>
                <th>Estado</th>
                <th>Correo</th>
                <th>Rol</th>
                <th>Acción</th>
            </tr>
            <tr>
                <td>${usuarioPorNit.idusuario}</td>
                <td>${usuarioPorNit.primerNombre} ${usuarioPorNit.segundoNombre} ${usuarioPorNit.primerApellido} ${usuarioPorNit.segundoApellido}</td>
                <td>${usuarioPorNit.login}</td>
                <td>${usuarioPorNit.contrasenia}</td>
                <td>${usuarioPorNit.nitpersona}</td>
                <td>${usuarioPorNit.puesto}</td>
                <td>
                    <select name="txtEstado" required>
                        <option value="Activo" ${usuarioPorNit.estado == 'Activo' ? 'selected' : ''}>Activo</option>
                        <option value="Inactivo" ${usuarioPorNit.estado == 'Inactivo' ? 'selected' : ''}>Inactivo</option>
                    </select>
                </td>
                <td>${usuarioPorNit.correo}</td>
                <td>
                    <form action="Controlador" method="post">
                        <input type="hidden" name="menu" value="usuarios">
                        <input type="hidden" name="idUsuario" value="${usuarioPorNit.idusuario}">
                        <input type="hidden" name="txtNom1" value="${usuarioPorNit.primerNombre}">                                 
                        <input type="hidden" name="txtNom2" value="${usuarioPorNit.segundoNombre}">
                        <input type="hidden" name="txtAp1" value="${usuarioPorNit.primerApellido}">
                        <input type="hidden" name="txtAp2" value="${usuarioPorNit.segundoApellido}">
                        <input type="hidden" name="txtLog" value="${usuarioPorNit.login}">
                        <input type="hidden" name="txtCont" value="${usuarioPorNit.contrasenia}">
                        <input type="hidden" name="txtNit" value="${usuarioPorNit.nitpersona}">
                        <input type="hidden" name="txtPuesto" value="${usuarioPorNit.puesto}">
                        <input type="hidden" name="txtCorreo" value="${usuarioPorNit.correo}">
                        
                        <select class="form-control" name="txtRol" id="opciones">
                            <%
                                PersonaDAO dao = new PersonaDAO(); 
                                List<Roles> listaRoles = dao.listaRoles();
                                for (Roles rol : listaRoles) {
                            %>
                                <option value="<%= rol.getIdRol() %>"><%= rol.getNombreRol() %></option>
                            <%
                                }
                            %>
                        </select>
                        <input type="hidden" name="txtEstado" value="${usuarioPorNit.estado}"> <!-- Agregamos el estado -->
                        <td>
                            <button type="submit" name="accion" value="agregar" class="btn btn-success">Agregar Usuario</button>
                        </td>
                    </form>
                </td>
            </tr>
        </table>
    </c:if>

    <c:if test="${not empty mensaje}">
        <div class="alert alert-warning" role="alert">
            ${mensaje}
        </div>
    </c:if>
</div>

<script src="js/bootstrap.bundle.min.js"></script>
</body>
</html>
