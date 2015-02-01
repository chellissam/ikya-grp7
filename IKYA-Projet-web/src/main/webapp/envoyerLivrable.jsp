<%-- 
    Document   : envoyerLivrable
    Created on : Dec 2, 2014, 5:11:29 PM
    Author     : Essafi
--%>

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
        <div class="authentification" style="margin-left: auto; margin-right: auto">
            <h3>
                <i class="glyphicon glyphicon-lock"></i> Envoyer un livrable
            </h3>
            <hr>
            <form  method="post" action="envoyerLivrable.action?p=<%= request.getParameter("p") %>" enctype="multipart/form-data">
                <div class="form-group">
                    <label for="fichier">Fichier : </label>
                    <input name="fichier" type="file" class="form-control" id="fichier"  required="" />
                </div>
                <div class="form-group">
                    <label for="check">Dernier livrable ? </label>
                    <input type="checkbox" name="check" class="form-control" id="check"   />
                </div>
                <input type="submit" value="envoyerLivrable" class="btn btn-success"/>

            </form>
        </div>
    </body>
</html>
