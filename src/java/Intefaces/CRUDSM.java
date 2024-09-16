
package Intefaces;

import Modelo.SoliMuestra;
import java.util.List;

/**
 *
 * @author Samuel
 */
public interface CRUDSM {
        
    public List listarR();
    public SoliMuestra listR(int idSolicitud);
    public boolean addR(SoliMuestra smu);
    public boolean editR(SoliMuestra smu);
    public boolean eliminarR(int idSolicitud);

}
