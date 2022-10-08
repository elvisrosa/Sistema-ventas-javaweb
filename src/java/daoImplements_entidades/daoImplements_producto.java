package daoImplements_entidades;

import dao_entidades.daoProducto;
import entidades_basededatos.Producto;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistencia.NewHibernateUtil;

public class daoImplements_producto implements daoProducto {

    private Transaction trans;

    @Override
    public void agregarProdcto(Producto p) {
        Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(p);
            sesion.getTransaction().commit();
        } catch (HibernateException e) {
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }

    }

    @Override
    public void eliminarProdicto(Producto po) {
        Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(po);
            sesion.getTransaction().commit();

        } catch (HibernateException e) {
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    @Override
    public void editarProducto(Producto p) {
        Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            //sesion.clear();
            sesion.update(p);
            sesion.getTransaction().commit();
        } catch (HibernateException e) {
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    @Override
    public List<Producto> listar() {
        List<Producto> lista = null;
        Session sesion = null;
       
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            Transaction t = sesion.beginTransaction();
            String hql = " From Producto c inner join fetch c.categoria order by productoid";
           
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

    @Override
    public List<Producto> filtrarPorCategoria(int p) {
        List<Producto> listarPorFiltro=null;
        Session sesion = null;
        sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "Select * from Producto where codigocategoria = '" + p + "'";
        try {
            listarPorFiltro=sesion.createQuery(hql).list();
            t.commit();
            sesion.close();
            
        } catch (HibernateException e) {
            System.out.println("error al filtrar los datos" +e.getMessage());
            t.rollback();
        }
        return listarPorFiltro;
    }

}
