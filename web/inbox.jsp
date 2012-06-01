<%-- 
    Document   : inbox
    Created on : 13-mai-2012, 19:43:38
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">			
        <h2>Ma boîte de réception</h2>
        ${ListeMessageReception}
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
