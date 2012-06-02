/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
        
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());

        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        System.out.println(session.getAttribute("idDocument").toString());
        Document idDoc = dFacade.findRang(Integer.parseInt(session.getAttribute("idDocument").toString()));
        String com = request.getParameter("comm");
        System.out.println("Com = "+com);
        if (com != null && !com.isEmpty()){
            CommentaireFacade cFacade = ServicesLocator.getCommentaireFacade();
            Commentaire comm = new Commentaire();
            comm.setContenu(com);
            comm.setFKidetatcommentaire(new EtatCommentaire(2));
            comm.setFKiddocument(idDoc);
            comm.setFKidutilisateur((Utilisateur)session.getAttribute("user"));
            comm.setDate(new Date());
            cFacade.create(comm);
            cFacade.edit(comm);
            System.out.println("FICHIER => "+comm.getFKiddocument().getDescriptionDocument());
            request.setAttribute("contenu", comm.getContenu());
            request.setAttribute("auteur", comm.getFKidutilisateur().getNom()+" "+comm.getFKidutilisateur().getPrenom());
            
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(comm.getDate().getTime());
            int jour = cal.get(Calendar.DAY_OF_MONTH);
            int mois = cal.get(Calendar.MONTH) + 1;
            String moisDate = "";
            switch (mois) {
                case 1:
                    moisDate = "Janvier";
                    break;
                case 2:
                    moisDate = "Février";
                    break;
                case 3:
                    moisDate = "Mars";
                    break;
                case 4:
                    moisDate = "Avril";
                    break;
                case 5:
                    moisDate = "Mai";
                    break;
                case 6:
                    moisDate = "Juin";
                    break;
                case 7:
                    moisDate = "Juillet";
                    break;
                case 8:
                    moisDate = "Août";
                    break;
                case 9:
                    moisDate = "Septembre";
                    break;
                case 10:
                    moisDate = "Octobre";
                    break;
                case 11:
                    moisDate = "Novembre";
                    break;
                default:
                    moisDate = "Décembre";
                    break;
            }
            int annee = cal.get(Calendar.YEAR);
            int h = cal.get(Calendar.HOUR_OF_DAY);
            int m = cal.get(Calendar.MINUTE);
            String htmlDate = jour + " " + moisDate + " " + annee + " à " + h + "h" + m;
            request.setAttribute("date",htmlDate);
        }
        
        
        
        request.getRequestDispatcher("ajaxCommentaire.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
