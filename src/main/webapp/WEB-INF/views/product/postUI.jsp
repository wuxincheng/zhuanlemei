<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index">
  <jsp:include page="../HEADER.jsp" />
  <div class="container">
    <div class="content row row cf">
      <div class="forms">
        <form accept-charset="UTF-8" action="${root}/product/doPost" class="form-horizontal" id="new_note"
          method="post">
          <div class="form-group">
            <label>产品名称</label>
            <input class="form-control input-small" id="fundName" maxlength="50"
              name="fundName" placeholder="输入基金名称" type="text" />
          </div>
          
          <div class="form-group">
            <label>产品代码</label>
            <input class="form-control input-small" id="fundCode" maxlength="10"
              name="fundCode" placeholder="输入基金代码" type="text" />
          </div>
          
          <div class="form-group">
            <label>最新价</label>
            <input class="form-control input-small" id="latestPrice" maxlength="10"
              name="latestPrice" placeholder="输入最新价" type="text" />
          </div>
          
          <div class="form-group">
            <label>涨跌幅</label>
            <input class="form-control input-small" id="changePercent" maxlength="10"
              name="changePercent" placeholder="输入涨跌幅" type="text" />
          </div>
          
          <div class="form-group">
            <label>成本价</label>
            <input class="form-control input-small" id="costPrice" maxlength="10"
              name="costPrice" placeholder="输入成本价" type="text" />
          </div>
          
          <div class="form-group">
            <label>盈亏比例</label>
            <input class="form-control input-small" id="profitLossRat" maxlength="10"
              name="profitLossRat" placeholder="输入盈亏比例" type="text" />
          </div>
          
          <div class="form-group">
            <label>仓位</label>
            <input class="form-control input-small" id="positionPercent" maxlength="10"
              name="positionPercent" placeholder="输入仓位" type="text" />
          </div>
          
          <!-- 
          <div class="form-group">
            <label class="text required">一句话描述这个产品</label>
            <textarea class="form-control input-big" id="memo" name="memo"></textarea>
            <p class="help-block">请精简描述, 36字以内</p>
          </div>
           -->
          
          <input type="hidden" name="collectid" id="collectid" value="${collectid}" />
          <input class="btn submit" name="commit" type="submit" value="提交" />
        </form>
      </div>
      
      <aside class="aside">
        <p>&nbsp;</p>
      </aside>

    </div>
  </div>

  <jsp:include page="../FOOTER.jsp" />

</body>
</html>