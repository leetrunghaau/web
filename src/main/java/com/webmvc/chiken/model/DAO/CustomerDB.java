package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.ViewProductEntity;
import com.webmvc.chiken.util.HibernateUtil;

import javax.persistence.*;
import java.util.ArrayList;

public class CustomerDB {
    public static void inset(CustomerEntity customer){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.persist(customer);
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
    public static void update(CustomerEntity customer){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.merge(customer);
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
    public static CustomerEntity getCustomerByFBID(String id){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CustomerEntity> customer;
        CustomerEntity results = null;
        try {
            customer = em.createQuery("select c from CustomerEntity c where c.fbId = ?1", CustomerEntity.class);
            customer.setParameter(1,id);


            results   =  customer.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;
    }
    public static CustomerEntity getCustomerByGGID(String id){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CustomerEntity> customer;
        CustomerEntity results = null;
        try {
            customer = em.createQuery("select c from CustomerEntity c where c.ggId = ?1", CustomerEntity.class);
            customer.setParameter(1,id);


            results   =  customer.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;

    }
    public static CustomerEntity getCustomerByEmail(String email){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CustomerEntity> customer;
        CustomerEntity results = null;
        try {
            customer = em.createQuery("select c from CustomerEntity c where c.mail = ?1", CustomerEntity.class);
            customer.setParameter(1,email);


            results   =  customer.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;

    }
    public static CustomerEntity getCustomerById(int id){

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CustomerEntity> customer;
        CustomerEntity results = null;
        try {
            customer = em.createQuery("select c from CustomerEntity c where c.customerId = ?1", CustomerEntity.class);
            customer.setParameter(1,id);


            results   =  customer.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;

    }
    public static CustomerEntity getCustomerByEmailAndPasswd(String email, String passwd){
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<CustomerEntity> customer;
        CustomerEntity results = null;
        try {
            customer = em.createQuery("select c from CustomerEntity c where c.mail = ?1 and c.passwd = ?2", CustomerEntity.class);
            customer.setParameter(1,email);
            customer.setParameter(2,passwd);

            results   =  customer.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;
    }


}
