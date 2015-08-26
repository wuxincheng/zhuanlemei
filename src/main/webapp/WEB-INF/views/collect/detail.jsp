<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>TOP - 榜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta name="description" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index">
  <jsp:include page="../HEADER.jsp" />
  <div>

    <section class="list-header row" style="margin-top: 25px; max-width: 960px; left: -10px;">
      <div class="list-header-cover" style="
        <c:if test="${empty collect.bgColor}">background-image: url(${root}/collect/coverbg/${collect.coverImgPath});</c:if>
        <c:if test="${not empty collect.bgColor}">background-color: #${collect.bgColor};</c:if>">
      </div>
      <div class="list-header-info row">
        <h2>${collect.collectName}</h2>
        <div class="list-stats">
          <span></span><span>最后更新于 ${collect.updateTime}</span>
        </div>
        <p class="list-bio">${collect.memo}</p>
        <div class="list-header-meta cf">
          <div class="list-fav-btn <c:if test="${not empty collectUser}">faved</c:if>">
            <c:if test="${empty collectUser}">
            <a class="btn " href="${root}/collect/collect?collectid=${collect.collectid}&userid=${user.userid}">收藏</a>
            </c:if>
            
            <c:if test="${not empty collectUser}">
            <a class="btn fav" href="${root}/collect/collect?collectid=${collect.collectid}&userid=${user.userid}">已收藏</a>
            </c:if>
            <span class="fav-number"><span class='total-count'>${collect.collectSum}</span> 人已收藏</span>
          </div>
          <div class="share">
            <div class="share-weibo">
              <a href="http://service.weibo.com/share/share.php?searchPic=false&amp;title=${collect.collectName} - ${collect.memo}&amp;url=http://www.zhuanlemei.com/top/collect/detail?collectid=${collect.collectid}" target="_blank" title="点击分享到微博">
                <i class="svg-weibo"></i> <span>微博</span>
              </a>
            </div>
            <div class="share-wechat">
              <a title="微信扫码后分享" href="javascript:void(0)">
                <i class="svg-wechat"></i> <span>微信</span>
              </a>
              <div class="dropdown share-dropdown">
                <img height="156" src="http://s.jiathis.com/qrcode.php?url=http://www.zhuanlemei.com/top/collect/detail?collectid=${collect.collectid}" width="156" />
                <span>微信扫一扫：分享</span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
    
    
    <c:if test="${not empty collect.recommend}">
    <div class="container row">
      <div class="product-item">
        <div class="posts-group cf">
          <span class="post-tagline">${collect.recommend}</span>
        </div>
      </div>
    </div>
    </c:if>

    
    <div class="content row ">
      <section class="main-header cf row">
        <div class="list-tip left">
          <p><p>还有更好的产品没出现在这里？点击右边的按钮添加吧！</p>
        </div>
        <a class="btn submit-btn right" href="${root}/product/postUI?collectid=${collect.collectid}">补充好产品</a>
      </section>
      <div>
        <input type="hidden" id="likePage" name="likePage" value="list" />
        <c:choose>
        <c:when test="${not empty fundMarkets}">
        <c:forEach items="${fundMarkets}" var="fundMarket">
          <div class="fund-panel">
            <div class="zm-votebar goog-scrollfloater">
              <button class="up" aria-pressed="false" title="赞同"
                onclick="likeMarket('${fundMarket.fundCode}', '0')">
              <i class="icon vote-arrow"></i>
              <span class="label">赞同</span>
              <div id="${fundMarket.fundCode}count" class="count" style="margin-top: 10px;">${fundMarket.likeScore}</div>
              </button>
              <button class="down" aria-pressed="false" title="反对，不会显示你的姓名"
                onclick="likeMarket('${fundMarket.fundCode}', '1')">
              <div id="${fundMarket.fundCode}countdown" class="countdown">${fundMarket.unLikeScore}</div>
              <i class="icon vote-arrowdown"></i>
              <span class="label">反对，不会显示你的姓名</span>
              </button>
            </div>
            <div class="fund-info">
              <div class="fund-name"><a href="${root}/fund/market/detail?fundCode=${fundMarket.fundCode}" target="_blank">${fundMarket.fundName}（${fundMarket.fundCode}）</a></div>
              <div class="fund-base">单位净值&nbsp;[${fundMarket.navDate}] <span class="fund-nv-up">${fundMarket.currentNav}</span><span class="fund-nv-down">（${fundMarket.rateChange}）</span></div>
              <div class="fund-base">最新规模：${fundMarket.newScale}亿&nbsp;&nbsp;成立日期：${fundMarket.foundedDate}&nbsp;&nbsp;基金经理：${fundMarket.fundManager}</div>
              <div class="fund-base">（${fundMarket.commentSum}人评价）</div>
            </div>
          </div>
        </c:forEach>
        </c:when>
        <c:otherwise>
        </c:otherwise>
        </c:choose>
      </div>
    </div>
  </div>    

  <jsp:include page="../FOOTER.jsp" />
  <script type="text/javascript">
    function likeProduct (prodid) {
      var url = "${root}/product/like";
      
      $.ajax({
        url : url, // 跳转到 action    
        data : {prodid : prodid},
        type : 'post',
        beforeSend:function(){
        },
        cache : false,
        dataType : 'json',
        success : function(data) {
        var result = data;
        var clazz = 'voted'; // 样式
        var divname = '#prodlike'+result.prodid; // 产品div
        var scorespan = '#span'+result.prodid; // 产品关注度div
        if ('1' == result.flag) { // 点赞标志
          $(divname).addClass(clazz);
        } else {
          $(divname).removeClass(clazz);
        }
        $(scorespan).text(result.score); // 产品关注度
        },
        error : function() {
          alert("友情提示：您还未登录!");
        }
      });
    }  
  </script>
</body>
</html>