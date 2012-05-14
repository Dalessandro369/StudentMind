<%-- 
    Document   : contact
    Created on : 13-mai-2012, 17:28:00
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>studentmind - Home</title>
        <link href="css/www_default.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
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

        </div>

        <div id="page">
                <div id="content">
                        <h2>Vous êtes sur le point de contacter nos administrateurs</h2>			
                        <form method="" action="">
                                <fieldset>
                                        <legend>Formulaire de contact</legend>
                                        <label for="">Nom :</label> <input type="text" name="" id=""  required /><br/>
                                        <label for="">Adresse e-mail :</label> <input type="text" name="" id=""  required /><br/>
                                        <label for="">Objet :</label> <input type="text" name="" id=""  required /><br/>
                                        <label for="">Message :</label> <textarea required ></textarea><br/>
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

                                <input type="button" value="Envoyer mon message" />
                        </form>				
                </div>
        </div>

        <%@ include file="footer.jspf"%>
    </body>
</html>
