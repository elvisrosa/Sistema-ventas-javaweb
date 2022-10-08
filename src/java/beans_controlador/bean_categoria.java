/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_controlador;

import daoImplements_entidades.daoImplement_categoria;
import dao_entidades.daoCategoria;
import entidades_basededatos.Categoria;
import java.io.Serializable;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Pc
 */
@Named(value = "bn_categoria")
@SessionScoped
public class bean_categoria implements Serializable {

    private Categoria categoria;
    private List<Categoria> lstCstegoria;
    daoCategoria daoCategoria;

    public bean_categoria() {
        categoria = new Categoria();
    }

   
    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public List<Categoria> getLstCstegoria() {
        daoCategoria = new daoImplement_categoria();
        return lstCstegoria = daoCategoria.listCategoria();
    }

    public void setLstCstegoria(List<Categoria> lstCstegoria) {
        this.lstCstegoria = lstCstegoria;
    }

    //Metodo de categoria de producto
    public void agregarCategoria() {
        try {
            daoCategoria = new daoImplement_categoria();
            daoCategoria.agregarCategoria(categoria);
            categoria = new Categoria();
            System.out.println("categoria agregada");
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }

    }

    public void elimianrCategoria() {
        try {
            daoCategoria = new daoImplement_categoria();
            daoCategoria.eliminarCategoria(categoria);
            categoria = new Categoria();
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }

    }

    public void editarCategoria() {
        try {
            daoCategoria = new daoImplement_categoria();
            daoCategoria.editarCategoria(categoria);
            categoria = new Categoria();
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
        }

    }

    

}
