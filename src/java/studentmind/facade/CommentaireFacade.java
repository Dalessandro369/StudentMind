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
import studentmind.model.Commentaire;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class CommentaireFacade extends AbstractFacade<Commentaire> {

    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommentaireFacade() {
        super(Commentaire.class);
    }

    public List<Commentaire> findCom(int idDoc) {
        String query = "SELECT c FROM  Commentaire c JOIN c.fKiddocument doc JOIN c.fKidetatcommentaire etat WHERE doc.idDocument = :idDoc AND (etat.idEtatCommentaire = 4 OR etat.idEtatCommentaire = 2)";
        Query q = em.createQuery(query).setParameter("idDoc", idDoc);
        return q.getResultList();
    }

    public List<Commentaire> findComSignaler() {
        String query = "SELECT c FROM  Commentaire c JOIN c.fKidetatcommentaire etat WHERE etat.idEtatCommentaire = 3";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    public Commentaire find(int id) {
        Query query = em.createNamedQuery("Commentaire.findByIdCommentaire");
        query.setParameter("idCommentaire", id);
        Commentaire c = null;
        try {
            c = (Commentaire) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }

        return c;
    }

    public int nbrComSignaler() {
        String query = "SELECT COUNT(c) FROM Commentaire c JOIN c.fKidetatcommentaire etat WHERE etat.idEtatCommentaire = 3";
        Query q = em.createQuery(query);
        return ((Long) q.getSingleResult()).intValue();
    }
}
