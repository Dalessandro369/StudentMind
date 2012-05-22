/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import studentmind.model.Famille;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class FamilleFacade extends AbstractFacade<Famille> {

    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FamilleFacade() {
        super(Famille.class);
    }

}

