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
        <form accept-charset="UTF-8" action="${root}/product/doPost" class="simple_form new_note" id="new_note"
          method="post">
          <div style="display: none">
            <input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token"
              type="hidden" value="UPiDrTAjt2KU/pN2Pk67fOAFuScLNlZ77yx8CJ+TfSE=" />
          </div>

          <div class="form-group hidden note_source">
            <input class="hidden form-control" id="note_source" name="note[source]" type="hidden"
              value="direct" />
          </div>
          <div class="form-group string required note_title">
            <label class="string required" for="note_title">产品名称</label><input aria-required="true"
              autofocus="autofocus" class="string required form-control input-small" id="prodName"
              name="prodName" placeholder="如：Startup Base" required="required" type="text" />
          </div>
          <div class="form-group url required note_url">
            <label class="url required" for="note_url">产品官网</label><input aria-required="true"
              class="string url required form-control input-small" id="prodUrl" name="prodUrl"
              placeholder="以 http:// 开头的有效网址" required="required" type="url" />
          </div>
          <div class="form-group text required note_summary">
            <label class="text required" for="note_summary">一句话描述这个产品</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="memo" name="memo" required="required">
</textarea>
            <p class="help-block">请精简描述, 36字以内</p>
          </div>
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