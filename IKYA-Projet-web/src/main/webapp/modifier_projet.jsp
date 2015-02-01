<%-- 
    Document   : modifier_projet.jsp
    Created on : 25 nov. 2014, 09:46:11
    Author     : Lemhamid
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Categorie"%>
<%@page import="java.util.Date"%>
<%@page import="Model.Projet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <% Projet projet = (Projet) request.getAttribute("projet"); %>
        <center>
        <div style="width:500px; margin: 20px; padding: 20px; border:1px solid #DDD; background: #FFF; text-align: left">
            <h3><i class="glyphicon glyphicon-file"></i> Modifier un projet </h3>
            <hr>
            <form method="POST" action="execmodifier_projet.action?id=<%= projet.getId()%>">
                Catégorie : <select name="categorie" class="form-control">
                    <% List<Categorie> categories = (List) request.getAttribute("categories");
                    for (Categorie cat : categories) {%>
                    <option <%if(cat.getId()==projet.getCatgorie().getId()){%> selected <%}%> value="<%= cat.getId()%>"><%= cat.getNom()%></option>
                    <%}%>
                </select><br/>
                Sujet : <input type="text" name="sujet" class="form-control" required=""  value="<%= projet.getSujet()%>"/><br>
                Description : <textarea name="description" class="form-control" rows="5" required="" ><%= projet.getDescription()%></textarea><br>
                Date Livraison : <input type="date" name="date_livraison" class="form-control"  required="" value="<%= projet.getDate_livraison()%>" /><br>
                <input type="submit" class="btn btn-primary btn-sm" value="Modifier le projet" style="width: 120px" />
                <a href="index.action" class="btn btn-default btn-sm">Annuler</a>
            </form>
        </div>
    </center>
    </body>
</html>
