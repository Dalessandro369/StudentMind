/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import studentmind.facade.*;
import studentmind.model.Commentaire;
import studentmind.model.EtatCommentaire;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class GererAbusServlet extends HttpServlet {
    
    HttpSession session;
    Utilisateur userExp;
    Utilisateur user;
    
    @Override
    public void init() throws ServletException{
    }

    public GererAbusServlet() {
        super();
    }    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        user = (Utilisateur) session.getAttribute("user");
        request.setAttribute("ListerAbus", afficherAbus());
        request.setAttribute("nbrDocUser", afficherNombreDocUser());
        request.setAttribute("nbrMess", afficherMess());
        request.setAttribute("nbrCom", afficherNbrComSignaler()); 
        request.setAttribute("nbrDocFile", afficherNbrDocAttente()); 
        request.getRequestDispatcher("gererAbus.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        user = (Utilisateur) session.getAttribute("user");
        if (user != null){
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        String signaleur = request.getParameter("idSignaleur");
        String auteur = request.getParameter("idAuteur");
        
        CommentaireFacade cFacade = ServicesLocator.getCommentaireFacade();
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        Utilisateur userSignaleur = uFacade.findId(Integer.parseInt(signaleur));
        Utilisateur userAuteur = uFacade.findId(Integer.parseInt(auteur));
        Commentaire com = cFacade.find(Integer.parseInt(id));
        
        if (type.equals("Valider")){
            //Commentaire supprime et auteur perd de point et signaleur gagne des point
            userSignaleur.setPoints(userSignaleur.getPoints()+10);
            if (userAuteur.getPoints() >= 10) userAuteur.setPoints(userAuteur.getPoints()-10);
            else userAuteur.setPoints(0);
            userAuteur.setNbrSignal(userAuteur.getNbrSignal()+1);
            uFacade.edit(userAuteur);
            uFacade.edit(userSignaleur);
            com.setFKidetatcommentaire(new EtatCommentaire(1));
            cFacade.edit(com);
        }else {
            com.setFKidetatcommentaire(new EtatCommentaire(4));
            cFacade.edit(com);            
        }            
                
                
                
        request.setAttribute("ListerAbus", afficherAbus());
        request.setAttribute("nbrMess", afficherMess());
        request.setAttribute("nbrCom", afficherNbrComSignaler()); 
        request.setAttribute("nbrDocFile", afficherNbrDocAttente()); 
        request.getRequestDispatcher("gererAbus.jsp").forward(request,response);
        }else{
            
            request.getRequestDispatcher("index.jsp").forward(request,response);
        }
    }
    
    public String afficherAbus(){
        String html = "";
        html += "<table>"
                +"<tr>"
                +"<th class=\"colonne_nom\">Auteur du commentaire</th>"
                + "<th>Personne ayant signalé</th>"
                + "<th>Commentaire</th>"               
                + "<th>Action</th>"
                + "</tr>";
        CommentaireFacade cFacade = ServicesLocator.getCommentaireFacade();
        List<Commentaire> liste = cFacade.findComSignaler();
        for (Commentaire com : liste){
            html +=  "<tr>"                    
                    + "<td class=\"colonne_nom\">"+com.getFKidutilisateur().getNom()+" "+com.getFKidutilisateur().getPrenom()+"</td>"               
                    + "<td>"+com.getFKidutilisateursignaleur().getNom()+" "+com.getFKidutilisateursignaleur().getPrenom()+"</td>"
                    + "<td class=\"colonne_description\">"+com.getContenu()+"</td>"
                    + "<td class=\"colonne_action\">"
                    + "<form name='ligne" + com.getIdCommentaire() + "' method='POST' action='./gerer-abus.html' >"
                    + "<input type='image' src=\"img/accepter.png\" title=\"Valider\" alt=\"Valider\" onclick='ValiderAbus(" + com.getIdCommentaire() + ");'/>"
                    + "<input type='image'src=\"img/delete.png\" title=\"Supprimer\" alt=\"Supprimer\" onclick='RetirerAbus(" + com.getIdCommentaire() + ");'/>"
                    + "<input type='hidden' value='"+com.getIdCommentaire()+"' name='id'/>"
                    + "<input type='hidden' value='"+com.getFKidutilisateursignaleur().getIdUtilisateur()+"' name='idSignaleur'/>"
                    + "<input type='hidden' value='"+com.getFKidutilisateur().getIdUtilisateur()+"' name='idAuteur'/>"
                    + "<input type='hidden' name='type' id='type"+com.getIdCommentaire()+"'/>"
                    + "</td>"
                    + "</tr>"
                    + "</form>";
        }
             html+="</table>";
        return html;
    }
    
    public String afficherNombreDocUser() {
      
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
    
    public String afficherMess() {
        if (user != null) {
            MessageFacade mFacade = ServicesLocator.getMessageFacade();
            int nbrMessage = mFacade.nbrMessNonLu(user.getIdUtilisateur());
            int nbrTotal = mFacade.nbrMessTotal(user.getIdUtilisateur());

            if (nbrMessage == 0) {
                return "";
            } else {
                return "(" + nbrMessage + "/" + nbrTotal + ")";
            }
        } else {
            return "";
        }
    }
    
    public String afficherNbrDocAttente(){
        
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
