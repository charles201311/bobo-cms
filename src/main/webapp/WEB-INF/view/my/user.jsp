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
	
	
	<form action="" id="form1">
	  <div class="form-group form-inline" >
	    <input type="hidden" name="id" value="${user.id }">
	    <label for="nickname">昵称</label>
	    <input type="text" name="nickname" class="form-control" value="${user.nickname }">
	  </div>
	  <div class="form-group form-inline">
	    <label for="url">博客地址</label>
	    <input type="text" name="url" class="form-control"  value="${user.url }">
	    <a href="${user.url}" target="blank">进入网址</a>
	  </div>
	
	  <div class="form-group form-inline">
	   <button class="btn btn-info" type="button" onclick="update()">更新</button>
	    </div>
	
	</form>
	 
	</div>
	
	<script type="text/javascript">
     function update(){
    	 $.post("/my/user/update",$("#form1").serialize(),function(flag){
    		 if(flag){
    			 alert("更新成功")
    		 }else{
    			 alert("更新失败")
    		 }
    	 })
     }
	
	
	</script>
</body>
</html>