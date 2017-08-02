/* 
* @Author: anchen
* @Date:   2017-07-25 14:00:54
* @Last Modified by:   anchen
* @Last Modified time: 2017-07-25 15:57:45
*/


var url = '';
function newJob(){
    // 打开对话框 并 设置标题
    $('#dlg').dialog('open').dialog('setTitle','新建作业');
    // 清空表单数据
    $('#fm').form('clear');
    url = ctx+'/job/addJob';
}
function editJob(){
    var row = $('#dg').datagrid('getSelected');

    if (row) {
        $('#dlg').dialog('open').dialog('setTitle','编辑作业');
        $('#fm').form('load',row);
        url = ''+row.jid;
    }
}
function removeJob(){
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm','你确定要删除这行数据?',function(r){
            if (r) {

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
function saveJob(){
    $('#fm').form('submit',{
        url: url,
        data: $('#fm').serialize(),
        onSubmit: function(){
            return $(this).form('validate');
        },
        success: function(result){
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
        }
    });
}
