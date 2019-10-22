<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<!-- 视窗 -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 引入  css -->
<link rel="stylesheet" href="/resource/css/bootstrap.min.css">
<!-- 后台页面样式模板 -->
<link rel="stylesheet" href="/resource/css/sb-admin.css">
<link rel="stylesheet" href="/resource/css/all.min.css">
<script type="text/javascript" src="/resource/js/cms.js"></script>
</head>
<body>
	<div class="container">
		<div class="form-group form-inline">
			<label for="title">文章标题:</label> <input id="title" type="text"
				class="form-control" name="title" value="${article.title }">
			&nbsp; 文章状态: <select name="status" class="form-control" id="status">

				<option value="0">待审</option>
				<option value="1">已审</option>
				<option value="-1">驳回</option>

			</select> &nbsp;
			<button class="btn btn-success btn-sm" type="button"
				onclick="query()">查询</button>
		</div>

		<table class="table table-bordered table-hover"
			style="text-align: center">
			<tr>
				<td>序号</td>
				<td>文章标题</td>
				<td>作者</td>
				<td>昵称</td>
				<td>发布时间</td>
				<td>文章状态</td>
				<td>点击量</td>
				<td>是否热门</td>
				<td>操作</td>
			</tr>

			<c:forEach items="${articles }" var="a" varStatus="index">
				<tr>
					<td>${index.index+1 }</td>
				<td><a href="javascript:detail(${a.id})">${a.title}</a></td>
					<td>${a.user.username }</td>
					<td>${a.user.nickname }</td>
					<td><fmt:formatDate value="${a.created }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>
					<td>${a.status==0?"待审":a.status==1?"已审":"驳回" }</td>
					<td>${a.hits}</td>

					<td><c:if test="${a.hot==0 }">
							<button class="btn btn-success" onclick="update(${a.id},this)">否</button>
						</c:if> <c:if test="${a.hot==1 }">
							<button class="btn btn-danger" onclick="update(${a.id},this)">是</button>
						</c:if></td>
						
						<td><button class="btn btn-info" onclick="update(${a.id})">修改</button> </td>
				</tr>

			</c:forEach>




		</table>

		<div>${pages }</div>

	</div>
	<script type="text/javascript">
	//去修改
	function update(id){
		 var url="/my/publish?id="+id;
			// alert(url)
			 $("#center").load(url);
	}
	
	 //文章详情
	 function detail(id){

		 var url="/my/article?id="+id;
		// alert(url)
		 $("#center").load(url);
	 }


//回显查询条件
$(function(){
	var status='${article.status}';//获取查询条件
	//让下拉框选中
	$("#status").each(function(){
		$(this).val(status);
	})
})
	

function query(){
	//在框架的中间区域显示查询
	$("#center").load("/my/articles?title="+$("[name='title']").val()+"&status="+$("[name='status']").val());
}

</script>
</body>
</html>