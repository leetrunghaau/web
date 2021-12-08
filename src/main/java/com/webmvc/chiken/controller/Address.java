package com.webmvc.chiken.controller;

import com.webmvc.chiken.model.DAO.AddressDB;
import com.webmvc.chiken.model.DAO.CustomerDB;
import com.webmvc.chiken.model.entity.AddressEntity;
import com.webmvc.chiken.model.entity.CartEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Address", value = "/address")
public class Address extends HttpServlet {
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
        String url = "/address.jsp";
        String status = "";
        System.out.println("url 1: " + url);

        CustomerEntity customer = (CustomerEntity) httpSession.getAttribute("customer");
        if (customer == null) {
            status = "lỗi hệ thống";
            url = "/address.jsp";
            System.out.println("url 2: " + url);

        } else {
            String action = req.getParameter("action");
            if (action == null || action.equals("")) {
                action = "address";
                System.out.println("url 3: " + url);
            }
            if (action.equals("address")) {

                AddressEntity address = AddressDB.getAddressByCustomer(customer);
                req.setAttribute("address", address);
                url = "/address.jsp";
                System.out.println("url 4: " + url);

            } else if (action.equals("update")) {

                String name = req.getParameter("name");
                String phone = req.getParameter("phone");
                String province = req.getParameter("province");
                String district = req.getParameter("district");
                String commune = req.getParameter("commune");
                String details = req.getParameter("details");
                System.out.println("url 5: " + url);

                if (name == null || phone == null || province == null || district == null || commune == null || details == null ||
                        name.equals("") || phone.equals("") || province.equals("") || district.equals("") || commune.equals("") || details.equals("")) {
                    status = "bạn cần điền đủ thông tin";
                    url = "/address.jsp";
                    System.out.println("url 6: " + url);
                } else if (!MyUtil.isInteger(phone)) {
                    status = "bạn nhập sai định dang số điện thoại";
                    url = "/address.jsp";
                    System.out.println("url 7: " + url);

                } else {
                    AddressEntity address = AddressDB.getAddressByCustomer(customer);
                    if (address == null){
                        address = new AddressEntity();
                        address.setAddressName(name);
                        address.setPhone(Integer.valueOf(phone));
                        address.setProvince(province);
                        address.setDistrict(district);
                        address.setCommune(commune);
                        address.setDetails(details);
                        address.setCustomerId(customer);
                        AddressDB.insert(address);
                    }else {
                        address.setAddressName(name);
                        address.setPhone(Integer.valueOf(phone));
                        address.setProvince(province);
                        address.setDistrict(district);
                        address.setCommune(commune);
                        address.setDetails(details);
                        AddressDB.update(address);
                    }

                    System.out.println("url 8: " + url);

                    String state = req.getParameter("state");
                    if (state == null) {
                        state = "infor";
                        System.out.println("url 9: " + url);
                    }
                    if (state.equals("infor")) {
                        req.setAttribute("user", customer);
                        req.setAttribute("address", address);

                        url = "/infor.jsp";
                        status = "đổi địa chỉ giao hàng thành công";
                        System.out.println("url 10: " + url);
                    } else if (state.equals("bill")) {
                        //cart +address
                        req.setAttribute("address", address);
                        CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                        req.setAttribute("cart", cart);
                        url = "/bill.jsp";
                        status = "đổi địa chỉ giao hàng thành công";
                        System.out.println("url 11: " + url);

                    }

                }
            }
        }
        System.out.println("url 12: " + url);
        String state = req.getParameter("state");
        req.setAttribute("state", state);
        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);
    }
}
