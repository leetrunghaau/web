package com.webmvc.chiken.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AdminSig",value = "/admin-sig")
public class AdminSig extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String url = "/admin-login.jsp";
        String action = req.getParameter("action");
        String status = "";
        HttpSession httpSession = req.getSession();
        String admin = (String) httpSession.getAttribute("admin");



        if (action == null){
            action = "login";
        }
        if (action.equals("login")){

            if(admin == null || !admin.equals("18112001")){
                String user = req.getParameter("user");
                String passwd = req.getParameter("passwd");
                if ( user == null || user.equals("") || passwd == null || passwd.equals("")){
                    status = "bạn nhập thiết thông tin";
                    url = "/admin-login.jsp";
                }else if (!user.equals("admin") || !passwd.equals("123")){
                    status = "bạn nhập sai user hoặc mật khẩu";
                    url = "/admin-login.jsp";
                }else {
                    httpSession.setAttribute("admin","18112001");
                    url = "/admin";
                }

            }else {
                //bạn đã đăng nhập nên chuyển về trang admin home
                url = "/admin";
            }
        }else if (action.equals("logout")){
            httpSession.removeAttribute("admin");
            httpSession.removeAttribute("customer");
            url = "/index.jsp";
        }

        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }
}
