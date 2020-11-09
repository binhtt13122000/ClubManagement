/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.blos;

import binhtt.entities.TblUser;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.*;

/**
 *
 * @author binht
 */
public class AuthenticationBLO implements Serializable {

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

    //check login
    public TblUser checkLogin(String studentID, String password) throws NoResultException {
        TblUser user = null;
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT u FROM TblUser u WHERE u.studentID = :studentID AND u.password = :password";
            TypedQuery<TblUser> query = em.createQuery(jpql, TblUser.class);
            query.setParameter("studentID", studentID);
            query.setParameter("password", password);
            user = query.getSingleResult();
        } finally {
            em.close();
        }
        return user;
    }

    //check login google by email
    public TblUser checkLoginByGoogle(String email) throws NoResultException {
        TblUser user = null;
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT u FROM TblUser u WHERE u.email = :email";
            TypedQuery<TblUser> query = em.createQuery(jpql, TblUser.class);
            query.setParameter("email", email);
            user = query.getSingleResult();
        } finally {
            em.close();
        }
        return user;
    }
}
