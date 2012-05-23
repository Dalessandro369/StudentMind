/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import studentmind.model.Categorie;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class CategorieFacade extends AbstractFacade<Categorie> {

    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategorieFacade() {
        super(Categorie.class);
    }

    public List<Categorie> findAllAlpha() {
        Query query = em.createNamedQuery("Categorie.findAllAlpha");
        List<Categorie> t = null;
        try {
            t = (List<Categorie>) query.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return t;
    }
        
    public Categorie find(int id){
        Query query = em.createNamedQuery("Categorie.findByIdCategorie");
        query.setParameter("idCategorie",id);
        Categorie c = null;
        try{
            c = (Categorie)query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return c;
    }
}
