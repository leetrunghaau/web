package com.webmvc.chiken.controller;

import com.webmvc.chiken.model.DAO.ViewProductDB;
import com.webmvc.chiken.model.entity.CartEntity;
import com.webmvc.chiken.model.entity.CustomerEntity;
import com.webmvc.chiken.model.entity.LineItemEntity;
import com.webmvc.chiken.model.entity.ViewProductEntity;
import com.webmvc.chiken.util.MyUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@WebServlet(name = "Cart", value = "/cart")
public class Cart extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html; charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");

        String url = "/cart.jsp";
        String status = "";
        HttpSession httpSession = req.getSession();
        String action = req.getParameter("action");

        CustomerEntity customer = (CustomerEntity) httpSession.getAttribute("customer");


        if (customer == null){
            status = "bạn cần đăng nhập";
            url = "/report.jsp";
        }else {
            if(action == null){
                action = "details";

            }
            if (action.trim().equals("details")){
                url = details(req, resp);
            }else if (action.equals("addToCart")){
                url = addToDetails(req,resp);
            }else if (action.equals("update")){
                url = update(req,resp);
            }
        }

        getServletContext().getRequestDispatcher(url).forward(req,resp);
    }



    String addToDetails(HttpServletRequest req, HttpServletResponse resp){
        String url = "";
        String productCode = req.getParameter("productCode");

        //tạo line item
        ViewProductEntity product = ViewProductDB.getProductByCode(productCode);
        LineItemEntity lineItemEntity = new LineItemEntity();
        lineItemEntity.setProduct(product);

        //lấy cart từ session
        HttpSession session = req.getSession();
        CartEntity cart = (CartEntity) session.getAttribute("cart");


        if (cart == null){
            cart = new CartEntity();
            lineItemEntity.setQuantity(1);
        }else {

            if (cart.getItemByProductCode(productCode) == null){
                lineItemEntity.setQuantity(1);
            } else {
                lineItemEntity.setQuantity(cart.getItemByProductCode(productCode).getQuantity() + 1);
            }
        }

        cart.addItem(lineItemEntity);

        session.setAttribute("cart", cart);
        req.setAttribute("cart",cart);
        url = "/cart.jsp";
        return  url;
    }
    String update(HttpServletRequest req, HttpServletResponse resp){
        String url = "";
        String productCode = req.getParameter("productCode");
        String quantityString = req.getParameter("quantity");


        //lấy cart từ session
        HttpSession session = req.getSession();
        CartEntity cart = (CartEntity) session.getAttribute("cart");
        if (cart == null){
            cart = new CartEntity();
        }
        int quantity;
        if (MyUtil.isInteger(quantityString)){
            quantity = Integer.parseInt(quantityString);
            if (quantity < 0 ){
                quantity = 1;
            }
        }else {
            quantity = 1;
        }

        //lấy product từ database
        ViewProductEntity product = ViewProductDB.getProductByCode(productCode);
        LineItemEntity lineItem = new LineItemEntity();
        lineItem.setProduct(product);
        lineItem.setQuantity(quantity);
        if (quantity > 0){
            cart.addItem(lineItem);
        }else if (quantity == 0){
            cart.removeItem(lineItem);
        }

        session.setAttribute("cart", cart);
        req.setAttribute("cart",cart);
        url = "/cart.jsp";
        return  url;
    }

    String details (HttpServletRequest req, HttpServletResponse resp){
        String url = "/";

        HttpSession session = req.getSession();
        CartEntity cart = (CartEntity) session.getAttribute("cart");
        if (cart == null){
            cart = new CartEntity();
        }
        session.setAttribute("cart", cart);
        req.setAttribute("cart",cart);
        url = "/cart.jsp";
        return url;
    }
}
