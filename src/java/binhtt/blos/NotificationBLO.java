/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.blos;

import binhtt.entities.TblNotifications;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author binht
 */
public class NotificationBLO implements Serializable {

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

    //get all notification
    public List<TblNotifications> getAll() throws Exception{
        EntityManager em = emf.createEntityManager();
        List<TblNotifications> notifications;
        try {
            TypedQuery<TblNotifications> query = em.createQuery("SELECT t FROM TblNotifications t ORDER BY t.timeCreated desc", TblNotifications.class);
            notifications = query.getResultList();
        } finally {
            em.close();
        }
        return notifications;
    }

    //create notification
    public boolean createNotification(TblNotifications notification) throws Exception{
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            TblNotifications currentNotification = em.find(TblNotifications.class, notification.getIdNoti());
            if(currentNotification == null){
                em.getTransaction().begin();
                em.persist(notification);
                em.getTransaction().commit();
                check = true;
            }
        } finally {
            em.close();
        }
        return check;
    }
}
