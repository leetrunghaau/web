<%--
  Created by IntelliJ IDEA.
  User: Hau
  Date: 11/30/2021
  Time: 7:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="jsp-header.jsp" %>
<main class="login-box">
    <div class="container">
        <div class="login-form">
            <form action="confirm" method="post">
                <h1>nhập mã xác nhận</h1>
                <input type="hidden" name="action" value="code">
                <div class="input-box">
                    <i></i>
                    <input type="text" placeholder="Nhập code" name="code">
                </div>
                <p class="text-danger"> ${status}</p>
                <div class="btn-box">
                    <button type="submit">
                        Xác nhận
                    </button>
                </div>

            </form>
        </div>
    </div>
</main>


<%@include file="jsp-footer.jsp" %>