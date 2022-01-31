package benas;

import entidades.Usuario;
import java.io.Serializable;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "plantilla")
@ViewScoped

public class plantilla implements Serializable {
    

    public void verificarSesion() {
        try {
          
            Usuario u =  (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
            if(u==null){
                FacesContext.getCurrentInstance().getExternalContext().redirect("./../noDisponible");  //vistas/noDisponible
            }

        } catch (Exception e) {
        }
    }

}
