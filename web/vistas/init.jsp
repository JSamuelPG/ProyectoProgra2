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
                            <a href="Controlador?menu=visualizarSolicitud&accion=visualizarSolicitudes">Visualización de Solicitudes</a>
                            <a href="Controlador?menu=Reasignar&accion=listReasignar">Reasignacion de Solicitudes</a>
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
        <h2>BIENVENIDO!</h2>
    </div>

</body>
</html>
