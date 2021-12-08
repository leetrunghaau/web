package com.webmvc.chiken.controller;


import com.webmvc.chiken.model.DAO.BillDB;
import com.webmvc.chiken.model.DAO.BillItemDB;
import com.webmvc.chiken.model.DAO.ViewBillDB;
import com.webmvc.chiken.model.DAO.ViewBillItemDB;
import com.webmvc.chiken.model.entity.Bill;
import com.webmvc.chiken.model.entity.BillEntity;
import com.webmvc.chiken.model.entity.ViewBillEntity;
import com.webmvc.chiken.model.entity.ViewBillItemEntity;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "AdminBill", value = "/admin-bill")
public class AdminBill extends HttpServlet {
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
        String status = "";
        String url = "/admin-login.jsp";
        String admin = (String) httpSession.getAttribute("admin");
        if (admin == null || !admin.equals("18112001")) {
            url = "/admin-login.jsp";


        } else {
            String action = req.getParameter("action");
            if (action == null){
                action = "bill";

            }
            if (action.trim().equals("bill")){
                String id = req.getParameter("id");
                ViewBillEntity viewBill = ViewBillDB.getViewBillByBillId(id);
                ArrayList<ViewBillItemEntity> listBillItem = ViewBillItemDB.getListViewBillItemByBillId(id);
                Bill bill = new Bill();
                        bill.setBill(listBillItem);
                        bill.setDiscountValue(viewBill.getDiscountValue());
                req.setAttribute("listBillItem",listBillItem);
                req.setAttribute("bill", bill);
                req.setAttribute("viewBill", viewBill);
                url = "/admin-bill.jsp";

            }else if (action.trim().equals("confirm")){
                String id = req.getParameter("id");
                BillEntity bill = BillDB.getBillById(id);
                if (bill == null){
                    url = "/report.jsp";
                    status = "lỗi hệ thống";
                }else {
                    bill.setProcessed((byte) 1);
                    BillDB.update(bill);
                    url = "/admin.jsp";
                }

            }


        }
        req.setAttribute("status", status);
        getServletContext().getRequestDispatcher(url).forward(req, resp);

    }
}
