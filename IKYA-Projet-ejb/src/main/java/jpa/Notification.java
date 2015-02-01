/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "notification")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Notification.findAll", query = "SELECT n FROM Notification n"),
    @NamedQuery(name = "Notification.findByIdReception", query = "SELECT n FROM Notification n WHERE n.notificationPK.idReception = :idReception"),
    @NamedQuery(name = "Notification.findByIdEmetteur", query = "SELECT n FROM Notification n WHERE n.notificationPK.idEmetteur = :idEmetteur"),
    @NamedQuery(name = "Notification.findByType", query = "SELECT n FROM Notification n WHERE n.type = :type"),
    @NamedQuery(name = "Notification.findByDateNotification", query = "SELECT n FROM Notification n WHERE n.dateNotification = :dateNotification"),
    @NamedQuery(name = "Notification.findByMsgLu", query = "SELECT n FROM Notification n WHERE n.msgLu = :msgLu")})
public class Notification implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotificationPK notificationPK;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_notification")
    @Temporal(TemporalType.DATE)
    private Date dateNotification;
    @Basic(optional = false)
    @NotNull
    @Column(name = "msg_lu")
    private boolean msgLu;
    @JoinColumn(name = "id_emetteur", referencedColumnName = "id_user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user;
    @JoinColumn(name = "id_reception", referencedColumnName = "id_user", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private User user1;

    public Notification() {
    }

    public Notification(NotificationPK notificationPK) {
        this.notificationPK = notificationPK;
    }

    public Notification(NotificationPK notificationPK, String type, Date dateNotification, boolean msgLu) {
        this.notificationPK = notificationPK;
        this.type = type;
        this.dateNotification = dateNotification;
        this.msgLu = msgLu;
    }

    public Notification(int idReception, int idEmetteur) {
        this.notificationPK = new NotificationPK(idReception, idEmetteur);
    }

    public NotificationPK getNotificationPK() {
        return notificationPK;
    }

    public void setNotificationPK(NotificationPK notificationPK) {
        this.notificationPK = notificationPK;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateNotification() {
        return dateNotification;
    }

    public void setDateNotification(Date dateNotification) {
        this.dateNotification = dateNotification;
    }

    public boolean getMsgLu() {
        return msgLu;
    }

    public void setMsgLu(boolean msgLu) {
        this.msgLu = msgLu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationPK != null ? notificationPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Notification)) {
            return false;
        }
        Notification other = (Notification) object;
        if ((this.notificationPK == null && other.notificationPK != null) || (this.notificationPK != null && !this.notificationPK.equals(other.notificationPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.Notification[ notificationPK=" + notificationPK + " ]";
    }
    
}
