
package ModeloDAO;

import Modelo.Entidad;
import Config.Conexion;
import Intefaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Samuel
 */
public class EntidadDAO {
   
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
public List<Entidad> obtenerPorTipo(String tipoEntidad) {
    List<Entidad> lista = new ArrayList<>();
    String sql = "SELECT * FROM entidades_registrado WHERE er_Tipo = ?";

    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql);
        ps.setString(1, tipoEntidad);
        System.out.println("Tipo de entidad buscado: " + tipoEntidad);
        
        rs = ps.executeQuery(); // Ejecutar la consulta

        while (rs.next()) {
            Entidad entidad = new Entidad();
            entidad.setEntidadId(rs.getInt("er_Id"));
            entidad.setEntidadNit(rs.getString("er_Nit"));
            entidad.setEntidadNombre(rs.getString("er_Nombre"));
            entidad.setEntidadTipo(rs.getString("er_Tipo"));
            lista.add(entidad);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    } 
    return lista;
}

public Entidad obtenPorNit(String nitEntidad) {
    Entidad entidad = null;
    String sql = "SELECT * FROM entidades_registrado WHERE er_Nit = ?";
    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql);
        ps.setString(1, nitEntidad);
        System.out.println("NIT buscado: " + nitEntidad);
        
        rs = ps.executeQuery(); // Ejecutar la consulta
        
        if (rs.next()) {
            entidad = new Entidad();
            entidad.setEntidadId(rs.getInt("er_Id"));
            entidad.setEntidadNit(rs.getString("er_Nit"));
            entidad.setEntidadNombre(rs.getString("er_Nombre"));
            entidad.setEntidadTipo(rs.getString("er_Tipo"));
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }  
    return entidad;
}  



public Entidad obtenerPorNit(String nitEntidad) {
    Entidad entidad = null;
    String sql = "SELECT * FROM entidades_total WHERE e_Nit = ?";
    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql);
        ps.setString(1, nitEntidad);
        System.out.println("NIT buscado: " + nitEntidad);
        
        rs = ps.executeQuery(); // Ejecutar la consulta
        
        if (rs.next()) {
            entidad = new Entidad();
            entidad.setEntidadId(rs.getInt("e_Id"));
            entidad.setEntidadNit(rs.getString("e_Nit"));
            entidad.setEntidadNombre(rs.getString("e_Nombre"));
            entidad.setEntidadTipo(rs.getString("e_Tipo"));
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    }  
    return entidad;
}  
public boolean addEntidad(Entidad enti) {
    String sql = "INSERT INTO entidades_registrado (er_Nit, er_Nombre, er_Tipo) VALUES (?, ?, ?)";
    boolean inserted = false; // Variable para verificar si se insertó

    try {
        con = cn.getConnection();
        ps = con.prepareStatement(sql);
        ps.setString(1, enti.getEntidadNit());
        ps.setString(2, enti.getEntidadNombre());
        ps.setString(3, enti.getEntidadTipo());
        
        int rowsAffected = ps.executeUpdate(); // Ejecuta la inserción
        inserted = rowsAffected > 0; // Verifica si se afectó alguna fila
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return inserted; // Retorna verdadero si se insertó
}

}