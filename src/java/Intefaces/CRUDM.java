
package Intefaces;

import Modelo.SoliMuestra;
import java.util.List;


public interface CRUDM {
    public List listarR();
    public SoliMuestra list(int idSolicitud);
    public boolean add(SoliMuestra smu);
    public boolean edit(SoliMuestra smu);
    public boolean eliminar(int idSolicitud);
}
