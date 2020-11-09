/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author binht
 */
@Entity
@Table(name = "tblEvent", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEvent.findAll", query = "SELECT t FROM TblEvent t")
    , @NamedQuery(name = "TblEvent.findByEventID", query = "SELECT t FROM TblEvent t WHERE t.eventID = :eventID")
    , @NamedQuery(name = "TblEvent.findByEventName", query = "SELECT t FROM TblEvent t WHERE t.eventName = :eventName")
    , @NamedQuery(name = "TblEvent.findByEventDesc", query = "SELECT t FROM TblEvent t WHERE t.eventDesc = :eventDesc")
    , @NamedQuery(name = "TblEvent.findByBanner", query = "SELECT t FROM TblEvent t WHERE t.banner = :banner")
    , @NamedQuery(name = "TblEvent.findByTimeCloseRegister", query = "SELECT t FROM TblEvent t WHERE t.timeCloseRegister = :timeCloseRegister")
    , @NamedQuery(name = "TblEvent.findByTimeStartEvent", query = "SELECT t FROM TblEvent t WHERE t.timeStartEvent = :timeStartEvent")
    , @NamedQuery(name = "TblEvent.findByTimeCloseEvent", query = "SELECT t FROM TblEvent t WHERE t.timeCloseEvent = :timeCloseEvent")
    , @NamedQuery(name = "TblEvent.findByLocator", query = "SELECT t FROM TblEvent t WHERE t.locator = :locator")
    , @NamedQuery(name = "TblEvent.findByTotal", query = "SELECT t FROM TblEvent t WHERE t.total = :total")
    , @NamedQuery(name = "TblEvent.findByIsInternal", query = "SELECT t FROM TblEvent t WHERE t.isInternal = :isInternal")
    , @NamedQuery(name = "TblEvent.findByNumRegister", query = "SELECT t FROM TblEvent t WHERE t.numRegister = :numRegister")
    , @NamedQuery(name = "TblEvent.findByNumOfAttendees", query = "SELECT t FROM TblEvent t WHERE t.numOfAttendees = :numOfAttendees")
    , @NamedQuery(name = "TblEvent.findByEventStatus", query = "SELECT t FROM TblEvent t WHERE t.eventStatus = :eventStatus")
    , @NamedQuery(name = "TblEvent.findByCreatedTime", query = "SELECT t FROM TblEvent t WHERE t.createdTime = :createdTime")})
public class TblEvent implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventID")
    private Collection<TblComment> tblCommentCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "eventID")
    private Collection<TblEventDetail> tblEventDetailCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "eventID", nullable = false, length = 20)
    private String eventID;
    @Basic(optional = false)
    @Column(name = "eventName", nullable = false, length = 50)
    private String eventName;
    @Basic(optional = false)
    @Column(name = "eventDesc", nullable = false, length = 1000)
    private String eventDesc;
    @Column(name = "banner", length = 200)
    private String banner;
    @Basic(optional = false)
    @Column(name = "timeCloseRegister", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCloseRegister;
    @Basic(optional = false)
    @Column(name = "timeStartEvent", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeStartEvent;
    @Basic(optional = false)
    @Column(name = "timeCloseEvent", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeCloseEvent;
    @Basic(optional = false)
    @Column(name = "locator", nullable = false, length = 100)
    private String locator;
    @Basic(optional = false)
    @Column(name = "total", nullable = false)
    private int total;
    @Basic(optional = false)
    @Column(name = "isInternal", nullable = false)
    private boolean isInternal;
    @Basic(optional = false)
    @Column(name = "numRegister", nullable = false)
    private int numRegister;
    @Basic(optional = false)
    @Column(name = "numOfAttendees", nullable = false)
    private int numOfAttendees;
    @Column(name = "eventStatus", length = 20)
    private String eventStatus;
    @Column(name = "createdTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;

    public TblEvent() {
    }

    public TblEvent(String eventID) {
        this.eventID = eventID;
    }

    public TblEvent(String eventID, String eventName, String eventDesc, Date timeCloseRegister, Date timeStartEvent, Date timeCloseEvent, String locator, int total, boolean isInternal, int numRegister, int numOfAttendees) {
        this.eventID = eventID;
        this.eventName = eventName;
        this.eventDesc = eventDesc;
        this.timeCloseRegister = timeCloseRegister;
        this.timeStartEvent = timeStartEvent;
        this.timeCloseEvent = timeCloseEvent;
        this.locator = locator;
        this.total = total;
        this.isInternal = isInternal;
        this.numRegister = numRegister;
        this.numOfAttendees = numOfAttendees;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public Date getTimeCloseRegister() {
        return timeCloseRegister;
    }

    public void setTimeCloseRegister(Date timeCloseRegister) {
        this.timeCloseRegister = timeCloseRegister;
    }

    public Date getTimeStartEvent() {
        return timeStartEvent;
    }

    public void setTimeStartEvent(Date timeStartEvent) {
        this.timeStartEvent = timeStartEvent;
    }

    public Date getTimeCloseEvent() {
        return timeCloseEvent;
    }

    public void setTimeCloseEvent(Date timeCloseEvent) {
        this.timeCloseEvent = timeCloseEvent;
    }

    public String getLocator() {
        return locator;
    }

    public void setLocator(String locator) {
        this.locator = locator;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean getIsInternal() {
        return isInternal;
    }

    public void setIsInternal(boolean isInternal) {
        this.isInternal = isInternal;
    }

    public int getNumRegister() {
        return numRegister;
    }

    public void setNumRegister(int numRegister) {
        this.numRegister = numRegister;
    }

    public int getNumOfAttendees() {
        return numOfAttendees;
    }

    public void setNumOfAttendees(int numOfAttendees) {
        this.numOfAttendees = numOfAttendees;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (eventID != null ? eventID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEvent)) {
            return false;
        }
        TblEvent other = (TblEvent) object;
        if ((this.eventID == null && other.eventID != null) || (this.eventID != null && !this.eventID.equals(other.eventID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "binhtt.entities.TblEvent[ eventID=" + eventID + " ]";
    }

    @XmlTransient
    public Collection<TblEventDetail> getTblEventDetailCollection() {
        return tblEventDetailCollection;
    }

    public void setTblEventDetailCollection(Collection<TblEventDetail> tblEventDetailCollection) {
        this.tblEventDetailCollection = tblEventDetailCollection;
    }

    @XmlTransient
    public Collection<TblComment> getTblCommentCollection() {
        return tblCommentCollection;
    }

    public void setTblCommentCollection(Collection<TblComment> tblCommentCollection) {
        this.tblCommentCollection = tblCommentCollection;
    }
    
}
