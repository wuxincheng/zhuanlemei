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

</head>
<body id="home" class="notes-index">
  <div class="container">

    <jsp:include page="../HEADER.jsp" />

    <div class="content row ">
      <section class="product cf">
        <ul class="product-list">
          <li class="product-item">
            <div class="posts-group cf row">
              <div class="upvote  ">
                  <a class="upvote-link vote-up" href="/users/sign_in?ok_url=%2Fposts%2F14941">
                    <i class="upvote-arrow"></i>
                    <span class="vote-count">27</span>
                  </a>        
              </div>
              <div class="product-url">
                <a class="post-url" href="/posts/14941/hit" ref="nofollow" target="_blank" title="App Store">${product.prodName}</a>
                <br>
                <span class="post-tagline">${product.memo}</span>
              </div>
              <ul class="product-meta right">
                <li class="product-avatar">
                  <div class="user-image">
                    <a class="user-image-link" href="/users/2673">
                      <img alt="0" class="avatar" height="60" src="http://wx.qlogo.cn/mmopen/t5Vs01SBdHex8k5QQQeicibDrAjI5G6R0NOMXOO6uGRCff12vSBrXxt4yvKDrVVK4MQpXnPRobtg61nllz6jajBw/0" width="60" />
                    </a>
                  </div>
                  <div class="user-tooltip">
                    <a class="user-image-link" href="/users/2673">
                      <img alt="0" class="avatar avatar-big" height="120" src="http://wx.qlogo.cn/mmopen/t5Vs01SBdHex8k5QQQeicibDrAjI5G6R0NOMXOO6uGRCff12vSBrXxt4yvKDrVVK4MQpXnPRobtg61nllz6jajBw/0" width="120" />
                    </a>
                    <h3 class="user-nickname">徐奕奕奕奕</h3>
                    <p class="user-bio"> 有好产品，别出声</p>
                  </div>
                </li>
              </ul>
            </div>
          </li>
        </ul>
      
        <div class="upvotes">
          <h2>7人觉得很赞：</h2>
          <ul class="upvote-users cf">
            <li class="product-avatar">
              <div class="user-image">
                <a class="user-image-link" href="/users/171">
                  <img alt="0" class="avatar" height="60" src="http://wx.qlogo.cn/mmopen/2pm5Nb2cMaNmdZ87OJZicZExoMfnYtEN29l7k5He9v6x8EjaxV9mrY8Q9mAqelmAXicx4QJ0qXnujzd4k5eRfbYg/0" width="60" />
                </a>
              </div>
              <div class="user-tooltip">
                <a class="user-image-link" href="/users/171">
                  <img alt="0" class="avatar avatar-big" height="120" src="http://wx.qlogo.cn/mmopen/2pm5Nb2cMaNmdZ87OJZicZExoMfnYtEN29l7k5He9v6x8EjaxV9mrY8Q9mAqelmAXicx4QJ0qXnujzd4k5eRfbYg/0" width="120" />
                </a>
                <h3 class="user-nickname">Allen朝辉</h3>
                <h4 class='user-title'>Biu App - Designer<br></h4>
                <p class="user-bio"> 微信公众号: Emoji_News</p>
              </div>
            </li>
          </ul>
        </div>
      
        <div class="share">
          <h3>分享到</h3>
          <div class="share-weibo">
            <a href="http://service.weibo.com/share/share.php?searchPic=false&amp;title=${product.prodName} - ${product.memo}&amp;url=http://www.kaffeedaily.com/top/product/detail?prodid=${product.prodid}" target="_blank" title="点击分享到微博">
              <i class="svg-weibo"></i> <span>微博</span>
            </a>
          </div>
          <div class="share-wechat">
            <a title="微信扫码后分享" href="javascript:void(0)">
              <i class="svg-wechat"></i> <span>微信</span>
            </a>
            <div class="dropdown share-dropdown">
              <img height="156" src="http://s.jiathis.com/qrcode.php?url=http://www.kaffeedaily.com/top/product/detail?prodid=${product.prodid}" width="156" />
              <span>微信扫一扫：分享</span>
            </div>
          </div>
        </div>
      </section>
      
      <section class="comments row" id="comments">
        <h4>评论 (1)</h4>
        <c:if test="${empty user}">
        <form accept-charset="UTF-8" action="/posts/14941/comments" class="simple_form comment" method="post">
          <a href="${root}/login/">登录后发表评论</a></form>
        </c:if>
        <c:if test="${not empty user}">
        <form accept-charset="UTF-8" action="${root}/comment/post" class="simple_form comment" method="post">
          <input id="productid" name="productid" type="hidden" value="${product.prodid}" />
          <div style="display:none">
            <input name="utf8" type="hidden" value="&#x2713;" />
            <input name="authenticity_token" type="hidden" value="ANEyEpJJ68CL9sOaM+TBgWkLVrJ+y6mWIHYHa5OiijI=" />
          </div>
          <input id="ok_url" name="ok_url" type="hidden" value="/posts/14905#comments" />
          <div class="form-group hidden comment_content">
          <input class="hidden form-control input-big" id="comment_content" name="content" type="hidden" />
          </div>
          <div contenteditable="true" class="input-big mention editable-comment form-control" disabled="disabled"
            data-for="content" required="required" mentionable="true">
          </div>
          <input class="btn submit" name="commit" type="submit" value="发表评论" />
          <span class='help-inline'>支持Markdown语法</span>
        </form>
        </c:if>
        
        <!-- 评论 -->
        <div class="media comment" id="comment_25191">
          <div class="comment-avatar">
            <div class="user-image">
              <a class="user-image-link" href="/users/5133">
                <img alt="Fa10347b 7a54 42dc 9376 1bf0d7c219bb" class="avatar" height="60" src="https://rs-images.b0.upaiyun.com/uploads/user/avatar/5133/fa10347b-7a54-42dc-9376-1bf0d7c219bb.png!50x50" width="60" />
              </a>
            </div>
            <div class="user-tooltip">
              <a class="user-image-link" href="/users/5133">
                <img alt="Fa10347b 7a54 42dc 9376 1bf0d7c219bb" class="avatar avatar-big" height="120" src="https://rs-images.b0.upaiyun.com/uploads/user/avatar/5133/fa10347b-7a54-42dc-9376-1bf0d7c219bb.png!160x160" width="120" />
              </a>
              <h3 class="user-nickname">iamjohnny</h3>
              <h4 class='user-title'>邪恶组织 - 卧底<br></h4>
              <p class="user-bio">If anyone can have it, I don&#39;t want it!</p>
            </div>
          </div>
          <div class="media-body">
            <div class="comment-details">
              <div class='comment-meta cf'>
                <h3 class="user-nickname"><a href="/users/5133">iamjohnny</a></h3>
                <span class="user-bio">If anyone can have it, I don&#39;t want it!</span>
              </div>
              <div class="comment-content">
                <p>会被微信封吧</p>
                <em>5 小时前</em>
              </div>
              <p>- <a class="reply" href="">回复</a><span class="reply">(1)</span></p>
            </div>
            <div class="replies">
              <form accept-charset="UTF-8" action="/posts/15011/comments/25191/reply" class="simple_form hide reply" method="post">
                <div style="display:none">
                  <input name="utf8" type="hidden" value="&#x2713;" />
                  <input name="authenticity_token" type="hidden" value="4weD74pwLj3ICENu5xmiHrB85fTyvzXm3fPBLizkj0E=" />
                </div>
                <div class="form-group hidden comment_content">
                  <input class="hidden form-control input-big" id="comment_content" name="comment[content]" type="hidden" />
                </div>
                <div contenteditable="true" class="input-big mention editable-comment form-control reply"
                  data-for="comment[content]" required="required" mentionable="true">
                </div>
                <input class="btn submit" name="commit" type="submit" value="发表回复" />
                <span class='help-inline'>支持Markdown语法</span>
                <br><br>
              </form>
              <div class="media comment" id="comment_25221">
                <div class="comment-avatar">
                  <div class="user-image">
                    <a class="user-image-link" href="/users/4505"> <img
                      alt="Aa183ee0 dd8f 46d5 a84d 36f18f6b0efb" class="avatar" height="60"
                      src="https://rs-images.b0.upaiyun.com/uploads/user/avatar/4505/aa183ee0-dd8f-46d5-a84d-36f18f6b0efb.jpg!50x50"
                      width="60" />
                    </a>
                  </div>
                  <div class="user-tooltip">
                    <a class="user-image-link" href="/users/4505">
                      <img alt="Aa183ee0 dd8f 46d5 a84d 36f18f6b0efb" class="avatar avatar-big" height="120" width="120"
                        src="https://rs-images.b0.upaiyun.com/uploads/user/avatar/4505/aa183ee0-dd8f-46d5-a84d-36f18f6b0efb.jpg!160x160" />
                    </a>
                    <h3 class="user-nickname">黑柴</h3>
                    <h4 class='user-title'>BOARDING - Editor<br></h4>
                    <p class="user-bio">You know nothing</p>
                  </div>
                </div>
                <div class="media-body">
                  <div class="comment-details">
                    <div class='comment-meta cf'>
                      <h3 class="user-nickname">
                        <a href="/users/4505">黑柴</a>
                      </h3>
                      <span class="user-bio"> You know nothing </span>
                    </div>
                    <div class="comment-content">
                      <p>Github 上说抓取的是搜狗的微信搜索那里的结果内容，除非他们屏蔽掉这些内容或者出个什么禁止抓取的协议</p>
                      <em>4 小时前</em>
                    </div>
                    <p></p>
                  </div>
                  <div class="replies"></div>
                </div>
              </div>
            </div>
          </div>
        </div>
      
        <div id="mention_wrapper" data-note-id="14941">
          <input type="text" id="mention_user_selector">
        </div>
      </section>
    </div>
  </div>

  <jsp:include page="../FOOTER.jsp" />

</body>
</html>