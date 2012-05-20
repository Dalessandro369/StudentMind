<%-- 
    Document   : lireMessage
    Created on : 13-mai-2012, 19:48:57
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">			
        <h2>Lire nouveau message</h2>
        <div id="new_message">
            <img src="../img/avatar2.jpg" title="" alt="" />
            <ul>
                <li><label><strong>Expéditeur :</strong></label> Benjamin Brunquers</li>
                <li><label><strong>Objet :</strong></label> J'ai réussi !!</li>
                <li><label><strong>Date :</strong></label> 17 décembre 2011 12:37</li>
                <li><a href="">Supprimer</a> <img src="../img/trash.gif" title="" alt="" /></li>
            </ul>
            <blockquote>
                Salut Anthony, j'ai réussi à télécharger mon premier document sur studentmind ! Votre plateforme est vraiment super géniale :)
            </blockquote>
            <textarea placeholder="Cliquez ici pour répondre"></textarea></br>
            <input type="button" value="Répondre" />
        </div>


    </div>
</div>

<%@ include file="footer.jspf"%>
</body>
</html>
