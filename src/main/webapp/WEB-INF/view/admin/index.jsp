<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员后台</title>
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入  css -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/sb-admin.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/all.min.css" />
<link rel="stylesheet" type="text/css" href="/resource/css/cms.css" />
<script type="text/javascript" src="/resource/js/jquery-3.2.1.js"></script>
<script type="text/javascript"
		src="/resource/js/bootstrap.min.js"></script>
		<script type="text/javascript"
		src="/resource/js/sb-admin.min.js"></script>
</head>
<body class="bg-light">

	<!--top -->
	<div>
		<jsp:include page="top.jsp"></jsp:include>
	</div>

	<div id="wrapper">
		<!-- 后台管理系统左册菜单 -->
		<jsp:include page="left.jsp" />
		<!-- 中间内容显示区域 -->
		<div id="content-wrapper" class="bg-light">
		
		
			<div align="center"  >
				<img alt="" src="/resource/images/bg_admin.jpg"
					class="rounded-circle">
			</div>

		</div>
	</div>

<script type="text/javascript">



//文档就绪函数
 $(function(){
	 //为左侧菜单添加点击事件
	 $(".nav-link").click(function(){
		 var li =$("ul li a");
		//先移除所有的list-group-item-info样式
		li.removeClass("list-group-item-info");
		//为左侧菜单添加动态点击样式 
		$(this).addClass("list-group-item-info");
		 
		 
		 //获取点击URL
      var url = $(this).attr("data");
		 //在当前页面的中间区域执行url
		 $("#content-wrapper").load(url);
	 })
	 
 })

</script>

</body>
</html>