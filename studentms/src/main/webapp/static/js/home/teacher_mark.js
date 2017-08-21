/* 
* @Author: anchen
* @Date:   2017-08-21 08:33:10
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-21 08:39:37
*/

$(function(){
    // 筛选指定班级的作业   选择一个班级 datagrid会进行查询
    $('#class_combobox').combobox({
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
    // 新增表单
    $('#add_dlg').dialog({
        title: '录入成绩',
        closed: true,
        buttons: [{
            text: '确认录入',
            iconCls: 'icon-ok',
            handler: function(){
                // 点击确认录入按钮事件 因为没有修改已录入 建议加个确认 
                alert('确认录入');
            }
        },{
            text: '取消',
            iconCls: 'icon-cancel',
            handler: function(){
                // 点击新增按钮事件  
                $('#add_dlg').dialog('close');
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
            {field: 'a', title: '课程名', width: 100},
            {field: 'b', title: '班级', width: 100},
            {field: 'c', title: '录入时间', width: 100},
            {field: 'd', title: '备注', width: 200},
        ]],
        toolbar:'#toolbar',
        
    });

});
function enterMark(){
    $('#add_dlg').dialog('open');
}
function doSearch(){
    alert('方法在63行:点击查询');
}