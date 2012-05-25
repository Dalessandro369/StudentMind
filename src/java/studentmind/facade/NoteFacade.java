/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import studentmind.model.Note;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class NoteFacade extends AbstractFacade<Note> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NoteFacade() {
        super(Note.class);
    }
    public double moyenne(int idDoc) {
        String query = "SELECT AVG(n.note) FROM Note n JOIN n.document doc WHERE doc.idDocument = :idDoc";
        Query q = em.createQuery(query).setParameter("idDoc", idDoc);
        Double result =(Double)q.getSingleResult(); 
        if (result == null){
            return 0;
        }else return result.doubleValue();
        
       
    }    
}
