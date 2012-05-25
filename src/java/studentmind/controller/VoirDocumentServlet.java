/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.CommentaireFacade;
import studentmind.facade.DocumentFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.TelechargementFacade;
import studentmind.model.Commentaire;
import studentmind.model.Document;

/**
 *
 * @author ProjetJava
 */
public class VoirDocumentServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public VoirDocumentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());

        String idDoc = request.getParameter("id");

        String html = "<ul>";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        Document doc = dFacade.findRang(Integer.parseInt(idDoc));
        html += " <li><strong>Type : </strong>" + doc.getFKidtype().getNomType() + "</li>"
                + "<li><strong>Matière : </strong>" + doc.getFKidcategorie().getNomCategorie() + "</li>"
                + "<li><strong>Taille du fichier : </strong>" + doc.getTaille() + " Mo</li>"
                + "<li><strong>Type de fichier : </strong>" + doc.getFKidextension().getNomExtension() + "( " + doc.getFKidextension().getFKidfamille().getNomFamille() + " )</li>"
                + "<li><strong>Téléchargé : </strong> ";
        TelechargementFacade tFacade = ServicesLocator.getTelechargementFacade();
        html += tFacade.nbrTelecharger(doc.getIdDocument()) + " fois</li>"
                + "<li>"
                + "<strong>Moyenne : </strong>"
                + "<script type=\"text/javascript\">"
                + "$(document).ready(function(){"
                + " $(\".avg_note\").jRating({"
                + "type:'small', // type of the rate.. can be set to 'small' or 'big'"
                + "length : 5, // nb of stars"
                + "decimalLength : 1, // number of decimal in the rate"
                + "isDisabled : true"
                + " });"
                + "});"
                + "</script>"
                + "<div class=\"avg_note\" data=\"2\"></div>"
                + "</li>"
                + "</ul>";
        request.setAttribute("infoDoc", html);
        html = "<div class=\"article_header\"><header><h3>" + doc.getTitreDocument() + "</h3></header></div>"
                + " <div class=\"article_content\">"
                + "<p>" + doc.getDescriptionDocument() + "</p>"
                + "</div>"
                + "<div class=\"article_footer\"><footer><strong>Catégorie : </strong>" + doc.getFKidcategorie().getNomCategorie() + "</footer></div>";
        request.setAttribute("informationDoc", html);
        html = "";
        CommentaireFacade cFacade = ServicesLocator.getCommentaireFacade();
        List<Commentaire> liste = cFacade.findCom(Integer.parseInt(idDoc));
        for (Commentaire com : liste) {

            html += "<div class=\"commentaire_icon\"></div>"
                    + "<div class=\"commentaire_header_article\"><header><strong>Auteur : </strong>" + com.getFKidutilisateur().getNom() + " " + com.getFKidutilisateur().getPrenom() + "</header></div>"
                    + "<div class=\"commentaire_content_article\">"
                    + "<blockquote>" + com.getContenu() + "</blockquote>"
                    + "</div>";
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(com.getDate().getTime());
            int jour = cal.get(Calendar.DAY_OF_MONTH);
            int mois = cal.get(Calendar.MONTH) + 1;
            String moisDate = "";
            switch (mois) {
                case 1:
                    moisDate = "Janvier";
                    break;
                case 2:
                    moisDate = "Février";
                    break;
                case 3:
                    moisDate = "Mars";
                    break;
                case 4:
                    moisDate = "Avril";
                    break;
                case 5:
                    moisDate = "Mai";
                    break;
                case 6:
                    moisDate = "Juin";
                    break;
                case 7:
                    moisDate = "Juillet";
                    break;
                case 8:
                    moisDate = "Août";
                    break;
                case 9:
                    moisDate = "Septembre";
                    break;
                case 10:
                    moisDate = "Octobre";
                    break;
                case 11:
                    moisDate = "Novembre";
                    break;
                default:
                    moisDate = "Décembre";
                    break;
            }
            int annee = cal.get(Calendar.YEAR);
            int h = cal.get(Calendar.HOUR_OF_DAY);
            int m = cal.get(Calendar.MINUTE);
            html += "<div class=\"commentaire_footer_article\"><footer><strong>Posté le : </strong>"
                    + jour + " " + moisDate + " " + annee + " à " + h + "h" + m + "</div>";

        }
        request.setAttribute("ListeCommentaire", html);
        request.getRequestDispatcher("voirDocument.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
