
package Modelo;

/**
 *
 * @author Samuel
 */
public class Solicitantes {
    
    public int solId;
    public String solNit;
    public String solNombre;
    public String solCorreo;
    
    public Solicitantes(){}

    public Solicitantes(int solId, String solNit, String solNombre, String solCorreo) {
        this.solId = solId;
        this.solNit = solNit;
        this.solNombre = solNombre;
        this.solCorreo = solCorreo;
    }

    public int getSolId() {
        return solId;
    }

    public void setSolId(int solId) {
        this.solId = solId;
    }

    public String getSolNit() {
        return solNit;
    }

    public void setSolNit(String solNit) {
        this.solNit = solNit;
    }

    public String getSolNombre() {
        return solNombre;
    }

    public void setSolNombre(String solNombre) {
        this.solNombre = solNombre;
    }

    public String getSolCorreo() {
        return solCorreo;
    }

    public void setSolCorreo(String solCorreo) {
        this.solCorreo = solCorreo;
    }
       
}