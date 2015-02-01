
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="jpa.Contact"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>

    </head>
    <body>
        <%@include file="header.jsp" %>
    <center>
        <div style="width:500px; margin: 20px; padding: 20px; border:1px solid #DDD; background: #FFF; text-align: left">
            <h3><i class="glyphicon glyphicon-file"></i> Publier un nouveau projet </h3>
            <hr>
            <form method="POST" action="enregister_projet.action">
                Catégorie : <select name="contact" class="form-control">
                    <% List<Contact> Contacts = (List) request.getAttribute("Contacts");
                    for (Contact cat : Contacts) {%>
                    <option value="<%= cat.getId()%>"><%= cat.getNom()%></option>
                    <%}%>
                </select><br/>
                Sujet : <input type="text" name="sujet" class="form-control" required="" /><br>
                Description : <textarea name="description" class="form-control" rows="5" required="" ></textarea><br>
                Date Livraison : <input type="date" name="date_livraison" class="form-control"  required="" /><br>
                <input type="submit" class="btn btn-primary btn-sm" value="Ajouter le projet" style="width: 120px" />
                <a href="index.action" class="btn btn-default btn-sm">Annuler</a>
            </form>
        </div>
    </center>
</body>
</html>
