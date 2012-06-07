/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class TelechargerDocumentServlet extends HttpServlet {

    Utilisateur user2;
    HttpSession session;
    Document doc;

    @Override
    public void init() throws ServletException {
    }

    public TelechargerDocumentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(false);

        String idDoc = request.getParameter("doc");
        String urlDoc = request.getParameter("urlDoc");

        user2 = (Utilisateur) session.getAttribute("user");
        TelechargementFacade tfacade = ServicesLocator.getTelechargementFacade();
        UtilisateurFacade ufacade = ServicesLocator.getUtilisateurFacade();
        if (user2.getPoints() >= 10) {
            Telechargement tel = new Telechargement();
            tel.setDateTelechargement(new Date());
            tel.setDocumentIdDocument(new Document(Integer.parseInt(idDoc)));
            tel.setUtlisateurIdUtilisateur(user2);
            tel.setIdTelechargement(1);
            tfacade.create(tel);
            user2.setPoints(user2.getPoints() - 10);
            ufacade.edit(user2);
        }
      
    }

    public String afficherCategorie() {

        String html = "";

        CategorieFacade cFacade = ServicesLocator.getCategorieFacade();
        List<Categorie> liste = cFacade.findAllAlpha();
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();

        for (Categorie cat : liste) {
            if (dFacade.nbrDocCat(cat.getIdCategorie()) > 0) {
                html += "<ul>"
                        + "<li><a href=\"liste-documents.html?idCategorie=" + cat.getIdCategorie() + "\">" + cat.getNomCategorie() + "</a> (" + dFacade.nbrDocCat(cat.getIdCategorie()) + ")</li>"
                        + "</ul>";
            }
        }
        return html;
    }

    public String afficherDocument() {

        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        Document doc = dFacade.documentUne();
        if (doc != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(doc.getDate().getTime());
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
            String html = "<div class=\"article_header\"><header><h3>" + doc.getTitreDocument() + "</h3></header></div>"
                    + "<div class=\"article_content\">"
                    + "<p>" + doc.getDescriptionDocument() + "</p>"
                    + "</div>"
                    + "<div class=\"article_footer\">"
                    + "<footer><strong>Ajouté le</strong> "
                    + jour + " " + moisDate + " " + annee
                    + "<strong> par</strong> "
                    + doc.getFKidutilisateur().getNom() + " " + doc.getFKidutilisateur().getPrenom()
                    + "</footer></div>"
                    + ""
                    + ""
                    + "";

            return html;
        } else {
            return "";
        }
    }

    public String afficherNbrDoc() {

        String html = "";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        html = "" + dFacade.nbrDoc();
        return html;

    }

    public String afficherTop() {
        String html = "<ul>";
        int longeur;
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        List<Document> liste = dFacade.top3();
        for (Document doc : liste) {
            longeur = doc.getDescriptionDocument().length();
            if (longeur >= 150) {
                longeur = 150;
            }
            html += "<li><strong>" + doc.getTitreDocument() + "</strong> - " + doc.getDescriptionDocument().substring(0, longeur) + " <a href=\"voir-document.html?id=" + doc.getIdDocument() + "\"> Lire la suite</a></li>";
        }
        html += "</ul>";
        return html;
    }

    public String afficherTopUser() {
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        String html = "<ul>";

        List<Utilisateur> liste = uFacade.topUitlisateur();
        for (Utilisateur urser2 : liste) {
            html += "<li><a href=\"afficher-profil.html?u=" + urser2.getIdUtilisateur() + "\">" + urser2.getNom() + " " + urser2.getPrenom() + "</a> (" + urser2.getPoints() + " pts.)</li>";
        }
        html += "</ul>";
        return html;

    }

    public String afficherNombreDocUser() {

        int i = 0;
        if (user2 != null) {
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
            i = dFacade.nbrDocUser(user2.getIdUtilisateur());
        }
        if (i >= 1) {
            return "(" + i + ")";
        } else {
            return "";
        }
    }

    public String afficherNbrMembre() {

        String html = "";
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        html = "" + uFacade.nbrUser();
        return html;

    }

    public String afficherTelechargement() {
        String html = "<form method='GET' action='telecharger-document.html'>"
                + "<input type='hidden' name='idDoc' id='idDoc' value='" + doc.getIdDocument() + "'/>"
                + "<figcaption>";
        if (user2 != null) {
            if (user2.getPoints() >= 10) {
                html += "<input type='hidden' name='urlDoc' id='urlDoc' value='" + doc.getNomFichier() + "' />"
                        + "<a href='#' id='lien_dl'>Télécharger</a>";
            } else {
                html += "<span>Désolé, vous n'avez pas assez de point que pour télécharger !</span>";
            }
            html += "</figcaption>"
                    + "</form>";
            return html;
        } else {
            return "";
        }
    }

    public String afficherMess() {
        if (user2 != null) {
            MessageFacade mFacade = ServicesLocator.getMessageFacade();
            int nbrMessage = mFacade.nbrMessNonLu(user2.getIdUtilisateur());
            int nbrTotal = mFacade.nbrMessTotal(user2.getIdUtilisateur());

            if (nbrMessage == 0) {
                return "";
            } else {
                return "(" + nbrMessage + "/" + nbrTotal + ")";
            }
        } else {
            return "";
        }
    }
}
