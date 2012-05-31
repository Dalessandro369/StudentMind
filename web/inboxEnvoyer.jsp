<%-- 
    Document   : inboxEnvoyer
    Created on : 31-mai-2012, 15:32:00
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">			
        <h2>Ma boîte de réception</h2>
        ${ListeMessageEnvoyer}
        <!-- Ne surtout pas déplacer le script qui suit ! -->
        <script type="text/javascript">
            var sorter = new TINY.table.sorter("sorter");
            sorter.head = "head";
            sorter.asc = "asc";
            sorter.desc = "desc";
            sorter.even = "evenrow";
            sorter.odd = "oddrow";
            sorter.evensel = "evenselected";
            sorter.oddsel = "oddselected";
            sorter.paginate = true;
            sorter.currentid = "currentpage";
            sorter.limitid = "pagelimit";
            sorter.init("inbox",1);
        </script>
    </div>
</div>

<%@ include file="footer.jspf"%>
