package Controlador;



import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Calendar;
import Config.Conexion;
import Modelo.*;
import ModeloDAO.*;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfPTable;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Controlador extends HttpServlet {

    String login2 = "index.jsp";
    String init = "vistas/init.jsp";
    String listar = "vistas/listar.jsp";
    String add = "vistas/add.jsp";
    String edit = "vistas/edit.jsp";
    String listarr = "vistas/listarR.jsp";
    String addr = "vistas/addR.jsp";
    String editr = "vistas/editR.jsp";
    String entid = "vistas/entidades.jsp";
    String addenti = "vistas/addEntidad.jsp";
    String listReasignar = "vistas/reasignarSol.jsp";
    
    SoliMuestra sm = new SoliMuestra();
    Users u = new Users();
    PersonaDAO dao = new PersonaDAO();
    SoliMuestraDAO sdao = new SoliMuestraDAO();
    EntidadDAO edao = new EntidadDAO();
    ReasignarDAO rdao = new ReasignarDAO();
    int idsolicitud;
    int id;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "";
        }
        String menu = request.getParameter("menu");
        //////ACA METER CADA METODO 
        if (menu != null && menu.equals("inicio")) {

            switch (accion) {
                case "authenticate":
                    String login = request.getParameter("login");
                    String contrasenia = request.getParameter("contrasenia");

                    Conexion conexion = new Conexion();
                    Connection con = conexion.getConnection();

                    boolean isValid = conexion.validateUser(login, contrasenia);

                    if (isValid) {
                        // Obtener el ID y el nombre del usuario aquí
                        int idUsuario = conexion.getUserId(login, contrasenia); // Método que debes implementar para obtener el ID del usuario
                        String nombreUsuario = conexion.getUserName(login, contrasenia); // Método que debes implementar para obtener el nombre del usuario
                        int idRol = conexion.getUserRole(login, contrasenia); // Método para obtener el ID del rol

                        // Guardar el ID y el nombre del usuario en la sesión
                        HttpSession session = request.getSession();
                        session.setAttribute("usuarioLogueadoId", idUsuario);
                        session.setAttribute("usuarioLogueadoNombre", nombreUsuario); // Guardar el nombre del usuario
                        switch (idRol) {
                            case 1:
                                acceso = listarr;
                                break;
                            case 2:
                                acceso = "";
                                break;
                            case 3:
                                acceso = "supervisor.jsp";
                                break;
                            case 4:
                                acceso = "admin.jsp";
                                break;
                            case 5:
                                acceso = "reportes.jsp";
                                break;
                            case 6:
                                acceso = "reportes.jsp";
                                break;
                            case 7:
                                acceso = "reportes.jsp";
                                break;
                            case 8:
                                acceso = "reportes.jsp";
                                break;
                            case 9:
                                acceso = init;
                                break;
                        }
                    } else {
                        request.setAttribute("errorMessage", "usuario o contraseña inválido");
                        acceso = login2;
                    }
                    break;
                case "init":
                    acceso = init;
                    break;
            }
        }

        if (menu != null && menu.equals("listaent")) {
            switch (accion) {

                case "entidades":
                    acceso = entid;
                    break;
                case "obtenerpornit":
                    String nitEntidad = request.getParameter("nitEntidad").trim();
                    Entidad entidad = edao.obtenerPorNit(nitEntidad); // Llama al método obtenerPorNit

                    request.setAttribute("entidadPorNit", entidad);

                    // Mensaje si no se encontraron resultados
                    if (entidad == null) {
                        request.setAttribute("mensaje", "No se encontró entidad con NIT " + nitEntidad);
                    }

                    // Redirige a la página de resultados de búsqueda
                    RequestDispatcher dispatcher = request.getRequestDispatcher(addenti);
                    dispatcher.forward(request, response);
                    return;
                case "agregarEnt":
                    acceso = addenti;
                    break;

                case "agregarEntidad":
                    String nite = request.getParameter("nit");

                    // Verificar si ya existe una entidad con el NIT que ingrese
                    Entidad entidadExistente = edao.existeNit(nite);

                    if (entidadExistente != null) {
                        // Si la entidad ya existe, mostrar un mensaje de error
                        request.setAttribute("mensaje", "La entidad con el NIT " + nite + " ya está registrada.");
                    } else {
                        // Crear una nueva entidad, ya que vio que no existe
                        Entidad nuevaEntidad = new Entidad();
                        nuevaEntidad.setEntidadNit(nite);
                        nuevaEntidad.setEntidadTipo(request.getParameter("tipoEntidad"));
                        nuevaEntidad.setEntidadNombre(request.getParameter("nombreEntidad"));
                        nuevaEntidad.setEntidadCorreo(request.getParameter("correoEntidad"));
                        nuevaEntidad.setEntidadDireccion(request.getParameter("direccionEntidad"));
                        nuevaEntidad.setEntidadTelefono(request.getParameter("telefonoEntidad"));

                        // Insertar la nueva entidad
                        boolean entidadInsertada = edao.addEntidad(nuevaEntidad);

                        // Mensaje según el resultado de la inserción
                        String mensaje = entidadInsertada ? "Entidad agregada exitosamente." : "Error al agregar la entidad.";
                        request.setAttribute("mensaje", mensaje);
                    }

                    // Redirigir a la vista correspondiente
                    request.getRequestDispatcher(addenti).forward(request, response);
                    break;
                case "eliminarEntidad":
                    int idEntidad = Integer.parseInt(request.getParameter("idEntidad"));
                    edao.elimEnti(idEntidad);
                    acceso = entid;
                    break;

                case "obtenerportipo":
                    String tipoEntidad = request.getParameter("tipoEntidad");
                    List<Entidad> listaEntidades = edao.obtenerPorTipo(tipoEntidad);
                    request.setAttribute("listaEntidadesPorTipo", listaEntidades);

                    // Manejo de mensaje si la lista está vacía
                    if (listaEntidades == null || listaEntidades.isEmpty()) { // Añade verificación de null
                        request.setAttribute("mensaje", "No se encontraron entidades de tipo " + tipoEntidad);
                    } else {
                        request.setAttribute("mensaje", "Resultados encontrados para el tipo " + tipoEntidad); // Mensaje de éxito
                    }
                    request.getRequestDispatcher(entid).forward(request, response);
                    break;
            }
            /* request.getRequestDispatcher(entid).forward(request,response);*/
        }

        // Configura los parámetros para el correo una única vez
        String host = "smtp.gmail.com"; 
        String puerto = "587"; 
        String usuarioCorreo = "patzanj45@gmail.com"; // Cambia esto por tu correo
        String contrasenaCorreo = "bpox ahkr ooyg qlvw"; // Cambia esto por tu contraseña de aplicación o credenciales seguras

        // Crea una instancia de la clase Correo solo una vez
        Correo correo = new Correo(host, puerto, usuarioCorreo, contrasenaCorreo);
        
        if (menu != null && menu.equals("solicit")) {
            switch (accion) {
                case "obtenSolici":
                    List<Users> listaUsuarios = sdao.obtenAnalista();
                    request.setAttribute("listaUsuarios", listaUsuarios);

                    String nitSoli = request.getParameter("nitSolicitante").trim();
                    Solicitantes soli = sdao.obtenerSolicitante(nitSoli);
                    request.setAttribute("obtenSol", soli);

                    // Mantener los valores de los campos
                    request.setAttribute("nitSolicitante", nitSoli);
                    request.setAttribute("txtNomSol", request.getParameter("txtNomSol"));
                    request.setAttribute("txtCorSol", request.getParameter("txtCorSol"));
                    request.setAttribute("txtDescProd", request.getParameter("txtDescProd"));
                    request.setAttribute("txtFecha", request.getParameter("txtFecha"));

                    if (soli == null) {
                        request.setAttribute("mensajeSolicitante", "No se encontró este NIT " + nitSoli);
                    }

                    String nitEntidad = request.getParameter("nitEntidad").trim();
                    Entidad entidad = sdao.obtenerPorNit(nitEntidad);
                    request.setAttribute("entidadPorNit", entidad);

                    // Mantener los valores de los campos
                    request.setAttribute("nitEntidad", nitEntidad);
                    request.setAttribute("txtNomProv", request.getParameter("txtNomProv"));
                    request.setAttribute("txtEnti", request.getParameter("txtEnti"));
                    request.setAttribute("txtCorProv", request.getParameter("txtCorProv"));
                    request.setAttribute("txtDiProv", request.getParameter("txtDiProv"));
                    request.setAttribute("txtTelProv", request.getParameter("txtTelProv"));

                    if (entidad == null) {
                        request.setAttribute("mensajeEntidad", "No se encontró este NIT " + nitEntidad);
                    }

                    RequestDispatcher dispatcher = request.getRequestDispatcher(addr);
                    dispatcher.forward(request, response);
                    break;

                case "obtenprov":
                    List<Users> listaUsuarios2 = sdao.obtenAnalista();
                    request.setAttribute("listaUsuarios", listaUsuarios2);

                    String nitEntidad6 = request.getParameter("nitEntidad").trim();
                    Entidad entidad6 = sdao.obtenerPorNit(nitEntidad6);
                    request.setAttribute("entidadPorNit", entidad6);

                    // Mantener los valores de los campos
                    request.setAttribute("nitEntidad", nitEntidad6);
                    request.setAttribute("txtNomProv", request.getParameter("txtNomProv"));
                    request.setAttribute("txtEnti", request.getParameter("txtEnti"));
                    request.setAttribute("txtCorProv", request.getParameter("txtCorProv"));
                    request.setAttribute("txtDiProv", request.getParameter("txtDiProv"));
                    request.setAttribute("txtTelProv", request.getParameter("txtTelProv"));

                    if (entidad6 == null) {
                        request.setAttribute("mensajeProveedor", "No se encontró este NIT " + nitEntidad6);
                    }

                    String nitSoli6 = request.getParameter("nitSolicitante").trim();
                    Solicitantes soli6 = sdao.obtenerSolicitante(nitSoli6);
                    request.setAttribute("obtenSol", soli6);

                    // Mantener los valores de los campos
                    request.setAttribute("nitSolicitante", nitSoli6);
                    request.setAttribute("txtNomSol", request.getParameter("txtNomSol"));
                    request.setAttribute("txtCorSol", request.getParameter("txtCorSol"));
                    request.setAttribute("txtDescProd", request.getParameter("txtDescProd"));
                    request.setAttribute("txtFecha", request.getParameter("txtFecha"));

                    if (soli6 == null) {
                        request.setAttribute("mensajeSolicitante", "No se encontró este NIT " + nitSoli6);
                    }

                    RequestDispatcher dispatcher2 = request.getRequestDispatcher(addr);
                    dispatcher2.forward(request, response);
                    break;


                case "listarr":
                    acceso = listarr;
                    break;
                case "addr":
                    List<Users> listaUsuarios1 = sdao.obtenAnalista();
                    request.setAttribute("listaUsuarios", listaUsuarios1);
                    acceso = addr;
                    break;
                case "Agregar":
                    // Obtener los parámetros del request
                    String tipoSolicitud2 = request.getParameter("txtSoli");
                    String tipoEntidad = request.getParameter("txtEnti");
                    Date fechaSolicitud = Date.valueOf(request.getParameter("txtFecha")); // año-Mes-Dia
                    String tipoDocumento = request.getParameter("txtDoc");
                    String noDocumento = request.getParameter("txtNodoc");
                    String nitProveedor = request.getParameter("nitEntidad");
                    String nombreProveedor = request.getParameter("txtNomProv");
                    String correoProveedor = request.getParameter("txtCorProv");
                    String correoSolicitante = request.getParameter("txtCorSol");
                    String direccionProveedor = request.getParameter("txtDiProv");
                    String telefonoProveedor = request.getParameter("txtTelProv");
                    String nitSolicitante = request.getParameter("nitSolicitante");
                    String nombreSolicitante = request.getParameter("txtNomSol");
                    String noMuestra = request.getParameter("txtNoMuestra");
                    if (noMuestra == null || noMuestra.trim().isEmpty()) {
                        noMuestra = null;
                    }
                    String descripProducto = request.getParameter("txtDescProd");
                    int idUsuario = Integer.parseInt(request.getParameter("analista"));

                    // Obtener el nombre del usuario desde la sesión
                    HttpSession session = request.getSession();
                    String regUsuario = (String) session.getAttribute("usuarioLogueadoNombre"); // Obtener el nombre del usuario logueado
                    String estado = request.getParameter("estado");
                  
                    // Validación de campos requeridos
                    if (nitProveedor == null || nitProveedor.trim().isEmpty() ||
                        nitSolicitante == null || nitSolicitante.trim().isEmpty() ||
                        noDocumento == null || noDocumento.trim().isEmpty() ||
                        //noMuestra == null || noMuestra.trim().isEmpty() ||
                        correoSolicitante == null || correoSolicitante.trim().isEmpty() ||
                        descripProducto == null || descripProducto.trim().isEmpty() ||
                        nombreSolicitante == null || nombreSolicitante.trim().isEmpty() ||
                        nombreProveedor == null || nombreProveedor.trim().isEmpty()){
                        
                        request.setAttribute("error", "Por favor complete todos los campos requeridos.");
                        acceso = addr; 
                        List<Users> listaUsuarios3 = sdao.obtenAnalista();
                        request.setAttribute("listaUsuarios", listaUsuarios3);
                    } else {

                        // Crear una instancia de Conexion para la validación
                        Conexion conexion = new Conexion();

                        // Validar si el número de muestra ya está registrado
                        if (sdao.existeNoMuestra(noMuestra)) {
                            List<Users> listaUsuarios3 = sdao.obtenAnalista();
                            request.setAttribute("listaUsuarios", listaUsuarios3);
                            request.setAttribute("error", "El número de muestra ya se encuentra registrado en otra solicitud.");
                            acceso = addr; // Quedar en la misma página
                        } else {
                            String correoAnalista = sdao.obtenCorreoAnalista(idUsuario);

                            // Configurar los atributos de la solicitud
                            sm.setTipoSolicitud(tipoSolicitud2);
                            sm.setTipoEntidad(tipoEntidad);
                            sm.setFechaSolicitud(fechaSolicitud);
                            sm.setTipodeDocumento(tipoDocumento);
                            sm.setNoDedocumento(noDocumento);
                            sm.setNitProveedor(nitProveedor);
                            sm.setNombreProveedor(nombreProveedor);
                            sm.setCorreoProveedor(correoProveedor);
                            sm.setCorreoSolicitante(correoSolicitante);
                            sm.setDireccionProveedor(direccionProveedor);
                            sm.setTelefonoProveedor(telefonoProveedor);
                            sm.setNitSolicitante(nitSolicitante);
                            sm.setNombreSolicitante(nombreSolicitante);
                            sm.setNoMuestra(noMuestra);
                            sm.setDescripcionProducto(descripProducto);
                            sm.setIdUsuario(idUsuario);
                            sm.setRegUsuario(regUsuario);
                            sm.setEstado(estado);

                            // agregar la solicitud a la base de datos
                            sdao.addR(sm);

                        try {
                            int anio = LocalDate.now().getYear();

                            // obtener el número correlativo para el tipo de solicitud
                            String numeroGenerado = sdao.obtenerNumeroCorrelativoCorreo(tipoSolicitud2);

                            // enviar el correo al solicitante con el número generado
                            correo.enviarCorreo(
                                correoSolicitante, 
                                fechaSolicitud.toString(), 
                                tipoSolicitud2, 
                                numeroGenerado,  // utiliza el número generado por obtenerNumeroCorrelativo()
                                noDocumento, 
                                tipoDocumento
                            );

                            // enviar el correo al analista con el mismo número generado
                            correo.enviarCorreo(
                                correoAnalista,
                                fechaSolicitud.toString(),
                                tipoSolicitud2,
                                numeroGenerado,  
                                noDocumento,
                                tipoDocumento
                            );
                                request.setAttribute("mensaje", "Solicitud creada y correo enviado exitosamente.");
                            } catch (Exception e) {
                                request.setAttribute("errorCorreo", "Error al enviar el correo: " + e.getMessage());
                            }

                            acceso = listarr; 
                        }
                    }
                    break;


                case "editarr":
                    //idusuario va en el JSP
                    request.setAttribute("idsolicitud", request.getParameter("idsoli"));
                    acceso = editr;
                    break;
                case "actualizarr":
                    idsolicitud = Integer.parseInt(request.getParameter("txtIdSol"));
                    tipoSolicitud2 = request.getParameter("txtSoli");
                    tipoEntidad = request.getParameter("txtEnti");
                    fechaSolicitud = Date.valueOf(request.getParameter("txtFecha"));
                    tipoDocumento = request.getParameter("txtDodc");
                    noDocumento = request.getParameter("txtNoDoc");
                    nitProveedor = request.getParameter("nitEntidad");
                    nombreProveedor = request.getParameter("txtNomProv");
                    correoProveedor = request.getParameter("txtCorProv");
                    correoSolicitante = request.getParameter("txtCorSol");
                    direccionProveedor = request.getParameter("txtDiProv");
                    telefonoProveedor = request.getParameter("txtTelProv");
                    nitSolicitante = request.getParameter("nitSolicitante");
                    nombreSolicitante = request.getParameter("txtNomSol");
                    noMuestra = request.getParameter("txtNoMuestra");
                    descripProducto = request.getParameter("txtDescProd");
                    idUsuario = Integer.parseInt(request.getParameter("analista"));
                    //regUsuario = request.getParameter("usuarioLogueadoNombre");
                    //estado = request.getParameter("estado");

                    // Setear los valores en el objeto de solicitud
                    sm.setIdSolicitud(idsolicitud);
                    sm.setTipoSolicitud(tipoSolicitud2);
                    sm.setTipoEntidad(tipoEntidad);
                    sm.setFechaSolicitud(fechaSolicitud);
                    sm.setTipodeDocumento(tipoDocumento);
                    sm.setNoDedocumento(noDocumento);
                    sm.setNitProveedor(nitProveedor);
                    sm.setNombreProveedor(nombreProveedor);
                    sm.setCorreoProveedor(correoProveedor);
                    sm.setCorreoSolicitante(correoSolicitante);
                    sm.setDireccionProveedor(direccionProveedor);
                    sm.setTelefonoProveedor(telefonoProveedor);
                    sm.setNitSolicitante(nitSolicitante);
                    sm.setNombreSolicitante(nombreSolicitante);
                    sm.setNoMuestra(noMuestra);
                    sm.setDescripcionProducto(descripProducto);
                    sm.setIdUsuario(idUsuario);
                    //sm.setRegUsuario(regUsuario);
                    //sm.setEstado(estado);

                    // Llamar a la función para editar la solicitud en la base de datos
                    sdao.editR(sm);
                    acceso = listarr; // Redirigir a la lista de solicitudes
                    break;
                

                case "eliminarr":
                    idsolicitud = Integer.parseInt(request.getParameter("idsolicitud"));//el "id" viene del jsp de listar boton
                    sm.setIdSolicitud(idsolicitud);
                    sdao.eliminarR(idsolicitud);
                    acceso = listarr;
                    break;
            }
        }
        

        if ("generarpdf".equals(accion)) {  
            // Configurar el tipo de respuesta como PDF
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=etiqueta.pdf");

            try {
                // Crear el PDF
                Document document = new Document();
                // Obtener el output stream de la respuesta
                PdfWriter.getInstance(document, response.getOutputStream());

                // Abrir el documento para escribir
                document.open();

                // Crear una tabla con 2 columnas
                PdfPTable table = new PdfPTable(2);
                table.setWidthPercentage(100); // Ancho de la tabla
                table.setWidths(new float[]{1, 1}); // Proporción igual para ambas columnas

                // Columna izquierda: Información del laboratorio (centrado)
                PdfPCell cellLeft = new PdfPCell();
                cellLeft.setBorder(Rectangle.BOX); // Borde alrededor de la celda
                cellLeft.setPadding(10); // Margen interno
                cellLeft.setHorizontalAlignment(Element.ALIGN_CENTER); 
                cellLeft.setVerticalAlignment(Element.ALIGN_MIDDLE); 
                cellLeft.addElement(new Paragraph("LABORATORIO DE\nINSPECCIÓN DE CALIDAD ALIMENTOS\n'QUE RIQUITO ESTÁ'", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 10)));
                table.addCell(cellLeft);

                // Columna derecha: Información de la muestra y del proveedor
                PdfPCell cellRight = new PdfPCell();
                cellRight.setBorder(Rectangle.BOX); // Borde alrededor de la celda
                cellRight.setPadding(10); // Margen interno
                cellRight.setHorizontalAlignment(Element.ALIGN_LEFT); // Alinear texto a la izquierda
                cellRight.setVerticalAlignment(Element.ALIGN_TOP); // Alinear texto en la parte superior

                // Obtener datos de la solicitud
                String tipoSolicitud = request.getParameter("txtSoli"); 
                String numeroMuestra5 = request.getParameter("txtNoMuestra");
                String nombreProveedor = request.getParameter("txtNomProv"); 
                String nitProveedor = request.getParameter("nitEntidad"); 
                String numeroExpediente = request.getParameter("txtNodoc"); 
                String nombreAnalista = request.getParameter("nombreAnalista"); 

                // Obtener el número correlativo
                String numeroCorrelativo = sdao.obtenerNumeroCorrelativo(tipoSolicitud);

                // Generar el número de muestra o solicitud
                String numeroGenerado;
                if ("ConNumerodeMuestra".equals(tipoSolicitud)) {
                    numeroGenerado = numeroMuestra5 + "-" + Calendar.getInstance().get(Calendar.YEAR);
                } else {
                    numeroGenerado = numeroCorrelativo; // Usar el número correlativo
                }

                // Añadir información de la muestra y proveedor en la celda derecha
                cellRight.addElement(new Paragraph("Número de Muestra o Porción de Muestra: " + numeroGenerado, FontFactory.getFont(FontFactory.HELVETICA, 10)));
                cellRight.addElement(new Paragraph("Nombre del Proveedor: " + nombreProveedor, FontFactory.getFont(FontFactory.HELVETICA, 10)));
                cellRight.addElement(new Paragraph("NIT del Proveedor: " + nitProveedor, FontFactory.getFont(FontFactory.HELVETICA, 10)));
                cellRight.addElement(new Paragraph("Número de Expediente: " + numeroExpediente, FontFactory.getFont(FontFactory.HELVETICA, 10)));
                cellRight.addElement(new Paragraph("Nombre del Analista: " + nombreAnalista, FontFactory.getFont(FontFactory.HELVETICA, 10)));

                table.addCell(cellRight);

                // Añadir la tabla al documento
                document.add(table);

                // Cerrar el documento
                document.close();
            } catch (Exception e) {
                e.printStackTrace();
                response.getWriter().println("Error al generar el PDF: " + e.getMessage());
            }
        }

        
        if(menu != null && menu.equals("reasignar")){
            switch(accion){
                case "listReasignar":
                    acceso = listReasignar;
                    break;
                case "1":
                    break;
                case "2":
                    break;
                    
            }
            
        }

        if (menu != null && menu.equals("usuarios")) {
            switch (accion) {
                case "listar":
                    acceso = listar;
                    break;
                case "init":
                    acceso = init;
                    break;
                case "obtenUsuario":
                    List<Roles> listaRoles1 = dao.listaRoles();
                    request.setAttribute("listaRoles", listaRoles1);
                    
                   String nitPersona = request.getParameter("nitPersona").trim(); // obtener el nit del request
                   Users usuario = dao.obtenUsuarioPorNit(nitPersona); //ejecutar metodo

                   // Establecer el usuario en el request
                   request.setAttribute("usuarioPorNit", usuario);

                   // Mensaje si no se encontraron resultados
                   if (usuario == null) {
                       request.setAttribute("mensaje", "No se encontró el usuario con NIT " + nitPersona);
                   }

                   // Redirige a la página de resultados de búsqueda
                   acceso = add; // Asegúrate de que la ruta sea correcta
                   break;

                case "add":
                    List<Roles> listaRoles = dao.listaRoles();
                    request.setAttribute("listaRoles", listaRoles);
                    acceso = add;
                    break;
                    
                case "agregar":
                    String nomb = request.getParameter("txtNom1");
                    String nomb2 = request.getParameter("txtNom2");
                    String ap1 = request.getParameter("txtAp1");
                    String ap2 = request.getParameter("txtAp2");
                    String log = request.getParameter("txtLog");
                    String cont = request.getParameter("txtCont");
                    String nit = request.getParameter("txtNit");
                    String puesto = request.getParameter("txtPuesto");
                    int idrol = Integer.parseInt(request.getParameter("txtRol"));
                    String estado = request.getParameter("txtEstado");
                    String correo2 = request.getParameter("txtCorreo");

                    u.setPrimerNombre(nomb);
                    u.setSegundoNombre(nomb2);
                    u.setPrimerApellido(ap1);
                    u.setSegundoApellido(ap2);
                    u.setLogin(log);
                    u.setContrasenia(cont);
                    u.setNitpersona(nit);
                    u.setPuesto(puesto);
                    u.setIdRol(idrol);
                    u.setEstado(estado);
                    u.setCorreo(correo2);
                    
                    // Verificar si el NIT ya existe
                    if (dao.existeNitu(nit)) { // Verifica si existe el NIT
                        request.setAttribute("mensaje", "El NIT ya está registrado.");
                        acceso = add; // Redirige a una página de error o muestra un mensaje
                    } else {
                        // Agregar el usuario ya que el NIT no existe
                        dao.add(u);
                        request.setAttribute("mensaje", "Usuario agregado exitosamente");
                        acceso = add; // Redirigir a la lista de usuarios
                    }
                    break;
                case "editar":
                    List<Users> listaUsuarios = dao.listar();
                    //idusuario va en el JSP
                    request.setAttribute("idusuario", request.getParameter("idusua"));
                    acceso = edit;
                    break;
                case "actualizar":
                    id = Integer.parseInt(request.getParameter("idusua"));
                    nomb = request.getParameter("txtNom1");
                    nomb2 = request.getParameter("txtNom2");
                    ap1 = request.getParameter("txtAp1");
                    ap2 = request.getParameter("txtAp2");
                    /*log = request.getParameter("txtLog");*/
                    cont = request.getParameter("txtCont");
                    nit = request.getParameter("txtNit");
                    puesto = request.getParameter("txtPuesto");
                    idrol = Integer.parseInt(request.getParameter("txtRol"));
                    estado = request.getParameter("txtEstado");
                    correo2 = request.getParameter("txtCorreo");
                    String motivo = request.getParameter("txtMotivo");

                    u.setIdusuario(id);
                    u.setPrimerNombre(nomb);
                    u.setSegundoNombre(nomb2);
                    u.setPrimerApellido(ap1);
                    u.setSegundoApellido(ap2);
                    /*u.setLogin(log);*/
                    u.setContrasenia(cont);
                    u.setNitpersona(nit);
                    u.setPuesto(puesto);
                    u.setIdRol(idrol);
                    u.setEstado(estado);
                    u.setMotivo(motivo);
                    
                    dao.edit(u);
                    acceso = listar;
                    break;

                case "eliminar":
                    id = Integer.parseInt(request.getParameter("id"));//el "id" viene del jsp de listar boton
                    u.setIdusuario(id);
                    dao.eliminar(id);
                    acceso = listar;
                    break;
            }
        }
        
        
     
        

        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
