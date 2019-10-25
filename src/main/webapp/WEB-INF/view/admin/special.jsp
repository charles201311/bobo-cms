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
<body>
	<div class="container">

		<table>

			<tr>
				<td>专题名称:${s.title }</td>
			</tr>
			<tr>
				<td>专题摘要:${s.abstracts}</td>
			</tr>

		</table>
		
		<table class="table table-bordered table-hover"
			style="text-align: center">
			<tr>
				<td>文章ID</td>
				<td>文章标题</td>
				<td>发布时间</td>
			</tr>

			<c:forEach items="${s.specialArticles }" var="a" varStatus="index">
				<tr>
					<td>${a.article.id}</td>
					<td>${a.article.title}</td>
					<td><fmt:formatDate value="${a.article.created }"
							pattern="yyyy-MM-dd HH:mm:ss" /></td>

				</tr>

			</c:forEach>


		</table>


   <div class="form-group form-inline">
     <input class="form-control" type="text" name="aid" placeholder="文章ID"  >
     <button type="button" class="btn btn-class" onclick="addArticle(${s.id})">增加文章</button>
   
    </div>

	</div>
	
	<script type="text/javascript">
	function addArticle(sid){
		var aid =$.trim($("[name='aid']").val())
		
		if(aid.length==0){
			alert("文章ID必须输入")
			return ;
		}
		
		
		$.post("/admin/special/addArticle",{sid:sid,aid:aid},function(flag){
			if(flag){
				alert("添加成功");
				$("#content-wrapper").load("/admin/special/select?sid="+sid);
			}else{
				alert("添加失败")
			}
		})
	}
	
	</script>
</body>
</html>