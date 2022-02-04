package benas;

import entidades.Usuario;
import java.io.IOException;
import java.io.Serializable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "plantilla")
@ViewScoped

public class plantilla implements Serializable {

    public void verificarSesion() {
        try {

            Usuario u = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            ExternalContext ex = FacesContext.getCurrentInstance()
                    .getExternalContext();
            if (u == null) {

                ex.redirect(ex.getRequestContextPath() + "/faces/vistas/noDisponible.xhtml");  //vistas/noDisponible
            }

        } catch (IOException e) {
        }
    }

}
