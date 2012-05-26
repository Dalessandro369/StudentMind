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
    }
}
