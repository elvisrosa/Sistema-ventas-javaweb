
package beans_controlador;

import daoImplements_entidades.daoImplements_acercadelaEmpresa;
import dao_entidades.daoContactoEmpresa;
import entidades_basededatos.Acercade;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;

@Named(value = "bean_datosEmpresa")
@ViewScoped
public class bean_datosEmpresa implements Serializable {

    private Acercade empresa;
    private daoContactoEmpresa dao;
    private List<Acercade> lstEmpresa;

    public bean_datosEmpresa() {
        empresa = new Acercade();
    }

    public Acercade getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Acercade empresa) {
        this.empresa = empresa;
    }

    public List<Acercade> getLstEmpresa() {
        dao = new daoImplements_acercadelaEmpresa();
        return lstEmpresa = dao.listar();
    }

    public void setLstEmpresa(List<Acercade> lstEmpresa) {
        this.lstEmpresa = lstEmpresa;
    }

    public void guardarDato() {
        dao = new daoImplements_acercadelaEmpresa();
        try {  
            //this.empresa.setIdDatos(1);
            dao.agregarDato(this.empresa);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Datos agregado"));
            empresa = new Acercade();
            
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "No se pudo agregar los datos"));
        }
        
    }

    public void editarDatos() {
        try {
            dao = new daoImplements_acercadelaEmpresa();
            dao.editarDato(this.empresa);
            empresa = new Acercade();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "No se pudo editar los datos"));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", "Datos editados"));

    }

    public void eliminarDatos() {
        try {
            dao = new daoImplements_acercadelaEmpresa();
            dao.EliminarDato(this.empresa);
            empresa = new Acercade();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema",
                    "No se pudo elimianr los datos"));
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sistema", 
                "Datos eliminados"));

    }

}
