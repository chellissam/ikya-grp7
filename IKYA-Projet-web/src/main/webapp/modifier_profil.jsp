<%-- 
    Document   : modifier_profil
    Created on : 15 déc. 2014, 07:47:46
    Author     : Lemhamid
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .modprofil{
                width: 540px;
                border: 1px solid #E0E0E0; margin: 20px;
                background: #FFF;
                padding: 30px;
                text-align: left;
                margin-right: auto;
                margin-left: auto;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="modprofil">
            <h3>
                <i class="glyphicon glyphicon-lock"></i> Modifier mon profil
            </h3>
            <form method="POST" action="execmodifier_profil.action">
                <table class="table">
                    <tr>
                        <td>Raison sociale : </td>
                        <td><input type="text" class="form-control" name="rs" value="<%= me.getRaison_sociale()%>" /></td>
                    </tr>
                    <tr>
                        <td>Mot de passe : </td>
                        <td><input type="password" class="form-control" name="pass" value="<%= me.getMot_de_pass()%>" /></td>
                    </tr>
                    <tr>
                        <td>Ville : </td>
                        <td><input type="text" class="form-control" name="ville" value="<%= me.getVille()%>" /></td>
                    </tr>
                    <tr>
                        <td>Telephone : </td>
                        <td><input type="text" class="form-control" name="phone" value="<%= me.getTelephone()%>" /></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" class="btn btn-primary btn-sm" value="Modifier" /></td>
                    </tr>
                </table>
            </form>
        </div>
    </body>
</html>
