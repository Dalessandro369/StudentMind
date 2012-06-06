/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.ServicesLocator;
import studentmind.facade.TelechargementFacade;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.Document;
import studentmind.model.Telechargement;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class TelechargerDocumentServlet extends HttpServlet {
    
    Utilisateur user2;
    HttpSession session;
    
    
    @Override
    public void init() throws ServletException {
    }

    public TelechargerDocumentServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        session = request.getSession(false);
        
        String doc = request.getParameter("doc");
        String urlDoc = request.getParameter("urlDoc");
        System.out.println("test");
        user2 = (Utilisateur) session.getAttribute("user");
        TelechargementFacade tfacade = ServicesLocator.getTelechargementFacade();
        UtilisateurFacade ufacade = ServicesLocator.getUtilisateurFacade();
        if(user2.getPoints() >= 10) {
            Telechargement tel = new Telechargement();     
            tel.setDateTelechargement(new Date());
            tel.setDocumentIdDocument(new Document(Integer.parseInt(doc)));
            tel.setUtlisateurIdUtilisateur(user2);
            tel.setIdTelechargement(1);
            tfacade.create(tel);                  
            user2.setPoints(user2.getPoints()-10);
            ufacade.edit(user2);  
        } 
              
        request.getRequestDispatcher("voirDocument.jsp").forward(request, response);
    }
}