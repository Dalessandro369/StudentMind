<%-- 
    Document   : rechercherDocument
    Created on : 13-mai-2012, 18:08:51
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Rechercher un document</h2>
        <form method="POST" action="rechercher-document.html">
            <fieldset>
                <legend>Informations générales</legend>
                <label for="extension">Type de fichier :</label>
                <select name="extension" id="extension">
                    ${ListeExtension}
                    <!-- <optgroup label="Microsoft">
                             <option value="">.doc</option>
                             <option value="">.docx</option>
                             <option value="">.ppt</option>
                             <option value="">.pptx</option>	
                             <option value="">.xls</option>	
                             <option value="">.xlsx</option>									
                     </optgroup>
                     <optgroup label="OpenOffice / LibreOffice">
                             <option value="">.odt</option>
                             <option value="">.ods</option>
                             <option value="">.odp</option>
                     </optgroup>
                     <optgroup label="Adobe Systems">
                             <option value="">.pdf</option>
                     </optgroup>-->
                </select><br/>
                <label for="type">Type de document :</label>
                <select name="type">
                    ${ListeType}
                </select><br/>
                <label for="categorie">Catégorie :</label>
                <select name="categorie" id="categorie">
                    ${ListeCategorie}
                </select><br/>

                <label for="mot-cle">Mots clés :</label> <input type="text" name="mot-cle" id="mot-cle" />
            </fieldset>

            <input type="submit" value="Lancer la recherche !" />
        </form>				
    </div>
</div>

<%@ include file="footer.jspf"%>
