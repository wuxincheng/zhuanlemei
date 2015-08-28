<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<title>TOP | 创建榜单</title>

<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="icon" />
<link href="${root}/assets/img/logo/logoEN.png" type="image/x-icon" rel="shortcut icon" />

</head>
<body id="home" class="notes-index">
  <jsp:include page="../HEADER.jsp" />
  <div class="container">

    <div class="content row row cf">
      <div class="forms">
        <form accept-charset="UTF-8" action="${root}/collect/create" class="simple_form new_note"
          method="post" enctype="multipart/form-data">
          <div style="display: none">
            <input name="utf8" type="hidden" value="&#x2713;" /><input name="authenticity_token"
              type="hidden" value="UPiDrTAjt2KU/pN2Pk67fOAFuScLNlZ77yx8CJ+TfSE=" />
          </div>

          <div class="form-group hidden note_source">
            <input class="hidden form-control" id="note_source" name="note[source]" type="hidden"
              value="direct" />
          </div>
          <div class="form-group string required note_title">
            <input type="hidden" id="userid" name="userid" value="${userid}" />
            <label class="string required" for="note_title">榜单名称</label><input aria-required="true"
              autofocus="autofocus" class="string required form-control input-small" id="collectName"
              name="collectName" placeholder="榜单名称" required="required" type="text" />
          </div>
          <div class="form-group string required note_title">
            <label class="string required" for="note_title">榜单背景图片（建议：625*350）</label>
            <input autofocus="autofocus" class="form-control input-small" 
              id="coverImgFile" name="coverImgFile" type="file" />
          </div>
          <div class="form-group text required note_summary">
            <label class="text required" for="note_summary">一句话介绍</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="memo" name="memo" required="required"></textarea>
            <p class="help-block">请精简描述, 36字以内</p>
          </div>
          <div class="form-group text required note_summary">
            <label class="text required" for="note_summary">内容介绍</label>
            <textarea aria-required="true" class="text required form-control input-big"
              id="recommend" name="recommend" required="required"></textarea>
          </div>

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