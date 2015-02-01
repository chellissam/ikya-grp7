<%@page import="javax.enterprise.inject.Model"%>
<%@page import="javax.enterprise.inject.Model"%>
<%@page import="javax.ejb.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .authentification{
                width: 540px;
                border: 1px solid #E0E0E0; margin: 20px;
                background: #FFF;
                padding: 30px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
    <center>
        <div class="authentification">
            <h3>
                <i class="glyphicon glyphicon-lock"></i> Authentification des membres
            </h3>
            <hr>
            <% if (request.getParameter("error") != null) { %>
            <h5><span class="label label-danger" style="width: 90%">Email ou mot de passe incorrect</span></h5>
            <%}%>
            <form role="form" action="authentification_entreprise.action" method="post" >
                <div class="form-group">
                    <label for="email">Email : </label>
                    <input name="email" type="email" class="form-control" id="email" placeholder="Entrer email" required="">
                </div>
                <div class="form-group">
                    <label for="pass">Mot de passe :</label>
                    <input name="authentificationPwd" type="password" class="form-control" id="pass" placeholder="Entrer mot de passe" required="">
                </div>
                <input type="submit" value="Connexion" class="btn btn-primary btn-sm" style="width: 30%"/>
                <a href="page_inscription.action" class="btn btn-success btn-sm"  style="width: 30%">Inscription</a>
            </form>
            
        </div></center>


</body>
</html>
