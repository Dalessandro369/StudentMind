/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package studentmind.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import studentmind.facade.*;
import studentmind.model.*;
import studentmind.utilities.EmailSender;
import studentmind.utilities.HashMD5;

/**
 *
 * @author ProjetJava
 */
public class InscriptionServlet extends HttpServlet {

    HttpSession session;
    Utilisateur user;

    @Override
    public void init() throws ServletException {
    }

    public InscriptionServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        session = request.getSession(false);
        session.setAttribute("servlet", getClass().getName());
        user = (Utilisateur) session.getAttribute("user");
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

        user = (Utilisateur) session.getAttribute("user");
        boolean champOk = true;
        boolean defaultAvatar = false; // par défaut l'utilisateur choisi son avatar
        String mesNom = "";
        String mesPrenom = "";
        String mesDn = "";
        String mesSexe = "";
        String mesEmail = "";
        String mesMdp = "";
        String mesVille = "";
        String mesPays = "";
        String mesImage = "";

        // Récupération du formulaire
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
        //String urlimage = request.getParameter("image");
        Part part = request.getPart("image");

        // Contrôle de validité
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
        // si l'utilisateur n'a pas sélectionné d'image, alors on mettra un avatar par défaut
        //if (urlimage == null || urlimage.isEmpty()) {
        /*
         * if (part == null) { mesImage = "Veuillez sélectionner une image";
         * request.setAttribute("ErreurImage", mesImage); defaultAvatar = true;
         * }
         */

        UtilisateurFacade uFacade1 = ServicesLocator.getUtilisateurFacade();
        Utilisateur userEmail = uFacade1.findEmail(HashMD5.encode(email));

        String extensionFichier = "";
        /*
         * if(!defaultAvatar) {
         */
        String message = "";
        // on lit le nom du fichier
        extensionFichier = request.getPart("image").toString();
        int p = extensionFichier.indexOf(",");
        if (p == 10) {
            defaultAvatar = true;
        } else {
            extensionFichier = extensionFichier.substring(10, p);

            int pos = extensionFichier.lastIndexOf("."); // on récupère la position du . en partant de la fin
            if (pos != -1) // si on trouve
            {
                extensionFichier = extensionFichier.substring(pos).toLowerCase();
                if (!".jpg".equals(extensionFichier) && !".jpeg".equals(extensionFichier) && !".png".equals(extensionFichier)) {
                    champOk = false;
                }
            } else {
                champOk = false; // on ne peut pas aller plus loin
            }
        }
        //}



        if (champOk && userEmail == null) {

            UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
            ImageFacade iFacade = ServicesLocator.getImageFacade();

            Utilisateur userbdd = new Utilisateur();


            Image img = null;
            if (!defaultAvatar) //on upload l'avatar choisi par l'utilisateur
            {
                try {
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
                    FileOutputStream os = new FileOutputStream(outputfile);

                    // on écrit les bytes du fichier de la source vers la destination
                    int ch = is.read();
                    while (ch != -1) {
                        os.write(ch);
                        ch = is.read();
                    }
                    os.close();
                    img = new Image(1, filename + extensionFichier);
                } catch (Exception ex) {
                    //out.println("Exception -->" + ex.getMessage());
                } finally {
                    //out.close();
                }
            } else // on met l'avatar par défaut
            {
                // changer ici pour l'image par défaut
//                if (sexe.equals("M")) {
//                    String defAvatar = this.getServletContext().getRealPath("/upload/avatars/male-icon.png");
//                    img = new Image(1, "male-icon.png");
//                } else {
//                    String defAvatar = this.getServletContext().getRealPath("/upload/avatars/female-icon.png");
//                    img = new Image(1, "female-icon.png");
//                }

                //String defAvatar = "..\\upload\\avatars\\Koala.jpg";
                String defAvatar = this.getServletContext().getRealPath("/upload/avatars/user.png");
                img = new Image(1, "user.png");

            }


            iFacade.create(img);





            //affiche id image
            userbdd.setFKidImage(img);
            //user.setFKidImage(img);

            userbdd.setIdUtilisateur(1);
            userbdd.setFKidpays(new Pays(Integer.parseInt(pays))); // ok
            userbdd.setNom(nom); // ok
            userbdd.setPrenom(prenom); //ok
            userbdd.setDateNaissance(dateNaissance); //ok
            userbdd.setSexe(sexe); // ok
            userbdd.setEmail(HashMD5.encode(email)); // ok
            userbdd.setPassword(HashMD5.encode(mdp)); //ok
            userbdd.setEcole(ecole);// ok
            userbdd.setVille(ville); //ok    
            userbdd.setSiteWeb(site); //ok
            //user.setFKidrang(new Rang(1)); // ok 
            userbdd.setFKidrang(new Rang(1)); // ok
            //user.setFKidetatutlisateur(new EtatUtilisateur(1));  // ok
            userbdd.setFKidetatutlisateur(new EtatUtilisateur(1));  // ok
            userbdd.setPoints(50); //ok 
            userbdd.setNbrSignal(0); // ok       


            uFacade.create(userbdd);

            //HttpSession session = request.getSession(true);
            //session.setAttribute("Utilisateur", user);




            
             EmailSender es = new EmailSender( "contact.studentmind@gmail.com", email,
              "Merci d'avoir rejoint la     communauté estudiantine StudentMind", "Bonjour " + prenom 
             + ",\n\nFélicitations ! Vous êtes maintenant inscrit sur notre " 
             + " plateforme de partage de ressources étudiantes. Cependant, vous"
             +" devez encore confirmer votre adresse email afin que votre compte"
             +" soit activé. Pour" + "se faire, veuillez cliquer sur le lien"
             +" suivant :"
             +" http://localhost:8080/StudentMind/confirm.html?e="+HashMD5.encode(email)
             +  "\nSi vous ne pouvez pas cliquer sur le lien ci-dessous,"
             +" veuillez le copier-coller dans la barre d'adresse de votre"
             +" navigateur." + "\n\n@ bientôt sur StudentMind. Nous vous"
             +" souhaitons d'ores et déjà de bons partages !" + "\n\nL'équipe"
             +" StudentMind");
             
            request.setAttribute("afficherAvatar", afficherImage());
            request.setAttribute("ListeCategorie", afficherCategorie());
            request.setAttribute("DocumentUne", afficherDocument());
            request.setAttribute("nbrDoc", afficherNbrDoc());
            request.setAttribute("nbrMembre", afficherNbrMembre());
            request.setAttribute("top", afficherTop());
            request.setAttribute("topUser", afficherTopUser());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } else {
            request.setAttribute("etatUpload", "Erreur de l'inscription");
            request.getRequestDispatcher("uploadTermine.jsp").forward(request, response);
        }

    }

    public String afficherCategorie() {

        String html = "";

        CategorieFacade cFacade = ServicesLocator.getCategorieFacade();
        List<Categorie> liste = cFacade.findAllAlpha();
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();

        for (Categorie cat : liste) {
            if (dFacade.nbrDocCat(cat.getIdCategorie()) > 0) {
                html += "<ul>"
                        + "<li><a href=\"liste-documents.html?idCategorie=" + cat.getIdCategorie() + "\">" + cat.getNomCategorie() + "</a> (" + dFacade.nbrDocCat(cat.getIdCategorie()) + ")</li>"
                        + "</ul>";
            }
        }
        return html;
    }

    public String afficherImage() {


        String html = "";

        if (user != null) {

            //html = "<img src=\""+user.getFKidImage().getUrlImage()+"\" title=\"avatar\" alt=\"avatar\" />";alt=\"avatar\"
            html = "<img src=\"upload/avatars/" + user.getFKidImage().getUrlImage() + "\" title=\"avatar\" alt=\"avatar\" height=\"70\" width=\"70\" />";
            //System.out.println(user.getFKidImage().getUrlImage());
        }

        return html;
    }

    public String afficherNbrDoc() {

        String html = "";
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        html = "" + dFacade.nbrDoc();
        return html;

    }

    public String afficherNbrMembre() {

        String html = "";
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        html = "" + uFacade.nbrUser();
        return html;

    }

    public String afficherDocument() {

        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        Document doc = dFacade.documentUne();
        if (doc != null) {
            Calendar cal = new GregorianCalendar();
            cal.setTimeInMillis(doc.getDate().getTime());
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
            String html = "<div class=\"article_header\"><header><h3>" + doc.getTitreDocument() + "</h3></header></div>"
                    + "<div class=\"article_content\">"
                    + "<p>" + doc.getDescriptionDocument() + "</p>"
                    + "</div>"
                    + "<div class=\"article_footer\">"
                    + "<footer><strong>Ajouté le</strong> "
                    + jour + " " + moisDate + " " + annee
                    + "<strong> par</strong> "
                    + doc.getFKidutilisateur().getNom() + " " + doc.getFKidutilisateur().getPrenom()
                    + "</footer></div>"
                    + ""
                    + ""
                    + "";

            return html;
        } else {
            return "";
        }
    }

    public String afficherTop() {
        String html = "<ul>";
        int longeur = 0;
        DocumentFacade dFacade = ServicesLocator.getDocumentFacade();
        List<Document> liste = dFacade.top3();
        for (Document doc : liste) {
            longeur = doc.getDescriptionDocument().length();
            if (longeur >= 150) {
                longeur = 150;
            }
            html += "<li><strong>" + doc.getTitreDocument() + "</strong> - " + doc.getDescriptionDocument().substring(0, longeur) + " <a href=\"voir-document.html?id=" + doc.getIdDocument() + "\"> Lire la suite</a></li>";
        }
        html += "</ul>";
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

    public String afficherTopUser() {
        UtilisateurFacade uFacade = ServicesLocator.getUtilisateurFacade();
        String html = "<ul>";

        List<Utilisateur> liste = uFacade.topUitlisateur();
        for (Utilisateur user : liste) {
            html += "<li><a href=\"afficher-profil.html?u=" + user.getIdUtilisateur() + "\">" + user.getNom() + " " + user.getPrenom() + "</a> (" + user.getPoints() + " pts.)</li>";
        }
        html += "</ul>";
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
}
