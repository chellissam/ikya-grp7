<%-- 
    Document   : evaluation
    Created on : Dec 19, 2014, 3:22:58 PM
    Author     : Essafi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .authentification{
                width: 540px;
                border: 1px solid #E0E0E0; margin: 20px;
                background: #FFF;
                padding: 30px;
                text-align: left;
            }
        </style>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="authentification" style="margin-left: auto; margin-right: auto">
            <h4>Etes vous satisfait de la qualité de la prestation ?</h4><br/>
            <form action="evaluation.action?id=<%= request.getAttribute("id")%>&p=<%= request.getParameter("p") %>" method="post">
                <input type="checkbox" name="element" value="1"/>
                <input type="checkbox" name="element" value="1"/>
                <input type="checkbox" name="element" value="1"/>
                <input type="checkbox" name="element" value="1"/>
                <input type="checkbox" name="element" value="1"/>
                <img src="img/rating.png" height=16" style="margin-top: -6px" />
                <br/><br/>
                <input type="submit" value="Evaluder" class="btn btn-success" />
            </form>
        </div>
    </body>
</html>
