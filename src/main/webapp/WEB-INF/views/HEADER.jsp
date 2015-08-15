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

  <div class="jumbotron">
    <div class="container">
    
    <div class="row">
        <div class="col-md-2">
          <img alt="赚了没" src="${root}/assets/img/logo/logo.png" />
        </div>
        <div class="col-md-8" style="text-align: center; padding-top: 20px;">
          <form class="form-inline">
            <div class="input-group">
              <input type="text" class="form-control" placeholder="基金名称、代码或评论" maxlength="50" />
              <span class="input-group-btn">
                <button class="btn btn-default" type="button">查找</button>
              </span>
            </div>
          </form>
       </div>
        <div class="col-md-1" style="padding-top: 20px;"><p><a href="${root}/collect/list"><span class="bar-title">榜单</span></a></p></div>
        <div class="col-md-1 bar-title" style="padding-top: 20px;"><p><a href="${root}/login/"><span class="bar-title">登录</span></a></p></div>
      </div>
    </div>
  </div>
  
</body>
</html>