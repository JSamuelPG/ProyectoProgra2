
package Modelo;

import java.sql.Date;

public class SoliMuestra {
    
    int idSolicitud;
    String tipoSolicitud;
    String tipoEntidad;
    Date fechaSolicitud;
    String tipodeDocumento; 
    String noDedocumento;
    String nitProveedor;
    String nombreProveedor;
    String correoProveedor;
    String correoSolicitante; 
    String direccionProveedor; 
    String telefonoProveedor;
    String nitSolicitante;
    String nombreSolicitante; 
    String noMuestra;
    String descripcionProducto;

    public SoliMuestra(){}
    
    public SoliMuestra(int idSolicitud, String tipoSolicitud, String tipoEntidad, Date fechaSolicitud, String tipodeDocumento, String noDedocumento, String nitProveedor, String nombreProveedor, String correoProveedor, String correoSolicitante, String direccionProveedor, String telefonoProveedor, String nitSolicitante, String nombreSolicitante, String noMuestra, String descripcionProducto) {
        this.idSolicitud = idSolicitud;
        this.tipoSolicitud = tipoSolicitud;
        this.tipoEntidad = tipoEntidad;
        this.fechaSolicitud = fechaSolicitud;
        this.tipodeDocumento = tipodeDocumento;
        this.noDedocumento = noDedocumento;
        this.nitProveedor = nitProveedor;
        this.nombreProveedor = nombreProveedor;
        this.correoProveedor = correoProveedor;
        this.correoSolicitante = correoSolicitante;
        this.direccionProveedor = direccionProveedor;
        this.telefonoProveedor = telefonoProveedor;
        this.nitSolicitante = nitSolicitante;
        this.nombreSolicitante = nombreSolicitante;
        this.noMuestra = noMuestra;
        this.descripcionProducto = descripcionProducto;
    }

    public int getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(int idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }

    public String getTipoEntidad() {
        return tipoEntidad;
    }

    public void setTipoEntidad(String tipoEntidad) {
        this.tipoEntidad = tipoEntidad;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getTipodeDocumento() {
        return tipodeDocumento;
    }

    public void setTipodeDocumento(String tipodeDocumento) {
        this.tipodeDocumento = tipodeDocumento;
    }

    public String getNoDedocumento() {
        return noDedocumento;
    }

    public void setNoDedocumento(String noDedocumento) {
        this.noDedocumento = noDedocumento;
    }

    public String getNitProveedor() {
        return nitProveedor;
    }

    public void setNitProveedor(String nitProveedor) {
        this.nitProveedor = nitProveedor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }

    public String getCorreoProveedor() {
        return correoProveedor;
    }

    public void setCorreoProveedor(String correoProveedor) {
        this.correoProveedor = correoProveedor;
    }

    public String getCorreoSolicitante() {
        return correoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
        this.correoSolicitante = correoSolicitante;
    }

    public String getDireccionProveedor() {
        return direccionProveedor;
    }

    public void setDireccionProveedor(String direccionProveedor) {
        this.direccionProveedor = direccionProveedor;
    }

    public String getTelefonoProveedor() {
        return telefonoProveedor;
    }

    public void setTelefonoProveedor(String telefonoProveedor) {
        this.telefonoProveedor = telefonoProveedor;
    }

    public String getNitSolicitante() {
        return nitSolicitante;
    }

    public void setNitSolicitante(String nitSolicitante) {
        this.nitSolicitante = nitSolicitante;
    }

    public String getNombreSolicitante() {
        return nombreSolicitante;
    }

    public void setNombreSolicitante(String nombreSolicitante) {
        this.nombreSolicitante = nombreSolicitante;
    }

    public String getNoMuestra() {
        return noMuestra;
    }

    public void setNoMuestra(String noMuestra) {
        this.noMuestra = noMuestra;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    
    
    
}
