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
import sessions.MessagerieFacade;

@ManagedBean(name="messageController")
@SessionScoped
public class MessageController implements Serializable{
    
    @EJB
    MessagerieFacade messagerieFacade ;
    
    
    Contact contact;
    
    public MessageController(){
        
    }

    public MessagerieFacade getMessagerieFacade() {
        return messagerieFacade;
    }
    
    
    
    public Contact getSelected(){
        if (contact == null) {
            contact = new Contact();
        }
        return contact;
    }

}
