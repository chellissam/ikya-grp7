<%-- 
    Document   : messages
    Created on : 9 déc. 2014, 17:24:22
    Author     : Lemhamid
--%>

<%@page import="Model.Messagerie"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div class="row" style="margin-left: auto; margin-right: auto; width: 80%;">
            <div class="col-md-4">
                <ul class="list-group">
                    <li class="list-group-item">
                        Boite de méssagerie
                    </li>
                    <li class="list-group-item
                        <% if (me.nbrMessageNonLu() > 0) {%>
                        active">
                        <span class="badge"><%= me.nbrMessageNonLu()%></span> 
                        <a style="color:#FFF"
                           <% } else { %>
                           "><a 
                                <% } %>
                                href="?box=nonread"> Messages non lus</a>
                    </li>
                    <li class="list-group-item">
                        <a href="?box=all">Messages reçus</a>
                    </li>
                    <li class="list-group-item">
                        <a href="?box=sent">Messages envoyés</a>
                    </li>
                </ul>
            </div>

            <div class="col-md-8">  
                <div class="list-group">
                    <% if (request.getParameter("box").equals("nonread") || request.getParameter("box").equals("all")) {
                            for (Messagerie message : me.getMessagesRecues()) {

                                if (request.getParameter("box").equals("nonread")) {
                                    if (message.getVue() != 0) {
                                        message = null;
                                    }
                                }
                                if (message != null) {%>

                    <a href="?box=read&id=<%= message.getId()%>" class="list-group-item">
                        <h4 class="list-group-item-heading"><i class="glyphicon glyphicon-arrow-right" style="font-size: 15px"></i> <%= message.getSujet()%></h4>
                        <p class="list-group-item-text">Envoyé par : <%= message.getEmeteur().getRaison_sociale()%></p>

                    </a>
                    <% }
                        }
                    } else if (request.getParameter("box").equals("sent")) {%>
                    <% for (Messagerie message : me.getMessagesEnvoyes()) {%>

                    <a  href ="?box=read&id=<%= message.getId()%>" class="list-group-item">
                        <h4 class="list-group-item-heading"><i class="glyphicon glyphicon-arrow-right" style="font-size: 15px"></i> <%= message.getSujet()%></h4>
                        <p class="list-group-item-text">Envoyé par : <%= message.getEmeteur().getRaison_sociale()%></p>

                    </a>


                    <% }
                    } else if (request.getParameter("box").equals("read")) { %>
                    <% Messagerie msg = (Messagerie) request.getAttribute("read");%>
                    <a  class="list-group-item">
                        <h4 class="list-group-item-heading"><i class="glyphicon glyphicon-arrow-right" style="font-size: 15px"></i> <%= msg.getSujet()%></h4>
                        <p class="list-group-item-text">Envoyé par : <%= msg.getEmeteur().getRaison_sociale()%><hr/><%= msg.getMessage()%></p>
                        <form method="POST" action="envoyer_message.action?to=<%= msg.getEmeteur().getId()%>">
                            <input name="sujet" type="hidden" value="Re: <%= msg.getSujet()%>">
                            <div class="form-group">
                                <label for="message">Repondre : </label>
                                <textarea name="message"  class="form-control" placeholder="Entrer message" id="message" required="" rows="4"></textarea>
                            </div>
                            <input type="submit" class="btn btn-primary btn-sm" style="width: 30%" value="Envoyer message"/>
                        </form>
                    </a>
                    <% }%>
                </div>
            </div>
        </div>



    </body>
</html>
