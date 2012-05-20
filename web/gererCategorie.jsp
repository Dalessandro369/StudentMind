<%-- 
    Document   : gererCategorie
    Created on : 14-mai-2012, 0:00:27
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

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