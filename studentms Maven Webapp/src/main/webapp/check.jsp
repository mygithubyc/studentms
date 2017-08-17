<%@page import="com.google.code.kaptcha.Constants"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<% 
	//检查是否正确的验证码
	String k = (String) session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
	String str = request.getParameter("r");
	if(str.equals(k)){
		out.println("通过");
	}
	out.println(k+"---"+str);
%>
