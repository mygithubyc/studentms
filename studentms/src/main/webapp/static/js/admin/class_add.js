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
    
    $('#fm_entrance').numberbox({
        validateOnCreate: false,
        required: true
    });
    $('#fm_id').validatebox({
        validateOnCreate: false,
        required: true
    });
    // 二级联动
   
    $('#fm_college_name').combobox({

        url: '',
        editable: false,
        valueField: 'id',
        textField: 'text',
        onSelect: function(rec){
            $('#fm_depart_name').combobox('reload','xxx?id='+rec.id);
        }                
    });
    // 设定上搜索出不是班主任的老师   返回的格式如下data  发送的值最好调试下看看
    $('#fm_teacher').combogrid({
        editable: false,
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
        // data: [{
        //     id: 1,
        //     username: 'sy110',
        //     name: '张三',
        //     depart_name: '计算机系',
        // }]

    });
    $('#fm_depart_name').combobox({
        
        editable: false,
        valueField: 'id',
        textField: 'text',                
    });
    // 定义表单提交的事件
    $('.fm').form({
        
        url: 'xxx',
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
                // msg = '成功!';
            }else{
                // msg = result.msg;
            }
            $.messager.show({
                title: '执行结果',
                msg: msg
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