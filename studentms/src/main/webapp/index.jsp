<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/view/home/common/taglibs.jsp"%>   
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8">
<title>欢迎登录学生管理系统</title>
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/home/base.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/static/css/home/index.css" />
 
</head>

<body class="index_bg">
	<div class="container">
        <div class="container__header">
            <h1 class="container__h1">学生管理系统</h1>
        </div>
        <div class="container__main">
            <div class="container__switcher">
                <a href="#" class="container__a">登录</a>
                <br>
                <hr>
                <div class="container__tip "></div>
            </div>
            <div class="container__validate">
                <ol>
                    
                </ol>
            </div>
            <div class="container__form">
                <form action="" id="login_form" method="post" style="display:hidden;">
                    <div class="container__fitem"> 
                        <label for="username">账号:</label>
                        <input type="text" name="username" id="username">
                    </div>
                    <div class="container__fitem"> 
                        <label for="password">密码:</label>
                        <input type="password" name="password" id="password">
                    </div>
                    <div class="container__fitem rad"> 
                    <label for="teacher">教师:<input type="radio" name="type" value="2" id="teacher"></label>
                    <label for="student">学生:<input type="radio" name="type" value="3" id="student" ></label>
                    </div>
                    <div class="container__fitem"> 
                    <label for=""></label>
                    <button  class="container__form_button" id="login_button">登录</button>
                    </div>
                </form>

                
            </div>
        </div>
    </div>
</body>
<script src="${ctx}/static/js-modules/easyui/jquery.min.js"></script>
<script src="${ctx}/static/js/home/index.js"></script>
</html>
