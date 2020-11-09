package binhtt.blos;

import binhtt.entities.TblComment;
import binhtt.entities.TblEvent;
import binhtt.entities.TblUser;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class CommentBLO implements Serializable {

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

    public HashMap<TblComment, List<TblComment>> getComments(String id) throws Exception {
        EntityManager em = emf.createEntityManager();
        HashMap<TblComment, List<TblComment>> tblCommentListHashMap;
        try {
            TypedQuery<TblComment> query = em.createQuery("SELECT t FROM TblComment t WHERE t.eventID.eventID = :id and t.parentID = null", TblComment.class);
            query.setParameter("id", id);
            List<TblComment> commentParents = query.getResultList();
            tblCommentListHashMap = new HashMap<>();
            for (TblComment comment : commentParents) {
                tblCommentListHashMap.put(comment, getCommentsByParent(comment.getId()));
            }
        } finally {
            em.close();
        }
        return tblCommentListHashMap;
    }

    public List<TblComment> getCommentsByParent(int id) throws Exception {
        EntityManager em = emf.createEntityManager();
        List<TblComment> comments;
        try {
            TypedQuery<TblComment> query = em.createQuery("SELECT t FROM TblComment t WHERE t.parentID.id = :id", TblComment.class);
            query.setParameter("id", id);
            comments = query.getResultList();
        } finally {
            em.close();
        }
        return comments;
    }

    public boolean insert(TblUser user, String id, String parentId, String comment, String studentID, String fullname, String email) {
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            TblEvent event = null;
            try {
                event = new EventBLO().getOneEvent(id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            TblComment tblComment = new TblComment();
            tblComment.setContent(comment);
            tblComment.setEmail(email);
            tblComment.setEventID(event);
            tblComment.setFullname(fullname);
            tblComment.setCreatedTime(new Date());
            if (!parentId.isEmpty()) {
                tblComment.setParentID(em.find(TblComment.class, Integer.parseInt(parentId)));
            }
            if (user != null) {
                tblComment.setMemberID(user);
            }
            tblComment.setStudentID(studentID);
            em.getTransaction().begin();
            em.persist(tblComment);
            em.getTransaction().commit();
            valid = true;
        } finally {
            em.close();
        }
        return valid;
    }

    public boolean delete(String id) throws Exception {
        EntityManager em = emf.createEntityManager();
        boolean valid = false;
        try {
            int idCmt = Integer.parseInt(id);
            TblComment comment = em.find(TblComment.class, idCmt);
            if(comment != null){
                if(comment.getParentID() != null){
                    em.getTransaction().begin();
                    em.remove(comment);
                    em.getTransaction().commit();
                } else {
                    TypedQuery<TblComment> query = em.createQuery("select c From TblComment c WHERE c.parentID.id = :id", TblComment.class);
                    query.setParameter("id", idCmt);
                    List<TblComment> list = query.getResultList();
                    em.getTransaction().begin();
                    for (TblComment commentChild: list) {
                        em.remove(commentChild);
                    }
                    em.remove(comment);
                    em.getTransaction().commit();
                }
                valid = true;
            } else {
                valid = false;
            }
        } finally {
            em.close();
        }
        return valid;
    }
}
