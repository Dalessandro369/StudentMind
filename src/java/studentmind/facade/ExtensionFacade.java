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
import studentmind.model.Extension;

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

    public Extension findExtensionNom(String name) {
        Query query = em.createNamedQuery("Extension.findByNomExtension");
        query.setParameter("nomExtension", name);
        Extension ext = null;
        try {
            ext = (Extension) query.getSingleResult();
        } catch (NoResultException e) {
        }

        return ext;
    }

    public List<Extension> find() {
        String query = "SELECT e FROM  Extension e JOIN e.fKidfamille f ORDER BY f.nomFamille,e.nomExtension ASC";
        Query q = em.createQuery(query);
        return q.getResultList();
    }
    // tjs partir de l'objet qui a la clé etrangere

    public List<Extension> findAllAlpha() {
        Query query = em.createNamedQuery("Extension.findAllAlpha");
        List<Extension> ext = null;
        try {
            ext = (List<Extension>) query.getResultList();
        } catch (NoResultException e) {
        }
        return ext;
    }
}
