
<%@page import="ejb.UserFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">

        <style>
            @import url(http://fonts.googleapis.com/css?family=Mr+Dafoe);
            body{
                background:#2c2c2c url(img/bg.jpg) no-repeat center center fixed;
                -webkit-background-size:cover;
                -moz-background-size:cover;
                -o-background-size:cover;
                background-size:cover;
                min-height:100%;
                width:99%;


            }
            #logo{
                color: #FFF;
                width: 99%;
                text-align: center;
                margin-top: 100px;
            }
            .txt{
                font-family: 'Mr Dafoe', cursive;
                font-size: 70px;
            }
            #logo p{
                font-family: arial;
                font-size: 28px;
                margin-top: 24px;
            }
            .btnss{
                width: 99%;
                text-align: center;
                margin-top: 40px;
            }
        </style>
    </head>
    <body>
        <div id="logo">
            <% if (request.getParameter("ins")!=null) {%>
            <h3><div class="label label-danger">Vous allez reçevoir un email pour valider votre compte</div></h3>
            <% }%>
            <% if (request.getParameter("ad")!=null) {%>
            <h3><div class="label label-danger">Votre compte sera validé par l'administrateur</div></h3>
            <% }%>
            <div class="txt">Plateforme B2B</div>
            <p>Echange de préstation entre les entreprises</p>
            <div class="btnss">
                <a href="page_inscription.action" class="btn btn-success" style="width: 260px; height: 40px; font-size: 18px">
                    <i class="glyphicon glyphicon-user"></i> Nouveau compte
                </a>
                <a href="login.action" class="btn btn-default" style="width: 260px; height: 40px; font-size: 18px">
                    <i class="glyphicon glyphicon-log-in"></i> Authentification
                </a>
            </div>
        </div>
    </body>
</html>
