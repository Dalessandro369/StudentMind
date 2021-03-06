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
import studentmind.model.EtatUtilisateur;
import studentmind.model.Rang;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class GererUtilisateurServlet extends HttpServlet {

    HttpSession session;
    Utilisateur userExp;
    Utilisateur user;

    @Override
    public void init() throws ServletException {
    }

    public GererUtilisateurServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(false);
        if ((session != null) && ((Utilisateur) session.getAttribute("user") != null)) {
            session.setAttribute("servlet", getClass().getName());
            user = (Utilisateur) session.getAttribute("user");
            request.setAttribute("nbrDocUser", afficherNombreDocUser());
            request.setAttribute("ListeUtilisateur", afficherUtilisateur(0));
            request.setAttribute("nbrMess", afficherMess());
            request.getRequestDispatcher("gererUtilisateur.jsp").forward(request, response);
        } else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        String id = request.getParameter("tags");
        String type = request.getParameter("type");
        String idUtilisateur = request.getParameter("id");
        String rang = request.getParameter("rang");
        user = (Utilisateur) session.getAttribute("user");
        
        if (idUtilisateur != null && (!idUtilisateur.isEmpty())) {
            UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
            Utilisateur u = uFacade.findId(Integer.parseInt(idUtilisateur));
            System.out.println(type); 
            if (type.equals("Modifier")) {
                u.setFKidrang(new Rang(Integer.parseInt(rang)));                
            } else {
                u.setFKidetatutlisateur(new EtatUtilisateur(3));                
            }
            uFacade.edit(u);
            request.setAttribute("ListeUtilisateur", afficherUtilisateur(0));
        } else {
            try {
                int i = Integer.parseInt(id);
                request.setAttribute("ListeUtilisateur", afficherUtilisateur(Integer.parseInt(id)));
                request.setAttribute("nbrDocUser", afficherNombreDocUser());               
                request.setAttribute("nbrMess", afficherMess());
            } catch (Exception e) {
                request.setAttribute("ListeUtilisateur", afficherUtilisateur(0));
                request.setAttribute("nbrDocUser", afficherNombreDocUser());
                request.setAttribute("nbrMess", afficherMess());
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
            for (Utilisateur user2 : liste) {
                html += "\"" + user2.getIdUtilisateur() + " - " + user2.getNom() + " " + user2.getPrenom() + "\"";
                if (id == user2.getIdUtilisateur()) {
                    u = user2;
                }
            }
        } else {
            for (Utilisateur user2 : liste) {
                html += "\"" + user2.getIdUtilisateur() + " - " + user2.getNom() + " " + user2.getPrenom() + "\",";
                if (id == user2.getIdUtilisateur()) {
                    u = user2;
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
                //--------------------------
                //Verifier en js que lorsqu'on sort du champ que sa soit un nombre si oui ok si pas remettre le champ a blanc
                + "<input class=\"nom_utilisateur\" type=\"text\" name=\"tags\" id=\"tags\" placeholder=\"Nom d\'utilisateur\" />"
                
                + "</form>"
                + "</div>"
                + " </td>";
        if (id == 0 || u == null) {
            html += "</table>";

        } else {

            html += "<td class=\"colonne_description\">" + u.getNom() + " " + u.getPrenom() + "</td>";
            RangFacade rFacade = ServicesLocator.getRangFacade();
            List<Rang> listeRang = rFacade.findAllAlpha();
            html += "<td>"
                    + "<form name=\"update\" method=\"POST\" action=\"./gerer-utilisateur.html\"> "
                    + "<input type=\"hidden\" name=\"id\" id=\"id\" value=\"" + u.getIdUtilisateur() + "\" />"
                    + "<input type=\"hidden\" name=\"type\" id=\"type\" />";
            if (u.getFKidrang().getIdRang() != 3) {
                html += "<select name=\"rang\">";
            } else {
                html += "<select name=\"rang\" disabled='disabled'>";
            }
            for (Rang r : listeRang) {
                if (u.getFKidrang().equals(r)) {
                    html += "<option value=\"" + r.getIdRang() + "\"selected='selected'>" + r.getNomRang() + "</option>";
                } else {
                    html += "<option value=\"" + r.getIdRang() + "\">" + r.getNomRang() + "</option>";
                }
            }
            html += "</select>"
                    + "</form>"
                    + "</td>";


            html += "<td>" + u.getNbrSignal() + "</td>"
                    + "<td>" + u.getFKidetatutlisateur().getNomEtatUtilisateur() + "</td>";
            if (u.getFKidrang().getIdRang() != 3) {
                html += "<td class=\"colonne_action\"> <img onclick=\"validerUser();\" src=\"img/accepter.png\" title=\"Ajouter\" alt=\"Ajouter\"/> </td>"
                        + "<td class=\"colonne_action\"> <img onclick=\"bannirUser();\" src=\"img/bannir.png\" title=\"Bannir\" alt=\"Bannir\" /> </td> </tr>";
            }
            html += "</table>";

        }

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

    public String afficherMess() {
        if (user != null) {
            MessageFacade mFacade = ServicesLocator.getMessageFacade();
            int nbrMessage = mFacade.nbrMessNonLu(user.getIdUtilisateur());
            int nbrTotal = mFacade.nbrMessTotal(user.getIdUtilisateur());

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
