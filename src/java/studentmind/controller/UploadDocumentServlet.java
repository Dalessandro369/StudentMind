/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.*;
import studentmind.model.*;

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
        
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        
        // Affichage des types dans la liste déroulante

        TypeFacade tFacade = ServicesLocator.getTypeFacade();
        List<Type> listType = tFacade.findAllAlpha();
        String listeHtml = "";
        for (Type type : listType) {
            listeHtml += "<option value=\"" + type.getIdType() + "\">" + type.getNomType() + "</option>";
        }
        request.setAttribute("ListeType", listeHtml);
        
        // Affichage des catégories dans la liste déroulante
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
        
        boolean champOk = true;
        String mesType = "";
        String mesCategorie = "";
        String mesTitre = "";
        String mesDescription = "";
        
        //Recupérer le formulaire
        String type = request.getParameter("type");
        String categorie = request.getParameter("categorie");
        String titre = request.getParameter("titre");     
        String description = request.getParameter("description");
        
        if (type == null || type.isEmpty()) {
            mesType = "Veuillez à remplir correctement le type";
            request.setAttribute("ErreurType", mesType);
            champOk = false;
        }
        if (categorie == null || categorie.isEmpty()) {
            mesCategorie = "Veuillez à remplir correctement la catégorie";
            request.setAttribute("ErreurCategorie", mesCategorie);
            champOk = false;
        }
        if (titre == null || titre.isEmpty()) {
            mesTitre = "Veuillez à remplir correctement le titre";
            request.setAttribute("ErreurTitre", mesTitre);
            champOk = false;
        }
        if (description == null || description.isEmpty()) {
            mesDescription = "Veuillez à remplir correctement la description";
            request.setAttribute("ErreurDescription", mesDescription);
            champOk = false;
        }        
 
        HttpSession session = request.getSession(false);
        ExtensionFacade eFacade = ServicesLocator.getExtensionFacade();
       // StringTokenizer toc = new StringTokenizer(file,".");
         //pour ne pas prendre la premiere partie
       // String str1 = toc.nextToken();
       // String str2 = toc.nextToken();
       // Extension ext = eFacade.findExtensionNom(toc.nextToken());    
        Extension ext = new Extension(1);
        if (champOk && session != null && ext!=null) {
            
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();


            Document doc = new Document();
            doc.setIdDocument(1);
            //Vérifier la taille document ici je mes 5Mo
            doc.setTaille(5);
            doc.setTitreDocument(titre);
            doc.setDescriptionDocument(description); 
            
            //Utilisateur user = (Utilisateur) session.getAttribute("user");
            Utilisateur user = new Utilisateur(1);
            doc.setFKidutilisateur(user); 
            doc.setFKidcategorie(new Categorie(Integer.parseInt(categorie)));
            doc.setFKidtype(new Type(Integer.parseInt(type)));
            doc.setFKidetatdocument(new EtatDocument(1));
            doc.setFKidextension(ext);
            dFacade.create(doc);
            request.setAttribute("test", "upload ok");
        }else {
            request.setAttribute("test", "upload remplir formulaire");
        }
         request.getRequestDispatcher("uploadDocument.jsp").forward(request, response);
    }
}
