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
}
