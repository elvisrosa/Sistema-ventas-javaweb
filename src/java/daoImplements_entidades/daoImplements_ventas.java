/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImplements_entidades;

import entidades_basededatos.Detalleventa;
import entidades_basededatos.Venta;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistencia.NewHibernateUtil;

public class daoImplements_ventas {

    public void regitrar(Venta venta) {
        Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(venta);

            sesion.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }
    }

    public void regitrardtVenta(Detalleventa ventadet) {
        Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(ventadet);
            sesion.getTransaction().commit();

        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            sesion.getTransaction().rollback();
        } finally {
            if (sesion != null) {
                sesion.close();
            }
        }

    }

    public List<Venta> listarVentas() {
        List<Venta> lista = null;
        Session sesion = null;

        sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        try {
            String hql = "From Venta c inner join fetch c.producto";
            lista = sesion.createQuery(hql).list();
            t.commit();
            sesion.close();

        } catch (HibernateException e) {
            System.out.println("Error al mostrar" + e.getMessage());
            t.rollback();
        } 
        
        return lista;
    }

    public List<Venta> listar(int u) {
        List<Venta> lista = null;
        Session sesion = null;

        sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
        String hql = "From Venta where idusuario='" + u + "'";
        try {
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
