<%-- 
    Document   : gererFileAttenteDocument
    Created on : 24-mai-2012, 9:38:07
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Gérer la file d'attente des documents</h2>
        ${ListeDocument}
    </div>
</div>

<%@ include file="footer.jspf"%>
