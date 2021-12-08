package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.BillEntity;

import com.webmvc.chiken.model.entity.ViewBillItemEntity;
import com.webmvc.chiken.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ViewBillItemDB {

    public static ArrayList<ViewBillItemEntity> getListViewBillItemByBillId(String id) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ViewBillItemEntity> billItem;
        ArrayList<ViewBillItemEntity> results = null;
        try {
            billItem = em.createQuery("select b from ViewBillItemEntity b  where b.billId = ?1", ViewBillItemEntity.class);
            billItem.setParameter(1,id);
            results = (ArrayList<ViewBillItemEntity>) billItem.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();
        }
        return results;
    }


}
