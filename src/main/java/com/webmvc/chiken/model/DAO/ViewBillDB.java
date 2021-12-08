package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.ViewBillEntity;
import com.webmvc.chiken.util.HibernateUtil;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ViewBillDB {
    public static ArrayList<ViewBillEntity> getListBill (){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ViewBillEntity> bill;
        ArrayList<ViewBillEntity> results = null;
        try {
            bill = em.createQuery("select b from ViewBillEntity b ", ViewBillEntity.class);

            results   = (ArrayList<ViewBillEntity>) bill.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();
        }
        return results;
    }
    public static ViewBillEntity getViewBillByBillId(String id){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ViewBillEntity> bill;
       ViewBillEntity results = null;
        try {
            bill = em.createQuery("select b from ViewBillEntity b where b.billId =?1", ViewBillEntity.class);
            bill.setParameter(1,id);
            results   =  bill.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();
        }
        return results;
    }

}
