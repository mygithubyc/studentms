<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/js-modules/easyui/themes/bootstrap/easyui.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/js-modules/easyui/themes/icon.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/css/home/base.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/common/css/home/main.css" />

</head>
<body>
	<table id="dg"></table>
	<!-- 工具栏 -->
	<div id="toolbar" style="padding: 5px; height: auto">
		<div style="margin-bottom: 5px">
			<a href="#" onclick="newJob()" class="easyui-linkbutton"
				iconCls="icon-add" plain="true">布置作业</a> <a href="#"
				onclick="editJob()" class="easyui-linkbutton" iconCls="icon-edit"
				plain="true">修改作业</a> <a href="#" onclick="delJob()"
				class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除作业</a>
			<a href="#" onclick="downloadAll()" class="easyui-linkbutton"
				iconCls="icon-large-smartart" plain="true">多文件下载作业</a>
		</div>
		<div>
			班级:<input id="class_combobox"> 作业名称:<input
				style="width: 130px"> 发布日期: <input class="easyui-datebox">
			到: <input class="easyui-datebox"> <a href="#"
				class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch()">Search</a>
		</div>

	</div>

	<!-- 布置作业表单 表单dialog都不好和编辑共用一个 class=dlg为了样式 -->
	<div id="add_dlg" class="dlg">
		<form id="add_fm" class="fm" method="post">
			<div class="fitem">
				<label>标题:</label> <input name="">
			</div>
			<div class="fitem">
				<label>说明:</label> <input name="">
			</div>
			<div class="fitem">
				<label>截止日期:</label> <input name="">
			</div>
			<div class="fitem">
				<label>文件:</label> <input name="">
			</div>
			<div class="fitem">
				<label>课程:</label> <input name="">
			</div>
		</form>
	</div>
	<!-- 编辑作业表单 请自行设置非空 -->
	<div id="edit_dlg" class="dlg">
		<form id="edit_fm" class="fm" method="post">
			<div class="fitem">
				<label>标题:</label> <input name="">
			</div>
			<div class="fitem">
				<label>说明:</label> <input name="">
			</div>
			<div class="fitem">
				<label>截止日期:</label> <input name="">
			</div>
			<div class="fitem">
				<label>文件:</label> <input name="">
			</div>
			<div class="fitem">
				<label>课程:</label> <input name="">
			</div>
		</form>
	</div>
</body>
<script src="${ctx }/static/common/js-modules/easyui/jquery.min.js"></script>
<script
	src="${ctx }/static/common/js-modules/easyui/jquery.easyui.min.js"></script>
<script
	src="${ctx }/static/common/js-modules/easyui/locale/easyui-lang-zh_CN.js"></script>
<script
	src="${ctx }/static/common/js-modules/easyui/datagrid-detailview.js"></script>
<script src="${ctx }/static/common/js/home/teacher_job.js"></script>
</html>