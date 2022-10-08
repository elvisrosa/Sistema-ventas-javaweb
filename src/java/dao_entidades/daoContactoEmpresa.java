
package dao_entidades;

import entidades_basededatos.Acercade;
import java.util.List;


public interface daoContactoEmpresa {
    public void agregarDato(Acercade e);
    public void EliminarDato(Acercade e);
    public void editarDato(Acercade e);
    public List<Acercade> listar();
}
