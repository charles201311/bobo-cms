<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${article.title }</title>


</head>
<body>
	<div class="container">

		<dl>
			<!-- 标题 -->
			<dt>
				<h2 align="center">${article.title }</h2>
			</dt>
			<!-- 内容 -->
			<dd>${article.user.username } &nbsp; <fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<dd>${article.content }</dd>

		</dl>


	</div>



</body>
</html>