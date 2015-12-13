<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>登录 - 赚了没</title>

<link href="${root}/assets/vendor/bootstrap/css/bootstrap.css" media="all" rel="stylesheet">
<link href="${root}/assets/vendor/mobile/css/style.css" media="all" rel="stylesheet">

<script src="${root}/assets/js/jquery.min.js"></script>
<script src="${root}/assets/vendor/layer/layer.js"></script>

<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="renderer" content="webkit">
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
</head>
<body>
  <div class="container">
      <div class="form-panel" style="padding-top: 10px;"> <!-- login-box -->
        <div class="title">账号登录</div>
        <form accept-charset="UTF-8" action="${root}/mobile/login/submit" method="post">
          <div class="form-group email required user_email">
            <input class="form-control" maxlength="50" autocomplete="off"
              id="loginEmail" name="loginEmail" placeholder="邮箱" type="email" />
          </div>
          <div class="form-group password required user_password">
            <input class="password required form-control input-small" maxlength="50"
              id="password" name="password" placeholder="密码" required="required" type="password">
          </div>
          <button class="btn btn-primary btn-block" type="button" onclick="doSubmit();">登录</button>
          <button class="btn btn-block" type="button">取消</button>

          <div class="fotter-tip">
            <strong><a href="${root}/mobile/register/">还是去注册吧！</a></strong>
          </div>
        </form>
    </div>
  </div>
</body>
</html>