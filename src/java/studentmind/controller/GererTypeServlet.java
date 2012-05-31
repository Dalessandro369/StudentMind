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
import studentmind.facade.ServicesLocator;
import studentmind.facade.TypeFacade;
import studentmind.model.Type;

/**
 *
 * @author ProjetJava
 */
public class GererTypeServlet extends HttpServlet {
    @Override
    public void init() throws ServletException{
    }

    public GererTypeServlet() {
        super();
    }    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());    
         request.setAttribute("ListeType", afficheType());
        request.getRequestDispatcher("gererType.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String nom = request.getParameter("nom");
        String description = request.getParameter("des");
        String id = request.getParameter("id");
        String typeGestion = request.getParameter("typeGestion");

        HttpSession session = request.getSession(false);
        if (session != null) {
            TypeFacade tFacade = ServicesLocator.getTypeFacade();
            Type type = null;
            if (id.isEmpty()) {
                if ((!nom.equals("")) && (!description.equals(""))){
                type = new Type(0);
                type.setNomType(nom);
                type.setDescriptionType(description);
                tFacade.create(type);
                }
            } else {

                type = tFacade.find(Integer.parseInt(id));
                if (typeGestion.equals("Modifier")) {
                    type.setDescriptionType(description);
                    type.setNomType(nom);
                    tFacade.edit(type);
                } else {
                     try{
                       tFacade.remove(type);
                    }catch(EJBException e){
                        
                    }
                    
                }
            }
            request.setAttribute("ListeType", afficheType());
            request.getRequestDispatcher("gererType.jsp").forward(request, response);
        }
    }
    public String afficheType() {
        String html = "<table>"
                + "<tr>"
                + "<th class='colonne_nom'>Nom du type</th>"
                + "<th>Description</th>"
                + "<th>Action</th>"
                + "</tr>";
        TypeFacade tFacade = ServicesLocator.getTypeFacade();
        List<Type> liste = tFacade.findAllAlpha();
        for (Type type : liste) {


            html += "<form name='ligne" + type.getIdType() + "' method='POST' action='./gerer-type.html' >"
                    + "<tr id='ligne" + type.getIdType() + "'>"
                    + "<input type='hidden' name='id' value ='" + type.getIdType() + "'/>"
                    + "<input type='hidden' name='typeGestion' id ='id" + type.getIdType() + "'/>"
                    + "<td class='colonne_nom'><input  type='text' id='nom" + type.getIdType() + "' name='nom' value='" + type.getNomType() + "' required disabled /></td>"
                    + "<td class='colonne_description'><textarea id='des" + type.getIdType() + "' name='des' required disabled >" + type.getDescriptionType() + "</textarea></td>"
                    + "<td class='colonne_action'>"
                    + "<img src='img/edit.png' title='Editer' class='editer' alt='Editer' id='Editer" + type.getIdType() + "' onclick='Editer(" + type.getIdType() + ")' />"
                    + "<img src='img/delete.png' title='Supprimer' class='supprimer' alt='Supprimer' id='Supprimer" + type.getIdType() + "' onclick='Supprimer(" + type.getIdType() + ")' />"
                    + "<input src='img/accepter.png' type='image' class='modifier' title='Valider' name='Modifier' onclick='Modifier(" + type.getIdType() + ")' style='display: none;' />"
                    + "<img src='img/annuler.png' alt='Annuler' class='annuler' title='Annuler' onclick='Annuler(" + type.getIdType() + ")' style='display: none;' />"
                    + "<br/><span style='display: none;' class='suppresionConfirmation'>Êtes-vous sur ?</span>"
                    + "</td>"
                    + "</tr>"
                    + "</form>";
        }
        html += "<form name='ajouter' method='POST' action='./gerer-type.html'>"
                + "<tr>"
                + "<input type='hidden' name='id' value =''/>"
                + "<td class='colonne_nom'><input type='text' name='nom' id='nom' required placeholder='Nouveau type' /></td>"
                + "<td class='colonne_description'><input type='text' name='des' id='description' required placeholder='Description du nouveau type'/></td>"
                + "<td class='colonne_action'>"
                + "<img src='img/valid.png' title='Ajouter' alt='Ajouter' onclick='Ajouter()' />"
                + "</td>"
                + "</tr>"
                + "</form>"
                + "</table>";
        return html;
   }
}
