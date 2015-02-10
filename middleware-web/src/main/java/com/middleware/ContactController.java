/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.middleware;

import com.middleware.model.Contact;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import sessions.ContactFacade;

@ManagedBean(name="contactController")
@SessionScoped
public class ContactController implements Serializable{
    @EJB
    private ContactFacade contactFacade;
    
    Contact contact;
    
    public ContactController(){
        
    }

    public ContactFacade getContactFacade() {
        return contactFacade;
    }
    
    
    
    public Contact getSelected(){
        if (contact == null) {
            contact = new Contact();
        }
        return contact;
    }

}
