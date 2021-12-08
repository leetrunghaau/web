package com.webmvc.chiken.controller;

import com.webmvc.chiken.model.DAO.AddressDB;
import com.webmvc.chiken.model.DAO.CustomerDB;
import com.webmvc.chiken.model.entity.AddressEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Infor",value = "/infor")
public class Infor extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        HttpSession httpSession = req.getSession();
        String url = "/login.jsp";
        String status = "";
        CustomerEntity userSession =(CustomerEntity) httpSession.getAttribute("customer");
        String action =req.getParameter("action");
        if (action == null){
            action = "infor";
        }
        if (action.equals("infor")){
            if (userSession == null){
                //chưa đăng nhập
                url = "/login.jsp";
                status = "bạn cần đăng nhập";
            }else {
                //
                int id = userSession.getCustomerId();
                AddressEntity address = AddressDB.getAddressByCustomerId(id);

                req.setAttribute("user", userSession);
                req.setAttribute("address", address);


                url = "/infor.jsp";


            }
        }else if (action.equals("update")){
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            if (name == null || email == null || name.equals("") || email.equals("")){
                int id = userSession.getCustomerId();
                AddressEntity address = AddressDB.getAddressByCustomerId(id);

                req.setAttribute("user", userSession);
                req.setAttribute("address", address);


                url = "/infor.jsp";
                status = "bạn điền thiếu thông tin ";
            }else {
                userSession.setName(name);
                userSession.setMail(email);
                CustomerDB.update(userSession);
                httpSession.setAttribute("customer", userSession);

                int id = userSession.getCustomerId();
                AddressEntity address = AddressDB.getAddressByCustomerId(id);

                req.setAttribute("user", userSession);
                req.setAttribute("address", address);
                url = "/infor.jsp";

            }
        }

        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req,resp);

    }
}
