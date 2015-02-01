/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import jpa.Contact;
import jpa.User;

/**
 *
 * @author TOSHIBA
 */

public interface Idao {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

        // liste des user
 public List<User> getAllUsers();
 // liste des contacts
 public List<Contact> getAllContacts();
 
  // trouver un Contact identifié par son id
 public Contact getContactById(Long idUser1);
  // trouver un user identifié par son id
 public User getUserById(Long idUser); 

 public User authentified(String username, String mdp);

    public void store(User users);

    public boolean exists(String username);
    
}
