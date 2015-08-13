<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录 - TOP</title>

<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="shortcut icon" />
<!-- Wechat -->
<script src="http://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"></script>
<!-- meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">

<script type="text/javascript">
var obj = new WxLogin({
  id:"login_container", 
  appid: "wx8bc224d6280b4ace", 
  scope: "snsapi_login", 
  redirect_uri: "www.zhuanlemei.com",
  state: "sdfsdftytyh",
  style: "black",
  href: ""
});
</script>

</head>
<body id="home" class="notes-index">
  <div class="container">
    <jsp:include page="HEADER.jsp" />
    <div class="content login-row cf">
      <div class="forms">
        
      </div>
    </div>
  </div>

  <jsp:include page="FOOTER.jsp" />

</body>
</html>