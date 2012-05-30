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
import studentmind.model.*;

/**
 *
 * @author ProjetJava
 */
public class SignalerCommentaireServlet extends HttpServlet {

    HttpSession session;

    public void init() throws ServletException {
    }

    public SignalerCommentaireServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession(false);

        if (session != null && ((Utilisateur) session.getAttribute("user"))!= null) {
            session.setAttribute("servlet", getClass().getName());
            
            String idDoc = (String) session.getAttribute("idDocument");
            String idCom = request.getParameter("c");

            CommentaireFacade cFacade = ServicesLocator.getCommentaireFacade();
            Commentaire commentaire = cFacade.find(Integer.parseInt(idCom));
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
            Document document = dFacade.findRang(Integer.parseInt(idDoc));            
            Utilisateur user = (Utilisateur) session.getAttribute("user");
            
            commentaire.setFKidetatcommentaire(new EtatCommentaire(3));
            commentaire.setFKidutilisateursignaleur(new Utilisateur(user.getIdUtilisateur()));
            
            cFacade.edit(commentaire);

            String html = "<ul>";

            html += " <li><strong>Type : </strong>" + document.getFKidtype().getNomType() + "</li>"
                    + "<li><strong>Matière : </strong>" + document.getFKidcategorie().getNomCategorie() + "</li>"
                    + "<li><strong>Taille du fichier : </strong>" + document.getTaille() + " Mo</li>"
                    + "<li><strong>Type de fichier : </strong>" + document.getFKidextension().getNomExtension() + "( " + document.getFKidextension().getFKidfamille().getNomFamille() + " )</li>"
                    + "<li><strong>Téléchargé : </strong> ";
            
            TelechargementFacade tFacade = ServicesLocator.getTelechargementFacade();
            NoteFacade nFacade = ServicesLocator.getNoteFacade();

            html += tFacade.nbrTelecharger(document.getIdDocument()) + " fois</li>"
                    + "<li>"
                    + "<strong>Moyenne : </strong>"
                    + "<div class=\"avg_note\" data=\"" + nFacade.moyenne(document.getIdDocument()) + "\"></div>"
                    + "<script type=\"text/javascript\">"
                    + "$(document).ready(function(){"
                    + "$(\".avg_note\").jRating({"
                    + "type:'small'," // type of the rate.. can be set to 'small' or 'big'
                    + "length : 5," // nb of stars
                    + "decimalLength : 1," // number of decimal in the rate
                    + "isDisabled : true,"
                    + "});"
                    + "});"
                    + "</script>"
                    + "</li>"
                    + "</ul>";
            request.setAttribute("infoDoc", html);
            html = "<div class=\"article_header\"><header>";
            html += "<h3>" + document.getTitreDocument() + "</h3>"
                    + "</header></div>"
                    + " <div class=\"article_content\">"
                    + "<p>" + document.getDescriptionDocument() + "</p>"
                    + "</div>"
                    + "<div class=\"article_footer\"><footer><strong>Catégorie : </strong>" + document.getFKidcategorie().getNomCategorie() + "</footer></div>";
            request.setAttribute("informationDoc", html);
            html = "";

            List<Commentaire> liste = cFacade.findCom(document.getIdDocument());
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
                        + jour + " " + moisDate + " " + annee + " à " + h + "h" + m;
                if (com.getFKidetatcommentaire().getIdEtatCommentaire() == 2) {
                    html += " - <img src=\"img/signaler.png\" title=\"Signaler un abus\" alt=\"\" /><a href=\"signaler-commentaire.html?c=" + com.getIdCommentaire() + "\">Signaler le commentaire</a>";
                } else {
                    html += " - Commentaire approuvé";
                }
                html += "</div>";
            }

            request.setAttribute("ListeCommentaire", html);
            request.setAttribute("top", afficherTop());
            request.setAttribute("nbrDocUser", afficherNombreDocUser());

            request.getRequestDispatcher("voirDocument.jsp").forward(request, response);
        }
        session.setAttribute("servlet", getClass().getName());
        request.setAttribute("nbrDocUser", afficherNombreDocUser());


        request.setAttribute("ListeCategorie", afficherCategorie());
        request.setAttribute("DocumentUne", afficherDocument());
        request.setAttribute("nbrDoc", afficherNbrDoc());
        request.setAttribute("nbrMembre", afficherNbrMembre());
        request.setAttribute("top", afficherTop());
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    public String afficherNombreDocUser() {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
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

  

  
}
