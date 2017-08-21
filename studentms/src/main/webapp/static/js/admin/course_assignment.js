/* 
* @Author: anchen
* @Date:   2017-08-16 14:02:50
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-17 14:29:10
*/

$(function(){
    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 课程管理 > 专业课程配置',
        iconCls: 'icon-page'
    });
    
    $('#search_do').linkbutton({
    	
    });
    // 分配状态可以
    $('#dg').datagrid({
        title: '分配情况',
        height: 750,
        url: ctx+'/course/showCourseAssign',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        columns: [[
            {field: 'departName', title: '系名称', width: 100},
            {field: 'schoolName', title: '所属学院', width: 100},
            {field: 'isAssign', title: '分配状态', width: 100, formatter: statusformatter},
            {field: 'departId', title: '操作', width: 100, align: 'center', formatter: rowformatter}
        ]],
        toolbar: '#toolbar',
        // data: [{
        //     depart_name: '计算机系',
        //     college_name: '计算机学院',
        //     status: 5,
        //     id: 1,
        // }]
    });
    
});
function statusformatter(value, row, index){
	if(value > 0){
		return "<p style='color: green'>已配置</p>";
	}else{
		return "<p style='color: gray'>未配置</p>";
	}
}
function rowformatter(value, row, index){
	if(row.isAssign===0){
		return "<a href=# onclick='assign_course("+index+")' >配置课程</a>";
	}else{
		return "<a href=# onclick='assignment_cancle("+index+")'>取消配置</a>";
	}
	
}
function assign_course(index){
	$('#dg').datagrid('selectRow', index);
	var row = $('#dg').datagrid('getSelected');
	if(row){
		location.href=ctx+"/course/assignCourse?id="+row.departId+"&name="+row.departName;
	}
}
function assignment_cancle(index){
	$('#dg').datagrid('selectRow', index);
    var row = $('#dg').datagrid('getSelected');
    $.messager.confirm('提示','确认要重新配置该系的课程?',function(r){
        if(r){
            // 填写删除的url 参数1
            $.post(ctx+'/courseAssign/cancleAssignByDepart', {id: row.departId}, function(result) {
                var result = JSON.parse(result);
                if (result.success) {
                	$('#dg').datagrid('reload');
                	$.messager.show({
                        title: '提示',
                        msg: '取消成功'
                    });
                }else{
                    $.messager.show({
                        title: 'Error',
                        msg: result.msg
                    });
                }
            });
        }
    });
}
function doSearch(){
    var depart_name = $('#search_depart').val();
    $('#dg').datagrid('load',{
        depart_name: depart_name
    });
}
