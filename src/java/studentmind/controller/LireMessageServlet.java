/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.MessageFacade;
import studentmind.facade.ServicesLocator;
import studentmind.model.EtatMessage;
import studentmind.model.Message;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class LireMessageServlet extends HttpServlet {
    HttpSession session = null;
    @Override
    public void init() throws ServletException{
    }

    public LireMessageServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  
        session = request.getSession(false);
        String html = "";
        if ((session  != null) && ((Utilisateur) session.getAttribute("user") != null)) {
        session.setAttribute("servlet", getClass().getName());
        
        String idMes = request.getParameter("m");
        MessageFacade mFacade = ServicesLocator.getMessageFacade();
        Message mes = mFacade.findId(Integer.parseInt(idMes));
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        //changer l'avatar ici
        html = "<img src=\"../img/avatar2.jpg\" title=\"\" alt=\"\" />"
                + "<ul>"
                + "<li><label><strong>Expéditeur :</strong></label>"+mes.getFKidutilisateurexp().getNom() +" "+mes.getFKidutilisateurexp().getPrenom()+" "+"</li>"
                + "<li><label><strong>Objet :</strong></label>"+mes.getObjetMessage()+"</li>";
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(mes.getDateMessage().getTime());
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

            html += "<li><label><strong>Date :</strong></label> " + jour + " " + moisDate + " " + annee + " " + h + ":" + m + "</li>"
                    + "<li><a href='ecrire-message.html?u="+mes.getFKidutilisateurexp().getIdUtilisateur()+"?m="+mes.getIdMessage()+"'>Répondre</a></li>"
                    + "</ul>"
                    + "<blockquote>"+ mes.getContenuMessage()+ "</blockquote>"
                    + "<form method=\"POST\" action=\"./ecrire-message.html\">"
                    + "<input type='hidden' name='nom'value='"+mes.getFKidutilisateurexp().getIdUtilisateur()+" - "+mes.getFKidutilisateurexp().getNom() +" "+mes.getFKidutilisateurexp().getPrenom()+"'/>"
                    + "<input type='hidden' name='objet'value='"+mes.getObjetMessage()+" - Reponse'/>"
                    + "<textarea name='contenu' placeholder=\"Cliquez ici pour répondre\"></textarea></br>"
                    + "<input type=\"submit\" value=\"Répondre\" />"                   
                    + "</form>";    
              
            mes.setFKidetatmessage(new EtatMessage(1));
            mFacade.edit(mes);
            
        
        request.setAttribute("Message", html);
        request.getRequestDispatcher("lireMessage.jsp").forward(request,response); 
        } //else rediriger index
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     
    }
}
