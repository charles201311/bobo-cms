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
<title>欢迎回来</title>

<!-- Bootstrap -->
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<link rel="icon" type="image/x-icon" href="/resource/pic/title.png" />

</head>
<body>
	<jsp:include page="/WEB-INF/view/common/top.jsp"></jsp:include>

	<!-- 登录注册页面 -->
	<div style="height: 20px;"></div>

	<div class="container">
		<div class="row passport_bg">
			<div class="col-md-6">
					<div class="card" style="width: 26rem;height: 20rem">
						<div class="card-body">
							<span style="color: red">${error }</span>
							<h3 class="card-title" align="center">用户登录</h3>
							<form id="form1" method="post" action="/passport/login">
								<div class="form-group">
									<label for="username"> 用户名:</label> <input type="text"
										class="form-control" name="username" id="username" value="${userVO.username }">
								</div>
								<div class="form-group">
									<label for="password"> 密码:</label> <input type="password"
										class="form-control" name="password" id="password">
								</div>

								<div class="form-group">
									<button type="submit" class="btn btn-info">登录</button>
									<button type="reset" class="btn btn-warning">重置</button>
								</div>

							</form>

					</div>


				</div>
			</div>
			<div class="col-md-6">
				<div class="passport_r">
					<h3>最新加入的用户：</h3>
					<p align="center">
						<img src="/resource/images/guest.jpg" alt="..."
							class="rounded-circle img-thumbnail" /> &nbsp;&nbsp;&nbsp;&nbsp;
						<img src="/resource/images/guest1.jpg" alt="..."
							class="rounded-circle img-thumbnail" />
					</p>
				</div>
			</div>
		</div>
	</div>
	<div>
		<br />
		<br />
	</div>
	<jsp:include page="/WEB-INF/view/common/footer.jsp" />

	<script type="text/javascript">
	//jquery前端验证
	$(function(){
		$("#form1").validate({
			rules:{
				
				username:{
					required:true,
				},
				password:{
					required:true,
				}
			},
			messages:{
				
				username:{
					required:"用户名不能为空",
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