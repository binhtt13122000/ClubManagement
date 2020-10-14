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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author binht
 */
@Entity
@Table(name = "tblUser", catalog = "ClubManagement", schema = "dbo", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"phone"})
    , @UniqueConstraint(columnNames = {"email"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUser.findAll", query = "SELECT t FROM TblUser t")
    , @NamedQuery(name = "TblUser.findByStudentID", query = "SELECT t FROM TblUser t WHERE t.studentID = :studentID")
    , @NamedQuery(name = "TblUser.findByPassword", query = "SELECT t FROM TblUser t WHERE t.password = :password")
    , @NamedQuery(name = "TblUser.findByFullname", query = "SELECT t FROM TblUser t WHERE t.fullname = :fullname")
    , @NamedQuery(name = "TblUser.findByEmail", query = "SELECT t FROM TblUser t WHERE t.email = :email")
    , @NamedQuery(name = "TblUser.findByPhone", query = "SELECT t FROM TblUser t WHERE t.phone = :phone")
    , @NamedQuery(name = "TblUser.findByAvatar", query = "SELECT t FROM TblUser t WHERE t.avatar = :avatar")
    , @NamedQuery(name = "TblUser.findByGetNotification", query = "SELECT t FROM TblUser t WHERE t.getNotification = :getNotification")
    , @NamedQuery(name = "TblUser.findByStatus", query = "SELECT t FROM TblUser t WHERE t.status = :status")})
public class TblUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "studentID", nullable = false, length = 8)
    private String studentID;
    @Basic(optional = false)
    @Column(name = "password", nullable = false, length = 20)
    private String password;
    @Column(name = "fullname", length = 30)
    private String fullname;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    @Column(name = "phone", length = 10)
    private String phone;
    @Column(name = "avatar", length = 200)
    private String avatar;
    @Basic(optional = false)
    @Column(name = "getNotification", nullable = false)
    private boolean getNotification;
    @Basic(optional = false)
    @Column(name = "status", nullable = false)
    private boolean status;
    @JoinColumn(name = "roleId", referencedColumnName = "id")
    @ManyToOne
    private TblRole roleId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "leaderId")
    private Collection<TblGroup> tblGroupCollection;
    @OneToMany(mappedBy = "memberID")
    private Collection<TblComment> tblCommentCollection;
    @OneToMany(mappedBy = "memberID")
    private Collection<TblEventDetail> tblEventDetailCollection;
    @OneToMany(mappedBy = "memberID")
    private Collection<TblGroupDetail> tblGroupDetailCollection;

    public TblUser() {
    }

    public TblUser(String studentID) {
        this.studentID = studentID;
    }

    public TblUser(String studentID, String password, String email, boolean getNotification, boolean status) {
        this.studentID = studentID;
        this.password = password;
        this.email = email;
        this.getNotification = getNotification;
        this.status = status;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean getGetNotification() {
        return getNotification;
    }

    public void setGetNotification(boolean getNotification) {
        this.getNotification = getNotification;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public TblRole getRoleId() {
        return roleId;
    }

    public void setRoleId(TblRole roleId) {
        this.roleId = roleId;
    }

    @XmlTransient
    public Collection<TblGroup> getTblGroupCollection() {
        return tblGroupCollection;
    }

    public void setTblGroupCollection(Collection<TblGroup> tblGroupCollection) {
        this.tblGroupCollection = tblGroupCollection;
    }

    @XmlTransient
    public Collection<TblComment> getTblCommentCollection() {
        return tblCommentCollection;
    }

    public void setTblCommentCollection(Collection<TblComment> tblCommentCollection) {
        this.tblCommentCollection = tblCommentCollection;
    }

    @XmlTransient
    public Collection<TblEventDetail> getTblEventDetailCollection() {
        return tblEventDetailCollection;
    }

    public void setTblEventDetailCollection(Collection<TblEventDetail> tblEventDetailCollection) {
        this.tblEventDetailCollection = tblEventDetailCollection;
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
        hash += (studentID != null ? studentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUser)) {
            return false;
        }
        TblUser other = (TblUser) object;
        if ((this.studentID == null && other.studentID != null) || (this.studentID != null && !this.studentID.equals(other.studentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "binhtt.entities.TblUser[ studentID=" + studentID + " ]";
    }
    
}
