/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studentmind.model.EtatCommentaire;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class EtatCommentaireFacade extends AbstractFacade<EtatCommentaire> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtatCommentaireFacade() {
        super(EtatCommentaire.class);
    }
    
}
