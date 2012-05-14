<%-- 
    Document   : gererAbus
    Created on : 14-mai-2012, 0:01:29
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
                <div class="menu">
                        <header><h3>Configuration du système</h3></header>
                        <ul>
                                <li><a href="gerer-systeme.html">Gérer les paramètres système</a></li>
                        </ul>
                </div>
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
                        <h2>Gestion des commentaires signalés</h2>
                        <table>
                                <tr>
                                        <th class="colonne_nom">Auteur du commentaire</th>
                                        <th>Commentaire</th>
                                        <th>Action</th>
                                </tr>					
                                <tr>
                                        <td class="colonne_nom">Alessandro D'aviera</td>
                                        <td class="colonne_description">Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna. Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.</td>
                                        <td class="colonne_action">
                                                <img src="../img/valid.png" title="Valider" alt="Valider" />
                                                <img src="../img/delete.png" title="Supprimer" alt="Supprimer" />
                                        </td>
                                </tr>
                                <tr>
                                        <td class="colonne_nom">Bruno Boi</td>
                                        <td class="colonne_description">Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna. Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.</td>
                                        <td class="colonne_action">
                                                <img src="../img/valid.png" title="Valider" alt="Valider" />
                                                <img src="../img/delete.png" title="Supprimer" alt="Supprimer" />
                                        </td>
                                </tr>				
                        </table>
                </div>
        </div>

        <%@ include file="footer.jspf"%>
    </body>
</html>
