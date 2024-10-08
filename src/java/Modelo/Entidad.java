
package Modelo;

/**
 *
 * @author Samuel
 */
public class Entidad {
    int entidadId;
    String entidadNit;
    String entidadNombre;
    String entidadTipo;
    
    public Entidad(){}

    public Entidad(int entidadId, String entidadNit, String entidadNombre, String entidadTipo) {
        this.entidadId = entidadId;
        this.entidadNit = entidadNit;
        this.entidadNombre = entidadNombre;
        this.entidadTipo = entidadTipo;
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
}
