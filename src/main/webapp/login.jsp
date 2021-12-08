<%--
  Created by IntelliJ IDEA.
  User: Hau
  Date: 11/29/2021
  Time: 8:57 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="jsp-header.jsp" %>
<main class="login-box">
    <div class="container">
        <div class="login-form">
            <form action="sig" method="post">
                <h1>Đăng nhập</h1>
                <input type="hidden" name="action" value="login">
                <div class="input-box">
                    <i></i>
                    <input type="email" placeholder="Nhập email" name="email">
                </div>
                <div class="input-box">
                    <i></i>
                    <input type="password" placeholder="Nhập mật khẩu" name="passwd">
                </div>
                <a href="register" style="float:left ; font-size: 15px;">Bạn chưa có tài khoản ?</a> <br>
                <div class="login-box">
                   
                    <a href="https://www.facebook.com/dialog/oauth?client_id=606938923966761&redirect_uri=https://group6project.herokuapp.com/fb-login" class="social-button" id="facebook-connect"> <span>Login with Facebook</span></a>
                    <a href="https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=https://group6project.herokuapp.com/gg-login&response_type=code&client_id=1020369265111-u30b21k5ghkap6g6bh666r6j3q1qhvni.apps.googleusercontent.com&approval_prompt=force" class="social-button" id="google-connect"> <span>Login with Google</span></a>
                    
                </div>
                <p class="text-danger"> ${status}</p>
                
                <div class="btn-box">
                    <button type="submit">
                        Đăng nhập
                    </button>

                </div>
        </form>
    </div>
    </div>
</main>


<%--                  https://www.facebook.com/dialog/oauth?client_id=606938923966761&redirect_uri=https://group6project.herokuapp.com/fb-login    --%>

<%--           https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=https://group6project.herokuapp.com/gg-login&response_type=code&client_id=1020369265111-u30b21k5ghkap6g6bh666r6j3q1qhvni.apps.googleusercontent.com&approval_prompt=force --%>



<%-- https://www.facebook.com/dialog/oauth?client_id=606938923966761&redirect_uri=http://localhost:8081/chiken_war_exploded/fb-login  --%>
<%--   https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri=http://localhost:8081/chiken_war_exploded/gg-login&response_type=code           --%>
<%--    &client_id=1020369265111-u30b21k5ghkap6g6bh666r6j3q1qhvni.apps.googleusercontent.com&approval_prompt=force    --%>

<%@include file="jsp-footer.jsp" %>
