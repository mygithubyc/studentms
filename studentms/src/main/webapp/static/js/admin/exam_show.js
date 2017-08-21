$(function(){
	// 改变左上角title 用于导航
    $('.easyui-layout').layout('panel','center').panel({
        title: '首页 > 考试管理 > 维护考试安排文件',
        iconCls: 'icon-page'
    });
    $('#dg').datagrid({
        url: '',
        height: 750,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        pagination: true,
        rownumbers: true,
        columns:[[
            {field: 'a', title: '说明', width: 200},
            {field: 'b', title: '文件名称', width: 100},
            
        ]],
        
        toolbar:'#toolbar',
    });
    $('#dlg').dialog({
        modal: true,
        closed: true,

        buttons:[{
        text: '提交',
        iconCls: 'icon-ok',
        handler: saveExam
    },{
        text: '取消',
        iconCls: 'icon-cancel',
        handler: function(){
            $('#dlg').dialog('close');
        }
    }]
    })
});
var url = '';
function newExam(){
    // 打开对话框 并 设置标题
    $('#dlg').dialog('open').dialog('setTitle','发布考试安排');
    // 清空表单数据
    $('#fm').form('clear');
    url = '';
}
function editExam(){
    var row = $('#dg').datagrid('getSelected');

    if (row) {
        $('#dlg').dialog('open').dialog('setTitle','修改考试安排');
        $('#fm').form('load',row);
        url = ''+row.jid;
    }
}
function removeExam(){
    var row = $('#dg').datagrid('getSelected');
    if (row) {
        $.messager.confirm('Confirm','你确定要删除这个安排?',function(r){
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
function saveExam(){
    $('#fm').form('submit',{
        url: url,
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