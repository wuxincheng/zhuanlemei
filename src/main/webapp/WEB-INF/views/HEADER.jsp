<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP</title>

<link data-turbolinks-track="true" href="${root}/assets/css/style.css" media="all" rel="stylesheet">
<link data-turbolinks-track="true" href="${root}/assets/css/application.css" media="all" rel="stylesheet">
<script data-turbolinks-track="true" src="${root}/assets/js/application.js"></script>

<!-- meta -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="renderer" content="webkit">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<!-- info -->
<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

<!--iOS -->
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
<meta name="apple-mobile-web-app-title" content="Title">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="HandheldFriendly" content="True">
<meta name="MobileOptimized" content="320">

<meta content="authenticity_token" name="csrf-param">
<meta content="+pyNg3ggt3L/IZk4ood3x5j18ChulGazM+BKMuOT/ks=" name="csrf-token">
</head>
<body id="home" class="notes-index">
  <!--[if lt IE 9]>
  <div class="for-ie-suckers">
    <div class="row">
      <b>36<del>0</del>TOP安全卫士提醒您：</b>您的 IE 浏览器不被支持。试试其他的：
      <a href="http://www.google.com/chrome">Google 浏览器</a>、
      <a href="http://firefox.com.cn/">火狐浏览器</a>、
      <a href="http://www.apple.com.cn/safari/">Safari</a>
    </div>
  </div>
<![endif]-->

  <header class="topbar cf">
    <div class="row">
  
      <div class="brand">
        <a href="${root}/fund/market/list">
        <img src="${root}/assets/img/logo/logo-top-bar.png" height="35px" width="113px" />
        </a>
      </div>
      
      <nav class="nav-title">
        <ul>
          <li><a class="main-nav " href="${root}/fund/market/list">首页</a></li>
          <li><a class="main-nav " href="${root}/collect/list">榜单</a></li>
        </ul>
      </nav>
      
      <nav class="navigation text-center">
        <ul>
          <!-- 
          <li>
            <div class="hunt">
              <form action="/posts/search" method="post">
                <input id="keyword" name="keyword" class="main-hunt_input" type="text" placeholder="查找基金产品、基金代码" />
                <input class="main-hunt_button g-search" type="submit">
              </form>  
            </div>
          </li>
           -->
          <c:choose>
          <c:when test="${not empty user}">
          <li class="account login-menu">
            <a class="account-menu main-nav" href="javascript:">
              <img class="avatar" height="30" width="30"
              <c:choose>
              <c:when test="${not empty user.picPath}">
              src="${root}/user/avatar/${user.picPath}"
              </c:when>
              <c:when test="${not empty user.socialPicPath}">
              src="${user.socialPicPath}"
              </c:when>
              <c:otherwise>
              src="${root}/assets/img/logo/toplogo.png"
              </c:otherwise>
              </c:choose>  />
            </a>
            <ul class="dropdown login-dropdown">
              <li><a href="${root}/my/home/list">我的主页</a></li>
              <li><a href="${root}/my/collect/list">我的收藏</a></li>
              <li><a href="${root}/my/collects/list">我的榜单</a></li>
              <li><a href="${root}/my/info/query">个人设置</a></li>
              <li><a data-method="delete" href="${root}/logout/" rel="nofollow">退出登录</a></li>
            </ul>
          </li>        
          </c:when>
          <c:otherwise>
          <li class="account login-wechat">
            <a class="main-nav" href="${root}/login/">登录</a>
          </li> 
          </c:otherwise>
          </c:choose>
        </ul>
      </nav>
    
    </div>
  </header>
  
  <div class="row">
    <c:if test="${not empty success}">
    <div id="msg-alert" class="alert alert-success fade in row" style="margin-top: 30px;">
      <h4>${success}</h4>
    </div>
    </c:if>
    
    <c:if test="${not empty info}">
    <div id="msg-alert" class="alert alert-info fade in row" style="margin-top: 30px;">
      <h4>${info}</h4>
    </div>
    </c:if>
  
    <c:if test="${not empty warning}">
    <div id="msg-alert" class="alert alert-warning fade in row" style="margin-top: 30px;">
      <h4>${warning}</h4>
    </div>
    </c:if>
  
    <c:if test="${not empty danger}">
    <div id="msg-alert" class="alert alert-danger fade in row" style="margin-top: 30px;">
      <h4>${danger}</h4>
    </div>
    </c:if>
  </div>
  
  <script type="text/javascript">
    $(document).ready(function(){
  	  var msg = $("#msg-alert").text();
    	if (msg == '') {
    		return;
    	}
    	
    	// sleep(1000);
    	
    	// $("#msg-alert").remove();
    	
    	// $("#msg-alert").hide(8000, 'swing');
    	// $("#msg-alert").fadeTo(50000,1).hide();
    });
    
    function sleep(numberMillis) { 
  	   var now = new Date();
  	   var exitTime = now.getTime() + numberMillis;  
  	   while (true) { 
  	       now = new Date(); 
  	       if (now.getTime() > exitTime) return;
  	    }
  	}
  </script>
</body>
</html>