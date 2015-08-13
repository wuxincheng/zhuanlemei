<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册 - TOP</title>

<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="shortcut icon" />

<!-- meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">

</head>
<body id="home" class="notes-index">
  <div class="container">
    <jsp:include page="HEADER.jsp" />
    <div class="content row cf">
      <div class="forms">
        <form accept-charset="UTF-8" action="${root}/register/doRegister"
          class="simple_form form" id="new_user" method="post">
          <div style="display: none">
            <input name="utf8" type="hidden" value="&#x2713;" /><input
              name="authenticity_token" type="hidden"
              value="yXHXPCuoke25kYrOhAbhrrdplZICg6L01fjRe3X6+Q4=" />
          </div>
          <div class="form-group email optional user_email">
            <label class="email optional" for="loginEmail">邮箱</label><input
              class="string email optional form-control input-small"
              id="loginEmail" name="loginEmail"
              placeholder="name@your_company.com" type="email" value="" />
          </div>
          <div class="form-group password required user_password">
            <label class="password required" for="password">密码</label><input
              aria-required="true"
              class="password required form-control input-small"
              id="password" name="password" placeholder="密码"
              required="required" type="password" />
          </div>
          <input class="btn submit" name="commit" type="submit"
            value="完成注册" />

          <hr>

          <a href="${root}/login/">登录</a><br> <a
            href="#">没有收到验证邮件?</a><br>
        </form>
      </div>

      <aside class="aside">
        <h4>微信扫一扫：立即登录、快速注册～</h4>
        <img
          src="#"
          alt="微信二维码" id="sign_in_qrcode_image" width="160" height="160"
          data-ok-url="/posts">
        <ul class="aside-tips">
          <li>1.微信授权仅用于获取昵称和头像信息</li>
          <li>2.定制关注和订阅，个性化内容和动态提醒</li>
          <li>3.定向推送最新产品信息，还有更多…</li>
        </ul>
      </aside>

    </div>
  </div>

  <jsp:include page="FOOTER.jsp" />

</body>
</html>