<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>我的榜单 - TOP</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">

<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index">
  <div class="container">
    <jsp:include page="../../HEADER.jsp" />

    <div class="content row ">
      <section class="main-header cf row">
        <!-- 
        <div class="list-sort left">
          <a class="list-all active" href="/posts/collections">最新</a>
          <a class="list-faved " href="/posts/collections/hot">热门</a>
        </div>
        <a class="btn submit-btn right" href="${root}/collect/edit">创建榜单</a>
        <c:if test="${'1' eq user.collectPermission}">
        </c:if>
         -->
      </section>
      <section class="list">
        <ul class="list-grid cf">
          <c:choose>
          <c:when test="${not empty collects}">
          <c:forEach items="${collects}" var="collect">
          <li class="list-item">
            <div class="cover" style="background-image: url(${root}/collect/coverbg/${collect.coverImgPath})">
              <div class="cover-meta">
                <h3>${collect.collectName}</h3>
                <ul class="list-meta">
                  <li><span>${collect.productSum}</span>个产品</li>
                  <li><span class="liked-count">${collect.collectSum}</span>人收藏</li>
                </ul>
                <div style="margin-top: 15px; margin-bottom: 10px; border-top: 1px solid #e4e4e4;"></div>
                <ul class="list-meta">
                  <li><a href="${root}/collect/detail?collectid=${collect.collectid}" target="_blank">查看</a></li>
                  <li><a href="${root}/my/collects/edit?collectid=${collect.collectid}">修改</a></li>
                  <c:if test="${collect.productSum < 1 && collect.collectSum < 1}">
                  <li><a href="${root}/my/collects/delete?collectid=${collect.collectid}">删除</a></li>
                  </c:if>
                </ul>
              </div>
            </div>
          </li>
          </c:forEach>
          </c:when>
          <c:otherwise>
            <div class="alert text-info fade in row">
              <h4>你还没有发布过榜单信息</h4>
            </div>
          </c:otherwise>
          </c:choose>
        </ul>
      </section>
    </div>
  </div>

  <jsp:include page="../../FOOTER.jsp" />

</body>
</html>