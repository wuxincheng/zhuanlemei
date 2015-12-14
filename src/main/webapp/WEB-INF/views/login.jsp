<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>登录 - TOP</title>

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">
</head>
<body id="home" class="notes-index">
  <jsp:include page="HEADER.jsp" />
  <div class="container">
    <div class="content row cf"> <!-- login-row -->
      <div class="forms " style="padding-top: 10px;"> <!-- login-box -->
        <div style="padding-bottom: 30px; font-size: 20px; text-align: center;">账号登录</div>
        <form accept-charset="UTF-8" action="${root}/login/doLogin" class="simple_form new_user" id="new_user" method="post" role="form">
          <div class="form-group email required user_email">
            <span>登录邮箱：</span>
            <input aria-required="true" class="string email required form-control input-small" maxlength="50"
              id="loginEmail" name="loginEmail" placeholder="邮箱" required="required" type="email" value="">
          </div>
          <div class="form-group password required user_password">
            <span>登录密码：</span>
            <input aria-required="true" class="password required form-control input-small" maxlength="50"
              id="password" name="password" placeholder="密码" required="required" type="password">
          </div>
          <button class="btn submit" name="button" type="submit">登录</button>

          <div class="oauth-panel" >
            <ul class="upvote-users cf">
              <li class="product-avatar">
                <div class="user-image"><strong><a href="${root}/register/">还是去注册吧！</a></strong>
                <div style="margin-top:10px; font-style:italic;">登录好麻烦呀，在右则直接使用微信扫码登录</div></div>
              </li>
            </ul>
          </div>
          
          <!-- 
          <a href="#">忘记密码?</a><br>
          <a href="#">没有收到验证邮件?</a><br>
           -->
        </form>
      </div>

      <aside class="aside" style="width: 500px; padding-left: 150px;">
        <iframe id="main" name="main" src="${wechatOAuthJSURI}" style="width:350px; height: 400px;
          frameborder="0" scrolling="no" marginheight="0" allowTransparency="true" >
        </iframe>
        
        <div class="oauth-panel" style="margin: 0px; padding: 20px 0;">
          <ul class="upvote-users cf" style="float: left;">
            <li class="product-avatar">
              <div class="user-image"><strong><a href="${root}/register/">　　其它方式授权登录：</a></strong></div>
            </li>
            <li class="product-avatar">
              <div class="user-image">
                <a class="user-image-link" href="${root}/top/oauth/qq/login">
                  <img  src="${root}/assets/img/oauth/Connect_logo_1.png" style="height: 16px; width: 16px; margin-top: 0px; margin-bottom: 0px;" />
                </a>
              </div>
            </li>
          </ul>
        </div>
        
      </aside>
    </div>
  </div>

  <jsp:include page="FOOTER.jsp" />
  
</body>
</html>