<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/jquery/jquery/jquery-3.2.1.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/jquery/jquery.form.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/jquery/jquery.metadata.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/jquery/messages_zh.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/static/js/register.js"></script>
</head>
<body>
	<h1>欢迎注册</h1>
	<form id="register" action="users/register" method="post">
		<table>
			<tr>
				<td>账号：</td>
				<td><input type="text" id="username" name="username" /></td>
			</tr>
			<tr>
				<td>密码：</td>
				<td><input type="password" id="password" name="password" /></td>
			</tr>
			<tr>
				<td>确认密码：</td>
				<td><input type="password" id="confrimPassword"
					name="confrimPassword" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="确认提交" id="submit"/></td>
				<td><input type="reset" value="重新填写" id="reset"/></td>
			</tr>
		</table>
	</form>
</body>
</html>