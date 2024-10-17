
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
   
    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List<Entidad> obtenerPorTipo(String tipoEntidad) {
        List<Entidad> lista = new ArrayList<>();
        String sql = "SELECT * FROM entidades_registrado WHERE er_Tipo = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion cn = new Conexion();
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
        } finally {
            // Cerrar ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;
    }
    
//VERIFICAR SI YA EXISTE LA ENTIDAD EN LOS REGISTRADOS
    public Entidad existeNit(String nitEntidad) {
        Entidad entidad = null;
        String sql = "SELECT * FROM entidades_registrado WHERE er_Nit = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {Conexion cn = new Conexion();
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
        } finally {
            // Cerrar ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } 
        return entidad;
    }

    public Entidad obtenerPorNit(String nitEntidad) {
        Entidad entidad = null;
        String sql = "SELECT * FROM entidades_total WHERE e_Nit = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Conexion cn = new Conexion();
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
                entidad.setEntidadCorreo(rs.getString("e_Correo"));
                entidad.setEntidadDireccion(rs.getString("e_Direccion"));
                entidad.setEntidadTelefono(rs.getString("e_Telefono"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        } finally {
            // Cerrar ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }  
        return entidad;
    }

    public boolean addEntidad(Entidad enti) {
        String sql = "INSERT INTO entidades_registrado (er_Nit, er_Nombre, er_Tipo, er_Correo, er_Direccion, er_Telefono) VALUES (?, ?, ?, ?, ?, ?)";
        boolean inserted = false; // Variable para verificar si se insertó
        Connection con = null;
        PreparedStatement ps = null;

        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, enti.getEntidadNit());
            ps.setString(2, enti.getEntidadNombre());
            ps.setString(3, enti.getEntidadTipo());
            ps.setString(4, enti.getEntidadCorreo());
            ps.setString(5, enti.getEntidadDireccion());
            ps.setString(6, enti.getEntidadTelefono());
            
            int rowsAffected = ps.executeUpdate(); // Ejecuta la inserción
            inserted = rowsAffected > 0; // Verifica si se afectó alguna fila
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar PreparedStatement y Connection (ResultSet no es necesario aquí)
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return inserted; // Retorna verdadero si se inserto
    }
    
    public boolean elimEnti(int idEntidad) {
        String sql = "delete from entidades_registrado where er_Id=" + idEntidad;
        Connection con = null;
        PreparedStatement ps = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                return true;  // devuelve verdadero en eliminar la entidad o fila 
            }
        } catch (Exception e) {
            e.printStackTrace();  // maneja la excepción si es necesario
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;  // Retorna false si no se eliminó ninguna fila
    }

    
    
}