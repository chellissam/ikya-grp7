<%-- 
    Document   : listeDeslivrables
    Created on : Dec 6, 2014, 11:55:30 AM
    Author     : Essafi
--%>

<%@page import="Model.Projet"%>
<%@page import="java.util.Map"%>
<%@page import="Model.Livrable"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    Projet projet = (Projet) request.getAttribute("projet");
    Map<String, Livrable> lesLivrables = projet.getLivrables();
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des livrables</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="width: 90%; margin-left: auto; margin-right: auto; border:1px solid #DDD; background: #FFF">
            <table class="table table-hover">

                <tr>
                    <td>
                        Livrable
                    </td>
                    <td>
                        Etat
                    </td>
                    <td>
                        Validation
                    </td>

                    <% for (Livrable e : lesLivrables.values()) {%>
                </tr>
                <tr>
                    <td>
                        <a  href="chargementFichier.action?file=<%= e.getFile()%>"><%= e.getFile()%></a>
                    </td>
                    <td>
                        <% if (e.getEtat() == 1) {%><span class="label label-warning">En attente</span><% } %>
                        <% if (e.getEtat() == 2) {%><span class="label label-primary">Valide</span><% } %>
                        <% if (e.getEtat() == 3) {%><span class="label label-danger">En attente : Dernier livrable</span><% } %>
                        <% if (e.getEtat() == 4) {%><span class="label label-success">Valide : Dernier livrable</span><% }%>
                        <% if (e.getEtat() == 5) {%><span class="label label-info">Refusé</span><% }%>
                    </td>
                    <td>
                        <% if ((e.getEtat() == 1 || e.getEtat() == 3) && (me.getId() - projet.getCommenditaire().getId() == 0)) {%>
                        <a href="validerLivrable.action?ok=1&ki=<%= e.getId()%>&p=<%= projet.getId()%>"><h4><i style="color:#1cad00; float: left; margin-right: 4px" class="glyphicon glyphicon-plus-sign"></i></h4></a> 
                        <a href="validerLivrable.action?ok=0&ki=<%= e.getId()%>&p=<%= projet.getId()%>"><h4><i style="color:#ff0000; float: left" class="glyphicon glyphicon-remove-sign"></i></h4></a>
                        <% } else { %> - <% } %>
                    </td>
                </tr>

                <% }%>

                <tr>

                    <td>

                    </td>

                    <td></td>
                    <td>

                    </td>
                </tr>

            </table>
            <div style="text-align: right; margin: 12px">
                <% if (me.getId() - projet.getPrestataire().getId() == 0 && projet.getEtat() == 3) {%>
                <a href="formenvoyerLivrable.action?p=<%= projet.getId()%>" class="btn btn-success">Envoyer un livrable</a>
                <% } %>
                <% if (projet.getEtat() == 3) {%>
                <a href="demande_annuler_projet.action?p=<%= projet.getId()%>" class="btn btn-danger">Annuler le projet</a>
                <% }%>

                <% if (projet.getEtat() == 99 && projet.getCommenditaire().getId() - me.getId() == 0) {%>
                <a href="vlider_annulation.action?c=1&p=<%= projet.getId()%>" class="btn btn-sm btn-success">Accepter et réouvrir le projet</a>
                <a href="vlider_annulation.action?c=2&p=<%= projet.getId()%>" class="btn btn-sm btn-warning">Accepter et annuler le projet</a>
                <a href="vlider_annulation.action?c=3&p=<%= projet.getId()%>" class="btn btn-sm btn-danger">Refuser</a>
                <% }%>
                <% if (projet.getEtat() == 98 && projet.getPrestataire().getId() - me.getId() == 0) {%>
                <a href="vlider_annulation.action?c=2&p=<%= projet.getId()%>" class="btn btn-sm btn-success">Accepter</a>
                <a href="vlider_annulation.action?c=3&p=<%= projet.getId()%>" class="btn btn-sm btn-danger">Refuser</a>
                <% }%>
            </div>
        </div>
    </body>
</html>
