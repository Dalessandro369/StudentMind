/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import studentmind.model.Extension;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class ExtensionFacade extends AbstractFacade<Extension> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExtensionFacade() {
        super(Extension.class);
    }
    
}
