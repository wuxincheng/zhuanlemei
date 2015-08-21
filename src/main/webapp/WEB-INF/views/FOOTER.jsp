<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
</head>
<body>

  <footer class="footer">
    <div class="text-center">
      <div class="copyright">
        <p>
          © 2014-2015 <strong>TOP</strong>. All Rights Reserved. A <a href="http://www.zhuanlemei.com/">zhuanlemei</a> product.
        </p>
      </div>
    </div>
  </footer>
  <script type="text/javascript">
    function likeMarket(fundCode, likeState) {
    	var url = "${root}/fund/market/like";
      	$.ajax({
          url : url, // 跳转到 action    
          data : {fundCode:fundCode,likeState:likeState},
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
    
              var likePage = $("#likePage").val();
              if ("list" == likePage) {
            	  $("#"+result.fundCode+"count").html(result.likeScore);
            	  $("#"+result.fundCode+"countdown").html(result.unLikeScore);
              }
              
			  if ("detail" == likePage) {
            	  $("#likeScore").html(result.likeScore);
            	  $("#unLikeScore").html(result.unLikeScore);
              }
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