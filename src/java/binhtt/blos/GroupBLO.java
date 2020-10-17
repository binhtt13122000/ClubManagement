package binhtt.blos;

import binhtt.entities.TblGroup;
import binhtt.entities.TblGroupDetail;
import binhtt.entities.TblUser;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class GroupBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("FPT_Club_ManagementPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    public TblGroup getAGroup(String groupID) throws Exception{
        EntityManager em = emf.createEntityManager();
        TblGroup group = null;
        try {
            group = em.find(TblGroup.class, groupID);
        } finally {
            em.close();
        }
        return group;
    }

    public List<TblGroup> getAllGroup() throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblGroup> groups;
        try {
            TypedQuery<TblGroup> query = em.createNamedQuery("TblGroup.findAll", TblGroup.class);
            groups = query.getResultList();
        } finally {
            em.close();
        }
        return groups;
    }

    public List<TblGroup> getAllGroup(String leaderId) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblGroup> groups;
        try {
            TypedQuery<TblGroup> query = em.createQuery("SELECT t FROM TblGroup t WHERE t.leaderId.studentID = :leaderId", TblGroup.class);
            query.setParameter("leaderId", leaderId);
            groups = query.getResultList();
        } finally {
            em.close();
        }
        return groups;
    }

    public List<TblGroup> getGroupByName(String name) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblGroup> groups;
        try {
            TypedQuery<TblGroup> query = em.createQuery("SELECT t FROM TblGroup  t WHERE t.groupName LIKE :name", TblGroup.class);
            query.setParameter("name", "%" + name + "%");
            groups = query.getResultList();
        } finally {
            em.close();
        }
        return groups;
    }

    public List<TblGroup> getAllGroupOfAMember(String memberId) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblGroup> groups;
        try {
            TypedQuery<TblGroup> query = em.createQuery("SELECT t.groupID FROM TblGroupDetail t WHERE t.memberID.studentID = :memberId", TblGroup.class);
            query.setParameter("memberId", memberId);
            groups = query.getResultList();
        } finally {
            em.close();
        }
        return groups;
    }

    public List<TblGroup> getGroupOfAMemberByName(String memberId, String name) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblGroup> groups;
        try {
            TypedQuery<TblGroup> query = em.createQuery("SELECT t.groupID FROM TblGroupDetail t WHERE t.memberID.studentID = :memberId AND t.groupID.groupName LIKE :name", TblGroup.class);
            query.setParameter("memberId", memberId);
            query.setParameter("name", "%" + name + "%");
            groups = query.getResultList();
        } finally {
            em.close();
        }
        return groups;
    }
    public List<TblGroup> getGroupByName(String name, String leaderId) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblGroup> groups;
        try {
            TypedQuery<TblGroup> query = em.createQuery("SELECT t FROM TblGroup  t WHERE t.groupName LIKE :name AND t.leaderId = :leaderId", TblGroup.class);
            query.setParameter("name", "%" + name + "%");
            query.setParameter("leaderId", leaderId);
            groups = query.getResultList();
        } finally {
            em.close();
        }
        return groups;
    }

    public List<TblGroupDetail> getGroupDetailByGroupId(String id) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblGroupDetail> groupDetails;
        try {
            TypedQuery<TblGroupDetail> query = em.createQuery("SELECT t FROM TblGroupDetail t WHERE t.groupID.groupID = :groupID AND t.status != 'DELETED'", TblGroupDetail.class);
            query.setParameter("groupID", id);
            groupDetails = query.getResultList();
        } finally {
            em.close();
        }
        return groupDetails;
    }

    public boolean deleteGroup(String id) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            TblGroup group = em.find(TblGroup.class, id);
            if(group != null){
                group.setStatus("BLOCK");
                em.getTransaction().begin();
                em.merge(group);
                em.getTransaction().commit();
                valid = true;
            }
        } finally {
            em.close();
        }
        return valid;
    }

    public List<TblUser> getUserNotInGroup(String id) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblUser> user;
        try {
            TypedQuery<TblUser> query = em.createQuery("SELECT t.memberID FROM TblGroupDetail t WHERE t.groupID.groupID != :id", TblUser.class);
            query.setParameter("id", id);
            user = query.getResultList();
        } finally {
            em.close();
        }
        return user;
    }
}
