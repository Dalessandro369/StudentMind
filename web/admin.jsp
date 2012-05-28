<%-- 
    Document   : admin
    Created on : 20-mai-2012, 17:00:37
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Gestion des documents</h2>
        <div class="management">
            <figure>
                <img src="img/folder.png" alt="">
                <figcaption><a href="gerer-type.html">Gérer les types</a></figcaption>
            </figure>
            <figure>
                <img src="img/folder.png" alt="">
                <figcaption><a href="gerer-categorie.html">Gérer les catégories</a></figcaption>
            </figure>
            <figure>
                <img src="img/folder.png" alt="">
                <figcaption><a href="gerer-extension.html">Gérer les extensions</a></figcaption>
            </figure>
        </div>
        <div class="clear"></div>
        <h2>Gestion des demandes</h2>
        <div class="management">
            <figure>
                <img src="img/admin-demandes.png" alt="">
                <figcaption><a href="gerer-abus.html">Gérer les signalements d'abus</a></figcaption>
            </figure>
            <figure>
                <img src="img/admin-demandes.png" alt="">
                <figcaption><a href="gerer-file-attente-document.html">Gérer la file d'attente des documents</a></figcaption>
            </figure>
        </div>
        <div class="clear"></div>
        <h2>Gestion des utilisateurs</h2>
        <div class="management">
            <figure>
                <img src="img/admin-user.png" alt="">
                <figcaption><a href="gerer-utilisateur.html">Modifier rang / Bannir</a></figcaption>
            </figure>
        </div>
        <div class="clear"></div>
    </div>
</div>

<%@ include file="footer.jspf"%>