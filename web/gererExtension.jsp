<%-- 
    Document   : gererExtension
    Created on : 13-mai-2012, 23:59:19
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Liste de toutes les extensions de documents</h2>
        <table>
            <tr>
                <th class="colonne_nom">Nom de l'extension</th>
                <th>Description</th>
                <th>Action</th>
            </tr>					
            <tr>
                <td class="colonne_nom">.doc</td>
                <td class="colonne_description">Ce type d'extension appartient à la famille Microsoft et regroupe les documents Word</td>
                <td class="colonne_action">
                    <img src="../img/edit.png" title="Editer" alt="Editer" />
                    <img src="../img/delete.png" title="Supprimer" alt="Supprimer" />
                </td>
            </tr>
            <tr>
                <td class="colonne_nom"><input type="text" name="" id="" placeholder="Nouvelle extension" /></td>
                <td class="colonne_description"><input type="text" name="" id="" placeholder="Description de la nouvelle extension" /></td>
                <td class="colonne_action">
                    <img src="../img/valid.png" title="Ajouter" alt="Ajouter" />
                </td>
            </tr>
        </table>				
    </div>
</div>

<%@ include file="footer.jspf"%>