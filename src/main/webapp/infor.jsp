<%--
  Created by IntelliJ IDEA.
  User: Hau
  Date: 12/1/2021
  Time: 1:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="jsp-header.jsp" %>
<main class="login-box">
    <div class="contain">
        <div class="login-form">
            <form action="infor" method="post">
                <h1>Chỉnh sửa thông tin</h1>
                <div class="input-box ">
                    <i>Họ và tên : </i>
                    <input type="text" placeholder="Nhập tên" name="name" id="update-name" value="${user.getName()}">

                </div>
                <div class="input-box ">
                    <i>Email :</i>
                    <input type="email" placeholder="Nhập email" name="email" id="update-email" value="${user.getMail()}">

                </div>
                <div class="input-box">
                    <i>Thông tin giao hàng :</i>
                    <p style="text-align: left;">Tên người nhận: ${address.getAddressName()}</p>
                    <p style="text-align: left;">Số điện thoại người nhận: ${address.getPhone()}</p>
                    <p style="text-align: left;">Địa chỉ: ${address.fullAddress()}</p>

                </div>
                <div class="login-box">
                    <h2>Đăng nhập bằng tài khoản khác: </h2>
                    <input type="hidden" name="action" value="update">

<%--                    facebook--%>
                    <c:choose>
                        <c:when test="${customer.getFbId() == null}">
                            <a href="https://www.facebook.com/dialog/oauth?client_id=606938923966761&redirect_uri=https://group6project.herokuapp.com/fb-login" class="social-button" id="facebook-connect"> <span>Connect with Facebook</span></a>
                        </c:when>
                        <c:when test="${customer.getFbId() != null}">
                            <a href="fb-login?action=remove" class="social-button" id="facebook-connect"> <span>Remove with Facebook</span></a>
                        </c:when>
                    </c:choose>
                    <%--                    google--%>
                    <c:choose>
                        <c:when test="${customer.getGgId() == null}">
                            <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=https://group6project.herokuapp.com/gg-login&response_type=code&client_id=1020369265111-u30b21k5ghkap6g6bh666r6j3q1qhvni.apps.googleusercontent.com&approval_prompt=force" class="social-button" id="google-connect"> <span>Connect with Google</span></a>
                        </c:when>
                        <c:when test="${customer.getGgId() != null}">
                            <a href="gg-login?action=remove" class="social-button" id="google-connect"> <span>Remove with Google</span></a>
                        </c:when>
                    </c:choose>


                </div>

                <a href="re-passwd" style="float:left;">Đổi mật khẩu ?</a><br>
                <a href="address?state=infor" style="float:left;">Đổi thông tin giao hàng ?</a>
                <br> <p class="text-danger" style="float:left;"> ${status}</p>
                <a href="confirmemail.html " style="color: #FFF9E5; ">
                    <div class="btn-box ">
                        <button type="submit ">
                            Cập nhật
                        </button>
                    </div>
                </a>
            </form>
        </div>
    </div>
</main>
<%@include file="jsp-footer.jsp" %>