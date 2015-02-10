/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.middleware.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "messagerie")
@NamedQueries({
    @NamedQuery(name = "Messagerie.findAll", query = "SELECT m FROM Messagerie m"),
    @NamedQuery(name = "Messagerie.findByIdMsg", query = "SELECT m FROM Messagerie m WHERE m.idMsg = :idMsg"),
    @NamedQuery(name = "Messagerie.findByIdUser", query = "SELECT m FROM Messagerie m WHERE m.idUser2 = :idUser"),
    @NamedQuery(name = "Messagerie.findByDateMsg", query = "SELECT m FROM Messagerie m WHERE m.dateMsg = :dateMsg"),
    @NamedQuery(name = "Messagerie.findByTypeMsg", query = "SELECT m FROM Messagerie m WHERE m.typeMsg = :typeMsg"),
    @NamedQuery(name = "Messagerie.findByMsgLu", query = "SELECT m FROM Messagerie m WHERE m.msgLu = :msgLu")})
public class Messagerie implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_msg")
    private Integer idMsg;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "contenu1")
    private String contenu1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_msg")
    @Temporal(TemporalType.DATE)
    private Date dateMsg;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 2)
    @Column(name = "type_msg")
    private String typeMsg;
    @Basic(optional = false)
    @NotNull
    @Column(name = "msg_lu")
    private boolean msgLu;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 32767)
    @Column(name = "contenu2")
    private String contenu2;
    @JoinColumn(name = "id_user1", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idUser1;
    @JoinColumn(name = "id_user2", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idUser2;

    public Messagerie() {
    }

    public Messagerie(Integer idMsg) {
        this.idMsg = idMsg;
    }

    public Messagerie(Integer idMsg, String contenu1, Date dateMsg, String typeMsg, boolean msgLu, String contenu2) {
        this.idMsg = idMsg;
        this.contenu1 = contenu1;
        this.dateMsg = dateMsg;
        this.typeMsg = typeMsg;
        this.msgLu = msgLu;
        this.contenu2 = contenu2;
    }

    public Integer getIdMsg() {
        return idMsg;
    }

    public void setIdMsg(Integer idMsg) {
        this.idMsg = idMsg;
    }

    public String getContenu1() {
        return contenu1;
    }

    public void setContenu1(String contenu1) {
        this.contenu1 = contenu1;
    }

    public Date getDateMsg() {
        return dateMsg;
    }

    public void setDateMsg(Date dateMsg) {
        this.dateMsg = dateMsg;
    }

    public String getTypeMsg() {
        return typeMsg;
    }

    public void setTypeMsg(String typeMsg) {
        this.typeMsg = typeMsg;
    }

    public boolean getMsgLu() {
        return msgLu;
    }

    public void setMsgLu(boolean msgLu) {
        this.msgLu = msgLu;
    }

    public String getContenu2() {
        return contenu2;
    }

    public void setContenu2(String contenu2) {
        this.contenu2 = contenu2;
    }

    public User getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(User idUser1) {
        this.idUser1 = idUser1;
    }

    public User getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(User idUser2) {
        this.idUser2 = idUser2;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMsg != null ? idMsg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Messagerie)) {
            return false;
        }
        Messagerie other = (Messagerie) object;
        if ((this.idMsg == null && other.idMsg != null) || (this.idMsg != null && !this.idMsg.equals(other.idMsg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.middleware.entities.Messagerie[ idMsg=" + idMsg + " ]";
    }

}
