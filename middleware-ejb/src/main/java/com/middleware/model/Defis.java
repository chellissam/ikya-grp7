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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "defis")
@NamedQueries({
    @NamedQuery(name = "Defis.findAll", query = "SELECT d FROM Defis d"),
    @NamedQuery(name = "Defis.findByIdDefi", query = "SELECT d FROM Defis d WHERE d.idDefi = :idDefi"),
    @NamedQuery(name = "Defis.findByScore1", query = "SELECT d FROM Defis d WHERE d.score1 = :score1"),
    @NamedQuery(name = "Defis.findByScore2", query = "SELECT d FROM Defis d WHERE d.score2 = :score2"),
    @NamedQuery(name = "Defis.findByEloMatch", query = "SELECT d FROM Defis d WHERE d.eloMatch = :eloMatch"),
    @NamedQuery(name = "Defis.findByDateDefi", query = "SELECT d FROM Defis d WHERE d.dateDefi = :dateDefi")})
public class Defis implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_defi")
    private Integer idDefi;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score1")
    private int score1;
    @Basic(optional = false)
    @NotNull
    @Column(name = "score2")
    private int score2;
    @Basic(optional = false)
    @NotNull
    @Column(name = "elo_match")
    private int eloMatch;
    @Basic(optional = false)
    @NotNull
    @Column(name = "date_defi")
    @Temporal(TemporalType.DATE)
    private Date dateDefi;
    @JoinColumn(name = "id_user1", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idUser1;
    @JoinColumn(name = "id_user2", referencedColumnName = "id_user")
    @ManyToOne(optional = false)
    private User idUser2;

    public Defis() {
    }

    public Defis(Integer idDefi) {
        this.idDefi = idDefi;
    }

    public Defis(Integer idDefi, int score1, int score2, int eloMatch, Date dateDefi) {
        this.idDefi = idDefi;
        this.score1 = score1;
        this.score2 = score2;
        this.eloMatch = eloMatch;
        this.dateDefi = dateDefi;
    }

    public Integer getIdDefi() {
        return idDefi;
    }

    public void setIdDefi(Integer idDefi) {
        this.idDefi = idDefi;
    }

    public int getScore1() {
        return score1;
    }

    public void setScore1(int score1) {
        this.score1 = score1;
    }

    public int getScore2() {
        return score2;
    }

    public void setScore2(int score2) {
        this.score2 = score2;
    }

    public int getEloMatch() {
        return eloMatch;
    }

    public void setEloMatch(int eloMatch) {
        this.eloMatch = eloMatch;
    }

    public Date getDateDefi() {
        return dateDefi;
    }

    public void setDateDefi(Date dateDefi) {
        this.dateDefi = dateDefi;
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
        hash += (idDefi != null ? idDefi.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Defis)) {
            return false;
        }
        Defis other = (Defis) object;
        if ((this.idDefi == null && other.idDefi != null) || (this.idDefi != null && !this.idDefi.equals(other.idDefi))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.middleware.entities.Defis[ idDefi=" + idDefi + " ]";
    }

}
