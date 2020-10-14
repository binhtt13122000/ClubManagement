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
@Table(name = "tblGroupDetail", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblGroupDetail.findAll", query = "SELECT t FROM TblGroupDetail t")
    , @NamedQuery(name = "TblGroupDetail.findById", query = "SELECT t FROM TblGroupDetail t WHERE t.id = :id")
    , @NamedQuery(name = "TblGroupDetail.findByStatus", query = "SELECT t FROM TblGroupDetail t WHERE t.status = :status")
    , @NamedQuery(name = "TblGroupDetail.findByAddTime", query = "SELECT t FROM TblGroupDetail t WHERE t.addTime = :addTime")
    , @NamedQuery(name = "TblGroupDetail.findByRemoveTime", query = "SELECT t FROM TblGroupDetail t WHERE t.removeTime = :removeTime")})
public class TblGroupDetail implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id", nullable = false, length = 10)
    private String id;
    @Basic(optional = false)
    @Column(name = "status", nullable = false, length = 10)
    private String status;
    @Basic(optional = false)
    @Column(name = "addTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date addTime;
    @Column(name = "removeTime")
    @Temporal(TemporalType.TIMESTAMP)
    private Date removeTime;
    @JoinColumn(name = "groupID", referencedColumnName = "groupID", nullable = false)
    @ManyToOne(optional = false)
    private TblGroup groupID;
    @JoinColumn(name = "memberID", referencedColumnName = "studentID")
    @ManyToOne
    private TblUser memberID;

    public TblGroupDetail() {
    }

    public TblGroupDetail(String id) {
        this.id = id;
    }

    public TblGroupDetail(String id, String status, Date addTime) {
        this.id = id;
        this.status = status;
        this.addTime = addTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public Date getRemoveTime() {
        return removeTime;
    }

    public void setRemoveTime(Date removeTime) {
        this.removeTime = removeTime;
    }

    public TblGroup getGroupID() {
        return groupID;
    }

    public void setGroupID(TblGroup groupID) {
        this.groupID = groupID;
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
        if (!(object instanceof TblGroupDetail)) {
            return false;
        }
        TblGroupDetail other = (TblGroupDetail) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "binhtt.entities.TblGroupDetail[ id=" + id + " ]";
    }
    
}
