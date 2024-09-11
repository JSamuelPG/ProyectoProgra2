<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            /* Estilos para la cinta de opciones */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }
            .navbar {
                overflow: hidden;
                background: #000000; /* Color negro */
                box-shadow: 0 4px 6px rgba(0, 0, 0, 0.3);
            }
            .navbar a {
                float: left;
                display: block;
                color: white;
                text-align: center;
                padding: 14px 20px;
                text-decoration: none;
                font-size: 18px;
                position: relative;
                transition: all 0.3s ease;
            }
            .navbar a:hover {
                background-color: #7f8c8d; /* Color gris al pasar el ratón */
                color: white;
            }
            .navbar a.active {
                background-color: #3498db; /* Color azul para el Home */
                color: white;
            }
        </style>
    </head>
    <body>
        <!-- Cinta de opciones -->
        <div class="navbar">
            <a class="active" href="#home">Menú</a>
            <a href="#services">Servicios</a>
            <a href="#about">SCM</a>
            <a href="#contact">Bandeja Analista</a>
        </div>

        <h1>Hello World!</h1>

        <p>Bienvenido a tu página JSP con una barra de opciones personalizada.</p>
    </body>
</html>
