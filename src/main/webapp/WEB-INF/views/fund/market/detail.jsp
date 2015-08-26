<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="hfn" uri="/WEB-INF/hfn.tld"%>
<%@ taglib prefix="fund" uri="/WEB-INF/fund.tld"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<title>${fundMarket.fundName}【${fundMarket.fundCode}】 | 赚了没</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

<meta name="description" content="${fundMarket.fundName}，赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">
<meta name="keywords" content="${fundMarket.fundName}，赚了没-TOP|找到你喜欢的理财产品，榜单|赚了没-TOP">

</head>
<body id="home" class="notes-index">
  <jsp:include page="../../HEADER.jsp" />
  
  <div class="content row cf">
    <div class="forms">
      <div class="main-panel">
        <section class="product cf">
          <div class="fund-detail-panel">
            <input type="hidden" id="likePage" name="likePage" value="detail" />
            <div class="fund-name">
              <span>${fundMarket.fundName}（${fundMarket.fundCode}）</span>
              <c:if test="${not empty fundMarket.fundType}"><span class="fund-type">${fund:type(fundMarket.fundType)}</span></c:if>
              <c:if test="${'0' != fundMarket.fundRiskLevel}"><span class="fund-type">${fund:riskLevel(fundMarket.fundRiskLevel)}</span></c:if>
            </div>
            
            <div class="fund-market">
              <div class="fund-nav-panel">
                <table style="width: 100%;"><tr>
                  <td style="width: 30%;"><div class="fund-nav">
                    <span class="fund-nv-up">${fundMarket.currentNav}</span>
                    <br><span>单位净值 [${fundMarket.navDate}]</span>
                  </div></td>
                  <td style="width: 30%;"><div class="fund-nav-updown">
                    <span class="fund-nv-down">（${fundMarket.rateChange}）</span>
                    <br><span>涨跌幅</span>
                  </div></td>
                  <td style="width: 30%;"><div class="fund-nav-sort">
                    <span class="fund-nv-down">
                      <c:if test="${not empty fundMarket.fundSortThreeMonth}">
                      <span class="fund-nv-up">${fundMarket.fundSortThreeMonth}</span>
                      <span>/&nbsp;${fundMarket.fundTotalThreeMonth}</span>
                      </c:if>
                      <c:if test="${empty fundMarket.fundSortThreeMonth}">
                      <span>无</span>
                      </c:if>
                    </span><br><span>近3月排名</span>
                  </div></td>
                </tr></table>
              </div>
              <div class="fund-nav-info">
                <div class="fund-base">
                  <c:if test="${not empty fundMarket.fundRiseThreeMonth}"><span> 近3月：${fundMarket.fundRiseThreeMonth}&nbsp;&nbsp;</span></c:if>
                  <c:if test="${not empty fundMarket.fundRiseYear}"><span>近1年：${fundMarket.fundRiseYear}&nbsp;&nbsp;</span></c:if>
                  <c:if test="${not empty fundMarket.newScale}"><span>最新规模：${fundMarket.newScale}亿&nbsp;&nbsp;</span></c:if>
                  <c:if test="${not empty fundMarket.foundedDate}"><span>成立日期：${fundMarket.foundedDate}</span></c:if>
                </div>
              </div>
            </div>
          </div>
          
          <div class="zmd-votebar">
            <table><tr>
              <td width="50px">&nbsp;<br><button class="up" aria-pressed="false" title="赞同" 
                onclick="likeMarket('${fundMarket.fundCode}', '0')">
              <i class="icon vote-arrow"></i>
              <span class="label">赞同</span>
              <div id="likeScore" class="count" style="margin-top: 10px;">${fundMarket.likeScore}</div>
              </button></td>
              <td style="width:50px;">&nbsp;<br><button class="down" aria-pressed="false" 
                onclick="likeMarket('${fundMarket.fundCode}', '1')" title="反对，不会显示你的姓名">
              <div id="unLikeScore" class="countdown">${fundMarket.unLikeScore}</div>
              <i class="icon vote-arrowdown"></i>
              <span class="label">反对，不会显示你的姓名</span>
              </button></td>
            </tr></table>
          </div>
          
          <c:if test="${not empty prodLikes}">
          <div class="upvotes">
            <h2>${product.likeSum}人觉得很赞：</h2>
            <ul class="upvote-users cf">
            <c:forEach items="${prodLikes}" var="prodLike">
              <li class="product-avatar">
                <div class="user-image">
                  <a class="user-image-link" href="${root}/user/main?queryUserid=${prodLike.userid}" target="_blank">
                    <img class="avatar" height="60" width="60" 
                      <c:choose>
                      <c:when test="${not empty prodLike.socialPicPath}">src="${prodLike.socialPicPath}"</c:when>
                      <c:when test="${not empty prodLike.picPath}">src="${root}/user/avatar/${prodLike.picPath}"</c:when>
                      <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                      </c:choose> />
                  </a>
                </div>
                <div class="user-tooltip">
                  <a class="user-image-link" href="#">
                    <img class="avatar avatar-big" height="120" width="120"
                      <c:choose>
                      <c:when test="${not empty prodLike.socialPicPath}">src="${prodLike.socialPicPath}"</c:when>
                      <c:when test="${not empty prodLike.picPath}">src="${root}/user/avatar/${prodLike.picPath}"</c:when>
                      <c:otherwise>src="${root}/assets/img/logo/toplogo.png"</c:otherwise>
                      </c:choose> />
                  </a>
                  <h3 class="user-nickname">${prodLike.nickName}</h3>
                  <h4 class="user-title">${prodLike.userGroup} - ${prodLike.position}<br></h4>
                  <p class="user-bio">${prodLike.memo}</p>
                </div>
              </li>
            </c:forEach>
            </ul>
          </div>
          </c:if>
        
          <div class="share">
            <h3>分享到</h3>
            <div class="share-weibo">
              <a href="http://service.weibo.com/share/share.php?searchPic=false&amp;title=${fundMarket.fundName}[${fundMarket.fundCode}]&amp;url=http://www.zhuanlemei.com/fund/market/detail?fundCode=${fundMarket.fundCode}" target="_blank" title="点击分享到微博">
                <i class="svg-weibo"></i> <span>微博</span>
              </a>
            </div>
            <div class="share-wechat">
              <a title="微信扫码后分享" href="javascript:void(0)">
                <i class="svg-wechat"></i> <span>微信</span>
              </a>
              <div class="dropdown share-dropdown">
                <img height="156" src="http://s.jiathis.com/qrcode.php?url=http://www.zhuanlemei.com/fund/market/detail?fundCode=${fundMarket.fundCode}"
                   width="156" />
                <span>微信扫一扫：分享</span>
              </div>
            </div>
          </div>
        </section>
        
        <section class="comments row" id="comments">
          <h4>评论 (${fundMarket.commentSum})</h4>
          <c:if test="${empty user}">
          <form accept-charset="UTF-8" action="" class="simple_form comment" method="post">
            <a href="${root}/login/">登录后发表评论</a></form>
          </c:if>
          <c:if test="${not empty user}">
          <form accept-charset="UTF-8" action="${root}/comment/post" class="simple_form comment" method="post">
            <input id="fundCode" name="fundCode" type="hidden" value="${fundMarket.fundCode}" />
            <input id="commentType" name="commentType" type="hidden" value="fundMarket" />
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
    <aside class="aside-zlm">
      <jsp:include page="sort.jsp" />
    </aside>
  </div>

  <jsp:include page="../../FOOTER.jsp" />
  
</body>
</html>