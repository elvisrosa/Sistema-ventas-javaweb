package beans_controlador;

import entidades_database.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "plantilla")
@ViewScoped

public class bean_plantilla implements Serializable {

    public void verificarSesion() {
        try {

            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("obj_usuario");
            ExternalContext ex = FacesContext.getCurrentInstance().getExternalContext();
            if (u == null){
                ex.redirect(ex.getRequestContextPath() + "/faces/vistas_proyecto/noDisponible.xhtml");  //vistas/noDisponible
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public String cerrarSesion() {
        try {
             FacesContext.getCurrentInstance().getExternalContext()
             .invalidateSession();
         
        } catch (Exception e) {
            
        }
         return "Login?faces-redirect=true";
      }


}
