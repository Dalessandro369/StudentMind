<%-- 
    Document   : inbox
    Created on : 13-mai-2012, 19:43:38
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>studentmind - Home</title>
        <link href="css/www_default.css" rel="stylesheet" type="text/css" />
        <link href="css/tinytablesorter.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/tinytablesorter/packed.js"></script> 
        <script type="text/javascript" src="js/tinytablesorter/script.js"></script>
        <script type="text/javascript" src="js/jquery-1.6.2.min.js"></script>
        <script type="text/javascript" src="js/jquery-ui-1.8.16.custom.min.js"></script>
	<script>
	$(function() {
		$( "#progressbar" ).progressbar({
			value: 37
		});
	});
	</script>
    </head>
    <body>
        <%@ include file="header.jspf"%>

        <div id="left_menu">			
                <div class="menu">
                        <header><h3>Statistiques du site</h3></header>
                        <ul>
                                <li><strong><span class="">Nombre de membres :</span></strong> 224</li>
                                <li><strong><span class="">Nombre de documents :</span></strong> 63</li>
                        </ul>	
                </div>		
        </div>

        <div id="right_menu">
                <div class="menu">
                        <header><h3>Anthony Molina-Diaz</h3></header>
                        <div id="avatar">
                                <img src="../img/avatar.jpg" title="" alt="" />
                        </div>
                        <ul>
                                <li><a href="">Mon profil</a></li>
                                <li><a href="inbox.html">Ma messagerie privée</a> (4)</li>
                                <li><a href="">Mes documents</a> (12)</li>
                                <li><a href="">Se déconnecter</a></li>
                        </ul>
                </div>

                <div class="menu">
                        <header><h3>Ma messagerie privée</h3></header>
                        <ul>
                                <li><a href="new-message.html">Nouveau message</a></li>
                                <li><a href="">Boîte de réception</a> (4 nouveaux messages)</li>
                                <li><a href="">Messages envoyés</a></li>
                        </ul>
                </div>
        </div>

        <div id="page">
                <div id="content">			
                        <h2>Ma boîte de réception</h2>
                        <table id="inbox" class="sortable">
                                <thead> 
                                        <tr>
                                                <th class="nosort"><input type="checkbox" name="" id="" /></th>
                                                <th>Expéditeur</th>
                                                <th>Objet</th>
                                                <th>Date</th>
                                                <th class="nosort"></th>
                                        </tr>
                                </thead> 
                                <tbody>
                                        <tr>
                                                <td class="lu"><input type="checkbox" name="" id="" /></td>
                                                <td class="lu"><a href="">Benjamin Brunquers</a></td>
                                                <td class="lu"><a href="message.html">J'ai réussi !!</a></td>
                                                <td class="lu">17 décembre 2011 12:37</td>
                                                <td class="lu"><img src="../img/read.png" title="" alt="t" /></td>

                                        </tr>				
                                        <tr>
                                                <td><input type="checkbox" name="" id="" /></td>
                                                <td>Colin Reginster</td>
                                                <td>Est-ce que tu peux répondre à ce sondage, s.v.p ?</td>
                                                <td>04 novembre 2011 15:45</td>
                                                <td><img src="../img/unread.png" title="" alt="t" /></td>
                                        </tr>
                                        <tr>
                                                <td><input type="checkbox" name="" id="" /></td>
                                                <td>Bruno Boi</td>
                                                <td>Objet inconnu</td>
                                                <td>21 décembre 2011 12:45</td>
                                                <td><img src="../img/unread.png" title="" alt="t" /></td>
                                        </tr>
                                        <tr>
                                                <td><input type="checkbox" name="" id="" /></td>
                                                <td>Colin
                                                Reginster</td>
                                                <td>Mon premier sondage :)</td>
                                                <td>04 novembre 2011 11:31</td>
                                                <td><img src="../img/unread.png" title="" alt="t" /></td>
                                        </tr>
                                        <tr>
                                                <td><input type="checkbox" name="" id="" /></td>
                                                <td>Charlee Jeunhomme</td>
                                                <td>J'ai uploadé un nouveau document pour toi ;-)</td>
                                                <td>16 novembre 2011 23:49</td>
                                                <td><img src="../img/read.png" title="" alt="" /></td>
                                        </tr>	
                                </tbody>
                        </table>
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
    </body>
</html>
