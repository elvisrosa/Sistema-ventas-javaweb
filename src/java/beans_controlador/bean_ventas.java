/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_controlador;

import daoImplements_entidades.daoImplements_ventas;
import entidades_basededatos.Venta;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Pc
 */
@Named(value = "bean_ventas")
@Dependent
public class bean_ventas {

    private Venta venta;
    private List<Venta> lstListarVentas;

    public bean_ventas() {
        venta = new Venta();
    }

    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public List<Venta> getLstListarVentas() {
        daoImplements_ventas daoVenta = new daoImplements_ventas();
        return lstListarVentas=daoVenta.listarVentas();
    }

    public void setLstListarVentas(List<Venta> lstListarVentas) {
        this.lstListarVentas = lstListarVentas;
    }

}
