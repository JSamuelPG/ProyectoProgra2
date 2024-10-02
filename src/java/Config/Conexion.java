
package Config;

import java.sql.*;

public class Conexion {
    private Connection con;

    // constructor para iniciar la conexión a la BaseDatos
    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/queriquitoesta", "root", "32..@Tam:78:Fo5");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // metodo para obtener la conexión
    public Connection getConnection() {
        return con;
    }

    // metodo para validar usuario
    public boolean validateUser(String login, String contrasenia) {
        String query = "SELECT * FROM usuarios WHERE login = ? AND contrasenia = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, contrasenia);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // retorna true si encuentra un registro que coincida

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // retorna false si hay algún error o si no encuentra coincidencias
    }
    //metodo para validar roles
     public int getUserRole(String login, String contrasenia) {
        String query = "SELECT id_rol FROM usuarios WHERE login = ? AND contrasenia = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, contrasenia);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_rol");  // Obtener el rol del usuario
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;  // Retorna nulo si hay algún error o si no encuentra coincidencias
    }

}
