package com.webmvc.chiken.controller;

import com.webmvc.chiken.model.DAO.AddressDB;
import com.webmvc.chiken.model.DAO.CustomerDB;

import com.webmvc.chiken.model.entity.AddressEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.util.GooglePojo;
import com.webmvc.chiken.util.GoogleUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "GgLogin", value = "/gg-login")
public class GgLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GgLogin() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String code = req.getParameter("code");
        String url = "/login.jsp";
        String status = "";
        HttpSession httpSession = req.getSession();

        CustomerEntity customer = (CustomerEntity) httpSession.getAttribute("customer");

        String action = req.getParameter("action");
        if (action == null){
            action = "siginOrSigup";
        }
        if (action.equals("siginOrSigup")){
            if (customer == null) {
                //đăng nhập
                if (code == null || code.isEmpty()) {
                    url = "/login.jsp";
                    status = "lỗi OAuth2";
                } else {
                    String accessToken = GoogleUtils.getToken(code);
                    GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                    String ggId = googlePojo.getId();
                    CustomerEntity getCustomer = CustomerDB.getCustomerByGGID(ggId);
                    if (getCustomer == null) {
                        //tài khoảng chưa được đăng ký
                        httpSession.setAttribute("ggId", ggId);
                        url = "/login.jsp";
                        status = "tài khoản gg này chưa được đăng ký";

                    } else {
                        //thủ tục đăng nhập
                        httpSession.setAttribute("customer", getCustomer);
                        url = "/index.jsp";
                    }


                }
            } else {
                //đăng ký
                if (code == null || code.isEmpty()) {
                    url = "/login.jsp";
                    status = "lỗi OAuth2";
                } else {
                    String accessToken = GoogleUtils.getToken(code);
                    GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);
                    String ggId = googlePojo.getId();
                    customer.setGgId(ggId);
                    CustomerDB.update(customer);
                    httpSession.setAttribute("customer", customer);
                    req.setAttribute("user", customer);

                    AddressEntity address = AddressDB.getAddressByCustomer(customer);
                    req.setAttribute("address", address);

                    url = "/infor.jsp";
                    status = "đã thêm đăng nhập nhanh bằng gg thành công";
                }
            }

        }else if (action.equals("remove")){
            customer.setGgId(null);
            CustomerDB.update(customer);
            httpSession.setAttribute("customer", customer);
            req.setAttribute("user", customer);

            AddressEntity address = AddressDB.getAddressByCustomer(customer);
            req.setAttribute("address", address);

            url = "/infor.jsp";
            status = "đã xóa đăng nhập nhanh bằng gg thành công";

        }


        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
