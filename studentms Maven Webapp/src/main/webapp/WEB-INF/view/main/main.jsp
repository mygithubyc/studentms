<%@ page language="java"  pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>学生管理系统</title>
	<jsp:include page="/WEB-INF/view/common/include_css.jsp"></jsp:include>
	<link rel="stylesheet" type="text/css" href="${ctx }/static/css/main.css">

  </head>
  
  <body>
    <div class="header">
            <div class="header__wrap">
                <div class="header__logo">
                    学生管理平台 欢迎你,xxx 同学/老师
                </div>
                <div class="header__logout">
                    登出
                </div>
            </div>
            
        </div>
       <div style="background:#fafafa;width:100%;border:1px solid #ccc">
        <a href="#" class="easyui-menubutton" menu="#mm1" iconCls="icon-edit">作业模块</a>
        <a href="#" class="easyui-menubutton" menu="#mm2" iconCls="icon-man">个人信息</a>
        <div id="mm1" style="width:150px;">
            <div url="${ctx }/job/teacher" title="布置作业">布置作业</div>
            <div url="${ctx }/comJob/teacher" title="查看提交">查看提交</div>
            <div url="${ctx }/job/student" title="提交作业">提交作业</div>
        </div>
        <div id="mm2" style="width:100px;">
            <div url="http://www.baidu.com" title="布置作业4">完善个人信息</div>
            <div url="http://www.baidu.com" title="布置作业5">修改密码</div>
            
        </div>
        <div id="tt" class="easyui-tabs" style="width:100%;height:800px;">
            <div title="Home">
                <div  style="margin-left:300px;margin-top: 100px;">
                    <img src="${ctx }/static/img/welcome.png"  alt="" >

                </div>
            </div>
        </div>
    </div>
  </body>
  <jsp:include page="/WEB-INF/view/common/include_js.jsp"></jsp:include>
  <script type="text/javascript" src="${ctx }/static/js/main.js"></script>
</html>
