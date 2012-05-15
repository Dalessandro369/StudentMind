/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studentmind.model.Telechargement;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class TelechargementFacade extends AbstractFacade<Telechargement> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelechargementFacade() {
        super(Telechargement.class);
    }
    
}
