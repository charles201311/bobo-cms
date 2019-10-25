<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("UTF-8");
	String htmlData = request.getParameter("content1") != null ? request.getParameter("content1") : "";
%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<link rel="stylesheet"
	href="/resource/kindeditor/themes/default/default.css" />
<link rel="stylesheet"
	href="/resource/kindeditor/plugins/code/prettify.css" />
	<!-- jquery.validate 校验提示样式 -->
<link rel="stylesheet" type="text/css"
	href="/resource/css/jquery/screen.css">
<script charset="utf-8"
	src="/resource/kindeditor/plugins/code/prettify.js"></script>
<script charset="utf-8" src="/resource/kindeditor/kindeditor-all.js"></script>
    
<script charset="utf-8" src="/resource/kindeditor/lang/zh-CN.js"></script>
<script src="/resource/js/jquery-3.2.1.js"></script>

	<script type="text/javascript" src="/resource/js/jquery.validate.js"></script>
<script>
	KindEditor.ready(function(K) {
		window.editor1 = K.create('textarea[name="content1"]', {
			cssPath : '/resource/kindeditor/plugins/code/prettify.css',
			uploadJson : '/resource/kindeditor/jsp/upload_json.jsp',
			fileManagerJson : '/resource/kindeditor/jsp/file_manager_json.jsp',
			allowFileManager : true,
			afterCreate : function() {
				var self = this;
				K.ctrl(document, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
				K.ctrl(self.edit.doc, 13, function() {
					self.sync();
					document.forms['example'].submit();
				});
			}
		});
		prettyPrint();
	});
	function query() {
		alert(editor1.html())
		//alert( $("[name='content1']").attr("src"))
	}
</script>
</head>
<body>


	<form id="form1">

		<div class="form-group">
			<label for="title"> 文章标题:</label> <input class="form-control"
				type="text" name="title" id="title">
		</div>
		<div class="form-group">
			<label for="original"> 文章来源:</label> <input class="form-control"
				type="text" name="original" id="original">
		</div>
		<div class="form-group">
			<label for="keywords"> 关键词:</label> <input class="form-control"
				type="text" name="keywords" id="keywords">
		</div>
		<div class="form-group">
			<label for="content"> 文章内容:</label>
			<textarea rows="10" cols="30" name="content1" id="content"
				style="width: 825px">
   
   </textarea>
		</div>
		<div class="form-group form-inline">
			栏目:<select class="form-control-sm" id="channel" name="channelId">
				<option value="-1">请选择</option>

			</select> &nbsp;&nbsp; 分类:
			<select class="form-control-sm" id="category" name="categoryId">
				<option value="-1">请选择</option>
			</select>
		</div>
		<div class="form-group">
			<label for="content"> 标题图片:</label> <input type="file" name="file" value=""
				class="form-control">

		</div>
		<div class="form-group">
			<button class="btn btn-success" type="button" onclick="publish()">发布</button>
			<button class="btn btn-info" type="reset">重置</button>

		</div>
	</form>
	<script type="text/javascript">
	//发布文章
	function publish(){
		$("#form1").submit();
	}
	
	
	
  $(function(){
	  //文档就绪时加载文章栏目
	  $.get("/channel/channels",function(channel){
		for(var i in channel){
			$("#channel").append(" <option value='"+channel[i].id+"'> "+channel[i].name+"</option");
		};
		//为栏目绑定change事件,
		$("#channel").change(function(){
			 //先清空分类原有的option
			  $("#category").empty();
			//获取当前选中的栏目ID
		  var cid =$(this).val();
		  //根据栏目ID 查询栏目下的分类
		  $.get("/channel/selectCategorysByCid",{cid:cid},function(categorys){
			 
			  for(var i in categorys){
					$("#category").append(" <option value='"+categorys[i].id+"'> "+categorys[i].name+"</option");
				};  
			  
		  })
			
			
		})
		
	  })
	  
	  //
	  
  })
 
  
	//jquery前端验证
	$(function(){
		$("#form1").validate({
			rules:{
				
				title:{
					required:true,
				},
				channelId:{
					min:1,
				},
				categoryId:{
					min:1,
				}
			},
			messages:{
				
				title:{
					required:"标题不能为空",
				},
				channelId:{
					min:"请选择栏目",
				},categoryId:{
					min:"请选择分类",
				}
			},submitHandler: function(form) {
				
				 var formData = new FormData($( "#form1" )[0]);
				 //获取带html样式的文章内容,并封装到formData
				 formData.set("content",editor1.html());
				 $.ajax({
					 type:"post",
					 url:"/my/publish",
					 data : formData,
					// 告诉jQuery不要去处理发送的数据
					 processData : false,
					 // 告诉jQuery不要去设置Content-Type请求头
					 contentType : false,
					 success:function(flag){
						 if(flag){
							 alert("发布成功");
							 location.href="/my"
						 }else{
							 alert("发布失败,试试重新登录后再发布")
						 }
					 }
					 
				 })
				
				
				
				
			}
			
			
			
		})
		
		
	})
  
  
	//jquery前端验证
	$(function(){
		$("#form1").validate({
			rules:{
				
				title:{
					required:true,
				},
				password:{
					required:true,
				}
			},
			messages:{
				
				title:{
					required:"标题不能为空",
				},
				password:{
					required:"密码不能为空",
				}
			}
			
			
			
		})
		
		
	})
	
 
 </script>
</body>
</html>
