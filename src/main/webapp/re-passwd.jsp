<%--
  Created by IntelliJ IDEA.
  User: Hau
  Date: 12/4/2021
  Time: 1:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="jsp-header.jsp" %>
<main class="login-box">
    <div class="container">
        <div class="login-form">
            <form action="re-passwd" method="post">
                <h1>Đăng ký</h1>
                <input type="hidden" name="action" value="update">

                <div class="input-box">
                    <i></i>
                    <input type="password" placeholder="Nhập mật cũ" name="oldPasswd">
                </div>
                <div class="input-box">
                    <i></i>
                    <input type="password" placeholder="Nhập mật mới" name="newPasswd">
                </div>
                <div class="input-box">
                    <i></i>
                    <input type="password" placeholder="xác nhận mật khẩu" name="checkPasswd">
                </div>

                <p class="text-danger">${status}</p>

                <div class="btn-box">
                    <button type="submit">
                        Đăng ký
                    </button>
                </div>

            </form>
        </div>
    </div>
</main>


<%@include file="jsp-footer.jsp" %>