<%-- 
    Document   : newjsp
    Created on : 12-mai-2012, 15:07:09
    Author     : ProjetJava
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>



<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/www_default.css" rel="stylesheet" type="text/css" />
        <title>HomeBoyRun</title>
    </head>
    <body>
        <header role="banner">
            <span class="namepart1">student</span><span class="namepart2">mind</span>

            <div id="login">
                    <form method="" action="">
                            <label for="">Adresse email :</label> <label for="">Mot de passe :</label><br/>
                            <input type="email" name="" for="" placeholder="Adresse email" required /> <input type="password" name="" for="" placeholder="Mot de passe" required />
                            <input type="submit" name="" for="" value="Connexion" /><br/>
                    </form>
            </div>

            <nav role="navigation">
                    <ul>
                            <li><a href="index.html">Accueil</a></li>
                            <li><a href="rechercher.html">Rechercher un document</a></li>
                            <li><a href="contact.html">Contact</a></li>
                            <li><a href="inscription.html">S'inscrire</a></li>
                    </ul>
            </nav>
        </header>

        <div id="left_menu">
            <div class="menu">
                    <header><h3>Qu'est-ce que studentmind ?</h3></header>
                    <p>Bienvenue sur <strong>studentmind</strong>, la plateforme de référence en matière de partage de ressources numériques, principalement étudiantes.</p> 
                    <p>Notre communauté met à disposition de nombreux documents téléchargeables, tels que des synthèses de cours, des exposés, des dissertations, des rapports de stages, CV & lettre
                    de motivation, et bien d'autres. Que vous soyez étudiants, à la recherche d'un emploi ou encore simple internaute, vous trouverez ce dont vous avez besoin sur studentmind.</p>
                    <p>Notre service est <strong>entièrement gratuit</strong> et propose de nombreux avantages à tous nos membres. Bonne visite !</p>
            </div>

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
                        <header><h3>Derniers documents</h3></header>
                        <ul>
                                <li><a href="document.html">Manier la trigonométrie</a> - Vous verrez que la trigo ne se résume pas à un ensemble de formules, c'est aussi une interprétation géométrique qui 
                                        a toute son importance. Et puis la trigo n'a pas été inventée pour rien : les applications sont nombreuses en physique.
                                </li>
                        </ul>
                        <footer><a href="liste-documents.html">>> Vers tous les documents</a></footer>
                </div>
        </div>

        <div id="page">
            <div id="content">
                    <h2>Télécharger un document</h2>
                    <ul>
                            <li><a href="">Biographie</a> (5)</li>
                            <li><a href="">Cours / Synthèse de cours</a> (12)</li>
                            <li><a href="">Dissertation</a> (3)</li>
                            <li><a href="">Exposé</a> (9)</li>
                            <li><a href="">Guide méthodologique / Tutoriel</a> (11)</li>
                            <li><a href="">CV / Lettre de motivation</a> (10)</li>
                            <li><a href="">Mémoire / Rapport de stage</a> (13)</li>
                    </ul>			

                    <h2>Article à la une</h2>
                    <div class="article_header"><header><h3>Noter les documents est désormais possible</h3></header></div>
                    <div class="article_content">
                            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed non risus. Suspendisse lectus tortor, dignissim sit amet, adipiscing nec, ultricies sed, dolor. Cras elementum ultrices diam. Maecenas ligula massa, varius a, semper congue, euismod non, mi. Proin porttitor, orci nec nonummy molestie, enim est eleifend mi, non fermentum diam nisl sit amet erat. Duis semper. Duis arcu massa, scelerisque vitae, consequat in, pretium a, enim. Pellentesque congue. Ut in risus volutpat libero pharetra tempor. Cras vestibulum bibendum augue. Praesent egestas leo in pede. Praesent blandit odio eu enim. Pellentesque sed dui ut augue blandit sodales. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Aliquam nibh. Mauris ac mauris sed pede pellentesque fermentum. Maecenas adipiscing ante non diam sodales hendrerit. Ut velit mauris, egestas sed, gravida nec, ornare ut, mi. Aenean ut orci vel massa suscipit pulvinar. Nulla sollicitudin. Fusce varius, ligula non tempus aliquam, nunc turpis ullamcorper nibh, in tempus sapien eros vitae ligula. Pellentesque rhoncus nunc et augue. Integer id felis. Curabitur aliquet pellentesque diam. Integer quis metus vitae elit lobortis egestas. Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi vel erat non mauris convallis vehicula. Nulla et sapien. Integer tortor tellus, aliquam faucibus, convallis id, congue eu, quam. Mauris ullamcorper felis vitae erat. Proin feugiat, augue non elementum posuere, metus purus iaculis lectus, et tristique ligula justo vitae magna. Aliquam convallis sollicitudin purus. Praesent aliquam, enim at fermentum mollis, ligula massa adipiscing nisl, ac euismod nibh nisl eu lectus. Fusce vulputate sem at sapien. Vivamus leo. Aliquam euismod libero eu enim. Nulla nec felis sed leo placerat imperdiet. Aenean suscipit nulla in justo. Suspendisse cursus rutrum augue. Nulla tincidunt tincidunt mi. Curabitur iaculis, lorem vel rhoncus faucibus, felis magna fermentum augue, et ultricies lacus lorem varius purus. Curabitur eu amet.
                            </p>
                    </div>
                    <div class="article_footer"><footer><strong>Ajouté le</strong> 21/12/11 <strong>par</strong> Anthony Molina-Diaz</footer></div>				

                    <h2>Brèves d'actualités</h2>
                    <ul>
                            <li>12/12/11 - <a href="">Le module de commentaire est opérationnel</a></li>
                            <li>07/12/11 - <a href="">Téléchargez des documents gratuitement !</a></li>
                            <li>01/12/11 - <a href="">C'est la rentrée avec studentmind</a></li>
                    </ul>
            </div>
        </div>

        <footer id="body">
            Copyright &copy; 2011 studentmind.
        </footer>
    </body>
</html>