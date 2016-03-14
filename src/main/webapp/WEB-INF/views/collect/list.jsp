<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>组合 - 赚了没：最好玩的基金组合排行榜</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index" style="background-color: #eeeeee;">
  <jsp:include page="../HEADER.jsp" />
  <div class="container">

    <div class="content row ">
      <section class="main-header cf row">
        <!-- 
        <div class="list-sort left">
          <a class="list-all active" href="/posts/collections">最新</a>
          <a class="list-faved " href="/posts/collections/hot">热门</a>
        </div>
         -->
        <c:if test="${'1' eq user.collectPermission}">
        <a class="btn submit-btn right" href="${root}/collect/edit">创建组合</a>
        </c:if>
      </section>
      <section class="list">
        <ul class="list-grid cf">
          <c:if test="${not empty collects}">
          <c:forEach items="${collects}" var="collect">
          <li class="list-item">
            <a href="${root}/collect/detail?collectid=${collect.collectid}" target="_blank">
            <c:if test="${not empty collect.coverImgPath}">
            <img class="cover" src="${collect.coverImgPath}" />
            </c:if>
            <c:if test="${empty collect.coverImgPath}">
            <div style="height:123px; width: 237px; background-color: ${collect.bgColor};">&nbsp;</div>
            </c:if>
            </a>
            <div class="list-item-memo">
              <a href="${root}/collect/detail?collectid=${collect.collectid}" target="_blank">${collect.collectName}</a>
            </div>
            <div class="item-article">
              <div class="prods"><span>${collect.productSum}</span>个产品</div>
              <div class="focus"><span>${collect.collectSum}</span>人收藏</div>
            </div>
          </li>
          
          <!-- 
          <li class="list-item">
            <a class="cover" href="${root}/collect/detail?collectid=${collect.collectid}" target="_blank" style="
              <c:if test="${not empty collect.coverImgPath}">background-image: url(${collect.coverImgPath});</c:if>
              <c:if test="${empty collect.coverImgPath}">background-color: #${collect.bgColor};</c:if>">
            </a>
            <div class="list-item-memo"><a href="${root}/collect/detail?collectid=${collect.collectid}" target="_blank">${collect.collectName}</a></div>
            <div class="item-article">
              <div class="prods"><span>${collect.productSum}</span>个产品</div>
              <div class="focus"><span>${collect.collectSum}</span>人收藏</div>
            </div>
          </li>
           -->
          
          </c:forEach>
          </c:if>
          <c:if test="${empty collects}">
          </c:if>
        </ul>
      </section>
    </div>
  </div>

  <jsp:include page="../FOOTER.jsp" />

</body>
</html>