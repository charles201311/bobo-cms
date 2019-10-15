<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
</head>
<body>

	<table class="table table-bordered table-hover" style="text-align: center">
		<tr>
			<td>序号</td>
			<td>用户名</td>
			<td>昵称</td>
			<td>生日</td>
			<td>状态</td>
			<td>角色</td>
			<td>注册日期</td>
			<td>修改日期</td>
		</tr>

		<c:forEach items="${users }" var="u" varStatus="index">
			<tr>
				<td>${index.index+1 }</td>
				<td>${u.username }</td>
				<td>${u.nickname }</td>
				<td>${u.birthday }</td>
				<td>${u.locked }</td>
				<td>${u.role }</td>
				<td>${u.created }</td>
				<td>${u.updated}</td>
			</tr>

		</c:forEach>
	</table>

</body>
</html>