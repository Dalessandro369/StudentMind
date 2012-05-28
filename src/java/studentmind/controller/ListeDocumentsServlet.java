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
import studentmind.facade.DocumentFacade;
import studentmind.facade.NoteFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.TelechargementFacade;
import studentmind.model.Document;

/**
 *
 * @author ProjetJava
 */
public class ListeDocumentsServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public ListeDocumentsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        
        request.setAttribute("top", afficherTop());
        
        String idCat = request.getParameter("idCategorie");
        String html = "";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        List<Document> liste = dFacade.listDocCat(Integer.parseInt(idCat));
        for (Document doc : liste) {
            html += "<div class=\"article_header_articles\"><header>"
                    + "<h3><a href=\"voir-document.html?id=" + doc.getIdDocument() + "\">" + doc.getTitreDocument() + "</a></h3></header></div>"
                    + "<div class=\"article_content_articles\">"
                    + "<ul>"
                    + "<li><strong><span class=\"\">Type : </span></strong>" + doc.getFKidtype().getNomType() + "</li>"
                    + "<li><strong><span class=\"\">Matière : </span></strong>" + doc.getFKidcategorie().getNomCategorie() + "</li>"
                    + "<li><strong><span class=\"\">Taille du fichier : </span></strong>" + doc.getTaille() + " Mo</li>"
                    + "<li><strong><span class=\"\">Type de fichier : </span></strong>" + doc.getFKidextension().getNomExtension() + " ( " + doc.getFKidextension().getFKidfamille().getNomFamille() + " ) </li>"
                    + "<li><strong><span class=\"\">Téléchargé : </span></strong>";
            TelechargementFacade tFacade = ServicesLocator.getTelechargementFacade();
            html += tFacade.nbrTelecharger(doc.getIdDocument()) + " fois</li>"
                    + "<li><strong><span class=\"\">Note : </span></strong>";

            NoteFacade nFacade = ServicesLocator.getNoteFacade();

            html += nFacade.moyenne(doc.getIdDocument()) + "/5</li>"
                    + "</ul>"
                    + "</div>"
                    + "<div class=\"article_footer\"><footer></footer></div>";

        }
      
        request.setAttribute("listeDocument", html);
        request.getRequestDispatcher("listeDocuments.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        request.setAttribute("top", afficherTop());

        boolean champOk = true;
        String mesType = "";
        String mesCategorie = "";
        String mesExtension = "";
        String strExtWhere = "";

        //Recupérer le formulaire
        String type = request.getParameter("type");
        String categorie = request.getParameter("categorie");
        String extension = request.getParameter("extension");
        String mot = request.getParameter("mot-cle");
        String strType, strCat, strExt;
        strType = strCat = strExt = "";
        String requete = "";

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
        if (extension == null || extension.isEmpty()) {
            mesExtension = "Veuillez à remplir correctement la extension";
            request.setAttribute("ErreurCategorie", mesExtension);
            champOk = false;
        }
        if (mot == null || mot.isEmpty()) {
            mot = "";
        }
        if (champOk) {

            requete += " WHERE (UPPER(d.titreDocument) like :mot OR UPPER(d.descriptionDocument) like :mot) AND etat.idEtatDocument = 2";

            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
            List<Document> liste = dFacade.find(requete, mot);
            String listeHtml = "";
            Boolean ok = true;
            for (Document doc : liste) {
                //après vérifier si l'etat du document
                if ((!categorie.equals("0")) && doc.getFKidcategorie().getIdCategorie() != Integer.parseInt(categorie)) {
                    ok = false;
                }
                if (ok && (!extension.equals("0")) && doc.getFKidextension().getIdExtension() != Integer.parseInt(extension)) {
                    ok = false;
                }
                if (ok && (!type.equals("0")) && doc.getFKidtype().getIdType() != Integer.parseInt(type)) {
                    ok = false;
                }
                if (ok) {
                    listeHtml += "<div class=\"article_header_articles\"><header>"
                            + "<h3><a href=\"voir-document.html?id=" + doc.getIdDocument() + "\">" + doc.getTitreDocument() + "</a></h3></header></div>"
                            + "<div class=\"article_content_articles\">"
                            + "<ul>"
                            + "<li><strong><span class=\"\">Type : </span></strong>" + doc.getFKidtype().getNomType() + "</li>"
                            + "<li><strong><span class=\"\">Matière : </span></strong>" + doc.getFKidcategorie().getNomCategorie() + "</li>"
                            + "<li><strong><span class=\"\">Taille du fichier : </span></strong>" + doc.getTaille() + " Mo</li>"
                            + "<li><strong><span class=\"\">Type de fichier : </span></strong>" + doc.getFKidextension().getNomExtension() + " ( " + doc.getFKidextension().getFKidfamille().getNomFamille() + " ) </li>"
                            + "<li><strong><span class=\"\">Téléchargé : </span></strong>";
                    TelechargementFacade tFacade = ServicesLocator.getTelechargementFacade();
                    listeHtml += tFacade.nbrTelecharger(doc.getIdDocument()) + " fois</li>"
                            + "<li><strong><span class=\"\">Note : </span></strong>";
                    
                            NoteFacade nFacade = ServicesLocator.getNoteFacade();
                            
                       listeHtml += nFacade.moyenne(doc.getIdDocument()) +"/5</li>"
                            + "</ul>"
                            + "</div>"
                            + "<div class=\"article_footer\"><footer></footer></div>";
                } else {
                    ok = true;
                }

            }

            if (listeHtml.equals("")) {
                listeHtml = "Aucun résultat";
            }
            request.setAttribute("listeDocument", listeHtml);
        }
        request.getRequestDispatcher("listeDocuments.jsp").forward(request, response);
    }
    
    public String afficherTop(){
        String html = "<ul>";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        List<Document> liste = dFacade.top3();
        for (Document doc :liste){
            html += "<li><strong>"+doc.getTitreDocument()+"</strong> - "+doc.getDescriptionDocument().substring(0, 150) +" <a href=\"voir-document.html?id=" + doc.getIdDocument()+"\"> Lire la suite</a></li>";
        }
        html += "</ul>";
        return html;
    }
}
