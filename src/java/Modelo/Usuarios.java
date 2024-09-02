
package Modelo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Samuel
 */
public class Usuarios {
    private String username;
    private String password;

    public Usuarios(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Validación de usuario contra la base de datos
    public boolean validateUser() {
        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String dbUser = "root";  // Cambia esto si tienes un usuario y contraseña diferentes
        String dbPassword = "root";

        try (Connection conn = DriverManager.getConnection(url, dbUser, dbPassword)) {
            String query = "SELECT * FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Retorna true si encuentra un registro que coincida

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}