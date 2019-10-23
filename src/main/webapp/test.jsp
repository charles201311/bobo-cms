<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href="/resource/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css"
	href="/resource/open-iconic/font/css/open-iconic-bootstrap.css" />
<link rel="stylesheet" type="text/css"
	href="/resource/css/cms.css?v=1.1" />
	<link rel="stylesheet" type="text/css"
	href="/resource/css/jquery/screen.css" />
</head>
<body>
	<div class="container" style="padding: 20px">
		<div class="alert alert-success">
			<a href="#" class="close" data-dismiss="alert" aria-hidden="true">&times;</a>
			<strong>成功!</strong>结果是成功的。
		</div>
		<div class="alert alert-warning">
			<a href="#" class="close" data-dismiss="alert" aria-hidden="true">&times;</a>
			<strong>警告！</strong>网络连接有问题。
		</div>
	</div>
	<script>
		$(document).ready(function() {
			$(".close").click(function() {
				//$(this).alert();
				$(this).alert("close");
			})
		})
	</script>
</body>
</html>