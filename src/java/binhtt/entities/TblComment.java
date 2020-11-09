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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tblComment", catalog = "ClubManagement", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblComment.findAll", query = "SELECT t FROM TblComment t")
    , @NamedQuery(name = "TblComment.findById", query = "SELECT t FROM TblComment t WHERE t.id = :id")
    , @NamedQuery(name = "TblComment.findByStudentID", query = "SELECT t FROM TblComment t WHERE t.studentID = :studentID")
    , @NamedQuery(name = "TblComment.findByFullname", query = "SELECT t FROM TblComment t WHERE t.fullname = :fullname")
    , @NamedQuery(name = "TblComment.findByEmail", query = "SELECT t FROM TblComment t WHERE t.email = :email")
    , @NamedQuery(name = "TblComment.findByContent", query = "SELECT t FROM TblComment t WHERE t.content = :content")
    , @NamedQuery(name = "TblComment.findByCreatedTime", query = "SELECT t FROM TblComment t WHERE t.createdTime = :createdTime")})
public class TblComment implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "studentID", length = 8)
    private String studentID;
    @Column(name = "fullname", length = 30)
    private String fullname;
    @Basic(optional = false)
    @Column(name = "email", nullable = false, length = 40)
    private String email;
    @Column(name = "content", length = 100)
    private String content;
    @Basic(optional = false)
    @Column(name = "createdTime", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTime;
    @OneToMany(mappedBy = "parentID")
    private Collection<TblComment> tblCommentCollection;
    @JoinColumn(name = "parentID", referencedColumnName = "id")
    @ManyToOne
    private TblComment parentID;
    @JoinColumn(name = "eventID", referencedColumnName = "eventID", nullable = false)
    @ManyToOne(optional = false)
    private TblEvent eventID;
    @JoinColumn(name = "memberID", referencedColumnName = "studentID")
    @ManyToOne
    private TblUser memberID;

    public TblComment() {
    }

    public TblComment(Integer id) {
        this.id = id;
    }

    public TblComment(Integer id, String email, Date createdTime) {
        this.id = id;
        this.email = email;
        this.createdTime = createdTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    @XmlTransient
    public Collection<TblComment> getTblCommentCollection() {
        return tblCommentCollection;
    }

    public void setTblCommentCollection(Collection<TblComment> tblCommentCollection) {
        this.tblCommentCollection = tblCommentCollection;
    }

    public TblComment getParentID() {
        return parentID;
    }

    public void setParentID(TblComment parentID) {
        this.parentID = parentID;
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
        if (!(object instanceof TblComment)) {
            return false;
        }
        TblComment other = (TblComment) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "binhtt.entities.TblComment[ id=" + id + " ]";
    }
    
}
