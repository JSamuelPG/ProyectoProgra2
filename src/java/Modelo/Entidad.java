
package Modelo;

/**
 *
 * @author Samuel
 */
public class Entidad {
    public int entidadId;
    public String entidadNit;
    public String entidadNombre;
    public String entidadTipo;
    public String entidadCorreo;
    public String entidadDireccion;
    public String entidadTelefono;
    
    public Entidad(){}

    public Entidad(int entidadId, String entidadNit, String entidadNombre, String entidadTipo, String entidadCorreo, String entidadDireccion, String entidadTelefono) {
        this.entidadId = entidadId;
        this.entidadNit = entidadNit;
        this.entidadNombre = entidadNombre;
        this.entidadTipo = entidadTipo;
        this.entidadCorreo = entidadCorreo;
        this.entidadDireccion = entidadDireccion;
        this.entidadTelefono = entidadTelefono;
    }

    public int getEntidadId() {
        return entidadId;
    }

    public void setEntidadId(int entidadId) {
        this.entidadId = entidadId;
    }

    public String getEntidadNit() {
        return entidadNit;
    }

    public void setEntidadNit(String entidadNit) {
        this.entidadNit = entidadNit;
    }

    public String getEntidadNombre() {
        return entidadNombre;
    }

    public void setEntidadNombre(String entidadNombre) {
        this.entidadNombre = entidadNombre;
    }

    public String getEntidadTipo() {
        return entidadTipo;
    }

    public void setEntidadTipo(String entidadTipo) {
        this.entidadTipo = entidadTipo;
    }

    public String getEntidadCorreo() {
        return entidadCorreo;
    }

    public void setEntidadCorreo(String entidadCorreo) {
        this.entidadCorreo = entidadCorreo;
    }

    public String getEntidadDireccion() {
        return entidadDireccion;
    }

    public void setEntidadDireccion(String entidadDireccion) {
        this.entidadDireccion = entidadDireccion;
    }

    public String getEntidadTelefono() {
        return entidadTelefono;
    }

    public void setEntidadTelefono(String entidadTelefono) {
        this.entidadTelefono = entidadTelefono;
    }
}
