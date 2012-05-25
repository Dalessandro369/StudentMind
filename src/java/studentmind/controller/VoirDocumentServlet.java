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
import studentmind.facade.DocumentFacade;
import studentmind.facade.ServicesLocator;
import studentmind.model.Document;
/**
 *
 * @author ProjetJava
 */
public class VoirDocumentServlet extends HttpServlet {
    @Override
    public void init() throws ServletException{
    }

    public VoirDocumentServlet() {
        super();
    }    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        HttpSession session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());  
        
        String idDoc = request.getParameter("id");
        
        String html = "<ul>";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        Document doc = dFacade.findRang(Integer.parseInt(idDoc));
        html += " <li><strong>Type : </strong>"+doc.getFKidtype().getNomType()+"</li>"
                + "<li><strong>Matière : </strong>"+ doc.getFKidcategorie().getNomCategorie()+"</li>"
                + "<li><strong>Taille du fichier : </strong>"+doc.getTaille()+" Mo</li>"
                + "<li><strong>Type de fichier : </strong>"+doc.getFKidextension().getNomExtension()+ "( " + doc.getFKidextension().getFKidfamille().getNomFamille()+" )</li>"
                + "<li><strong>Téléchargé : </strong> 217 fois</li>"
                + "<li>"
                + "<strong>Moyenne : </strong>"
                + "<script type=\"text/javascript\">"
                + "$(document).ready(function(){"
                + " $(\".avg_note\").jRating({"
                + "type:'small', // type of the rate.. can be set to 'small' or 'big'"
                + "length : 5, // nb of stars"
                + "decimalLength : 1, // number of decimal in the rate"
                + "isDisabled : true"
                + " });"
                + "});"
                + "</script>"
                + "<div class=\"avg_note\" data=\"2\"></div>"
                + "</li>"
                + "</ul>";
        request.setAttribute("infoDoc", html);
        html = "<div class=\"article_header\"><header><h3>"+doc.getTitreDocument()+"</h3></header></div>"
                + " <div class=\"article_content\">"
                + "<p>"+doc.getDescriptionDocument()+ "</p>"
                + "</div>"
                + "<div class=\"article_footer\"><footer><strong>Catégorie : </strong>"+ doc.getFKidcategorie().getNomCategorie()+"</footer></div>";
        request.setAttribute("informationDoc", html);
               
        
        request.getRequestDispatcher("voirDocument.jsp").forward(request,response); 
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
