<%--
  Created by IntelliJ IDEA.
  User: Hau
  Date: 12/5/2021
  Time: 12:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="admin-header.jsp"%>
<main class="costumerbill">
    <h1>Hoá đơn</h1>
    <h2>Họ và tên :  ${viewBill.getAddressName()}</h2>
    <h2>Số điện thoại :  ${viewBill.getPhone()} </h2>
    <h2>Địa chỉ : ${viewBill.fullAddress()}</h2>

    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">Tên sản phẩm</div>
            <div class="col col-2">Giá</div>
            <div class="col col-3">Số lượng</div>
            <div class="col col-4">Tổng</div>
        </li>

        <c:forEach var="billItem" items="${listBillItem}">
            <li class="table-row">
                <div class="col col-1" data-label="Product">${billItem.getProductName()}</div>
                <div class="col col-2" data-label="Amount">${billItem.priceFormat()}</div>
                <div class="col col-3" data-label="Number">${billItem.getQuantity()}</div>
                <div class="col col-4" data-label="Cost">${billItem.totalFormat()}</div>
            </li>
        </c:forEach>


    </ul>

    <ul class="responsive-table">
        <li class="table-header">
            <div class="col col-1">Mã khuyến mãi</div>
            <div class="col col-2">Tổng tiền</div>
            <div class="col col-3">Số tiền giảm</div>
            <div class="col col-4">Thành tiền</div>
        </li>
        <li class="table-row">
            <div class="col col-1" data-label="Product">${viewBill.getDiscountCode()}</div>
            <div class="col col-2" data-label="Amount">${bill.getSumTotalFormat()}</div>
            <div class="col col-3" data-label="Number">${bill.getDiscountValueFormat()}</div>
            <div class="col col-4" data-label="Cost">${bill.getSumTotalFinalFormat()}</div>


        </li>
    </ul>

    <a href="admin-bill?action=confirm&id=${viewBill.getBillId()}" >Xác nhận</a>
</main>


<%@ include file="admin-footer.jsp"%>