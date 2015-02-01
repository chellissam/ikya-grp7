<%-- 
    Document   : login_admin
    Created on : 20 déc. 2014, 11:45:38
    Author     : Lemhamid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <style>
            .authentification{
                width: 540px;
                border: 1px solid #E0E0E0; margin: 20px;
                background: #FFF;
                padding: 30px;
                text-align: left;
                margin-top: 40px;
            }
            body{
                background: #EEE;
            }
        </style>
    </head>
    <body>
        <center>
        <div class="authentification">
            <h3>
                <i class="glyphicon glyphicon-lock"></i> Authentification Admin
            </h3>
            <hr>
            <% if (request.getParameter("error") != null) { %>
            <h5><span class="label label-danger" style="width: 90%">Nom ou mot de passe incorrect</span></h5>
            <%}%>
            <form role="form" action="logadmin.action" method="post" >
                <div class="form-group">
                    <label for="nom">Nom : </label>
                    <input name="nom" type="text" class="form-control" id="email" placeholder="Entrer nom" required="">
                </div>
                <div class="form-group">
                    <label for="pass">Mot de passe :</label>
                    <input name="pass" type="password" class="form-control" id="pass" placeholder="Entrer mot de passe" required="">
                </div>
                <input type="submit" value="Connexion" class="btn btn-primary btn-sm" style="width: 30%"/>
                <a href="page_inscription.action" class="btn btn-success btn-sm"  style="width: 30%">Inscription</a>
            </form>
            
        </div></center>
    </body>
</html>
