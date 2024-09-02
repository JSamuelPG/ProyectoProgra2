
package Config;

import java.sql.*;

public class Conexion {
    private Connection con;

    // Constructor para inicializar la conexión a la base de datos
    public Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/registro", "root", "32..@Tam:78:Fo5");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    // Método para obtener la conexión
    public Connection getConnection() {
        return con;
    }

    // Método para validar el usuario
    public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM usuarios WHERE usarname = ? AND password = ?";
        try (PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();  // Retorna true si encuentra un registro que coincida

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // Retorna false si hay algún error o si no encuentra coincidencias
    }
}
