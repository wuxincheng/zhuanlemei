<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<%@ taglib prefix="fund" uri="/WEB-INF/fund.tld"%>
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
    <div class="content row cf">
      <div class="forms">
        <div class="main-panel">
          <section class="list-header row" style="max-width: 960px; left: -10px;">
            <div class="list-header-cover" style="
              <c:if test="${empty collect.bgColor}">background-image: url(${root}/collect/coverbg/${collect.coverImgPath});</c:if>
              <c:if test="${not empty collect.bgColor}">background-color: #${collect.bgColor};</c:if>">
            </div>
          </section>
        </div>
      </div>
      
      <div class="collect-left">
        <div class="collect-name">${collect.collectName}</div>
        <div class="collect-create">
          <div class="collect-creater">
            <ul class="product-meta left">
              <li class="product-avatar">
                <div class="user-image">
                  <a class="user-image-link" href="${root}/user/main?queryUserid=${product.userid}" target="_blank">
                    <img alt="0" class="avatar" height="60" width="60" 
                      <c:choose>
                      <c:when test="${not empty product.socialPicPath}">src="${product.socialPicPath}"</c:when>
                      <c:when test="${not empty product.picPath}">src="${root}/user/avatar/${product.picPath}"</c:when>
                      <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                      </c:choose> />
                  </a>
                </div>
                <div class="user-tooltip">
                  <a class="user-image-link" href="#">
                    <img alt="0" class="avatar avatar-big" height="120" width="120"
                      <c:choose>
                      <c:when test="${not empty product.socialPicPath}">src="${product.socialPicPath}"</c:when>
                      <c:when test="${not empty product.picPath}">src="${root}/user/avatar/${product.picPath}"</c:when>
                      <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                      </c:choose> />
                  </a>
                  <h3 class="user-nickname">${product.nickName}</h3>
                  <h4 class='user-title'>${product.userGroup} - ${product.position}<br></h4>
                  <p class="user-bio"> ${product.userMemo} </p>
                </div>
              </li>
              <li>
                <div class="collect-time">发布于 ${collect.updateTime}</div>
              </li>
            </ul>
          </div>
        </div>
        <div class="collect-memo">${collect.memo}</div>
      </div>
    </div>
    
    <div class="row">
      <div class="collect-hand">
        <ul class="upvote-users cf">
          <li class="product-avatar">
            <div class="zmd-votebar" style="width: 200px; padding-left: 50px; margin-bottom: 20px; margin-left: 20px;">
              <table><tr>
                <td width="50px">&nbsp;<br><button class="up" aria-pressed="false" title="赞同" 
                  onclick="likeCollect('${collect.collectid}', '0')">
                <i class="icon vote-arrow"></i>
                <span class="label">赞同</span>
                <div id="likeScore" class="count" style="margin-top: 10px;">${collect.likeScore}</div>
                </button></td>
                <td style="width:50px;">&nbsp;<br><button class="down" aria-pressed="false" 
                  onclick="likeCollect('${collect.collectid}', '1')" title="反对，不会显示你的姓名">
                <div id="unLikeScore" class="countdown">${collect.unLikeScore}</div>
                <i class="icon vote-arrowdown"></i>
                <span class="label">反对，不会显示你的姓名</span>
                </button></td>
              </tr></table>
            </div>
          </li>
          <li class="product-avatar">
            <div class="user-image" style="margin-top: -60px; margin-left: 30px;">
              <div class="list-fav-btn <c:if test="${not empty collectUser}">faved</c:if>" style="margin-bottom: 0px;">
                <c:if test="${empty collectUser}">
                <a class="btn " href="${root}/collect/collect?collectid=${collect.collectid}&userid=${user.userid}">收藏</a>
                </c:if>
                
                <c:if test="${not empty collectUser}">
                <a class="btn fav" href="${root}/collect/collect?collectid=${collect.collectid}&userid=${user.userid}">已收藏</a>
                </c:if>
                <span class="fav-number"><span class='total-count'>${collect.collectSum}</span> 人已收藏</span>
              </div>
            </div>
          </li>
          <li class="product-avatar" style="top: -37px;">
            <div class="share" style="border-bottom:0px; padding-bottom: 0px; margin-left: 100px;">
              <div class="share-weibo">
                  <a href="http://service.weibo.com/share/share.php?searchPic=false&amp;title=申万菱信量化小盘股票LOF[163110]&amp;url=http://www.zhuanlemei.com/fund/market/detail?fundCode=163110" target="_blank" title="点击分享到微博">
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
          </li>
        </ul>
      </div>
    </div>    
    
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
          <div class="collect-fund">
            <div class="fund-info">
              <div class="fund-name"><a href="${root}/fund/market/detail?fundCode=${fundMarket.fundCode}" target="_blank">${fundMarket.fundName}（${fundMarket.fundCode}）</a></div>
              <div class="fund-base">单位净值&nbsp;[${fundMarket.navDate}] <span class="fund-nv-up">${fundMarket.currentNav}</span><span class="fund-nv-down">（${fundMarket.rateChange}）</span></div>
              <div class="fund-base">
                <c:if test="${not empty fundMarket.newScale}">最新规模：${fundMarket.newScale}亿&nbsp;&nbsp;</c:if>
                <c:if test="${not empty fundMarket.fundManager}">基金经理：${fund:huntHigh(fundMarket.fundManager, keyword)}&nbsp;&nbsp;</c:if>
                <c:if test="${not empty fundMarket.fundCompany}">基金公司：${fund:huntHigh(fundMarket.fundCompany, keyword)}&nbsp;&nbsp;</c:if>
                <c:if test="${not empty fundMarket.foundedDate}"><span>成立日期：${fundMarket.foundedDate}</span></c:if>
              </div>
            </div>
          </div>
        </c:forEach>
        </c:when>
        <c:otherwise>
        </c:otherwise>
        </c:choose>
      </div>
    </div>
    
    <!-- 评论 --> 
    <div class="row" style="margin-top: 30px;">
      <section class="comments row" id="comments">
        <h4>评论 (${collect.commentSum})</h4>
        <c:if test="${empty user}">
        <form accept-charset="UTF-8" action="" class="simple_form comment" method="post">
          <a href="${root}/login/">登录后发表评论</a></form>
        </c:if>
        <c:if test="${not empty user}">
        <form accept-charset="UTF-8" action="${root}/comment/post" class="simple_form comment" method="post">
          <input id="collectid" name="collectid" type="hidden" value="${collect.collectid}" />
          <input id="commentType" name="commentType" type="hidden" value="collect" />
          <div class="form-group hidden comment_content">
          <input class="hidden form-control input-big" id="comment_content" name="content" type="hidden" />
          </div>
          <div contenteditable="true" class="input-big mention editable-comment form-control" 
            data-for="content" required="required" mentionable="true">
          </div>
          <input class="btn submit" name="commit" type="submit" value="发表评论" />
          <span class='help-inline'>支持Markdown语法</span>
        </form>
        </c:if>
        
        <!-- 评论 -->
        <c:if test="${not empty comments}">
        <c:forEach items="${comments}" var="comment">
        <div class="media comment" id="comment_25191">
          <div class="comment-avatar">
            <div class="user-image">
              <a class="user-image-link" href="${root}/user/main?queryUserid=${comment.userid}" target="_blank">
                <img class="avatar" height="60" width="60" 
                  <c:choose>
                  <c:when test="${not empty comment.socialPicPath}">src="${comment.socialPicPath}"</c:when>
                  <c:when test="${not empty comment.picPath}">src="${root}/user/avatar/${comment.picPath}"</c:when>
                  <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                  </c:choose> />
              </a>
            </div>
            <div class="user-tooltip">
              <a class="user-image-link" href="#">
                <img class="avatar avatar-big" height="120" width="120" 
                  <c:choose>
                  <c:when test="${not empty comment.socialPicPath}">src="${comment.socialPicPath}"</c:when>
                  <c:when test="${not empty comment.picPath}">src="${root}/user/avatar/${comment.picPath}"</c:when>
                  <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                  </c:choose> />
              </a>
              <h3 class="user-nickname">${comment.nickName}</h3>
              <h4 class='user-title'>${comment.userGroup} - ${comment.position}<br></h4>
              <p class="user-bio">${comment.userMemo}</p>
            </div>
          </div>
          <div class="media-body">
            <div class="comment-details">
              <div class='comment-meta cf'>
                <h3 class="user-nickname"><a href="#">${comment.nickName}</a></h3>
                <span class="user-bio">${comment.userMemo}</span>
              </div>
              <div class="comment-content">
                <p>${comment.content}</p>
                <em>${hfn:relativeDateFormat(comment.createTime)}</em>
              </div>
              <!-- 
              <p>- <a class="reply" href="">回复</a><span class="reply">(1)</span></p>
               -->
            </div>
          </div>
        </div>        
        </c:forEach>
        </c:if>
      
        <div id="mention_wrapper" data-note-id="14941">
          <input type="text" id="mention_user_selector">
        </div>
      </section>
    </div>
  </div>    

  <jsp:include page="../FOOTER.jsp" />
  <script type="text/javascript">
    function likeCollect(collectid, likeState) {
    	var url = "${root}/collect/like";
      	$.ajax({
          url : url, // 跳转到 action    
          data : {collectid:collectid,likeState:likeState},
          type : 'post',
          beforeSend:function(){
          },
          cache : false,
          dataType : 'json',
          success : function(data) {
              var result = data;
              if (!result.flag) {
            	  if (result.message != null) {
  	            	  alert(result.message);
            	  }
            	  return;
              }
              
          	  $("#likeScore").html(result.likeScore);
          	  $("#unLikeScore").html(result.unLikeScore);
          $(scorespan).text(result.score); // 产品关注度
          },
          error : function() {
            alert("友情提示：系统异常，请重试!");
          }
        });
    }
  </script>
</body>
</html>