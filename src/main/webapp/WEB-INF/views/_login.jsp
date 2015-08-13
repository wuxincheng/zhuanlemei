<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录 - TOP</title>

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
        <form accept-charset="UTF-8"
          action="${root}/login/doLogin"
          class="simple_form new_user" id="new_user" method="post"
          role="form">
          <div style="display: none">
            <input name="utf8" type="hidden" value="✓"><input
              name="authenticity_token" type="hidden"
              value="+pyNg3ggt3L/IZk4ood3x5j18ChulGazM+BKMuOT/ks=">
          </div>
          <span class="error "> </span>
          <div class="form-group email required user_email">
            <input aria-required="true"
              class="string email required form-control input-small"
              id="loginEmail" name="loginEmail" placeholder="邮箱"
              required="required" type="email" value="">
          </div>
          <div class="form-group password required user_password">
            <input aria-required="true"
              class="password required form-control input-small"
              id="password" name="password" placeholder="密码"
              required="required" type="password">
          </div>
          <button class="btn submit" name="button" type="submit">登录</button>

          <hr>

          <a href="${root}/register/">注册</a><br> <a
            href="#">忘记密码?</a><br>
          <a href="#">没有收到验证邮件?</a><br>
        </form>
      </div>

      <aside class="aside">
        <h4>使用第三方账号登录：</h4>
        <div>
        <img src="${root}/assets/img/oauth/qq_oauth.png" style="width: 35px; height: 35px;" />
        <img src="${root}/assets/img/oauth/qq_oauth.png" style="width: 35px; height: 35px;" />
        </div>
        
        <!-- 
        <img
          src="#"
          alt="微信二维码" id="sign_in_qrcode_image" width="160" height="160"
          data-ok-url="/posts">
        <ul class="aside-tips">
          <li>1.微信授权仅用于获取昵称和头像信息</li>
          <li>2.定制关注和订阅，个性化内容和动态提醒</li>
          <li>3.定向推送最新产品信息，还有更多…</li>
        </ul>
         -->
      </aside>

    </div>
  </div>

  <jsp:include page="FOOTER.jsp" />

</body>
</html>