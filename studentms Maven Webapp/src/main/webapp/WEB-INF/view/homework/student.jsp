<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/view/common/taglibs.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<jsp:include page="/WEB-INF/view/common/include_css.jsp"></jsp:include>
<link rel="stylesheet" type="text/css"
	href="${ctx }/static/css/homework_student.css">
<title>提交作业</title>
</head>
<body>
	<!-- 数据网格 学生可以看到所有老师布置的作业 搜索和排序的操作都将在该tabel指向的url操作中执行 -->
	<table id="dg_student" title="提交作业" class="easyui-datagrid"
		style="width:100%;height:800px;" url="${ctx }/job/stuGetJob" toolbar="#toolbar_student"
		pagination="true" rownumbers="true" fitColumns="true"
		singleSelect="true" striped="true">
		<thead>
			<tr>
				<th field="title" width="50px">标题</th>
				<th field="content" width="50px">内容</th>
				<th field="username" width="50px">老师</th>
				<th field="sendTime" width="50px" sortable="true" data-options={formatter:myformater}>发布日期</th>
				<th field="deadTime" width="50px" sortable="true" data-options={formatter:myformater}>截止日期</th>
				<th field="path" width="50px">文件名</th>
				<th field="status" width="50px" data-options={formatter:statusFormater}>是否已交</th>
			</tr>
		</thead>
	</table>
	<!-- 工具栏 -->
	<div id="toolbar_student">
		<div>
			老师:<input type="text" class="easyui-textbox" width="100px"
				id="teacherName" name="" value="" placeholder=""> 发布日期: <input
				class="easyui-datebox" style="width:100px" id="formDate">
			To: <input class="easyui-datebox" style="width:100px" id="toDate">
			 <a href="#" class="easyui-linkbutton"
				iconCls="icon-search" onclick="doSearch()">Search</a>
		</div>
		<a href="#" onclick="uploadForm()" class="easyui-linkbutton"
			iconCls="icon-ok" plain="true">选中要提交的作业</a>
	</div>
	<!-- 跳出的表单 -->
	<div id="dlg_student" class="easyui-dialog"
		style="width:500px;height:600px;padding:10px 20px;margin-left:0;" closed="true"
		buttons="#dlg-buttons_student">
		<div class="ftitle">作业信息</div>
		<form id="fm_student" method="post" novalidate>
			<div class="fitem">
				<label>标题:</label> <input class="easyui-validatebox" name="title"
					readonly="readonly">
			</div>
			<div class="fitem">
				<label>老师:</label> <input class="easyui-validatebox"
					name="username" readonly="readonly">
			</div>
			<div class="fitem">
				<label>内容:</label>
				<textarea name="content" class="easyui-validatebox"
					readonly="readonly"></textarea>
			</div>
			<div class="fitem">
				<label>文件:</label> <input name="cpath" class="easyui-filebox"
					data-options="prompt: '选择作业文件'" required="true">
			</div>
		</form>
	</div>
	<!-- 对应上面buttons所指向的 save cancle按钮 -->
	<div id="dlg-buttons_student">
		<a href="#" class="easyui-linkbutton" iconCls="icon-ok"
			onclick="upload()">提交/上传作业</a> <a href="#" class="easyui-linkbutton"
			iconCls="icon-cancel" onclick="javascript:$('#dlg').dialog('close')">取消</a>
	</div>
</body>
<jsp:include page="/WEB-INF/view/common/include_js.jsp"></jsp:include>
<script type="text/javascript"
	src="${ctx }/static/js/homework_student.js"></script>
</html>
