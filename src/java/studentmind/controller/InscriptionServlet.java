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
import studentmind.facade.EtatUtilisateurFacade;
import studentmind.facade.ImageFacade;
import studentmind.facade.PaysFacade;
import studentmind.facade.RangFacade;
import studentmind.facade.ServicesLocator;
import studentmind.facade.UtilisateurFacade;
import studentmind.model.*;

/**
 *
 * @author ProjetJava
 */
public class InscriptionServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
    }

    public InscriptionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Affichage des pays dans la liste déroulante

        PaysFacade paysFacade = ServicesLocator.getPaysFacade();
        List<Pays> listPays = paysFacade.findAllAlpha();
        String listeHtml = "";
        for (Pays pays : listPays) {
            listeHtml += "<option value=\"" + pays.getIdPays() + "\">" + pays.getNomPays() + "</option>";
        }
        request.setAttribute("listePays", listeHtml);


        request.getRequestDispatcher("inscription.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean champOk = true;
        String mesNom = "";
        String mesPrenom = "";
        String mesDn = "";
        String mesSexe = "";
        String mesEmail = "";
        String mesMdp = "";
        String mesVille = "";
        String mesPays = "";
        String mesImage = "";

        //Recuper le formulaire
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String dateNaissance = request.getParameter("dateNaissance");
        String sexe = request.getParameter("sexe");
        String email = request.getParameter("email");
        String mdp = request.getParameter("mdp");
        String mdp2 = request.getParameter("mdp2");
        String ecole = request.getParameter("ecole");
        String site = request.getParameter("site");
        String ville = request.getParameter("ville");
        String pays = request.getParameter("pays");
        String urlimage = request.getParameter("image");

        //Teste des champs
        if (nom == null || nom.isEmpty()) {
            mesNom = "Veuillez à remplir correctement le nom";
            request.setAttribute("ErreurNom", mesNom);
            champOk = false;
        }
        if (prenom == null || prenom.isEmpty()) {
            mesPrenom = "Veuillez à remplir correctement le prénom";
            request.setAttribute("ErreurPrenom", mesPrenom);
            champOk = false;
        }
        if (dateNaissance == null || dateNaissance.isEmpty()) {
            mesDn = "Veuillez à remplir la date de naissance";
            request.setAttribute("ErreurDn", mesDn);
            champOk = false;
        }
        if (sexe == null || sexe.isEmpty()) {
            mesSexe = "Veuillez à remplir correctement le sexe";
            request.setAttribute("ErreurSexe", mesSexe);
            champOk = false;
        }
        if (email == null || email.isEmpty()) {
            mesEmail = "Veuillez à remplir correctement l'email";
            request.setAttribute("ErreurEmail", mesEmail);
            champOk = false;
        }
        if (((mdp == null || mdp.isEmpty()) || (mdp2 == null || mdp2.isEmpty())) || !(mdp2.equals(mdp))) {
            mesMdp = "Veuillez à remplir correctement les mots de passe";
            request.setAttribute("ErreurMdp", mesMdp);
            champOk = false;
        }
        if (ville == null || ville.isEmpty()) {
            mesVille = "Veuillez à remplir correctement la ville";
            request.setAttribute("ErreurVille", mesVille);
            champOk = false;
        }
        if (pays == null || pays.isEmpty()) {
            mesPays = "Veuillez sélectionner un pays";
            request.setAttribute("ErreurPays", mesPays);
            champOk = false;
        }
        if (urlimage == null || urlimage.isEmpty()) {
            mesImage = "Veuillez sélectionner une image";
            request.setAttribute("ErreurImage", mesImage);
            champOk = false;
        }
        UtilisateurFacade uFacade1 = ServicesLocator.getUtilisateurFacade();
        Utilisateur userEmail = uFacade1.findEmail(email);
            
        if (champOk && userEmail == null) {
            
            UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
            ImageFacade iFacade = ServicesLocator.getImageFacade();

            Utilisateur user = new Utilisateur();
            Image img = null;
            //changer l'url de l'image pour le faire unique sinon sa ira pas pour le ftp

            img = new Image(1, urlimage);
            iFacade.create(img);
            //affiche id image
            user.setFKidImage(img);

            user.setIdUtilisateur(1);
            user.setFKidpays(new Pays(Integer.parseInt(pays))); // ok
            user.setNom(nom); // ok
            user.setPrenom(prenom); //ok
            user.setDateNaissance(dateNaissance); //ok
            user.setSexe(sexe); // ok
            user.setEmail(email); // ok
            user.setPassword(mdp); //ok
            user.setEcole(ecole);// ok
            user.setVille(ville); //ok    
            user.setSiteWeb(site); //ok
            user.setFKidrang(new Rang(1)); // ok 
            user.setFKidetatutlisateur(new EtatUtilisateur(1));  // ok
            user.setPoints(50); //ok 
            user.setNbrSignal(0); // ok       


            uFacade.create(user);
                  
          //  HttpSession session = request.getSession(true);
            //session.setAttribute("Utilisateur", user);
            
            request.setAttribute("test", "inscription ok"+mesMdp);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("test", "erreur");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }

    }
}
