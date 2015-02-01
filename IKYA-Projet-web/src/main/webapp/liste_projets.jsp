<%-- 
    Document   : liste_projets
    Created on : 25 nov. 2014, 10:32:54
    Author     : Lemhamid
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="Model.Projet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #uloo a{
                color: #FFF;
                font-size: 13px;
            }
            #uloo a:hover{
                background: #444;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <% boolean suivi = true; %>
        <% if (me != null) { %>
        <div style="background: #333; width: 100%; height: 50px; margin-top: -20px; margin-bottom: 16px">
            <ul class="nav navbar-nav" style="width: 90%; margin-left: 6%;" id="uloo">
                <li><a href="projets.action"><i class="glyphicon glyphicon-link"></i> Projets ouverts</a></li>
                <li><a href="projets.action?a=commandes"><i class="glyphicon glyphicon-link"></i> Mes projets</a></li>
                <li><a href="projets.action?a=prestations"><i class="glyphicon glyphicon-link"></i> Mes prestations</a></li>
            </ul>
        </div>
        <% } %>
        <div style="width: 90%; margin-left: auto; margin-right: auto; border:1px solid #DDD; background: #FFF">
            <table class="table table-hover">
                <thead>
                    <tr style="background: #F5F5F5; height: 60px; margin-top: 10px">
                        <th>Les projets</th>
                        <th width="8%">Devis</th>
                        <th width="8%">Moyenne</th>
                        <th width="12%">Publié le</th>
                        <th width="12%">Livraison</th>
                    </tr>
                </thead>
                <%
                    List<Projet> projets = new ArrayList<Projet>();

                    if (request.getParameter("a") == null) {
                        suivi = false;
                        List<Projet> projetsto = (List) request.getAttribute("projets");
                        for (Projet projet : projetsto) {
                            if (projet.getEtat() == 1) {
                                projets.add(projet);
                            }
                        }
                    } else {
                        if (request.getParameter("a").equals("commandes")) {
                            projets = me.getProjets();
                        } else if (request.getParameter("a").equals("prestations")) {
                            projets = me.getPrestations();
                        }
                    }
                %>
                <% for (Projet projet : projets) {%>
                <tr style="height: 60px;">
                    <td>
                        <h4>
                            <a href="details_projet.action?id=<%= projet.getId()%>" ><%= projet.getSujet()%></a>
                            <% if (suivi) {%>
                            <small><a href="listerLivrables.action?id=<%= projet.getId()%>" class="label label-info">
                                    <i class="glyphicon glyphicon-play-circle"></i> Suivi
                                </a></small>
                                <% }%>
                        </h4>
                    </td>
                    <td><%= projet.getDevis().size()%></td>
                    <td><%= projet.getMoyenneDevis()%> DH</td>
                    <td><%= projet.getDate_livraison()%></td>
                    <td><%= projet.getDate_publication()%></td>
                </tr>
                <% }%>
            </table>
        </div>
    </body>
</html>
