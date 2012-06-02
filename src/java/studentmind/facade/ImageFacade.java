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
import studentmind.model.Image;

/**
 *
 * @author ProjetJava
 */
@Stateless
public class ImageFacade extends AbstractFacade<Image> {
    @PersistenceContext(unitName = "StudentMindPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImageFacade() {
        super(Image.class);
    }
    public Image findImage(String name){
        Query query = em.createNamedQuery("Image.findByUrlImage");
        query.setParameter("urlImage",name);
        Image img = null;
        try{
            img = (Image)query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return img;
    }
        public Image findId(int id){
        Query query = em.createNamedQuery("Image.findByIdImage");
        query.setParameter("idImage",id);
        Image img = null;
        try{
            img = (Image)query.getSingleResult();
        }catch(NoResultException e){
            e.printStackTrace();
        }
        
        return img;
    }
        
    
}
