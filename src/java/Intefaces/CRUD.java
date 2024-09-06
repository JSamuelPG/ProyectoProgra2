
package Intefaces;

import Modelo.Users;
import java.util.List;


public interface CRUD{
    public List listar();
    public Users list(int idusuario);
    public boolean add(Users user);
    public boolean edit(Users user);
    public boolean eliminar(int idusuario);
}