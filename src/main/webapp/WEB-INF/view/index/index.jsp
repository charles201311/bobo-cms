<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>cms系统</title>
<body>
	<!-- top -->
	<div>
		<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>
	</div>
	<div class="container">

		<br>
		<!-- 主体 -->
		<div class="row">
			<!-- 左侧栏目 -->
			<div class="col-md-2 ">
				<ul class="list-group text-center">
					<li
						class="list-group-item cms-list-group-item-action ${c.id==article.channelId?"cms-list-group-item-active":"" }"><a
						href="/?hot=1" class="channel">热门 </a></li>
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
					<!-- 轮播图 -->
					<div>
						<div class="bd-example">
							<div id="carouselExampleCaptions" class="carousel slide"
								data-ride="carousel">
								<ol class="carousel-indicators">
									<li data-target="#carouselExampleCaptions" data-slide-to="0"
										class="active"></li>
									<li data-target="#carouselExampleCaptions" data-slide-to="1"></li>
									<li data-target="#carouselExampleCaptions" data-slide-to="2"></li>
								</ol>
								<div class="carousel-inner">
									<div class="carousel-item active">
										<img src="/pic/1.jpg" class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block">
											<h5>图片一</h5>

										</div>
									</div>
									<div class="carousel-item">
										<img src="/pic/2.jpg" class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block">
											<h5>图片二一</h5>

										</div>
									</div>
									<div class="carousel-item">
										<img src="/pic/3.jpg" class="d-block w-100" alt="...">
										<div class="carousel-caption d-none d-md-block">
											<h5>图片三</h5>

										</div>
									</div>
								</div>
								<a class="carousel-control-prev" href="#carouselExampleCaptions"
									role="button" data-slide="prev"> <span
									class="carousel-control-prev-icon" aria-hidden="true"></span> <span
									class="sr-only">Previous</span>
								</a> <a class="carousel-control-next"
									href="#carouselExampleCaptions" role="button" data-slide="next">
									<span class="carousel-control-next-icon" aria-hidden="true"></span>
									<span class="sr-only">Next</span>
								</a>
							</div>
					
					
						</div>


					</div>
					<br>

					<!-- 热点文章 -->
					<div>

						<c:forEach items="${hotArticles}" var="a">
							<div class="media">
								<img src="/pic/${a.picture }" class="mr-3" alt="..."
									style="width: 190px; height: 124px">
								<div class="media-body ">
									<h5 class="mt-0 " style="height: 100px">
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

				<!-- 如果栏目不为空则先栏目下的文章 -->
				<c:if test="${article.channelId!=null}">

					<div>
						<!--显示栏目下分类  -->
						<ul class="nav">
							<li class="nav-item ${c.id==article.categoryId?"cms-list-group-item-active":"" } "><a
								class="nav-link" href="?channelId=${article.channelId}">全部</a></li>
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
								<img src="/pic/${a.picture }" class="mr-3" alt="..."
									style="width: 190px; height: 124px">
								<div class="media-body ">
									<h5 class="mt-0 " style="height: 100px">
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
			<div class="col-md-3 split min_h_500">
			<!-- 专题 -->
			<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong>专题</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${specialList }" var="s">
								<div class="media">
									
									<div class="media-body">
										<h3><strong>${s.title }</strong></h3>
										 <h5>${s.abstracts }</h5>
										<ul>
										<c:forEach items="${s.specialArticles}" var="ss">
									
										    <li>  <a href="/select?id=${ss.article.id }" target="blank">${ss.article.title }</a></li>
										    	
										
										</c:forEach>
										</ul>
									</div>
								</div>
								<br>
								<hr>
							</c:forEach>

						</div>
					</div>
				</div>

	<!-- 图片集 -->
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong>图片集</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${articlepics }" var="a">
								<div class="media">
									<img src="/pic/${a.picture }" class="mr-3" alt="..."
										width="60px" height="60px">
									<div class="media-body">
										<h6>
											<a href="/selectpic?id=${a.id }" target="blank">${a.title }</a>
										</h6>
									</div>
								</div>
								<br>
							</c:forEach>

						</div>
					</div>
				</div>

				<!-- 24小时热文 -->
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong>24小时热文</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${article24 }" var="a">
								<div class="media">
									<img src="/pic/${a.picture }" class="mr-3" alt="..."
										width="60px" height="60px">
									<div class="media-body">
										<h6>
											<a href="/select?id=${a.id }" target="blank">${a.title }</a>
										</h6>
									</div>
								</div>
								<br>
							</c:forEach>

						</div>
					</div>
				</div>
            <!--  最新文章 -->
				<div>
					<div class="card" style="width: 18rem;">
						<div class="card-header">
							<strong> 最新文章</strong>
						</div>
						<div class="card-body">
							<c:forEach items="${articlehot }" var="a">
								<div class="media">
									<img src="/pic/${a.picture }" class="mr-3" alt="..."
										width="60px" height="60px">
									<div class="media-body">
										<h6>
											<a href="/select?id=${a.id }" target="blank">${a.title }</a>
										</h6>
									</div>
								</div>
								<br>
							</c:forEach>

						</div>
					</div>
				</div>


			</div>
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