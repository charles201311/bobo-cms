<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="keywords" content="${article.keywords}" />
<meta name="description" content="${article.summary}" />
<title>${article.title }</title>


</head>
<body>
<!-- top -->
	<div>
		<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	</div>
	<div class="container">

		<dl>
			<!-- 标题 -->
			<dt>
				<h2 align="center"><strong>${article.title }</strong></h2>
			</dt>
			<hr>
			<!-- 内容 -->
			<dd>${article.user.username } &nbsp;
			 <fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/>
			 文章来源:${article.original }
			 </dd>
			
			<dd>${article.content }</dd>

		</dl>


	</div>



</body>
</html>