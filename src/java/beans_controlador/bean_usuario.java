 
package beans_controlador;

import daoImplements_entidades.daoImplements_usuario;
import dao_entidades.daoUsuario;
import entidades_database.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "bean_usuario")
@SessionScoped
public class bean_usuario implements Serializable{
    
    private Usuario usuario;
    private daoUsuario dao;
    //FacesMessage  mensaje = new FacesMessage();

    public bean_usuario() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    
    public void crearUsuario(){
        dao = new daoImplements_usuario();
        try {
              dao.crearUsuario(usuario);                       
              usuario = new Usuario();
        } catch (Exception e) {
              FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, 
                      "Mensaje del sistema", "El usuario ya existte"));
        }
         FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, 
                 "Mensaje del sistema" , "Usuario creado"));
    }
    
    
    public String iniciarSesion()  throws Exception{
        daoUsuario daoLogin = new daoImplements_usuario();
        Usuario u;
        String redireccion = null;
        try {
            u = daoLogin.iniciarSesion(this.usuario);
            if (u != null) {                                                         
                redireccion = "/vistas_proyecto/vistaInicio?faces-redirect=true";
                   FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
                
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Datos incorrecto"));
            }
        }
        catch (Exception e) {
           throw e;   
       }
        return redireccion;
    }
    
    public void agregarFoto(){
        try {
            
        } catch (Exception e) {
        }
    }
    
     
    public String mostrarPersona() throws Exception{
       Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
       String resultado = u.getNombre()+ " " +u.getApellido();
       return resultado;
   }
     
     public void verificarSesion() throws IOException {
        if(FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("usuario") == null) {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("faces/vistas_proyecto/noDisponible.xhtml");
        } 
    }
    
   public String cerrarSesion() {
       String redireccion="./Login?faces-redirect=true";
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return redireccion;
    }

    
    
    
   
    
}
