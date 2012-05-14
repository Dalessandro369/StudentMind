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
                        <form method="POST" action="\upload" enctype="multipart/form-data">
                                <fieldset>
                                        <legend>Fiche technique à compléter</legend>
                                        <label for="">Type de document :</label>
                                        <select name="">
                                                <option value="">Biographie</option>
                                                <option value="">Exposé</option>
                                        </select><br/>
                                        <label for="">Catégorie :</label>
                                        <select name="">
                                                <option value="">Anglais</option>
                                        </select><br/>
                                        <label for="">Titre :</label> <input type="text" name="" id="" class="titre_document" required /><br/>
                                        <label for="">Introduction :</label> <textarea></textarea><br/>
                                </fieldset>

                                <fieldset>
                                        <legend>Choisissez le document à uploader</legend>
                                        <input id="file_upload" name="file_upload" type="file" />
                                </fieldset>

                                <input type="submit" value="Uploader" />
                        </form>
                </div>
        </div>

        <%@ include file="footer.jspf"%>
    </body>
</html>
