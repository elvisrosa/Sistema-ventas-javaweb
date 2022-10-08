/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao_entidades;

import entidades_basededatos.Producto;
import java.util.List;

/**
 *
 * @author Pc
 */
public interface daoProducto {
    
    public void agregarProdcto(Producto p);
    public void eliminarProdicto(Producto p);
    public void editarProducto(Producto p);
    public List<Producto> listar();
    public List<Producto> filtrarPorCategoria(int p);
}
