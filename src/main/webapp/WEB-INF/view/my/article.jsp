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
	<div class="container" align="center">
	     <div align="right">
	    
	     <button class="btn btn-info" onclick="back()">返回</button>
	     </div>
		<dl>
			<dt>${article.title }</dt>
			<dd>${article.user.username} &nbsp;<fmt:formatDate value="${article.created }" pattern="yyyy-MM-dd HH:mm:ss"/></dd>
			<hr>
			<dd>${article.content }</dd>

		</dl>




	</div>
	
	<script type="text/javascript">
	//返回上层
	function back(){
		$("#center").load("/my/articles");
	}
	//审核
	function check(status){
		var id='${article.id}';
		$.post("/admin/updateArticle",{id:id,status:status},function(flag){
			if(flag){
				alert("操作成功");
			}else{
				alert("操作失败");
			}
		})
		
	}
	
	
	</script>
</body>
</html>