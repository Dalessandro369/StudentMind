/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studentmind.model.EtatMessage;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class EtatMessageFacade extends AbstractFacade<EtatMessage> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtatMessageFacade() {
        super(EtatMessage.class);
    }
    
}
