<%-- 
    Document   : inscription
    Created on : Dec 2, 2014, 11:27:10 AM
    Author     : Essafi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inscription</title>
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
        <div class="authentification" style="margin-left: auto; margin-right: auto">
            <h3>
                <i class="glyphicon glyphicon-lock"></i> Inscription
            </h3>
            <hr>
            <% if (request.getParameter("reinscription") != null) {%>
            <h4><span class="label label-danger" style="width: 90%">Cet Email existe déjà</span></h4>
            <%}%>
            <form action="inscription_entreprise.action" method="post">
                <div class="form-group">
                    <label for="rs">Raison sociale : </label>
                    <input name="inscriptionRaison" type="text" class="form-control" id="rs" placeholder="Entrer raison sociale" required="" />
                </div>
                <div class="form-group">
                    <label for="email">Email : </label>
                    <input name="inscriptionEmail" type="text" class="form-control" id="email" placeholder="Entrer email" required="" />
                </div>
                <div class="form-group">
                    <label for="pass">Mot de passe : </label>
                    <input name="inscriptionPwd" type="password" class="form-control" id="pass" placeholder="Entrer mot de passe" required="" />
                </div>
                <div class="form-group">
                    <label for="ville">Ville : </label>
                    <input name="inscriptionVille" type="text" class="form-control" id="ville" placeholder="Entrer ville" required="" />
                </div>
                <div class="form-group">
                    <label for="phone">Telephone : </label>
                    <input name="inscriptionTelephone" type="text" class="form-control" id="phone" placeholder="Entrer telephone" required="" />
                </div>
                <input type="submit" class="btn btn-primary btn-sm" value="Inscription" style="width: 30%"/>
                <a href="login.action" class="btn btn-success btn-sm"  style="width: 30%">Authentification</a>
            </form>
        </div>
    </body>
</html>
