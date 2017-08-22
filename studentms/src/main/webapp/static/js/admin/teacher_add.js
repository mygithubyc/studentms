/* 
* @Author: anchen
* @Date:   2017-08-16 08:49:25
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-17 15:55:37
*/
var flag = false;
$(function(){
    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 教师管理 > 录入教师信息',
        iconCls: 'icon-page'
    });
    $('.content_div').panel({
        title: '教师信息',
        width: 500
    });
    $('#fm_name').validatebox({
        validateOnCreate: false,
        required: true
    });
    $('#fm_num').blur(function(event) {
        var username = $(this).val(),
            validate_span = $(this).next('span');
        validate_span.html('');  
        if(username.length !== 0){
            $.post(ctx+'/admin/dCheckUsername',{username: username},function(data){
            	var result = JSON.parse(data);
            	if(result.success){
            		flag = false;
            		validate_span.html('工号已存在!');
            	}else{
            		flag = true;
            		validate_span.html('可用');
            	}
            });
        }
    });
    $('#fm_birth').datebox();
    $('#fm_entrance').datebox({});
    $('#fm_id').validatebox({
        validateOnCreate: false,
        required: true

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
        method: 'post',
        
    });
    // 定义表单提交的事件
    $('.fm').form({        
        url: ctx+'/teacher/dAddTeacher',
        // 提交前事件定义  progress的进度条防止重复提交 + 表单中validbox的确认验证
        onSubmit: function(){
        	if(!flag){
        		return false;
        	}
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
            flag = false;
        }
    });
    // 保存点击事件
    $('.fm_save').click(function(event) {
        $('.fm').submit();
    });
    // 清空点击则清空
    $('.fm_clear').click(function(event) {

        $('.fm').form('clear');
        $('.fm table input[type=radio]').get(0).checked = true;
    });
});
