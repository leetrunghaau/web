package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.FeedbackEntity;
import com.webmvc.chiken.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class FeedBackDB {
    public static void inset(FeedbackEntity feedback){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.persist(feedback);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (transaction.isActive()){
                transaction.rollback();
            }
            em.close();

        }

    }
}
