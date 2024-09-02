
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
    public List listar2();
    public Users list2(int idusuario);
    public boolean add2(Users user);
    public boolean edit2(Users user);
    public boolean eliminar2(int idusuario);
}