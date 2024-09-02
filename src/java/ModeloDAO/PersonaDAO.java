
package ModeloDAO;

import Modelo.Users;

import Config.Conexion;
import Intefaces.CRUD;
import Modelo.Persona;
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
    /*Persona p=new Persona();*/
    Users u=new Users();
    
    private Conexion conexion;

    public PersonaDAO() {
        conexion = new Conexion();
    }

    /*public boolean validateUser(String username, String password) {
        String query = "SELECT * FROM usuarios WHERE username = ? AND password = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/
    
    public boolean validateUser2(String login, String contrasenia) {
        String query = "SELECT * FROM usuarios WHERE login = ? AND contrasenia = ?";
        try (Connection con = conexion.getConnection();
             PreparedStatement stmt = con.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, contrasenia);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    //MODIFICADO
    @Override
    public List listar2(){
    ArrayList<Users>list2=new ArrayList<>();
    String sql = "select * from usuarios";
    try{
        con=cn.getConnection();
        ps = con.prepareStatement(sql);
        rs=ps.executeQuery();
        while(rs.next()){
            Users user = new Users();
            user.setIdusuario(rs.getInt("id_usuario"));
            user.setPrimerNombre(rs.getString("primer_nombre"));
            user.setPrimerNombre(rs.getString("segundo_nombre"));
            user.setPrimerNombre(rs.getString("primer_apellido"));
            user.setPrimerNombre(rs.getString("segundo_apellido"));
            user.setPrimerNombre(rs.getString("login"));
            user.setPrimerNombre(rs.getString("contrasenia"));
            user.setPrimerNombre(rs.getString("nit_persona"));
            user.setPrimerNombre(rs.getString("puesto"));
            user.setIdusuario(rs.getInt("roles"));
            list2.add(user);
        }
        
    }catch(Exception e){
        }
        return list2;
    }
    
    //MODIFICADO
    @Override
    public Users list2(int idusuario){
        String sql =" select * from usuarios where id_usuario="+idusuario;
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                u.setIdusuario(rs.getInt("id_usuario"));
                u.setPrimerNombre(rs.getString("primer_nombre"));
                u.setPrimerNombre(rs.getString("segundo_nombre"));
                u.setPrimerNombre(rs.getString("primer_apellido"));
                u.setPrimerNombre(rs.getString("segundo_apellido"));
                u.setPrimerNombre(rs.getString("login"));
                u.setPrimerNombre(rs.getString("contrasenia"));
                u.setPrimerNombre(rs.getString("nit_persona"));
                u.setPrimerNombre(rs.getString("puesto"));
                u.setIdusuario(rs.getInt("roles"));
            }
        }catch(Exception e){
            
        }return u;
    }
    //MODIFICADO
    
    public boolean add2(Users user){
        String sql = "insert into usuarios(primer_nombre, segundo_nombre, primer_apellido, segundo_apellido,contrasenia, nit_persona, puesto,roles) values('"+user.getPrimerNombre()+"','"+user.getSegundoNombre()+"','"+user.getPrimerApellido()+"','"+user.getSegundoApellido()+"','"+user.getContrasenia()+"','"+user.getNitpersona()+"','"+user.getPuesto()+"','"+user.getRoles()+"')";
            try{
                con=cn.getConnection();
                ps=con.prepareStatement(sql);
                ps.executeUpdate();
            }catch(Exception e){
            }
            return false;
    }
    //MODIFICADO
    
    public boolean edit2(Users user){
        String sql = "update usuarios set primer_nombre = '"+user.getPrimerNombre()+"', segundo_nombre = '"+user.getSegundoNombre()+"', primer_apellido = '"+user.getPrimerApellido()+"', segundo_apellido = '"+user.getSegundoApellido()+"', contrasenia = '"+user.getContrasenia()+"', nit_persona = '"+user.getNitpersona()+"', puesto = '"+user.getPuesto()+"', roles = '"+user.getRoles()+"'";
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
    public boolean eliminar2(int idusuario){
        String sql = "delete from usuarios where id_usuario";
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
        }
        return false;
    }
    
    /*
    @Override
    public List listar() {
        ArrayList<Persona>list=new ArrayList<>();
        String sql="select * from persona";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Persona per=new Persona();
                per.setId(rs.getInt("Id"));
                per.setDpi(rs.getString("DPI"));
                per.setNom(rs.getString("Nombres"));
                list.add(per);
            }
        } catch (Exception e) {
        }
        return list;
    }

    @Override
    public Persona list(int id) {
        String sql="select * from persona where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){                
                p.setId(rs.getInt("Id"));
                p.setDpi(rs.getString("DPI"));
                p.setNom(rs.getString("Nombres"));
                
            }
        } catch (Exception e) {
        }
        return p;
    }

    @Override
    public boolean add(Persona per) {
       String sql="insert into persona(DPI, Nombres)values('"+per.getDpi()+"','"+per.getNom()+"')";
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
       return false;
    }

    @Override
    public boolean edit(Persona per) {
        String sql="update persona set DPI='"+per.getDpi()+"',Nombres='"+per.getNom()+"'where Id="+per.getId();
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql="delete from persona where Id="+id;
        try {
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
        return false;
    }*/
    
}
