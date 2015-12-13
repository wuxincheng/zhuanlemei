<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>登录 - 赚了没</title>

<link data-turbolinks-track="true" href="${root}/assets/vendor/style/style.css" media="all" rel="stylesheet">
<link data-turbolinks-track="true" href="${root}/assets/vendor/style/application.css" media="all" rel="stylesheet">

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="renderer" content="webkit">
</head>
<body id="home" class="notes-index">
  <div class="container">
    <div class="content row cf"> <!-- login-row -->
      <div class="forms" style="padding-top: 10px;"> <!-- login-box -->
        <div style="padding-bottom: 30px; font-size: 20px; text-align: center;">账号登录</div>
        <form accept-charset="UTF-8" action="${root}/login/doLogin" class="mobile_login_form" method="post" role="form">
          <div class="form-group email required user_email">
            <input aria-required="true" class="string email required form-control input-small" maxlength="50"
              id="loginEmail" name="loginEmail" placeholder="邮箱" required="required" type="email" value="">
          </div>
          <div class="form-group password required user_password">
            <input aria-required="true" class="password required form-control input-small" maxlength="50"
              id="password" name="password" placeholder="密码" required="required" type="password">
          </div>
          <button class="btn submit" name="button" type="submit">登录</button>

          <div class="oauth-panel" >
            <ul class="upvote-users cf">
              <li class="product-avatar">
                <div class="user-image"><strong><a href="${root}/register/">还是去注册吧！</a></strong></div>
              </li>
            </ul>
          </div>
        </form>
      </div>

    </div>
  </div>
  
</body>
</html>