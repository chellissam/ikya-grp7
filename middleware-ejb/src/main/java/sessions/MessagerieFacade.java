/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sessions;

import com.middleware.model.Messagerie;
import com.middleware.model.User;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


@Stateless
@LocalBean
public class MessagerieFacade extends AbstractFacade<Messagerie> implements MessagerieFacadeLocal {
    @PersistenceContext(unitName = "com.middleware_middleware-ejb_ejb_1.0-SNAPSHOTPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessagerieFacade() {
        super(Messagerie.class);
    }
    
    @Override
    public void create(Messagerie messagerie){
        em.persist(messagerie);
    }
    
    public List<Messagerie> findMessages(User idUser){
        Query query=em.createNamedQuery("Messagerie.findByIdUser").setParameter("idUser", idUser);
        return query.getResultList();
    }

}
