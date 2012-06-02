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
import studentmind.model.Document;
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
        Double result = (Double) q.getSingleResult();
        if (result == null) {
            return 0;
        } else {
            return result.doubleValue();
        }
    }
   public int NoteTest(int idDoc) {
        String query = "SELECT COUNT(n) FROM Note n JOIN n.document doc WHERE doc.idDocument = :idDoc";
        Query q = em.createQuery(query).setParameter("idDoc", idDoc);        
        Long result = (Long) q.getSingleResult();
          if (result == null) {
            return 0;
        } else {
            return result.intValue();
        }
    }
      public int verifNote(int idDoc,int idUser) {
        String query = "SELECT COUNT(n) FROM Note n JOIN n.document doc JOIN n.utilisateur user WHERE doc.idDocument = :idDoc AND user.idUtilisateur =:idUser";
        Query q = em.createQuery(query).setParameter("idDoc", idDoc).setParameter("idUser", idUser);        
        Long result = (Long) q.getSingleResult();
          if (result == null) {
            return 0;
        } else {
            return result.intValue();
        }
    }
 
   /* public List<Note> topDocNote() {       
        String query = "SELECT n FROM Note n JOIN n.document doc WHERE doc.fKidetatdocument = 2 ORDER BY n.document.idDocument ASC";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    */
    public List<Note> topDocNote() {       
        String query = "SELECT n FROM Note n ORDER BY n.document.idDocument ASC";
        Query q = em.createQuery(query);
        return q.getResultList();
    }
    
}

// GROUP BY n.documentIdDocument
