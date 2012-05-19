/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import studentmind.facade.CategorieFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.TypeFacade;
import studentmind.model.Categorie;
import studentmind.model.Type;

/**
 *
 * @author ProjetJava
 */
public class UploadDocumentServlet extends HttpServlet {
    @Override
    public void init() throws ServletException{
    }

    public UploadDocumentServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        // Affichage des pays dans la liste déroulante

        TypeFacade tFacade = ServicesLocator.getTypeFacade();
        List<Type> listType = tFacade.findAllAlpha();
        String listeHtml = "";
        for (Type type : listType) {
            listeHtml += "<option value=\"" + type.getIdType() + "\">" + type.getNomType() + "</option>";
        }
        request.setAttribute("ListeType", listeHtml);
        
        CategorieFacade cFacade = ServicesLocator.getCategorieFacade();
        List<Categorie> listCategorie = cFacade.findAllAlpha();
        listeHtml = "";
        for (Categorie cat : listCategorie) {
            listeHtml += "<option value=\"" + cat.getIdCategorie()+ "\">" + cat.getNomCategorie() + "</option>";
        }
        request.setAttribute("ListeCategorie", listeHtml);
        
        request.getRequestDispatcher("uploadDocument.jsp").forward(request,response); 
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
    }
}
