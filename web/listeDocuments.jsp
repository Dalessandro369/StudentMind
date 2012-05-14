<%-- 
    Document   : listeDocuments
    Created on : 13-mai-2012, 18:03:53
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>studentmind - Tous les documents</title>
	<link href="css/www_default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@ include file="header.jspf"%>

        <div id="left_menu">			
                <div class="menu">
                        <header><h3>Statistiques</h3></header>
                        <ul>
                                <li><strong><span class="">Documents Word :</span></strong> 40</li>
                                <li><strong><span class="">Documents OpenOffice.org :</span></strong> 3</li>
                                <li><strong><span class="">Documents Adobe (PDF) :</span></strong> 20</li>
                        </ul>	
                </div>		
        </div>

        <div id="right_menu">
                <div class="menu">
                        <header><h3>Catégories de documents</h3></header>
                        <ul>
                                <li><a href="">Anglais</a> (5)</li>
                                <li><a href="">Français</a> (17)</li>
                                <li><a href="">Mathémtique</a> (2)</li>
                        </ul>	
                </div>			

                <div class="menu">
                        <header><h3>Derniers documents</h3></header>
                        <ul>
                                <li><a href="document.html">Manier la trigonométrie</a> - Vous verrez que la trigo ne se résume pas à un ensemble de formules, c'est aussi une interprétation géométrique qui 
                                        a toute son importance. Et puis la trigo n'a pas été inventée pour rien : les applications sont nombreuses en physique.
                                </li>
                        </ul>
                        <footer><a href="liste-documents.html">>> Vers tous les documents</a></footer>
                </div>
        </div>

        <div id="page">
                <div id="content">
                        <h2>Liste de tous les documents</h2>
                        <div class="article_header_articles"><header><h3><a href="document.html">Manier la trigonométrie</a></h3></header></div>
                        <div class="article_content_articles">
                                <ul>
                                        <li><strong><span class="">Type :</span></strong> Cours / Synthèse de cours</li>
                                        <li><strong><span class="">Matière : </span></strong> Mathématique</li>
                                        <li><strong><span class="">Taille du fichier :</span></strong> 4.3 Mo</li>
                                        <li><strong><span class="">Type de fichier :</span></strong> .doc (Microsoft Word)</li>
                                        <li><strong><span class="">Téléchargé :</span></strong> 217 fois</li>
                                        <li><strong><span class="">Note :</span></strong> 3.5/5</li>
                                </ul>	
                        </div>
                        <div class="article_footer"><footer></footer></div>
                </div>
        </div>

        <%@ include file="footer.jspf"%>
    </body>
</html>
