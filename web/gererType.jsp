<%-- 
    Document   : gererType
    Created on : 13-mai-2012, 23:57:34
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Liste de tous les types de documents</h2>
        <table>
            <tr>
                <th class="colonne_nom">Nom du type</th>
                <th>Description</th>
                <th>Action</th>
            </tr>					
            <tr>
                <td class="colonne_nom">Cours / Synthèse de cours</td>
                <td class="colonne_description">Ce type de document regroupe les synthèses de cours</td>
                <td class="colonne_action">
                    <img src="../img/edit.png" title="Editer" alt="Editer" />
                    <img src="../img/delete.png" title="Supprimer" alt="Supprimer" />
                </td>
            </tr>
            <tr>
                <td class="colonne_nom"><input type="text" name="" id="" placeholder="Nouveau type" /></td>
                <td class="colonne_description"><input type="text" name="" id="" placeholder="Description du nouveau type" /></td>
                <td class="colonne_action">
                    <img src="../img/valid.png" title="Ajouter" alt="Ajouter" />
                </td>
            </tr>
        </table>		
    </div>
</div>

<%@ include file="footer.jspf"%>