package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.DiscountEntity;
import com.webmvc.chiken.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DiscountDB {
    public static DiscountEntity getDiscountByCode(String code){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<DiscountEntity> customer;
        DiscountEntity results = null;
        try {
            customer = em.createQuery("select d from DiscountEntity d where d.discountCode = ?1", DiscountEntity.class);
            customer.setParameter(1,code);


            results   =  customer.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;
    }
}
