<%@page import="java.util.Iterator"%>
<%@page import="Modelo.SoliMuestra"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.SoliMuestraDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
        
    <body>
        <!-- Cinta de opciones -->
        <div class="navbar">
            <a href="Controlador?accion=init">Inicio</a>
            <a class="active" href="#home">Lista de Usuarios</a>
            <a href="Controlador?accion=add">Agregar Usuarios</a>
            <a href="#home1">Servicios</a>
            <a href="#home2">Reportes</a>
            <a href="Controlador?accion=index">Cerrar Sesion</a>
        </div>
        <div class="container">
            <h1>Registro de Solicitudes y Muestras</h1>
            <br>
            <br>
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th class="text-center">Fecha Solicitado</th>
                        <th class="text-center">Tipo de Documento</th>
                        <th class="text-center">No de documento</th>
                        <th class="text-center">Nit Proveedor</th>
                        <th class="text-center">Nombre del Proveedor</th>
                        <th class="text-center">Correo Proveedor</th>
                        <th class="text-center">COrreo Solicitante</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Telefono</th>
                        <th class="text-center">Nit Solicitante</th>
                        <th class="text-center">Nombre Solicitante</th>
                        <th class="text-center">No de Muestra</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        SoliMuestraDAO dao = new SoliMuestraDAO();
                        List<SoliMuestra> list = dao.listarR();
                        Iterator<SoliMuestra> iter = list.iterator();
                        SoliMuestra sm = null;
                        while (iter.hasNext()) {
                            sm = iter.next();
                    %>
                    <tr>
                        <td class="text-center"><%= sm.getFechaSolicitud()%></td>
                        <td class="text-center"><%= sm.getTipodeDocumento()%></td>
                        <td class="text-center"><%= sm.getNoDedocumento()%></td>
                        <td class="text-center"><%= sm.getNitProveedor()%></td>
                        <td class="text-center"><%= sm.getNombreProveedor()%></td>
                        <td class="text-center"><%= sm.getCorreoProveedor() %></td>
                        <td class="text-center"><%= sm.getCorreoSolicitante() %></td>
                        <td class="text-center"><%= sm.getDireccionProveedor() %></td>
                        <td class="text-center"><%= sm.getTelefonoProveedor() %></td>
                        <td class="text-center"><%= sm.getNitSolicitante() %></td>
                        <td class="text-center"><%= sm.getNombreSolicitante() %></td>
                        <td class="text-center"><%= sm.getNoMuestra() %></td>
                        <td class="text-center">
                            <a class="btn btn-warning" href="Controlador?accion=editar&idusua=<%= sm.getIdSolicitud()%>">Editar</a>
                            <a class="btn btn-danger" href="Controlador?accion=eliminar&id=<%= sm.getIdSolicitud() %>">Remove</a>
                        </td>
                    </tr>
                    <% } %>
                </tbody>
            </table>

        </div>
    </body>
</html>
