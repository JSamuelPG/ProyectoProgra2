<%@page import="Modelo.Users"%>
<%@page import="Modelo.Roles"%>
<%@page import="Modelo.SoliMuestra"%>
<%@page import="ModeloDAO.PersonaDAO"%>
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
        .dropdown:hover > .dropdown-content {
            display: block;
        }
        .dropdown-content .dropdown {
            position: relative;
        }
        .dropdown-content .dropdown-content {
            display: none;
            top: 0;
            left: 100%;
            margin-left: 1px;
        }
        .dropdown-content .dropdown:hover .dropdown-content {
            display: block;
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

<h1>Solicitudes del Usuario</h1>
<a class="btn btn-info" href="Controlador?menu=usuarios&accion=listar">Regresar</a>


         <form action="Controlador" method="post">
            <table border="1" class="table table-bordered">
                <tr>
                    <th>ID Solicitud</th>
                    <th>No Documento</th>
                    <th>Fecha Solicitud</th>
                    <th>Estado</th>
                    <th>Seleccionar Analista</th>
                </tr>
                <%
                    List<SoliMuestra> solicitudes = (List<SoliMuestra>) request.getAttribute("solicitudes");
                    if (solicitudes != null && !solicitudes.isEmpty()) {
                        for (int i = 0; i < solicitudes.size(); i++) {
                            SoliMuestra solicitud = solicitudes.get(i);
                %>
                <tr>
                    <td><%= solicitud.getIdSolicitud() %></td>
                    <td><%= solicitud.getNoDedocumento() %></td>
                    <td><%= solicitud.getFechaSolicitud() != null ? solicitud.getFechaSolicitud().toString() : "N/A" %></td>
                    <td><%= solicitud.getEstado() %></td>
                    <td>
                        <select class="form-control" name="analista_<%= i %>">
                            <%
                                List<Users> listaUsuarios = (List<Users>) request.getAttribute("listaUsuarios");
                                if (listaUsuarios != null) {
                                    for (Users usuario : listaUsuarios) {
                            %>
                                <option value="<%= usuario.getIdusuario() %>">
                                    <%= usuario.getPrimerNombre() %> - <%= usuario.getCorreo() %>
                                </option>
                            <%
                                    }
                                } else {
                            %>
                                <option value="" disabled>No hay analistas disponibles</option>
                            <%
                                }
                            %>
                        </select>

                        <input type="hidden" name="idSolicitud_<%= i %>" value="<%= solicitud.getIdSolicitud() %>"/>
                    </td>
                </tr>
                <%
                        }
                %>
                <input type="hidden" name="menu" value="solicit"/>
                <input type="hidden" name="totalSolicitudes" value="<%= solicitudes.size() %>"/>
                <tr>
                    <td colspan="5">
                        <button type="submit" name="accion" value="actualizarR" class="btn btn-primary">Actualizar</button>
                    </td>
                </tr>
                <%
                    } else {
                %>
                <tr>
                    <td colspan="5">No hay solicitudes para mostrar.</td>
                </tr>
                <%
                    }
                %>
            </table>
        </form>
</body>
</html>
