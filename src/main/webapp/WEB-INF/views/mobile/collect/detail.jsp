<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<%@ taglib prefix="fund" uri="/WEB-INF/fund.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html style="font-size: 25.875px;">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${collect.collectName}&nbsp;&nbsp;最好玩的基金排行榜就在赚了没+www.zhuanlemei.com</title>
<meta charset="UTF-8">
<meta name="viewport"
  content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<meta name="format-detection" content="telephone=no">

<script async="" src="${root}/assets/vendor/mobile/js/analytics.js"></script>
<script type="text/javascript">
	// 转化rem到vm，主要是用来计算padding，margin，width等等
	;
	(function(win) {
		function setUnitA() {
			document.documentElement.style.fontSize = document.documentElement.clientWidth
					/ 16 + "px";
		}
		var h = null;
		window.addEventListener("resize", function() {
			clearTimeout(h);
			h = setTimeout(setUnitA, 300);
		}, false);
		setUnitA();
	})(window);
</script>

<script src="${root}/assets/vendor/mobile/js/redirect.js" type="text/javascript"></script>
<link href="${root}/assets/vendor/mobile/css/fund.css" media="all" rel="stylesheet" type="text/css">
<link href="${root}/assets/vendor/mobile/css/show.css" media="all" rel="stylesheet" type="text/css">
<meta content="authenticity_token" name="csrf-param">
<meta content="0NHL00yRE9MiwmISDmJC7SCOvoWJEpLAMT9qghUsUHA=" name="csrf-token">
</head>
<body class="mobile articles show">
  <script language="JavaScript" type="text/javascript">
  	var o = 'd' + 'i' + 'v', a = ' class="com-s' + 'eo" style=\'disp', b = 'lay:', c = 'none\'';
  	document.write("<" + o + a + b + c + ">");
  </script>
  <div class="com-seo" style="display: none">
    <script language="JavaScript" type="text/javascript">
		var o = 'di' + 'v';
		document.write("</"+o+">");
	</script>
  </div>

  <div class="menu-overlay"></div>

  <div class="page-content">
    <div class="com-article-detail short" data-categoryid="62" data-initialized="true" data-guid="7">
      <div class="article-detail-hd">
        <h1 class="title">${collect.collectName}</h1>
        <div class="author-date clearfix">
          <div class="author clearfix">
            <span class="avatar x25 circle"><img
              <c:choose>
              <c:when test="${not empty userQuery.socialPicPath}">src="${userQuery.socialPicPath}"</c:when>
              <c:when test="${not empty userQuery.picPath}">src="${root}/user/avatar/${userQuery.picPath}"</c:when>
              <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
              </c:choose> /></span>
            <span class="name">${userQuery.nickName}</span>
          </div>
          <span class="date smart-date">${hfn:relativeDateFormat(collect.updateTime)}</span>
        </div>
        <div class="banner">
          <img 
            <c:choose>
            <c:when test="${not empty collect.coverImgPath}">src="${root}/collect/coverbg/${collect.coverImgPath}"</c:when>
            <c:when test="${empty collect.coverImgPath}">src="${collect.bgColor}"</c:when>
            <c:otherwise></c:otherwise>
            </c:choose> />
        </div>
      </div>
      <div class="article-detail-bd">
        <div class="excerpt">${collect.recommend}</div>
          <div class="public-fund-list">
            <c:choose>
            <c:when test="${not empty fundMarkets}">
              <c:forEach items="${fundMarkets}" var="fundMarket">
                <div class="public_fund_item">
                  <div><h3 class="name">${fundMarket.fundName}（${fundMarket.fundCode}）</h3></div>
                  <div class="public-fund-type">
                    <span class="gray9">[混合型]</span>
                  </div>
                  <div class="rate cf">
                    <ul class="fl">
                      <li>近6月收益</li>
                      <li><span class="cGreen">${fundMarket.rateChange}</span></li>
                    </ul>
                    <ul class="fl" style="padding-left: 20px;">
                      <li>最新净值</li>
                      <li><span>${fundMarket.currentNav}</span></li>
                    </ul>
                    <ul class="fr" style="padding-top: 10px;">
                      <li style="margin-left:20px;"><a class="btn" href="#">关注</a></li>
                    </ul>
                  </div>
                </div>
              </c:forEach>
            </c:when>
            <c:otherwise>
              <div style="color: #5AABE7; font-weight: bold; margin-top: 30px;">目前还没有添加任何基金产品</div>
            </c:otherwise>
          </c:choose>
        </div>
      </div>
    </div>
  </div>

  <div class="com-related-comments" data-id="18650" data-initialized="true" data-guid="13">
    <div class="gap"></div>
    <div class="related-comments-bd">
      <p class="count" style="visibility: visible;">评论(${collect.commentSum})</p>
      <div class="items comments">
        <c:if test="${not empty comments}">
        <c:forEach items="${comments}" var="comment">
        <div class="com-comment clearfix" data-commentid="154030">
          <div class="comment-left">
            <a href="javascript:void(0)" class="avatar circle x35">
              <img 
                <c:choose>
                <c:when test="${not empty comment.socialPicPath}">src="${comment.socialPicPath}"</c:when>
                <c:when test="${not empty comment.picPath}">src="${root}/user/avatar/${comment.picPath}"</c:when>
                <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                </c:choose> />
            </a>
          </div>
          <div class="comment-right">
            <div class="name-date">
              <a href="javascript:void(0)" class="name">${comment.nickName}</a>
              <span class="date smart-date">${hfn:relativeDateFormat(comment.createTime)}</span>
            </div>
            <p class="comment-text">${comment.content}</p>
          </div>
        </div>
        </c:forEach>
        </c:if>
        <c:if test="${empty comments}"><div class="comment-tip">暂无评论</div></c:if>
      </div>
    </div>
  </div>

  <!-- component comment Popup -->
  <div class="popup com-comment-popup" data-initialized="true" data-guid="2">
    <div class="comment-popup-hd">
      <div class="left">
        <a href="http://www.qdaily.com/mobile/articles/18650.html#" class="link close-popup">取消</a>
      </div>
      <div class="center">我有意见</div>
      <div class="right">
        <a href="http://www.qdaily.com/mobile/articles/18650.html#" class="link submit">发布</a>
      </div>
    </div>
    <div class="comment-popup-bd">
      <!-- 评论form表单 -->
      <form action="http://www.qdaily.com/" method="post">
        <textarea name="content" placeholder="我有意见"></textarea>
      </form>
    </div>
  </div>
</body>
</html>