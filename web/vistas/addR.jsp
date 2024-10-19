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
                            <a href="Controlador?menu=reasignar&accion=listReasignar">Reasignacion de Solicitudes</a>
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
            <h2>Agregar Solicitud</h2>
            <a class="btn btn-secondary" href="Controlador?menu=solicit&accion=listarr">Regresar</a>
            <form action="Controlador" method="get">
                <div class="row">
                    <!-- Primera columna -->
                    <div class="col-lg-5">
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
                            String mensajeProv = (String) request.getAttribute("mensajeProveedor");

                            // Inicializar los valores a vacíos si no se encuentra la entidad o si hay un mensaje
                            String nombreProveedor = "";
                            String tipoEntidad = "";
                            String correoProveedor = "";
                            String direccionProveedor = "";
                            String telefonoProveedor = "";

                            if (entidadPorNit != null) {
                                // Si se encuentra la entidad, asignar los valores
                                nombreProveedor = entidadPorNit.entidadNombre;
                                tipoEntidad = entidadPorNit.entidadTipo;
                                correoProveedor = entidadPorNit.entidadCorreo;
                                direccionProveedor = entidadPorNit.entidadDireccion;
                                telefonoProveedor = entidadPorNit.entidadTelefono;
                            } else if (mensajeProv != null) {
                                // Si hay un mensaje, dejar los campos vacíos
                                nombreProveedor = "";
                                tipoEntidad = "";
                                correoProveedor = "";
                                direccionProveedor = "";
                                telefonoProveedor = "";
                            }
                        %>
                        <%
                            // Mostrar mensaje de error si corresponde
                            if (mensajeProv != null) {
                        %>
                            <p style="color: red;"> <%= mensajeProv %></p>
                        <%
                            }
                        %>

                        Nombre Proveedor: <br>
                        <input class="form-control" type="text" name="txtNomProv" id="txtNomProv" 
                               value="<%= nombreProveedor != null ? nombreProveedor : (request.getParameter("txtNomProv") != null ? request.getParameter("txtNomProv") : "") %>" readonly /><br>

                        <!-- Tipo de Entidad -->
                        Tipo de Entidad: <br>
                        <input class="form-control" type="text" name="txtEnti" id="txtEnti" 
                               value="<%= tipoEntidad != null ? tipoEntidad : (request.getParameter("txtEnti") != null ? request.getParameter("txtEnti") : "") %>" readonly/><br>

                        <!-- Correo Proveedor -->
                        Correo Proveedor: <br>
                        <input class="form-control" type="text" name="txtCorProv" id="txtCorProv" 
                               value="<%= correoProveedor != null ? correoProveedor : (request.getParameter("txtCorProv") != null ? request.getParameter("txtCorProv") : "") %>" readonly/><br>

                        <!-- Dirección Proveedor -->
                        Dirección Proveedor: <br>
                        <input class="form-control" type="text" name="txtDiProv" id="txtDiProv" 
                               value="<%= direccionProveedor != null ? direccionProveedor : (request.getParameter("txtDiProv") != null ? request.getParameter("txtDiProv") : "") %>" readonly/><br>

                        <!-- Teléfono Proveedor -->
                        Teléfono Proveedor: <br>
                        <input class="form-control" type="text" name="txtTelProv" id="txtTelProv" 
                               value="<%= telefonoProveedor != null ? telefonoProveedor : (request.getParameter("txtTelProv") != null ? request.getParameter("txtTelProv") : "") %>" readonly/><br>

                        Tipo de Solicitud: <br>
                        <select class="form-control" name="txtSoli" id="txtSoli" onchange="toggleFields()" >
                            <option value="MuestraParaAnalisis" <%= "MuestraParaAnalisis".equals(request.getParameter("txtSoli")) ? "selected" : ""%>>Muestra para análisis</option>
                            <option value="SolicitudSinMuestra" <%= "SolicitudSinMuestra".equals(request.getParameter("txtSoli")) ? "selected" : ""%>>Solicitud sin muestra</option>
                            <option value="ConNumerodeMuestra" <%= "ConNumerodeMuestra".equals(request.getParameter("txtSoli")) ? "selected" : ""%>>Con número de muestra</option>
                        </select>
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
                               value="<%= request.getParameter("nitSolicitante") != null ? request.getParameter("nitSolicitante") : "" %>"><br>
                        <input type="hidden" name="menu" value="solicit">
                        <button type="submit" name="accion" value="obtenSolici" class="btn btn-info">Buscar</button>

                        <%
                            Solicitantes obtenSol = (Solicitantes) request.getAttribute("obtenSol");
                            String mensajeSolicitante = (String) request.getAttribute("mensajeSolicitante");

                            // Inicializar los valores a vacíos si no se encuentra el solicitante o si hay un mensaje
                            String nombreSolicitante = "";
                            String correoSolicitante = "";

                            if (obtenSol != null) {
                                // Si se encuentra el solicitante, asignar los valores
                                nombreSolicitante = obtenSol.solNombre;
                                correoSolicitante = obtenSol.solCorreo;
                            } else if (mensajeSolicitante != null) {
                                // Si hay un mensaje, dejar los campos vacíos
                                nombreSolicitante = "";
                                correoSolicitante = "";
                            }
                        %>

                        <%
                            // Mostrar mensaje de error si corresponde
                            if (mensajeSolicitante != null) {
                        %>
                            <p style="color: red;"><%= mensajeSolicitante %></p>
                        <%
                            }
                        %>

                       <!-- Nombre Solicitante -->
                        Nombre Solicitante: <br>
                        <input class="form-control" type="text" name="txtNomSol" id="txtNomSol" 
                               value="<%= (nombreSolicitante != null ? nombreSolicitante : (request.getAttribute("txtNomSol") != null ? request.getAttribute("txtNomSol") : "")) %>" readonly/><br>

                        <!-- Correo Solicitante -->
                        Correo Solicitante: <br>
                        <input class="form-control" type="text" name="txtCorSol" id="txtCorSol" 
                               value="<%= (correoSolicitante != null ? correoSolicitante : (request.getAttribute("txtCorSol") != null ? request.getAttribute("txtCorSol") : "")) %>" /><br>

                        <!-- No de Muestra -->
                        No de Muestra: <br>
                        <input class="form-control" type="text" name="txtNoMuestra" id="txtNoMuestra"  /><br>
                        <c:if test="${not empty error}">
                            <div style="color: red;">${error}</div> <!-- Mostrar el mensaje de error en rojo -->
                        </c:if>
                        
                        <!--Asignar el estado de la solicitud-->
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
                        <select class="form-control" name="analista" id="analista" onchange="this.form.nombreAnalista.value=this.options[this.selectedIndex].text.split(' - ')[0];">
                            <% if (listaUsuarios != null) {
                                    for (Users usuario : listaUsuarios) {
                            %>
                            <option value="<%= usuario.getIdusuario()%>">
                                <%= usuario.getPrimerNombre() %> - <%= usuario.getCorreo() %> 
                            </option>
                            <%
                                    }
                                }
                            %>
  
                        </select>
                            <input type="hidden" name="nombreAnalista" id="nombreAnalista" value="">
                        <br>
                        <input type="hidden" name="menu" value="solicit">
                        <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                        <button type="submit" name="accion" value="generarpdf" class="btn btn-secondary">Generar PDF</button>
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

            // habilita o inhabilita la escritura en los campos según el tipo de solicitud
            if (tipoSolicitud === "MuestraParaAnalisis") {
                noMuestraField.readOnly = true; 
                noMuestraField.value = "";
                descripcionField.readOnly = false; 
            } else if (tipoSolicitud === "SolicitudSinMuestra") {
                noMuestraField.readOnly = true; 
                descripcionField.readOnly = false; 
                noMuestraField.value = ""; 
                descripcionField.value = ""; 
            } else if (tipoSolicitud === "ConNumerodeMuestra") {
                noMuestraField.readOnly = false; 
                descripcionField.readOnly = false; 
            }
        }
        // Ejecutar la función para configurar los campos al cargar la página
        window.onload = toggleFields;
    </script>    
 </body>
</html>