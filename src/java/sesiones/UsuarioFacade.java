package sesiones;

import entidades.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> implements UsuarioFacadeLocal {

    @PersistenceContext(unitName = "proyectoFinApiWebPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }

    @Override
    public Usuario inicioSesion(Usuario u) {
        Usuario usuario = null;
        String psq;
        try {
            psq = "From Usuario u where u.email =?1 and u.contrasenna =?2 ";
            Query query = em.createQuery(psq);
            query.setParameter(1, u.getEmail());
            query.setParameter(2, u.getContrasenna());
                List<Usuario> lista = query.getResultList();
                if(!lista.isEmpty()){
                    usuario = lista.get(0);
                }
            
        } catch (Exception e) {
            throw e;
        }
        return usuario;
    }

}


        
    
    

