/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studentmind.model.EtatDocument;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class EtatDocumentFacade extends AbstractFacade<EtatDocument> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtatDocumentFacade() {
        super(EtatDocument.class);
    }
    
}
