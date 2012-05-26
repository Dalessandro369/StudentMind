/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    public int nbrUser() {
        String query = "SELECT COUNT(u) FROM Utilisateur u JOIN u.fKidetatutlisateur etat WHERE etat.idEtatUtilisateur = 2";
        Query q = em.createQuery(query);
        return ((Long) q.getSingleResult()).intValue();
    }

    public Utilisateur findEmail(String name) {
        Query query = em.createNamedQuery("Utilisateur.findByEmail");
        query.setParameter("email", name);
        Utilisateur u = null;
        try {
            u = (Utilisateur) query.getSingleResult();
        } catch (NoResultException e) {
        }

        return u;
    }

    public Utilisateur find() {
        Query query = em.createNamedQuery("Utilisateur.findAll");
        Utilisateur u = null;
        try {
            u = (Utilisateur) query.getSingleResult();
        } catch (NoResultException e) {
        }

        return u;
    }

    public Utilisateur findId(int id) {
        Query query = em.createNamedQuery("Utilisateur.findByIdUtilisateur");
        query.setParameter("idUtilisateur", id);
        Utilisateur u = null;
        try {
            u = (Utilisateur) query.getSingleResult();
        } catch (NoResultException e) {
        }

        return u;
    }
}
