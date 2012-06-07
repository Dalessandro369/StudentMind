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
import studentmind.facade.DocumentFacade;
import studentmind.facade.MessageFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.Document;
import studentmind.model.EtatDocument;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class GererFileAttenteDocumentServlet extends HttpServlet {
    
    HttpSession session;
    Utilisateur userExp;
    Utilisateur user;    
    
    @Override
    public void init() throws ServletException{
    }

    public GererFileAttenteDocumentServlet() {
        super();
    }    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        session = request.getSession(false);
        if ((session != null) && ((Utilisateur) session.getAttribute("user") != null)) {
            session.setAttribute("servlet", getClass().getName());
            user = (Utilisateur) session.getAttribute("user");
            request.setAttribute("nbrMess", afficherMess());
            request.setAttribute("nbrDocUser", afficherNombreDocUser());
            request.setAttribute("ListeFileAttente", afficheFileAttenteDocument());
            request.getRequestDispatcher("gererFileAttenteDocument.jsp").forward(request,response);
        } else{
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        user = (Utilisateur) session.getAttribute("user");
        
        String id = request.getParameter("id");
        String type = request.getParameter("type");
        if (id!=null && !id.isEmpty()){ 
            DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
            Document doc = dFacade.findRang(Integer.parseInt(id));
            if (type.equals("Valider")){               
                UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
                Utilisateur user = uFacade.findId(doc.getFKidutilisateur().getIdUtilisateur());
                user.setPoints(user.getPoints()+50);
                uFacade.edit(user);
                doc.setFKidetatdocument(new EtatDocument(2));                
            }
            if (type.equals("Supprimer")){      
                doc.setFKidetatdocument(new EtatDocument(3));                               
            }
             System.out.println(doc.getFKidetatdocument()+" "+doc.getDescriptionDocument()+" "+type);
            dFacade.edit(doc);
        }
        request.setAttribute("ListeFileAttente", afficheFileAttenteDocument());
        request.getRequestDispatcher("gererFileAttenteDocument.jsp").forward(request,response);
       
    }
    
    public String afficheFileAttenteDocument() {
        
        String html = "";
        
        html += "<table>"
                +"<tr>"
                +"<th class=\"colonne_nom\">Nom du document</th>"
                + "<th>Description</th>"
                + "<th>Auteur document</th>"               
                + "<th>Catégorie</th>"
                + "<th>Type</th>"
                + "<th>Extension</th>"
                + "<th>Action</th>"
                + "</tr>";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        List<Document> liste = dFacade.listDocAttente();
        for (Document doc : liste){
               html += "<tr>"                    
                    + "<td class=\"colonne_nom\"><a href=\"upload/documents/"+doc.getNomFichier()+"\">"+doc.getTitreDocument()+"</a></td>"               
                    + "<td>"+doc.getDescriptionDocument()+"</td>"
                    + "<td class=\"colonne_description\">"+doc.getFKidutilisateur().getNom()+" "+doc.getFKidutilisateur().getPrenom()+"</td>"
                    + "<td>"+doc.getFKidcategorie().getNomCategorie()+"</td>"
                    + "<td>"+doc.getFKidtype().getNomType()+"</td>"
                    + "<td>"+doc.getFKidextension().getNomExtension()+" - "+doc.getFKidextension().getFKidfamille().getNomFamille()+"</td>"                      
                    + "<td class=\"colonne_action\">" 
                    + "<form name='ligne" + doc.getIdDocument() + "' method='POST' action='./gerer-file-attente-document.html'>"
                    + "<input type='image' src=\"img/accepter.png\" title=\"Valider\" alt=\"Valider\" onclick='ValiderDoc(" + doc.getIdDocument() + ");'/>"
                    + "<input type='image'src=\"img/delete.png\" title=\"Supprimer\" alt=\"Supprimer\" onclick='RetirerDoc(" + doc.getIdDocument() + ");'/>"
                    + "<input type='hidden' value='"+doc.getIdDocument()+"' name='id'/>"            
                    + "<input type='hidden' name='type' id='type"+doc.getIdDocument()+"'/>"
                    + "</form>"
                    + "</td>"
                    + "</tr>";            
        }
        html+="</table>";
        return html;
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
}
