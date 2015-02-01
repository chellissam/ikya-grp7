/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author TOSHIBA
 */
@Embeddable
public class NotificationPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_reception")
    private int idReception;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id_emetteur")
    private int idEmetteur;

    public NotificationPK() {
    }

    public NotificationPK(int idReception, int idEmetteur) {
        this.idReception = idReception;
        this.idEmetteur = idEmetteur;
    }

    public int getIdReception() {
        return idReception;
    }

    public void setIdReception(int idReception) {
        this.idReception = idReception;
    }

    public int getIdEmetteur() {
        return idEmetteur;
    }

    public void setIdEmetteur(int idEmetteur) {
        this.idEmetteur = idEmetteur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idReception;
        hash += (int) idEmetteur;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotificationPK)) {
            return false;
        }
        NotificationPK other = (NotificationPK) object;
        if (this.idReception != other.idReception) {
            return false;
        }
        if (this.idEmetteur != other.idEmetteur) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.NotificationPK[ idReception=" + idReception + ", idEmetteur=" + idEmetteur + " ]";
    }
    
}
