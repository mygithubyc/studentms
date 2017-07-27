<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>


<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html;charset=utf-8">
    <title>欢迎登录学生管理系统</title>
    
	<jsp:include page="/WEB-INF/view/common/include_css.jsp"></jsp:include>
	<link rel="stylesheet" href="${ctx }/static/css/index.css">

  </head>
  
  <body class="index_bg">
  	
    <div class="container">
		<div class="container__header">
			<h1 class="container__h1">学生管理系统</h1>
		</div>
		<div class="container__main">
			<div class="container__switcher">
				<a href="#" class="container__a">快速登录</a> <a href="#"
					class="container__a">注册(仅学生)</a>
				<div class="container__tip "></div>
			</div>
			<div class="container__validate">
				<ol>
				</ol>
			</div>
			<div class="container__form">
				<form action="" id="login_form" method="post"
					style="display:hidden;">
					<div class="container__fitem">
						<label for="username">账号:</label> <input type="text"
							name="username" id="username">
					</div>
					<div class="container__fitem">
						<label for="password">密码:</label> <input type="password"
							name="password" id="password">
					</div>
					<div class="container__fitem rad">
						<label for="teacher">教师:<input type="radio" name="type"
							value="teacher" id="teacher"></label> <label for="student">学生:<input
							type="radio" name="type" value="student" id="student"></label>
					</div>
					<div class="container__fitem">
						<label for=""></label>
						<button type="" class="container__form_button" id="login_button">登录</button>
					</div>
				</form>
				<form action="" id="register_form" method="post"
					style="display:hidden;">
					<div class="container__fitem">
						<label for="username">账号:</label> <input type="text" name=""
							id="username" placeholder="请移出输入框以测试账号可用">
					</div>
					<div class="container__fitem">
						<label for="password">密码:</label> <input type="password" name=""
							id="password">
					</div>
					<div class="container__fitem">
						<label for="password_again">确认密码:</label> <input type="password"
							name="" id="password_again">
					</div>
					<div class="container__fitem">
						<label for=""></label>
						<button type="" class="container__form_button"
							id="register_button">注册</button>
					</div>
				</form>
			</div>
		</div>
	</div>
  </body>
  <jsp:include page="/WEB-INF/view/common/include_js.jsp" />
  <script type="text/javascript" src="${ctx}/static/js/index.js"></script>
</html>
