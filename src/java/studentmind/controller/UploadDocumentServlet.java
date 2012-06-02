package studentmind.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import studentmind.facade.*;
import studentmind.model.*;

/**
 *
 * @author ProjetJava
 */
@MultipartConfig   
public class UploadDocumentServlet extends HttpServlet 
{    
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

 
    /* doPost version Jean */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException 
    {        
        boolean champOk = true;
        String message = "";
        
        //Recupérer le formulaire
        String type = request.getParameter("type");
        String categorie = request.getParameter("categorie");
        String titre = request.getParameter("titre");   
        String description = request.getParameter("description");
        if (type == null || type.isEmpty()) {
            message += "Veuillez à remplir correctement le type\n";
            champOk = false;
        }
        if (categorie == null || categorie.isEmpty()) {
            message += "Veuillez à remplir correctement la catégorie\n";
            champOk = false;
        }
        if (titre == null || titre.isEmpty()) {
            message += "Veuillez à remplir correctement le titre\n";
            champOk = false;
        }
        if (description == null || description.isEmpty()) {
            message += "Veuillez à remplir correctement la description\n";
            champOk = false;
        }        
 
        HttpSession session = request.getSession(false);
        ExtensionFacade eFacade = ServicesLocator.getExtensionFacade();
        
        Extension ext = null;
        // on lit le nom du fichier
        String extensionFichier  = request.getPart("urlFichier").toString();
        int p = extensionFichier.indexOf(",");
        extensionFichier = extensionFichier.substring(10, p);

        int pos = extensionFichier.lastIndexOf("."); // on récupère la position du . en partant de la fin
        if(pos != -1) // si on trouve
        {
            extensionFichier = extensionFichier.substring(pos).toLowerCase();
            ext = eFacade.findExtensionNom(extensionFichier);
        }
        else
            champOk = false; // on ne peut pas aller plus loin
        
        if(ext == null)
                message += "Extension de fichier non autorisée\n";
        
        PrintWriter out = response.getWriter();
        if (champOk && session != null && ext != null)
        {
        
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        
            response.setContentType("text/html;charset=UTF-8");
            //PrintWriter out = response.getWriter();
            try {
                // on accède au fichier uploadé par le client
                Part p1 = request.getPart("urlFichier");
                InputStream is = p1.getInputStream();

                // on récupère le timestamp
                long fn = System.currentTimeMillis();
                String filename = Long.toString(fn);

                // on récupère le nom à utiliser sur le serveur
                String outputfile = this.getServletContext().getRealPath(filename + extensionFichier);  // on récupère le chemin sur le serveur
                FileOutputStream os = new FileOutputStream (outputfile);

                // on écrit les bytes du fichier de la source vers la destination
                int ch = is.read();
                while (ch != -1) {
                    os.write(ch);
                    ch = is.read();
                }
                os.close();
                
                // on crée et enregistre l'entité en BDD
                Document doc = new Document();
                doc.setIdDocument(1);
                doc.setTaille(p1.getSize()); // la taille du fichier
                doc.setTitreDocument(titre);
                doc.setDescriptionDocument(description);
                doc.setNomFichier(filename + extensionFichier);

                Utilisateur user = (Utilisateur) session.getAttribute("user");
                doc.setFKidutilisateur(user); 
                doc.setFKidcategorie(new Categorie(Integer.parseInt(categorie)));
                doc.setFKidtype(new Type(Integer.parseInt(type)));
                doc.setFKidetatdocument(new EtatDocument(1));
                doc.setFKidextension(ext);
                dFacade.create(doc);
                
                //out.println("<h3>Fichier uploadé avec succès !</h3>");
                message = titre + " correctement uploadé !";
                out.println(message);
            }
            catch(Exception ex) {
            out.println("Exception -->" + ex.getMessage());
            }
            finally { 
                out.close();
            }        
        }// fin du if
        else 
        {
            out.println(message);
        }        
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

