/* 
* @Author: anchen
* @Date:   2017-08-21 10:10:42
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-21 11:31:42
*/

$(function(){
    $('#dg').datagrid({
        url: '',
        height: 750,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        pagination: true,
        columns:[[
            {field: 'a', title: '开始时间', width: 100, align: 'center', halign: 'center'},
            {field: 'b', title: '结束时间', width: 100, align: 'center', halign: 'center'},
            {field: 'c', title: '文件名', width: 200, align: 'center', halign: 'center'},
        ]],
        
        toolbar:'#toolbar',
    })
});
function doSearch(){
    alert('方法在52行:点击查询');
}