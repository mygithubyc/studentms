<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/WEB-INF/view/common/include_css.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/homework_teacher.css">
<title>My JSP 'teacher.jsp' starting page</title>
</head>
<body>
	<!-- 数据网格 这是某老师布置的 所以只能看到自己布置的 -->
	<table id="dg" title="布置作业" class="easyui-datagrid"
		style="width:100%;height:800px;margin-left:0;" url="${ctx }/job/getJobs" toolbar="#toolbar"
		pagination="true" rownumbers="true" fitColumns="true"
		singleSelect="true" striped="true">
		<thead>
			<tr>
				<th field="title" width="50px">标题</th>
				<th field="content" width="50px">内容</th>
				<th field="sendTime" width="50px" sortable="true" data-options={formatter:myformater} >发布日期</th>
				<th field="deadTime" width="50px" sortable="true"  data-options={formatter:myformater}  >截止日期</th>
				<th field="path" width="50px" data-options={formatter:fileFormater}>文件名</th>
			</tr>
		</thead>
	</table>
	<!-- 上面增删改查几个按钮 -->
	<div id="toolbar">
		<a href="#" onclick="newJob()" class="easyui-linkbutton"
			iconCls="icon-add" plain="true">新建作业</a> <a href="#"
			onclick="editJob()" class="easyui-linkbutton" iconCls="icon-edit"
			plain="true">修改作业</a> <a href="#" onclick="removeJob()"
			class="easyui-linkbutton" iconCls="icon-remove" plain="true">删除作业</a>
	</div>
	<!-- 点击上面几个按钮跳出的表单 -->
	<div id="dlg" class="easyui-dialog"
		style="width:500px;height:600px;padding:10px 20px" closed="true"
		buttons="#dlg-buttons">
		<div class="ftitle">作业信息</div>
		<form id="fm" method="post" enctype="multipart/form-data">
			<div class="fitem">
				<label>标题:</label> <input name="title" class="easyui-validatebox"
					required="true">
			</div>
			<div class="fitem">
				<label>内容:</label>
				<textarea name="content" class="easyui-validatebox"></textarea>
			</div>
			<div class="fitem">
				<label>截止日期:</label> <input name="deadtime" type="text"
					class="easyui-datebox" required="true">
			</div>
			<div class="fitem">
				<label>文件:</label> <input name="path" class="easyui-filebox" 
					data-options="prompt: '选择作业文件'" required="true">
			</div>
		</form>
	</div>
	<!-- 对应上面buttons所指向的 save cancle按钮 -->
	<div id="dlg-buttons">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="saveJob()">Save</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">Cancel</a>
	</div>
</body>
<jsp:include page="/WEB-INF/view/common/include_js.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctx }/static/js/homework_teacher.js"></script>
</html>
