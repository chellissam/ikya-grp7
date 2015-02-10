package com.middleware;

import com.middleware.model.Contact;
import com.middleware.model.Defis;
import com.middleware.model.Messagerie;
import sessions.UserFacade;
import com.middleware.model.User;
import com.middleware.util.JsfUtil;
import com.middleware.util.PaginationHelper;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
//import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpSession;
import sessions.ContactFacade;
import sessions.DefisFacade;
import sessions.DefisFacadeLocal;
import sessions.MessagerieFacade;

@ManagedBean(name="userController")
@SessionScoped
public class UserController implements Serializable {
    @EJB
    private DefisFacade defisFacade;
    @EJB
    private ContactFacade contactFacade;
    @EJB
    private UserFacade userFacade;

    @EJB
    MessagerieFacade messagerieFacade ;
    
    private Map<User,Integer> scores;
    private Defis defi;
    private User current;
    private DataModel items = null;
    private DataModel messages = null;
    
    private PaginationHelper pagination;
    private PaginationHelper paginationMessages;
    private int selectedItemIndex;

    public UserController() {
    }

    public User getSelected() {
        if (current == null) {
            current = new User();
            selectedItemIndex = -1;
        }
        return current;
    }

    public Defis getDefi() {
        if(defi==null) defi=new Defis();
        return defi;
    }

    public DefisFacade getDefisFacade() {
        return defisFacade;
    }
        
    User user;
    public void add(){
        FacesContext fc = FacesContext.getCurrentInstance();
        String email=fc.getExternalContext().getRequestParameterMap().get("user");
        user=userFacade.findByEmail(email);
        if(user==null) {
            user=userFacade.findAll().get(0);            
        }
        
        Messagerie messagerie=new Messagerie();
        messagerie.setIdUser1(current);
        messagerie.setIdUser2(user);
        messagerie.setContenu1("Ajoute Moi SVP!");
        messagerie.setContenu2("Ok !");
        messagerie.setTypeMsg("0");
        messagerie.setDateMsg(Calendar.getInstance().getTime());
//ajout
        getMessagerieFacade().create(messagerie);
    }

    public ContactFacade getContactFacade() {
        return contactFacade;
    }
    
    private UserFacade getFacade() {
        return userFacade;
    }

    private MessagerieFacade getMessagerieFacade(){
        return messagerieFacade;
    }
    
    public Map<User,Integer> getScores(){
        List<Defis> defis=defisFacade.findAll();
        scores=new HashMap<>();
        for(Defis d:defis){
            if(scores.containsKey(d.getIdUser1())){
                scores.put(d.getIdUser1(), scores.get(d.getIdUser1())+d.getScore1());
            }else 
                scores.put(d.getIdUser1(), d.getScore1());
            
            if(scores.containsKey(d.getIdUser2())){
                scores.put(d.getIdUser2(), scores.get(d.getIdUser2())+d.getScore2());
            }else 
                scores.put(d.getIdUser2(), d.getScore2());
        }
        scores=sortByValues(scores);
        return scores;
    }
    
    private static HashMap sortByValues(Map map) { 
       List list = new LinkedList(map.entrySet());
       // Defined Custom Comparator here
       Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
               return ((Comparable) ((Map.Entry) (o1)).getValue())
                  .compareTo(((Map.Entry) (o2)).getValue());
            }
       });

       // Here I am copying the sorted list in HashMap
       // using LinkedHashMap to preserve the insertion order
       HashMap sortedHashMap = new LinkedHashMap();
       for (Iterator it = list.iterator(); it.hasNext();) {
              Map.Entry entry = (Map.Entry) it.next();
              sortedHashMap.put(entry.getKey(), entry.getValue());
       } 
       return sortedHashMap;
  }
    
    public String challenge(){
        FacesContext fc = FacesContext.getCurrentInstance();
        String email=fc.getExternalContext().getRequestParameterMap().get("email");
        User user=getFacade().findByEmail(email);
        if(user==null) {
            user=userFacade.findAll().get(0);            
        }
        defi.setIdUser1(current);
        defi.setIdUser2(user);
        defi.setDateDefi(Calendar.getInstance().getTime());
        defisFacade.create(defi);
        return "List";
    }
    
    public PaginationHelper getPagination() {
        
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findAll());//findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        return pagination;
    }

    public PaginationHelper getPaginationMessage() {
        
            paginationMessages = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getMessagerieFacade().findMessages(current).size();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getMessagerieFacade().findMessages(current));//findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        
        return paginationMessages;
    }
    
    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (User) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new User();
        selectedItemIndex = -1;
        return "/index";
    }

    public String create() {
        try { 
            current.setElo(100);
            current.setNiveau("Debutant");
            current.setDateAjout(Calendar.getInstance().getTime());
            System.out.println("user name: "+current.toString());
            getFacade().create(current);
            //JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserCreated"));
            return prepareCreate();
        } catch (Exception e) {
            //JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }
    
    public String login(){
        current=getFacade().find(current.getUsername(), current.getPassword());
        if(current == null)
        return "login";
        return "acceuil";
    }
    
    public String remove(){
        getFacade().remove(current);
        return "index";
    }

    public String prepareEdit() {
        current = (User) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserUpdated"));
            return "View";
        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (User) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }
    
    public String disconnect(){
        //FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
//        current=null; 
//        userFacade=null;
//        contactFacade=null;
      // Permet de rerouter l'utilisateur sur la page d'accueil
      
        return "/index.xhtml?faces-redirect=true";
    }
    
    private void performDestroy() {
        try {
            getFacade().remove(current);
//            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("UserDeleted"));
        } catch (Exception e) {
//            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }
    
    public void accept(){
        FacesContext fc = FacesContext.getCurrentInstance();
        String email=fc.getExternalContext().getRequestParameterMap().get("iduser");
        User user=getFacade().findByEmail(email);
        Contact contact=new Contact();
        contact.setUser(current);
        contact.setUser1(user);
        contact.setDateAjout(Calendar.getInstance().getTime());
        
        getContactFacade().create(contact);
        
        Messagerie messagerie = new Messagerie();
        messagerie.setIdUser1(current);
        messagerie.setIdUser2(user);
        messagerie.setContenu1("I accepted you :)");
        messagerie.setContenu2("");
        messagerie.setDateMsg(Calendar.getInstance().getTime());
        
        getMessagerieFacade().create(messagerie);
    }
    
    public boolean isFriend(){
        FacesContext fc = FacesContext.getCurrentInstance();
        String email=fc.getExternalContext().getRequestParameterMap().get("user");
        System.out.println("email 2 = "+email);
        User user=getFacade().findByEmail(email);
        List<Contact> contacts=contactFacade.findAll();
        for(Contact contact:contacts){
            if(contact.getUser().getIdUser()==current.getIdUser() && contact.getUser1().getIdUser()==user.getIdUser()
                    || contact.getUser().getIdUser()==user.getIdUser() && contact.getUser1().getIdUser()==current.getIdUser())
                return true;
                }
        return false;
    }
    
    public DataModel getMessages() {
        if (messages == null) {
            messages = getPaginationMessage().createPageDataModel();
        }
        return messages;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(userFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(userFacade.findAll(), true);
    }

    public User getUser(java.lang.Integer id) {
        return userFacade.find(id);
    }

    @FacesConverter(forClass = User.class)
    public static class UserControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserController controller = (UserController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userController");
            return controller.getUser(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof User) {
                User o = (User) object;
                return getStringKey(o.getIdUser());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + User.class.getName());
            }
        }

    }

}
