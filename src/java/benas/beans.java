package benas;

import entidades.Usuario;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import sesiones.UsuarioFacadeLocal;

@Named(value = "beans")
@RequestScoped
public class beans implements Serializable{

    @EJB
    private UsuarioFacadeLocal usuarioFacade;
    private Usuario usuario;
    @PostConstruct
    public void beans() {
        usuario = new Usuario();
    }
    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    

    public String inicioSesio() {
        Usuario u;
        String redireccion = null;
        try {
            u = usuarioFacade.inicioSesion(usuario);
            if (u != null) {
                FacesContext.getCurrentInstance().getExternalContext()
                        .getSessionMap().put("usuario", u);
                 FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Logue Exitoso"));
                redireccion = "vistas/vistaInicio?faces-redirect=true";
                 
            } else {

                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Datos incorrecto"));

            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error del sistema al iniciar sesion"));
            redireccion = "./../noDisponible";
            
        }

        return redireccion;

    }
       
/*
    public void verificarSesion() throws IOException {
        if(FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().get("usuario") == null) {
            
            FacesContext.getCurrentInstance().getExternalContext().redirect("/vistas/noDisponible.xhtml");
        } 
    }
    
    
*/
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext()
             .invalidateSession();
        return "/faces/index";

    }

    public String horaServidor() {
        return DateFormat.getTimeInstance(DateFormat.LONG).format(new Date());
    }
}

//https://www.youtube.com/watch?v=iHhBtwwSleQ&t=182s

//https://www.youtube.com/watch?v=GCbrUnHMts0
