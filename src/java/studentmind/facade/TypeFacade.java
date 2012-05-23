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
import studentmind.model.Type;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class TypeFacade extends AbstractFacade<Type> {

    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TypeFacade() {
        super(Type.class);
    }

    public List<Type> findAllAlpha() {
        Query query = em.createNamedQuery("Type.findAllAlpha");
        List<Type> t = null;
        try {
            t = (List<Type>) query.getResultList();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return t;
    }
        public Type find(int id){
        Query query = em.createNamedQuery("Type.findByIdType");
        query.setParameter("idType",id);
        Type t = null;
        try{
            t = (Type)query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return t;
    }
}
