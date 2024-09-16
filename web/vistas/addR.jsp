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
            <a href="Controlador?accion=init">Inicio</a>
            <a href="Controlador?accion=listar">Lista de Usuarios</a>
            <a class="active" href="#home">Agregar Usuario</a>
            <a href="#home1">Servicios</a>
            <a href="#home2">Reportes</a>
            <a href="Controlador?accion=listar">Regresar</a>
            <a href="Controlador?accion=index">Cerrar Sesion</a>
        </div>

        <div class="container">
            <div class="col-lg-6">
                <h1>Agregar Persona</h1>
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
                        <option value="RegistroMuestras">Registro Muestras</option>
                        <option value="AnalistadeLaboratorio">Analista de Laboratorio</option>
                        <option value="AlmacenamientodeMuestra">Almacenamiento de Muestra</option>
                        <option value="SupervisorLaboratorio">Supervisor de Laboratorio</option>
                        <option value="JefeUnidadLaboratorio">Jefe Unidad Laboratorio</option>
                        <option value="LaboratorioExterno">Laboratorio Externo</option>
                        <option value="Reportes">Reportes</option>
                        <option value="VisualizacionDocumentos">Visualización de Documentos</option>
                        <option value="Administrador">Administrador</option>
                    </select>
                    <br>
                    Estado: <br>
                    <select class="form-control" name="txtEstado" id="txtEstado">
                        <option value="Activo">Activo</option>
                        <option value="Inactivo">Inactivo</option>
                    </select>
                    <br>
                    <input class="btn btn-primary" type="submit" name="accion" value="Agregar">
                    
                </form>
            </div>
        </div>
    </body>
</html>
