/* 
* @Author: anchen
* @Date:   2017-08-20 22:02:58
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-21 11:32:08
*/

$(function(){
    // 筛选指定班级的作业   选择一个班级 联动datagrid  值一般为id 
    $('#class_combobox').combobox({
        url: '',
        textField: '',
        valueField: '',
        editable: false,
        onClick: function(rec){
            $('#dg').datagrid('load',{
                // classId: rec.classId
            })
        }
    });
    // 新增表单
    $('#add_dlg').dialog({
        title: '布置作业',
        closed: true,
        buttons: [{
            text: '新增',
            iconCls: 'icon-ok',
            handler: function(){
                // 点击新增按钮事件  
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
            		var formData = new FormData($("#add_fm")[0]);
            		$.ajax({
            			url : ctx + "/job/publishJob", // 请求到controller
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
            					$('#add_dlg').dialog('close');
            					// 获得JSON数据刷新界面
            					/*$.getJSON(ctx + "/job/refresh", function(data) {
            						$('#dg').datagrid('loadData', eval("(" + data + ")"));
            					});*/
            				}
            			},
            			error : function() {
            				window.alert("系统繁忙，请稍后再试");
            			}
            		});
            	} else {
            		window.alert("选择的文件必须为zip或者rar的压缩格式");
            	}
            	
                alert('新增作业');
            }
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                // 点击新增按钮事件  
                $('#add_dlg').dialog('close');
            }
        }]
    });
    // 编辑作业表单
    $('#edit_dlg').dialog({
        title: '修改作业',
        closed: true,
        buttons: [{
            text: '保存修改',
            iconCls: 'icon-ok',
            handler: function(){
                // 点击保存修改按钮事件  在这里写提交表单
                alert('编辑作业');
            }
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                // 点击新增按钮事件  在这里写提交表单
                $('#edit_dlg').dialog('close');
            }
        }]
    });
    
    
    // data为测试数据  请删除  并修改field
    // 警告 这里idField必须设置!  就是该表的主键  因为要通过他生成 id为 ddv-id 的datagrid二级表格  有4处row.id需要替换
    $('#dg').datagrid({
        url: ctx+'/job/getCommitByPid',
        height: 750,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        pagination: true,
        idField: 'id',
        columns:[[
            {field: 'title', title: '作业名', width: 100},
            {field: 'content', title: '说明', width: 100},
            {field: 'course', title: '课程', width: 100},
            {field: 'publishTime', title: '发布日期', width: 100},
            {field: 'deadTime', title: '截止日期', width: 100},
            {field: 'path', title: '文件名', width: 100},
           // {field: 'g', title: '提交情况', width: 100},
        ]],
        
        toolbar:'#toolbar',
        view: detailview,
        detailFormatter:function(index,row){
            return '<div style="padding:2px"><table id="ddv-' + row.id + '"></table></div>';
        },
        onExpandRow: function(index,row){
            $('#ddv-'+row.id).datagrid({
                url: ''+row.id,
                fitColumns:true,
                singleSelect:false,
                selectOnCheck: true,
                checkOnSelect: true,
                pagination: true,
                rownumbers:true,
                loadMsg:'',
                height:'auto',
                columns:[[
                    {field: 'ck', checkbox: true},
                    {field:'a',title:'学号', width:100},
                    {field:'b',title:'姓名', width:100},
                    {field:'c',title:'班级', width:100},
                    {field:'d',title:'提交时间', width:100},  
                    {field:'e',title:'文件名', width:100},                
                ]],
                data: [{
                    a: '第一次作业',
                    b: '第一次作业',
                    c: '第一次作业',
                    d: '第一次作业',
                    e: '第一次作业',

                }],
                onResize:function(){
                    $('#dg').datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    setTimeout(function(){
                        $('#dg').datagrid('fixDetailRowHeight',index);
                    },0);
                }
            });
            $('#dg').datagrid('fixDetailRowHeight',index);
        }
    });
});
// 自行设置查询条件id
function doSearch(){
    alert('方法在127行:点击查询');
}
// 布置作业
function newJob(){
    // 初始化清空表单
    $('#add_fm').form('clear');
    $('#add_dlg').dialog('open');

}
// 修改作业
function editJob(){
    var row = $('#dg').datagrid('getSelected');
    if(row){
        $('#edit_dlg').dialog('open');
        // 将选中行的对象赋给form 需要field和name对应
        $('#edit_fm').form('load', row);
    }
    
}
// 删除作业
function delJob(){
    var row = $('#dg').datagrid('getSelected');
    if(row){
        // $.messager.confirm('警告','确定要取消发布这次作业?',function(r){
        //     if(r){

        //     }
        // })
    }
}
// 下载一级表格所属的二级表格中选中的作业 (需要先选中一级表格的)  
function downloadAll(){
    // 获取一级表格选中的某行
    var row = $('#dg').datagrid('getSelected');
    if(row){
        // var checks =  $('#ddv-'+row.id).datagrid('getChecked');
        // console.log(checks);
        // $.each(checkedItems, function(index, item){
        //     item就是一行的对象
        // });
    }
}