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
import studentmind.model.Extension;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class ExtensionFacade extends AbstractFacade<Extension> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExtensionFacade() {
        super(Extension.class);
    }
    //
     public Extension findExtensionNom(String name){
        Query query = em.createNamedQuery("Extension.findByNomExtension");
        query.setParameter("nomExtension",name);
        Extension ext = null;
        try{
            ext = (Extension)query.getSingleResult();            
        }catch(NoResultException e){
        }
        
        return ext;
    }
}
