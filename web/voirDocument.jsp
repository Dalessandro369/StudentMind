<%-- 
    Document   : voirDocument
    Created on : 13-mai-2012, 18:04:10
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="left_menu">			
    <div class="menu">
        <header><h3>Informations du document</h3></header>
        ${infoDoc}
    </div>		
</div>
<div id="page">
    <div id="content">
       
        <h2>Vous êtes sur le point de télécharger un document !</h2>
         ${informationDoc} 
               
        <h2>Commentaires</h2>
        <div id="output_new_comm"></div>        
          
        ${ListeCommentaire}
    </div>
</div>

<%@ include file="footer.jspf"%>

