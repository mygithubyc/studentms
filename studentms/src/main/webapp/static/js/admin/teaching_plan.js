/* 
* @Author: anchen
* @Date:   2017-08-17 10:52:36
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-17 14:29:30
*/

$(function(){
    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 课程管理 > 安排各班教学计划',
        iconCls: 'icon-page'
    });
    $('#dCombobox').combobox({
        editable: false,
        url: ctx+'/school/dCombobox',
        valueField: 'schoolId',
        textField: 'schoolName',
        method: 'post',
        onClick: function(rec){
        	$('#departCombobox').combobox({
        		url: ctx+'/department/departCombobox?schoolId='+rec.schoolId,
        	})
        }
    });
    $('#departCombobox').combobox({
    	editable: false,
    	valueField: 'departId',
        textField: 'departName',
        onClick: function(rec){
        	$('#dg').datagrid('load',{
            	departId: rec.departId
            });
        }
        
    });
    $('#dlg').dialog({
    	modal: true,
    	closed: true,
    	buttons:[{
            text: '保存',
            iconCls: 'icon-ok',
            handler: function(){
            	alert('保存');
            }
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                $('#dlg').dialog('close');
            }
        }]
    })
    // url写上 查看某个班级所属系的分配的课程  所以在没有选班级时 返回值为空即可
    $('#dg').datagrid({
        url: ctx+'/course/dCourseByDepart',
        title: '教学计划',
        height: 600,
        pagination: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'courseName', title: '课程名称', width: 100},
            {field: 'term', title: '授课学期', width: 100},
            {field: 'schoolName', title: '开课学院', width: 100},            
            {field: 'courseId', title: '操作', width: 100, formatter: rowformatter}
        ]],
        toolbar: '#toolbar',
        
    });
    
    
    
   
    // 最好由该学院的老师负责开课
    $('#fm_teacher').combogrid({
        required: true,
        editable: false,
        width: 180,
        panelWidth:300,
        fitColumns: true,
        url: '',
        idField: 'id',
        textField: 'name',
        columns: [[
            {field: 'id', title: 'ID', hidden: true},
            {field: 'username', title: '工号'},
            {field: 'name', title: '姓名'},
            {field: 'depart_name', title: '所属系', width: 100, align: 'center'}
            
        ]],
        data: [{
            id: 1,
            username: 'sy110',
            name: '张三',
            depart_name: '计算机系',
        }]

    });
    
});
// 暂时没写取消分配的操作
function rowformatter(value, row, index){
    return "<a href='#' onclick='assign_teacher("+index+")'>分配老师</a>";
}
function assign_teacher(index){
	$('#dg').datagrid('selectRow', index);
	var row = $('#dg').datagrid('getSelected');
	if(row){
		var departId = $('#departCombobox').val();
		$('#dlg').dialog('open');
		$('#fm').form('load', row);
		$('#class_combobox').combobox({
			url: ctx+'/class/dClassCombobox?departId='+departId,
			textField: 'className',
	        valueField: 'classId',
	        method: 'post',
	        editable: false,
		});
		$('#teacher_combobox').combogrid({
			url:ctx+'/class/dTeacherCombobox',
			idField:'teacherId',
			textField:'realName',
			fitColums: true,
			columns:[[
		          {field: '', title: '工号', width: 100},
		          {field: '', title: '姓名', width: 100},
		          {field: '', title: '所属系', width: 100},
			]]
		})
	}
}
