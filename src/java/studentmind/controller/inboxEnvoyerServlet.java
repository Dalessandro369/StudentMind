/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.MessageFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.EtatMessage;
import studentmind.model.Message;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class inboxEnvoyerServlet extends HttpServlet {

    HttpSession session = null;
    
    @Override
    public void init() throws ServletException {
    }

    public inboxEnvoyerServlet() {
        super();
    }
    

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        session = request.getSession(false);
        if ((session  != null) && ((Utilisateur) session.getAttribute("user") != null)) {
        session.setAttribute("servlet", getClass().getName());

        request.setAttribute("ListeMessageEnvoyer", afficherMessageEnvoyer());
        request.getRequestDispatcher("inboxEnvoyer.jsp").forward(request, response);
        }
       // 
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String id = request.getParameter("id");
        MessageFacade mFacade = ServicesLocator.getMessageFacade();
        Message mes = mFacade.findId(Integer.parseInt(id));
        if (mes.getFKidetatmessage().getIdEtatMessage() == 3){
            mes.setFKidetatmessage(new EtatMessage(4));
        }else{
            mes.setFKidetatmessage(new EtatMessage(3));
        }
        mFacade.edit(mes);
        
        request.setAttribute("ListeMessageEnvoyer", afficherMessageEnvoyer());
        request.getRequestDispatcher("inboxEnvoyer.jsp").forward(request, response);
       
    }
     public String afficherMessageEnvoyer() {

        String html = "<table id=\"inbox\" class=\"sortable\">"
                + "<thead> "
                + "<tr>"
                + "<th class=\"nosort\"></th>"
                + "<th>Expéditeur</th>"
                + "<th>Objet</th>"
                + "<th>Date</th>"
                + "</tr>"
                + "</thead> "
                + "<tbody>";
        MessageFacade mFacade = ServicesLocator.getMessageFacade();
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        Utilisateur id = (Utilisateur) session.getAttribute("user");
        Utilisateur user = uFacade.findId(id.getIdUtilisateur());
        List<Message> liste = mFacade.findMessEnvoyer(user.getIdUtilisateur());
        
        for (Message mes : liste) {

            html += "<tr>"                  
                    + "<td class=\"lu\">"
                    + "<form name='ligne" + mes.getIdMessage() + "' method='POST' action='./inbox-envoyer.html'>"
                    + "<input type=\"image\" src=\"img/trash.png\" title=\"\" alt=\"t\" onclick='SupprimerMes(" + mes.getIdMessage() + ")' />"
                    + "<input type='hidden' value='"+mes.getIdMessage()+"' name='id'/>"
                    + "</form>"
                    +"</td>"
                    + "<td class=\"lu\"><a href=\"\">" + mes.getFKidutilisateurexp().getNom() + " " + mes.getFKidutilisateurexp().getPrenom() + "</a></td>"
                    + "<td class=\"lu\">"+mes.getObjetMessage() + "</td>";
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

            html += "<td class=\"lu\"> " + jour + " " + moisDate + " " + annee + " " + h + ":" + m + "</td>";
            html += "</tr>";
        }

        html += "</tbody>"
                + "</table>";
        return html;
    }

  
}
