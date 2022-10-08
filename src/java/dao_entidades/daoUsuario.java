
package dao_entidades;

import entidades_basededatos.Usuario;
import java.util.List;


public interface daoUsuario {
    
         Usuario iniciarSesion(Usuario u);
         public void crearUsuario(Usuario u);
         public void actualizarUsuario(Usuario u);
         public void eliminarUsuario(Usuario  u);
         
         List<Usuario> lstUsuarios();
        
   
}
