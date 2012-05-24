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
import studentmind.facade.ExtensionFacade;
import studentmind.facade.FamilleFacade;
import studentmind.facade.ServicesLocator;
import studentmind.model.Categorie;
import studentmind.model.Extension;
import studentmind.model.Famille;

/**
 *
 * @author ProjetJava
 */
public class GererExtensionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public GererExtensionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());

        request.setAttribute("ListeExtension", afficheExtension());
        request.getRequestDispatcher("gererExtension.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String description = request.getParameter("des");
        String id = request.getParameter("id");
        String famille = request.getParameter("famille");
        String typeGestion = request.getParameter("typeGestion");

        HttpSession session = request.getSession(false);
        if (session != null) {
            ExtensionFacade eFacade = ServicesLocator.getExtensionFacade();
            Extension ext = null;
            if (id.isEmpty()) {
                if ((!nom.equals("")) && (!description.equals(""))) {
                    ext = new Extension(0);
                    ext.setNomExtension(nom);
                    ext.setDescriptionExtension(description);
                    ext.setFKidfamille(new Famille(Integer.parseInt(famille)));
                    eFacade.create(ext);
                }
            } else {

                ext = eFacade.find(Integer.parseInt(id));
                if (typeGestion.equals("Modifier")) {
                    ext.setDescriptionExtension(description);
                    ext.setNomExtension(nom);
                    ext.setFKidfamille(new Famille(Integer.parseInt(famille)));
                    eFacade.edit(ext);
                } else {
                    eFacade.remove(ext);
                }
            }
            request.setAttribute("ListeExtension", afficheExtension());
            request.getRequestDispatcher("gererExtension.jsp").forward(request, response);
        }
    }

    public String afficheExtension() {
        String html = "<table>"
                + "<tr>"
                + "<th class='colonne_nom'>Nom d'extension</th>"
                + "<th>Description</th>"
                + "<th>Famille</td>"
                + "<th>Action</th>"
                + "</tr>";
        ExtensionFacade eFacade = ServicesLocator.getExtensionFacade();
        List<Extension> liste = eFacade.findAllAlpha();
        for (Extension ext : liste) {
            html += "<form name='ligne" + ext.getIdExtension() + "' method='POST' action='./gerer-extension.html' >"
                    + "<tr id='ligne" + ext.getIdExtension() + "'>"
                    + "<input type='hidden' name='id' value ='" + ext.getIdExtension() + "'/>"
                    + "<input type='hidden' name='typeGestion' id ='id" + ext.getIdExtension() + "'/>"
                    + "<td class='colonne_nom'><input  type='text' id='nom" + ext.getIdExtension() + "' name='nom' value='" + ext.getNomExtension() + "' required disabled /></td>"
                    + "<td class='colonne_description'><textarea id='des" + ext.getIdExtension() + "' name='des' required disabled >" + ext.getDescriptionExtension() + "</textarea></td>"
                    + "<td id=\"Famille\">" + afficherFamille(ext.getIdExtension(),ext.getFKidfamille().getIdFamille()) + "</td>"
                    + "<td class='colonne_action'>"
                    + "<img src='img/edit.png' title='Editer' class='editer' alt='Editer' id='Editer" + ext.getIdExtension() + "' onclick='Editer(" + ext.getIdExtension() + ")' />"
                    + "<img src='img/delete.png' title='Supprimer' class='supprimer' alt='Supprimer' id='Supprimer" + ext.getIdExtension() + "' onclick='Supprimer(" + ext.getIdExtension() + ")' />"
                    + "<input src='img/accepter.png' type='image' class='modifier' title='Valider' name='Modifier' onclick='Modifier(" + ext.getIdExtension() + ")' style='display: none;' />"
                    + "<img src='img/annuler.png' alt='Annuler' class='annuler' title='Annuler' onclick='Annuler(" + ext.getIdExtension() + ")' style='display: none;' />"
                    + "<br/><span style='display: none;' class='suppresionConfirmation'>Êtes-vous sur ?</span>"
                    + "</td>"
                    + "</tr>"
                    + "</form>";
        }
        html += "<form name='ajouter' method='POST' action='./gerer-extension.html'>"
                + "<tr>"
                + "<input type='hidden' name='id' value =''/>"
                + "<td class='colonne_nom'><input type='text' name='nom' id='nom' required placeholder='Nouvelle extension' /></td>"
                + "<td class='colonne_description'><input type='text' name='des' id='description' required placeholder='Description de la nouvelle extension'/></td>"
                + "<td>" + afficherFamille() + "</td>"
                + "<td class='colonne_action'>"
                + "<img src='img/valid.png' title='Ajouter' alt='Ajouter' onclick='Ajouter()' />"
                + "</td>"
                + "</tr>"
                + "</form>"
                + "</table>";
        return html;
    }

    public String afficherFamille(int id,int famille) {
        FamilleFacade fFacade = ServicesLocator.getFamilleFacade();
        List<Famille> liste = fFacade.findAllAlpha();
        String html = "";
        html = "<select name=\"famille\" id=\"famille" + id + "\"disabled>";
        for (Famille fam : liste) {
            if (famille == fam.getIdFamille()) {
            html += "<option value=\"" + fam.getIdFamille() + "\"selected=\"selected\">" + fam.getNomFamille() + "</option>";
            }else  html += "<option value=\"" + fam.getIdFamille() + "\">" + fam.getNomFamille() + "</option>";
        }
        html += "</select>";
        return html;
    }
    public String afficherFamille() {
        FamilleFacade fFacade = ServicesLocator.getFamilleFacade();
        List<Famille> liste = fFacade.findAllAlpha();
        String html = "";
        html = "<select name=\"famille\" id=\"famille\">";

        for (Famille fam : liste) {          
                    
                    html += "<option value=\"" + fam.getIdFamille() + "\">" + fam.getNomFamille() + "</option>";
        }


        html += "</select>";
        return html;
    }
}
