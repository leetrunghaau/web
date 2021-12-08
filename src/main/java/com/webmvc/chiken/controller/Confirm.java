package com.webmvc.chiken.controller;

import com.webmvc.chiken.model.DAO.CustomerDB;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.util.EmailUtil;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "Confirm", value = "/confirm")
public class Confirm extends HttpServlet {
    private String host;
    private String port;
    private String username;
    private String pass;

    public void init() {
        // reads SMTP server setting from web.xml file
        ServletContext context = getServletContext();
        host = context.getInitParameter("host");
        port = context.getInitParameter("port");
        username = context.getInitParameter("username");
        pass = context.getInitParameter("pass");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html; charset=UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        req.setCharacterEncoding("UTF-8");
//
//        String action = req.getParameter("action");
//        String url = "/confirm.jsp";
//        String status = "";
//        if (action == null){
//            //lỗi
//        }else if (action.trim().equals("reConfirm")){
//            HttpSession httpSession = req.getSession();
//            CustomerEntity customer = (CustomerEntity) httpSession.getAttribute("customer");
//            String email = customer.getMail();
//            String code = EmailUtil.getRandom();
//            boolean state;
//            try {
//                state = EmailUtil.sendEmail(host,port,username,pass,email,"Email Verification",
//                        "Registered successfully.Please verify your account using this code: " + code);
//            }catch (MessagingException e){
//                e.printStackTrace();
//                state = false;
//            }
//            if (state){
//
//                httpSession.setAttribute("code", code);
//                url = "/confirm.jsp";
//                status = "chúng tôi đã gửi code đến email của bạn, vui lòng kiểm tra ";
//            }else {
//                url = "/report.jsp";
//                status = "chúng tôi đã xảy ra lỗi vui thử lại";
//            }
//        }
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        HttpSession httpSession = req.getSession();
        String url = "/confirm.jsp";
        String action = req.getParameter("action");
        String status = "";

        if (action == null) {
            action = "confirm";
        }
        if (action.equals("confirm")) {
            url = "/confirm.jsp";
        } else if (action.equals("code")) {
            String auhCode = (String) httpSession.getAttribute("code");
            String code = req.getParameter("code");
            if (auhCode == null || auhCode.equals("")) {
                url = "/confirm.jsp";
                status = "lỗi hệ thống, vui lòng nhập trở về bước đăng ký";
            } else if (code == null || code.equals("")) {
                status = "vui lòng nhập code";
                url = "/confirm.jsp";
            } else if (!code.equals(auhCode)) {
                status = "bạn đã nhập code sai";
                url = "/confirm.jsp";
            } else {
                //xác nhận đúng
                //lưu xuống database


                CustomerEntity user = (CustomerEntity) httpSession.getAttribute("user");
                if (user == null) {
                    status = "lỗi không tìm thấy user trên session";
                    url = "/confirm.jsp";
                } else {
                    CustomerDB.inset(user);
                    CustomerEntity customer = CustomerDB.getCustomerByEmail(user.getMail());
                    httpSession.removeAttribute("user");
                    httpSession.setAttribute("customer", customer);


                    url = "/index.jsp";
                }


            }
        }
        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
