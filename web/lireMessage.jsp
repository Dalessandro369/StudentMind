<%-- 
    Document   : lireMessage
    Created on : 13-mai-2012, 19:48:57
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">			
        <h2>Lire nouveau message</h2>        
        <div id="new_message">
           ${Message} 
        </div>
    </div>
</div>

<%@ include file="footer.jspf"%>
</body>
</html>
