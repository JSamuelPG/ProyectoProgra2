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
            background-image: url('images/th (1).jpeg'); /* Ruta de la imagen */
            background-size: 50%; /* Hace que la imagen cubra todo el fondo */
            background-position: center; /* Centra la imagen */
            background-repeat: no-repeat; /* Evita que la imagen se repita */
            height: 100vh; /* Asegura que el fondo cubra toda la altura de la página */
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
            transition: background-color 0.3s;
        }

        .navbar a:hover {
            background-color: #7f8c8d; /* Color gris al pasar el ratón */
            color: white;
        }

        .navbar a.active {
            background-color: #3498db; /* Color azul para el Home */
            color: white;
        }

        /* Estilos del dropdown */
        .dropdown {
            position: relative;
            display: inline-block; /* Se mantiene en línea */
        }

        .dropdown-content {
            display: none; /* Oculto por defecto */
            position: absolute;
            background-color: #f9f9f9;
            min-width: 160px;
            box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
            z-index: 1;
        }

        .dropdown-content a {
            color: black;
            padding: 12px 16px;
            text-decoration: none;
            display: block;
            text-align: left;
        }

        .dropdown-content a:hover {
            background-color: #ddd;
        }

        .dropdown:hover .dropdown-content {
            display: block; /* Muestra el menú desplegable */
            margin-top: 0; /* Alinea el submenú justo debajo del botón */
        }

        .dropdown:hover .dropbtn {
            background-color: #7f8c8d; /* Cambia el color al pasar el ratón */
        }
    </style>
</head>
<body>

    <!-- Cinta de opciones -->
    <div class="navbar">
        <a class="active" href="#home">Inicio</a>
        <a href="Controlador?accion=listar">Lista de Usuarios</a>
        <a href="Controlador?accion=add">Agregar Usuario</a>

        <!-- Dropdown para Servicios -->
        <div class="dropdown">
            <a href="#" class="dropbtn">Servicios</a>
            <div class="dropdown-content">
                <a href="#analista">Analista</a>
                <a href="#servicioscrear">Servicios Crear</a>
                <a href="#serviciosproceso">Servicios Proceso</a>
            </div>
        </div>

        <a href="#home2">Reportes</a>
        <a href="Controlador?accion=index">Cerrar Sesion</a>
    </div>

    <!-- Contenido principal -->
    <div class="container">
        <h2>BIENVENIDO!</h2>
    </div>

</body>
</html>
