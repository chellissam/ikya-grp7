<%@page import="jpa.User"%>
<%@page import="java.util.List"%>
<%@page import="ejb.UserFacade"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    session = pageContext.getSession();
    List<User> userNonValides = (List) session.getAttribute("entreprisesNonValides");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="adminheader.jsp" %>
        <div class="lista">
            <table class="table table-hover">

                <tr>
                    <td>
                        Raison sociale
                    </td>
                    <td>
                        Ville
                    </td>
                    <td>
                        Telephone
                    </td>
                    <td>
                        Email
                    </td>
                    <td>
                        Validation
                    </td>

                    <% for (User element : userNonValides) {%>
                </tr>
                <tr>
                    <td>
                        <%= element.getEmail()%>
                    </td>
                    <td>
                        <%= element.getUsername()%>
                    </td>
                    <td>
                        <%= element.getNiveau()%>
                    </td>

                    <td>
                        <a  href="admin_validerInsDefinitive.action?valider=<%= element.getIdUser()%>"><h4><i style="color:#1cad00; float: left" class="glyphicon glyphicon-plus-sign"></i></h4></a>
                        <a  href="admin_validerInsDefinitive.action?refuser=<%= element.getIdUser()%>"><h4><i style="color:#ff0000; float: left; margin-left: 6px" class="glyphicon glyphicon-remove-sign"></i></h4></a>
                    </td>
                </tr>
                <% }%>
            </table>
        </div>
    </body>
</html>
