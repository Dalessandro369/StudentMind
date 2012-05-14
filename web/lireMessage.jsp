<%-- 
    Document   : lireMessage
    Created on : 13-mai-2012, 19:48:57
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>studentmind - Lire nouveau message</title>
        <link href="css/www_default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@ include file="header.jspf"%>

        <div id="left_menu">			
                <div class="menu">
                        <header><h3>Statistiques du site</h3></header>
                        <ul>
                                <li><strong><span class="">Nombre de membres :</span></strong> 224</li>
                                <li><strong><span class="">Nombre de documents :</span></strong> 63</li>
                        </ul>	
                </div>		
        </div>

        <div id="right_menu">
                <div class="menu">
                        <header><h3>Anthony Molina-Diaz</h3></header>
                        <div id="avatar">
                                <img src="../img/avatar.jpg" title="" alt="" />
                        </div>
                        <ul>
                                <li><a href="">Mon profil</a></li>
                                <li><a href="inbox.html">Ma messagerie privée</a> (4)</li>
                                <li><a href="">Mes documents</a> (12)</li>
                                <li><a href="">Se déconnecter</a></li>
                        </ul>
                </div>

                <div class="menu">
                        <header><h3>Ma messagerie privée</h3></header>
                        <ul>
                                <li><a href="new-message.html">Nouveau message</a></li>
                                <li><a href="">Boîte de réception</a> (4 nouveaux messages)</li>
                                <li><a href="">Messages envoyés</a></li>
                        </ul>
                </div>
        </div>

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
