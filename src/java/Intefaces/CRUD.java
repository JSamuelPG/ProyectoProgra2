
package Intefaces;

import Modelo.Users;
import Modelo.Persona;
import java.util.List;

/*
public interface CRUD {
    public List listar();
    public Persona list(int id);
    public boolean add(Persona per);
    public boolean edit(Persona per);
    public boolean eliminar(int id);
}*/

public interface CRUD{
    public List listar();
    public Users list(int idusuario);
    public boolean add(Users user);
    public boolean edit(Users user);
    public boolean eliminar(int idusuario);
}