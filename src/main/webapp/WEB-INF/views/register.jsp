<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册 - 赚了没</title>

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<!-- meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">

</head>
<body id="home" class="notes-index">
  <jsp:include page="HEADER.jsp" />
  
  <div class="container">
    <div class="content row cf"> <!-- login-row  -->
      <div class="forms "> <!-- login-box -->
        <form accept-charset="UTF-8" action="${root}/register/doRegister" class="simple_form form" id="new_user" method="post">
          <div class="form-group email optional user_email">
            <label class="email optional" for="loginEmail">邮箱（用于登录）</label>
            <input aria-required="true" class="string email optional form-control input-small" id="loginEmail" name="loginEmail"
              placeholder="name@your_company.com" type="email" value="" maxlength="50" />
          </div>
          <div class="form-group nickname optional user_nickname">
            <label class="nickname optional" for="loginEmail">昵称</label>
            <input aria-required="true" class="string nickname optional form-control input-small" id="nickName" name="nickName"
              placeholder="昵称" type="text" value="" maxlength="10" />
          </div>
          <div class="form-group password required user_password">
            <label class="password required" for="password">密码</label>
            <input aria-required="true" class="password required form-control input-small" maxlength="50"
              id="password" name="password" placeholder="密码" required="required" type="password" />
          </div>
          <div class="form-group password required user_password">
            <label class="password required" for="password">重新输入密码</label>
            <input aria-required="true" class="password required form-control input-small" maxlength="50"
              id="password2" name="password2" placeholder="重新输入密码" required="required" type="password" />
          </div>
          <input class="btn submit" name="commit" type="submit" value="完成注册" />
          
          <div class="oauth-panel" >
            <ul class="upvote-users cf">
              <li class="product-avatar">
                <div class="user-image"><strong><a href="${root}/login/">立即登录</a></strong>
                <div style="margin-top:10px; font-style:italic;">注册好麻烦呀，在右则直接使用微信扫码登录</div></div>
              </li>
            </ul>
          </div>
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
                <a class="user-image-link" href="${root}/oauth/qq/login">
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