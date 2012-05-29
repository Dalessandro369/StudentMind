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
import studentmind.facade.*;
import studentmind.model.Commentaire;
import studentmind.model.Document;
import studentmind.model.EtatCommentaire;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class AjouterCommentaireServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public AjouterCommentaireServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        
        Document idDoc = (Document) session.getAttribute("idDocument");
        String com = request.getParameter("comm");
        
        if (com != null && !com.isEmpty()){
            CommentaireFacade cFacade = ServicesLocator.getCommentaireFacade();
            Commentaire comm = new Commentaire();
            comm.setContenu(com);
            comm.setFKidetatcommentaire(new EtatCommentaire(2));
            comm.setFKiddocument(idDoc);
            comm.setFKidutilisateur((Utilisateur)session.getAttribute("user"));
            cFacade.create(comm);
        }

    }
}
