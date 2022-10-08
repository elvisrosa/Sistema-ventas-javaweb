/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daoImplements_entidades;

import dao_entidades.daoCategoria;
import entidades_basededatos.Categoria;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import persistencia.NewHibernateUtil;

/**
 *
 * @author Pc
 */
public class daoImplement_categoria implements daoCategoria{

    @Override
    public void agregarCategoria(Categoria c) {
        Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.save(c);
            sesion.getTransaction().commit();
        } catch (HibernateException e) {
            sesion.getTransaction().rollback();
        }finally{
            if(sesion!=null){
                sesion.close();
            }
        }
    }

    @Override
    public void editarCategoria(Categoria c) {
        Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.update(c);
            sesion.getTransaction().commit();
        } catch (HibernateException e) {
            sesion.getTransaction().rollback();
        }finally{
            if(sesion!=null){
                sesion.close();
            }
        }
    }

    @Override
    public void eliminarCategoria(Categoria c) {
       Session sesion = null;
        try {
            sesion = NewHibernateUtil.getSessionFactory().openSession();
            sesion.beginTransaction();
            sesion.delete(c);
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
    public List<Categoria> listCategoria() {
        Session sesion = null;
        List<Categoria> lstCategoria = null;
        sesion = NewHibernateUtil.getSessionFactory().openSession();
        Transaction t = sesion.beginTransaction();
         String hql ="From Categoria order by id";
        try {
            lstCategoria = sesion.createQuery(hql).list();
            t.commit();
            sesion.close();
           
        } catch (Exception e) {
            System.out.println("error al mostrar los datos"+e.getMessage());
            t.rollback();
        }
        return lstCategoria;
    }
    
}
