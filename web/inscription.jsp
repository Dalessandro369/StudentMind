<%-- 
    Document   : inscription
    Created on : 13-mai-2012, 18:04:29
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Je m'inscris !</h2>
        <form method="POST" action="inscription.html">
            <fieldset>
                <legend>Informations générales</legend>
                <label for="nom">Nom :</label> <input type="text" name="nom" id="nom" required /><br/>
                <label for="prenom">Prénom :</label> <input type="text" name="prenom" id="prenom" required /><br/>
                <label for="dateNaissance">Date de naissance :</label>
                <script>
                    $(function() {
                        $( "#datepicker" ).datepicker({
                            changeMonth: true,
                            changeYear: true
                        });
                    });
                </script>
                <input type="text" name="dateNaissance" id="datepicker"><br/>
                <label for="sexe">Sexe :</label>
                <select name="sexe" id="sexe" required>
                    <option value="M">M</option>
                    <option value="F">F</option>
                </select><br/>
                <label for="email">Adresse e-mail :</label> <input type="text" name="email" id="email" required /><br/>
                <label for="mdp">Mot de passe : </label> 
                <input type="password" name="mdp" id="mdp" required /> 
                <input type="password" name="mdp2" id="mdp2" placeholder="Confirmez votre mot de passe" required />
            </fieldset>

            <fieldset>
                <legend>Profil étudiant</legend>
                <label for="image">Avatar :</label> <input type="file" name="image" id="image" required/><br/>
                <label for="ecole">Ecole / Université :</label> <input type="text" name="ecole" id="ecole" /><br/>
                <label for="site">Site Web :</label> <input type="text" name="site" id="site" placeholder="http://"/><br/>
                <label for="ville">Ville :</label> <input type="text" name="ville" id="ville" required /><br/>
                <label for="pays" required>Pays :</label>
                <select name="pays" id="pays">
                    ${listePays}
                </select>
            </fieldset>

            <fieldset>
                <legend>Contrôle anti-robot</legend>
                <label for="verif">Anti-spam :</label>
                <script type="text/javascript" src="http://www.google.com/recaptcha/api/challenge?k=6LcSYssSAAAAAEXFSGjOCoP14CoMv4e136L8Klxk"></script>
                <noscript>
                <iframe src="http://www.google.com/recaptcha/api/noscript?k=6LcSYssSAAAAAEXFSGjOCoP14CoMv4e136L8Klxk"
                        height="300" width="500" frameborder="0"></iframe><br>
                <textarea name="recaptcha_challenge_field" rows="3" cols="40"></textarea>
                <input type="hidden" name="recaptcha_response_field" value="manual_challenge">
                </noscript>
            </fieldset>

            <input type="submit" value="Je m'inscris !" />
        </form>
    </div>
</div>

<%@ include file="footer.jspf"%>
