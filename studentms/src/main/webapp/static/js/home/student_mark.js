/* 
* @Author: anchen
* @Date:   2017-08-21 11:23:58
* @Last Modified by:   anchen
* @Last Modified time: 2017-08-21 11:31:52
*/

$(function(){
    $('#dg').datagrid({
        url: '',
        height: 750,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        pagination: true,
        rownumbers: true,
        columns:[[
            {field: 'a', title: '课程名', width: 100},
            {field: 'b', title: '任课老师', width: 100},
            {field: 'c', title: '成绩', width: 100},
            {field: 'd', title: '开课学院', width: 200},
        ]],
        
        toolbar:'#toolbar',
        
    });
})
function doSearch(){
    alert('查询');
}