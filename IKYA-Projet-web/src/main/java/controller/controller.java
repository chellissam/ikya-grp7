package controller;

import dao.*;
import ejb.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.ejb.EJB;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import jpa.Notification;
import jpa.User;

/**
 *
 * @author Lemhamid
 */
public class controller extends HttpServlet {

    @EJB
    
    private ContactFacadeLocal ContactFacade;

    @EJB
    private DefisFacadeLocal DefisFacade;

    @EJB
    private MessagerieFacadeLocal MessagerieFacade;

    @EJB
    private NotificationFacadeLocal notificationFacade;

    @EJB
    private UserFacadeLocal UserFacade;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String uri = request.getRequestURI();
        int lastIndex = uri.lastIndexOf("/");
        String action = uri.substring(lastIndex + 1);
        String dispatchUrl = null;
        HttpSession session = request.getSession();

        User  users = new User();
      //  Entreprise entreprise = new Entreprise();

        User me = (User) session.getAttribute("users");

        if (me != null) {
            me = UserFacade.find(me.getIdUser());
            session.setAttribute("users", me);
        }

        Notification notif = new Notification();

        switch (action) {

            // 
            case "index.action":
                dispatchUrl = "/index.jsp";

                break;

            // Login
            case "login.action":
                dispatchUrl = "/authentification.jsp";

                break;

            // admin
            case "admin.action":
                dispatchUrl = "/login_admin.jsp";

                break;

            case "logadmin.action":
                if (request.getParameter("nom").equals("admin") && request.getParameter("pass").equals("123")) {
                    session.setAttribute("admin", "ok");
                    response.sendRedirect("admin_listeProjetAvalider.action");
                } else {
                    response.sendRedirect("admin.action?error=ok");
                }

                break;

            case "logoutadmin.action":
                session.setAttribute("admin", null);
                response.sendRedirect("admin.action");
                break;

            // Logout
            case "logout.action":
                session.setAttribute("entreprise", null);
                response.sendRedirect("index.action");

                break;
/*
            // authentification traitement 
            case "authentification_entreprise.action":
                String username = request.getParameter("username");
                String mdp = request.getParameter("password");

                User e = DaoJpa.authentified(username, mdp);

                if (e != null && e.getVerification_key().equals("1")) {
                    session.setAttribute("entreprise", e);
                    response.sendRedirect("projets.action");
                } else {
                    response.sendRedirect("login.action?error=ok");
                }

                break;

            // modifier profil
            case "modifier_profil.action":
                dispatchUrl = "/modifier_profil.jsp";

                break;

            //  profil
            case "profile.action":
                Entreprise he = entrepriseFacade.find(Long.parseLong(request.getParameter("id")));
                request.setAttribute("he", he);
                dispatchUrl = "/profile.jsp";

                break;

            // exec modifier profil
            case "execmodifier_profil.action":
                me.setMot_de_pass(request.getParameter("pass"));
                me.setRaison_sociale(request.getParameter("rs"));
                me.setVille(request.getParameter("ville"));
                me.setTelephone(Integer.parseInt(request.getParameter("phone")));

                session.setAttribute("entreprise", me);
                entrepriseFacade.edit(me);

                response.sendRedirect("modifier_profil.action");

                break;

            // Afficher la liste des projets
            case "projets.action":
                List<Projet> projets = projetFacade.findAll();
                if (projets.size() > 0) {
                    request.setAttribute("projets", projets);
                    dispatchUrl = "/liste_projets.jsp";
                } else {

                }

                break;

            // les notifications
            case "notifications.action":
                if (me != null) {
                    for (Notification vuno : me.getNotifications()) {
                        vuno.setVue(1);
                        notificationFacade.edit(vuno);
                    }
                    dispatchUrl = "/notifications.jsp";
                } else {
                    response.sendRedirect("login.action");
                }
                break;

            // Afficher details d'un projet
            case "details_projet.action":
                Projet projetdetails = projetFacade.find(Long.parseLong(request.getParameter("id")));
                if (projetdetails != null) {
                    request.setAttribute("projet", projetdetails);
                    for (Devis d : projetdetails.getDevis()) {
                        System.out.println(d.getCommentaire());
                    }
                    dispatchUrl = "/details_projet.jsp";
                } else {
                    response.sendRedirect("login.action");
                }
                break;

            case "demande_annuler_projet.action":
                Projet projanull = projetFacade.find(Long.parseLong(request.getParameter("p")));
                Notification notifannul = new Notification();
                if (projanull.getCommenditaire().getId() - me.getId() == 0) {
                    projanull.setEtat(98);
                    notifannul.setEntreprise(projanull.getPrestataire());
                    notifannul.setMessage("Le commanditaire demande l'annulation du projet");
                } else {
                    projanull.setEtat(99);
                    notifannul.setEntreprise(projanull.getCommenditaire());
                    notifannul.setMessage("Le prestataire demande l'annulation du projet");
                }
                notifannul.setLien("listerLivrables.action?id=" + projanull.getId());
                notificationFacade.create(notifannul);
                projetFacade.edit(projanull);

                response.sendRedirect("listerLivrables.action?id=" + projanull.getId());

                break;

            case "vlider_annulation.action":
                Notification notifva = new Notification();
                Projet projva = projetFacade.find(Long.parseLong(request.getParameter("p")));
                int c = Integer.parseInt(request.getParameter("c"));

                if (c == 1) {
                    projva.setEtat(1);
                    notifva.setMessage("Votre demande d'annulation d'un projet est accéptée");
                } else if (c == 2) {
                    projva.setEtat(5);
                    notifva.setMessage("Votre demande d'annulation d'un projet est accéptée");
                } else {
                    projva.setEtat(3);
                    notifva.setMessage("Votre demande d'annulation d'un projet est refusée");
                }

                if (me.getId() - projva.getCommenditaire().getId() == 0) {
                    notifva.setEntreprise(projva.getPrestataire());
                } else {
                    notifva.setEntreprise(projva.getCommenditaire());
                }
                
                notifva.setLien("listerLivrables.action?id="+projva.getId());

                notificationFacade.create(notifva);
                projetFacade.edit(projva);

                response.sendRedirect("listerLivrables.action?id=" + projva.getId());

                break;

            case "admin_ajouter_categorie.action":
                dispatchUrl = "/ajouter_categorie.jsp";
                break;

            case "admin_modifier_categorie.action":
                request.setAttribute("categorie", categorieFacade.find(Long.parseLong(request.getParameter("id"))));
                dispatchUrl = "/modifier_categorie.jsp";
                break;

            case "admin_categories.action":
                request.setAttribute("categories", categorieFacade.findAll());
                dispatchUrl = "/lescategories.jsp";
                break;

            case "admin_add_categorie.action":
                Categorie cattoadd = new Categorie();
                String nomct = new String(request.getParameter("nom").getBytes(),Charset.forName("UTF-8"));
                cattoadd.setNom(nomct);
                categorieFacade.create(cattoadd);
                response.sendRedirect("admin_categories.action");
                break;

            case "admin_edit_categorie.action":
                Categorie cattoedit = new Categorie();
                cattoedit.setId(Long.parseLong(request.getParameter("id")));
                cattoedit.setNom(request.getParameter("nom"));
                categorieFacade.edit(cattoedit);
                response.sendRedirect("admin_categories.action");
                break;

            case "admin_delete_categorie.action":
                Categorie cattoedelt = categorieFacade.find(Long.parseLong(request.getParameter("id")));
                categorieFacade.remove(cattoedelt);

                response.sendRedirect("admin_categories.action");
                break;

            // Afficher formulaire pour ajouter projet
            case "ajouter_projet.action":
                if (me != null) {
                    List<Categorie> categoriesadd = categorieFacade.findAll();
                    request.setAttribute("categories", categoriesadd);
                    dispatchUrl = "/ajouter_projet.jsp";
                }

                break;

            // enregistrer le nouveau projet dans la base de données
            case "enregister_projet.action":
                Entreprise commenditaire = (Entreprise) session.getAttribute("entreprise");
                if (commenditaire != null && me != null) {
                    projet = null;
                    projet = new Projet();
                    projet.setCommenditaire(commenditaire);
                    projet.setEtat(0);//0
                    projet.setSujet(new String(request.getParameter("sujet").getBytes(),Charset.forName("UTF-8")));
                    projet.setDescription(new String(request.getParameter("description").getBytes(),Charset.forName("UTF-8")));
                    projet.setCatgorie(categorieFacade.find(Long.parseLong(request.getParameter("categorie"))));
                    projet.setDate_livraison(request.getParameter("date_livraison"));
                    projet.setDate_publication("30/12/2014");
                    projet.setPrestataire(null);

                    projetFacade.create(projet);

                } else {
                    // authentification obligatoire
                }
                response.sendRedirect("projets.action");
                break;

            // Afficher formulaire pour modifier un projet
            case "modifier_projet.action":
                projet = null;
                projet = projetFacade.find(Long.parseLong(request.getParameter("id")));
                if (projet != null && projet.getEtat() == 10 && (me.getId() - projet.getCommenditaire().getId() == 0)) { // && me == projet.getCommenditaire()
                    request.setAttribute("projet", projet);
                    List<Categorie> categoriesedit = categorieFacade.findAll();
                    request.setAttribute("categories", categoriesedit);
                    dispatchUrl = "/modifier_projet.jsp";
                } else {
                    // ###

                    System.out.print("### else");
                }
                break;

            // Effectuer la modification d'un projet
            case "execmodifier_projet.action":
                projet = null;
                Long id = Long.parseLong(request.getParameter("id"));
                projet = projetFacade.find(id);
                if (projet != null) { // && me == projet.getCommenditaire()
                    projet.setCatgorie(categorieFacade.find(Long.parseLong(request.getParameter("categorie"))));
                    projet.setSujet(request.getParameter("sujet"));
                    projet.setDescription(request.getParameter("description"));
                    projet.setDate_livraison(request.getParameter("date_livraison"));
                    projet.setEtat(0);
                    projetFacade.edit(projet);
                }
                response.sendRedirect("projets.action");
                break;

            // Effectuer la modification d'un projet
            case "ajouter_devis.action":
                projet = null;
                Long idp = Long.parseLong(request.getParameter("id"));
                projet = projetFacade.find(idp);
                if (projet != null) { // && me == projet.getCommenditaire()

                    Devis devis = new Devis();
                    devis.setCommentaire(request.getParameter("commentaire"));
                    devis.setPrix(Integer.parseInt(request.getParameter("prix")));
                    devis.setProjet(projet);
                    devis.setEntreprise(me);

                    Notification notifdevis = new Notification();
                    notifdevis.setEntreprise(projet.getCommenditaire());
                    notifdevis.setMessage("Un nouveau devis pour votre projet " + projet.getSujet());
                    notifdevis.setLien("details_projet.action?id=" + projet.getId());

                    projet.getDevis().add(devis);

                    devisFacade.create(devis);
                    notificationFacade.create(notifdevis);

                    me.getNotifications().add(notifdevis);

                    response.sendRedirect("details_projet.action?id=" + request.getParameter("id"));

                } else {
                    // 
                }
                break;

            // Choisir prestataire
            case "choisir_prestataire.action":

                Long idprj = Long.parseLong(request.getParameter("id"));
                Long idprs = Long.parseLong(request.getParameter("p"));

                Projet projetv = projetFacade.find(idprj);
                Entreprise choix = entrepriseFacade.find(idprs);

                if (projetv != null && choix != null) { // && me == projet.getCommenditaire()
                    projetv.setPrestataire(choix);
                    projetv.setEtat(2);
                    projetFacade.edit(projetv);

                    Notification notifpp = new Notification();
                    notifpp.setEntreprise(choix);
                    notifpp.setMessage("Vous ete choisis pour un projet: " + projetv.getSujet());
                    notifpp.setLien("demandes_validation.action");

                    notificationFacade.create(notifpp);
                } else {
                    // 
                }
                response.sendRedirect("projets.action");
                break;

            // Accepter prestation
            case "accepter_prstation.action":

                Long idpa = Long.parseLong(request.getParameter("id"));
                int cx = Integer.parseInt(request.getParameter("c"));

                projet = projetFacade.find(idpa);

                if (projet != null && projet.getEtat() == 2) { // && me == projet.getCommenditaire()
                    Notification notifoo = new Notification();
                    notifoo.setEntreprise(projet.getCommenditaire());
                    if (cx == 1) {
                        projet.setEtat(3);
                        notifoo.setMessage("Le prestataire a accepté la prestation: " + projet.getSujet());
                    } else {
                        projet.setEtat(1);
                        projet.setPrestataire(null);
                        notifoo.setMessage("Le prestataire a refusé la prestation: " + projet.getSujet());
                    }
                    notifoo.setLien("details_projet.action?id=" + projet.getId());
                    projetFacade.edit(projet);
                    notificationFacade.create(notifoo);
                } else {
                    // 
                }
                response.sendRedirect("demandes_validation.action");
                break;

            // Afficher demande de prestation
            case "demandes_validation.action":
                List<Projet> projetss = projetFacade.findAll();
                System.out.println(projetss);
                if (projetss.size() > 0) {
                    request.setAttribute("projets", projetss);
                    dispatchUrl = "/demandes_validation.jsp";
                } else {
                    // Aucun projet n'est trouvé
                }

                break;

            // boite de messagerie
            case "messages.action":
                if (request.getParameter("id") != null) {
                    Messagerie readmsg = messageFacade.find(Long.parseLong(request.getParameter("id")));
                    if (readmsg == null) {
                        break;
                    }
                    readmsg.setVue(1);
                    if (me.getId() - readmsg.getRecepteur().getId() == 0) {
                        messageFacade.edit(readmsg);

                    }
                    request.setAttribute("read", readmsg);
                }
                dispatchUrl = "/messages.jsp";

                break;

            // envoyer nouveau message
            case "nouveau_message.action":
                Entreprise tou = entrepriseFacade.find(Long.parseLong(request.getParameter("to")));
                request.setAttribute("to", tou);
                dispatchUrl = "/nouveau_message.jsp";

                break;

            // envoyer un message
            case "envoyer_message.action":

                Messagerie messagerie = new Messagerie();

                messagerie.setVue(0);
                messagerie.setMessage(request.getParameter("message"));
                messagerie.setSujet(request.getParameter("sujet"));

                Entreprise to = entrepriseFacade.find(Long.parseLong(request.getParameter("to")));
                if (me != null && to != null) {

                    messagerie.setEmeteur(me);
                    messagerie.setRecepteur(to);
                    messageFacade.create(messagerie);
                } else {
                    // Aucun projet n'est trouvé
                }

                response.sendRedirect("messages.action?box=sent");

                break;

            //#### Ali
            case "inscription_entreprise.action":
                String raisonSociale2 = request.getParameter("inscriptionRaison");
                String email2 = request.getParameter("inscriptionEmail");
                String mdp2 = request.getParameter("inscriptionPwd");
                String ville2 = request.getParameter("inscriptionVille");
                String telephone2 = request.getParameter("inscriptionTelephone");
                if (entrepriseManagerBean.exists(email2) == false) {
                    byte[] bytesEncoded;
                    bytesEncoded = Base64.encodeBase64((email2 + "/" + mdp2 + "/").getBytes());
                    String verification_key = new String(bytesEncoded);
                    entreprise = new Entreprise();
                    entreprise.setVerification_key(verification_key);
                    entreprise.setRaison_sociale(raisonSociale2);
                    entreprise.setEmail(email2);
                    entreprise.setMot_de_pass(mdp2);
                    entreprise.setTelephone(Integer.parseInt(telephone2));
                    entreprise.setVille(ville2);
                    System.out.println("ecncoded value is " + new String(bytesEncoded));
                    byte[] valueDecoded = Base64.decodeBase64(bytesEncoded);
                    System.out.println("Decoded value is " + new String(valueDecoded));
                    // verification_key=request.getRequestURL()+"_"+new String(bytesEncoded);
                    entrepriseManagerBean.store(entreprise);
                    envoyerMessage(entreprise, "http://localhost:8080/B2BA-war/confirming_entreprise.action", new String(bytesEncoded));
                    response.sendRedirect("index.action?ins=ok");

                } else {
                    response.setHeader("Refresh", "0; URL=page_inscription.action?reinscription=true");
                }

                break;
            case "confirming_entreprise.action":
                String key = request.getParameter("key");
                entreprise = entrepriseManagerBean.authVerified(key);
                if (entreprise != null) {
                    out.println("Votre demande d'inscription à été envoyée");
                    entreprise.setVerification_key(null);
                    entrepriseFacade.edit(entreprise);
                }

                response.sendRedirect("index.action?ad=ad");
                break;
            case "page_inscription.action":

                dispatchUrl = "/inscription.jsp";
                break;

            case "formenvoyerLivrable.action":

                dispatchUrl = "/envoyerLivrable.jsp?p=" + request.getParameter("p");
                break;

            case "envoyerLivrable.action":
                String dernierProjet = request.getParameter("check");
                projet = projetFacade.find(Long.parseLong(request.getParameter("p")));

                Notification notiliv = new Notification();
                notiliv.setMessage("Nouveau livrable reçu : " + projet.getSujet());
                notiliv.setLien("listerLivrables.action?id=" + projet.getId());
                notiliv.setEntreprise(projet.getCommenditaire());
                notificationFacade.create(notiliv);

                String fichierLivrable = "fichier";
                Livrable livrable;
                String valeurChamp = "";
                String CHEMIN = "chemin";
                int TAILLE_TAMPON = 10240; // 10 ko
                String nomFichier = "";
                CHEMIN = this.getServletConfig().getInitParameter(CHEMIN);
                Part part = request.getPart(fichierLivrable);
                for (String contentDisposition : part.getHeader("content-disposition").split(";")) {
                    if (contentDisposition.trim().startsWith("filename")) {
                        nomFichier = contentDisposition.substring(contentDisposition.indexOf('=') + 1);
                        nomFichier = nomFichier.substring(nomFichier.indexOf("\"") + 1, nomFichier.lastIndexOf("\""));
                    }
                }
                if (nomFichier != null && !nomFichier.isEmpty()) {
                    String nomChamp = part.getName();
                    request.setAttribute(nomChamp, nomFichier);
                }
                BufferedInputStream entree = null;
                BufferedOutputStream sortie = null;

                try {
                    entree = new BufferedInputStream(part.getInputStream(), TAILLE_TAMPON);
                    sortie = new BufferedOutputStream(new FileOutputStream(new File(CHEMIN + "_" + nomFichier)),
                            TAILLE_TAMPON);
                    byte[] tampon = new byte[TAILLE_TAMPON];
                    int longueur;
                    while ((longueur = entree.read(tampon)) > 0) {
                        sortie.write(tampon, 0, longueur);
                    }

                } finally {
                    try {
                        sortie.close();
                    } catch (IOException ignore) {
                        System.out.println(ignore.getMessage());
                    }
                    try {
                        entree.close();
                    } catch (IOException ignore) {
                        System.err.println(ignore.getMessage());
                    }
                    livrable = new Livrable();
                    livrable.setFile(nomFichier);

                    //  livrableManagerBean.store(livrable);
                    projet.getLivrables().put(nomFichier, livrable);

                    if (dernierProjet != null) {
                        livrable.setEtat(3);
                    } else {
                        livrable.setEtat(1);
                    }

                    projetFacade.edit(projet);
                }

                response.sendRedirect("listerLivrables.action?id=" + request.getParameter("p"));

                break;
            case "listerLivrables.action":
                projet = projetFacade.find(Long.parseLong(request.getParameter("id")));
                request.setAttribute("projet", projet);
                dispatchUrl = "/listeDeslivrables.jsp";
                break;

            case "validerLivrable.action":
                projet = projetFacade.find(Long.parseLong(request.getParameter("p")));
                Long id3 = Long.parseLong(request.getParameter("ki"));
                int what = Integer.parseInt(request.getParameter("ok"));
                livrable = livrableManagerBean.findById(id3);

                Notification notilival = new Notification();

                notilival.setLien("listerLivrables.action?id=" + projet.getId());
                notilival.setEntreprise(projet.getPrestataire());

                int redir = 0;

                if (what == 0) {
                    livrable.setEtat(5);
                    notilival.setMessage("Livrable refusé : " + projet.getSujet());
                } else {
                    if (livrable.getEtat() == 1) {
                        livrable.setEtat(2);
                    } else {
                        livrable.setEtat(4);
                        projet.setEtat(5);
                        redir = 1;
                    }
                    notilival.setMessage("Livrable accepté : " + projet.getSujet());
                }

                notificationFacade.create(notilival);

                livrableManagerBean.edit(livrable);
                projetFacade.edit(projet);

                if (redir == 0) {
                    response.sendRedirect("listerLivrables.action?id=" + request.getParameter("p"));
                } else {
                    response.sendRedirect("page_evaluation.action?id=" + projet.getPrestataire().getId() + "&p=" + projet.getId());
                }

                break;

            case "chargementFichier.action":
                nomFichier = request.getParameter("file");
                String CHEMINx = "chemin";
                CHEMINx = this.getServletConfig().getInitParameter(CHEMINx);
                InputStream is = new FileInputStream(CHEMINx + "_" + nomFichier);
                OutputStream os = response.getOutputStream();
                response.setHeader("Content-Disposition", "attachment;filename=" + nomFichier);
                int count;
                byte buf[] = new byte[4096];
                while ((count = is.read(buf)) > -1) {
                    os.write(buf, 0, count);
                }

                is.close();
                os.close();

                break;

            case "page_evaluation.action":
                request.setAttribute("id", request.getParameter("id"));
                Projet pevtion = (Projet) projetFacade.find(Long.parseLong(request.getParameter("p")));
                if (pevtion.getPrestataire().canEvaluer(pevtion)) {
                    dispatchUrl = "/evaluation.jsp";
                }
                break;
            case "evaluation.action":
                String[] tab = request.getParameterValues("element");
                Evaluation evaluation = new Evaluation();
                evaluation.setNote(tab.length);
                Entreprise ese = entrepriseManagerBean.findById(Long.parseLong(request.getParameter("id")));

                evaluation.setEntreprise(me);
                evaluation.setEntrepriseEvaluee(ese);

                evaluation.setProjet(projetFacade.find(Long.parseLong(request.getParameter("p"))));

                evaluationFacade.create(evaluation);

                response.sendRedirect("profile.action?id=" + ese.getId());
                break;

            case "admin_validerInscription.action":
                if (request.getParameter("value") == null) {
                    List<User> users = entrepriseManagerBean.findAll();
                    List<User> usersNonvalide = new ArrayList();
                    for (User element : users) {
                        if (element.getVerification_key() == null) {
                            entreprisesNonvalide.add(element);
                        }
                    }
                    session.setAttribute("entreprisesNonValides", entreprisesNonvalide);
                }
                dispatchUrl = "/ValiderInscription.jsp";

                break;
            case "admin_validerInsDefinitive.action":
                Long refuser = 0L;
                Long valider = 0L;
                if (request.getParameter("valider") != null) {
                    valider = Long.parseLong(request.getParameter("valider"));
                }

                if (request.getParameter("refuser") != null) {
                    refuser = Long.parseLong(request.getParameter("refuser"));
                }
                if (valider != 0) {
                    entreprise = entrepriseManagerBean.findById(valider);
                    entreprise.setVerification_key("1");
                    entrepriseManagerBean.edit(entreprise);
                }
                if (refuser != 0) {
                    entreprise = entrepriseManagerBean.findById(refuser);
                    entreprise.setVerification_key("2");
                    entrepriseManagerBean.edit(entreprise);
                }
                response.sendRedirect("admin_validerInscription.action");
                break;
            case "admin_listeProjetAvalider.action":
                if (request.getParameter("value") == null) {
                    List<Projet> aprojets = projetFacade.findAll();
                    List<Projet> listeProjetNvalides = new ArrayList();
                    for (Projet p : aprojets) {
                        if (p.getEtat() == 0) {
                            listeProjetNvalides.add(p);
                        }
                    }
                    session.setAttribute("projetsNonValides", listeProjetNvalides);
                    // response.setHeader("Refresh", "0; URL=/B2B-war/admin/listeDesProjetsNonValides.jsp");
                }
                dispatchUrl = "/listeDesProjetsNonValides.jsp";
                break;
            case "admin_validerNouveauProjet.action":
                valider = 0L;
                refuser = 0L;
                Notification notvain = new Notification();
                System.out.println("###REfuser avant : " + refuser);
                if (request.getParameter("projetV") != null) {
                    valider = Long.parseLong(request.getParameter("projetV"));
                }
                if (request.getParameter("projetR") != null) {
                    refuser = Long.parseLong(request.getParameter("projetR"));
                }

                if (valider != 0) {
                    projet = projetFacade.find(valider);
                    projet.setEtat(1);
                    projetFacade.edit(projet);
                    notvain.setMessage("Projet accepté par l'administrateur");
                    notvain.setLien("details_projet.action?id=" + projet.getId());
                    notvain.setEntreprise(projet.getCommenditaire());
                }
                if (refuser != 0) {
                    projet = projetFacade.find(refuser);
                    projet.setEtat(10);
                    projetFacade.edit(projet);
                    notvain.setMessage("Projet refusé : modifiez le");
                    notvain.setLien("modifier_projet.action?id=" + projet.getId());
                    notvain.setEntreprise(projet.getCommenditaire());
                }
                notificationFacade.create(notvain);
                // response.sendRedirect("/admin_listeProjetAvalider.action?value=ok");
                response.sendRedirect("admin_listeProjetAvalider.action");
                break;
*/
        }

        if (dispatchUrl != null) {
            RequestDispatcher rd = request.getRequestDispatcher(dispatchUrl);
            rd.forward(request, response);
        }

        //commenditaire.setRaison_sociale("Google");
        //entrepriseFacade.create(commenditaire);
    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void envoyerMessage(User users, String link, String verification) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session2 = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("icdabn1@gmail.com", "Micda2014");

            }
        });
        try {

            Message message = new MimeMessage(session2);
            message.setFrom(new InternetAddress("icdabn1@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(users.getEmail()));

            message.setSubject("Merci de confirmer votre inscription");
            String messageEmail = ""
                    + "<html><head>"
                    + "<style>"
                    + ".s1{"
                    + "padding:10px; border:1px solid #008000; background-color:#EAF7D9;"
                    + "-moz-border-radius: 8px; -webkit-border-radius: 8px; border-radius:8px;"
                    + "}"
                    + "</style>"
                    + "</head>"
                    + "<body>"
                    + "<br/><p class=\"s1\">Bonjour " + users.getUsername() + " <b><br>"
                    + "veuillez confirmez votre inscription en visitant ce lien : </b><br/>"
                    + "<a href='" + link + "?key=" + verification + "'>" + link + "/" + verification + "</a>"
                    + "</body>"
                    + "</html>";
            message.setContent(messageEmail, "text/html; charset=ISO-8859-1");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            {

            }
        }

    }
}

