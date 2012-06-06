<%-- 
    Document   : contact
    Created on : 13-mai-2012, 17:28:00
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Vous êtes sur le point de contacter nos administrateurs</h2>			
        <form method="POST" action="contact.html">
            <fieldset>
                <legend>Formulaire de contact</legend>
                <label for="nom">Nom :</label> <input type="text" name="nom" id="nom"  required /><br/>
                <label for="email">Adresse e-mail :</label> <input type="text" name="email" id="email"  required /><br/>
                <label for="objet">Objet :</label> <input type="text" name="objet" id="objet" required /><br/>
                <label for="message">Message :</label> <textarea name="message" id="message" required ></textarea><br/>
            </fieldset>

            <input type="submit" value="Envoyer mon message" />
        </form>				
    </div>
</div>

<%@ include file="footer.jspf"%>
