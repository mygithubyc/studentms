//时间戳转为时间格式
Date.prototype.format = function(format) {
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	}
	if (/(y+)/.test(format))
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(format))
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
	return format;
}
function formatDatebox(value) {
	if (value == null || value == '') {
		return '';
	}
	var dt;
	if (value instanceof Date) {
		dt = value;
	} else {
		dt = new Date(value);
	}
	return dt.format("yyyy-MM-dd"); // 扩展的Date的format方法(上述插件实现)
}

/**
 * @多文件下载
 */
function mutilDownlload() {
	var rows = $('#dg').datagrid('getSelections');
	var fileNames = '';
	for (var i = 0; i < rows.length; i++) {
		fileNames += rows[i].path + ',';
	}
	// 配置超链接
	window.location.href = ctx + "/job/mutiDownload?fileNames=" + fileNames;
}

/** ***Excel表格上传**** */
function newExcel() {
	$('#import').dialog('open').dialog('setTitle', '导入Excel');
	$('#uploadForm').form('clear');
}
// 检查Excel
function cheakExcel() {
	var filePath = $("#upExcel").val();
	window.alert("cheakExcel filePath" + filePath);
	var suffix = filePath.substr(filePath.lastIndexOf("."));
	window.alert("suffix:" + suffix)
	if ("" == filePath) {
		alert("选择需要导入的Excel文件！");
		return false;
	}
	if (".xls" != suffix && ".xlsx" != suffix) {
		alert("选择Excel格式的文件导入！");
		return false;
	}
	return true;
}
// 传入Excel

function importExcel() {
	alert("importExcel is ok!");
	var file = document.getElementById('filebox_file_id_2').files[0]; // id位1可能会冲突记得修改
	// 判断文件是否合法
	if (file == null) {
		alert("文件不能为空");
		return;
	}
	var fileName = file.name;
	var fileType = fileName.substring(fileName.lastIndexOf('.'), fileName.length);
	
	window.alert("fileType:  " + fileType);
	if (fileType == ".xls" || fileType == ".xlsx") {
		// 获取form数据
		var formData = new FormData($("#uploadForm")[0]);
		$.ajax({
			url : ctx + "/users/importExcel", // 请求到controller
			type : "POST",
			data : formData, // 正表单对象传过去
			async : false,
			cache : false,
			dataType : "json", // 要返回JSON数据不要忘了它，否则会报undefined
			contentType : false,
			processData : false,
			success : function(data) {
				// 上传成功后将控件内容清空，并显示上传成功信息
				var jsonObj = eval("(" + data + ")"); // 转换为json对象
				window.alert("json.success  " + jsonObj.success);
				if (jsonObj.success == "success") {
					$('#ipmportExcel').dialog('close');
					
				}
			},
			error : function() {
				window.alert("系统繁忙，请稍后再试");
			}
		});
	} else {
		alert("选择的文件类型不对");
		return;
	}
}


function exportExcel(){
	alert("exportExcel is ok!");
	
	window.location.href=ctx + "/users/exportExcel?object=userInfo.xlsx";
}

/*
 * $("#importExcel").click(function() { window.alert("ok"); if (cheakExcel()) {
 * $("#uploadForm").$.fn.ajaxSubmit({ url : ctx + "/users/importExcel", dataType :
 * "json" }); } });
 */

/**
 * @下载方法
 * @param val
 * @param row
 * @returns {String}
 */
function download(val, row) {
	// return '<a href="#" onclick="constructionManager(\'' + row.id+
	// '\')">'+row.path+'</a> '
	return '<a href="' + ctx + "/job/download?fileName=" + row.path + '" >'
			+ row.path + '</a>';
}

// 定义easyui的数据表格，默认填充表格表头样式
$('#dg').datagrid({
	/*
	 * url : ctx + '/job/refresh', //url需要用单引号 method : 'GET',
	 */
	columns : [ [ {
		field : 'jid',
		title : '作业编号',
		width : 50
	}, {
		field : 'title',
		title : '标题',
		width : 50
	}, {
		field : 'content',
		title : '内容',
		width : 50
	}, {
		field : 'sendTime',
		title : '发送时间',
		width : 50,
		formatter : formatDatebox
	}, {
		field : 'deadTime',
		title : '截止日期',
		width : 50,
		formatter : formatDatebox
	}, {
		field : 'path',
		title : '文件',
		width : 50,
		formatter : download
	}, ] ]
});
// 获得JSON数据
$.getJSON(ctx + "/job/refresh", function(data) {
	$('#dg').datagrid('loadData', eval("(" + data + ")"));
});

var url = '';
function newJob() {
	// 打开对话框 并 设置标题
	$('#dlg').dialog('open').dialog('setTitle', '新建作业');
	// 清空表单数据
	$('#fm').form('clear');
	url = '';
}
function editJob() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$('#dlg').dialog('open').dialog('setTitle', '编辑作业');
		$('#fm').form('load', row);
		url = '' + row.jid;
	}
}
function removeJob() {
	var row = $('#dg').datagrid('getSelected');
	if (row) {
		$.messager.confirm('Confirm', '你确定要删除这行数据?', function(r) {
			if (r) {

				$.post('', {
					id : row.jid
				}, function(result) {
					var result = JSON.parse(result);
					if (result.success) {
						$('#dlg').dialog('close');
						$('#dg').datagrid('reload');
					} else {
						$.messager.show({
							title : 'Error',
							msg : result.msg
						});
					}
				});
			}

		});
	}
}

/**
 * @发布作业
 */
function send() {
	alert("send() is ok");
	/* var file = $('input[name="file"][type="file"]').prop('files')[0]; // 获取文件 */
	var file = document.getElementById('filebox_file_id_1').files[0];
	window.alert("file  " + file);
	if (file == null) {
		window.alert("文件不能为空");
		return;
	}
	var fileName = file.name;
	window.alert("file.name  " + file.name);
	// 获取文件类型名称
	var fileType = fileName.substring(fileName.lastIndexOf('.'),
			fileName.length);

	if (fileType == ".zip" || fileType == ".rar") {

		// 获取form数据
		var formData = new FormData($("#fm")[0]);
		$.ajax({
			url : ctx + "/job/upload", // 请求到controller
			type : "POST",
			data : formData, // 正表单对象传过去
			async : false,
			cache : false,
			dataType : "json", // 要返回JSON数据不要忘了它，否则会报undefined
			contentType : false,
			processData : false,
			success : function(data) {
				// 上传成功后将控件内容清空，并显示上传成功信息
				var jsonObj = eval("(" + data + ")"); // 转换为json对象
				window.alert("json.success  " + jsonObj.success);
				if (jsonObj.success == "success") {
					$('#dlg').dialog('close');
					// 获得JSON数据刷新界面
					$.getJSON(ctx + "/job/refresh", function(data) {
						$('#dg').datagrid('loadData', eval("(" + data + ")"));
					});
				}
			},
			error : function() {
				window.alert("系统繁忙，请稍后再试");
			}
		});
	} else {
		window.alert("选择的文件必须为zip或者rar的压缩格式");
	}
}

/*
 * function saveJob(){
 * 
 * $("#fm").form("submit",{ url: "job/upload", type:"POST", onSubmit:
 * function(){ return $(this).form("validate"); },
 * data:{"title":$("#title").val()}, success: function(result){ var result =
 * JSON.parse(result); if (result.success) { $("#dlg").dialog("close");
 * $("#dg").datagrid("reload"); }else{ $.messager.show({ title: "Error", msg:
 * result.msg }); } } }); }
 */

/** **老师上传作业*** */
/*
 * $(document).ready(function() { $("#fm").submit(function(evt) {
 * evt.preventDefault(); $("#fm").ajaxSubmit({ url : ctx + "/job/upload",
 * dataType : "json", type : "POST", success : function(data) { }, error :
 * function(error) { } }); });
 * 
 * });
 */
