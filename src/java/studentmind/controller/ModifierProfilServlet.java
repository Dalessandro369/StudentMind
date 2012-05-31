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
import studentmind.facade.PaysFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.Image;
import studentmind.model.Pays;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class ModifierProfilServlet extends HttpServlet {

    HttpSession session = null;

    @Override
    public void init() throws ServletException {
    }

    public ModifierProfilServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
      
        
        request.setAttribute("information", afficherInformation());
        request.getRequestDispatcher("profil.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());  
        
        String image = request.getParameter("image");
        String ecole = request.getParameter("ecole");
        String site = request.getParameter("site");
        String ville = request.getParameter("ville");
        
        
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        Utilisateur u = (Utilisateur) session.getAttribute("user");
        Image img = new Image(1, "1");
        //changer ici quand l'uplaod marchera
        u.setFKidImage(img);
        u.setEcole(ecole);
        u.setSiteWeb(site);
        u.setVille(ville);
        
        uFacade.edit(u);
        request.setAttribute("information", afficherInformation());
        request.getRequestDispatcher("profil.jsp").forward(request, response);
        
    }

    public String afficherInformation() {

        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        String html = "";
        html += "<form method=\"POST\" action=\"modifier-profil.html\" enctype=\"multipart/form-data\">"
                + " <fieldset>"
                + "<legend>Informations générales</legend>"
                + "<label for=\"nom\">Nom :</label><span class=\"profilNom\">" + user.getNom() + "</span><br/>"
                + "<label for=\"prenom\">Prénom :</label><span class=\"profilPrenom\">" + user.getPrenom() + "</span><br/>"
                + "<label for=\"dateNaissance\">Date de naissance :</label><span class=\"profilDOB\">" + user.getDateNaissance() + "</span><br/>"
                + "<label for=\"sexe\">Sexe :</label><span class=\"profilSexe\">" + user.getSexe() + "</span><br/>"
                + "<label for=\"pays\">Pays :</label><span class=\"profilPays\">" + user.getFKidpays().getNomPays() + "</span>"
                + " </fieldset>";

        html += "<fieldset>"
                + "<legend>Profil étudiant</legend>"
                + "<label for=\"image\">Avatar :</label> <input type=\"file\" name=\"image\" id=\"image\"/><br/>"
                + "<label for=\"ecole\">Ecole / Université :</label> <input type=\"text\" name=\"ecole\" value='"+user.getEcole()+"' id=\"ecole\"  /><br/>"
                + "<label for=\"site\">Site Web :</label> <input type=\"text\" name=\"site\" value='"+user.getSiteWeb()+"' id=\"site\" /><br/>"
                + "<label for=\"ville\">Ville :</label> <input type=\"text\" name=\"ville\" value='"+user.getVille()+"' id=\"ville\" /><br/>";

        
        html += "</fieldset>"
                + "<input type=\"submit\" id=\"submit\" value=\"Je modifie!\" />"
                + " </form>";
        return html;
    }
}
