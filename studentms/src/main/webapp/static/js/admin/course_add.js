$(function(){
    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 教师管理 > 录入课程信息',
        iconCls: 'icon-page'
    });
    $('.content_div').panel({
        title: '课程信息',
        width: 500
    });
    $('#fm_name').validatebox({
        required: true,
    });
    $('#fm_depart_name').combobox({
        editable: false,
        url: ctx+'/school/dCombobox',
        valueField: 'schoolId',
        textField: 'schoolName',
        method: 'post'
    });
    // 定义表单提交的事件
    $('.fm').form({
        
        url: ctx+'/course/addCourse',
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
        	var result = JSON.parse(data);

            $.messager.progress('close');
            if (result.success) {
            	$.messager.show({
                    title: '执行结果',
                    msg: '新增成功!'
                });
            }else{
            	$.messager.show({
                    title: '执行结果',
                    msg: data.msg
                });
            }
            
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