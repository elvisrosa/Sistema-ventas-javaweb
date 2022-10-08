package entidades_basededatos;
// Generated 31/08/2022 20:07:14 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Producto generated by hbm2java
 */
public class Producto  implements java.io.Serializable {


     private int productoid;
     private Categoria categoria;
     private String nombre;
     private Double precio;
     private Integer stock;
     private String descripcion;
     private byte[] imagen;
     private String nombrefoto;
     private Set ventas = new HashSet(0);

    public Producto() {
    }

	
    public Producto(int productoid) {
        this.productoid = productoid;
    }
    public Producto(int productoid, Categoria categoria, String nombre, Double precio, Integer stock, String descripcion, byte[] imagen, String nombrefoto, Set ventas) {
       this.productoid = productoid;
       this.categoria = categoria;
       this.nombre = nombre;
       this.precio = precio;
       this.stock = stock;
       this.descripcion = descripcion;
       this.imagen = imagen;
       this.nombrefoto = nombrefoto;
       this.ventas = ventas;
    }
   
    public int getProductoid() {
        return this.productoid;
    }
    
    public void setProductoid(int productoid) {
        this.productoid = productoid;
    }
    public Categoria getCategoria() {
        return this.categoria;
    }
    
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(Double precio) {
        this.precio = precio;
    }
    public Integer getStock() {
        return this.stock;
    }
    
    public void setStock(Integer stock) {
        this.stock = stock;
    }
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public byte[] getImagen() {
        return this.imagen;
    }
    
    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }
    public String getNombrefoto() {
        return this.nombrefoto;
    }
    
    public void setNombrefoto(String nombrefoto) {
        this.nombrefoto = nombrefoto;
    }
    public Set getVentas() {
        return this.ventas;
    }
    
    public void setVentas(Set ventas) {
        this.ventas = ventas;
    }




}

