<%-- 
    Document   : profile
    Created on : 19 déc. 2014, 09:46:54
    Author     : Lemhamid
--%>

<%@page import="Model.Evaluation"%>
<%@page import="Model.Entreprise"%>
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
        <% Entreprise he = (Entreprise) request.getAttribute("he");%>
        <div class="authentification" style="margin-left: auto; margin-right: auto">
            <h3>
                <i class="glyphicon glyphicon-bookmark"></i> <%= he.getRaison_sociale()%>
                <% int ii = 0;
                    for (ii = 0; ii < he.moyenneEvaluations(); ii++) { %>
                <img src="img/rating.png" />
                <%}%>
            </h3>
            <table class="table table-hover">
                <tr>
                    <td>Raison sociale : </td>
                    <td><%= he.getRaison_sociale()%></td>
                </tr>
                <tr>
                    <td>Ville : </td>
                    <td><%= he.getVille()%></td>
                </tr>
                <tr>
                    <td>Telephone : </td>
                    <td><%= he.getTelephone()%></td>
                </tr>
                <tr>
                    <td>Projets : </td>
                    <td><%= he.getProjets().size() %></td>
                </tr>
                <tr>
                    <td>Prestations : </td>
                    <td><%= he.getPrestations().size() %></td>
                </tr>
                <tr>
                    <td>Note : </td>
                    <td><%= he.moyenneEvaluations() %>/5</td>
                </tr>
            </table>
            <% if (me.getId() - he.getId() != 0) {%>
            <a class="btn btn-primary" style="margin-right: auto" href="nouveau_message.action?to=<%= he.getId()%>">Envoyer un message</a>
            <% } %>
            <hr>
            <% if (he.getEvaluations().size() > 0) { %>
            <h4>Evaluations des entreprises</h4>
            <table class="table table-hover">
                <% for (Evaluation ev : he.getEvaluations()) {%>
                <tr>
                    <td><%= ev.getEntreprise().getRaison_sociale()%></td>
                    <td>
                        <% int i = 0;
                            for (i = 0; i < ev.getNote(); i++) { %>
                        <img src="img/rating.png" />
                        <%} %>
                    </td>
                </tr>
                <% } %>
            </table>
            <% }%>
        </div>
    </body>
</html>
