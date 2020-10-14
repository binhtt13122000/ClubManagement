/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author binht
 */
@Entity
@Table(name = "tblGroup", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblGroup.findAll", query = "SELECT t FROM TblGroup t")
    , @NamedQuery(name = "TblGroup.findByGroupID", query = "SELECT t FROM TblGroup t WHERE t.groupID = :groupID")
    , @NamedQuery(name = "TblGroup.findByGroupName", query = "SELECT t FROM TblGroup t WHERE t.groupName = :groupName")
    , @NamedQuery(name = "TblGroup.findByGroupDesc", query = "SELECT t FROM TblGroup t WHERE t.groupDesc = :groupDesc")
    , @NamedQuery(name = "TblGroup.findByTotalMember", query = "SELECT t FROM TblGroup t WHERE t.totalMember = :totalMember")})
public class TblGroup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "groupID", nullable = false, length = 20)
    private String groupID;
    @Basic(optional = false)
    @Column(name = "groupName", nullable = false, length = 30)
    private String groupName;
    @Column(name = "groupDesc", length = 100)
    private String groupDesc;
    @Basic(optional = false)
    @Column(name = "totalMember", nullable = false)
    private int totalMember;
    @JoinColumn(name = "leaderId", referencedColumnName = "studentID", nullable = false)
    @ManyToOne(optional = false)
    private TblUser leaderId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "groupID")
    private Collection<TblGroupDetail> tblGroupDetailCollection;

    public TblGroup() {
    }

    public TblGroup(String groupID) {
        this.groupID = groupID;
    }

    public TblGroup(String groupID, String groupName, int totalMember) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.totalMember = totalMember;
    }

    public String getGroupID() {
        return groupID;
    }

    public void setGroupID(String groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public int getTotalMember() {
        return totalMember;
    }

    public void setTotalMember(int totalMember) {
        this.totalMember = totalMember;
    }

    public TblUser getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(TblUser leaderId) {
        this.leaderId = leaderId;
    }

    @XmlTransient
    public Collection<TblGroupDetail> getTblGroupDetailCollection() {
        return tblGroupDetailCollection;
    }

    public void setTblGroupDetailCollection(Collection<TblGroupDetail> tblGroupDetailCollection) {
        this.tblGroupDetailCollection = tblGroupDetailCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (groupID != null ? groupID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblGroup)) {
            return false;
        }
        TblGroup other = (TblGroup) object;
        if ((this.groupID == null && other.groupID != null) || (this.groupID != null && !this.groupID.equals(other.groupID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "binhtt.entities.TblGroup[ groupID=" + groupID + " ]";
    }
    
}
