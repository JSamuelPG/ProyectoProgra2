
package Controlador;

import Modelo.Users;
import Modelo.Persona;
import ModeloDAO.PersonaDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
    String login2 = "index.jsp"; // Página de login
    Persona p = new Persona();
    Users u= new Users();
    PersonaDAO dao = new PersonaDAO();
    int id;
    int idusua;

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
                int rol = Integer.parseInt(request.getParameter("txtRol"));
                
                u.setPrimerNombre(nomb);
                u.setSegundoNombre(nomb2);
                u.setPrimerApellido(ap1);
                u.setSegundoApellido(ap2);
                u.setLogin(log);
                u.setContrasenia(cont);
                u.setNitpersona(nit);
                u.setPuesto(puesto);
                u.setRoles(rol);
                dao.add(u);
                acceso=listar;

                break;
            case "editar":
                //idusuario va en el JSP
                request.setAttribute("idusuario", request.getParameter("idusua"));
                
                /*request.setAttribute("idper", request.getParameter("id"));*/
                acceso = edit;
                break;
            case "actualizar":
                idusua = Integer.parseInt(request.getParameter("txtidusu"));
                nomb = request.getParameter("txtNom1");
                nomb2 = request.getParameter("txtNom2");
                ap1 = request.getParameter("txtAp1");
                ap2 = request.getParameter("txtAp2");
                log = request.getParameter("txtLog");
                cont = request.getParameter("txtCont");
                nit = request.getParameter("txtNit");
                puesto = request.getParameter("txtPuesto");
                rol = Integer.parseInt(request.getParameter("txtRol"));

                u.setIdusuario(idusua); 
                u.setPrimerNombre(nomb);
                u.setSegundoNombre(nomb2);
                u.setPrimerApellido(ap1);
                u.setSegundoApellido(ap2);
                u.setLogin(log);
                u.setContrasenia(cont);
                u.setNitpersona(nit);
                u.setPuesto(puesto);
                u.setRoles(rol);
                dao.edit(u); 
                acceso = listar;
                break;

            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                p.setId(id);
                dao.eliminar(id);  // Asegúrate de que eliminar2 sea el método adecuado en tu DAO
                acceso = listar;
                break;

            case "login":
                acceso = login2;
                break;
            case "authenticate":
                String login = request.getParameter("login");
                String contrasenia = request.getParameter("contrasenia");

                boolean isValid = dao.validateUser(login, contrasenia); 
                if (isValid) {
                    acceso = listar; 
                } else {
                    request.setAttribute("errorMessage", "usuario o contraseña invalido");
                    acceso = login;
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
