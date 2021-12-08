package com.webmvc.chiken.controller;

import com.webmvc.chiken.model.DAO.*;
import com.webmvc.chiken.model.entity.*;
import com.webmvc.chiken.util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

@WebServlet(name = "CheckOut", value = "/check-out")
public class CheckOut extends HttpServlet {
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
        String url = "/login.jsp";
        String action = req.getParameter("action");
        String status = "";
        String discountStatus = "";
        CustomerEntity customer = (CustomerEntity) httpSession.getAttribute("customer");
        if (customer == null) {
            //chưa đăng nhập
            url = "/report.jsp";
            status = "bạn chưa đăng nhập";
        } else {
            if (action == null) {
                action = "checkOut";
            }
            if (action.equals("checkOut")) {
                AddressEntity address = AddressDB.getAddressByCustomer(customer);
                CartEntity cart = (CartEntity) httpSession.getAttribute("cart");

                req.setAttribute("address", address);
                req.setAttribute("cart", cart);
                url = "/bill.jsp";

            } else if (action.equals("checkCode")) {
                String code = req.getParameter("code");
                DiscountEntity discount = DiscountDB.getDiscountByCode(code);
                if (discount == null) {
                    //không có mã
                    CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                    cart.setDiscountCode("Mã không hợp lệ");
                    cart.setDiscountValue(null);
                    req.setAttribute("cart", cart);
                    AddressEntity address = AddressDB.getAddressByCustomer(customer);
                    req.setAttribute("address", address);
                    url = "/bill.jsp";

                } else {
                    long millis = System.currentTimeMillis();
                    Date toDate = new Date(millis);

                    if (toDate.getTime() > discount.getBeginDiscount().getTime() && toDate.getTime() < discount.getEndDiscount().getTime()) {
                        //còn hiệu lực của mã giảm giá
                        CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                        cart.setDiscountCode(discount.getDiscountCode());
                        cart.setDiscountValue(discount.getDiscountValue());
                        req.setAttribute("cart", cart);
                        AddressEntity address = AddressDB.getAddressByCustomer(customer);
                        req.setAttribute("address", address);
                        url = "/bill.jsp";

                    } else {
                        CartEntity cart = (CartEntity) httpSession.getAttribute("cart");
                        cart.setDiscountCode("Mã hết hạn");
                        cart.setDiscountValue(null);
                        req.setAttribute("cart", cart);
                        AddressEntity address = AddressDB.getAddressByCustomer(customer);
                        req.setAttribute("address", address);
                        url = "/bill.jsp";
                    }
                }
            } else if (action.equals("final")) {
                //lưu tất cả thông tin xuống database
                //lấy thời gian hệ thống


                CartEntity cart = (CartEntity) httpSession.getAttribute("cart");


                //khởi tạo và lưu bill
                BillEntity bill = new BillEntity();
                //set Id
                bill.setBillId(MyUtil.createBillCode(customer));
                //set address
                AddressEntity address = AddressDB.getAddressByCustomer(customer);
                bill.setAddressByAddressId(address);
                //set date
                Date toDate = Date.valueOf(MyUtil.sqlDate());
                bill.setInvoiceDate(toDate);
                //set Processed
                bill.setProcessed((byte) 0);
                //set customer
                bill.setCustomer(customer);
                //nếu có discount thì set discount
                if (cart.getDiscountValue() != 0.0) {
                    bill.setDiscount(DiscountDB.getDiscountByCode(cart.getDiscountCode()));
                }
                //gửi đến data base
                BillDB.inset(bill);


                System.out.println("luu bill");
                //lưu bill items

                ArrayList<LineItemEntity> listLineItem = cart.getItems();
                for (int i = 0; i < listLineItem.size(); i++) {
                    BillItemEntity item = new BillItemEntity();
                    item.setBillId(bill);
                    item.setQuantity(listLineItem.get(i).getQuantity());
                    item.setProductId(ProductDB.getProductByCode(listLineItem.get(i).getProduct().getProductCode()));
                    BillItemDB.inset(item);
                }
                url = "/report.jsp";
                status = "cảm ơn bạn đã sử dụng dịch vụ của chúng tôi";

            }
        }

        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }
}
