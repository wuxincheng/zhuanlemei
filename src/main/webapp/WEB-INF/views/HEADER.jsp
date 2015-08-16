<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP</title>

<link data-turbolinks-track="true" href="${root}/assets/css/bootstrap.css" media="all" rel="stylesheet">
<link data-turbolinks-track="true" href="${root}/assets/css/application.css" media="all" rel="stylesheet">
<link data-turbolinks-track="true" href="${root}/assets/css/style.css" media="all" rel="stylesheet">

<meta charset="utf-8">

<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="shortcut icon" />

<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

</head>
<body>

  <div class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">
          <img alt="赚了没" src="${root}/assets/img/logo/logo-top.png" 
            style="margin-top: -15px;" />
        </a>
      </div>
      <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li class="active">
            <div style="margin-left: 150px; margin-top: 8px;">
              <form class="form-inline">
                <div class="input-group">
                  <input type="text" class="form-control" style="width: 400px;" placeholder="基金名称、代码或评论" maxlength="50" />
                  <span class="input-group-btn">
                    <button class="btn btn-default" type="button">查找</button>
                  </span>
                </div>
              </form>
           </div>
          </li>
        </ul>
        <ul class="nav navbar-nav navbar-left">
          <li><a href="./">榜单</a></li>
        </ul>
        
        <ul class="nav navbar-nav navbar-right">
          <li><a href="../navbar/">登录</a></li>
        </ul>
      </div>
    </div>
  </div>
  
  <div style="margin: 60px;"></div>

</body>
</html>