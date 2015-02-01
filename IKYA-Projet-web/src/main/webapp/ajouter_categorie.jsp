
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="adminheader.jsp" %>
        <div class="lista" style="width: 450px">
            <h3>Ajouter une catégorie</h3>
            <hr/>
            <form action="admin_add_categorie.action" >
                Nom : <input type="text" name="nom" class="form-control" /><br/>
                <input type="submit" value="Ajouter" class="btn btn-primary" />
            </form>
        </div>
    </body>
</html>
