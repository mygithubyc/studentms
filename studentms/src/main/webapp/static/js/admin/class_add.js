/* 
* @Author: anchen
* @Date:   2017-08-16 09:24:28
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-17 14:13:38
*/
$(function(){
    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 教师管理 > 录入班级信息',
        iconCls: 'icon-page'
    });
    $('.content_div').panel({
        title: '班级信息',
        width: 500
    });
    $('#fm_name').validatebox({
        validateOnCreate: false,
        required: true
    });
    
    $('#fm_entrance').combobox({
        validateOnCreate: false,
        required: true,
        valueField: 'id',
        textField: 'text',
        editable: false,
        data:[
           {id: 2010, text: 2010},
           {id: 2011, text: 2011},
           {id: 2012, text: 2012},
           {id: 2013, text: 2013},
           {id: 2014, text: 2014},
           {id: 2015, text: 2015},
           {id: 2016, text: 2016},
           {id: 2017, text: 2017},
           {id: 2018, text: 2018},
        ]
    });
    $('#fm_college_name').combobox({
    	editable: false,
        required: true,
        url: ctx+'/school/dCombobox',
        valueField: 'schoolId',
        textField: 'schoolName',
        method: 'post',
        onSelect: function(rec){     
            $('#fm_depart_name').combobox({
            	url: ctx+'/department/departCombobox?schoolId='+rec.schoolId
            });
        }
    });
    $('#fm_depart_name').combobox({
        editable: false,
        required: true,
        valueField: 'departId',
        textField: 'departName',
        
        
    });
    
    $('#fm_teacher').combogrid({
		url:ctx+'/class/dNotTeacherCombobox',
		idField:'teacherId',
		textField:'realName',
		editable: false,
		fitColums: true,
		panelWidth:400,
		required: true,
		method: 'post',
		columns:[[
	          {field: 'username', title: '工号', width: 100},
	          {field: 'realName', title: '姓名', width: 100},
	          {field: 'departName', title: '所属系', width: 200},
		]]
	});
   
    // 定义表单提交的事件
    $('.fm').form({
        
        url: ctx+'/class/dAddClass',
        // 提交前事件定义  progress的进度条防止重复提交 + 表单中validbox的确认验证
        onSubmit: function(){
            $.messager.progress();
            var isValid = $(this).form('validate');
            if (!isValid) {
                $.messager.progress('close');
            }
            return isValid;
        },
        // 有返回值触发success JSON.parse看情况去留
        success: function(data){
            var result = JSON.parse(data),
                msg = '';
            
            $.messager.progress('close');
            
            if (result.success) {
            	
                msg = '成功!';
            }else{
                msg = '失败!';
            }
            $.messager.show({
                title: '执行结果',
                msg: msg
            });
            $('#fm_teacher').combogrid({
            	url:ctx+'/class/dNotTeacherCombobox',
            });
        }
    });
    // 保存点击事件
    $('.fm_save').click(function(event) {
        $('.fm').submit();
    });
    // 清空点击则清空
    $('.fm_clear').click(function(event) {

        $('.fm').form('clear');
    });
});