<%-- 
    Document   : nouveau_message
    Created on : 9 déc. 2014, 17:11:59
    Author     : Lemhamid
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
        <%@include  file="header.jsp" %>
        <% Entreprise to = (Entreprise) request.getAttribute("to");%>
        <div class="authentification" style="margin-left: auto; margin-right: auto">
            <h3>
                <i class="glyphicon glyphicon-lock"></i> Envoyer message à : <%= to.getRaison_sociale()%>
            </h3>
            <hr>
            <form method="POST" action="envoyer_message.action?to=<%= request.getParameter("to")%>">
                 <div class="form-group">
                    <label for="sujet">Email : </label>
                    <input name="sujet" type="text" class="form-control" id="sujet" placeholder="Entrer sujet" required="">
                </div>
                 <div class="form-group">
                    <label for="message">Message : </label>
                    <textarea name="message"  class="form-control" placeholder="Entrer message" id="message" required="" rows="4"></textarea>
                </div>
                 <input type="submit" class="btn btn-primary btn-sm" style="width: 30%" value="Envoyer message"/>
            </form>
        </div>
    </body>
</html>
