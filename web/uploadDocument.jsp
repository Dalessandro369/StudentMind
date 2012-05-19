<%-- 
    Document   : uploadDocument
    Created on : 13-mai-2012, 23:39:27
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>studentmind</title>
        <link href="css/www_default.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%@ include file="header.jspf"%>

        <div id="left_menu">

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
        </div>

        <div id="page">
                <div id="content">
                        <h2>Partager un document</h2>
                        <form method="POST" action="upload-document.html" enctype="multipart/form-data">
                                <fieldset>
                                        <legend>Fiche technique à compléter</legend>
                                        <label for="type">Type de document :</label>
                                        <select name="type" id="type">
                                            ${ListeType}
                                        </select><br/>
                                        <label for="categorie">Catégorie :</label>
                                        <select name="categorie" id="categorie">
                                                ${ListeCategorie}
                                        </select><br/>
                                        <label for="titre">Titre :</label> <input type="text" name="titre" id="titre" class="titre_document" required /><br/>
                                        <label for="intro">Introduction :</label> <textarea name="intro" id="intro" required></textarea><br/>
                                </fieldset>

                                <fieldset>
                                        <legend>Choisissez le document à uploader</legend>
                                        <input id="file_upload" name="file_upload" type="file" required/>
                                </fieldset>

                                <input type="submit" value="Uploader" />
                        </form>
                </div>
        </div>

        <%@ include file="footer.jspf"%>
    </body>
</html>
