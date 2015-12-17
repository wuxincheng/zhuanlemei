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

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index">
  <jsp:include page="../../HEADER.jsp" />
  <div class="container">
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
        <c:if test="${'1' eq user.collectPermission}">
          <a class="btn submit-btn right" href="${root}/collect/edit">创建榜单</a>
        </c:if>
      </section>
      <section class="list">
        <ul class="list-grid cf">
          <c:choose>
          <c:when test="${not empty collects}">
          <c:forEach items="${collects}" var="collect">
          <li class="list-item">
            <div class="cover" style="background-image: url(${root}/imgbase/coverbg/${collect.coverImgPath})">
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
                  <li><a href="#" onclick="delCollect(${collect.collectid});">删除</a></li>
                  </c:if>
                </ul>
              </div>
            </div>
          </li>
          </c:forEach>
          </c:when>
          <c:otherwise>
          </c:otherwise>
          </c:choose>
        </ul>
      </section>
    </div>
  </div>

  <jsp:include page="../../FOOTER.jsp" />
  <script type="text/javascript">
  function delCollect(collectid) {
  	layer.confirm('您确定要删除该榜单吗？', {
  	    btn: ['确定','取消'] //按钮
  	}, function(){
  		$.get('${root}'+"/my/collects/delete", {
  			"collectid": collectid
  		}, function(response){
  			checkDataAndReload(response);
  			layer.msg("榜单删除成功");
  		});
  	}, function(){
  		layer.msg("榜单没有删除");
  	});
  	return false;
  }
  </script>
</body>
</html>