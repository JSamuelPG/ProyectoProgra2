<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Roles"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<%@page import="java.util.List"%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>JSP Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        /* Estilos para los botones */
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
            background: #000000; /* Color negro */
            border: none;
            transition: background-color 0.3s;
            cursor: pointer;
            display: inline-block;
        }

        /* Hover en los botones */
        .navbar a:hover, .dropbtn:hover {
            background-color: #e38d13; /* Color al pasar el ratón */
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

        <a href="Controlador?accion=index">Cerrar Sesión</a>
    </div>

    <!-- Contenido principal -->
    <div class="container">
        <div class="col-lg-6">
            <h1>Agregar Usuario</h1>
            <form action="Controlador">
                Primer Nombre: <br>
                <input class="form-control" type="text" name="txtNom1"><br>
                Segundo Nombre: <br>
                <input class="form-control" type="text" name="txtNom2"><br>
                Primer Apellido: <br>
                <input class="form-control" type="text" name="txtAp1"><br>
                Segundo Apellido: <br>
                <input class="form-control" type="text" name="txtAp2"><br>
                Contraseña: <br>
                <input class="form-control" type="text" name="txtCont"><br>
                Nit: <br>
                <input class="form-control" type="text" name="txtNit"><br>
                Puesto: <br>
                <input class="form-control" type="text" name="txtPuesto"><br>
                Rol: <br>
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
                <br>
                Estado: <br>
                <select class="form-control" name="txtEstado" id="txtEstado">
                    <option value="Activo">Activo</option>
                    <option value="Inactivo">Inactivo</option>
                </select>
                <br>
                
                
                <input type="hidden" name="menu" value="usuarios">
                <input class="btn btn-primary" type="submit" name="accion" value="agregar">
            </form>
        </div>
    </div>
</body>
</html>
