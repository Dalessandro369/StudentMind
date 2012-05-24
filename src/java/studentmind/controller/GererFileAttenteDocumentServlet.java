/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
public class GererFileAttenteDocumentServlet extends HttpServlet {
    @Override
    public void init() throws ServletException{
    }

    public GererFileAttenteDocumentServlet() {
        super();
    }    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());    
        request.setAttribute("ListeFileAttente", afficheFileAttenteDocument());
        request.getRequestDispatcher("gererFileAttenteDocument.jsp").forward(request,response);
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
                    tFacade.remove(type);
                }
            }
            request.setAttribute("ListeType", afficheFileAttenteDocument());
            request.getRequestDispatcher("gererType.jsp").forward(request, response);
        }
    }
    
    public String afficheFileAttenteDocument() {
        return "";
    }
}
