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
@Table(name = "tblEventDetail", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEventDetail.findAll", query = "SELECT t FROM TblEventDetail t")
    , @NamedQuery(name = "TblEventDetail.findById", query = "SELECT t FROM TblEventDetail t WHERE t.id = :id")
    , @NamedQuery(name = "TblEventDetail.findByAttendance", query = "SELECT t FROM TblEventDetail t WHERE t.attendance = :attendance")
    , @NamedQuery(name = "TblEventDetail.findByStudentID", query = "SELECT t FROM TblEventDetail t WHERE t.studentID = :studentID")
    , @NamedQuery(name = "TblEventDetail.findByFullname", query = "SELECT t FROM TblEventDetail t WHERE t.fullname = :fullname")
    , @NamedQuery(name = "TblEventDetail.findByEmail", query = "SELECT t FROM TblEventDetail t WHERE t.email = :email")
    , @NamedQuery(name = "TblEventDetail.findByRegisterTime", query = "SELECT t FROM TblEventDetail t WHERE t.registerTime = :registerTime")
    , @NamedQuery(name = "TblEventDetail.findByComingtime", query = "SELECT t FROM TblEventDetail t WHERE t.comingtime = :comingtime")
    , @NamedQuery(name = "TblEventDetail.findByCancelTime", query = "SELECT t FROM TblEventDetail t WHERE t.cancelTime = :cancelTime")
    , @NamedQuery(name = "TblEventDetail.findByStatus", query = "SELECT t FROM TblEventDetail t WHERE t.status = :status")})
public class TblEventDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 20)
    private String id;
    @Column(name = "attendance")
    private Boolean attendance;
    @Basic(optional = false)
    @Column(name = "studentID", nullable = false, length = 8)
    private String studentID;
    @Column(name = "fullname", length = 30)
    private String fullname;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    @Basic(optional = false)
    @Column(name = "registerTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date registerTime;
    @Column(name = "comingtime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date comingtime;
    @Column(name = "cancelTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date cancelTime;
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 10)
    private String status;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvent eventID;
    @JoinColumn(name = "memberID", referencedColumnName = "studentID")
    @ManyToOne
    private TblUser memberID;

    public TblEventDetail() {
    }

    public TblEventDetail(String id) {
        this.id = id;
    }

    public TblEventDetail(String id, String studentID, String email, Date registerTime, String status) {
        this.id = id;
        this.studentID = studentID;
        this.email = email;
        this.registerTime = registerTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Boolean getAttendance() {
        return attendance;
    }

    public void setAttendance(Boolean attendance) {
        this.attendance = attendance;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Date getComingtime() {
        return comingtime;
    }

    public void setComingtime(Date comingtime) {
        this.comingtime = comingtime;
    }

    public Date getCancelTime() {
        return cancelTime;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TblEvent getEventID() {
        return eventID;
    }

    public void setEventID(TblEvent eventID) {
        this.eventID = eventID;
    }

    public TblUser getMemberID() {
        return memberID;
    }

    public void setMemberID(TblUser memberID) {
        this.memberID = memberID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEventDetail)) {
            return false;
        }
        TblEventDetail other = (TblEventDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "binhtt.entities.TblEventDetail[ id=" + id + " ]";
    }
    
}
