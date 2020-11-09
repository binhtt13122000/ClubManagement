package binhtt.blos;

import binhtt.entities.TblEvent;
import binhtt.entities.TblEventDetail;
import binhtt.entities.TblRole;
import binhtt.entities.TblUser;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

public class UserBLO implements Serializable {

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

    //get all user
    public List<TblUser> getAllUsers() throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblUser> users = null;
        try {
            Query query = em.createQuery("SELECT t FROM TblUser t WHERE t.roleId.id = 2 OR t.roleId.id = 3", TblUser.class);
            users = query.getResultList();
        } finally {
            em.close();
        }
        return users;
    }

    //get all user by name
    public List<TblUser> getUserByFullName(String search) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblUser> users;
        try {
            Query query = em.createQuery("SELECT t FROM TblUser t WHERE t.fullname LIKE :fullname AND (t.roleId.id = 2 or t.roleId.id = 3)", TblUser.class);
            query.setParameter("fullname", "%" + search + "%");
            users = query.getResultList();
        } finally {
            em.close();
        }
        return users;
    }

    //get all user by role
    public List<TblUser> getAllUsersByRole(int id) throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblUser> users = null;
        try {
            TypedQuery<TblUser> query = em.createQuery("SELECT t FROM TblUser t WHERE t.roleId.id = :id", TblUser.class);
            query.setParameter("id", id);
            users = query.getResultList();
        } finally {
            em.close();
        }
        return users;
    }

    //get one user
    public TblUser getOne(String id){
        EntityManager em = emf.createEntityManager();
        TblUser user;
        try {
            user = em.find(TblUser.class,id);
        } finally {
            em.close();
        }
        return user;
    }

    //update profile <=> update TblUser
    public boolean updateProfile(TblUser user, String studentID) throws Exception{
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            TblUser currentUser = em.find(TblUser.class, studentID);
            if(currentUser != null){
                currentUser.setAvatar(user.getAvatar());
                currentUser.setPhone(user.getPhone());
                currentUser.setFullname(user.getFullname());
                currentUser.setGetNotification(user.getGetNotification());
                currentUser.setPassword(user.getPassword());
                em.getTransaction().begin();
                em.merge(currentUser);
                em.getTransaction().commit();
                valid = true;
            }
        } finally {
            em.close();
        }
        return valid;
    }

    //change status <=> Update TblUser
    public boolean changeStatus(String studentID) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            TblUser user = em.find(TblUser.class, studentID);
            if(user != null){
                user.setStatus(!user.getStatus());
                em.getTransaction().begin();
                em.merge(user);
                em.getTransaction().commit();
                valid = true;
            }
        } finally {
            em.close();
        }
        return valid;
    }

    //change role <=> Update TblUser
    public boolean changeRole(String studentID, int newRole) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            TblUser user = em.find(TblUser.class, studentID);
            if(user != null){
                TblRole role = new TblRole(newRole);
                user.setRoleId(role);
                em.getTransaction().begin();
                em.merge(user);
                em.getTransaction().commit();
                valid = true;
            }
        } finally {
            em.close();
        }
        return valid;
    }

    //create user
    public boolean create(TblUser user) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            TblUser currentUser = em.find(TblUser.class, user.getStudentID());
            if(currentUser == null){
                em.getTransaction().begin();
                em.persist(user);
                em.getTransaction().commit();
                valid = true;
            }
        } finally {
            em.close();
        }
        return valid;
    }

    //get email
    public String[] getEmail() throws Exception {
        EntityManager em = emf.createEntityManager();
        String[] emailList;
        try {
            TypedQuery<String> query = em.createQuery("SELECT t.email FROM TblUser t WHERE t.getNotification = true and t.status = true", String.class);
            List<String> emails = query.getResultList();
            emailList = new String[emails.size()];
            int index = 0;
            for (String email: emails) {
                emailList[index++] = email;
            }
        } finally {
            em.close();
        }
        return emailList;
    }

    public boolean isInCLB(String studentID, String email) throws Exception {
        boolean valid = false;
        EntityManager em = emf.createEntityManager();
        try {
            TblUser user = em.find(TblUser.class, studentID);
            if (user != null) {
                valid = true;
            } else {
                TypedQuery<TblUser> query = em.createQuery("SELECT u FROM TblUser u WHERE u.email = :email", TblUser.class);
                query.setParameter("email", email);
                TblUser user1 = query.getSingleResult();
                valid = true;
            }
        } catch (Exception e){
            valid = false;
        } finally {
            em.close();
        }
        return valid;
    }

}
