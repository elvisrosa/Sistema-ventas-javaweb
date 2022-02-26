
package dao_entidades;

import entidades_database.Usuario;


public interface daoUsuario {
    
         Usuario iniciarSesion(Usuario u);
         public void crearUsuario(Usuario u);
         public void actualizarUsuario(Usuario u);
         public void eliminarUsuario(Usuario  u);
        
   
}
