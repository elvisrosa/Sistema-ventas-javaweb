package beans_controlador;

import daoImplements_entidades.daoImplement_categoria;
import daoImplements_entidades.daoImplements_producto;
import dao_entidades.daoCategoria;
import dao_entidades.daoProducto;
import entidades_basededatos.Categoria;
import entidades_basededatos.Detalleventa;
import entidades_basededatos.Producto;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.apache.commons.io.IOUtils;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import org.primefaces.model.file.UploadedFile;

@Named(value = "bean_productos")
@ViewScoped
public class bean_productos implements Serializable {

    private Producto producto;
    private List<Producto> lstProducto;
    private List<Producto> lstProductoFiltrados;
    private List<Detalleventa> lstDetalleVenta;
    private UploadedFile file;
    daoProducto dao;

    public bean_productos() {
        producto = new Producto();
    }

    public List<Detalleventa> getLstDetalleVenta() {
        return lstDetalleVenta;
    }

    public void setLstDetalleVenta(List<Detalleventa> lstDetalleVenta) {
        this.lstDetalleVenta = lstDetalleVenta;
    }

    public List<Producto> getLstProductoFiltrados() {
        return lstProductoFiltrados;
    }

    public void setLstProductoFiltrados(List<Producto> lstProductoFiltrados) {
        this.lstProductoFiltrados = lstProductoFiltrados;
    }

    public List<Producto> getLstProducto() {
        dao = new daoImplements_producto();
        return lstProducto = dao.listar();
    }

    public void setLstProducto(List<Producto> lstProducto) {
        this.lstProducto = lstProducto;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void agregarProducto() {
        String rutaCarpeta = "C:\\Users\\Pc\\Documents\\NetBeansProjects\\proyectoFinApiWeb\\web\\resources\\Imagenes_producto\\";
        dao = new daoImplements_producto();
        if (file.getSize() > 0) {
            try {
                producto.setImagen(file.getContent());
                producto.setNombrefoto(file.getFileName());
                dao.agregarProdcto(producto);
                producto = new Producto();
                escribirFotocarpeta(IOUtils.toByteArray(file.getInputStream()), rutaCarpeta, file.getFileName());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Producto creado"));

            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Error al crear producto " + e.getMessage()));
            }
        }
    }

    public void escribirFotocarpeta(byte[] bytes, String carpeta, String nombreImagen) {
        try {
            Path path = Paths.get(carpeta, nombreImagen);
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println("Error al escribir la imagem" + e.getMessage());
        }
    }

    public void eliminarProducto() {
        dao = new daoImplements_producto();
        try {
            dao.eliminarProdicto(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Producto eliminado"));
            producto = new Producto();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "erroro" + e.getMessage()));

        }

    }

    public void editarProducto() {
        String rutaCarpeta = "C:\\Users\\Pc\\Documents\\NetBeansProjects\\proyectoFinApiWeb\\web\\resources\\Imagenes_producto\\";
        dao = new daoImplements_producto();
        if (file==null) {
            dao.editarProducto(producto);
            producto = new Producto();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Producto editado"));
        } else{
            try {
                producto.setImagen(file.getContent());
                producto.setNombrefoto(file.getFileName());
                dao.editarProducto(producto);
                producto = new Producto();
                escribirFotocarpeta(IOUtils.toByteArray(file.getInputStream()), rutaCarpeta, file.getFileName());
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Producto editado"));

            } catch (IOException e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Error al editar el producto " + e.getMessage()));
            }
        }
    }
}
