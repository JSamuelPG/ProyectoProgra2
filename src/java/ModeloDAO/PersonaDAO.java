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

public class PersonaDAO implements CRUD{
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Users u=new Users();
    
    private Conexion conexion;

    public PersonaDAO() {
        conexion = new Conexion();
    }
    
    //MODIFICADO
    @Override
    public List listar(){
    ArrayList<Users>list2=new ArrayList<>();
    String sql = "select * from usuarios u join roles r on u.id_rol = r.id_rol";
    try{
        con=cn.getConnection();
        ps = con.prepareStatement(sql);
        rs=ps.executeQuery();
        while(rs.next()){
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
            user.setEstado(rs.getString("estado"));
            list2.add(user);
        }
        
    }catch(Exception e){
        }
        return list2;
    }
    
    //MODIFICADO
    @Override
    public Users list(int idusuario){
        String sql =" select * from usuarios where id_usuario="+idusuario;
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
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
                u.setEstado(rs.getString("estado"));
            }
        }catch(Exception e){
            
        }return u;
    }
    //MODIFICADO
    @Override
    public boolean add(Users user){
        String sql = "insert into usuarios(primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,contrasenia, nit_persona, puesto,id_roles,estado) values('"+user.getPrimerNombre()+"','"+user.getSegundoNombre()+"','"+user.getPrimerApellido()+"','"+user.getSegundoApellido()+"','"+user.getContrasenia()+"','"+user.getNitpersona()+"','"+user.getPuesto()+"','"+user.getIdRol()+"','"+user.getEstado()+"')";
            try{
                con=cn.getConnection();
                ps=con.prepareStatement(sql);
                ps.executeUpdate();
            }catch(Exception e){
            }
            return false;
    }
    //MODIFICADO
    @Override
    public boolean edit(Users user){
        String sql = "update usuarios set primer_nombre = '"+user.getPrimerNombre()+"', segundo_nombre = '"+user.getSegundoNombre()+"', primer_apellido = '"+user.getPrimerApellido()+"', segundo_apellido = '"+user.getSegundoApellido()+"', contrasenia = '"+user.getContrasenia()+"', nit_persona = '"+user.getNitpersona()+"', puesto = '"+user.getPuesto()+"', id_rol = '"+user.getIdRol()+"', estado = '"+user.getEstado()+"' where id_usuario="+user.getIdusuario();
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){ 
        }
        return false;
    }
    //MODIFICADO
    @Override
    public boolean eliminar(int idusuario){
        String sql = "delete from usuarios where id_usuario="+idusuario;
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
        }
        return false;
    }
    
        public List<Roles> listaRoles() {
            List<Roles> lista = new ArrayList<>();
            String sql = "SELECT id_rol, nombre_rol FROM roles";  // Consulta para obtener todos los roles
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
            }
            return lista;  // Devolver la lista de roles
        }  
}