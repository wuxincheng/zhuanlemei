<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">

<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/toplogo.png" type="image/x-icon" rel="shortcut icon" />

<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

</head>
<body>
  <jsp:include page="../../HEADER.jsp" />
  
  <div class="container">
  
    <div class="row">
      <div class="col-md-8">
        <c:choose>
        <c:when test="${not empty pager.fundMarkets}">
        <c:forEach items="${pager.fundMarkets}" var="fundMarket">
          <div class="fund-panel">
            <div class="zm-votebar goog-scrollfloater">
              <button class="up" aria-pressed="false" title="赞同">
              <i class="icon vote-arrow"></i>
              <span class="label">赞同</span>
              <span class="count">13K</span>
              </button>
              <button class="down" aria-pressed="false" title="反对，不会显示你的姓名">
              <i class="icon vote-arrow"></i>
              <span class="label">反对，不会显示你的姓名</span>
              </button>
            </div>
            <div class="fund-info">
              <div class="fund-name"><a href="${root}/fund/market/detail?fundCode=${fundMarket.fundCode}" target="_blank">${fundMarket.fundName}</a></div>
              <div class="fund-base">单位净值&nbsp;[${fundMarket.navDate}] <span class="fund-nv-up">${fundMarket.currentNav}</span><span class="fund-nv-down">（${fundMarket.rateChange}）</span></div>
              <div class="fund-base">最新规模：${fundMarket.newScale}亿&nbsp;&nbsp;成立日期：${fundMarket.foundedDate}&nbsp;&nbsp;基金经理：${fundMarket.fundManager}</div>
              <div class="fund-base">（5,7043人评价）</div>
            </div>
          </div>
        </c:forEach>
        
        <ul class="pager">
          <li
            <c:if test="${'1' eq pager.currentPage}">class="disabled"</c:if>>
            <a
            <c:if test="${pager.currentPage > 1}">href="${root}/fund/market/list?currentPage=1"</c:if>>首页</a>
          </li>
  
          <li
            <c:if test="${'1' eq pager.currentPage}">class="disabled"</c:if>>
            <a
            <c:if test="${pager.currentPage > 1}">href="${root}/fund/market/list?currentPage=${pager.currentPage-1}"</c:if>>上一页</a>
          </li>
  
          <li
            <c:if test="${pager.lastPage eq pager.currentPage}">class="disabled"</c:if>>
            <a
            <c:if test="${pager.currentPage < pager.lastPage}">href="${root}/fund/market/list?currentPage=${pager.currentPage+1}"</c:if>>下一页</a>
          </li>
  
          <li
            <c:if test="${pager.lastPage eq pager.currentPage}">class="disabled"</c:if>>
            <a
            <c:if test="${pager.currentPage < pager.lastPage}">href="${root}/fund/market/list?currentPage=${pager.lastPage}"</c:if>>尾页</a>
          </li>
  
          <li class="">&nbsp;</li>
          <li class=""><strong>${pager.currentPage}/${pager.lastPage}</strong></li>
          <li class="">&nbsp;</li>
        </ul>
        
        </c:when>
        <c:otherwise>
        </c:otherwise>
        </c:choose>
      </div>
      
      <div class="col-md-4">
        <p>&nbsp;</p>
        <span class="fund-nv-up">红榜</span>
        <div class="fund-sort">1、富国中证军工指数分级</div>
        <div class="fund-sort">2、富国中证军工指数分级</div>
        <div class="fund-sort">3、富国中证军工指数分级</div>
        <div class="fund-sort">4、富国中证军工指数分级</div>
        <div class="fund-sort">5、富国中证军工指数分级</div>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <span class="fund-nv-down">绿榜</span>
        <div class="fund-sort">1、富国中证军工指数分级</div>
        <div class="fund-sort">2、富国中证军工指数分级</div>
        <div class="fund-sort">3、富国中证军工指数分级</div>
        <div class="fund-sort">4、富国中证军工指数分级</div>
        <div class="fund-sort">5、富国中证军工指数分级</div>
     </div>
    </div>
    
  </div>
  
  <jsp:include page="../../FOOTER.jsp" />

</body>
</html>