<%-- 
    Document   : details_projet
    Created on : 25 nov. 2014, 10:45:33
    Author     : Lemhamid
--%>

<%@page import="Model.Devis"%>
<%@page import="Model.Entreprise"%>
<%@page import="Model.Projet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="ISO-8859-1">

        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <% Projet projet = (Projet) request.getAttribute("projet");
            Boolean toadd = true;%>
        <div style="width:100%;">
            <div style="width: 90%; margin-left: auto; margin-right: auto;">
                <div class="row" style="margin: 20px 0px">
                    <div class="col-md-8">
                        <h2><%= projet.getSujet()%></h2>
                        <i class="glyphicon glyphicon-user"></i> Publié par : <%= projet.getCommenditaire().getRaison_sociale()%>
                        <i class="glyphicon glyphicon-time"></i> <%= projet.getDate_publication()%>
                    </div>
                    <div class="col-md-2" style="text-align: center; border: 1px solid #CCC; background: #FCFCFC; width: 100px">
                        <p>Devis</p>
                        <h3 style="color: #333"><%= projet.getDevis().size()%></h3>
                    </div>
                    <div class="col-md-2" style="text-align: center; border: 1px solid #CCC; background: #FCFCFC;margin-left: 20px; width: 160px">
                        <p>Moyenne</p>
                        <h3 style="color: #FF9800"><%= projet.getMoyenneDevis()%> DH</h3>
                    </div>
                </div>            
            </div>
        </div>
        <div style="width:100%;height: 60px; background:#F6F6F6;border: 1px solid #E0E0E0">
            <a href="#devis" class="btn btn-primary" style="margin-left: 75%; margin-top: 10px"> <small><i class="glyphicon glyphicon-plus-sign"></i></small>  Ajouter devis</a>
        </div>
        <div style="background: #F0F0F0; width: 100%">
            <div class="row" style="width: 90%; margin-left: auto; margin-right: auto;">
                <div class="col-md-8" >
                    <div style="background: #FFF;border: 1px solid #E0E0E0; margin: 20px; ">
                        <table class="table">
                            <tr>
                                <td>
                                    <h4><i class="glyphicon glyphicon-list-alt"></i> Détails du projet</h4>
                                </td>
                            </tr>
                            <tr>
                                <td><%= projet.getDescription()%></td>
                            </tr>
                        </table>
                    </div>
                    <div style="background: #FFF;border: 1px solid #E0E0E0; margin: 20px; ">
                        <table class="table">
                            <% for (Devis devis : projet.getDevis()) {%>
                            <% if (devis.getEntreprise().getId() - me.getId() == 0) {
                                    toadd = false;
                                }%>
                            <tr>
                                <td>
                                    <h4>
                                        <i class="glyphicon glyphicon-user"></i> <a href="profile.action?id=<%= devis.getEntreprise().getId()%>"><%= devis.getEntreprise().getRaison_sociale()%></a>
                                        <% int i = 0;
                                            for (i = 0; i < devis.getEntreprise().moyenneEvaluations(); i++) { %>
                                            <img src="img/rating.png" width="10" />
                                        <%}%>
                                    </h4>
                                    <h6>
                                        <i class="glyphicon glyphicon-map-marker"></i> <%= devis.getEntreprise().getVille()%>
                                        <i class="glyphicon glyphicon-phone"></i> <%= devis.getEntreprise().getTelephone()%>
                                    </h6>
                                    <p><%= devis.getCommentaire()%></p>
                                </td>
                                <td  width="5%">
                                </td>
                                <td width="15%">
                                    <div>
                                        <table class="table" style="width:180px;border: 1px solid #E0E0E0; margin: 20px; text-align: center">
                                            <tr>
                                                <td>
                                                    <h5><b><%= devis.getPrix()%> DH</b></h5>
                                                </td>
                                            </tr>
                                            <% if (me.getId() - projet.getCommenditaire().getId() == 0 && projet.getPrestataire() == null) {%>
                                            <tr>
                                                <td>
                                                    <a href="choisir_prestataire.action?id=<%= projet.getId()%>&p=<%= devis.getEntreprise().getId()%>"><i class="glyphicon glyphicon-ok-sign"></i></a>
                                                </td>
                                            </tr>
                                            <% } %>
                                        </table>
                                    </div>
                                </td>
                            </tr>
                            <%}%>
                        </table>
                    </div>
                    <div style="background: #FFF;border: 1px solid #E0E0E0; margin: 20px; ">
                        <a name="devis"></a>
                        <table class="table">
                            <tr>
                                <td>
                                    <h4><i class="glyphicon glyphicon-circle-arrow-right"></i> Ajouter un devis</h4>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <% if (me != null && projet.canadd(me) && toadd) {%>
                                    <form method="POST" action="ajouter_devis.action?id=<%= projet.getId()%>">
                                        <p style="color: #333"><b>Prix </b></p>
                                        <div class="input-group">
                                            <label class="sr-only" for="prix">Prix</label>
                                            <input type="number" class="form-control" name="prix"  id="prix" placeholder="Entrer prix" value="<%= projet.getMoyenneDevis()%>">
                                            <div class="input-group-addon">DH</div>
                                        </div><br>
                                        <p style="color: #333"><b>Détails</b></p>
                                        <p><textarea name="commentaire" class="form-control" rows="5" placeholder="Entrer plus de détails"></textarea></p>
                                        <p><input type="submit" value="Ajouter devis" class="btn btn-primary" /></p>
                                    </form>
                                    <%} else {%>
                                    <h4>
                                        <span class="label label-danger" style="width: 90%">
                                            Vous ne puvez pas ajouter un devis
                                        </span>
                                    </h4>
                                    <% }%>
                                </td>
                            </tr>
                        </table>

                    </div>
                </div>
                <div class="col-md-3" style="background: #FFF;border: 1px solid #E0E0E0; margin: 20px; ">
                    <h4><i class="glyphicon glyphicon-info-sign"></i> Carte du projet</h4>
                    <table class="table">
                        </tr>
                        <tr>
                            <td>Moyenne</td>
                            <td><%= projet.getMoyenneDevis()%> DH</td>
                        </tr>
                        <tr>
                            <td>Date publication</td>
                            <td><%= projet.getDate_publication()%></td>
                        </tr>
                        <tr>
                            <td>Date livraison</td>
                            <td><%= projet.getDate_publication()%></td>
                        </tr>
                        <tr>
                            <td>Etat du projet</td>
                            <td>
                                <%if (projet.getEtat() == 1) {%>
                                <p class="btn btn-success btn-xs">Projet ouvert</p>
                                <%} else {%>
                                <p class="btn btn-danger btn-xs">Projet fermé</p>
                                <%}%>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <h4><a href="profile.action?id=<%= projet.getCommenditaire().getId()%>"><%= projet.getCommenditaire().getRaison_sociale()%></a></h4>
                                <h6>
                                    <i class="glyphicon glyphicon-map-marker"></i> <%= projet.getCommenditaire().getVille()%>
                                    <i class="glyphicon glyphicon-phone"></i> <%= projet.getCommenditaire().getTelephone()%>
                                </h6>
                            </td>
                        </tr>
                    </table>

                </div>
            </div>
        </div>
    </body>
</html>
