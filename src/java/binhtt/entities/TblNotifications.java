/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author binht
 */
@Entity
@Table(name = "tblNotifications", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNotifications.findAll", query = "SELECT t FROM TblNotifications t")
    , @NamedQuery(name = "TblNotifications.findByIdNoti", query = "SELECT t FROM TblNotifications t WHERE t.idNoti = :idNoti")
    , @NamedQuery(name = "TblNotifications.findByContentNoti", query = "SELECT t FROM TblNotifications t WHERE t.contentNoti = :contentNoti")
    , @NamedQuery(name = "TblNotifications.findByTimeCreated", query = "SELECT t FROM TblNotifications t WHERE t.timeCreated = :timeCreated")})
public class TblNotifications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idNoti", nullable = false, length = 20)
    private String idNoti;
    @Column(name = "contentNoti", length = 100)
    private String contentNoti;
    @Column(name = "timeCreated")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCreated;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvent eventID;

    public TblNotifications() {
    }

    public TblNotifications(String idNoti) {
        this.idNoti = idNoti;
    }

    public String getIdNoti() {
        return idNoti;
    }

    public void setIdNoti(String idNoti) {
        this.idNoti = idNoti;
    }

    public String getContentNoti() {
        return contentNoti;
    }

    public void setContentNoti(String contentNoti) {
        this.contentNoti = contentNoti;
    }

    public Date getTimeCreated() {
        return timeCreated;
    }

    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    public TblEvent getEventID() {
        return eventID;
    }

    public void setEventID(TblEvent eventID) {
        this.eventID = eventID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNoti != null ? idNoti.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNotifications)) {
            return false;
        }
        TblNotifications other = (TblNotifications) object;
        if ((this.idNoti == null && other.idNoti != null) || (this.idNoti != null && !this.idNoti.equals(other.idNoti))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "binhtt.entities.TblNotifications[ idNoti=" + idNoti + " ]";
    }
    
}
