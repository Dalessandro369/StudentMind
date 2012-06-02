/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import studentmind.facade.ImageFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.Image;
import studentmind.model.Utilisateur;

/**
 *
 * @author ProjetJava
 */
public class ModifierProfilServlet extends HttpServlet {

    HttpSession session = null;

    @Override
    public void init() throws ServletException {
    }

    public ModifierProfilServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
      
        
        request.setAttribute("information", afficherInformation());
        request.getRequestDispatcher("profil.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());  
        
        //String image = request.getParameter("image");
        String ecole = request.getParameter("ecole");
        String site = request.getParameter("site");
        String ville = request.getParameter("ville");
        
        
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        Utilisateur u = (Utilisateur) session.getAttribute("user");
        
        String extensionFichier = "";
        // on lit le nom du fichier
        extensionFichier = request.getPart("image").toString();
        int p = extensionFichier.indexOf(",");
        if(p != 10)
        {
            extensionFichier = extensionFichier.substring(10, p);

            int pos = extensionFichier.lastIndexOf("."); // on récupère la position du . en partant de la fin
            if(pos != -1) // si on trouve
            {
                extensionFichier = extensionFichier.substring(pos).toLowerCase();
                if(".jpg".equals(extensionFichier) || ".jpeg".equals(extensionFichier) || ".png".equals(extensionFichier))
                {                                     
                    ImageFacade iFacade = ServicesLocator.getImageFacade();
                    try 
                    {
                        // on accède au fichier uploadé par le client
                        Part p1 = request.getPart("image");
                        InputStream is = p1.getInputStream();

                        // on récupère le timestamp
                        long fn = System.currentTimeMillis();
                        String filename = Long.toString(fn);

                        // on récupère le nom à utiliser sur le serveur
                        //String outputfile = "../../../web/upload/avatars/" + filename + extensionFichier;
                        String outputfile = this.getServletContext().getRealPath("/upload/avatars/" + filename + extensionFichier);
                        // <img src="./img/accepter.png" alt="test" title="test" />
                        // String outputfile = "upload\\avatars\\"+filename+extensionFichier;
                        //System.out.println("" + filename + extensionFichier);
                        FileOutputStream os = new FileOutputStream (outputfile);

                        // on écrit les bytes du fichier de la source vers la destination
                        int ch = is.read();
                        while (ch != -1) {
                            os.write(ch);
                            ch = is.read();
                        }
                        os.close();  
                        Image img = new Image(1, filename + extensionFichier);
                        iFacade.create(img);
                        u.setFKidImage(img);
                    }
                    catch(Exception ex) {
                    //out.println("Exception -->" + ex.getMessage());
                    }
                }
            }
        }      
        
        
        u.setEcole(ecole);
        u.setSiteWeb(site);
        u.setVille(ville);
        
        uFacade.edit(u);
        request.setAttribute("information", afficherInformation());
        request.getRequestDispatcher("profil.jsp").forward(request, response);
        
    }

    public String afficherInformation() {

        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        String html = "";
        html += "<form method=\"POST\" action=\"modifier-profil.html\" enctype=\"multipart/form-data\">"
                + " <fieldset>"
                + "<legend>Informations générales</legend>"
                + "<label for=\"nom\">Nom :</label><span class=\"profilNom\">" + user.getNom() + "</span><br/>"
                + "<label for=\"prenom\">Prénom :</label><span class=\"profilPrenom\">" + user.getPrenom() + "</span><br/>"
                + "<label for=\"dateNaissance\">Date de naissance :</label><span class=\"profilDOB\">" + user.getDateNaissance() + "</span><br/>"
                + "<label for=\"sexe\">Sexe :</label><span class=\"profilSexe\">" + user.getSexe() + "</span><br/>"
                + "<label for=\"pays\">Pays :</label><span class=\"profilPays\">" + user.getFKidpays().getNomPays() + "</span>"
                + " </fieldset>";

        html += "<fieldset>"
                + "<legend>Profil étudiant</legend>"
                + "<label for=\"image\">Avatar :</label> <input type=\"file\" name=\"image\" id=\"image\"/><br/>"
                + "<label for=\"ecole\">Ecole / Université :</label> <input type=\"text\" name=\"ecole\" value='"+user.getEcole()+"' id=\"ecole\"  /><br/>"
                + "<label for=\"site\">Site Web :</label> <input type=\"text\" name=\"site\" value='"+user.getSiteWeb()+"' id=\"site\" /><br/>"
                + "<label for=\"ville\">Ville :</label> <input type=\"text\" name=\"ville\" value='"+user.getVille()+"' id=\"ville\" /><br/>";

        
        html += "</fieldset>"
                + "<input type=\"submit\" id=\"submit\" value=\"Je modifie!\" />"
                + " </form>";
        return html;
    }
}
