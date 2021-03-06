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
import studentmind.facade.*;
import studentmind.model.Categorie;
import studentmind.model.Document;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class IndexServlet extends HttpServlet {

    HttpSession session;
    Utilisateur user;

    @Override
    public void init() throws ServletException {
    }

    public IndexServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(true);
        user = (Utilisateur) session.getAttribute("user");
        session.setAttribute("servlet", getClass().getName());
        request.setAttribute("nbrDocUser", afficherNombreDocUser());
        request.setAttribute("topUser", afficherTopUser());
        request.setAttribute("nbrMess", afficherMess());
        request.setAttribute("ListeCategorie", afficherCategorie());
        request.setAttribute("DocumentUne", afficherDocument());
        request.setAttribute("nbrDoc", afficherNbrDoc());
        request.setAttribute("nbrMembre", afficherNbrMembre());
        request.setAttribute("top", afficherTop());
        request.setAttribute("afficherAvatar", afficherImage());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(false);
        if (session != null) {
            user = (Utilisateur) session.getAttribute("user");
            session.setAttribute("servlet", getClass().getName());
            request.setAttribute("nbrDocUser", afficherNombreDocUser());
            request.setAttribute("afficherAvatar", afficherImage());
        }
        request.setAttribute("ListeCategorie", afficherCategorie());
        request.setAttribute("DocumentUne", afficherDocument());
        request.setAttribute("nbrDoc", afficherNbrDoc());
        request.setAttribute("nbrMembre", afficherNbrMembre());
        request.setAttribute("top", afficherTop());
        request.setAttribute("topUser", afficherTopUser());

        request.getRequestDispatcher("/index.jsp").forward(request, response);
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

    public String afficherNbrDoc() {

        String html = "";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        html = "" + dFacade.nbrDoc();
        return html;

    }

    public String afficherNbrMembre() {

        String html = "";
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        html = "" + uFacade.nbrUser();
        return html;

    }

    public String afficherDocument() {

        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        Document doc = dFacade.documentUne();
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
    }

    public String afficherTop() {
        String html = "<ul>";
        int longeur = 0;
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

    public String afficherNombreDocUser() {

        int i = 0;
        if (user != null) {
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
            i = dFacade.nbrDocUser(user.getIdUtilisateur());
        }
        if (i >= 1) {
            return "(" + i + ")";
        } else {
            return "";
        }
    }

    public String afficherImage() {


        String html = "";

        if (user != null) {

            //html = "<img src=\""+user.getFKidImage().getUrlImage()+"\" title=\"avatar\" alt=\"avatar\" />";alt=\"avatar\"
            html = "<img src=\"upload/avatars/" + user.getFKidImage().getUrlImage() + "\" title=\"avatar\" alt=\"avatar\" height=\"70\" width=\"70\" />";
            //System.out.println(user.getFKidImage().getUrlImage());
        }

        return html;
    }

    public String afficherTopUser() {
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        String html = "<ul>";

        List<Utilisateur> liste = uFacade.topUitlisateur();
        for (Utilisateur user : liste) {
            html += "<li><a href=\"afficher-profil.html?u=" + user.getIdUtilisateur() + "\">" + user.getNom() + " " + user.getPrenom() + "</a> (" + user.getPoints() + " pts.)</li>";
        }
        html += "</ul>";
        return html;
    }

    public String afficherMess() {
        if (user != null) {
            MessageFacade mFacade = ServicesLocator.getMessageFacade();
            int nbrMessage = mFacade.nbrMessNonLu(user.getIdUtilisateur());
            int nbrTotal = mFacade.nbrMessTotal(user.getIdUtilisateur());

            if (nbrMessage == 0) {
                return "";
            }
            else {
                return "(" + nbrMessage + "/" + nbrTotal + ")";
            }
        } else return "";
    }
    
}