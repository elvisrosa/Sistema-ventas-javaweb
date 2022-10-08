package daoImplements_entidades;

import dao_entidades.daoContactoEmpresa;
import entidades_basededatos.Acercade;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistencia.NewHibernateUtil;

public class daoImplements_acercadelaEmpresa implements daoContactoEmpresa {

    private Transaction trans;

    @Override
    public void agregarDato(Acercade e) {
        Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(e);
            sesion.getTransaction().commit();
        } catch (HibernateException ex) {
            System.out.println("error " + ex.getMessage());
            sesion.getTransaction().rollback();

        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    @Override
    public void EliminarDato(Acercade e) {
        Session sesion = null;

        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(e);
            sesion.getTransaction().commit();
        } catch (HibernateException ex) {
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    @Override
    public void editarDato(Acercade e) {
         Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(e);
            sesion.getTransaction().commit();
        } catch (HibernateException ex) {
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    @Override
    public List<Acercade> listar() {
         List<Acercade> lista = null;
        Session sesion = null;
       
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            Transaction t = sesion.beginTransaction();
            String hql = "From Acercade order by id";
            try{
                lista = sesion.createQuery(hql).list();
                t.commit();
                sesion.close();

        } catch (HibernateException e) {
            System.out.println("Error al mostrar" + e.getMessage());
            t.rollback();
        }

        return lista;
    }

}
