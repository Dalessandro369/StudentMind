<%-- 
    Document   : voirDocument
    Created on : 13-mai-2012, 18:04:10
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="left_menu">			
    <div class="menu">
        <header><h3>Informations du document</h3></header>
        ${infoDoc}
    </div>		
</div>

<div id="right_menu">
    <div class="menu">
        <header><h3>Derniers documents</h3></header>
        <ul>
            <li><a href="document.html">Manier la trigonométrie</a> - Vous verrez que la trigo ne se résume pas à un ensemble de formules, c'est aussi une interprétation géométrique qui 
                a toute son importance. Et puis la trigo n'a pas été inventée pour rien : les applications sont nombreuses en physique.
            </li>
        </ul>
        <footer><a href="liste-documents.html">>> Vers tous les documents</a></footer>
    </div>		

    <div class="menu">
        <header><h3>Téléchargement du document</h3></header>
        <p>Vous devez être membre pour télécharger le document. <a href="inscription.html">S'inscrire gratuitement !</a></p>
    </div>

    <div class="menu">
        <header><h3>Poster un commentaire</h3></header>
        <p>Vous devez être membre pour réagir. <a href="inscription.html">S'inscrire gratuitement !</a></p>
    </div>

    <div class="menu">
        <header><h3>Noter un document</h3></header>
        <p>Vous devez être membre pour noter ce document. <a href="inscription.html">S'inscrire gratuitement !</a></p>
    </div>
</div>

<div id="page">
    <div id="content">
        <h2>Vous êtes sur le point de télécharger un document !</h2>
        ${informationDoc}
        

        <h2>Commentaires</h2>
        ${ListeCommentaire}
       
        

        
    </div>
</div>

<%@ include file="footer.jspf"%>

