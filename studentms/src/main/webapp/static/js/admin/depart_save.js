/* 
* @Author: anchen
* @Date:   2017-08-15 22:04:05
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-17 10:38:38
*/
$(function(){
    // 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 院系管理 > 维护院系信息',
        iconCls: 'icon-page'
    });
    $('#dg').datagrid({
        url: '',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers: true,
        pagination: true,
        columns: [[
            {field: 'depart_name', title: '系名称', width: 100, align: 'center'},
            {field: 'college_name', title: '所属学院', width: 100, align: 'center'},
            {field: 'id', title: '操作', width: 100, formatter: rowformatter, align: 'center'}
        ]],
        toolbar: '#toolbars'

        
    });
    $('#college_name').combobox({
        editable: false,
        // url: '',
        // textField: 'id',
        // valueField: 'text'
    });
    $('#search_a').linkbutton({
        iconCls: 'icon-search',
        onClick: doSearch
    });
    $('#dlg').dialog({
        title: '修改院系',
        closed: true,
        width: 400,
    });
    // 这里是学院下拉框的设置 返回格式 [{"id": 1,"text": "计算机系"}]  
    $('#fm_college_name').combobox({
        width: 180,
        height: 24,
        required: true,
        editable: false,
        // url: '',
        // textField: 'id',
        // valueField: 'text'
    });
    $('#fm_depart_name').validatebox({
        required: true
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
            var result = JSON.parse(data);
            $.messager.progress('close');
            if (result.success) {
                $('#dlg').dialog('close');
                $('#dg').datagrid('reload');
            }else{
                $.messager.show({
                    title: '执行结果',
                    msg: msg
                });
            }
            
        }
    });
    // 保存点击事件
    $('.fm_save').click(function(event) {
        $('#dlg .fm').submit();
    });
    // 清空点击则清空
    $('.fm_clear').click(function(event) {

        $('#dlg .fm').form('clear');
    });
});
// 查询操作
function doSearch(){
    var college_name = $('#college_name').val(),
        depart_name = $('#depart_name').val();
    $('#dg').datagrid('load',{
        college_name: college_name,
        depart_name: depart_name
    });
}
// 将最后一行转换为俩个a标签
function rowformatter(value, row, index){
    return "<a href='#' onclick='editDepart("+index+")'>编辑</a>&nbsp;<a href='#' onclick='deleteDepart("+index+")'>删除</a>";
}
// 编辑列
function editDepart(index){
    $('#dg').datagrid('selectRow', index);
    var row = $('#dg').datagrid('getSelected');
    if(row){
        $('#dlg').dialog('open');
        $('#dlg .fm').form('load', row);
    }
}
// 删除列
function deleteDepart(index){
    $('#dg').datagrid('selectRow', index);
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm','你确定要删除这行数据?',function(r){
            if (r) {
                // 填写删除的url 参数1
                $.post('', {id: row.jid}, function(result) {
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
}