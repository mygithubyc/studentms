<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登录学生管理系统</title>
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/js-modules/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/js-modules/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/css/home/base.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/css/home/main.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/js-modules/easyui/themes/IconExtension.css" />

</head>
<body>
	<div class="header">
		<div class="header__wrap">
			<div class="header__logo">学生管理平台 欢迎你,xxx 同学/老师</div>
			<div class="header__logout">登出</div>
		</div>

	</div>

	<!-- 测试用户类型 -->
	<c:set var="user" value="${sessionScope.user}" scope="session"></c:set>

	<%-- 选择显示控件 --%>
	<c:choose>
		<c:when test="${user.userType==2}">
			<%-- 老师菜单栏 --%>
			<div
				style="background: #fafafa; padding: 5px; width: 100%; border: 1px solid #ccc">
				<span id="teacher_job" url="" title="布置作业">布置作业</span> <span
					id="teacher_marks" url="" title="录入成绩">录入成绩</span> <span
					id="teacher_plan" url="" title="下载教学计划">下载教学计划</span> <span
					id="teacher_message" url="" title="发布通知">发布通知</span> <span
					id="teacher_info">个人信息</span>
				<div id="teacher_info_mm" style="width: 100px">
					<div url="" title="完善个人信息">完善个人信息</div>
					<div url="" title="修改密码" class="change_password">修改密码</div>
				</div>
				<%--老师菜单栏结束 --%>
		</c:when>

		<c:when test="${user.userType==3}">
			<%--学生菜单栏 --%>
			<span id="student_job" url="" title="布置作业">提交作业</span>
			<span id="student_marks" url="" title="成绩查询">成绩查询</span>
			<span id="student_info">个人信息</span>
			<div id="student_info_mm" style="width: 100px">
				<div url="" title="完善个人信息">完善个人信息</div>
				<div url="" title="修改密码" class="change_password">修改密码</div>
			</div>
			<%--学生菜单栏结束 --%>
		</c:when>


		<%--右边通知提示 学生:考试安排 老师布置作业情况 班主任消息列表 教师:考试安排 作业提交情况 --%>
		<c:when test="${user.userType==2}">
			<span id="exam_menu">考试安排</span>
		</c:when>
		<c:when test="${user.userType==3}">
			<span id="message_menu">班主任通知</span>
		</c:when>

		<c:when test="${user.userType==2}">
			<span id="job_menu">作业情况</span>
		</c:when>

		<%-- 考试通知--%>
		<c:when test="${user.userType==2||user.userType==3}">
			<div id="exam_menu_div" class="menu-content my_menu_content">
				<img src="${ctx }/static/common/img/exam.png">
				<p>
					<a href="#">2017-2018 上学期 期末考试安排表</a>
				</p>
				<hr>
			</div>
		</c:when>

		<c:when test="${user.userType==3}">
			<%-- 班主任通知   红色 紧急通知  橙色 一般通知  黑色 普通通知 --%>
			<div id="message_menu_div" class="menu-content my_menu_content">
				<img src="${ctx }/static/common/img/message.png">
				<p class="important_message">最近骗子很多,同学们小心!</p>
				<hr>
				<p class="common_message">快考试了,好好复习!</p>
				<hr>
				<p>大家中秋快乐!</p>
				<hr>
			</div>
			<div id="job_menu_div" class="menu-content my_menu_content">
				<img src="${ctx }/static/common/img/job.png">
				<p>C语言xxx老师在 2017-08-01 发布了作业 快去看看</p>
				<hr>
				<p>D语言xxx老师在 2017-08-01 发布了作业 快去看看</p>
				<hr>
				<p>E语言xxx老师在 2017-08-01 发布了作业 快去看看</p>
				<hr>
			</div>
		</c:when>
		<c:otherwise>?</c:otherwise>
	</c:choose>

	<!-- easyui tabs -->
	<div id="tt" class="easyui-tabs" style="width: 100%; height: 820px;">
		<div title="Home">
			<div style="margin-left: 300px; margin-top: 100px;">
				<img src="${ctx }/static/common/img/welcome.png" alt="">
			</div>
		</div>
	</div>
	</div>

	<!-- 密码修改表单 -->
	<div id="password_dlg">
		<div class="ftitle">修改密码</div>
		<form id="fm" method="post">
			<div class="fitem">
				<label>旧密码:</label> <input name="">
			</div>
			<div class="fitem">
				<label>新密码:</label> <input name="">
			</div>
			<div class="fitem">
				<label>确认密码:</label> <input name="">
			</div>

		</form>
	</div>

</body>
<script src="${ctx }/static/common/js-modules/easyui/jquery.min.js"></script>
<script
	src="${ctx }/static/common/js-modules/easyui/jquery.easyui.min.js"></script>
<script
	src="${ctx }/static/common/js-modules/easyui/locale/easyui-lang-zh_CN.js"></script>
<script src="${ctx }/static/common/js/home/main.js"></script>

</html>