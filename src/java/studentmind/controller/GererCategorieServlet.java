/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.List;
import javax.ejb.EJBException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.CategorieFacade;
import studentmind.facade.DocumentFacade;
import studentmind.facade.MessageFacade;
import studentmind.facade.ServicesLocator;
import studentmind.model.Categorie;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class GererCategorieServlet extends HttpServlet {
    
    HttpSession session;
    Utilisateur userExp;
    Utilisateur user;

    @Override
    public void init() throws ServletException {
    }

    public GererCategorieServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(false);
        if ((session  != null) && ((Utilisateur) session.getAttribute("user") != null)) { 
            session.setAttribute("servlet", getClass().getName());
            user = (Utilisateur) session.getAttribute("user");
            request.setAttribute("nbrMess", afficherMess());
            request.setAttribute("ListeCategorie", afficheCategorie());
            request.setAttribute("nbrDocUser", afficherNombreDocUser());

            request.getRequestDispatcher("gererCategorie.jsp").forward(request, response);
        } else{
            request.getRequestDispatcher("index.jsp").forward(request, response); 
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String nom = request.getParameter("nom");
        String description = request.getParameter("des");
        String id = request.getParameter("id");
        String typeGestion = request.getParameter("typeGestion");

        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        user = (Utilisateur) session.getAttribute("user");
        if (session != null) {
            CategorieFacade cFacade = ServicesLocator.getCategorieFacade();
            Categorie cat = null;
            if (id.isEmpty()) {
                if ((!nom.equals("")) && (!description.equals(""))){
                cat = new Categorie(0);
                cat.setNomCategorie(nom);
                cat.setDescriptionCategorie(description);
                cFacade.create(cat);
                }
            } else {

                cat = cFacade.find(Integer.parseInt(id));
                if (typeGestion.equals("Modifier")) {
                    cat.setDescriptionCategorie(description);
                    cat.setNomCategorie(nom);
                    cFacade.edit(cat);
                } else {
                    try{
                        cFacade.remove(cat);
                    }catch(EJBException e){
                        
                    }
                    
                }
            }
            request.setAttribute("ListeCategorie", afficheCategorie());
            request.setAttribute("nbrDocUser", afficherNombreDocUser());
            request.setAttribute("nbrMess", afficherMess());
            request.getRequestDispatcher("gererCategorie.jsp").forward(request, response);
        }

    }

    public String afficheCategorie() {
        String html = "<table>"
                + "<tr>"
                + "<th class='colonne_nom'>Nom de la catégorie</th>"
                + "<th>Description</th>"
                + "<th>Action</th>"
                + "</tr>";
        CategorieFacade cFacade = ServicesLocator.getCategorieFacade();
        List<Categorie> liste = cFacade.findAllAlpha();
        for (Categorie cat : liste) {


            html += "<form name='ligne" + cat.getIdCategorie() + "' method='POST' action='./gerer-categorie.html' >"
                    + "<tr id='ligne" + cat.getIdCategorie() + "'>"
                    + "<input type='hidden' name='id' value ='" + cat.getIdCategorie() + "'/>"
                    + "<input type='hidden' name='typeGestion' id ='id" + cat.getIdCategorie() + "'/>"
                    + "<td class='colonne_nom'><input  type='text' id='nom" + cat.getIdCategorie() + "' name='nom' value='" + cat.getNomCategorie() + "' required disabled /></td>"
                    + "<td class='colonne_description'><textarea id='des" + cat.getIdCategorie() + "' name='des' required disabled >" + cat.getDescriptionCategorie() + "</textarea></td>"
                    + "<td class='colonne_action'>"
                    + "<img src='img/edit.png' title='Editer' class='editer' alt='Editer' id='Editer" + cat.getIdCategorie() + "' onclick='Editer(" + cat.getIdCategorie() + ")' />"
                    + "<img src='img/delete.png' title='Supprimer' class='supprimer' alt='Supprimer' id='Supprimer" + cat.getIdCategorie() + "' onclick='Supprimer(" + cat.getIdCategorie() + ")' />"
                    + "<input src='img/accepter.png' type='image' class='modifier' title='Valider' name='Modifier' onclick='Modifier(" + cat.getIdCategorie() + ")' style='display: none;' />"
                    + "<img src='img/annuler.png' alt='Annuler' class='annuler' title='Annuler' onclick='Annuler(" + cat.getIdCategorie() + ")' style='display: none;' />"
                    + "<br/><span style='display: none;' class='suppresionConfirmation'>Êtes-vous sur ?</span>"
                    + "</td>"
                    + "</tr>"
                    + "</form>";
        }
        html += "<form name='ajouter' method='POST' action='./gerer-categorie.html'>"
                + "<tr>"
                + "<input type='hidden' name='id' value =''/>"
                + "<td class='colonne_nom'><input type='text' name='nom' id='nom' required placeholder='Nouvelle catégorie' /></td>"
                + "<td class='colonne_description'><input type='text' name='des' id='description' required placeholder='Description de la nouvelle catégorie'/></td>"
                + "<td class='colonne_action'>"
                + "<img src='img/valid.png' title='Ajouter' alt='Ajouter' onclick='Ajouter()' />"
                + "</td>"
                + "</tr>"
                + "</form>"
                + "</table>";
        return html;
    }
    
    public String afficherNombreDocUser() {
        user = (Utilisateur) session.getAttribute("user");
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
