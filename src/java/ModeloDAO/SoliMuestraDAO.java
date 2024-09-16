package ModeloDAO;

import Config.Conexion;
import Intefaces.CRUDSM;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Modelo.SoliMuestra;
import java.util.ArrayList;
import java.util.List;

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
    @Override
    public List listarR(){
        ArrayList<SoliMuestra>listR2 = new ArrayList<>();
        String sql = "select * from registro_solicitudmuestra";
        
        try{
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
                sm.setDescripcionProducto(rs.getString("descripcion_Producto"));
                listR2.add(sm);
            }
        }catch(Exception e){
    }   
        return listR2;
    }
    @Override
    public SoliMuestra listR(int idSolicitud){
        String sql =" select * from registro_solicitudmuestra where id_Solicitud="+idSolicitud;
        try{
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
                s.setDescripcionProducto(rs.getString("descripcion_Producto"));
            }
        }catch(Exception e){
            
        }return s;
    }
    
    
    @Override
    public boolean addR(SoliMuestra smu){
        String sql = "insert into registro_solicitudmuestra(tipo_Solicitud, tipo_Entidad, fecha_Solicitud, tipode_Documento,no_Dedocumento,nit_Proveedor, nombre_Proveedor, correo_Proveedor,correo_Solicitante,direccion_Proveedor,telefono_Proveedor,nit_Solicitante,nombre_Solicitante, no_Muestra, descripcion_Producto) values('"+smu.getIdSolicitud()+"','"+smu.getTipoSolicitud()+"','"+smu.getTipodeDocumento()+"','"+smu.getNoDedocumento()+"','"+smu.getNitProveedor()+"','"+smu.getNombreProveedor()+"','"+smu.getCorreoProveedor()+"','"+smu.getCorreoSolicitante()+"','"+smu.getDireccionProveedor()+"','"+smu.getTelefonoProveedor()+"','"+smu.getNitSolicitante()+"','"+smu.getNombreSolicitante()+"','"+smu.getNoMuestra()+"','"+smu.getDescripcionProducto()+"')";
            try{
                con=cn.getConnection();
                ps=con.prepareStatement(sql);
                ps.executeUpdate();
            }catch(Exception e){
            }
            return false;
    }
    @Override
        public boolean editR(SoliMuestra smu){
        String sql = "update registro_solicitudmuestra set tipo_Solicitud = '"+smu.getTipoSolicitud()+"', tipo_Entidad = '"+smu.getTipoEntidad()+"', fecha_Solicitud = '"+smu.getFechaSolicitud()+"', tipode_Documento = '"+smu.getTipodeDocumento()+"',no_Dedocumento = '"+smu.getNoDedocumento()+"',nit_Proveedor = '"+smu.getNitProveedor()+"', nombre_Proveedor = '"+smu.getNombreProveedor()+"', correo_Proveedor = '"+smu.getCorreoProveedor()+"',correo_Solicitante = '"+smu.getCorreoSolicitante()+"',direccion_Proveedor = '"+smu.getDireccionProveedor()+"',telefono_Proveedor = '"+smu.getTelefonoProveedor()+"',nit_Solicitante = '"+smu.getNitSolicitante()+"',nombre_Solicitante = '"+smu.getNombreSolicitante()+"', no_Muestra = '"+smu.getNoMuestra()+"', descripcion_Producto = '"+smu.getDescripcionProducto()+"' where id_Solicitud="+smu.getIdSolicitud();
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
    public boolean eliminarR(int idSolicitud){
        String sql = "delete from registro_solicitudmuestra where id_Solicitud="+idSolicitud;
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(Exception e){
        }
        return false;
    }    
}
