<%-- 
    Document   : ajouter_categorie
    Created on : 20 déc. 2014, 17:08:02
    Author     : Lemhamid
--%>

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
        <% Categorie cat = (Categorie) request.getAttribute("categorie");%>
        <div class="lista" style="width: 450px">
            <h3>Modifier une catégorie</h3>
            <hr/>
            <form action="admin_edit_categorie.action?id=<%= cat.getId()%>" method="POST" >
                Nom : <input type="text" name="nom" class="form-control" value="<%= cat.getNom()%>" /><br/>
                <input type="submit" value="Modifier" class="btn btn-primary" />
            </form>
        </div>
    </body>
</html>
