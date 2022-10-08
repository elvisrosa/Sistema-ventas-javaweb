package daoImplements_entidades;

import dao_entidades.daoUsuario;
import entidades_basededatos.Usuario;
import java.util.ArrayList;
import java.util.List;
import persistencia.NewHibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class daoImplements_usuario implements daoUsuario {

    private Session sesion = null;
    private Transaction transaccion;

    @Override
    public Usuario iniciarSesion(Usuario u) throws ExceptionInInitializerError {
        Usuario usuario = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            String hql = "From Usuario where email = '" + u.getEmail() + "' AND contrasenna = '" + u.getContrasenna() + "'";
            Query q = sesion.createQuery(hql);
            if (!q.list().isEmpty()) {
                usuario = (Usuario) q.list().get(0);
            }
            //sesion.close();
        } catch (HibernateException e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
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
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }

    }

    @Override
    public void actualizarUsuario(Usuario u) {
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            transaccion = sesion.beginTransaction();
            sesion.update(u);
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            e.printStackTrace();
            throw e;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    @Override
    public void eliminarUsuario(Usuario u) {
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            transaccion = sesion.beginTransaction();
            sesion.delete(u);
            transaccion.commit();
        } catch (HibernateException e) {
            transaccion.rollback();
            throw e;
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    @Override
    public List<Usuario> lstUsuarios() {
        List<Usuario> listaUsuarios = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            String hql = "From Usuario";
            Query q = sesion.createQuery(hql);
            listaUsuarios = q.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            System.out.println("-------------Error al mostrar usuario-----------" + e.getMessage());
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
        return listaUsuarios;
    }
//
//        public static void main(String[] args) {
//            daoImplements_usuario crud = new daoImplements_usuario();
//           crud.lstUsuarios();
//        }
}
