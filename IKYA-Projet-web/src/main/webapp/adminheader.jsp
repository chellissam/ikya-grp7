<link rel="stylesheet" href="css/bootstrap.min.css">
<style>
    body{
        margin: 0px;
        padding: 0px;
        background: #F0F0F0;
    }
    .navbar{
        background : #333;
        height: 60px;
    }
    #ulb li a{
        color: #FFF;
        font-family:sans-serif;
    }
    #ulb li a:hover{
        color: #FFF;
        font-family:sans-serif;
        background: #444;
        height: 58px;
    }
    #compte li{
        height: 36px;
        border: 1px #555 solid;
        margin-top: 12px;
        background: #444;
        color: #FFF;
    }
    #compte li a{
        color: #FFF;
        margin-top : -7px;
    }
    #compte li:hover{
        background: #666;
    }
    .lista{
        width: 700px;
        border: 1px solid #E0E0E0; margin: 20px;
        background: #FFF;
        padding: 30px;
        text-align: left;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<% if(session.getAttribute("admin")==null) {
    throw new Exception("Vous n'avez pas le droit d'acc?der ? cette page");
} %>
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

                <li><a href="admin_categories.action"><i class="glyphicon glyphicon-chevron-up"></i> Gestion des cat?gories</a></li>
                <li><a href="admin_validerInscription.action"><i class="glyphicon glyphicon-user"></i> Gestion de utilisateurs</a></li>
                <li><a href="admin_listeProjetAvalider.action"><i class="glyphicon glyphicon-th-large"></i> Gestion des projets</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right" id="compte">
                <li ><a href="logoutadmin.action"><i class="glyphicon glyphicon-off" title="Deconnexion"></i></a></li>
            </ul>
        </div>
    </div>
</nav>
<script src="js/bootstrap.min.js"></script>

