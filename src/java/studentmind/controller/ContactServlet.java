/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.utilities.EmailSender;
import studentmind.utilities.HashMD5;

/**
 *
 * @author ProjetJava
 */
public class ContactServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException{
    }

    public ContactServlet() {
        super();
    }    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());        
        
        request.getRequestDispatcher("contact.jsp").forward(request,response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        boolean champOk = true;
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String objet = request.getParameter("objet");
        String message = request.getParameter("message");
        String mesNom = "";
        String mesEmail = "";
        String mesObjet = "";
        String mesMessage = "";
        
        // Contrôle de validité
        if (nom == null || nom.isEmpty()) {
            mesNom = "Veuillez à remplir correctement le nom";
            request.setAttribute("ErreurNom", mesNom);
            champOk = false;
        }
        if (email == null || email.isEmpty()) {
            mesEmail = "Veuillez à remplir l'adresse email";
            request.setAttribute("ErreurEmail", mesEmail);
            champOk = false;
        }
        if (objet == null || objet.isEmpty()) {
            mesObjet = "Veuillez à remplir correctement l'objet";
            request.setAttribute("ErreurObjet", mesObjet);
            champOk = false;
        }
        if (message == null || message.isEmpty()) {
            mesEmail = "Veuillez à remplir correctement le message";
            request.setAttribute("ErreurObjet", mesEmail);
            champOk = false;            
        }
        
        EmailSender es = new EmailSender(
                // ici c contact.g
          "alessandrodaviera111@hotmail.com",  email, "[Quelqu'un veut vous parler] " + objet, "Un message vous a été envoyé depuis le formulaire de contact de StudentMind !"
                + "Il vous a été envoyé par " + nom + "("+email+")\n\n"
                + message);
        
    }
}
