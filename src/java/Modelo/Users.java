
package Modelo;

/**
 *
 * @author Samuel
 */
public class Users {
    
    int idusuario;
    String primerNombre;
    String segundoNombre;
    String primerApellido;
    String segundoApellido;
    String login;
    String contrasenia;
    String nitpersona;
    String puesto;
        String nombreRol;
    int idRol;
    String estado;
    
    public Users(){};

    public Users(int idusuario, String primerNombre, String segundoNombre, String primerApellido, String segundoApellido, String login, String contrasenia, String nitpersona, String puesto, String nombreRol, int idRol, String estado) {
        this.idusuario = idusuario;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.login = login;
        this.contrasenia = contrasenia;
        this.nitpersona = nitpersona;
        this.puesto = puesto;
                this.nombreRol = nombreRol;
        this.idRol = idRol;
        this.estado = estado;
    }

    
    
    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getNitpersona() {
        return nitpersona;
    }

    public void setNitpersona(String nitpersona) {
        this.nitpersona = nitpersona;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }
    
        public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    
}
