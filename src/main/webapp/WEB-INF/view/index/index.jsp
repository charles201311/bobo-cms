<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cms系统</title>


</head>
<body>
	<div class="container">
		<!-- top -->
		<div>
			<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
		</div>
		<br>
		<!-- 主体 -->
		<div class="row">
			<!-- 左侧栏目 -->
			<div class="col-md-2 ">
				<c:forEach items="${channels}" var="c">
					<ul class="list-group">

						<li class="list-group-item text-center"><a
							href="?channelId=${c.id }" class="channel">${c.name } </a></li>


					</ul>


				</c:forEach>




			</div>

			<!-- 中间文章标题 -->
			<div class="col-md-7 split min_h_500">
				<div>
					<!--显示栏目下分类  -->
					<ul class="nav">
						<c:forEach items="${categorys}" var="c">
							<li class="nav-item"><a class="nav-link"
								href="?channelId=${c.channelId}&categoryId=${c.id}">${c.name }</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				<!-- 文章 -->
				<div>
					<c:forEach items="${articles}" var="a">
						<div class="media">
							<img src="/pic/${a.picture }" class="mr-3" alt="...">
							<div class="media-body">
								<h5 class="mt-0"><a href="/select?id=${a.id }" target="blank">${a.title }</a></h5>
								<h5 class="mt-0">${a.user.username } &nbsp; <fmt:formatDate value="${a.created }" pattern="yyyy-MM-dd HH:mm:ss"/> </h5>
							</div>
						</div>
						<hr>
					</c:forEach>


				</div>

			</div>

			<!-- 右侧边栏 -->
			<div class="col-md-3 split min_h_500">右侧边栏</div>

		</div>



	</div>



</body>
</html>