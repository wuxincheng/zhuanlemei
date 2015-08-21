<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>

<p>&nbsp;</p>

<div class="fund-sort">
  <span class="fund-nv-up">红榜</span>
</div>

<c:forEach items="${topRedMarkets}" var="topRedMarket" varStatus="s">
  <div class="fund-sort">${s.index+1}、${topRedMarket.fundName}</div>
</c:forEach>

<p>&nbsp;</p>
<p>&nbsp;</p>

<div class="fund-sort">
  <span class="fund-nv-down">绿榜</span>
</div>

<c:forEach items="${topGreenMarkets}" var="topGreenMarket" varStatus="s">
  <div class="fund-sort">${s.index+1}、${topGreenMarket.fundName}</div>
</c:forEach>

</html>