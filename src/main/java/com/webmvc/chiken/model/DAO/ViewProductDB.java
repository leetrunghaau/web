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
import java.util.List;

public class ViewProductDB {

    public static ArrayList<ViewProductEntity> getListProduct(){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ViewProductEntity> product;
        ArrayList<ViewProductEntity> results = null;
        try {
            product = em.createQuery("select p from ViewProductEntity p ", ViewProductEntity.class);

            results   = (ArrayList<ViewProductEntity>) product.getResultList();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();
        }
        return results;
    }
    public static ViewProductEntity getProductByCode(String code){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ViewProductEntity> product;
        ViewProductEntity results = null;
        try {
            product = em.createQuery("select p from ViewProductEntity p where p.productCode = ?1", ViewProductEntity.class);
            product.setParameter(1,code);

            results   =  product.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;
    }
}
