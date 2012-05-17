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
import studentmind.model.EtatUtilisateur;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class EtatUtilisateurFacade extends AbstractFacade<EtatUtilisateur> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtatUtilisateurFacade() {
        super(EtatUtilisateur.class);
    }
     public EtatUtilisateur findEtatUtilisateur(int id){
        Query query = em.createNamedQuery("EtatUtilisateur.findByIdEtatUtilisateur");
        query.setParameter("idEtatUtilisateur",id);
        EtatUtilisateur EU = null;
        try{
            EU = (EtatUtilisateur)query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return EU;
    }
    
}
