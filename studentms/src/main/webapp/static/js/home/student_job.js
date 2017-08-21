/* 
* @Author: anchen
* @Date:   2017-08-21 10:53:32
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-21 10:55:28
*/

$(function(){
    // 筛选指定班级的作业   选择一个老师 联动datagrid  值一般为id 
    $('#teacher_combobox').combobox({
        url: '',
        textField: '',
        valueField: '',
        editable: false,
        onClick: function(rec){
            $('#dg').datagrid('load',{
                // classId: rec.classId
            })
        }
    });

    $('#dlg').dialog({
        modal: true,
        closed: true,
        buttons:[{
            text: '保存',
            iconCls: 'icon-ok',
            handler: saveJob,
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                $('#dlg').dialog('close');
            }
        }]
    });
    $('#dg').datagrid({
        url: '',
        height: 750,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        pagination: true,
        columns:[[
            {field: 'a', title: '作业名', width: 100},
            {field: 'b', title: '说明', width: 100},
            {field: 'c', title: '发布日期', width: 100},
            {field: 'd', title: '截止日期', width: 100},
            {field: 'e', title: '发布老师', width: 100},
            {field: 'f', title: '文件名', width: 100},
            {field: 'g', title: '提交状态', width: 100},
        ]],
        toolbar:'#toolbar',
    });
});
var url = '';
// 提交作业
function doJob(){
    url = '';
    $('#dlg').dialog('open').dialog('setTitle','提交作业');
}
// 重新提交作业
function redoJob(){
    var row = $('#dg').datagrid('getSelected');
    if(row){
        // 附上提交记录表的主键
        // url = ''+row.id
        $('#dlg').dialog('open').dialog('setTitle','重新提交作业');
        // 将选中的值给form
        $('#fm').form('load', row);
    }
}
// 提交表单按钮   带上url
function saveJob(){
    alert('120行:提交表单');
}

function doSearch(){
    alert('查询');
}