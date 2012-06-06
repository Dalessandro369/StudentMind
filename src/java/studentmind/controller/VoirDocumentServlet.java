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
public class VoirDocumentServlet extends HttpServlet {

    HttpSession session;
    Utilisateur userTest;
    Document document;

    @Override
    public void init() throws ServletException {
    }

    public VoirDocumentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        userTest = (Utilisateur) session.getAttribute("user");
        String idDoc = request.getParameter("id");
        try {
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
            Document doc = dFacade.findRang(Integer.parseInt(idDoc));
            document = doc;
            String html = "<ul>";


            session.setAttribute("typeFamille", doc.getFKidextension().getFKidfamille().getNomFamille());
            html += " <li><strong>Type : </strong>" + doc.getFKidtype().getNomType() + "</li>"
                    + "<li><strong>Matière : </strong>" + doc.getFKidcategorie().getNomCategorie() + "</li>"
                    + "<li><strong>Taille du fichier : </strong>" + doc.getTaille() + " Ko</li>"
                    + "<li><strong>Type de fichier : </strong>" + doc.getFKidextension().getNomExtension() + "( " + doc.getFKidextension().getFKidfamille().getNomFamille() + " )</li>"
                    + "<li><strong>Téléchargé : </strong> ";
            TelechargementFacade tFacade = ServicesLocator.getTelechargementFacade();
            NoteFacade nFacade = ServicesLocator.getNoteFacade();
            int test = nFacade.NoteTest(Integer.parseInt(idDoc));
            html += tFacade.nbrTelecharger(doc.getIdDocument()) + " fois</li>"
                    + "<li>";

            if (test != 0) {
                html += "<strong>Moyenne : </strong>" + nFacade.moyenne(doc.getIdDocument()) + "/5";
            } else {
                html += " Le document n'a pas été évalué";
            }
            html += "</li>"
                    + "</ul>";
            if (userTest != null) {
                test = nFacade.verifNote(Integer.parseInt(idDoc), userTest.getIdUtilisateur());
                int testTelechargement = tFacade.nbrTelechargerVerif(Integer.parseInt(idDoc), userTest.getIdUtilisateur());
                System.out.println(test + " " + testTelechargement);
                if ((test == 0) && (testTelechargement == 1)) {
                    html += "<div class=\"menu\">"
                            + "<header><h3>Noter le document</h3></header>"
                            + "<form method='POST' action='voir-document.html'>"
                            + "<select name='note' >"
                            + "<option value='1'>1</option>"
                            + "<option value='2'>2</option>"
                            + "<option value='3'>3</option>"
                            + "<option value='4'>4</option>"
                            + "<option value='5'>5</option>"
                            + "</select>"
                            + "<input type='hidden' value='" + doc.getIdDocument() + "' name='idDoc'/>"
                            + "<input type='submit'/>"
                            + "</form>"
                            + "</div>";

                } else if (testTelechargement == 0) {
                    html += "<div class=\"menu\">"
                            + "<header><h3>Noter le document</h3></header>"
                            + "<p>Veuillez télécharger le document pour mettre une note</p>"
                            + "</div>";
                }
            }
            request.setAttribute("infoDoc", html);

            html = "<div class=\"article_header\"><header><h3>" + doc.getTitreDocument() + "</h3></header></div>"
                    + " <div class=\"article_content\">"
                    + "<p>" + doc.getDescriptionDocument() + "</p>"
                    + "</div>"
                    + "<div class=\"article_footer\"><footer><strong>Catégorie : </strong>" + doc.getFKidcategorie().getNomCategorie() + "</footer></div>";
            //si il la telecharger et qu'il na pas note


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
                        + jour + " " + moisDate + " " + annee + " à " + h + "h" + m;
                if (com.getFKidetatcommentaire().getIdEtatCommentaire() == 2) {
                    if (userTest != null) {
                        html += " - <img src=\"img/signaler.png\" title=\"Signaler un abus\" alt=\"\" /><a href=\"signaler-commentaire.html?c=" + com.getIdCommentaire() + "\">Signaler le commentaire</a>";
                    }
                } else {
                    html += " - Commentaire approuvé";
                }
                html += "</div>";
            }
            session.setAttribute("idDocument", idDoc);
            request.setAttribute("ListeCommentaire", html);
            request.setAttribute("top", afficherTop());
            request.setAttribute("nbrDocUser", afficherNombreDocUser());
            request.setAttribute("lien", afficherTelechargement());
            request.setAttribute("nbrMess", afficherMess());
            request.getRequestDispatcher("voirDocument.jsp").forward(request, response);

        } catch (NumberFormatException e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        userTest = (Utilisateur) session.getAttribute("user");

        String note = request.getParameter("note");
        String idDoc = request.getParameter("idDoc");


        try {
            int intNote = Integer.parseInt(note);
            int intIdDoc = Integer.parseInt(idDoc);
            if (userTest != null) {
                request.setAttribute("nbrMess", afficherMess());
                NoteFacade nFacade = ServicesLocator.getNoteFacade();
                Note n = new Note();
                NotePK nPk = new NotePK(userTest.getIdUtilisateur(), intIdDoc);
                n.setNotePK(nPk);

                n.setNote(intNote);
                // n.setDocument(new Document(intIdDoc));
                //  n.setUtilisateur(userTest);
                nFacade.create(n);

                DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
                Document doc = dFacade.findRang(Integer.parseInt(idDoc));
                String html = "<ul>";


                session.setAttribute("typeFamille", doc.getFKidextension().getFKidfamille().getNomFamille());
                html += " <li><strong>Type : </strong>" + doc.getFKidtype().getNomType() + "</li>"
                        + "<li><strong>Matière : </strong>" + doc.getFKidcategorie().getNomCategorie() + "</li>"
                        + "<li><strong>Taille du fichier : </strong>" + doc.getTaille() + " Mo</li>"
                        + "<li><strong>Type de fichier : </strong>" + doc.getFKidextension().getNomExtension() + "( " + doc.getFKidextension().getFKidfamille().getNomFamille() + " )</li>"
                        + "<li><strong>Téléchargé : </strong> ";
                TelechargementFacade tFacade = ServicesLocator.getTelechargementFacade();


                int test = nFacade.NoteTest(Integer.parseInt(idDoc));
                html += tFacade.nbrTelecharger(doc.getIdDocument()) + " fois</li>"
                        + "<li>";

                if (test != 0) {
                    html += "<strong>Moyenne : </strong>" + nFacade.moyenne(doc.getIdDocument()) + "/5";
                } else {
                    html += " Le document n'a pas été évalué";
                }
                html += "</li>"
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
                            + jour + " " + moisDate + " " + annee + " à " + h + "h" + m;
                    if (com.getFKidetatcommentaire().getIdEtatCommentaire() == 2) {
                        html += " - <img src=\"img/signaler.png\" title=\"Signaler un abus\" alt=\"\" /><a href=\"signaler-commentaire.html?c=" + com.getIdCommentaire() + "\">Signaler le commentaire</a>";
                    } else {
                        html += " - Commentaire approuvé";
                    }
                    html += "</div>";
                    request.setAttribute("ListeCommentaire", html);
                }
            }
        } catch (NumberFormatException e) {

            request.setAttribute("ListeCategorie", afficherCategorie());
            request.setAttribute("DocumentUne", afficherDocument());
            request.setAttribute("nbrDoc", afficherNbrDoc());
            request.setAttribute("nbrMembre", afficherNbrMembre());
            request.setAttribute("top", afficherTop());
            request.setAttribute("topUser", afficherTopUser());
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }


        request.setAttribute("top", afficherTop());
        request.setAttribute("nbrDocUser", afficherNombreDocUser());
        request.setAttribute("lien", afficherTelechargement());
        request.getRequestDispatcher("voirDocument.jsp").forward(request, response);

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
        if (userTest != null) {
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
            i = dFacade.nbrDocUser(userTest.getIdUtilisateur());
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
                + "<input type='hidden' name='idDoc' id='idDoc' value='"+document.getIdDocument()+"'/>"
                + "<figcaption>";
        if (userTest != null) {
            if (userTest.getPoints() >= 10) {              
                html += "<input type='hidden' name='urlDoc' id='urlDoc' value='"+document.getNomFichier()+"' />"
                     +  "<a href='#' id='lien_dl'>Télécharger</a>";
            }
            else{
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
        if (userTest != null) {
            MessageFacade mFacade = ServicesLocator.getMessageFacade();
            int nbrMessage = mFacade.nbrMessNonLu(userTest.getIdUtilisateur());
            int nbrTotal = mFacade.nbrMessTotal(userTest.getIdUtilisateur());

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
