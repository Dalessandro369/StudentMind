<%-- 
    Document   : inscription
    Created on : 13-mai-2012, 18:04:29
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>studentmind</title>
        <link href="css/www_default.css" rel="stylesheet" type="text/css" />
        <link href="css/ui-lightness/jquery-ui-1.8.16.custom.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.16.custom.min.js"></script>
        <script type="text/javascript"> <!-- Captcha Google -->
                    var RecaptchaOptions = {
                        theme : 'clean'
                    };
        </script>	
    </head>
    <body>
        <%@ include file="header.jspf"%>

        <div id="left_menu">

        </div>

        <div id="right_menu">			
                <div class="menu">
                        <header><h3>Pourquoi s'inscrire</h3></header>
                        <ul>
                                <li>Rédigez et partagez de nombreux tutoriels ;</li>
                                <li>Uploadez vos propres documents à la communauté des étudiants de studentmind, tels que des synthèses de cours, exposés, fiches de lecture, dissertations,
                                rapports de stage et autres mémoires ;</li>
                                <li>Evaluez et commentez les articles mis à disposition des étudiants.</li>
                        </ul>
                </div>
        </div>

        <div id="page">
                <div id="content">
                        <h2>Je m'inscris !</h2>
                        <form method="" action="">
                                <fieldset>
                                        <legend>Informations générales</legend>
                                        <label for="">Nom :</label> <input type="text" name="" id="" required /><br/>
                                        <label for="">Prénom :</label> <input type="text" name="" id="" required /><br/>
                                        <label for="">Date de naissance :</label>
                                        <script>
                                        $(function() {
                                                $( "#datepicker" ).datepicker({
                                                        changeMonth: true,
                                                        changeYear: true
                                                });
                                        });
                                        </script>
                                        <input type="text" id="datepicker"><br/>
                                        <label for="">Sexe :</label>
                                        <select name="" id="">
                                                <option value="M">M</option>
                                                <option value="F">F</option>
                                        </select><br/>
                                        <label for="">Adresse e-mail :</label> <input type="text" name="" id="" required /><br/>
                                        <label for="">Mot de passe : </label> 
                                        <input type="password" name="" id="" required /> 
                                        <input type="password" name="" id="" placeholder="Confirmez votre mot de passe" required />
                                </fieldset>

                                <fieldset>
                                        <legend>Profil étudiant</legend>
                                        <label for="">Avatar :</label> <input type="file" name="" id="" /><br/>
                                        <label for="">Ecole / Université :</label> <input type="text" name="" id="" /><br/>
                                        <label for="">Site Web :</label> <input type="text" name="" id="" placeholder="http://"/><br/>
                                        <label for="">Ville :</label> <input type="text" name="" id="" /><br/>
                                        <label for="">Pays :</label>
                                        <select name="" id="">
                                                <option value="BE">Belgique</option>
                                        </select>
                                </fieldset>

                                <fieldset>
                                        <legend>Contrôle anti-robot</legend>
                                        <label for="">Anti-spam :</label>
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
    </body>
</html>
