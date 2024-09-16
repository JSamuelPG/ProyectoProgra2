<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ModeloDAO.SoliMuestraDAO"%>
<%@page import="java.util.Iterator"%>
<%@page import="Modelo.Users"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.PersonaDAO"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <title>JSP Page</title>
    <style>
body {
    font-family: Arial, sans-serif;
    background: url('images/th (1).jpeg') center no-repeat;
    background-size: 40%;
    height: 100vh;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}

.navbar {
    overflow: hidden;
    background: #000000;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
    margin-bottom: 40px;
    position: relative;
}

.navbar a, .dropdown button {
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 20px;
    text-decoration: none;
    font-size: 18px;
    background: #000000;
    border: none;
    transition: background-color 0.3s;
}

.navbar a:hover, .dropdown button:hover {
    background-color: #e38d13;
}

.navbar a.active {
    background-color: #3498db;
}

.dropdown {
    position: relative;
}

.dropdown button {
    cursor: pointer;
}

.dropdown-content {
    display: none;
    position: absolute;
    background-color: #000;
    min-width: 160px;
    box-shadow: 0px 8px 16px rgba(0, 0, 0, 0.2);
    z-index: 1;
    top: 100%;
    left: 0;
    margin-top: 10px;
}

.dropdown-content a {
    color: white;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
}

.dropdown-content a:hover {
    background-color: #555;
}

.dropdown:hover .dropdown-content {
    display: block;
}

    </style>
</head>
<body>

    <!-- Cinta de opciones -->
    <div class="navbar">
        <a class="active" href="#home">Inicio</a>
        <a href="Controlador?accion=listar">Lista de Usuarios</a>
        <a href="Controlador?accion=add">Agregar Usuario</a>
        <a href="Controlador?accion=listarR">Lista de Solicitud y Muestra</a>
        <a href="#home2">Reportes</a>
        <a href="Controlador?accion=index">Cerrar Sesion</a>
    </div>

    <!-- Contenido principal -->
    <div class="container">
        <h2>BIENVENIDO!</h2>
    </div>

</body>
</html>
