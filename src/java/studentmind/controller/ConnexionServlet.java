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
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.Pays;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class ConnexionServlet extends HttpServlet {
    @Override
    public void init() throws ServletException{
    }

    public ConnexionServlet() {
        super();
    }    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        //Recuperation du formulaire
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        
        if ((email==null || email.isEmpty()) && (mdp==null) || mdp.isEmpty()){
            request.setAttribute("test", "remplir formu correct" );
        }else 
            { 
               UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
               Utilisateur user = null;
               user = uFacade.findEmail(email);
               if (user != null) {
                   if (user.getPassword().equals(mdp)) {
                       request.setAttribute("test", "ok" );
                   }else request.setAttribute("test", "mdp errone donc remettre l'email dans le truc" );   
               }else request.setAttribute("test", "rien de bon" ); 
            }
        request.getRequestDispatcher("index.jsp").forward(request, response);
     }
}
