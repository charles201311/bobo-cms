<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title></title>

<script type="text/javascript">
function add(){
	$.post("/admin/special/add",$("#form1").serialize(),function(flag){
		if(flag){
			alert("增加成功")
		}else{
			alert("增加失败")
		}
	})
	
	
}


</script>

</head>
<body>

	<div class="container">
      <h1 align="center">增加专题</h1>
		<form id="form1">
			<div class="form-group">
				<label for="title">标题</label> <input type="text" name="title"
					id="title" class="form-control">

			</div>

			<div class="form-group">
				<label for="title">专题摘要</label>
				<textarea rows="5" cols="20" name="abstracts" class="form-control"></textarea>

			</div>


			<div class="form-group">
				<button type="button" class="btn btn-info" onclick="add()">增加</button>

			</div>


		</form>

	</div>

</body>
</html>