package beans_controlador;

import daoImplements_entidades.daoImplements_usuario;
import daoImplements_entidades.daoImplements_ventas;
import dao_entidades.daoUsuario;
import entidades_basededatos.Usuario;
import entidades_basededatos.Venta;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.commons.io.IOUtils;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.file.UploadedFile;

@Named(value = "bean_usuario")
@SessionScoped
public class bean_usuario implements Serializable {

    private UploadedFile file;
    private Usuario usuario;
    private daoUsuario dao;
    private List<Usuario> lstUsuario;
    private List<Usuario> lstFilterUsuario;
    private StreamedContent imagenUsuario;
    private Usuario u;
    private List<Venta> ventasGeneradas;

    public bean_usuario() {
        usuario = new Usuario();
    }

    public List<Venta> getVentasGeneradas() {
        daoImplements_ventas dao = new daoImplements_ventas();
        return ventasGeneradas = dao.listar(u.getIdusuario());
    }

    public void setVentasGeneradas(List<Venta> ventasGeneradas) {
        this.ventasGeneradas = ventasGeneradas;
    }

    public Usuario getU() {
        return u;
    }

    public void setU(Usuario u) {
        this.u = u;
    }

    public StreamedContent getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(StreamedContent imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public List<Usuario> getLstFilterUsuario() {
        return lstFilterUsuario;
    }

    public void setLstFilterUsuario(List<Usuario> lstFilterUsuario) {
        this.lstFilterUsuario = lstFilterUsuario;
    }

    public List<Usuario> getLstUsuario() {

        dao = new daoImplements_usuario();
        lstUsuario = dao.lstUsuarios();

        return lstUsuario;
    }

    public void setLstUsuario(List<Usuario> lstUsuario) {
        this.lstUsuario = lstUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    //Metodo crear usuario 
    //base de datos Usuario
    public void crearUsuario() {
        dao = new daoImplements_usuario();
        try {
            usuario.setNombrefoto("imgr.png");
            dao.crearUsuario(this.usuario);
            usuario = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mensaje del sistema", "Usuario creado"));
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mensaje del sistema", "El usuario ya existte"));
        }

    }

    //}
    //@Editar USuari cuando este logueado
    public void editarUsuario() {
        dao = new daoImplements_usuario();
        try {
            dao.actualizarUsuario(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mensaje del sistema", "Usuario actualizado"));
            usuario = new Usuario();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "Mensaje del sistema", "No se pudo actualizar el usuario"));
        }

    }

    public void editarUsuarioLogueado() throws IOException {
        String rutaCarpetaImagenes = "C:\\Users\\Pc\\Documents\\NetBeansProjects\\proyectoFinApiWeb\\web\\resources\\Imagenes_usuario\\";
        dao = new daoImplements_usuario();
        if (file.getSize() > 0) {
            u.setImagen(file.getContent());
            u.setNombrefoto(file.getFileName());
            dao.actualizarUsuario(u);
            u = new Usuario();
            escribirFotocarpeta(IOUtils.toByteArray(file.getInputStream()), rutaCarpetaImagenes, file.getFileName());
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema", "Actualizacion existosa"));
            cerrarSesion();
        } else if (file.getSize() < 0) {
            dao.actualizarUsuario(u);
            u = new Usuario();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema",
                    "Actualizacion exitosa"));
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

    //@Metodo para eliminar usuario
    public void eliminarUsuario() {
        dao = new daoImplements_usuario();
        try {
            dao.eliminarUsuario(this.usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema", "Eliminado"));
            usuario = new Usuario();

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema", "No se puedo eliminar el usuario"));
        }
    }

    //@Iniciar sesion metodo
    //Metodo de implementacion en el dao y daoImplement
    public String iniciarSesion() throws Exception {
        daoUsuario daoLogin = new daoImplements_usuario();
        String redireccion = null;
        try {
            u = daoLogin.iniciarSesion(this.usuario);
            if (u != null) {
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                if (u.getTipousuario().equals("admin")) {
                    redireccion = "vistaAdministracion?faces-redirect=true";
                } else if (u.getTipousuario().equals("usuario")) {
                    redireccion = "vistaInicio?faces-redirect=true";
                }

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Datos incorrecto"));
            }

        } catch (Exception e) {
            throw e;
        }
        return redireccion;
    }

    
    
    //@Metodo para verificar si el usuario esta logueado
    //No permite visitar paginas sin haber iniciado sesion
    public void verificarSesion() throws IOException {
        if (FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("usuario") == null) {
            FacesContext.getCurrentInstance().getExternalContext().redirect("noDisponible.xhtml");
        }
    }

    //@Metodo para cerrar sesión y redireccionamiento a la pagina LOGin
    public void cerrarSesion() {
        ExternalContext etx = FacesContext.getCurrentInstance().getExternalContext();
        String patch = ((ServletContext) etx.getContext()).getContextPath();
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        //return "faces/Login?faces-redirect=true";
        try {
            ((HttpSession) etx.getSession(false)).invalidate();
            etx.redirect(patch + "/faces/Login.xhtml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void engaño() {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Mensaje del sistema",
                "Inicia sesion por favor"));
    }

    //Funciono, pero la imagen se desaparecia cuando se actaulizaba la pagina
    //Corregir
    /*public void traerFotoUsuario() {
        System.out.println("* * * Antes de imagen * * *");
        // Convertir la imagen de byte[] a StreamedContent para Primefaces
        try {
            InputStream input = new ByteArrayInputStream(u.getImagen());
            System.out.println("* * * pasa imagen * * *");

            imagenUsuario = new DefaultStreamedContent(input);
            System.out.println("Sale de  verificaLogin");
        } catch (Exception e) {
            System.out.println("error en la imagen" + e.getMessage());
        }
    }
     */
}
