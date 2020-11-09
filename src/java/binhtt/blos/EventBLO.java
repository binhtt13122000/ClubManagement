/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binhtt.blos;

import binhtt.entities.TblEvent;
import binhtt.entities.TblEventDetail;
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

    public List<TblEventDetail> getRegisterOfAMember(TblUser user) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEventDetail> eventDetails = null;
        try {
            TypedQuery<TblEventDetail> query = em.createQuery("SELECT t from TblEventDetail t WHERE t.memberID = :studentID ORDER BY t.registerTime desc", TblEventDetail.class);
            query.setParameter("studentID", user);
            eventDetails = query.getResultList();
        } finally {
            em.close();
        }
        return eventDetails;
    }

    //get one by em.find
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

    //get all event
    public List<TblEvent> getAll() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> events;
        try {
            TypedQuery<TblEvent> query = em.createQuery("SELECT t FROM TblEvent t ORDER BY t.createdTime desc", TblEvent.class);
            events = query.getResultList();
        } finally {
            em.close();
        }
        return events;
    }

    //get all event not interval
    public List<TblEvent> getAllForGuest() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> events;
        try {
            TypedQuery<TblEvent> query = em.createQuery("SELECT t FROM TblEvent t WHERE t.isInternal = false ORDER BY t.createdTime desc", TblEvent.class);
            events = query.getResultList();
        } finally {
            em.close();
        }
        return events;
    }

    //get 3 event for context
    public List<TblEvent> getEventsForContext() throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> events;
        try {
            TypedQuery<TblEvent> query = em.createQuery("SELECT t FROM TblEvent t WHERE t.isInternal = false ORDER BY t.timeStartEvent desc", TblEvent.class);
            query.setMaxResults(3);
            events = query.getResultList();
        } finally {
            em.close();
        }
        return events;
    }

    //get event by name
    public List<TblEvent> getEventByName(String name) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> events;
        try {
            TypedQuery<TblEvent> query = em.createQuery("SELECT t FROM TblEvent t WHERE t.eventName LIKE :eventName ORDER BY t.createdTime desc", TblEvent.class);
            query.setParameter("eventName", "%" + name + "%");
            events = query.getResultList();
        } finally {
            em.close();
        }
        return events;
    }

    //get event by name for guest
    public List<TblEvent> getEventByNameForGuest(String name) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEvent> events;
        try {
            TypedQuery<TblEvent> query = em.createQuery("SELECT t FROM TblEvent t WHERE t.eventName LIKE :eventName AND t.isInternal = false ORDER BY t.createdTime desc", TblEvent.class);
            query.setParameter("eventName", "%" + name + "%");
            events = query.getResultList();
        } finally {
            em.close();
        }
        return events;
    }

    //create event
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

    //update event
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

    public boolean isContains(String userId, String eventID) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TblEventDetail> query = em.createQuery("SELECT t FROM TblEventDetail t WHERE t.eventID.eventID = :eventID AND t.studentID = :studentID", TblEventDetail.class);
            query.setParameter("studentID", userId);
            query.setParameter("eventID", eventID);
            TblEventDetail eventDetail = query.getSingleResult();
            return true;
        } catch (NoResultException e){
            return false;
        } finally {
            em.close();
        }
    }

    public boolean isContainsByEmail(String email, String eventID) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<TblEventDetail> query = em.createQuery("SELECT t FROM TblEventDetail t WHERE t.eventID.eventID = :eventID AND t.email = :email", TblEventDetail.class);
            query.setParameter("email", email);
            query.setParameter("eventID", eventID);
            TblEventDetail eventDetail = query.getSingleResult();
            return true;
        } catch (NoResultException e){
            return false;
        } finally {
            em.close();
        }
    }
    public boolean batchInsert(TblUser user, HashMap<String, TblEvent> events, String studentID, String fullname, String email) throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            TblEvent event = null;
            em.getTransaction().begin();
            for (String key: events.keySet()) {
                event = events.get(key);
                if(isContains(studentID, event.getEventID())){
                    return false;
                }
                if(isContainsByEmail(email, event.getEventID())){
                    return false;
                }
                TblEventDetail eventDetail = new TblEventDetail();
                if(user != null){
                    for(TblEventDetail eventDetail1: new EventBLO().getRegisterOfAMember(user)){
                        if(eventDetail1.getEventID().getTimeStartEvent().equals(event.getTimeStartEvent())){
                            return false;
                        }
                    }
                    eventDetail.setMemberID(user);
                }
                eventDetail.setStudentID(studentID);
                eventDetail.setEmail(email);
                eventDetail.setRegisterTime(new Date());
                eventDetail.setFullname(fullname);
                eventDetail.setEventID(event);
                eventDetail.setStatus("REGISTERED");
                event.setNumRegister(event.getNumRegister() + 1);
                em.persist(eventDetail);
                em.merge(event);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return true;
    }

    public List<TblEventDetail> getEventDetails(String id) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblEventDetail> eventDetails;
        try {
            TypedQuery<TblEventDetail> query = em.createQuery("SELECT t FROM TblEventDetail t WHERE t.eventID.eventID = :id order by t.registerTime desc", TblEventDetail.class);
            query.setParameter("id", id);
            eventDetails = query.getResultList();
        } finally {
            em.close();
        }
        return eventDetails;
    }

    public boolean saveAttendance(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean check = false;
        try {
            TblEventDetail eventDetail = em.find(TblEventDetail.class, id);
            if(eventDetail != null){
                eventDetail.setStatus("ATTENDANCE");
                eventDetail.setComingtime(new Date());
                TblEvent event = eventDetail.getEventID();
                event.setNumOfAttendees(event.getNumOfAttendees() + 1);
                em.getTransaction().begin();
                em.merge(event);
                em.merge(eventDetail);
                em.getTransaction().commit();
                check = true;
            }
        } finally {
            em.close();
        }
        return check;
    }

    public void changeStatus() throws Exception {
        EntityManager em = emf.createEntityManager();
        try {
            List<TblEvent> events = getAll();
            em.getTransaction().begin();
            for (TblEvent event: events) {
                event.setEventStatus(setStatus(event));
                em.merge(event);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public String setStatus(TblEvent event){
        if(event.getEventStatus().equals("DELETED")){
            return "DELETED";
        }
        Date date = new Date();
        if(date.before(event.getTimeCloseRegister())){
            return "REGISTER";
        } else if(date.before(event.getTimeStartEvent())){
            return "UPCOMING";
        } else if(date.before(event.getTimeCloseEvent())){
            return "PRESENT";
        } else {
            return "END";
        }
    }

    public List<TblEvent> getInRangeDate(String start, String end) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date timeStart = dateFormat.parse(start);
        Date timeEnd = dateFormat.parse(end);
        EntityManager em = emf.createEntityManager();
        List<TblEvent> events;
        try {
            TypedQuery<TblEvent> query = em.createQuery("SELECT t FROM TblEvent t WHERE t.timeStartEvent > :start AND t.timeCloseEvent < :end order by t.numOfAttendees desc", TblEvent.class);
            query.setParameter("start", timeStart);
            query.setParameter("end", timeEnd);
            query.setMaxResults(3);
            events = query.getResultList();
        } finally {
            em.close();
        }
        return events;
    }

//    public void changeStatusEventDetail() throws Exception {
//        EntityManager em = emf.createEntityManager();
//        try {
//            List<TblEventDetail> eventDetails = em.createNamedQuery("TblEventDetail.findAll", TblEventDetail.class).getResultList();
//            em.getTransaction().begin();
//            for (TblEventDetail eventDetail: eventDetails) {
//                eventDetail.setStatus(isNotAttendance(eventDetail) ? "ABSENT" : "ATTENDANCE");
//                em.merge(eventDetail);
//            }
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//    }
//
//    public boolean isNotAttendance(TblEventDetail eventDetail){
//        if(eventDetail.getStatus().equals("REGISTERED") && eventDetail.getEventID().getTimeCloseRegister().before(new Date())){
//            return true;
//        }
//        return false;
//    }

    public List<TblEventDetail> getRegisterOfAMemberByName(TblUser user, String name) {
        EntityManager em = emf.createEntityManager();
        List<TblEventDetail> eventDetails = null;
        try {
            TypedQuery<TblEventDetail> query = em.createQuery("SELECT t from TblEventDetail t WHERE t.studentID = : studentID AND t.eventID.eventName = :name ORDER BY t.registerTime desc", TblEventDetail.class);
            query.setParameter("studentID", user);
            query.setParameter("name", name);
            eventDetails = query.getResultList();
        } finally {
            em.close();
        }
        return eventDetails;
    }
}
