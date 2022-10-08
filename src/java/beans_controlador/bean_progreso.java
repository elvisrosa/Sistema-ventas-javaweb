/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans_controlador;

import java.io.Serializable;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Pc
 */
@Named(value = "bean_progreso")
@ViewScoped
public class bean_progreso implements  Serializable{

    /**
     * Creates a new instance of bean_progreso
     */
    private Integer progreso1;
    private Integer progreso2;
    public bean_progreso() {
    }

    public Integer getProgreso1() {
        progreso1=actualizarProgreso(progreso1);
        return progreso1;
    }

    public void setProgreso1(Integer progreso1) {
        this.progreso1 = progreso1;
    }

    public Integer getProgreso2() {
        return progreso2;
    }

    public void setProgreso2(Integer progreso2) {
        this.progreso2 = progreso2;
    }

   
    
    
    public void longitudDeProgreso() throws InterruptedException{
        setProgreso1(0);
        Integer k = getProgreso1();
        while(k==null || k >100){
            k = actualizarProgreso(k);
            setProgreso1(k);
            Thread.sleep(500);
            
        }
        
    }
    
    public Integer actualizarProgreso(Integer p){
        if(p==null){
            p=0;
        }else{
            p=p+(int) (Math.random()*35);
            if(p>100){
                p=100;
            }
        }
          return p;    
    }
    
    public void cancelar(){
        progreso1=null;
    }
    
}
