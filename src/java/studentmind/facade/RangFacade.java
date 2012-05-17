/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import studentmind.model.Pays;
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
    public Rang findRang(int id){
        Query query = em.createNamedQuery("Rang.findByIdRang");
        query.setParameter("idRang",id);
        Rang r = null;
        try{
            r = (Rang)query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return r;
    }
}
