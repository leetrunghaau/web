package com.webmvc.chiken.controller;


import com.webmvc.chiken.model.DAO.CategoriesDB;
import com.webmvc.chiken.model.DAO.ViewProductDB;
import com.webmvc.chiken.model.entity.CategoriesEntity;
import com.webmvc.chiken.model.entity.ViewProductEntity;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
@WebServlet(name = "Portfolio",value = "/portfolio")
public class Portfolio extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        ArrayList<CategoriesEntity> categoriesList = CategoriesDB.getListCategory();
        ArrayList<ViewProductEntity> productList = ViewProductDB.getListProduct();
        req.setAttribute("categoriesList",categoriesList);
        req.setAttribute("productList",productList);
        String url = "/portfolio.jsp";
        getServletContext().getRequestDispatcher(url).forward(req,resp);


    }
}
