<%-- 
    Document   : gererUtilisateur
    Created on : 13-mai-2012, 23:46:14
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ include file="header.jspf"%>

<div id="page">
    <div id="content">
        <h2>Modifier le rang d'un utilisateur / Bannir un utilisateur</h2>
        <table>
            <tr>
                <th>Rechercher</th>
                <th>Nom d'utilisateur</th>
                <th>Rang actuel</th>
                <th>Nouveau rang</th>
                <th>Commentaire(s) signalé(s)</th>
                <th>Valider</th>
                <th>Bannir membre</th>
            </tr>
            <tr>
                <td>
                    <script>
                        $(function() {
                            var availableTags = [
                                "ActionScript",
                                "AppleScript",
                                "Asp",
                                "BASIC",
                                "C",
                                "C++",
                                "Clojure",
                                "COBOL",
                                "ColdFusion",
                                "Erlang",
                                "Fortran",
                                "Groovy",
                                "Haskell",
                                "Java",
                                "JavaScript",
                                "Lisp",
                                "Perl",
                                "PHP",
                                "Python",
                                "Ruby",
                                "Scala",
                                "Scheme"
                            ];
                            $( "#tags" ).autocomplete({
                                source: availableTags
                            });
                        });
                    </script>
                    <div class="ui-widget">
                        <input class="nom_utilisateur" type="text" name="" id="tags" placeholder="Nom d'utilisateur" />
                    </div>
                </td>
                <td>
                    <select name="">
                        <option value="">Anthony Molina-Diaz</option>
                    </select>
                </td>
                <td class="colonne_description">Administrateur</td>
                <td>
                    <select name="">
                        <option value="">Membre</option>
                        <option value="">Modérateur</option>
                    </select>
                </td>
                <td>4</td>
                <td class="colonne_action">
                    <img src="../img/adduser.png" title="Ajouter" alt="Ajouter" />
                </td>
                <td class="colonne_action">
                    <img src="../img/bannir.png" title="Bannir" alt="Bannir" />
                </td>
            </tr>					
        </table>				
    </div>
</div>

<%@ include file="footer.jspf"%>