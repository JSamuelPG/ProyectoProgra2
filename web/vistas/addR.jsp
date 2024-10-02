<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                <a href="Controlador?accion=init">Inicio</a>
                <a href="Controlador?accion=listar">Lista de Usuarios</a>
                <a href="Controlador?accion=listarr">Lista de Solicitud y Muestra</a>
                <a class="active" href="#home">Agregar Solicitud</a>
                <a href="#home2">Reportes</a>
                <a href="Controlador?accion=listarr">Regresar</a>
            </div>
        </div>

        <!-- Opción de Cerrar Sesión que siempre estará visible -->
        <a href="Controlador?accion=index">Cerrar Sesión</a>
    </div>

    <!-- Contenido principal -->
    <div class="container">
        <div class="col-lg-6">
            <h1>Agregar Solicitud</h1>
            <form action="Controlador">
                Tipo de Solicitud: <br>
                <select class="form-control" name="textSoli" id="txtSoli"><br>
                    <option value="MuestraparaAnalisis">Muestra para análisis</option>
                    <option value="SolicitudsinMuestra">Solicitud sin muestra</option>
                    <option value="ConNumerodeMuestra">Con número de muestra</option>
                </select>
                <br>
                Tipo de Entidad: <br>
                <select class="form-control" name="txtEnti" id="textEnti"><br>
                    <option value="EntidadPrivada">Entidad privada</option>
                    <option value="EntidadPublica">Entidad pública</option>
                </select>
                <br>
                Fecha Solicitud: <br>
                <input class="form-control" type="text" name="txtFecha"><br>
                Tipo de Documento: <br>
                <input class="form-control" type="text" name="txtDoc"><br>
                No de Documento: <br>
                <input class="form-control" type="text" name="txtNodoc"><br>
                Nit Proveedor: <br>
                <input class="form-control" type="text" name="txtNitProv"><br>
                Nombre Proveedor: <br>
                <input class="form-control" type="text" name="txtNomProv"><br>
                Correo Proveedor: <br>
                <input class="form-control" type="text" name="txtCorProv"><br>
                Correo Solicitante: <br>
                <input class="form-control" type="text" name="txtCorSol"><br>
                Direccion Proveedor: <br>
                <input class="form-control" type="text" name="txtDiProv"><br>
                Teléfono Proveedor: <br>
                <input class="form-control" type="text" name="txtTelProv"><br>
                Nit Solicitante: <br>
                <input class="form-control" type="text" name="txtNitSol"><br>
                Nombre Solicitante: <br>
                <input class="form-control" type="text" name="txtNomSol"><br>
                No de Muestra: <br>
                <input class="form-control" type="text" name="txtNoMuestra"><br>
                Descripción de producto: <br>
                <input class="form-control" type="text" name="txtDescProd"><br>

                <br>
                <input class="btn btn-primary" type="submit" name="accion" value="Agregar">
            </form>
        </div>
    </div>
</body>
</html>
