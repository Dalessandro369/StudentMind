<%-- 
    Document   : voirDocument
    Created on : 13-mai-2012, 18:04:10
    Author     : ProjetJava
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>studentmind</title>
        <!-- CSS -->
        <link href="css/www_default.css" rel="stylesheet" type="text/css" media="screen"/>
        <link href="css/jRating.jquery.css" rel="stylesheet" type="text/css" media="screen" />
        <!-- Scripts -->
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jRating.jquery.js"></script>	
    </head>
    <body>
        <%@ include file="header.jspf"%>

        <div id="left_menu">			
                <div class="menu">
                        <header><h3>Informations du document</h3></header>
                        <ul>
                                <li><strong>Type :</strong> Cours / Synthèse de cours</li>
                                <li><strong>Matière :</strong> Mathématique</li>
                                <li><strong>Taille du fichier :</strong> 4.3 Mo</li>
                                <li><strong>Type de fichier :</strong> .doc (Microsoft Word)</li>
                                <li><strong>Téléchargé :</strong> 217 fois</li>
                                <li>
                                        <strong>Moyenne :</strong>
                                        <script type="text/javascript">
                                            $(document).ready(function(){
                                                $(".avg_note").jRating({

                                                    type:'small', // type of the rate.. can be set to 'small' or 'big'
                                                    length : 5, // nb of stars
                                                    decimalLength : 1, // number of decimal in the rate
                                                    isDisabled : true,
                                                });
                                            });
                                        </script>
                                        <div class="avg_note" data="2"></div>
                                </li>
                        </ul>
                </div>		
        </div>

        <div id="right_menu">
                <div class="menu">
                        <header><h3>Derniers documents</h3></header>
                        <ul>
                                <li><a href="document.html">Manier la trigonométrie</a> - Vous verrez que la trigo ne se résume pas à un ensemble de formules, c'est aussi une interprétation géométrique qui 
                                        a toute son importance. Et puis la trigo n'a pas été inventée pour rien : les applications sont nombreuses en physique.
                                </li>
                        </ul>
                        <footer><a href="liste-documents.html">>> Vers tous les documents</a></footer>
                </div>		

                <div class="menu">
                        <header><h3>Téléchargement du document</h3></header>
                        <p>Vous devez être membre pour télécharger le document. <a href="inscription.html">S'inscrire gratuitement !</a></p>
                </div>

                <div class="menu">
                        <header><h3>Poster un commentaire</h3></header>
                        <p>Vous devez être membre pour réagir. <a href="inscription.html">S'inscrire gratuitement !</a></p>
                </div>

                <div class="menu">
                        <header><h3>Noter un document</h3></header>
                        <p>Vous devez être membre pour noter ce document. <a href="inscription.html">S'inscrire gratuitement !</a></p>
                </div>
        </div>

        <div id="page">
                <div id="content">
                        <h2>Vous êtes sur le point de télécharger un document !</h2>
                        <div class="article_header"><header><h3>Manier la trigonométrie</h3></header></div>
                        <div class="article_content">
                                <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit. Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna. Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.
                                </p>
                        </div>
                        <div class="article_footer"><footer><strong>Catégorie :</strong> Mathématique</footer></div>

                        <h2>Commentaires</h2>
                        <div class="commentaire_icon"></div>
                        <div class="commentaire_header_article"><header><strong>Auteur :</strong> Anthony Molina-Diaz</header></div>
                        <div class="commentaire_content_article">
                                <blockquote>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit. Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna. Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.
                                </blockquote>
                        </div>
                        <div class="commentaire_footer_article"><footer><strong>Posté le, à :</strong> 12 décembre 2011, 15h12</footer></div>

                        <div class="commentaire_icon"></div>
                        <div class="commentaire_header_article"><header><strong>Auteur :</strong> Jean Traham</header></div>
                        <div class="commentaire_content_article">
                                <blockquote>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit. Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna. Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.
                                </blockquote>
                        </div>
                        <div class="commentaire_footer_article"><footer><strong>Posté le, à :</strong> 12 décembre 2011, 15h12</footer></div>

                        <div class="commentaire_icon"></div>
                        <div class="commentaire_header_article"><header><strong>Auteur :</strong> Alessandro D'aviera</header></div>
                        <div class="commentaire_content_article">
                                <blockquote>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit. Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna. Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.
                                </blockquote>
                        </div>
                        <div class="commentaire_footer_article"><footer><strong>Posté le, à :</strong> 12 décembre 2011, 15h12</footer></div>
                </div>
        </div>
        
        <%@ include file="footer.jspf"%>
    <body>
</html>