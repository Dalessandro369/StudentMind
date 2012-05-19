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
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
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
        
       HttpSession session = request.getSession(false);
    
        if (session != null) {
            session.invalidate();
        }
        if ((email==null || email.isEmpty()) && (mdp==null) || mdp.isEmpty()){
            request.setAttribute("test", "remplir formu correct" );
        }else 
            { 
               UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
               Utilisateur user = null;
               user = uFacade.findEmail(email);
               if (user != null) {
                   if (user.getFKidetatutlisateur().getIdEtatUtilisateur() == 2){
                   if (user.getPassword().equals(mdp)) {
                       request.setAttribute("test", "ok" + user.getFKidetatutlisateur() + user.getEmail());
                       session = request.getSession(true);
                       session.setAttribute("user", user);
                       session.setAttribute("rang", user.getFKidrang());
                   }else request.setAttribute("test", "mdp errone donc remettre l'email dans le truc" + user.getFKidetatutlisateur() + user.getEmail() );   
               }else request.setAttribute("test", "Veuillez activer votre compte " + user.getFKidetatutlisateur() + user.getEmail()); 
               }else
                   request.setAttribute("test", "Rien de bon");
               
            }
        request.getRequestDispatcher("index.jsp").forward(request, response);
     }
}
