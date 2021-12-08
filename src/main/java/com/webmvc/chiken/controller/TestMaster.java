package com.webmvc.chiken.controller;


import com.webmvc.chiken.model.entity.CategoriesEntity;

import com.webmvc.chiken.model.entity.ViewProductEntity;
import com.webmvc.chiken.util.HibernateUtil;
import org.hibernate.Transaction;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "TestMaster", value = "/test-master")
public class TestMaster extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        String url = "/test.jsp";
       ArrayList<ViewProductEntity> productEntities = null;
        if (action == null) {
            url = "/test.jsp";
        } else if (action.equals("getListProduct")) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
            EntityManager em = emf.createEntityManager();
            TypedQuery<ViewProductEntity> product;

            List<ViewProductEntity> results = null;
            try {
                product = em.createQuery("select p from ViewProductEntity p ", ViewProductEntity.class);

                results   =  product.getResultList();

            } catch (Exception e) {
                System.out.println(e.getMessage());

            } finally {
                em.close();
            }
        }else if (action.equals("getProduct")){
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
            EntityManager em = emf.createEntityManager();
            TypedQuery<ViewProductEntity> product;
            ViewProductEntity results = null;
            String code = req.getParameter("code");
            try {
                product = em.createQuery("select p from ViewProductEntity p where p.productCode = ?1", ViewProductEntity.class);
                product.setParameter(1,code);

                results   =  product.getSingleResult();

            } catch (Exception e) {
                System.out.println(e.getMessage());

            } finally {
                em.close();
            }

        }else if (action.equals("upca")){
            String id = req.getParameter("id");
            String name = req.getParameter("name");
            String code = req.getParameter("code");

            CategoriesEntity categories = new CategoriesEntity();
            categories.setCategoryId(Integer.valueOf(id));
            categories.setCategoryName(name);
            categories.setCategoryCode(code);

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("test");
            EntityManager em = emf.createEntityManager();
            Transaction transaction = (Transaction) em.getTransaction();


            try {
                transaction.begin();
                em.merge(categories);
                transaction.commit();

            } catch (Exception e) {
                if (transaction.isActive()){
                    transaction.rollback();
                }
                System.out.println(e.getMessage());

            } finally {
                em.close();
                emf.close();
            }



        }else if (action.equals("billItem")){

        }
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }

}
