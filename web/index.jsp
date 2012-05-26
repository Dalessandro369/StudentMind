<%-- 
    Document   : index
    Created on : 12-mai-2012, 15:07:09
    Author     : ProjetJava
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Télécharger un document</h2>
        ${ListeCategorie}
 		

        <h2>Article à la une</h2>
        ${DocumentUne}
    </div>
</div>

<%@ include file="footer.jspf"%>
