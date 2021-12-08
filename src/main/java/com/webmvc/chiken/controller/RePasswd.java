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

@WebServlet(name = "RePasswd", value = "/re-passwd")
public class RePasswd extends HttpServlet {
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
        String code = req.getParameter("code");
        String url = "/login.jsp";
        String status = "";

        CustomerEntity customer = (CustomerEntity) httpSession.getAttribute("customer");
        if (customer == null) {
            status = "lỗi hệ thống";
            url = "/re-passwd.jsp";

        } else {
            String action = req.getParameter("action");
            if (action == null || action.equals("")){
                action = "rePasswd";
            }
            if (action.equals("rePasswd")){
                url = "/re-passwd.jsp";

            }else if (action.equals("update")){
                String passwd = customer.getPasswd();
                String oldPasswd = req.getParameter("oldPasswd");
                String newPasswd = req.getParameter("newPasswd");
                String checkPasswd = req.getParameter("checkPasswd");

                if (oldPasswd == null || newPasswd == null || checkPasswd == null
                        || oldPasswd.equals("") || newPasswd.equals("") || checkPasswd.equals("")) {
                    status = "bạn cần điền đủ thông tin";
                    url = "/re-passwd.jsp";
                } else if ( !oldPasswd.trim().equals(passwd.trim())  ) {
                    status = "mật khẩu cũ không đúng";
                    url = "/re-passwd.jsp";
                } else if (!newPasswd.trim().equals(checkPasswd.trim())) {
                    status = "xác nhận mật khẩu không đúng";
                    url = "/re-passwd.jsp";
                } else {
                    customer.setPasswd(newPasswd);
                    CustomerDB.update(customer);
                    httpSession.setAttribute("customer", customer);

                    int id = customer.getCustomerId();
                    AddressEntity address = AddressDB.getAddressByCustomerId(id);
                    req.setAttribute("user", customer);
                    req.setAttribute("address", address);

                    url = "/infor.jsp";
                    status = "đổi mật khẩu thành công";
                }
            }
        }
        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }
}
