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
}
