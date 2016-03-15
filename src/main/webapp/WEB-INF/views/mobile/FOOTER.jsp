<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
</head>
<body>

  <footer id="footer">
    <p>
      ©上海里虎网络科技有限公司  2014-2015  All Rights Reserved. A 
      <a href="http://www.zhuanlemei.com/">zhuanlemei</a> product.
      <span>沪ICP备14033675号-2</span>
    </p>
    
    <!-- 
    <ul class="f-menu">
      <li><a href="">Home</a></li>
      <li><a href="">Dashboard</a></li>
      <li><a href="">Reports</a></li>
      <li><a href="">Support</a></li>
      <li><a href="">Contact</a></li>
    </ul>
     -->
  </footer>

  <!-- Page Loader -->
  <div class="page-loader">
    <div class="preloader pls-blue">
      <svg class="pl-circular" viewBox="25 25 50 50">
          <circle class="plc-path" cx="50" cy="50" r="20" />
      </svg>
      <p>正在加载数据...</p>
    </div>
  </div>

  <!-- Javascript Libraries -->
  <script type="text/javascript" src="${root}/assets/mobile/vendor/components/Waves/dist/waves.min.js"></script>
  <script type="text/javascript" src="${root}/assets/mobile/vendor/components/malihu-custom-scrollbar-plugin/jquery.mCustomScrollbar.concat.min.js"></script>
  <script type="text/javascript" src="${root}/assets/mobile/js/functions.js"></script>

  <!-- 百度分享 -->
  <script>
    var _hmt = _hmt || [];
    (function() {
      var hm = document.createElement("script");
      hm.src = "//hm.baidu.com/hm.js?98c61cccc0b766aeadc9cd6e2e3fb5b9";
      var s = document.getElementsByTagName("script")[0]; 
      s.parentNode.insertBefore(hm, s);
    })();
  </script>   
</body>
</html>