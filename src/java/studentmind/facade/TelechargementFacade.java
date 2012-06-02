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
import studentmind.model.Telechargement;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class TelechargementFacade extends AbstractFacade<Telechargement> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public TelechargementFacade() {
        super(Telechargement.class);
    }
    
    public int nbrTelecharger(int idDoc) {
        String query = "SELECT COUNT(t) FROM  Telechargement t JOIN t.documentIdDocument doc WHERE doc.idDocument = :idDoc";
        Query q = em.createQuery(query).setParameter("idDoc", idDoc);
        return ((Long)q.getSingleResult()).intValue();
    }
     public int nbrTelechargerVerif(int idDoc,int idUser) {
        String query = "SELECT COUNT(t) FROM  Telechargement t JOIN t.documentIdDocument doc JOIN t.utlisateurIdUtilisateur user WHERE doc.idDocument = :idDoc AND user.idUtilisateur =:idUser";
        Query q = em.createQuery(query).setParameter("idDoc", idDoc).setParameter("idUser", idUser);
        return ((Long)q.getSingleResult()).intValue();
    }   
}
