package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.ProductEntity;
import com.webmvc.chiken.model.entity.ViewProductEntity;
import com.webmvc.chiken.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class ProductDB {
    public static ProductEntity getProductByCode(String code){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProductEntity> product;
        ProductEntity results = null;
        try {
            product = em.createQuery("select p from ProductEntity p where p.productCode = ?1", ProductEntity.class);
            product.setParameter(1,code);

            results   =  product.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;
    }
    public static ProductEntity getProductById(int id){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ProductEntity> product;
        ProductEntity results = null;
        try {
            product = em.createQuery("select p from ProductEntity p where p.id = ?1", ProductEntity.class);
            product.setParameter(1,id);

            results   =  product.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;
    }
    public static void update(ProductEntity product){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.merge(product);
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
    public static void inset(ProductEntity product){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.persist(product);
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
    public static void delete(ProductEntity product){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.remove(product);
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
