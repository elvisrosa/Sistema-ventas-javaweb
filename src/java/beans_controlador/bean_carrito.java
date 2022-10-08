/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_controlador;

import daoImplements_entidades.daoImplements_ventas;
import entidades_basededatos.Detalleventa;
import entidades_basededatos.Producto;
import entidades_basededatos.Usuario;
import entidades_basededatos.Venta;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named(value = "bean_carrito")
@SessionScoped
public class bean_carrito implements Serializable {

    private Producto p;
    private ArrayList<Venta> lstDetalleVenta = new ArrayList<Venta>();
    private int cantidad;
    private Venta venta;
    private Usuario usuario;
    private int totalRegistroCarrito;

    public bean_carrito() {
        p = new Producto();
        venta = new Venta();
    }

    public ArrayList<Venta> getLstDetalleVenta() {
        return lstDetalleVenta;
    }

    public void setLstDetalleVenta(ArrayList<Venta> lstDetalleVenta) {
        this.lstDetalleVenta = lstDetalleVenta;
    }
    
    

    public int getTotalRegistroCarrito() {
        return totalRegistroCarrito;
    }

    public void setTotalRegistroCarrito(int totalRegistroCarrito) {
        this.totalRegistroCarrito = totalRegistroCarrito;
    }

    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return p;
    }

    public void setProducto(Producto p) {
        this.p = p;
    }

    public void agregar(Producto p) {
        Venta dtVenta = new Venta();
        dtVenta.setProducto(p);
        dtVenta.setCantidad(cantidad);
        lstDetalleVenta.add(dtVenta);
        totalRegistroCarrito = lstDetalleVenta.size();
        caulcularTotal();
    }

    public void elimiarProductoCarrito(Producto produc, int codigo) {
        int i = 0;
        for (Venta p : lstDetalleVenta) {
            if (p.getProducto().getProductoid() == codigo) {
                lstDetalleVenta.remove(i);
                totalRegistroCarrito = lstDetalleVenta.size();
                caulcularTotal();
                break;
            }
            i++;

        }
    }

    public double caulcularTotal() {
        double monto2 = 0;
        for (Venta pl : lstDetalleVenta) {
            monto2 += (pl.getProducto().getPrecio() * cantidad);
        }
        return monto2;
    }

    public void registrar(Usuario us) {
        double monto = 0;
              
        try {
            for (Venta dt : lstDetalleVenta) {
                monto += (dt.getProducto().getPrecio()) * cantidad;
                p=dt.getProducto();
            }
            if (lstDetalleVenta == null || lstDetalleVenta.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "No tienes productos en el carrito"));

            } else if (lstDetalleVenta != null) {
                daoImplements_ventas daoVentas = new daoImplements_ventas();
                venta.setFecha(Calendar.getInstance().getTime());
                venta.setMonto(monto);
                venta.setUsuario(us);
                venta.setCantidad(cantidad);
                venta.setProducto(p);

                daoVentas.regitrar(venta);
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "venta exitosa"));

            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Sistema", "Error"));
            throw e;
        }
                      

    }

    

}
