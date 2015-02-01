<%@page import="Model.Entreprise"%>
<link rel="stylesheet" href="css/bootstrap.min.css">
<style>
    body{
        margin: 0px;
        padding: 0px;
        background: #F0F0F0;
    }
    .navbar{
        background : #2386C8;
        height: 60px;
    }
    #ulb li a{
        color: #FFF;
        font-family:sans-serif;
    }
    #ulb li a:hover{
        color: #FFF;
        font-family:sans-serif;
        background: #2cabe3;
        height: 58px;
    }
    #compte li{
        height: 36px;
        border: 1px #1c6ca1 solid;
        margin-top: 12px;
        background: #1e75ae;
        color: #FFF;
    }
    #compte li a{
        color: #FFF;
        margin-top : -7px;
    }
    #compte li:hover{
        background: #2386c8;
    }
</style>

<nav class="navbar navbar-default navbar-static-top" role="navigation">
    <div class="container-fluid" style="max-width: 90%" >
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav" id="ulb">
                <% Entreprise me = (Entreprise) session.getAttribute("entreprise"); %>
                

                <li><a href="index.action"><i class="glyphicon glyphicon-home"></i> Accueil</a></li>
                <li><a href="projets.action"><i class="glyphicon glyphicon-th-large"></i> Les projets</a></li>
                    <% if (me != null) {  %>
                <li><a href="demandes_validation.action"><i class="glyphicon glyphicon-th"></i> Demandes de prestation</a></li>
                <li><a href="ajouter_projet.action"><i class="glyphicon glyphicon-file"></i> Publier projet</a></li>
                    <% } %>

            </ul>
            <ul class="nav navbar-nav navbar-right" id="compte">
                <% if (me == null) {  %> 
                <li><a href="login.action"><i class="glyphicon glyphicon-log-in"></i> Login</a></li>
                <li style="background: #314459;" ><a href="page_inscription.action"><i class="glyphicon glyphicon-user"></i> Inscription</a></li>
                    <% } else {%>
                <li ><a href="notifications.action"  title="Notifications"> <span class="badge" style="background: #F00; color: #FFF"><%= me.nbrNotifications()%></span></a></li>
                <li ><a href="messages.action?box=all"  title="Mes messages"><i class="glyphicon glyphicon-envelope"></i><% if (me.nbrMessageNonLu() > 0) {%> <span class="badge" style="background: #FFF; color: #000"><%= me.nbrMessageNonLu()%></span><% }%></a></li>
                <li ><a href="modifier_profil.action"  title="Modifier mon profil"><i class="glyphicon glyphicon-cog"></i></a></li>
                <li ><a href="logout.action"><i class="glyphicon glyphicon-off" title="Deconnexion"></i></a></li>

                <% }%>

            </ul>
        </div>
    </div>
</nav>
<script src="js/bootstrap.min.js"></script>

