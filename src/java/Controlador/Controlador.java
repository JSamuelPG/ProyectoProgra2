
package Controlador;

import java.sql.Connection;
import Config.Conexion;
import Modelo.SoliMuestra;
import Modelo.Users;
import ModeloDAO.PersonaDAO;
import ModeloDAO.SoliMuestraDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashSet;
import java.util.Set;
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
    String listarR = "vistas/listarR.jsp";
    String addR = "vistas/addR.jsp";
    String editR = "vistas/editR.jsp";
    
    SoliMuestra sm = new SoliMuestra();
    Users u= new Users();
    PersonaDAO dao = new PersonaDAO();
    SoliMuestraDAO sdao = new SoliMuestraDAO();
    
    int idsolicitud;
    
    int id;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";
        String action = request.getParameter("accion");

        if (action == null) {
            action = "";
        }

        switch (action.toLowerCase()) {
            case "listar":
                acceso = listar;
                break;
            case "init":
                acceso=init;
                break;
            case "add":
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
                String rol = request.getParameter("txtRol");
                String estado = request.getParameter("txtEstado");
                
                u.setPrimerNombre(nomb);
                u.setSegundoNombre(nomb2);
                u.setPrimerApellido(ap1);
                u.setSegundoApellido(ap2);
                u.setLogin(log);
                u.setContrasenia(cont);
                u.setNitpersona(nit);
                u.setPuesto(puesto);
                u.setRoles(rol);
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
                rol = request.getParameter("txtRol");
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
                u.setRoles(rol);
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
            case "listarR":
                acceso = listarR;
                break;
            case "addR":
                acceso = addR;
                break;
            case "agregarR":
                
                String tipoSolicitud =request.getParameter("txtSoli");
                String tipoEntidad =request.getParameter("txtEnti");
                Date fechaSolicitud = Date.valueOf(request.getParameter("txtFecha")); //Y-M-D
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
                acceso=listarR;

                break;
            case "editarR":
                //idusuario va en el JSP
                request.setAttribute("idsolicitud", request.getParameter("idsoli"));
                acceso = editR;
                break;
            case "actualizarR":
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
                acceso = listarR;
                break;

            case "eliminarR":
                idsolicitud = Integer.parseInt(request.getParameter("idsolicitud"));//el "id" viene del jsp de listar boton
                sm.setIdSolicitud(idsolicitud);
                sdao.eliminarR(idsolicitud); 
                acceso = listarR;
                break;
            
                
            case "login":
                acceso = login2;
                break;
            case "authenticate":
                String login = request.getParameter("login");
                String contrasenia = request.getParameter("contrasenia");

                Conexion conexion = new Conexion();
                Connection con = conexion.getConnection();

                boolean isValid = conexion.validateUser(login, contrasenia);

                if (isValid) {
                    String roleName = conexion.getUserRole(login, contrasenia);  // Método para obtener el nombre del rol
                    switch (roleName) {
                        case "RegistroMuestras":
                            acceso = listarR;
                            break;
                        case "AnalistadeLaboratorio":
                            acceso = "";
                            break;
                        case "AlmacenamientodeMuestra":
                            acceso = "supervisor.jsp";
                            break;
                        case "SupervisorLaboratorio":
                            acceso = "admin.jsp";
                            break;
                        case "JefeUnidadLaboratorio":
                            acceso = "reportes.jsp";
                            break;
                        case "LaboratorioExterno":
                            acceso = "reportes.jsp";
                            break;
                        case "Reportes":
                            acceso = "reportes.jsp";
                            break;
                        case "VisualizacionDocumentos":
                            acceso = "reportes.jsp";
                            break;
                        case "Administrador":
                            acceso = init;
                            break;
                        default:
                            acceso = login2;
                            break;
                    }
                } else {
                    request.setAttribute("errorMessage", "usuario o contraseña inválido");
                    acceso = login2;
                }
            break;   
        default:
            acceso = login2;
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
