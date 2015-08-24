<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<section class="featured cf">
  <a class="featured-item" href="http://www.howbuy.com/mobile/zsjj/"
    style="background-image: url(${root}/assets/images/ad/ad1.png)"
    target="_blank">
    <div class="featured-meta">
      <h3>掌上基金</h3>
      <p>人人都是基金经理</p>
    </div>
  </a>
</section>

<p>&nbsp;</p>

<div class="fund-sort">
  <span class="fund-nv-up">红榜</span>
</div>

<c:forEach items="${topRedMarkets}" var="topRedMarket" varStatus="s">
  <div class="fund-sort">
    <a href="${root}/fund/market/detail?fundCode=${topRedMarket.fundCode}" target="_blank">${s.index+1}、${topRedMarket.fundName}</a>
  </div>
</c:forEach>

<p>&nbsp;</p>
<p>&nbsp;</p>

<div class="fund-sort">
  <span class="fund-nv-down">绿榜</span>
</div>

<c:forEach items="${topGreenMarkets}" var="topGreenMarket" varStatus="s">
  <div class="fund-sort">
    <a href="${root}/fund/market/detail?fundCode=${topGreenMarket.fundCode}" target="_blank">${s.index+1}、${topGreenMarket.fundName}</a>
  </div>
</c:forEach>

</html>