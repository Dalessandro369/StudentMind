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
import javax.servlet.http.HttpSession;
import studentmind.facade.*;
import studentmind.model.*;

/* imports pour test Jean */
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

/**
 *
 * @author ProjetJava
 */
@MultipartConfig   
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
        request.setAttribute("top", afficherTop());
        request.getRequestDispatcher("uploadDocument.jsp").forward(request,response); 
    }

 
    /* doPost pour test Jean */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
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
        Extension ext = new Extension(2);
        if (champOk && session != null && ext!=null)
        {
        
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();


            Document doc = new Document();
            doc.setIdDocument(1);
            //Vérifier la taille document ici je mes 5Mo
            doc.setTaille(5);
            doc.setTitreDocument(titre);
            doc.setDescriptionDocument(description); 
 
            Utilisateur user = (Utilisateur) session.getAttribute("user");
            doc.setFKidutilisateur(user); 
            doc.setFKidcategorie(new Categorie(Integer.parseInt(categorie)));
            doc.setFKidtype(new Type(Integer.parseInt(type)));
            doc.setFKidetatdocument(new EtatDocument(1));
            doc.setFKidextension(ext);
            dFacade.create(doc);
            request.setAttribute("test", "upload ok");
        
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            try {
                // on accède au fichier uploadé par le client
                Part p1 = request.getPart("urlFichier");
                InputStream is = p1.getInputStream();

                // on lit le nom du fichier
                Part p2  = request.getPart("titre");
                Scanner s = new Scanner(p2.getInputStream());
                String filename = s.nextLine();    // on le récupère ici

                // on récupère le nom à utiliser sur le serveur
                String outputfile = this.getServletContext().getRealPath(filename);  // on récupère le chemin sur le serveur
                FileOutputStream os = new FileOutputStream (outputfile);

                // on écrit les bytes du fichier de la source vers la destination
                int ch = is.read();
                while (ch != -1) {
                    os.write(ch);
                    ch = is.read();
                }
                os.close();
                out.println("<h3>Fichier uploadé avec succès !</h3>");
            }
            catch(Exception ex) {
            out.println("Exception -->" + ex.getMessage());
            }
            finally { 
                out.close();
            }
        
        }// fin du if
        else {request.setAttribute("test", "upload remplir formulaire");}
    }
    
    public String afficherTop(){
        String html = "<ul>";
        int longeur = 0;
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        List<Document> liste = dFacade.top3();
        for (Document doc :liste){
            longeur = doc.getDescriptionDocument().length();
            if (longeur >= 150) longeur = 150;           
            html += "<li><strong>"+doc.getTitreDocument()+"</strong> - "+doc.getDescriptionDocument().substring(0, longeur) +" <a href=\"voir-document.html?id=" + doc.getIdDocument()+"\"> Lire la suite</a></li>";
        }
        html += "</ul>";
        return html;
    }
}
