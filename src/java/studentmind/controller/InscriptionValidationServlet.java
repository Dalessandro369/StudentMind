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
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.EtatUtilisateur;
import studentmind.model.Utilisateur;
import studentmind.utilities.HashMD5;

/**
 *
 * @author ProjetJava
 */
public class InscriptionValidationServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public InscriptionValidationServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());        

        /*
         * Récupération du hash MD5 (représentant l'adresse email)
         */
        String md5email = request.getParameter("e");

        if (md5email == null || md5email.isEmpty()) {
            request.setAttribute("test", "il n'y a aucun paramètre passé dans l'URL");
        } else {
            UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
            Utilisateur user = null;
            user = uFacade.findEmail(md5email);
            if (user != null) {
                if(user.getFKidetatutlisateur().getIdEtatUtilisateur() == 2)    request.setAttribute("messageInscription", "Votre compte a déjà été activé !");
                else{
                    request.setAttribute("messageInscription", "Félicitations ! Votre compte est activé ! Vous pouvez désormais vous connecter à l'aide de vos identifiants de connexion. Bons partages !");
                    user.setFKidetatutlisateur(new EtatUtilisateur(2)); // Activation du compte
                    uFacade.edit(user);
                }
            }

        }


        request.getRequestDispatcher("inscriptionValidation.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
