/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.CommentaireFacade;
import studentmind.facade.DocumentFacade;
import studentmind.facade.ServicesLocator;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class AdministrationServlet extends HttpServlet {
    HttpSession session;
    @Override
    public void init() throws ServletException{
    }

    public AdministrationServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        
        /* faire une vérification qui dit que si le rang est différent de celui d'administrateur, alors on affiche pas la page ! */
        
        request.setAttribute("nbrCom", afficherNbrComSignaler()); 
        request.setAttribute("nbrDocFile", afficherNbrDocAttente()); 
        
        request.getRequestDispatcher("/admin.jsp").forward(request,response); 
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        
        /* faire une vérification qui dit que si le rang est différent de celui d'administrateur, alors on affiche pas la page ! */
        
        request.setAttribute("nbrCom", afficherNbrComSignaler()); 
        request.setAttribute("nbrDocFile", afficherNbrDocAttente()); 
        
        request.getRequestDispatcher("/admin.jsp").forward(request,response); 
     
    }
    
    public String afficherNbrDocAttente(){
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        int i = dFacade.nbrDocAtten();
        if (i >= 1) return "("+i+")";               
        else return "";
    }
    public String afficherNbrComSignaler(){
        CommentaireFacade cFacade = ServicesLocator.getCommentaireFacade();
        int i = cFacade.nbrComSignaler();
        if (i >= 1) return "("+i+")";               
        else return "";
    }
}
