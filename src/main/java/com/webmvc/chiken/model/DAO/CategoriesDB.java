package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.CategoriesEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.ViewProductEntity;
import com.webmvc.chiken.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class CategoriesDB {
    public static ArrayList<CategoriesEntity> getListCategory(){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CategoriesEntity> category;
        ArrayList<CategoriesEntity> results = null;
        try {
            category = em.createQuery("select c from CategoriesEntity c ", CategoriesEntity.class);

            results   = (ArrayList<CategoriesEntity>) category.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;
    }
    public static CategoriesEntity getCategoryByCode(String code){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CategoriesEntity> customer;
        CategoriesEntity results = null;
        try {
            customer = em.createQuery("select c from CategoriesEntity c where c.categoryCode = ?1", CategoriesEntity.class);
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
