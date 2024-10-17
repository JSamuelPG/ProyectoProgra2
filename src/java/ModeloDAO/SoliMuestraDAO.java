package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUDSM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.SoliMuestra;
import java.util.ArrayList;
import java.util.List;
import Modelo.Solicitantes;
import Modelo.Users;
import java.sql.*;

public class SoliMuestraDAO implements CRUDSM {
    Conexion cn=new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    SoliMuestra s = new SoliMuestra();
    
    private Conexion conexion;
    
    public SoliMuestraDAO(){
        conexion = new Conexion();
    }
    
    public Solicitantes obtenerSolicitante(String nitSolicitante) {
        Solicitantes soli = null;
        String sql = "SELECT * FROM solicitantes WHERE s_Nit = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection(); // Obtener la conexión
            ps = con.prepareStatement(sql);
            ps.setString(1, nitSolicitante);
            System.out.println("NIT buscado: " + nitSolicitante);

            rs = ps.executeQuery(); // Ejecutar la consulta

            if (rs.next()) {
                soli = new Solicitantes();
                soli.setSolId(rs.getInt("s_Id"));
                soli.setSolNit(rs.getString("s_Nit"));
                soli.setSolNombre(rs.getString("s_Nombre"));
                soli.setSolCorreo(rs.getString("s_Correo"));
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }  finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return soli;
    }  
    
    public String obtenCorreoAnalista(int idUsuario){
        String correo ="";
        String sql ="select correo from usuarios where id_usuario = ?";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idUsuario);
            rs = ps.executeQuery();
            if(rs.next()){
                correo = rs.getString("correo");
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return correo;  
    }
   
    public List<Users> obtenAnalista() {
        List<Users> listaUsuarios = new ArrayList<>();
        String sql = "SELECT * FROM usuarios WHERE id_rol = 2 AND estado = 'Activo'"; // id_rol 2 para Analista
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            Conexion cn = new Conexion();
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Users usuario = new Users();
                usuario.setIdusuario(rs.getInt("id_usuario"));
                usuario.setPrimerNombre(rs.getString("primer_nombre"));
                usuario.setCorreo(rs.getString("correo"));
                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaUsuarios;
    }


    public boolean existeNoMuestra(String noMuestra) {
    boolean existe = false;
    String query = "SELECT COUNT(*) FROM reg_solmuestra WHERE no_Muestra = ?";
    Connection con = null;
    PreparedStatement ps = null; 
    ResultSet rs = null; 
    
    try {
        Conexion cn = new Conexion();
        con = cn.getConnection(); // Obtiene la conexión
        ps = con.prepareStatement(query); // Prepara la consulta SQL
        ps.setString(1, noMuestra); // Establece el parámetro en la consulta
        rs = ps.executeQuery(); // Ejecuta la consulta
        
        if (rs.next() && rs.getInt(1) > 0) {
            existe = true; // El número de muestra ya está registrado
        }
    } catch (SQLException e) {
        e.printStackTrace(); 
    } finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    return existe; // Retorna el resultado de la verificación
}


    @Override
    public List listarR(){
        ArrayList<SoliMuestra>listR2 = new ArrayList<>();
        String sql = "select * from reg_solmuestra";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            Conexion cn = new Conexion();
            con=cn.getConnection();
            ps = con.prepareStatement(sql);
            rs=ps.executeQuery();
        
            while(rs.next()){
                SoliMuestra sm = new SoliMuestra();
                sm.setIdSolicitud(rs.getInt("id_Solicitud"));
                sm.setTipoSolicitud(rs.getString("tipo_Solicitud"));
                sm.setTipoEntidad(rs.getString("tipo_Entidad"));
                sm.setFechaSolicitud(rs.getDate("fecha_Solicitud"));
                sm.setTipodeDocumento(rs.getString("tipode_Documento"));
                sm.setNoDedocumento(rs.getString("no_Dedocumento"));
                sm.setNitProveedor(rs.getString("nit_Proveedor"));
                sm.setNombreProveedor(rs.getString("nombre_Proveedor"));
                sm.setCorreoProveedor(rs.getString("correo_Proveedor"));
                sm.setCorreoSolicitante(rs.getString("correo_Solicitante"));
                sm.setDireccionProveedor(rs.getString("direccion_Proveedor"));
                sm.setTelefonoProveedor(rs.getString("telefono_Proveedor"));
                sm.setNitSolicitante(rs.getString("nit_Solicitante"));
                sm.setNombreSolicitante(rs.getString("nombre_Solicitante"));
                sm.setNoMuestra(rs.getString("no_Muestra"));
                sm.setDescripcionProducto(rs.getString("descrip_Producto"));
                sm.setIdUsuario(rs.getInt("id_Usuario"));
                sm.setRegUsuario(rs.getString("Reg_Usuario"));
                
                listR2.add(sm);
            }
        }catch(Exception e){
    }   finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return listR2;
    }
    @Override
    public SoliMuestra listR(int idSolicitud){
        String sql =" select * from reg_solmuestra where id_Solicitud="+idSolicitud;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Conexion cn = new Conexion();
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                s.setIdSolicitud(rs.getInt("id_Solicitud"));
                s.setTipoSolicitud(rs.getString("tipo_Solicitud"));
                s.setTipoEntidad(rs.getString("tipo_Entidad"));
                s.setFechaSolicitud(rs.getDate("fecha_Solicitud"));
                s.setTipodeDocumento(rs.getString("tipode_Documento"));
                s.setNoDedocumento(rs.getString("no_Dedocumento"));
                s.setNitProveedor(rs.getString("nit_Proveedor"));
                s.setNombreProveedor(rs.getString("nombre_Proveedor"));
                s.setCorreoProveedor(rs.getString("correo_Proveedor"));
                s.setCorreoSolicitante(rs.getString("correo_Solicitante"));
                s.setDireccionProveedor(rs.getString("direccion_Proveedor"));
                s.setTelefonoProveedor(rs.getString("telefono_Proveedor"));
                s.setNitSolicitante(rs.getString("nit_Solicitante"));
                s.setNombreSolicitante(rs.getString("nombre_Solicitante"));
                s.setNoMuestra(rs.getString("no_Muestra"));
                s.setDescripcionProducto(rs.getString("descrip_Producto"));
                s.setIdUsuario(rs.getInt("id_Usuario"));
                s.setRegUsuario(rs.getString("Reg_Usuario"));
            }
        }catch(Exception e){
            
        }finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }return s;
    }
    
    @Override
    public boolean addR(SoliMuestra smu){
        String sql = "insert into reg_solmuestra(tipo_Solicitud, tipo_Entidad, fecha_Solicitud, tipode_Documento, no_Dedocumento, nit_Proveedor, nombre_Proveedor, correo_Proveedor, correo_Solicitante, direccion_Proveedor, telefono_Proveedor, nit_Solicitante, nombre_Solicitante, no_Muestra, descrip_Producto, id_Usuario, Reg_Usuario, estado) values('"+smu.getTipoSolicitud()+"','"+smu.getTipoEntidad()+"','"+smu.getFechaSolicitud()+"','"+smu.getTipodeDocumento()+"','"+smu.getNoDedocumento()+"','"+smu.getNitProveedor()+"','"+smu.getNombreProveedor()+"','"+smu.getCorreoProveedor()+"','"+smu.getCorreoSolicitante()+"','"+smu.getDireccionProveedor()+"','"+smu.getTelefonoProveedor()+"','"+smu.getNitSolicitante()+"','"+smu.getNombreSolicitante()+"','"+smu.getNoMuestra()+"','"+smu.getDescripcionProducto()+"','"+smu.getIdUsuario()+"','"+smu.getRegUsuario()+"','"+smu.getEstado()+"')";
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;    
        try{
            Conexion cn = new Conexion();
                con=cn.getConnection();
                ps=con.prepareStatement(sql);
                ps.executeUpdate();
            }catch(Exception e){
            }finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
            return false;
    }   
    @Override
        public boolean editR(SoliMuestra smu){
        String sql = "update reg_solmuestra set tipo_Solicitud = '"+smu.getTipoSolicitud()+"', tipo_Entidad = '"+smu.getTipoEntidad()+"', fecha_Solicitud = '"+smu.getFechaSolicitud()+"', tipode_Documento = '"+smu.getTipodeDocumento()+"',no_Dedocumento = '"+smu.getNoDedocumento()+"',nit_Proveedor = '"+smu.getNitProveedor()+"', nombre_Proveedor = '"+smu.getNombreProveedor()+"', correo_Proveedor = '"+smu.getCorreoProveedor()+"',correo_Solicitante = '"+smu.getCorreoSolicitante()+"',direccion_Proveedor = '"+smu.getDireccionProveedor()+"',telefono_Proveedor = '"+smu.getTelefonoProveedor()+"',nit_Solicitante = '"+smu.getNitSolicitante()+"',nombre_Solicitante = '"+smu.getNombreSolicitante()+"', no_Muestra = '"+smu.getNoMuestra()+"', descrip_Producto = '"+smu.getDescripcionProducto()+"',id_Usuario = '"+smu.getIdUsuario()+"',Reg_Usuario = '"+smu.getRegUsuario()+"' where id_Solicitud="+smu.getIdSolicitud();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Conexion cn = new Conexion();
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){ 
        }finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return false;
    }
    //MODIFICADO
    @Override
    public boolean eliminarR(int idSolicitud){
        String sql = "delete from reg_solmuestra where id_Solicitud="+idSolicitud;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            Conexion cn = new Conexion();
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
        }finally {
        try {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        return false;
    }
    
    
    
}
