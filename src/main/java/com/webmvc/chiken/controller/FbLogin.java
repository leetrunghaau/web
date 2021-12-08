package com.webmvc.chiken.controller;
import com.restfb.types.User;
import com.webmvc.chiken.model.DAO.AddressDB;
import com.webmvc.chiken.model.DAO.CustomerDB;

import com.webmvc.chiken.model.entity.AddressEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.util.FacebookUtils;
import com.webmvc.chiken.util.GooglePojo;
import com.webmvc.chiken.util.GoogleUtils;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import javax.servlet.http.HttpServlet;
@WebServlet(name = "FbLogin", value = "/fb-login")
public class FbLogin extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public FbLogin() {
        super();
    }
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String code = req.getParameter("code");
        String url = "/login.jsp";
        String status = "";
        HttpSession httpSession = req.getSession();

        //////////////
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
                    String accessToken = FacebookUtils.getToken(code);
                    User user = FacebookUtils.getUserInfo(accessToken);
                    String fbId = user.getId();
                    CustomerEntity getCustomer = CustomerDB.getCustomerByFBID(fbId);
                    if (getCustomer == null){

                        //tài khoảng chưa được đăng ký
                        httpSession.setAttribute("fbId",fbId);
                        url = "/login.jsp";
                        status = "tài khoản fb này chưa được đăng ký";
                    }else {
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
                    String accessToken = FacebookUtils.getToken(code);
                    User user = FacebookUtils.getUserInfo(accessToken);
                    String fbId = user.getId();
                    customer.setFbId(fbId);
                    CustomerDB.update(customer);
                    httpSession.setAttribute("customer", customer);
                    req.setAttribute("user", customer);

                    AddressEntity address = AddressDB.getAddressByCustomer(customer);
                    req.setAttribute("address", address);

                    url = "/infor.jsp";
                    status = "đã thêm đăng nhập nhanh bằng fb thành công";
                }
            }

        }else if (action.equals("remove")){
            customer.setFbId(null);
            CustomerDB.update(customer);
            httpSession.setAttribute("customer", customer);
            req.setAttribute("user", customer);

            AddressEntity address = AddressDB.getAddressByCustomer(customer);
            req.setAttribute("address", address);

            url = "/infor.jsp";
            status = "đã xóa đăng nhập nhanh bằng fb thành công";

        }

        req.setAttribute("status",status);
        getServletContext().getRequestDispatcher(url).forward(req,resp);

    }


}
