<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>管理员后台</title>
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入  css -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<!-- 后台页面样式模板 -->
<link rel="stylesheet" href="/resource/css/sb-admin.css">
<link rel="stylesheet" href="/resource/css/all.min.css">
<script type="text/javascript" src="/resource/js/cms.js"></script>
<script type="text/javascript">

//去增加专题
   function add(){
	   $("#content-wrapper").load("/admin/special/add")
   }

   //增加文章
   function addArticle(sid){
		$("#content-wrapper").load("/admin/special/select?sid="+sid)
	}

</script>
</head>
<body>
	<div class="container">
<button class="btn btn-success" onclick="add()">增加专题</button>
		<table class="table table-bordered table-hover"
			style="text-align: center">
			<tr>
				<td>序号</td>
				<td>专题标题</td>
				<td>专题文章</td>
				<td>操作</td>
				
			</tr>

			<c:forEach items="${specials }" var="s" varStatus="index">
				<tr>
					<td>${index.index+1 }</td>
					<td>${s.title }</td>
					<td>${s.count }</td>
					<td>
					<button class="btn btn-info" onclick="addArticle(${s.id})">增加文章</button>
					<button class="btn btn-warning">修改专题</button>
					
					</td>
					
				</tr>

			</c:forEach>


		</table>
		
		<div>${pages }</div>

</div>



</body>
</html>