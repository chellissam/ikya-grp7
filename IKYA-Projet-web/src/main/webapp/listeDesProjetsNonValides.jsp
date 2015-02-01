<%-- 
    Document   : listeDesProjetsNonValides
    Created on : Dec 15, 2014, 7:56:53 PM
    Author     : Essafi
--%>

<%@page import="java.util.List"%>
<%@page import="Model.Projet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    session = pageContext.getSession();
    List<Projet> lesProjetNonValides = (List) session.getAttribute("projetsNonValides");

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
                            Sujet
                        </td>
                        <td>
                            Description
                        </td>
                        <td>
                            Date de publication
                        </td>
                        <td>
                            Date de livraison
                        </td>
                        <td>
                            Valider
                        </td>

                        <% for (Projet p : lesProjetNonValides) {%>
                    </tr>
                    <tr>
                        <td>
                            <%=p.getSujet()%>
                        </td>
                        <td>
                            <%=p.getDescription()%>
                        </td>
                        <td>
                            <%= p.getDate_publication()%>
                        </td>
                        <td>
                            <%=  p.getDate_livraison()%>
                        </td>
                        <td>
                            <a  href="admin_validerNouveauProjet.action?projetV=<%= p.getId()%>"><h4><i style="color:#1cad00; float: left" class="glyphicon glyphicon-plus-sign"></i></h4></a>
                            <a  href="admin_validerNouveauProjet.action?projetR=<%= p.getId()%>"><h4><i style="color:#ff0000; float: left; margin-left: 6px" class="glyphicon glyphicon-remove-sign"></i></h4></a>
                        </td>
                    </tr>
                    <% }%>
                </table>

        </div>
    </body>
</html>
