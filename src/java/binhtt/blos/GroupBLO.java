package binhtt.blos;

import binhtt.entities.TblGroup;
import binhtt.entities.TblGroupDetail;
import binhtt.entities.TblUser;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

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

    //get a group
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

    //get all group
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

    //get all group of a leader
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

    //get groups by name
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

    //get group of leader by name
    public List<TblGroup> getGroupByName(String name, String leaderId) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblGroup> groups;
        try {
            TypedQuery<TblGroup> query = em.createQuery("SELECT t FROM TblGroup  t WHERE t.groupName LIKE :name AND t.leaderId.studentID = :leaderId", TblGroup.class);
            query.setParameter("name", "%" + name + "%");
            query.setParameter("leaderId", leaderId);
            groups = query.getResultList();
        } finally {
            em.close();
        }
        return groups;
    }

    //get all group a member applies
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

    //get group of a member applies by name
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

    //get group details by group id
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

    //delete group <=> set status is BLOCK
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

    //get users are not in group and are active
    public List<TblUser> getUserNotInGroup(String id) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblUser> user;
        try {
            TypedQuery<TblUser> query = em.createQuery("SELECT t FROM TblUser t WHERE t.roleId.id = 3 AND t.status = true AND NOT EXISTS (SELECT g.memberID FROM TblGroupDetail g WHERE g.groupID.groupID = :id and g.memberID.studentID = t.studentID)", TblUser.class);
            query.setParameter("id", id);
            user = query.getResultList();
        } finally {
            em.close();
        }
        return user;
    }


    //delete user from group <=> set STATUS of TblGroupDetail is DELETED
    public boolean deleteUserFromGroup(String id, String groupID) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            TypedQuery<TblGroupDetail> query = em.createQuery("SELECT t FROM TblGroupDetail t WHERE t.memberID.studentID = :id AND t.groupID.groupID = :groupID", TblGroupDetail.class);
            query.setParameter("id", id);
            query.setParameter("groupID", groupID);
            TblGroupDetail groupDetail = query.getSingleResult();
            groupDetail.setStatus("DELETED");
            groupDetail.setRemoveTime(new Date());
            TblGroup group = em.find(TblGroup.class, groupID);
            if(group != null){
                group.setTotalMember(group.getTotalMember() - 1);
                em.getTransaction().begin();
                em.merge(groupDetail);
                em.merge(group);
                em.getTransaction().commit();
                valid = true;
            }
        } finally {
            em.close();
        }
        return valid;
    }


    public boolean isContains(String userId, String groupId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TblGroupDetail> query = em.createQuery("SELECT t FROM TblGroupDetail t WHERE t.groupID.groupID = :groupID AND t.memberID.studentID = :studentID", TblGroupDetail.class);
            query.setParameter("studentID", userId);
            query.setParameter("groupID", groupId);
            TblGroupDetail groupDetail = query.getSingleResult();
            return true;
        } catch (NoResultException e){
            return false;
        } finally {
            em.close();
        }
    }
    //insert into TblGroupDetail
    public boolean batchInsert(HashMap<String, TblUser> cart, TblGroup group) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblUser user = null;
            em.getTransaction().begin();
            for (String key: cart.keySet()) {
                user = cart.get(key);
                if(isContains(user.getStudentID(), group.getGroupID())){
                    return false;
                }
                TblGroupDetail groupDetail = new TblGroupDetail();
                groupDetail.setGroupID(group);
                groupDetail.setMemberID(user);
                groupDetail.setStatus("NORMAL");
                Date date = new Date();
                groupDetail.setAddTime(date);
                group.setTotalMember(group.getTotalMember() + 1);
                em.persist(groupDetail);
                em.merge(group);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return true;
    }

    //add new group
    public boolean insert(TblGroup group) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            TblGroup currentGroup = em.find(TblGroup.class, group.getGroupID());
            if(currentGroup == null){
                em.getTransaction().begin();
                em.persist(group);
                em.getTransaction().commit();
                valid = true;
            }
        } finally {
            em.close();
        }
        return valid;
    }
}
