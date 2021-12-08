package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.BillEntity;
import com.webmvc.chiken.model.entity.BillItemEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.util.HibernateUtil;


import javax.persistence.*;
import java.util.ArrayList;

public class BillItemDB {
    public static void inset(BillItemEntity item){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.persist(item);
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
