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
import studentmind.facade.CategorieFacade;
import studentmind.facade.ServicesLocator;
import studentmind.model.Categorie;

/**
 *
 * @author ProjetJava
 */
public class GererCategorieServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public GererCategorieServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        String html = "<table>"
                + "<tr>"
                + "<th class=\"colonne_nom\">Nom de la catégorie</th>"
                + "<th>Description</th>"
                + "<th>Action</th>"
                + "</tr>";
        CategorieFacade cFacade = ServicesLocator.getCategorieFacade();
        List<Categorie> liste = cFacade.findAllAlpha();
        for (Categorie cat : liste) {


            html += "<form method='POST' action='./gerer-categorie.html' >"
                        + "<tr>"
                            + "<input type=\"hidden\" name=\"id\" value = \"" + cat.getIdCategorie() + "\"/>"
                            + "<td class=\"colonne_nom\"><input type=\"text\" value=\""+ cat.getNomCategorie() + "\"required disabled /></td>"
                            + "<td class=\"colonne_description\"><input type=\"text\"" + cat.getDescriptionCategorie() + "\"required disabled /></td>"
                            + "<td class=\"colonne_action\">"
                                + "<img src=\"img/edit.png\" title=\"Editer\" alt=\"Editer\" onclick=\"Editer()\" />"
                                + "<img src=\"img/delete.png\" title=\"Supprimer\" alt=\"Supprimer\" onclick=\"Supprimer()\" />"
                            + "</td>"
                        + "</tr>"
                    + "</form>";




 



        }
        html += "<tr>"
                + "<td class=\"colonne_nom\"><input type=\"text\" name=\"nom\" id=\"nom\" placeholder=\"Nouvelle catégorie\" /></td>"
                + "<td class=\"colonne_description\"><input type=\"text\" name=\"description\" id=\"description\" placeholder=\"Description de la nouvelle catégorie\" /></td>"
                + "<td class=\"colonne_action\">"
                + "<img src=\"img/valid.png\" title=\"Ajouter\" alt=\"Ajouter\" />"
                + "</td>"
                + "</tr>"
                + "</table>";
        request.setAttribute("ListeCategorie", html);


        request.getRequestDispatcher("gererCategorie.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
