/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studentmind.model.Rang;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class RangFacade extends AbstractFacade<Rang> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RangFacade() {
        super(Rang.class);
    }
    
}
