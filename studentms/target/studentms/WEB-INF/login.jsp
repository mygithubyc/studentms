<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>欢迎登录</h1>
<form action="users/login" method="post">
	<table>
		<tr><td>用户名：</td><td><input type="text" id="username" name="username"/></td></tr>
		<tr><td>密码：</td><td><input type="password" id="password" name="password"/></td></tr>
		<tr><td><input type="submit" value="登录"/></td><td><input type="reset" value="取消"/></td></tr>
	</table>
</form>
<form action="users/goRegister"><input type="submit" value="去注册"/></form>
<h1>{mesg}</h1>
</body>
</html>