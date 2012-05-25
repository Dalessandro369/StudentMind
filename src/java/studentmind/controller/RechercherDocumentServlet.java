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

/**
 *
 * @author ProjetJava
 */
public class RechercherDocumentServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public RechercherDocumentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", this.getServletName());

        // Affichage des types dans la liste déroulante

        TypeFacade tFacade = ServicesLocator.getTypeFacade();
        List<Type> listType = tFacade.findAllAlpha();
        String listeHtml = "<option value=\"0\">Tous les types de document</option>";
        for (Type type : listType) {
            listeHtml += "<option value=\"" + type.getIdType() + "\">" + type.getNomType() + "</option>";
        }
        request.setAttribute("ListeType", listeHtml);

        // Affichage des catégories dans la liste déroulante
        CategorieFacade cFacade = ServicesLocator.getCategorieFacade();
        List<Categorie> listCategorie = cFacade.findAllAlpha();
        listeHtml = "<option value=\"0\">Tous les catégories de document</option>";
        for (Categorie cat : listCategorie) {
            listeHtml += "<option value=\"" + cat.getIdCategorie() + "\">" + cat.getNomCategorie() + "</option>";
        }
        request.setAttribute("ListeCategorie", listeHtml);

        // Affichage des extensions 
        ExtensionFacade eFacade = ServicesLocator.getExtensionFacade();
        List<Extension> liste = eFacade.find();
        listeHtml = "<option value=\"0\">Tous les extensions</option>";
        String prec = "";
        Famille t = null;
        for (Extension ext : liste) {

            if (!prec.equals(ext.getFKidfamille().getNomFamille())) {
                listeHtml += "<optgroup label=\"" + ext.getFKidfamille().getNomFamille() + "\">";
                listeHtml += "<option value=\"" + ext.getIdExtension() + "\">" + ext.getNomExtension() + "</option>";
                prec = ext.getFKidfamille().getNomFamille();
            } else {
                listeHtml += "<option value=\"" + ext.getIdExtension() + "\">" + ext.getNomExtension() + "</option>";
            }
        }
        request.setAttribute("ListeExtension", listeHtml);

        request.getRequestDispatcher("rechercherDocument.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


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

            requete += "WHERE UPPER(d.titreDocument) like :mot OR UPPER(d.descriptionDocument) like :mot ";

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
}
