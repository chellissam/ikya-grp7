<%-- 
    Document   : ajouter_categorie
    Created on : 20 déc. 2014, 17:08:02
    Author     : Lemhamid
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Categorie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="adminheader.jsp" %>
        <% List<Categorie> cats = (List) request.getAttribute("categories");%>
        <div class="lista">
            <a href="admin_ajouter_categorie.action" class="btn btn-success">Ajouter catégorie</a><br/><br/>
            <table class="table table-hover">
                <tr>
                    <td>Nom</td>
                    <td>Gerer</td>
                </tr>
                <% for(Categorie cat : cats){%>
                    <tr>
                    <td><%= cat.getNom() %></td>
                    <td>
                        <a  href="admin_modifier_categorie.action?id=<%= cat.getId()%>"><h4><i style="color:#1cad00; float: left" class="glyphicon glyphicon-plus-sign"></i></h4></a>
                        <a  href="admin_delete_categorie.action?id=<%= cat.getId()%>"><h4><i style="color:#ff0000; float: left; margin-left: 6px" class="glyphicon glyphicon-remove-sign"></i></h4></a>
                    </td>
                </tr>
                <%}%>
            </table>
        </div>
    </body>
</html>
