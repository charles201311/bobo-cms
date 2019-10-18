<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cms系统</title>
<style type="text/css">
</style>

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
				<ul class="list-group text-center">
					<li
						class="list-group-item cms-list-group-item-action ${c.id==article.channelId?"cms-list-group-item-active":"" }"><a
						href="/" class="channel">热门 </a></li>
				</ul>
				<c:forEach items="${channels}" var="c">
					<ul class="list-group text-center">
						<li
							class="list-group-item cms-list-group-item-action ${c.id==article.channelId?"cms-list-group-item-active":"" }"><a
							href="?channelId=${c.id }" class="channel"> ${c.name } </a></li>
					</ul>
				</c:forEach>

			</div>

			<!-- 中间文章标题 -->
			<div class="col-md-7 split min_h_500">
			

				<!-- 如果栏目为空,则显示热点文章 -->
				<c:if test="${article.channelId==null}">

					<c:forEach items="${hotArticles}" var="a">
						<div class="media">
							<img src="/pic/${a.picture }" class="mr-3" alt="...">
							<div class="media-body ">
								<h5 class="mt-0 ">
									<a href="/select?id=${a.id }" target="blank">${a.title }</a>
								</h5>
								<h5 class="mt-0 myposition">${a.user.username }
									&nbsp;
									<fmt:formatDate value="${a.created }"
										pattern="yyyy-MM-dd HH:mm:ss" />
								</h5>
							</div>
						</div>
						<hr>
					</c:forEach>

				</c:if>

				<!-- 如果栏目不为空则先栏目下的文章 -->
				<c:if test="${article.channelId!=null}">
				
					<div>
					<!--显示栏目下分类  -->
				 	<ul class="nav">
				   	<li class="nav-item ${c.id==article.categoryId?"cms-list-group-item-active":"" } "><a
				 				class="nav-link"
								href="?channelId=${article.channelId}">全部</a>
							</li>
						<c:forEach items="${categorys}" var="c">
							<li class="nav-item ${c.id==article.categoryId?"cms-list-group-item-active":"" } "><a
								class="nav-link"
								href="?channelId=${c.channelId}&categoryId=${c.id}">${c.name }</a>
							</li>
						</c:forEach>
					</ul>
				</div>
				
					<div>
						<hr>
						<c:forEach items="${articles}" var="a">
							<div class="media">
								<img src="/pic/${a.picture }" class="mr-3" alt="...">
								<div class="media-body ">
									<h5 class="mt-0 ">
										<a href="/select?id=${a.id }" target="blank">${a.title }</a>
									</h5>
									<h5 class="mt-0 myposition">${a.user.username }
										&nbsp;
										<fmt:formatDate value="${a.created }"
											pattern="yyyy-MM-dd HH:mm:ss" />
									</h5>
								</div>
							</div>
							<hr>
						</c:forEach>

						${pages }

					</div>
				</c:if>
			</div>

			<!-- 右侧边栏 -->
			<div class="col-md-3 split min_h_500">右侧边栏</div>

		</div>



	</div>
	<div>
		<jsp:include page="/WEB-INF/view/common/footer.jsp"></jsp:include>

	</div>
	<script type="text/javascript">
		$(function() {
			//分页处理
			$(".page-link").click(function() {
				var url = $(this).attr("data");
				location.href = url;
			})
		})
	</script>


</body>
</html>