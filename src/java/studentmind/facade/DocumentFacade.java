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
import studentmind.model.Extension;
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
   
    @Override
    public List<Document> findAll() {
        Query query = em.createNamedQuery("Document.findAll");
        List<Document> p = null;
        try{
            p = (List<Document>)query.getResultList();
        }catch(NoResultException e){

        }
        return p;
    }
    
    public List<Document> find(String requete,String motCle) {
        String query = "SELECT d FROM Document d JOIN d.fKidetatdocument etat"+requete+"ORDER BY d.titreDocument ASC";
        String motif = "%"+motCle+"%";
        Query q = em.createQuery(query)
                .setParameter("mot", motif.toUpperCase());
                
        //.setParameter("ext", Integer.parseInt(ext));
        return q.getResultList();
    }
        public Document findRang(int id) {
        Query query = em.createNamedQuery("Document.findByIdDocument");
        query.setParameter("idDocument", id);
        Document r = null;
        try {
            r = (Document) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return r;
    }
}
