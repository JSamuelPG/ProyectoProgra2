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
                            if (entidadPorNit != null) {
                        %>

                        <%
                        } else if (mensajeProv != null) {
                        %>
                        <p style="color: red;"> <%= mensajeProv %></p>
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

                        Tipo de Solicitud: <br>
                        <select class="form-control" name="txtSoli" id="txtSoli" onchange="toggleFields()" >
                            <option value="MuestraParaAnalisis" <%= "MuestraparaAnalisis".equals(request.getParameter("txtSoli")) ? "selected" : ""%>>Muestra para análisis</option>
                            <option value="SolicitudSinMuestra" <%= "SolicitudsinMuestra".equals(request.getParameter("txtSoli")) ? "selected" : ""%>>Solicitud sin muestra</option>
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
                               value="<%= request.getParameter("nitSolicitante") != null ? request.getParameter("nitSolicitante") : ""%>"><br>
                        <input type="hidden" name="menu" value="solicit">
                        <button type="submit" name="accion" value="obtenSolici" class="btn btn-info">Buscar</button>

                        <%
                            Solicitantes obtenSol = (Solicitantes) request.getAttribute("obtenSol");
                            String mensajeSolicitante = (String) request.getAttribute("mensajeSolicitante");
                            if (obtenSol != null) {
                        %>

                        <%
                        } else if (mensajeSolicitante != null) {
                        %>
                         <p style="color: red;"><%= mensajeSolicitante %></p>
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
                        <select class="form-control" name="analista" id="analista">
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
                        <br>
                        <input type="hidden" name="menu" value="solicit">
                        <input type="submit" name="accion" value="Agregar" class="btn btn-primary">
                        <button type="submit" name="accion" value="generarpdf" class="btn btn-secondary">Generar PDF</button>
                    </div>
                </div>
            </form>             
        </div>
                                      
<!-- <script>
    function toggleFields() {
        const tipoSolicitud = document.getElementById('txtSoli').value;

        // Los campos que deseas controlar
        const noMuestraField = document.getElementById('txtNoMuestra');
        const descripcionField = document.getElementById('txtDescProd');

        // Habilita o inhabilita la escritura en los campos según el tipo de solicitud
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
</script> -->     


<script>
    // Función para alternar campos según el tipo de solicitud
    function toggleFields() {
        const tipoSolicitud = document.getElementById('txtSoli').value;
        const noMuestraField = document.getElementById('txtNoMuestra');
        const descripcionField = document.getElementById('txtDescProd');

        // Lógica para habilitar/deshabilitar campos según el tipo de solicitud
        const isAnalysis = tipoSolicitud === "MuestraParaAnalisis";
        const isWithoutSample = tipoSolicitud === "SolicitudSinMuestra";
        const isWithSampleNumber = tipoSolicitud === "ConNumerodeMuestra";

        noMuestraField.readOnly = isAnalysis || isWithoutSample;
        noMuestraField.value = isAnalysis || isWithoutSample ? "" : noMuestraField.value;
    }

    // Función para habilitar/deshabilitar el selector de analista
    function validarCampos() {
        const fields = ['nitEntidad', 'nitSolicitante', 'txtNodoc', 'txtDescProd'];
        const allFilled = fields.every(id => document.getElementById(id).value.trim() !== '');
        const analistaSelect = document.getElementById('analista');

        analistaSelect.disabled = !allFilled;
        if (!allFilled) analistaSelect.selectedIndex = 0;
    }

    // Función para validar el formulario antes de enviarlo
    function validarFormulario() {
        if (document.getElementById('analista').disabled) {
            alert('Debes llenar todos los campos obligatorios antes de seleccionar un analista.');
            return false;
        }
        return true;
    }

    // Ejecutar las funciones de inicialización al cargar la página
    window.onload = function() {
        toggleFields();
        validarCampos();
    };
</script>


    
    </body>
</html>