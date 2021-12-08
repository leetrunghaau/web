package com.webmvc.chiken.controller;

import com.webmvc.chiken.model.DAO.CategoriesDB;
import com.webmvc.chiken.model.DAO.ViewBillDB;
import com.webmvc.chiken.model.DAO.ViewProductDB;
import com.webmvc.chiken.model.entity.CategoriesEntity;
import com.webmvc.chiken.model.entity.ViewBillEntity;
import com.webmvc.chiken.model.entity.ViewProductEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminListBill", value = "/admin-list-bill")
public class AdminListBill extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession httpSession = req.getSession();
        String status = "";
        String url = "/admin-login.jsp";
        String admin = (String) httpSession.getAttribute("admin");
        if (admin == null || !admin.equals("18112001")) {
            url = "/admin-login.jsp";


        } else {
            ArrayList<ViewBillEntity> listBill = ViewBillDB.getListBill();
            req.setAttribute("billList", listBill);
            url = "/admin-list-bill.jsp";
        }
        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);




    }
}
