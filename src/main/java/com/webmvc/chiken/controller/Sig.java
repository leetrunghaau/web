package com.webmvc.chiken.controller;

import com.restfb.logging.RestFBLogger;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import com.restfb.types.User;
import com.webmvc.chiken.model.DAO.CustomerDB;

import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.util.EmailUtil;
@WebServlet(name = "Sig", value = "/sig")
public class Sig extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");



        String url = "/login.jsp";
        String action = req.getParameter("action");
        String status = "";
        if (action == null){
            url = "/login.jsp";

//            HttpSession httpSession = req.getSession();
//            CustomerEntity customer = new CustomerEntity();
//            customer.setCustomerId(1);
//            customer.setName("hau");
//            customer.setMail("leetrunghaau@gmail.com");
//            customer.setPasswd("13297");
//            httpSession.setAttribute("customer", customer);
//            url = "/index.jsp";

        }else if (action.equals("login")){
            HttpSession httpSession = req.getSession();
            CustomerEntity userSession =(CustomerEntity) httpSession.getAttribute("customer");
            if (userSession == null){ //trong sesion chưa có user
                String email = req.getParameter("email");
                String passwd = req.getParameter("passwd");
                //lấy customer từ database
                CustomerEntity customer = CustomerDB.getCustomerByEmailAndPasswd(email,passwd);
                if (customer != null){
                    //đăng nhập thành công

                    //đưa user vào session

                    httpSession.setAttribute("customer", customer);


                    //add cookie
                    //add cookie that stores the user's email to browser
//                    Cookie emailCookie = new Cookie("emailCookie",email);
//                    emailCookie.setMaxAge(60*60); //coolie có thời gian sống = 1h
//                    emailCookie.setPath("/");
//                    resp.addCookie(emailCookie);

                    //trả về đăng nhập thành công, trả về trang chủ

                    url = "/index.jsp";

                    System.out.println("đăng nhập thành công");
                } else {
                    //sai email hoặc mật khẩu, trả về trang đăng nhập kèm theo thông báo
                    url = "/login.jsp";
                    status = "sai email hoặc mật khẩu";
                    req.setAttribute("status", status);
                }
            }else { //trường hợp người dùng đã đăng nhập rồi
                url = "/report.jsp";
                status = "bạn đã đăng nhập rồi";
                req.setAttribute("status", status);
            }

        }else if (action.equals("logout")){
            //clear cookie
//            Cookie[] cookies = req.getCookies();
//            for (Cookie cookie : cookies ){
//                if (cookie.getName().equals("emailCookie")){
//                    cookie.setMaxAge(0);
//                    cookie.setPath("/");
//                    resp.addCookie(cookie);
//                }
//            }
            //clear session
            HttpSession session = req.getSession();
            session.removeAttribute("customer");
            session.removeAttribute("cart");
            url = "/index.jsp";
        }

        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }

}
