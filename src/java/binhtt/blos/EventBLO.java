/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.blos;

import binhtt.entities.TblEvent;

import java.io.Serializable;
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
public class EventBLO implements Serializable {

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

    public TblEvent getOneEvent(String id) throws Exception {
        EntityManager em = emf.createEntityManager();
        TblEvent event;
        try {
            event = em.find(TblEvent.class, id);
        } finally {
            em.close();
        }
        return event;
    }

    public List<TblEvent> getAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> events;
        try {
            TypedQuery<TblEvent> query = em.createNamedQuery("TblEvent.findAll", TblEvent.class);
            events = query.getResultList();
        } finally {
            em.close();
        }
        return events;
    }

    public List<TblEvent> getEventByName(String name) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> events;
        try {
            TypedQuery<TblEvent> query = em.createQuery("SELECT t FROM TblEvent t WHERE t.eventName LIKE :eventName", TblEvent.class);
            query.setParameter("eventName", "%" + name + "%");
            events = query.getResultList();
        } finally {
            em.close();
        }
        return events;
    }

    public boolean create(TblEvent tblEvent) throws Exception{
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            TblEvent currentEvent = em.find(TblEvent.class, tblEvent.getEventID());
            if(currentEvent == null){
                em.getTransaction().begin();
                em.persist(tblEvent);
                em.getTransaction().commit();
                check = true;
            }
        } finally {
            em.close();
        }
        return check;
    }

    public boolean update(TblEvent event) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            TblEvent currentEvent = em.find(TblEvent.class, event.getEventID());
            if(currentEvent != null){
                currentEvent.setBanner(event.getBanner());
                currentEvent.setEventDesc(event.getEventDesc());
                currentEvent.setEventName(event.getEventName());
                currentEvent.setEventStatus(event.getEventStatus());
                currentEvent.setIsInternal(event.getIsInternal());
                currentEvent.setLocator(event.getLocator());
                currentEvent.setTotal(event.getTotal());
                currentEvent.setTimeCloseEvent(event.getTimeCloseEvent());
                currentEvent.setTimeStartEvent(event.getTimeStartEvent());
                currentEvent.setTimeCloseRegister(event.getTimeCloseRegister());
                em.getTransaction().begin();
                em.merge(currentEvent);
                em.getTransaction().commit();
                check = true;
            }
        } finally {
            em.close();
        }
        return check;
    }
}
