/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import studentmind.model.Pays;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class PaysFacade extends AbstractFacade<Pays> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PaysFacade() {
        super(Pays.class);
    }


    public List<Pays> findAllAlpha() {
        Query query = em.createNamedQuery("Pays.findAllAlpha");
        List<Pays> p = null;
        try{
            p = (List<Pays>)query.getResultList();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        return p;
    }
    
    public Pays findPaysId(int id){
        Query query = em.createNamedQuery("Pays.findByIdPays");
        query.setParameter("idPays",id);
        Pays p = null;
        try{
            p = (Pays)query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return p;
    }
    
    public Pays findPays(String name){
        Query query = em.createNamedQuery("Pays.findByNomPays");
        query.setParameter("nomPays",name);
        Pays p = null;
        try{
            p = (Pays)query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return p;
    }
    
}
