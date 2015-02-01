/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author TOSHIBA
 */
import javax.ejb.Stateless;
import javax.*;
import jpa.*;
import java.util.*;
import javax.ejb.*;
import javax.persistence.*;
import java.text.*;
//import javax.transaction.Transaction;
import midlle.exception.*;
@Stateless(mappedName = "Interface")
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DaoJpa implements IdaoLocal {

 @PersistenceContext
 private EntityManager em;
 
 
    /**
     *
     * @param username
     * @param mdp
     * @return
     */
    @Override
     public User authentified(String username, String mdp) {
        Query query = em.createQuery("from User e where e.username=?1 and e.password=?2 ");
        query.setParameter(1, username);
        query.setParameter(2, mdp);
        User e;
        try {
            e = (User) query.getSingleResult();
        } catch (NoResultException ex) {
            e = null;
        }
        return (e);
    }

 @Override
    public void store(User users) {
        em.persist(users);
    }

 @Override
    public boolean exists(String username) {
        Query query = em.createQuery("from User e where e.username=?1");
        query.setParameter(1, username);
        User e;
        try {
            e = (User) query.getSingleResult();
        } catch (NoResultException ex) {
            e = null;
            return false;
        }
        return true;

    }     
     
     
 // liste des user
 @Override
    public List<User> getAllUsers() {
        
         try {
                return em.createNamedQuery("User.findAll").getResultList();
                } catch (Throwable th) {
                throw new MiddleException(th, 1);
             }
        
    }
 // liste des contacts
 @Override
    public List<Contact> getAllContacts() {
        
          try {
                return em.createNamedQuery("Contact.findAll").getResultList();
                } catch (Throwable th) {
                throw new MiddleException(th, 2);
             }     
    }

// récupérer un contact donné
 @Override
    public Contact getContactById(Long idUser1) {
            try {
             return (Contact) em.find(Contact.class,  idUser1);
             } catch (Throwable th) {
             throw new MiddleException(th, 7);
             }
    }

// récupérer un user donné
 @Override
    public User getUserById(Long idUser) {

             try {
             return (User) em.find(User.class,  idUser);
             } catch (Throwable th) {
             throw new MiddleException(th, 2);
             }
    }
 

    
    
    
    
    
}