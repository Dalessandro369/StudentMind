/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.DocumentFacade;
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
public class EcrireMessageServlet extends HttpServlet {
    
    String idUser;
    String idMes;
    Utilisateur userExp;
    HttpSession session = null;
    
    @Override
    public void init() throws ServletException{
    }

    public EcrireMessageServlet() {
        super();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        session = request.getSession(false);
        if ((session != null) && ((Utilisateur) session.getAttribute("user") != null)) {          
            userExp = (Utilisateur) session.getAttribute("user");
            session.setAttribute("servlet", getClass().getName());
            request.setAttribute("nbrDoc", afficherNbrDoc());
            request.setAttribute("nbrDocUser", afficherNombreDocUser());
            request.setAttribute("nbrMess",afficherMess());

            idUser = request.getParameter("u");
            idMes = request.getParameter("m");

            if (userExp != null){
                if (idUser == null) { //nouveau
                    request.setAttribute("message", afficherNouveau());
                }else { //repondre
                    request.setAttribute("message", afficherRepondre());
                }
                request.getRequestDispatcher("ecrireMessage.jsp").forward(request,response);
            }
        } else{
            request.getRequestDispatcher("inscription.jsp").forward(request,response);
        }
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        session = request.getSession(false);
        if (session  != null) {            
        userExp = (Utilisateur) session.getAttribute("user");
        session.setAttribute("servlet", getClass().getName());  
        
        String nomUtilisateur = request.getParameter("nom");
        String objet = request.getParameter("objet");
        String contenu = request.getParameter("contenu");
        String[] splitab = nomUtilisateur.split("-");
        splitab[0] = splitab[0].trim();
        
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        Utilisateur user = new Utilisateur(Integer.parseInt(splitab[0]));
        
        
        MessageFacade mFacade = ServicesLocator.getMessageFacade();
        Message mes = new Message();
        mes.setIdMessage(1);
        mes.setDateMessage(new Date());
        mes.setFKidetatmessage(new EtatMessage(2));
        mes.setObjetMessage(objet);
        mes.setContenuMessage(contenu);
        mes.setFKidutilisateurdes(user);
        mes.setFKidutilisateurexp(userExp);
        
        mFacade.create(mes);
        
        request.setAttribute("ListeMessageReception", afficherMessageRecu());
        request.getRequestDispatcher("inbox.jsp").forward(request, response);
        }
     
    }
    
    public String afficherNouveau(){
        
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        List<Utilisateur> liste = uFacade.findAll();
        String html = "<div id=\"content\">"
                + "<h2>Ecrire nouveau message</h2>"
                + "<div id=\"new_message\">"
                + "<img src=\"upload/avatars/"+ userExp.getFKidImage().getUrlImage()+"\"  height='73' width='73'  title=\"\" alt=\"\" />"               
                + "<script>"
                + "$(function() {"
                + "var availableTags = [";             

        if (liste.size() <= 1) {
            for (Utilisateur user : liste) {
                html += "\"" + user.getIdUtilisateur() + " - " + user.getNom() + " " + user.getPrenom() + "\"";
              
            }
        } else {
            for (Utilisateur user : liste) {
                html += "\"" + user.getIdUtilisateur() + " - " + user.getNom() + " " + user.getPrenom() + "\",";
               
            
            }
        }
        html +="];"
                + "$( \"#tags\" ).autocomplete({"
                + "source: availableTags"
                + "});"
                + "});"
                + "</script>"
                + "<ul>"
                + "<li>"
                + "<label><strong>Destinataire :</strong></label>"
                + "<form method=\"POST\" action=\"./ecrire-message.html\">"
                + "<input class=\"nom_utilisateur\" type=\"text\" name=\"nom\" id=\"tags\" placeholder=\"Nom d'utilisateur\" />"
                + "</li>"
                + "<li><label><strong>Objet :</strong></label>"
                + "<input type=\"text\" name=\"objet\" id=\"objet\" placeholder=\"Objet\" required /></li>"
                + "</ul>"
                + "<textarea name=\"contenu\" id=\"contenu\" placeholder=\"Votre nouveau message\" required></textarea>"
                + "<input type=\"submit\" value=\"Envoyer\" />"
                + "</form>"
                + "</div>"
                + "</div>";
        return html;
    }
    public String afficherMess(){
        String html = "";
        MessageFacade mFacade = ServicesLocator.getMessageFacade();
        int nbrMessage = mFacade.nbrMessNonLu(userExp.getIdUtilisateur());
        int nbrTotal = mFacade.nbrMessTotal(userExp.getIdUtilisateur());
        if (nbrMessage >= 1){
            return "("+nbrMessage+"/"+nbrTotal+")";
        }
            return "";
        
        
    }
    public String afficherRepondre(){
        
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        MessageFacade mFacade = ServicesLocator.getMessageFacade();
        Message mes = mFacade.findId(Integer.parseInt(idMes));
        Utilisateur user = uFacade.findId(Integer.parseInt(idUser));
        List<Utilisateur> liste = uFacade.findAll();
        String html = "<div id=\"content\">"
                + "<h2>Ecrire nouveau message</h2>"
                + "<div id=\"new_message\">"
                + "<img src=\"upload/avatars/"+userExp.getFKidImage().getUrlImage()+"' title=\"\"  height='73' width='73' alt=\"\" />"
                + "<script>"
                + "$(function() {"
                + "var availableTags = [";
         //    Utilisateur u = null;

        if (liste.size() <= 1) {
            for (Utilisateur user1 : liste) {
                html += "\"" + user1.getIdUtilisateur() + " - " + user1.getNom() + " " + user1.getPrenom() + "\"";
               // if (Integer.parseInt(idUser) == user.getIdUtilisateur()) {
              //      u = user;
              //  }
            }
        } else {
            for (Utilisateur user1 : liste) {
                html += "\"" + user1.getIdUtilisateur() + " - " + user1.getNom() + " " + user1.getPrenom() + "\",";
             //   if (Integer.parseInt(idUser) == user.getIdUtilisateur()) {
             //       u = user;
              //  }
            }
        }
        html +="];"
                + "$( \"#tags\" ).autocomplete({"
                + "source: availableTags"
                + "});"
                + "});"
                + "</script>"
                + "<ul>"
                + "<li>"
                + "<blockquote>"+ mes.getContenuMessage()+"</blockquote>"                
                + "<label><strong>Destinataire :</strong></label>"
                + "<form method=\"POST\" action=\"./ecrire-message.html\">"
                + "<input class=\"nom_utilisateur\" type=\"text\" name=\"nom\" id=\"tags\" value='"+user.getIdUtilisateur()+" - "+user.getNom()+" "+user.getPrenom()+"' />"
                + "</li>"
                + "<li><label><strong>Objet :</strong></label>"
                + "<input type=\"text\" name=\"objet\" id=\"objet\" placeholder=\"Objet\" required /></li>"
                + "</ul>"
                + "<textarea name=\"contenu\" id=\"contenu\" placeholder=\"Votre nouveau message\" required></textarea>"
                + "<input type=\"submit\" value=\"Envoyer\" />"
                + "</form>"
                + "</div>"
                + "</div>";
        return html;
    }
      public String afficherMessageRecu() {

        String html = "<table id=\"inbox\" class=\"sortable\">"
                + "<thead> "
                + "<tr>"
                + "<th class=\"nosort\"></th>"
                + "<th>Expéditeur</th>"
                + "<th>Objet</th>"
                + "<th>Date</th>"
                + "<th class=\"nosort\"></th>"
                + "</tr>"
                + "</thead> "
                + "<tbody>";
        MessageFacade mFacade = ServicesLocator.getMessageFacade();
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        Utilisateur id = (Utilisateur) session.getAttribute("user");
        Utilisateur user = uFacade.findId(id.getIdUtilisateur());
        List<Message> liste = mFacade.findMessRecu(user.getIdUtilisateur());
        for (Message mes : liste) {

            html += "<form name='ligne" + mes.getIdMessage() + "' method='POST' action='./inbox.html' >"
                    + "<tr>"
                    + "<td class=\"lu\"><input type=\"image\" src=\"img/trash.png\" title=\"\" alt=\"t\" onclick='SupprimerMes(" + mes.getIdMessage() + ")' /></td>"
                    + "<input type='hidden' value='"+mes.getIdMessage()+"' name='id'/>"
                    + "<td class=\"lu\"><a href=\"\">" + mes.getFKidutilisateurexp().getNom() + " " + mes.getFKidutilisateurexp().getPrenom() + "</a></td>"
                    + "<td class=\"lu\"><a href=\"lire-message.html?m="+mes.getIdMessage()+"\">" + mes.getObjetMessage() + "</a></td>";
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
            if (mes.getFKidetatmessage().getIdEtatMessage() == 1) {
                html += "<td class=\"lu\"><img src=\"img/read.png\" title=\"\" alt=\"t\" /></td>";
            } else {
                html += "<td class=\"lu\"><img src=\"img/unread.png\" title=\"\" alt=\"t\" /></td>";
            }
            html += "</tr>"
                    + "</form>";
        }

        html += "</tbody>"
                + "</table>";
        return html;
    }
      
    public String afficherNbrDoc() {

        String html = "";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        html = "" + dFacade.nbrDoc();
        return html;

    }
    
    public String afficherNombreDocUser() {
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        int i = 0;
        if (user != null) {
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
            i = dFacade.nbrDocUser(user.getIdUtilisateur());
        }
        if (i >= 1) {
            return "(" + i + ")";
        } else {
            return "";
        }
    }
}
