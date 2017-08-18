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
    // url写上 查看某个班级所属系的分配的课程  所以在没有选班级时 返回值为空即可
    $('#dg').datagrid({
        url: '',
        title: '教学计划',
        height: 600,
        pagination: true,
        fitColumns: true,
        rownumbers: true,
        singleSelect: true,
        columns: [[
            {field: 'course_name', title: '课程名称', width: 100},
            {field: 'semester', title: '授课学期', width: 100},
            {field: 'name', title: '分配情况', width: 100},
            
            {field: 'id', title: '操作', width: 100, formatter: rowformatter}
        ]],
        toolbar: '#toolbar',
        data: [
            {course_name: 'C语言编程', name: '张三',semester: 1, id: 1}
        ]
    });
    
    
    // 三级联动  最后选定了班级触发表格的重新加载
    $('#fm_college_name').combobox({
        editable: false,
        url: '',
        valueField: 'id',
        textField: 'text',
        onSelect: function(rec){
            $('#fm_class_name').combobox('clear');
            $('#fm_depart_name').combobox('reload', 'xxx?id='+rec.id);
        }
    });
    $('#fm_depart_name').combobox({
        editable: false,
        valueField: 'id',
        textField: 'text',
        onSelect: function(rec){
            $('#fm_class_name').combobox('reload', 'xxx?id='+rec.id);
        }
    });
    $('#fm_class_name').combobox({
        editable: false,
        valueField: 'id',
        textField: 'text',
        onSelect: function(rec){
            $('#dg').datagrid('load',{
                id: rec.id
            });
        }
    });
    $('#dlg').dialog({
        closed: true,
        width:400,
        title: '分配教师'
    });
    $('#dlg #fm_course').textbox({
        width: 180,
        height: 24,
        editable: false,
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
// 暂时没写取消分配的操作
function rowformatter(value, row, index){
    return "<a href='#' onclick='assign_teacher()'>分配老师</a>&nbsp;<a href='#' >取消分配</a>";
}
function assign_teacher(){
    var row = $('#dg').datagrid('getSelected');
    if(row){
        $('#dlg').dialog('open');
        $('#dlg form').form('load', row);

    }
}