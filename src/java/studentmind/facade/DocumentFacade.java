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

    @Override
    public List<Document> findAll() {
        Query query = em.createNamedQuery("Document.findAll");
        List<Document> p = null;
        try {
            p = (List<Document>) query.getResultList();
        } catch (NoResultException e) {
        }
        return p;
    }

    public List<Document> find(String requete, String motCle) {
        String query = "SELECT d FROM Document d JOIN d.fKidetatdocument etat" + requete + "ORDER BY d.titreDocument ASC";
        String motif = "%" + motCle + "%";
        Query q = em.createQuery(query).setParameter("mot", motif.toUpperCase());
        return q.getResultList();
    }

    public List<Document> top3() {
        String query = "SELECT d FROM Document d JOIN d.fKidetatdocument etat WHERE etat.idEtatDocument = 2 ORDER BY d.idDocument DESC";
        Query q = em.createQuery(query).setMaxResults(3);
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

    public int nbrDocCat(int idCat) {
        String query = "SELECT COUNT(d) FROM  Document d JOIN d.fKidcategorie cat JOIN d.fKidetatdocument etat WHERE cat.idCategorie = :idCat AND etat.idEtatDocument = 2";
        Query q = em.createQuery(query).setParameter("idCat", idCat);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Document> listDocCat(int idCat) {
        String query = "SELECT d FROM  Document d JOIN d.fKidcategorie cat JOIN d.fKidetatdocument etat WHERE cat.idCategorie = :idCat AND etat.idEtatDocument = 2";
        Query q = em.createQuery(query).setParameter("idCat", idCat);
        return q.getResultList();
    }

    public Document documentUne() {
        Query query = em.createQuery("SELECT d FROM Document d JOIN d.fKidetatdocument etat WHERE etat.idEtatDocument = 2 ORDER BY d.idDocument DESC").setMaxResults(1);
        Document d = null;
        try {
            d = (Document) query.getSingleResult();
        } catch (NoResultException e) {
            e.printStackTrace();
        }
        return d;
    }

    public int nbrDoc() {
        String query = "SELECT COUNT(d) FROM Document d JOIN d.fKidetatdocument etat WHERE etat.idEtatDocument = 2";
        Query q = em.createQuery(query);
        return ((Long) q.getSingleResult()).intValue();
    }

    public List<Document> listDocAttente() {
        String query = "SELECT d FROM  Document d JOIN d.fKidetatdocument etat WHERE etat.idEtatDocument = 1 ORDER BY d.titreDocument ASC";
        Query q = em.createQuery(query);
        return q.getResultList();
    }

    public List<Document> listDocUser(int idUser) {
        String query = "SELECT d FROM  Document d JOIN d.fKidutilisateur user JOIN d.fKidetatdocument etat WHERE user.idUtilisateur = :idUser AND etat.idEtatDocument = 2 ORDER BY d.titreDocument ASC";
        Query q = em.createQuery(query).setParameter("idUser", idUser);
        return q.getResultList();
    }

    public int nbrDocUser(int idUser) {
        String query = "SELECT COUNT(d) FROM  Document d JOIN d.fKidutilisateur user JOIN d.fKidetatdocument etat WHERE user.idUtilisateur = :idUser AND etat.idEtatDocument = 2";
        Query q = em.createQuery(query).setParameter("idUser", idUser);
        return ((Long) q.getSingleResult()).intValue();
    }

    public int nbrDocAtten() {
        String query = "SELECT COUNT(d) FROM  Document d JOIN d.fKidetatdocument etat WHERE etat.idEtatDocument = 1";
        Query q = em.createQuery(query);
        return ((Long) q.getSingleResult()).intValue();
    }
}
