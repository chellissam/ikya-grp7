<%-- 
    Document   : notifications
    Created on : 11 déc. 2014, 10:51:42
    Author     : Lemhamid
--%>

<%@page import="java.util.Collections"%>
<%@page import="Model.Notification"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <% Collections.reverse(me.getNotifications()); %>
        <div style="width: 90%; margin-left: auto; margin-right: auto; border:1px solid #DDD;background: #FFF">
            <table class="table table-hover">
                <% for (Notification notif : me.getNotifications()) {%>
                <tr><td>
                        <a href="<%= notif.getLien()%>"><%= notif.getMessage()%></a>
                    </td></tr>
                    <% } %>

            </table>
        </div>
    </body>
</html>
