/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.middleware.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "user")
@NamedQueries({
    @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
    @NamedQuery(name = "User.findByIdUser", query = "SELECT u FROM User u WHERE u.idUser = :idUser"),
    @NamedQuery(name = "User.findByUsernameAndPassword", query = "SELECT u FROM User u WHERE u.username = :username and u.password = :password"),
    @NamedQuery(name = "User.findByUsername", query = "SELECT u FROM User u WHERE u.username = :username"),
    @NamedQuery(name = "User.findByPassword", query = "SELECT u FROM User u WHERE u.password = :password"),
    @NamedQuery(name = "User.findByElo", query = "SELECT u FROM User u WHERE u.elo = :elo"),
    @NamedQuery(name = "User.findByEmail", query = "SELECT u FROM User u WHERE u.email = :email"),
    @NamedQuery(name = "User.findByDateAjout", query = "SELECT u FROM User u WHERE u.dateAjout = :dateAjout"),
    @NamedQuery(name = "User.findByNiveau", query = "SELECT u FROM User u WHERE u.niveau = :niveau")})
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_user")
    private Integer idUser;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "password")
    private String password;
    @Basic(optional = false)
    @NotNull
    @Column(name = "elo")
    private int elo;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_ajout")
    @Temporal(TemporalType.DATE)
    private Date dateAjout;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "niveau")
    private String niveau;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
    private List<Contact> contactList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user1")
    private List<Contact> contactList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser1")
    private List<Messagerie> messagerieList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser2")
    private List<Messagerie> messagerieList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser1")
    private List<Defis> defisList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idUser2")
    private List<Defis> defisList1;

    public User() {
    }

    public User(Integer idUser) {
        this.idUser = idUser;
    }

    public User(Integer idUser, String username, String password, int elo, String email, Date dateAjout, String niveau) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.elo = elo;
        this.email = email;
        this.dateAjout = dateAjout;
        this.niveau = niveau;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDateAjout() {
        return dateAjout;
    }

    public void setDateAjout(Date dateAjout) {
        this.dateAjout = dateAjout;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public List<Contact> getContactList1() {
        return contactList1;
    }

    public void setContactList1(List<Contact> contactList1) {
        this.contactList1 = contactList1;
    }

    public List<Messagerie> getMessagerieList() {
        return messagerieList;
    }

    public void setMessagerieList(List<Messagerie> messagerieList) {
        this.messagerieList = messagerieList;
    }

    public List<Messagerie> getMessagerieList1() {
        return messagerieList1;
    }

    public void setMessagerieList1(List<Messagerie> messagerieList1) {
        this.messagerieList1 = messagerieList1;
    }

    public List<Defis> getDefisList() {
        return defisList;
    }

    public void setDefisList(List<Defis> defisList) {
        this.defisList = defisList;
    }

    public List<Defis> getDefisList1() {
        return defisList1;
    }

    public void setDefisList1(List<Defis> defisList1) {
        this.defisList1 = defisList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idUser != null ? idUser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.idUser == null && other.idUser != null) || (this.idUser != null && !this.idUser.equals(other.idUser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.middleware.entities.User[ idUser=" + idUser + " ]";
    }

}
