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
    $('#search_college').combobox({
        url: '',
        valueField: 'id',
        textField: 'text'
    });
    $('#search_do').linkbutton({});
    // 分配状态可以
    $('#dg').datagrid({
        title: '分配情况',
        height: 600,
        url: '',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        columns: [[
            {field: 'depart_name', title: '系名称', width: 100},
            {field: 'college_name', title: '所属学院', width: 100},
            {field: 'status', title: '分配状态', width: 100},
            {field: 'id', title: '操作', width: 100, align: 'center', formatter: rowformatter}
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
function rowformatter(value, row, index){
    return "<a href='#' class='easyui-linkbutton' iconCls='icon-edit' onclick='course_assignment("+index+")'>分配课程</a>&nbsp;<a href='#' class='easyui-linkbutton' iconCls='icon-cancle' onclick='assignment_cancle()'>取消分配</a>";
}

function course_assignment(index){

    var row = $('#dg').datagrid('getSelected');
    if(row){
        // 跳转到分配课程页面
        location.href = '';
    }
}
function assignment_cancle(row){
    var row = $('#dg').datagrid('getSelected');
    $.messager.confirm('提示','确认要重新配置该系的课程?',function(r){
        if(r){
            // 填写删除的url 参数1
            $.post('', {id: row.id}, function(result) {
                var result = JSON.parse(result);
                if (result.success) {
                    $('#dlg').dialog('close');
                    $('#dg').datagrid('reload');
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
    var college_name = $('#search_college').val(),
        depart_name = $('#search_depart').val();
    $('#dg').datagrid('load',{
        college_name: college_name,
        depart_name: depart_name
    });
}