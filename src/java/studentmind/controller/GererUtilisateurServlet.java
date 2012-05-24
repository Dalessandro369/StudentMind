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
import studentmind.facade.RangFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.Rang;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class GererUtilisateurServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public GererUtilisateurServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());

        request.setAttribute("ListeUtilisateur", afficherUtilisateur(0));
        request.getRequestDispatcher("gererUtilisateur.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("tags");
        String type = request.getParameter("type");
        String idUtilisateur = request.getParameter("id");
        String rang = request.getParameter("rang");

        if (idUtilisateur != null && idUtilisateur.isEmpty()) {
            UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
            Utilisateur u = uFacade.find(new Utilisateur(Integer.parseInt(idUtilisateur)));
            if (type.equals("Modifier")) {
                u.setFKidrang(new Rang(Integer.parseInt(rang)));
                uFacade.edit(u);

            } else {
                uFacade.remove(u);

            }
            //request.setAttribute("ListeUtilisateur", afficherUtilisateur(0));
        } else {

            if (id != null && (!id.isEmpty())) {
                request.setAttribute("ListeUtilisateur", afficherUtilisateur(Integer.parseInt(id)));
            } else {
                request.setAttribute("ListeUtilisateur", afficherUtilisateur(0));
            }
        }

        request.getRequestDispatcher("gererUtilisateur.jsp").forward(request, response);
    }

    public String afficherUtilisateur(int id) {

        String html = "<table>"
                + "<tr>"
                + "<th>Rechercher</th>"
                + "<th>Nom et Prénom</th>"
                + "<th>Rang</th>"
                + "<th>Commentaire(s) signalé(s)</th>"
                + "<th>Etat du membre</th>"
                + "<th>Valider</th>"
                + "<th>Bannir membre</th>"
                + "</tr>";
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        List<Utilisateur> liste = uFacade.findAll();
        html += "<tr>"
                + "<td>"
                + "<script>"
                + " $(function() {"
                + "var availableTags = [";
        Utilisateur u = null;

        if (liste.size() <= 1) {
            for (Utilisateur user : liste) {
                html += "\"" + user.getIdUtilisateur() + " - " + user.getNom() + " " + user.getPrenom() + "\"";
                if (id == user.getIdUtilisateur()) {
                    u = user;
                }
            }
        } else {
            for (Utilisateur user : liste) {
                html += "\"" + user.getIdUtilisateur() + " - " + user.getNom() + " " + user.getPrenom() + "\",";
                if (id == user.getIdUtilisateur()) {
                    u = user;
                }
            }
        }

        html += "];"
                + "$( \"#tags\" ).autocomplete({"
                + "source: availableTags,"
                + "close: function(){inputRecherche();}"
                + "});"
                + "});"
                + "</script>"
                + " <div class=\"ui-widget\">"
                + "<form name='user' method='POST' action='./gerer-utilisateur.html' >"
                + "<input class=\"nom_utilisateur\" type=\"text\" name=\"tags\" id=\"tags\" placeholder=\"Nom d\'utilisateur\" />"
                + "</form>"
                + "</div>"
                + " </td>";
        if (id == 0 || u == null) {
            html += "</table>";

        } else {
            //ici ma bicheeeeeee
            html += "<form name=\"update\" method=\"POST\" action=\"./gerer-utilisateur.html\"> "
                    + "<input type=\"hidden\" name=\"id\" id=\"id\" value=\"" + u.getIdUtilisateur() + "\" />"
                    + "<input type=\"hidden\" name=\"type\" id=\"type\" />"
                    + "<td class=\"colonne_description\">" + u.getNom() + " " + u.getPrenom() + "</td>";
            RangFacade rFacade = ServicesLocator.getRangFacade();
            List<Rang> listeRang = rFacade.findAllAlpha();
            html += "<td>"
                  + "<select name=\"rang\">";
            for (Rang r : listeRang) {
                if (u.getFKidrang().equals(r)) {
                    html += "<option value=\"" + r.getIdRang() + "\"selected='selected'>" + r.getNomRang() + "</option>";
                } else {
                    html += "<option value=\"" + r.getIdRang() + "\">" + r.getNomRang() + "</option>";
                }
            }
            html += "</select>"
                    + "</td>"
                    + "</form>";
                    

            html += "<td>" + u.getNbrSignal() + "</td>"
                    + "<td>" + u.getFKidetatutlisateur().getNomEtatUtilisateur() + "</td>"
                    + "<td class=\"colonne_action\"> <img onclick=\"validerUser();\" src=\"img/accepter.png\" title=\"Ajouter\" alt=\"Ajouter\"/> </td>"
                    + "<td class=\"colonne_action\"> <img onclick=\"bannirUser();\" src=\"img/bannir.png\" title=\"Bannir\" alt=\"Bannir\" /> </td> </tr>"
                    + "</table>";

        }

        return html;

    }
}
