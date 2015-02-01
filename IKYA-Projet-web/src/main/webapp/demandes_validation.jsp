<%@page import="javax.enterprise.inject.Model"%>

<%@page import="ejb.UserFacade"%>

<%@page import="java.util.List"%>
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
        <div style="width: 90%; margin-left: auto; margin-right: auto; border:1px solid #DDD;background: #FFF">
            <table class="table table-hover">
                <thead>
                    <tr style="background: #F5F5F5; height: 60px; margin-top: 10px">
                        <th>Les projets</th>
                        <th width="8%" style="text-align: center">Accepter</th>
                        <th width="8%" style="text-align: center">Refuser</th>
                    </tr>
                </thead>
                <% int n = 0;%>
                <% List<Projet> projets = (List) request.getAttribute("projets"); %>
                <% for (Projet projet : projets) {
                        if (projet.getPrestataire() != null) {
                            if ( projet.getEtat() == 2 && projet.getPrestataire().getId() - me.getId()==0) {
                %>
                
                <tr style="height: 60px; text-align: center">
                    <td style="text-align: left">
                        <h4>
                            <a href="details_projet.action?id=<%= projet.getId()%>" ><%= projet.getSujet()%></a>
                        </h4></td>
                        <td><a href="accepter_prstation.action?id=<%= projet.getId()%>&c=1"><h4><i style="color:#1cad00" class="glyphicon glyphicon-plus-sign"></i></h4></a></td>
                        <td><a href="accepter_prstation.action?id=<%= projet.getId()%>&c=0"><h4><i style="color:#ff0000" class="glyphicon glyphicon-remove-sign"></i></h4></a></td>
                </tr>
                <% n++;
                            }
                        }
                    }
                %>
            </table>
        </div>
    </body>
</html>

