<%-- 
    Document   : uploadDocument
    Created on : 13-mai-2012, 23:39:27
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>    

<div id="page">
    <div id="content">
        <h2>Partager un document</h2>
        <form method="POST" action="fileUpload" enctype="multipart/form-data">
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
                <label for="description">Description :</label> <textarea name="description" id="description" required></textarea><br/>
            </fieldset>

            <fieldset>
                <legend>Choisissez le document à uploader</legend>
                <input type="file" name="urlFichier" id="urlFichier" required />
            </fieldset>

            <input type="submit" value="Uploader" />
        </form>
    </div>
</div>

<%@ include file="footer.jspf"%>
