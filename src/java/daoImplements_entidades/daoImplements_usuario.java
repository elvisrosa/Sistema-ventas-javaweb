
package daoImplements_entidades;

import dao_entidades.daoUsuario;
import entidades_database.Usuario;
import persistencia.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class daoImplements_usuario implements daoUsuario{

    private Session sesion=null;
    private Transaction transaccion;
    
    @Override
    public Usuario iniciarSesion(Usuario u) throws ExceptionInInitializerError{
        Usuario usuario = null;
        try {
            sesion =  NewHibernateUtil.getSessionFactory().openSession();
            String hql = "From Usuario where email = '" +u.getEmail()+ "' AND contrasenna = '" +u.getContrasenna()+ "'";            
               Query q = sesion.createQuery(hql);                                  
                if(!q.list().isEmpty()){
                    usuario = (Usuario) q.list().get(0);                    
                }           
        } catch (HibernateException e) {
            throw e;
        }
        return usuario;
    }     

    @Override
    public void crearUsuario(Usuario u) {
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            transaccion = sesion.beginTransaction();
            sesion.save(u);
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            throw e;
        }
        
    }

    @Override
    public void actualizarUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminarUsuario(Usuario u) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    }

    
    

