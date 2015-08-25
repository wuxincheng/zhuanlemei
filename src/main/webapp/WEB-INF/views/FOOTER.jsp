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
    $('#keyword').bind('input propertychange', function() {
      $(".keywordPanel .items").css("left", "0px");
      
      $("#keyword").bind("focus", function() {
        var time = setInterval(filter_staff_from_exist, 100);
        $(this).bind("blur", function() {
          clearInterval(time);
        });
      });

      $(".keywordPanel .items").on("click", "a", function() {
        var fundCode = $(this).attr("fundCode");
        var fundName = $(this).find(".fn").text();
        $("[name=fundCode]").val(fundCode);
        $("#keyword").val(fundName);
        str = fundName;
        $(".keywordPanel .items").hide();
      });

      $(".keywordPanel .items").on("mouseover", "a", function() {
        var link = $(this);
        $(".keywordPanel .items a").each(function(i, o) {
          if ($(o).is(link)) {
            fundIndex = i;
            return;
          }
        });

        $(".keywordPanel .items a").removeClass("hover");
        $(this).addClass("hover").focus();
      });

      var funcKey = function(i) {
        var fundCount = $(".keywordPanel .items a").length;
        if (fundIndex < 0) {
          fundIndex = 0;
        } else {
          fundIndex += i;
          if (fundIndex < 0) {
            fundIndex = 0;
          }
          if (fundIndex >= fundCount) {
            fundIndex = 0;
          }
        }

        $(".keywordPanel .items a").removeClass("hover");
        $(".keywordPanel .items a").eq(fundIndex).addClass("hover").focus();
      };
      
      $("#keyword").keydown(function(e) {
        switch (e.which) {
        case 40://下
          funcKey(2);
          return false;
        }
      });

      $(".keywordPanel .items").keydown(function(e) {
        switch (e.which) {
        case 37://左
          funcKey(-1);
          return false;
        case 38://上
          funcKey(-1);
          return false;
        case 39://右
          funcKey(1);
          return false;
        case 40://下
          funcKey(1);
          return false;
        }
      });
      
      var keyword = $("#keyword").val();
      if (keyword != null && keyword.length >= 2) {
        var url = "${root}/fund/info/query";
          $.ajax({
            url : url, // 跳转到 action
            data : {keyword:keyword},
            type : 'post',
            beforeSend:function(){
            },
            cache : false,
            dataType : 'json',
            success : function(data) {
                if (data.length > 0) {
                  $(".keywordPanel .items").hide().empty();
                  
                  $(".keywordPanel .items").append("<ul></ul>");
                  $.each(data, function(i, n) {
                        var fundName = n.fundName.replace(keyword, "<span class='selected'>" + keyword
                          + "</span>");
                        var fundCode = n.fundCode.replace(keyword, "<span class='selected'>" + keyword
                          + "</span>");
                        $(".keywordPanel .items ul").append(
                          "<li><a href='javascript:void(0)' fundCode='" + n.fundCode
                            + "' hidefocus='true'><span class='fn'>" + fundName
                            + "</span><span class='fc'>" + fundCode + "</span></a></li>");
                      });
                    $(".keywordPanel .items").show();
                }
            },
            error : function() {
              alert("友情提示：系统异常，请重试!");
            }
          });
      }
    });
  </script>
  
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