package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.BillEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.ViewBillEntity;
import com.webmvc.chiken.model.entity.ViewBillItemEntity;
import com.webmvc.chiken.util.HibernateUtil;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Date;
import java.util.ArrayList;

public class BillDB {
    public static BillEntity getBillById(String id) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<BillEntity> bill;
        BillEntity results = null;
        try {
            bill = em.createQuery("select b from BillEntity b where b.billId = ?1", BillEntity.class);
            bill.setParameter(1, id);
            results = bill.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();
        }
        return results;
    }
    public static void update(BillEntity bill){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.merge(bill);
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
    public static void inset(BillEntity bill) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.persist(bill);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            em.close();

        }

    }

    public static int getCountBillToDate(Date toDate, CustomerEntity customer) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<BillEntity> bill;
        int results = 0;
        try {
            bill = em.createQuery("select b from BillEntity b where b.invoiceDate = ?1 and b.customer = ?2", BillEntity.class);
            bill.setParameter(1, toDate);
            bill.setParameter(2, customer);
            results = bill.getResultList().size();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();
        }
        return results;
    }


}