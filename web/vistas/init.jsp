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
            background: #000000;
            border: none;
            transition: background-color 0.3s;
            cursor: pointer;
            display: inline-block;
        }

        .navbar a:hover, .dropbtn:hover {
            background-color: #e38d13;
        }

        .dropdown {
            display: inline-block;
            position: relative;
        }

        .dropdown-content {
            display: none;
            position: absolute;
            background-color: #000;
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
            background-color: #555;
        }

        .dropdown:hover .dropdown-content {
            display: block;
        }
    </style>
</head>
<body>

    <div class="navbar">
        <div class="dropdown">
            <button class="dropbtn">Menú</button>
            <div class="dropdown-content">
                <a href="Controlador?accion=listar">Lista de Usuarios</a>
                <a href="Controlador?accion=add">Agregar Usuario</a>
                <a href="Controlador?accion=listarr">Lista de Solicitud y Muestra</a>
                <a href="Controlador?menu=listaent&accion=entidades">Entidades</a>

                <a href="Controlador?accion=reportes">Reportes</a>
            </div>
        </div>
        <a href="Controlador?accion=index">Cerrar Sesión</a>
    </div>

    <div class="container">
        <h2>BIENVENIDO!</h2>
    </div>

</body>
</html>
