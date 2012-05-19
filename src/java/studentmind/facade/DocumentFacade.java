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
import studentmind.model.Document;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class DocumentFacade extends AbstractFacade<Document> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DocumentFacade() {
        super(Document.class);
    }
   
    public List<Document> findAll() {
        Query query = em.createNamedQuery("Document.findAll");
        List<Document> p = null;
        try{
            p = (List<Document>)query.getResultList();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        return p;
    }
}
