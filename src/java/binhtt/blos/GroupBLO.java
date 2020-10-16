package binhtt.blos;

import binhtt.entities.TblGroup;
import binhtt.entities.TblGroupDetail;

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

    public TblGroup getAGroup(String groupID){
        EntityManager em = emf.createEntityManager();
        TblGroup group = null;
        try {
            group = em.find(TblGroup.class, groupID);
        } finally {
            em.close();
        }
        return group;
    }

    public List<TblGroup> getAllGroup(){
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

    public List<TblGroupDetail> getGroupDetailByGroupId(String id){
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
}
