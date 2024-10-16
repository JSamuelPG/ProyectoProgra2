package ModeloDAO;

import Modelo.Roles;
import Modelo.Users;
import Config.Conexion;
import Intefaces.CRUD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO implements CRUD {

    Conexion cn = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Users u = new Users();

    private Conexion conexion;

    public PersonaDAO() {
        conexion = new Conexion();
    }
    
public Users obtenUsuarioPorNit(String nitPersona) {
    Users usuario = null;
    // Consulta para buscar por nit_persona
    String sql = "SELECT * FROM usuarios_total WHERE nit_persona = ?";
    Conexion cn = new Conexion(); // Asegúrate de tener esta clase configurada correctamente
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
        con = cn.getConnection(); // Obtener la conexión
        ps = con.prepareStatement(sql);
        ps.setString(1, nitPersona); // Establecer el nit_persona en la consulta
        rs = ps.executeQuery(); // Ejecutar la consulta

        // Procesar el resultado
        if (rs.next()) {
            usuario = new Users();
            usuario.setIdusuario(rs.getInt("id_usuario"));
            usuario.setPrimerNombre(rs.getString("primer_nombre"));
            usuario.setSegundoNombre(rs.getString("segundo_nombre"));
            usuario.setPrimerApellido(rs.getString("primer_apellido"));
            usuario.setSegundoApellido(rs.getString("segundo_apellido"));
            usuario.setLogin(rs.getString("login"));
            usuario.setContrasenia(rs.getString("contrasenia"));
            usuario.setNitpersona(rs.getString("nit_persona"));
            usuario.setPuesto(rs.getString("puesto"));
            usuario.setIdRol(rs.getInt("id_rol"));
            usuario.setEstado(rs.getString("estado"));
            usuario.setCorreo(rs.getString("correo"));
        }
    } catch (SQLException e) {
        e.printStackTrace(); // Imprimir el stack trace para depurar
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
    return usuario; // Retornar el usuario encontrado o null si no se encontró
}

    //MODIFICADO
    @Override
    public List listar() {
        ArrayList<Users> list2 = new ArrayList<>();
        String sql = "select * from usuarios u join roles r on u.id_rol = r.id_rol";
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users user = new Users();
                user.setIdusuario(rs.getInt("id_usuario"));
                user.setPrimerNombre(rs.getString("primer_nombre"));
                user.setSegundoNombre(rs.getString("segundo_nombre"));
                user.setPrimerApellido(rs.getString("primer_apellido"));
                user.setSegundoApellido(rs.getString("segundo_apellido"));
                user.setLogin(rs.getString("login"));
                user.setContrasenia(rs.getString("contrasenia"));
                user.setNitpersona(rs.getString("nit_persona"));
                user.setPuesto(rs.getString("puesto"));
                user.setIdRol(rs.getInt("id_rol"));
                user.setNombreRol(rs.getString("nombre_rol"));
                user.setCorreo(rs.getString("correo"));
                user.setEstado(rs.getString("estado"));
                list2.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list2;
    }

    //MODIFICADO
    @Override
    public Users list(int idusuario) {
        String sql = " select * from usuarios where id_usuario=" + idusuario;
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                u.setIdusuario(rs.getInt("id_usuario"));
                u.setPrimerNombre(rs.getString("primer_nombre"));
                u.setSegundoNombre(rs.getString("segundo_nombre"));
                u.setPrimerApellido(rs.getString("primer_apellido"));
                u.setSegundoApellido(rs.getString("segundo_apellido"));
                u.setLogin(rs.getString("login"));
                u.setContrasenia(rs.getString("contrasenia"));
                u.setNitpersona(rs.getString("nit_persona"));
                u.setPuesto(rs.getString("puesto"));
                u.setIdRol(rs.getInt("id_rol"));
                u.setCorreo(rs.getString("correo"));
                u.setEstado(rs.getString("estado"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return u;
    }

public boolean add(Users user) { 
    String sql = "INSERT INTO usuarios(primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, login, contrasenia, nit_persona, puesto, id_rol, estado, correo, motivo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement ps = null;

    try {
        con = conexion.getConnection();
        ps = con.prepareStatement(sql);

        // Asignar valores a los parámetros
        ps.setString(1, user.getPrimerNombre());
        ps.setString(2, user.getSegundoNombre());
        ps.setString(3, user.getPrimerApellido());
        ps.setString(4, user.getSegundoApellido());
        ps.setString(5, user.getLogin());
        ps.setString(6, user.getContrasenia());
        ps.setString(7, user.getNitpersona());
        ps.setString(8, user.getPuesto());
        ps.setInt(9, user.getIdRol());
        ps.setString(10, user.getEstado());
        ps.setString(11, user.getCorreo());
        ps.setString(12, user.getMotivo());

        int rowsAffected = ps.executeUpdate();
        return rowsAffected > 0;

    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    } finally {
        // Cerrar PreparedStatement y Connection
        try {
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}


    //MODIFICADO
    @Override
    public boolean edit(Users user) {
        String sql = "update usuarios set primer_nombre = '" + user.getPrimerNombre() + "', segundo_nombre = '" + user.getSegundoNombre() + "', primer_apellido = '" + user.getPrimerApellido() + "', segundo_apellido = '" + user.getSegundoApellido() + "', contrasenia = '" + user.getContrasenia() + "', nit_persona = '" + user.getNitpersona() + "', puesto = '" + user.getPuesto() + "', id_rol = '" + user.getIdRol() + "', estado = '" + user.getEstado() + "' where id_usuario=" + user.getIdusuario();
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //MODIFICADO
    @Override
    public boolean eliminar(int idusuario) {
        String sql = "delete from usuarios where id_usuario=" + idusuario;
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Cerrar el ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public List<Roles> listaRoles() {
        List<Roles> lista = new ArrayList<>();
        String sql = "SELECT id_rol, nombre_rol FROM roles";
        Conexion cn = new Conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Roles rol = new Roles();
                rol.setIdRol(rs.getInt("id_rol"));  // Asignar el id del rol
                rol.setNombreRol(rs.getString("nombre_rol"));  // Asignar el nombre del rol
                lista.add(rol);  // Añadir el rol a la lista
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar el ResultSet, PreparedStatement y Connection
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return lista;  // Devolver la lista de roles
    }
}
