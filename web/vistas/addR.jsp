<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Modelo.Entidad"%>
<%@page import="Modelo.Solicitantes"%>
<%@page import="Modelo.Users"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="javax.servlet.ServletException" %>
<%@ page import="java.io.IOException" %>
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
                    <a href="Controlador?accion=init">Inicio</a>
                    <a href="Controlador?accion=listar">Lista de Usuarios</a>
                    <a href="Controlador?accion=listarr">Lista de Solicitud y Muestra</a>
                    <a class="active" href="#home">Agregar Solicitud</a>
                    <a href="#home2">Reportes</a>
                    <a href="Controlador?accion=listarr">Regresar</a>
                </div>
            </div>

            <a href="Controlador?accion=index">Cerrar Sesión</a>
        </div>

        <div class="container">
            <h2>Agregar Solicitud</h2>
            <form action="Controlador" method="get">
                <div class="row">
                    <!-- Primera columna -->
                    <div class="col-lg-5">
                        Tipo de Solicitud: <br>
                        <select class="form-control" name="txtSoli" id="txtSoli" onchange="toggleFields()" >
                            <option value="MuestraparaAnalisis" <%= "MuestraparaAnalisis".equals(request.getParameter("txtSoli")) ? "selected" : ""%>>Muestra para análisis</option>
                            <option value="SolicitudsinMuestra" <%= "SolicitudsinMuestra".equals(request.getParameter("txtSoli")) ? "selected" : ""%>>Solicitud sin muestra</option>
                            <option value="ConNumerodeMuestra" <%= "ConNumerodeMuestra".equals(request.getParameter("txtSoli")) ? "selected" : ""%>>Con número de muestra</option>
                        </select>
                        <br>
                        Tipo de Documento:<br>
                        <select class="form-control" name="txtDoc" id="txtDoc">
                            <option value="numerodeexpediente" <%= "numerodeexpediente".equals(request.getParameter("txtDoc")) ? "selected" : ""%>>No. de Expediente</option>
                            <option value="hojadetramite" <%= "hojadetramite".equals(request.getParameter("txtDoc")) ? "selected" : ""%>>Hoja de trámite</option>
                            <option value="memorandum" <%= "memorandum".equals(request.getParameter("txtDoc")) ? "selected" : ""%>>Memorandum</option>
                        </select>
                        <br>

                        Nit Proveedor: <br>
                        <input class="form-control" type="text" name="nitEntidad" id="nitEntidad" 
                               value="<%= request.getParameter("nitEntidad") != null ? request.getParameter("nitEntidad") : ""%>"><br>
                        <input type="hidden" name="menu" value="solicit">
                        <button type="submit" name="accion" value="obtenprov" class="btn btn-info">Buscar</button>

                        <%
                            Entidad entidadPorNit = (Entidad) request.getAttribute("entidadPorNit");
                            String mensaje = (String) request.getAttribute("mensaje");
                            if (entidadPorNit != null) {
                        %>

                        <%
                        } else if (mensaje != null) {
                        %>
                        <p><%= mensaje%></p>
                        <%
                            }
                        %>
                        Nombre Proveedor: <br>
                        <input class="form-control" type="text" name="txtNomProv" id="txtNomProv" 
                               value="${not empty entidadPorNit ? entidadPorNit.entidadNombre : param.txtNomProv != null ? param.txtNomProv : ''}"readonly /><br>

                        <!-- Tipo de Entidad -->
                        Tipo de Entidad: <br>
                        <input class="form-control" type="text" name="txtEnti" id="txtEnti" 
                               value="${not empty entidadPorNit ? entidadPorNit.entidadTipo : param.txtEnti != null ? param.txtEnti : ''}"  readonly/><br>

                        <!-- Correo Proveedor -->
                        Correo Proveedor: <br>
                        <input class="form-control" type="text" name="txtCorProv" id="txtCorProv" 
                               value="${not empty entidadPorNit ? entidadPorNit.entidadCorreo : param.txtCorProv != null ? param.txtCorProv : ''}" readonly/><br>

                        <!-- Dirección Proveedor -->
                        Dirección Proveedor: <br>
                        <input class="form-control" type="text" name="txtDiProv" id="txtDiProv" 
                               value="${not empty entidadPorNit ? entidadPorNit.entidadDireccion : param.txtDiProv != null ? param.txtDiProv : ''}" readonly/><br>

                        <!-- Teléfono Proveedor -->
                        Teléfono Proveedor: <br>
                        <input class="form-control" type="text" name="txtTelProv" id="txtTelProv" 
                               value="${not empty entidadPorNit ? entidadPorNit.entidadTelefono : param.txtTelProv != null ? param.txtTelProv : ''}" readonly/><br>

                    </div>

                    <!-- Segunda columna -->
                    <div class="col-lg-5">

                        <%
                            // Obtener la fecha actual
                            SimpleDateFormat fecha = new SimpleDateFormat("yyyy-MM-dd");
                            String formato = fecha.format(new Date());
                        %>

                        Fecha de Solicitud:<br>
                        <input class="form-control" type="date" name="txtFecha" id="txtFecha" 
                               value="<%= request.getParameter("txtFecha") != null ? request.getParameter("txtFecha") : formato%>" readonly><br>

                        No de Documento:<br>
                        <input class="form-control" type="text" name="txtNodoc" id="txtNodoc" 
                               value="<%= request.getParameter("txtNodoc") != null ? request.getParameter("txtNodoc") : ""%>"><br>

                        Nit Solicitante: <br>
                        <input class="form-control" type="text" name="nitSolicitante" id="nitSolicitante" 
                               value="<%= request.getParameter("nitSolicitante") != null ? request.getParameter("nitSolicitante") : ""%>"><br>
                        <input type="hidden" name="menu" value="solicit">
                        <button type="submit" name="accion" value="obtenSolici" class="btn btn-info">Buscar</button>

                        <%
                            Solicitantes obtenSol = (Solicitantes) request.getAttribute("obtenSol");
                            if (obtenSol != null) {
                        %>

                        <%
                        } else if (mensaje != null) {
                        %>
                        <p><%= mensaje%></p>
                        <%
                            }
                        %>

                        <!-- Nombre Solicitante -->
                        Nombre Solicitante: <br>
                        <input class="form-control" type="text" name="txtNomSol" id="txtNomSol" 
                               value="${not empty obtenSol ? obtenSol.solNombre : param.txtNomSol != null ? param.txtNomSol : ''}" readonly/><br>

                        <!-- Correo Solicitante -->
                        Correo Solicitante: <br>
                        <input class="form-control" type="text" name="txtCorSol" id="txtCorSol" 
                               value="${not empty obtenSol ? obtenSol.solCorreo : param.txtCorSol != null ? param.txtCorSol : ''}" /><br>

                        <!-- No de Muestra -->
                        No de Muestra: <br>
                        <input class="form-control" type="text" name="txtNoMuestra" id="txtNoMuestra"  /><br>
                        <c:if test="${not empty error}">
                            <div style="color: red;">${error}</div> <!-- Mostrar el mensaje de error en rojo -->
                        </c:if>
                        
                        
                        
                        
                        <input class="form-control" type="hidden" name="estado" id="estado" value="AsignadaAnalistaLaboratorio" readonly />

                        <!-- Descripción de Producto -->
                        Descripción de Producto: <br>
                        <input class="form-control" type="text" name="txtDescProd" id="txtDescProd" 
                               value="${param.txtDescProd != null ? param.txtDescProd : ''}" /><br>

                        <%
                            // Obtener la lista de usuarios con rol 2 (Analista de Laboratorio)
                            List<Users> listaUsuarios = (List<Users>) request.getAttribute("listaUsuarios");
                        %>

                        <!-- Campo para seleccionar al analista (usuario con rol 2) -->
                        <label for="analista">Seleccionar Analista:</label><br>
                        <select class="form-control" name="analista" id="analista">
                            <option value="">-- Seleccione un analista --</option>
                            <% if (listaUsuarios != null) {
                                    for (Users usuario : listaUsuarios) {
                            %>
                            <option value="<%= usuario.getIdusuario()%>">
                                <%= usuario.getPrimerNombre() %> 
                            </option>
                            <%
                                    }
                                }
                            %>
                            
                          


                            
                            
                        </select>
                        <br>

                        <input type="hidden" name="menu" value="solicit">
                        <input type="submit" name="accion" value="agregarr" class="btn btn-primary">
                    </div>
                </div>
            </form>             
        </div>
            
                                  
<script>
    function toggleFields() {
        const tipoSolicitud = document.getElementById('txtSoli').value;

        // Los campos que deseas controlar
        const noMuestraField = document.getElementById('txtNoMuestra');
        const descripcionField = document.getElementById('txtDescProd');

        // Habilita o inhabilita la escritura en los campos según el tipo de solicitud
        if (tipoSolicitud === "MuestraparaAnalisis") {
            noMuestraField.readOnly = true; // Inhabilitar campo
            descripcionField.readOnly = false; // Habilitar campo
        } else if (tipoSolicitud === "SolicitudsinMuestra") {
            noMuestraField.readOnly = true; // Inhabilitar campo
            descripcionField.readOnly = true; // Inhabilitar campo
            noMuestraField.value = ""; // Limpiar el campo si está inhabilitado
            descripcionField.value = ""; // Limpiar el campo si está inhabilitado
        } else if (tipoSolicitud === "ConNumerodeMuestra") {
            noMuestraField.readOnly = false; // Habilitar campo
            descripcionField.readOnly = false; // Habilitar campo
        } else {
            // Opcional: manejar cualquier otro caso
            noMuestraField.readOnly = false; // Habilitar por defecto
            descripcionField.readOnly = true; // Inhabilitar por defecto
        }
    }

    // Llama a la función al cargar la página para ajustar el estado inicial
    window.onload = function() {
        toggleFields();
    };
</script>


                        
    </body>
</html>