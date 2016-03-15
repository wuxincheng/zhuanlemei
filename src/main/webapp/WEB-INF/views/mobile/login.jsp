<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<title>登录 - 赚了没？</title>

<!-- Vendor CSS -->
<link type="text/css" rel="stylesheet"
  href="${root}/assets/mobile/vendor/components/animate.css/animate.min.css" />
<link type="text/css" rel="stylesheet"
  href="${root}/assets/mobile/vendor/components/material-design-iconic-font/dist/css/material-design-iconic-font.min.css" />

<!-- CSS -->
<link type="text/css" rel="stylesheet"
  href="${root}/assets/mobile/css/app.min.1.css" />
<link type="text/css" rel="stylesheet"
  href="${root}/assets/mobile/css/app.min.2.css" />
<link type="text/css" rel="stylesheet" 
  href="${root}/assets/mobile/css/style.css" />  
</head>

<body class="login-content">

  <!-- Login -->
  <div class="lc-block toggled" id="l-login">
    <form>
    <div class="input-group m-b-20">
      <span class="input-group-addon"><i
        class="zmdi zmdi-account"></i></span>
      <div class="fg-line">
          <input type="text" class="form-control" maxlength="50"
            id="loginEmail" name="loginEmail" placeholder="邮箱" />
        </div>
    </div>

    <div class="input-group m-b-20">
      <span class="input-group-addon"><i class="zmdi zmdi-male"></i></span>
      <div class="fg-line">
          <input type="password" class="form-control" maxlength="50"
            id="password" name="password" placeholder="密码" />
        </div>
    </div>
    
    <div style="text-align: left; padding-left: 42px;">
      <button class="btn btn-primary" type="button" onclick="doSubmit();">登录</button>
      <a href="${root}/mobile/register/">
      <button class="btn btn-default" type="button">注册</button>
      </a>
    </div>
    </form>
    <c:if test="${empty PCBrowser}">
    <hr>
    <div style="text-align: left; padding-left: 42px;">
      <span>第三方授权登录：</span>
      <a href="${root}/mobile/oauth/wechat/login/self">
      <img alt="" src="${root}/assets/mobile/img/social/icon32_wx_logo.png" style="height: 23px; width: 23px;" />
      </a>
    </div>
    </c:if>
  </div>

  <script type="text/javascript"
    src="${root}/assets/mobile/vendor/components/jquery/dist/jquery.min.js"></script>
  <script type="text/javascript"
    src="${root}/assets/mobile/vendor/components/bootstrap/dist/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="${root}/assets/vendor/layer/layer.js"></script>
  <script type="text/javascript"
    src="${root}/assets/mobile/vendor/components/Waves/dist/waves.min.js"></script>
  <script type="text/javascript" src="${root}/assets/mobile/js/functions.js"></script>
  
  <script type="text/javascript">
  $(document).ready(function() {
  });
  function doSubmit() {
    var form_data = $('form').serialize();
    $.ajax( {
      type : "POST",
          url : '${root}'+"/mobile/login/submit",
          data : form_data,
          dataType : "json",
          success : function(response){
            if (!response.success) {
              layer.msg(response.errorMsg);
                  return;
            }
            if (response.redirectUrl) {
              window.location = '${root}'+response.redirectUrl;
            }
            layer.msg("登录成功");
          },
          error : function(){layer.msg("登录失败");}
      });
  }
  </script>  
</body>
</html>