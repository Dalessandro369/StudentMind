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
import studentmind.model.Message;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class MessageFacade extends AbstractFacade<Message> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MessageFacade() {
        super(Message.class);
    }
    public List<Message> findMessRecu(int idUser) {
        String query = "SELECT m FROM Message m JOIN m.fKidetatmessage etat JOIN m.fKidutilisateurdes user WHERE user.idUtilisateur = :idUser AND (etat.idEtatMessage = 1 OR etat.idEtatMessage = 2) ORDER BY m.dateMessage DESC";
        Query q = em.createQuery(query).setParameter("idUser", idUser);
        return q.getResultList();
    }
    public List<Message> findMessEnvoyer(int idUser) {
        String query = "SELECT m FROM Message m JOIN m.fKidetatmessage etat JOIN m.fKidutilisateurexp user WHERE user.idUtilisateur = :idUser AND etat.idEtatMessage <> 4 ORDER BY m.dateMessage DESC";
        Query q = em.createQuery(query).setParameter("idUser", idUser);
        return q.getResultList();
    }
        public Message findId(int id) {
        Query query = em.createNamedQuery("Message.findByIdMessage");
        query.setParameter("idMessage", id);
        Message u = null;
        try {
            u = (Message) query.getSingleResult();
        } catch (NoResultException e) {
        }

        return u;
    }
   public int nbrMessNonLu(int idUser) {
        String query = "SELECT COUNT(m) FROM  Message m JOIN m.fKidutilisateurdes user JOIN m.fKidetatmessage etat WHERE user.idUtilisateur = :idUser AND etat.idEtatMessage = 2";
        Query q = em.createQuery(query).setParameter("idUser", idUser);
        return ((Long) q.getSingleResult()).intValue();
    } // si sa est plu grand que 0 alors tu l'affiche sinon
   
      public int nbrMessTotal(int idUser) {
        String query = "SELECT COUNT(m) FROM  Message m JOIN m.fKidutilisateurdes user JOIN m.fKidetatmessage etat WHERE (user.idUtilisateur = :idUser AND (etat.idEtatMessage = 2 or etat.idEtatMessage = 1))";
        Query q = em.createQuery(query).setParameter("idUser", idUser);
        return ((Long) q.getSingleResult()).intValue();
    }

}
