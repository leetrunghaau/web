package com.webmvc.chiken.model.DAO;

import com.webmvc.chiken.model.entity.AddressEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.ViewProductEntity;
import com.webmvc.chiken.util.HibernateUtil;

import javax.persistence.*;
import java.util.ArrayList;

public class AddressDB {
    public static AddressEntity getAddressByCustomerId(int id) {

        CustomerEntity customer = CustomerDB.getCustomerById(id);
        return getAddressByCustomer(customer);

    }

    public static AddressEntity getAddressByCustomer(CustomerEntity customer) {

        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        TypedQuery<AddressEntity> address;
        AddressEntity results = null;
        try {
            address = em.createQuery("select a from AddressEntity a where a.customerId = ?1", AddressEntity.class);
            address.setParameter(1, customer);

            results = address.getSingleResult();

        } catch (Exception e) {
            System.out.println(e.getMessage());

        } finally {
            em.close();

        }
        return results;
    }

    public static void update(AddressEntity address) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.merge(address);
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
    public static void insert(AddressEntity address) {
        EntityManagerFactory emf = HibernateUtil.getEMF();
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        CustomerEntity results = null;
        try {
            transaction.begin();
            em.persist(address);
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
}
