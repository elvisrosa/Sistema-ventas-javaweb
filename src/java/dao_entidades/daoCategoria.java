
package dao_entidades;

import entidades_basededatos.Categoria;
import java.util.List;


public interface daoCategoria {
    
    public void agregarCategoria(Categoria c);
    public void editarCategoria(Categoria c);
    public void eliminarCategoria(Categoria c);
    public List<Categoria> listCategoria();
    
}
