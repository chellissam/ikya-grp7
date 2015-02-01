/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import jpa.Defis;

/**
 *
 * @author TOSHIBA
 */
@Stateless
public class DefisFacade extends AbstractFacade<Defis> implements DefisFacadeLocal {
    @PersistenceContext(unitName = "PU-IKYA-Projet-ejb")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DefisFacade() {
        super(Defis.class);
    }
    
}
