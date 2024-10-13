
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
            return rs.next();  // retorna verdadero si encuentra un registro que coincida

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;  // retorna falso si hay algún error o si no encuentra coincidencias
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
     
            // Método para obtener el ID del usuario
       public int getUserId(String login, String contrasenia) {
           int idUsuario = -1;
           String sql = "SELECT id_usuario FROM usuarios WHERE login = ? AND contrasenia = ?";
           try (PreparedStatement stmt = con.prepareStatement(sql)) {
               stmt.setString(1, login);
               stmt.setString(2, contrasenia);
               ResultSet rs = stmt.executeQuery();
               if (rs.next()) {
                   idUsuario = rs.getInt("id_usuario");
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return idUsuario;
       }

       // Método para obtener el nombre del usuario
       public String getUserName(String login, String contrasenia) {
           String nombreUsuario = null;
           String sql = "SELECT primer_nombre, segundo_nombre, primer_apellido, segundo_apellido FROM usuarios WHERE login = ? AND contrasenia = ?";
           try (PreparedStatement stmt = con.prepareStatement(sql)) {
               stmt.setString(1, login);
               stmt.setString(2, contrasenia);
               ResultSet rs = stmt.executeQuery();
               if (rs.next()) {
                   String primerNombre = rs.getString("primer_nombre");
                   String segundoNombre = rs.getString("segundo_nombre");
                   String primerApellido = rs.getString("primer_apellido");
                   String segundoApellido = rs.getString("segundo_apellido");
                   // Concatenar el nombre completo
                   nombreUsuario = primerNombre + " " + (segundoNombre != null ? segundoNombre + " " : "") + primerApellido + " " + (segundoApellido != null ? segundoApellido : "");
               }
           } catch (SQLException e) {
               e.printStackTrace();
           }
           return nombreUsuario;
       }

     
     

}
