<%-- 
    Document   : gererCategorie
    Created on : 14-mai-2012, 0:00:27
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
                        <header><h3>Gestion des documents</h3></header>
                        <ul>
                                <li><a href="gerer-types.html">Gérer les types</a></li>
                                <li><a href="gerer-categories.html">Gérer les catégories</a></li>
                                <li><a href="gerer-extensions.html">Gérer les extensions</a></li>
                        </ul>
                </div>

                <div class="menu">
                        <header><h3>Gestion des demandes</h3></header>
                        <ul>
                                <li><a href="gerer-abus.html">Gérer les signalements d'abus</a> (2)</li>
                        </ul>
                </div>

                <div class="menu">
                        <header><h3>Gestion des utilisateurs</h3></header>
                        <ul>
                                <li><a href="gerer-user.html">Modifier rang / Bannir</a></li>
                        </ul>
                </div>			
        </div>

        <div id="page">
                <div id="content">
                        <h2>Liste de toutes les catégories de documents</h2>
                        <table>
                                <tr>
                                        <th class="colonne_nom">Nom de la catégorie</th>
                                        <th>Description</th>
                                        <th>Action</th>
                                </tr>					
                                <tr>
                                        <td class="colonne_nom">Anglais</td>
                                        <td class="colonne_description">Cette catégorie regroupe les documents liés à la langue de Shakespeare</td>
                                        <td class="colonne_action">
                                                <img src="../img/edit.png" title="Editer" alt="Editer" />
                                                <img src="../img/delete.png" title="Supprimer" alt="Supprimer" />
                                        </td>
                                </tr>
                                <tr>
                                        <td class="colonne_nom"><input type="text" name="" id="" placeholder="Nouvelle catégorie" /></td>
                                        <td class="colonne_description"><input type="text" name="" id="" placeholder="Description de la nouvelle catégorie" /></td>
                                        <td class="colonne_action">
                                                <img src="../img/valid.png" title="Ajouter" alt="Ajouter" />
                                        </td>
                                </tr>
                        </table>				
                </div>
        </div>

        <%@ include file="footer.jspf"%>
    </body>
</html>
