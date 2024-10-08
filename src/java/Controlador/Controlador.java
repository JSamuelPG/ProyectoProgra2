
package Controlador;

import Modelo.Roles;
import Modelo.Entidad;
import java.sql.Connection;
import Config.Conexion;
import Modelo.SoliMuestra;
import Modelo.Users;
import ModeloDAO.PersonaDAO;
import ModeloDAO.SoliMuestraDAO;
import ModeloDAO.EntidadDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controlador extends HttpServlet {

    String listar = "vistas/listar.jsp";
    String add = "vistas/add.jsp";
    String edit = "vistas/edit.jsp";
    String login2 = "index.jsp";
    String init = "vistas/init.jsp";
    String listarr = "vistas/listarR.jsp";
    String addr = "vistas/addR.jsp";
    String editr = "vistas/editR.jsp";
    String entid = "vistas/entidades.jsp";
    String addenti = "vistas/addEntidad.jsp";
    
    SoliMuestra sm = new SoliMuestra();
    Users u= new Users();
    PersonaDAO dao = new PersonaDAO();
    SoliMuestraDAO sdao = new SoliMuestraDAO();
    EntidadDAO edao = new EntidadDAO();
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
        if(menu != null && menu.equals("inicio")){
            
            switch(accion){
                case "authenticate":
                    String login = request.getParameter("login");
                    String contrasenia = request.getParameter("contrasenia");

                    Conexion conexion = new Conexion();
                    Connection con = conexion.getConnection();

                    boolean isValid = conexion.validateUser(login, contrasenia);

                    if (isValid) {
                        int idRol = conexion.getUserRole(login, contrasenia);  // Método para obtener el nombre del rol
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

                    // Verificar si ya existe una entidad con el NIT dado
                    Entidad entidadExistente = edao.obtenPorNit(nite);

                    if (entidadExistente != null) {
                        // Si la entidad ya existe, mostrar un mensaje de error
                        request.setAttribute("mensaje", "La entidad con el NIT " + nite + " ya está registrada.");
                    } else {
                        // Crear una nueva entidad directamente, ya que se verificó que no existe
                        Entidad nuevaEntidad = new Entidad();
                        nuevaEntidad.setEntidadNit(nite);
                        nuevaEntidad.setEntidadTipo(request.getParameter("tipoEntidad"));
                        nuevaEntidad.setEntidadNombre(request.getParameter("nombreEntidad"));

                        // Insertar la nueva entidad
                        boolean entidadInsertada = edao.addEntidad(nuevaEntidad);

                        // Mensaje según el resultado de la inserción
                        String mensaje = entidadInsertada ? "Entidad agregada exitosamente." : "Error al agregar la entidad.";
                        request.setAttribute("mensaje", mensaje);
                    }

                    // Redirigir a la vista correspondiente
                    request.getRequestDispatcher(addenti).forward(request, response);
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
                    request.getRequestDispatcher(entid).forward(request,response);
                    break; 


            }
           /* request.getRequestDispatcher(entid).forward(request,response);*/
        }
        
        
       
        
        
        
        
        
        
        
        
        
        
        // Obtener la lista de roles (solo una vez para reutilizarla)
        List<Roles> listaRoles = dao.listaRoles();
        request.setAttribute("listaRoles", listaRoles);

        switch (accion.toLowerCase()) {
            case "listar":
                acceso = listar;
                break;
            case "init":
                acceso=init;
                break;
            case "add":
                request.setAttribute("listaRoles", listaRoles);
                acceso = add;
                break;
            case "agregar":
                
                String nomb =request.getParameter("txtNom1");
                String nomb2 =request.getParameter("txtNom2");
                String ap1 =request.getParameter("txtAp1");
                String ap2 =request.getParameter("txtAp2");
                String log =request.getParameter("txtLog");
                String cont =request.getParameter("txtCont");
                String nit =request.getParameter("txtNit");
                String puesto =request.getParameter("txtPuesto");
                int idrol = Integer.parseInt(request.getParameter("txtRol"));
                String estado = request.getParameter("txtEstado");
                
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
                dao.add(u);
                acceso=listar;

                break;
            case "editar":
                //idusuario va en el JSP
                request.setAttribute("idusuario", request.getParameter("idusua"));
                acceso = edit;
                break;
            case "actualizar":
                id = Integer.parseInt(request.getParameter("txtidusu"));
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
                dao.edit(u); 
                acceso = listar;
                break;

            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));//el "id" viene del jsp de listar boton
                u.setIdusuario(id);
                dao.eliminar(id); 
                acceso = listar;
                break;
            
                
                //PARA REGISTRO DE SOLICITUDES Y MUESTRA///////////////////////////////////
            case "listarr":
                acceso = listarr;
                break;
            case "addr":
                acceso = addr;
                break;
            case "agregarr":
                
                String tipoSolicitud =request.getParameter("txtSoli");
                String tipoEntidad =request.getParameter("txtEnti");
                Date fechaSolicitud = Date.valueOf(request.getParameter("txtFecha")); //año-Mes-Dia
                String tipoDocumento =request.getParameter("txtDoc");
                String noDocumento =request.getParameter("txtNodoc");
                String nitProveedor =request.getParameter("txtNitProv");
                String nombreProveedor =request.getParameter("txtNomProv");
                String correoProveedor =request.getParameter("txtCorProv");
                String correoSolicitante = request.getParameter("txtCorSol");
                String direccionProveedor = request.getParameter("txtDiProv");
                String telefonoProveedor = request.getParameter("txtTelProv");
                String nitSolicitante = request.getParameter("txtNitSol");
                String nombreSolicitante = request.getParameter("txtNomSol");
                String noMuestra = request.getParameter("txtNoMuestra");
                String descripProducto = request.getParameter("txtDescProd");
                
                sm.setTipoSolicitud(tipoSolicitud);
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
                
                sdao.addR(sm);
                acceso=listarr;

                break;
            case "editarr":
                //idusuario va en el JSP
                request.setAttribute("idsolicitud", request.getParameter("idsoli"));
                acceso = editr;
                break;
            case "actualizarr":
                idsolicitud = Integer.parseInt(request.getParameter("txtIdSol"));
                tipoSolicitud = request.getParameter("txtSoli");
                tipoEntidad = request.getParameter("txtEnti");
                fechaSolicitud = Date.valueOf(request.getParameter("txtFecha"));
                tipoDocumento = request.getParameter("txtDodc");
                noDocumento = request.getParameter("txtNoDoc");
                nitProveedor = request.getParameter("txtNitProv");
                nombreProveedor = request.getParameter("txtNomProv");
                correoProveedor = request.getParameter("txtCorProv");
                correoSolicitante = request.getParameter("txtCorSol");
                direccionProveedor = request.getParameter("txtDiProv");
                telefonoProveedor = request.getParameter("txtTelProv");
                nitSolicitante = request.getParameter("txtNitSol");
                nombreSolicitante = request.getParameter("txtNomSol");
                noMuestra = request.getParameter("txtNoMuestra");
                descripProducto = request.getParameter("txtDescProd");
                
                sm.setIdSolicitud(idsolicitud);
                sm.setTipoSolicitud(tipoSolicitud);
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
                sdao.editR(sm); 
                acceso = listarr;
                break;

            case "eliminarr":
                idsolicitud = Integer.parseInt(request.getParameter("idsolicitud"));//el "id" viene del jsp de listar boton
                sm.setIdSolicitud(idsolicitud);
                sdao.eliminarR(idsolicitud); 
                acceso = listarr;
                break;
            

 
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
