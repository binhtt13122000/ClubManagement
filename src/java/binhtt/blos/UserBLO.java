package binhtt.blos;

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

    public List<TblUser> getAllUsers() throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblUser> users = null;
        try {
            String jpql = "TblUser.findAll";
            Query query = em.createNamedQuery(jpql);
            users = query.getResultList();
        } finally {
            em.close();
        }
        return users;
    }

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

}
