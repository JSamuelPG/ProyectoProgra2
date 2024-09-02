
package Controlador;

import Modelo.Users;
import Modelo.Persona;
import ModeloDAO.PersonaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Controlador extends HttpServlet {

    String listar = "vistas/listar.jsp";
    String add = "vistas/add.jsp";
    String edit = "vistas/edit.jsp";
    String login = "index.jsp"; // Página de login
    Persona p = new Persona();
    Users u= new Users();
    PersonaDAO dao = new PersonaDAO();
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
            case "add":
                acceso = add;
                break;
            case "agregar":
                String dpi = request.getParameter("txtDpi");
                String nom = request.getParameter("txtNom");
                p.setDpi(dpi);
                p.setNom(nom);
                dao.add2(p);
                acceso = listar;
                break;
            case "editar":
                request.setAttribute("idper", request.getParameter("id"));
                acceso = edit;
                break;
            case "actualizar":
                id = Integer.parseInt(request.getParameter("txtid"));
                String dni = request.getParameter("txtDpi");
                String nom2 = request.getParameter("txtNom");
                p.setId(id);
                p.setDpi(dni);
                p.setNom(nom2);
                dao.edit2(p);
                acceso = listar;
                break;
            case "eliminar":
                id = Integer.parseInt(request.getParameter("id"));
                p.setId(id);
                dao.eliminar2(id);
                acceso = listar;
                break;
            case "login":
                acceso = login;
                break;
            case "authenticate":
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                boolean isValid = dao.validateUser(username, password); 
                if (isValid) {
                    acceso = listar; 
                } else {
                    request.setAttribute("errorMessage", "usuario o contraseña invalido");
                    acceso = login;
                }
                break;
            default:
                acceso = login;
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
