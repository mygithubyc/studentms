<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/admin/common/taglibs.jsp"%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js-modules/easyui/themes/bootstrap/easyui.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js-modules/easyui/themes/icon.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/js-modules/easyui/themes/IconExtension.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/admin/login.css">

</head>
<body style="margin:0; padding: 0;">
        <div class="container">
            <h2 class="container__header">xx管理系统</h2>
            <div class="easyui-panel container__panel" title="登录到后台">
                <form  id="login_form" method="post">
                    <div class="container__item">
                        <input name="username" class="easyui-textbox easyui-validatebox" data-options="validateOnCreate: false,prompt:'账号',iconCls:'icon-client',iconWidth:38,required: true,missingMessage:'请输入管理员账号'">
                    </div>
                    <div class="container__item">
                        <input name="password" class="easyui-textbox easyui-validatebox" type="password" data-options="validateOnCreate: false,prompt:'密码',iconCls:'icon-lock',iconWidth:38,required: true,missingMessage:'请输入管理员密码'">
                    </div>
                    <div class="container__item">
                        <input name="captcha" class="easyui-textbox easyui-validatebox" data-options="validateOnCreate: false,prompt:'验证码',required: true,missingMessage:'请输入验证码'" style="width: 140px;">
                        <img src="randomcode.jpg" onclick="javascript:this.src='randomcode.jpg?rnd'+Math.random()" title="点击刷新验证码" class="container__code">
                    </div>
                    <div class="container__item">
                        <a href="#"  id="login_button" class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="padding:5px 0px;width:320px;">
                            <span style="font-size:16px;">Login</span>
                        </a>
                    </div>
                </form>
                
            </div>
        </div>
    </body>
<script src="${pageContext.request.contextPath}/static/js-modules/easyui/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js-modules/easyui/jquery.easyui.min.js"></script>
<script src="${pageContext.request.contextPath}/static/js-modules/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${pageContext.request.contextPath}/static/js/admin/login.js"></script>
</html>